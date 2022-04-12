"use strict"

function filtroCine(event) {
    let s = event.target.value;
    let peliculas = document.getElementById("peliculas");
}

function filtroSala(event) {
    let s = event.target.value;
    let peliculas = document.getElementById("peliculas");
}

function filtroFecha(event) {
    let s = event.target.value;
    let peliculas = document.getElementById("peliculas");
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
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /* Loop through all table rows (except the
      first, which contains table headers): */
      for (i = 0; i < (rows.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].getElementsByTagName("td")[1].firstChild.innerText;
        y = rows[i + 1].getElementsByTagName("td")[1].firstChild.innerText;
        // Check if the two rows should switch place:
        if (x.toLowerCase() > y.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
}

function filtroDuracion(event) {
    let table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("peliculas");
    switching = true;
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /* Loop through all table rows (except the
      first, which contains table headers): */
      for (i = 0; i < (rows.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].getElementsByTagName("td")[2].innerText.split(" ")[0];
        y = rows[i + 1].getElementsByTagName("td")[2].innerText.split(" ")[0];
        // Check if the two rows should switch place:
        if (+x > +y) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
}
