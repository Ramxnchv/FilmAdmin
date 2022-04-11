"use strict"

// ver https://www.omdbapi.com/
// requieren API key, pero se puede conseguir de forma gratuita
// API key cambiada :)
function fetchMovieData(imdb, targetImg) {
    go(`http://www.omdbapi.com/?i=${imdb}&apikey=2b8b7f72`, "GET", {}, {}).then(movieInfo => {
        console.log(`title: ${movieInfo.Title}`);
        // targetImg.src = movieInfo.Poster;
        readImageUrlData(movieInfo.Poster, targetImg)
    })
}
// click en boton de cargar datos peli
document.querySelector("#fetchMovie").onclick = e => {
    let imdb = document.querySelector("#imdb").value;
    console.log("fetching ", imdb);
    fetchMovieData(imdb, document.querySelector("#poster"));
};
// click en boton de enviar portada
document.querySelector("#postPortada").onclick = e => {
    e.preventDefault();
    let url = document.querySelector("#postPortada").parentNode.action;
    let img = document.querySelector("#portada");
    postImage(img, url, "photo");
};
// refresca previsualizacion cuando cambias imagen
document.querySelector("#f_avatar").onchange = e => {
    let img = document.querySelector("#avatar");
    let fileInput = document.querySelector("#f_avatar");
    console.log(img, fileInput);
    readImageFileData(fileInput.files[0], img);
};
// click en boton de enviar avatar
document.querySelector("#postAvatar").onclick = e => {
    e.preventDefault();
    let url = document.querySelector("#postAvatar").parentNode.action;
    let img = document.querySelector("#avatar");
    let file = document.querySelector("#f_avatar");
    postImage(img, url, "photo").then(() => {
        let cacheBuster = "?" + new Date().getTime();
        document.querySelector("a.nav-link>img.iwthumb").src = url + cacheBuster;
    });
};