function asientoOcupado() {
    alert("Este asiento está ocupado, selecciona otro");
}

function actualizarAsiento(id) {
    if (document.getElementById(id).style.fill !== "yellow") {
        document.getElementById(id).style.fill = "yellow";
        let valor = +(document.getElementById("inputcantidad").value) + 1;
        document.getElementById("inputcantidad").value = valor;
        actualizarPrecio(valor);
    }
}

function eliminarAsiento(e, id) {
    if (document.getElementById(id).style.fill === "yellow"){
        e.preventDefault();
        document.getElementById(id).style.fill = "#92bcea";
        let valor = +(document.getElementById("inputcantidad").value) - 1;
        document.getElementById("inputcantidad").value = valor;
        actualizarPrecio(valor);
    }
}

function actualizarPrecio(valor){
    let precioentrada = +(document.getElementById("precioentrada").innerHTML);
    let subtotal = precioentrada *  valor;
    document.getElementById("subtotal").innerHTML = subtotal.toFixed(2);
    document.getElementById("preciofinal").innerHTML = subtotal.toFixed(2);
}