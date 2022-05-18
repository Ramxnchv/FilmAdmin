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
                avisoSuccess(formpass,'Success:',"CONTRASEÑA CAMBIADA CORRECTAMENTE");
            } else {
                let formpass = document.getElementById('formpass');
                avisoDanger(formpass,'Danger:',"LAS CONTRASEÑAS NO COINCIDEN");
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
            avisoSuccess(formpass, 'Success:', "INFORMACIÓN DE USUARIO ACTUALIZADA");
        }

    } catch (e) {
        console.log(e);
    }

}

function avisoSuccess(selector, title, text) {
    let element = `
    <div class="alert alert-success d-flex align-items-center mt-4" role="alert">
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="${title}">
            <use href="#check-circle-fill"></use>
        </svg>
        <div>${text}</div>
    </div>`;
    selector.insertAdjacentHTML("beforeend", element);
}

function avisoDanger(selector, title, text) {
    let element = `
    <div class="alert alert-danger d-flex align-items-center mt-4" role="alert">
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="${title}">
            <use href="#exclamation-triangle-fill"></use>
        </svg>
        <div>${text}</div>
    </div>`;
    selector.insertAdjacentHTML("beforeend", element);
}