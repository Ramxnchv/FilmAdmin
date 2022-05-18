"use strict"

function generarMapa() { 
    crearAsientos();
}

function enviarAsientos(event){
    event.preventDefault();
    postAsientos();
}

var asientosSeleccionados = [];
var asientos = [];

async function crearAsientos(){

    let respuesta = await getAsientos();

    let id = 1;
    let FILAS_SALA = 4;
    let COLUMNAS_SALA = 12;
    let asientosOcupados = [];

    if(respuesta.length !== 0){
        id = respuesta[0].sesion.sala.asientos[0].id;
        FILAS_SALA = respuesta[0].sesion.sala.filas;
        COLUMNAS_SALA = respuesta[0].sesion.sala.columnas;
        for (let i = 0; i < respuesta.length;i++){
            asientosOcupados = respuesta[i].asientos.length!== 0 ? respuesta[i].asientos.map(a => a.id).concat(...asientosOcupados) : [];   
        }
        asientos = respuesta[0].sesion.sala.asientos.map(function(a){
            if(asientosOcupados.includes(a.id)){
                return {
                    id: a.id,
                    columna: a.columna,
                    fila: a.fila,
                    estado: "ocupado"
                }
            }else{
                return {
                    id: a.id,
                    columna: a.columna,
                    fila: a.fila,
                    estado: "libre"
                }
            }   
        })
    
        console.log(asientos)
    }

    

    const svgns = "http://www.w3.org/2000/svg";

    let posX = 0;
    let posY = 0;
    for(let i=0;i<FILAS_SALA;i++){
        posY += 50;
        posX = 0;
        for(let j=0;j<COLUMNAS_SALA;j++){
            posX += 50;
            let circle = document.createElementNS(svgns, 'circle');
            circle.id = id;
            circle.setAttributeNS(null, 'cx', posX);
            circle.setAttributeNS(null, 'cy', posY);
            circle.setAttributeNS(null, 'r', 20);
            if (!asientosOcupados.includes(id)){
                circle.setAttributeNS(null, 'style', 'fill: #92bcea; stroke: black; stroke-width: 3px;' );
                circle.addEventListener("click", e => {actualizarAsiento(event.target.id)});
                circle.addEventListener("contextmenu", e => {eliminarAsiento(event,event.target.id)});
            }else{
                circle.setAttributeNS(null, 'style', 'fill: red; stroke: black; stroke-width: 3px;' );
                circle.addEventListener("click", e => {asientoOcupado()});
            }
            document.getElementById("asientos").appendChild(circle);
            id++;
        }
    }
}

function asientoOcupado() {
    alert("Este asiento está ocupado, selecciona otro");
}

function actualizarAsiento(id) {
    if (asientos[+id-1].estado !== "seleccionado") {
        document.getElementById(id).style.fill = "yellow";
        asientos[+id-1].estado = "seleccionado"
        asientosSeleccionados.push(+id);
        let valor = +(document.getElementById("inputcantidad").value) + 1;
        document.getElementById("inputcantidad").value = valor;
        actualizarPrecio(valor);
        document.getElementById("botoncomprar").disabled = false;
    }
}

function eliminarAsiento(e, id) {
    if (asientos[+id-1].estado === "seleccionado"){
        e.preventDefault();
        document.getElementById(id).style.fill = "#92bcea";
        asientos[+id-1].estado = "libre"
        let asientosNuevos = asientosSeleccionados.filter(e => e!==id);
        asientosSeleccionados = asientosNuevos;
        let valor = +(document.getElementById("inputcantidad").value) - 1;
        document.getElementById("inputcantidad").value = valor;
        actualizarPrecio(valor);
        if(asientosSeleccionados.length === 0){document.getElementById("botoncomprar").disabled = true;}
    }
}

function botonmass(){
    const primerAsiento = asientos.find(asiento => asiento.estado !== "seleccionado" && asiento.estado !== "ocupado");
    const primerAsientoLibre = document.getElementById(primerAsiento.id);
    asientos[+primerAsiento.id-1].estado = "seleccionado";
    primerAsientoLibre.style.fill="yellow";
    asientosSeleccionados.push(+primerAsientoLibre.id);
    let valor = +(document.getElementById("inputcantidad").value) + 1;
    document.getElementById("inputcantidad").value = valor;
    actualizarPrecio(valor);
    document.getElementById("botoncomprar").disabled = false;
}

function botonmenoss(){
    const primerAsiento = [...asientos].reverse().find(asiento => asiento.estado === "seleccionado")
    const primerAsientoLibre = document.getElementById(primerAsiento.id);
    asientos[+primerAsiento.id-1].estado = "libre";
    primerAsientoLibre.style.fill="#92bcea";
    let asientosNuevos = asientosSeleccionados.filter(e => e!=primerAsientoLibre.id);
    asientosSeleccionados = asientosNuevos;
    let valor = +(document.getElementById("inputcantidad").value) - 1;
    document.getElementById("inputcantidad").value = valor;
    actualizarPrecio(valor);
    if(asientosSeleccionados.length === 0){document.getElementById("botoncomprar").disabled = true;}
}

function actualizarPrecio(valor){
    let precioentrada = +(document.getElementById("precioentrada").innerHTML);
    let subtotal = precioentrada *  valor;
    document.getElementById("subtotal").innerHTML = subtotal.toFixed(2);
    document.getElementById("preciofinal").innerHTML = subtotal.toFixed(2);
}

async function getAsientos(){
    try{
        //obtenemos la sesion del query string de /compra-entradas?sesion={sesion}
        const params = new URLSearchParams(window.location.search);
        let sesion = params.get('sesion');
        let asientos = await go(`${config.rootUrl}/entradas/asientos/${sesion}`, "GET");
        return asientos;
    }catch(e){
        console.log(e);
    }
}

async function postAsientos(){
    try{
        //obtenemos los asientos seleccionados y los enviamos a /compra-entradas?sesion={sesion}
        const params = new URLSearchParams(window.location.search);
        let sesion = params.get('sesion');
        let asientos = {asientos: asientosSeleccionados, numeroasientos: asientosSeleccionados.length};
        let entrada = await go(`${config.rootUrl}/entradas/compra-entradas/${sesion}`, "POST", asientos);
        window.location.href = `${config.rootUrl}/entradas/${entrada.id}`;
    }catch(e){
        console.log(e);
    }
}