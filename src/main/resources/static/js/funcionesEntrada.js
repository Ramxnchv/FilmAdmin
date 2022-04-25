function generateQR(){
    let website = `${config.rootUrl}/entradas/asientos/${document.getElementById("code").innerText}`;
    if (website) {
        let qrcodeContainer = document.getElementById("qrcode");
        qrcodeContainer.innerHTML = "";
        new QRCode(qrcodeContainer, {
            text: website,
            width: 368,
            height: 368,
            correctLevel : QRCode.CorrectLevel.H
        });
        document.getElementById("qrcode-container").style.display = "block";
    }
}