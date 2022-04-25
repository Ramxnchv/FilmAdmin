async function comprobarEntrada(event) {
    event.preventDefault();
    let code = document.getElementById("codigoentrada").value;
    let entrada = null;
    entrada = await go(config.rootUrl + `/entradas/info/${code}`, "GET");
    if (entrada && !entrada[0].validate) {
        var myModal = new bootstrap.Modal(document.getElementById('modalButacas'), {
            keyboard: false
        })
        myModal.show();
        console.log(new Date(entrada[0].hora).getHours())
        document.getElementById('tituloModal').innerHTML = `Entrada ${code} para ${entrada[0].pelicula} - ${new Date(entrada[0].hora).getHours()}:${new Date(entrada[0].hora).getMinutes() < 10 ? '0' + new Date(entrada[0].hora).getMinutes() : new Date(entrada[0].hora).getMinutes()} - ${entrada[0].cine} - ${entrada[0].sesion.sala.nombre}`;

        let id = 1;
        let FILAS_SALA = 4;
        let COLUMNAS_SALA = 12;
        let asientosOcupados = [];

        if (entrada.length !== 0) {
            id = entrada[0].sesion.sala.asientos[0].id;
            FILAS_SALA = entrada[0].sesion.sala.filas;
            COLUMNAS_SALA = entrada[0].sesion.sala.columnas;
            for (let i = 0; i < entrada.length; i++) {
                asientosOcupados = entrada[i].asientos.length !== 0 ? entrada[i].asientos.map(a => a.id).concat(...asientosOcupados) : [];
            }
        }

        const svgns = "http://www.w3.org/2000/svg";

        let posX = 0;
        let posY = 0;
        for (let i = 0; i < FILAS_SALA; i++) {
            posY += 50;
            posX = 0;
            for (let j = 0; j < COLUMNAS_SALA; j++) {
                posX += 50;
                let circle = document.createElementNS(svgns, 'circle');
                circle.id = id;
                circle.setAttributeNS(null, 'cx', posX);
                circle.setAttributeNS(null, 'cy', posY);
                circle.setAttributeNS(null, 'r', 20);
                if (!asientosOcupados.includes(id)) {
                    circle.setAttributeNS(null, 'style', 'fill: #92bcea; stroke: black; stroke-width: 3px;');
                } else {
                    circle.setAttributeNS(null, 'style', 'fill: red; stroke: black; stroke-width: 3px;');
                }
                document.getElementById("asientos").appendChild(circle);
                id++;
            }
        }

    } else if(entrada[0].validate){
        let formcomprobar = document.getElementById('formcomprobar');
        let alertdiv = document.createElement("div");
        let textdiv = document.createElement('div');
        let svgicon = document.createElementNS("http://www.w3.org/2000/svg", 'svg');
        let use = document.createElementNS("http://www.w3.org/2000/svg", 'use');
        svgicon.setAttribute('class', 'bi flex-shrink-0 me-2');
        svgicon.setAttribute('width', 24);
        svgicon.setAttribute('height', 24);
        svgicon.setAttribute('role', 'img');
        svgicon.setAttribute('aria-label', 'Danger:');
        use.setAttribute('href', '#exclamation-triangle-fill');
        alertdiv.setAttribute('class', 'alert alert-danger d-flex align-items-center mt-4');
        alertdiv.setAttribute('role', 'alert');
        textdiv.appendChild(document.createTextNode("ESTE CÓDIGO YA HA SIDO VALIDADO"));
        svgicon.appendChild(use);
        alertdiv.appendChild(svgicon);
        alertdiv.appendChild(textdiv);
        formcomprobar.appendChild(alertdiv);
    } else {
        let formcomprobar = document.getElementById('formcomprobar');
        let alertdiv = document.createElement("div");
        let textdiv = document.createElement('div');
        let svgicon = document.createElementNS("http://www.w3.org/2000/svg", 'svg');
        let use = document.createElementNS("http://www.w3.org/2000/svg", 'use');
        svgicon.setAttribute('class', 'bi flex-shrink-0 me-2');
        svgicon.setAttribute('width', 24);
        svgicon.setAttribute('height', 24);
        svgicon.setAttribute('role', 'img');
        svgicon.setAttribute('aria-label', 'Danger:');
        use.setAttribute('href', '#exclamation-triangle-fill');
        alertdiv.setAttribute('class', 'alert alert-danger d-flex align-items-center mt-4');
        alertdiv.setAttribute('role', 'alert');
        textdiv.appendChild(document.createTextNode("ESTE CÓDIGO NO CORRESPONDE A NINGUNA ENTRADA"));
        svgicon.appendChild(use);
        alertdiv.appendChild(svgicon);
        alertdiv.appendChild(textdiv);
        formcomprobar.appendChild(alertdiv);
    }
}

