function loadMessages(){
    cargarMensajes();
}

async function cargarMensajes(){
    // pinta mensajes viejos al cargarse, via AJAX
    let messageDiv = document.getElementById("mensajes");
    try{

        let ms = await go(config.rootUrl + "/user/sent", "GET");

        let mr = await go(config.rootUrl + "/user/received", "GET");

        let mgs = [...ms,...mr];
        
        mgs.sort(function(a, b) {
            var c = new Date(a.sent);
            var d = new Date(b.sent);
            return c-d;
        });

        mgs.forEach(m => messageDiv.insertAdjacentHTML("beforeend",document.getElementById("username").innerText === m.from ? renderMsg(m) : renderOtherMsg(m)))
        
    }catch(e){
        console.log(e);
    } 
}

function enviarMensaje(event) {
    event.preventDefault();
    postMensaje("admin");
}

function enviarMensajeUser(event) {
    event.preventDefault();
    postMensaje("user");
}

async function postMensaje(to) {
    // envio de mensajes con AJAX
    let b = document.getElementById("sendmsg");
    try{
        if(to === "admin"){
            const d = go("/user/1/msg", 'POST', {
                message: document.getElementById("message").value
            });
            console.log("Mensaje enviado a admin: " + d);
        }else{
            const d = go("/user/2/msg", 'POST', {
                message: document.getElementById("message").value
            });
            console.log("Mensaje enviado: " + d);
        }
        document.getElementById("mensajes").insertAdjacentHTML("beforeend", renderMsg({from: document.getElementById("username").innerText, text: document.getElementById("message").value}));
        document.getElementById("messageform").reset();
    }catch(e){
        console.log("Error al enviar mensaje: " + e)
    }
}

// cómo pintar 1 mensaje (devuelve html que se puede insertar en un div)
function renderMsg(msg) {
    console.log("rendering: ", msg);
    //msg.sent
    return `<div class="mb-2"><span class="bold">${msg.from}:</span> ${msg.text} </div>`;
}

function renderOtherMsg(msg) {
    console.log("rendering other: ", msg);
    console.log(msg.sent)
    return `<div class="d-flex justify-content-end"><div class="mb-2"><span class="bold">${msg.from}:</span> ${msg.text}</div></div>`;
}

// y aquí pinta mensajes según van llegando
if (ws.receive) {
    const oldFn = ws.receive; // guarda referencia a manejador anterior
    ws.receive = (m) => {
        oldFn(m); // llama al manejador anterior
        document.getElementById("mensajes").insertAdjacentHTML("beforeend", renderOtherMsg(m));
    }
}


