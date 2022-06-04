function loadMessages() {
    cargarMensajes();
}

function createTabForAdmin(id,nombre){
    document.getElementById("v-pills-tab").insertAdjacentHTML("beforeend", `<button class="nav-link chat-selector" id="v-pills-${id}-tab" data-bs-toggle="pill" data-bs-target="#v-pills-${id}", aria-controls="v-pills-${id}" type="button" role="tab" aria-selected="false">Cliente ${nombre}</button>`)
    document.getElementById("v-pills-tabContent").insertAdjacentHTML("beforeend", `<div class="tab-pane fade" id="v-pills-${user.id}" role="tabpanel" aria-labelledby="v-pills-${user.id}-tab">
    <div class="d-flex align-items-center top-chat">
      <span class="p-1 bold ms-auto">Chat con ${nombre}</span>
      <button type="button" class="btn-close btn-close-white ms-auto me-2" aria-label="Close"></button>
    </div>
    <div class="mensajes mb-3" id="mensajes-${id}">
      <!-- <div class="d-flex justify-content-center mt-4"><div>user está escribiendo...</div></div> -->
    </div>
    <form class="d-flex flex-column" method="POST" id="messageform" onsubmit="enviarMensajeUser(event)">
      <textarea id="message" class="message" onkeypress="enviarMensajeUser(event)"></textarea>
      <button class="btn btn-dark mt-3" type="submit" id="sendmsg">Enviar</button>
      <input type="hidden" id="userid" value="${id}"></input>
      <input type="hidden" class="usernameclient" value="${nombre}"></input>
    </form>
    </div>`);
    
}

async function cargarMensajes() {
    // pinta mensajes viejos al cargarse, via AJAX
    let messageDiv = document.getElementById("mensajes");
    try {

        let ms = await go(config.rootUrl + "/user/sent", "GET");

        let mr = await go(config.rootUrl + "/user/received", "GET");

        let mgs = [...ms, ...mr];

        mgs.sort(function (a, b) {
            var c = new Date(a.sent);
            var d = new Date(b.sent);
            return c - d;
        });

        let url = window.location.href.split('/');
        if (url[3] === "admin") {
            mgs.forEach(m => document.getElementById(`mensajes-${[...document.getElementsByClassName("usernameclient")].filter(e => e.value === m.from || e.value === m.to)[0].previousSibling.previousSibling.value}`)
                .insertAdjacentHTML("beforeend", document.getElementById("username").innerText === m.from ? renderMsg(m) : renderOtherMsg(m)));
        } else {
            mgs.forEach(m => document.getElementById("mensajes-1").insertAdjacentHTML("beforeend", document.getElementById("username").innerText === m.from ? renderMsg(m) : renderOtherMsg(m)));
        }



    } catch (e) {
        console.log(e);
    }
}

function enviarMensaje(event) {
    if (event.type === "submit") {
        event.preventDefault();
        postMensaje("admin", 1, event.target.parentNode.querySelector("#message").value, event);
    }else{
        if (event.key === 'Enter') {
            event.preventDefault();
            postMensaje("admin", 1, event.target.parentNode.querySelector("#message").value, event);
        }
    }
}

function enviarMensajeUser(event) {
    if (event.type === "submit") {
        event.preventDefault();
        postMensaje("user", event.target.querySelector("#userid").value, event.target.parentNode.querySelector("#message").value, event)
    }
    else {
        if (event.key === 'Enter') {
            event.preventDefault();
            postMensaje("user", event.target.parentNode.querySelector("#userid").value, event.target.parentNode.querySelector("#message").value, event)
        }
    }
}

async function postMensaje(to, user_id, message, event) {
    // envio de mensajes con AJAX
    let b = document.getElementById("sendmsg");
    try {
        if (to === "admin") {
            const d = go("/user/1/msg", 'POST', {
                message: message
            });
            console.log("Mensaje enviado a admin: " + d);
        } else {
            const d = go(`/user/${user_id}/msg`, 'POST', {
                message: message
            });
            console.log("Mensaje enviado: " + d);
        }
        document.getElementById(`mensajes-${user_id}`).insertAdjacentHTML("beforeend", renderMsg({ from: document.getElementById("username").innerText, text: message }));
        event.target.parentNode.reset();
    } catch (e) {
        console.log("Error al enviar mensaje: " + e)
    }
}

// cómo pintar 1 mensaje (devuelve html que se puede insertar en un div)
function renderMsg(msg) {
    console.log("rendering: ", msg);
    return `<div class="mb-2"><span class="bold">${msg.from}:</span> ${msg.text} </div>`;
}

function renderOtherMsg(msg) {
    console.log("rendering other: ", msg);
    return `<div class="d-flex justify-content-end"><div class="mb-2"><span class="bold">${msg.from}:</span> ${msg.text}</div></div>`;
}

// y aquí pinta mensajes según van llegando
if (ws.receive) {
    const oldFn = ws.receive; // guarda referencia a manejador anterior
    ws.receive = (m) => {
        oldFn(m); // llama al manejador anterior
        let url = window.location.href.split('/');
        if (url[3] === "admin") {
            document.getElementById(`mensajes-${[...document.getElementsByClassName("usernameclient")].filter(e => e.value === m.from)[0].previousSibling.previousSibling.value}`)
                .insertAdjacentHTML("beforeend", renderOtherMsg(m));
        } else {
            document.getElementById("mensajes-1").insertAdjacentHTML("beforeend", renderOtherMsg(m));
        }
    }
}