async function validarEntrada() {
    let code = document.getElementById("codigoentrada").value;
    let validate = await go(`${config.rootUrl}/entradas/validate/${code}`, "POST");
    if (validate[0].validate) {
        let formcomprobar = document.getElementById('formcomprobar');
        let alertdiv = document.createElement("div");
        let textdiv = document.createElement('div');
        let svgicon = document.createElementNS("http://www.w3.org/2000/svg", 'svg');
        let use = document.createElementNS("http://www.w3.org/2000/svg", 'use');
        svgicon.setAttribute('class', 'bi flex-shrink-0 me-2');
        svgicon.setAttribute('width', 24);
        svgicon.setAttribute('height', 24);
        svgicon.setAttribute('role', 'img');
        svgicon.setAttribute('aria-label', 'Success:');
        use.setAttribute('href', '#check-circle-fill');
        alertdiv.setAttribute('class', 'alert alert-success d-flex align-items-center mt-4');
        alertdiv.setAttribute('role', 'alert');
        textdiv.appendChild(document.createTextNode("ENTRADA VALIDADA CORRECTAMENTE"));
        svgicon.appendChild(use);
        alertdiv.appendChild(svgicon);
        alertdiv.appendChild(textdiv);
        formcomprobar.appendChild(alertdiv);
    }
}

async function verAsientos(event) {
    var myModal = new bootstrap.Modal(document.getElementById('modalButacasSesiones'), {
        keyboard: false
    })
    myModal.show();
    let sesion = event.target.parentNode.parentNode.id;
    let respuesta = await go(`${config.rootUrl}/entradas/asientos/${sesion}`, "GET");

    let id = 1;
    let FILAS_SALA = 4;
    let COLUMNAS_SALA = 12;
    let asientosOcupados = [];

    if (respuesta.length !== 0) {
        id = respuesta[0].sesion.sala.asientos[0].id;
        FILAS_SALA = respuesta[0].sesion.sala.filas;
        COLUMNAS_SALA = respuesta[0].sesion.sala.columnas;
        for (let i = 0; i < respuesta.length; i++) {
            asientosOcupados = respuesta[i].asientos.length !== 0 ? respuesta[i].asientos.map(a => a.id).concat(...asientosOcupados) : [];
        }
    }

    const svgns = "http://www.w3.org/2000/svg";

    let posX = 0;
    let posY = 0;
    for (let i = 0; i < FILAS_SALA; i++) {
        posY += 50;
        posX = 0;
        for (let j = 0; j < COLUMNAS_SALA; j++) {
            posX += 50;
            let circle = document.createElementNS(svgns, 'circle');
            circle.id = id;
            circle.setAttributeNS(null, 'cx', posX);
            circle.setAttributeNS(null, 'cy', posY);
            circle.setAttributeNS(null, 'r', 20);
            if (!asientosOcupados.includes(id)) {
                circle.setAttributeNS(null, 'style', 'fill: #92bcea; stroke: black; stroke-width: 3px;');
            } else {
                circle.setAttributeNS(null, 'style', 'fill: red; stroke: black; stroke-width: 3px;');
            }
            document.getElementById("asientosSesiones").appendChild(circle);
            id++;
        }
    }

}

async function filtroCine(event) {
    let cineId = event.target.value;
    let input, filter, table, tr;
    input = event.target;
    filter = input.value;
    table = document.getElementById("peliculas");
    tr = table.getElementsByTagName("tr");
    for (let i = 0; i < tr.length; i++) {
        if (tr[i]) {
            if (cineId === "0") {
                tr[i].style.display = "";
            } else {
                tr[i].classList.contains(`cine-${cineId}`) ? tr[i].style.display = "" : tr[i].style.display = "none";
            }
        }
    }

    //Obtenemos las salas del cine seleccionado para habilitar el filtro
    let salas = await go(config.rootUrl + `/peliculas/salas/${+cineId}`, "GET");
    if (document.getElementById("sala-selector").children.length > 1) {
        let options = document.getElementById("sala-selector");
        let selectoption = document.getElementById("sala-selector").children[0];
        while (options.firstChild) {
            options.removeChild(options.firstChild);
        }
        options.appendChild(selectoption);
    }
    salas.forEach(s => {
        document.getElementById("sala-selector").insertAdjacentHTML("beforeend", `<option value="${s.id}">${s.nombre}</option>`)
    })
    document.getElementById("sala-selector").removeAttribute("disabled");
}

function filtroSala(event) {
    let salaId = event.target.value;
    let input, filter, table, tr;
    input = event.target;
    filter = input.value;
    table = document.getElementById("peliculas");
    tr = table.getElementsByTagName("tr");
    for (let i = 0; i < tr.length; i++) {
        if (tr[i]) {
            if (salaId === "0") {
                tr[i].style.display = "";
            } else {
                tr[i].classList.contains(`sala-${salaId}`) ? tr[i].style.display = "" : tr[i].style.display = "none";
            }
        }
    }
}

function filtroFecha(event) {
    let inputdate, table, tr;
    inputdate = event.target.value;
    table = document.getElementById("peliculas");
    tr = table.getElementsByTagName("tr");
    for (let i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            let di = new Date(inputdate);
            console.log(td.innerText)
            let fecha = td.innerText.split("/");
            let ds = new Date("20" + fecha[2] + "-" + fecha[1] + "-" + fecha[0]);
            di.getDate() === ds.getDate() && di.getMonth() === ds.getMonth() && di.getFullYear() === ds.getFullYear() ? tr[i].style.display = "" : tr[i].style.display = "none";
        }
    }
}

function filtroTitulo(event) {
    let input, filter, table, tr, td, i, txtValue;
    input = event.target;
    filter = input.value.toUpperCase();
    table = document.getElementById("peliculas");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[3].firstChild.innerText;
        if (td) {
            txtValue = td;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}