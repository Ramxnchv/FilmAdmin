"use strict"

async function filtroCine(event) {
  //Filtramos las sesiones por cine
  let cineId = event.target.value;
  let input, filter, table, tr, sesiones;
  input = event.target;
  filter = input.value;
  table = document.getElementById("peliculas");
  tr = table.getElementsByTagName("tr");
  for (let i = 0; i < tr.length; i++) {
    sesiones = [...tr[i].getElementsByClassName("sesion")].forEach(s => {
      if (cineId === "0") {
        s.style.display = "";
      } else {
        s.classList.contains(`cine-${cineId}`) ? s.style.display = "" : s.style.display = "none";
      }
    });
  }

  //Obtenemos las salas del cine seleccionado para habilitar el filtro
  let salas = await go(config.rootUrl + `/peliculas/salas/${+cineId}`, "GET");
  if (document.getElementById("sala-selector").children.length > 1) {
    let options = document.getElementById("sala-selector");
    let selectoption = document.getElementById("sala-selector").children[0];
    while(options.firstChild){
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
  let input, filter, table, tr, sesiones;
  input = event.target;
  filter = input.value;
  table = document.getElementById("peliculas");
  tr = table.getElementsByTagName("tr");
  for (let i = 0; i < tr.length; i++) {
    sesiones = [...tr[i].getElementsByClassName("sesion")].forEach(s => {
      if (salaId === "0") {
        s.style.display = "";
      } else {
        s.classList.contains(`sala-${salaId}`) ? s.style.display = "" : s.style.display = "none";
      }
    });
  }
}

function filtroFecha(event) {
  let inputdate, table, tr, sesiones;
  inputdate = event.target.value;
  table = document.getElementById("peliculas");
  tr = table.getElementsByTagName("tr");
  for (let i = 0; i < tr.length; i++) {
    sesiones = [...tr[i].getElementsByClassName("sesion")].forEach(s => {
      console.log(s.lastElementChild.value)
      console.log(inputdate)
      let di = new Date(inputdate);
      let ds = new Date(s.lastElementChild.value);
      di.getDate() === ds.getDate() && di.getMonth() === ds.getMonth() && di.getFullYear() === ds.getFullYear() ? s.style.display = "" : s.style.display = "none";
    });
  }
}

function filtroTituloCategoria(event) {
  let input, filter, table, tr, td, tdcat, i, txtValue;
  input = event.target;
  filter = input.value.toUpperCase();
  table = document.getElementById("peliculas");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1].firstChild.innerText;
    tdcat = tr[i].getElementsByTagName("td")[3].innerText;
    if (td && tdcat) {
      txtValue = td;
      if (txtValue.toUpperCase().indexOf(filter) > -1 || tdcat.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function filtroAlfabetico(event) {

  let table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("peliculas");
  switching = true;

  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 0; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("td")[1].firstChild.innerText;
      y = rows[i + 1].getElementsByTagName("td")[1].firstChild.innerText;

      if (x.toLowerCase() > y.toLowerCase()) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}

function filtroDuracion(event) {
  let table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("peliculas");
  switching = true;

  while (switching) {
    switching = false;
    rows = table.rows;

    for (i = 0; i < (rows.length - 1); i++) {
      shouldSwitch = false;
  
      x = rows[i].getElementsByTagName("td")[2].innerText.split(" ")[0];
      y = rows[i + 1].getElementsByTagName("td")[2].innerText.split(" ")[0];
 
      if (+x > +y) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}
