"use strict"

function enviarUser(event) {
    event.preventDefault();
    postUser();
}

async function postUser() {
    try {
        const params = window.location.href;
        let parse = params.split('/');
        let id = parse[4];
        let u = {};
        if (document.getElementById("password").value !== '') {
            if (document.getElementById("password").value === document.getElementById("pass2").value) {
                u =
                {
                    password: document.getElementById("password").value,
                    pass2: document.getElementById("pass2").value,
                }
                let uback = await go(`${config.rootUrl}/user/${id}`, "POST", u);
                let formpass = document.getElementById('formpass');
                formpass.reset();
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
                textdiv.appendChild(document.createTextNode("CONTRASEÑA CAMBIADA CORRECTAMENTE"));
                svgicon.appendChild(use);
                alertdiv.appendChild(svgicon);
                alertdiv.appendChild(textdiv);
                formpass.appendChild(alertdiv);
            } else {
                let formpass = document.getElementById('formpass');
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
                textdiv.appendChild(document.createTextNode("LAS CONTRASEÑAS NO COINCIDEN"));
                svgicon.appendChild(use);
                alertdiv.appendChild(svgicon);
                alertdiv.appendChild(textdiv);
                formpass.appendChild(alertdiv);
            }
        } else {
            u =
            {
                firstName: document.getElementById("firstName").value,
                lastName: document.getElementById("lastName").value,
                email: document.getElementById("email").value,
                birthDate: document.getElementById("birthDate").value,
            }
            let uback = await go(`${config.rootUrl}/user/${id}`, "POST", u);
            let formpass = document.getElementById('formpass');
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
            textdiv.appendChild(document.createTextNode("INFORMACIÓN DE USUARIO ACTUALIZADA"));
            svgicon.appendChild(use);
            alertdiv.appendChild(svgicon);
            alertdiv.appendChild(textdiv);
            formpass.appendChild(alertdiv);
        }

    } catch (e) {
        console.log(e);
    }

}

$(function () {
    $('.editarusuario').click(function() {
        const $btn = $(this);
        const id = $btn.attr('data-id');
        const username = $btn.attr('data-username');
        const email = $btn.attr('data-email');
        const firstname = $btn.attr('data-firstname');
        const lastname = $btn.attr('data-lastname');
        const birthdate = $btn.attr('data-birthdate');

        let $modal = $('#modalUsuario').clone().removeAttr("id");

        $modal.find('input[id="username"]').val(username);
        $modal.find('input[id="email"]').val(email);
        $modal.find('input[id="nombre"]').val(firstname);
        $modal.find('input[id="apellidos"]').val(lastname);
        $modal.find('input[id="fecha"]').val(birthdate);

        $modal.find('input[id="username"]').prop('required', false);
        $modal.find('input[id="email"]').prop('required', false);
        $modal.find('input[id="password"]').prop('required', false);
        $modal.find('input[id="confirm_password"]').prop('required', false);
        $modal.find('input[id="nombre"]').prop('required', false);
        $modal.find('input[id="apellidos"]').prop('required', false);
        $modal.find('input[id="fecha"]').prop('required', false);

        const action = $modal.find('form').attr('action').replace('-1', id);
        $modal.find('form').attr('action', action);

        $modal.find('form').submit(function(event) {
            event.preventDefault();
            
            const $form = $(this);

            const username = $form.find('input[id="username"]').val();

            const email = $form.find('input[id="email"]').val();
            if (email) $modal.find('form').attr('action', action);

            const password = $form.find('input[id="password"]').val();
            const pass2 = $form.find('input[id="confirm_password"]').val();

            const firstName = $form.find('input[id="nombre"]').val();
            if (firstName) $btn.attr('data-firstname', firstName);

            const lastName = $form.find('input[id="apellidos"]').val();
            if (lastName) $btn.attr('data-lastname', lastName);

            const birthDate = $form.find('input[id="fecha"]').val();
            if (birthDate) $btn.attr('data-birthdate', birthDate);
           
            let data = { username, email, password, pass2, firstName, lastName, birthDate}

            $modal.modal('hide');
    
            go(action, "POST", data);
    
          });

        $modal.modal('show');
    });
});