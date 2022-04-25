"use strict"

/*
    Usando https://imdb-api.com/
*/

var API_KEY = "k_g5kmf1kq";

async function getPeliculaInfo(){
    let title = document.getElementById("titulo").innerText.replace("/(\s)/g", "%20");
    let id = await go(`https://imdb-api.com/en/API/SearchMovie/${API_KEY}/${title}`, "GET");
    if(id){
        console.log(id.results[0].id)
        let fullcastRequest = await go(`https://imdb-api.com/en/API/FullCast/${API_KEY}/${id.results[0].id}`, "GET");
        let trailerRequest = await go(`https://imdb-api.com/en/API/YouTubeTrailer/${API_KEY}/${id.results[0].id}`, "GET");
        let trailer = document.getElementById("trailer");
        trailer.src = `https://www.youtube.com/embed/${trailerRequest.videoId}`;
        document.getElementById("director").innerText=fullcastRequest.directors.items[0].name;
        document.getElementById("guionista").innerText=fullcastRequest.writers.items[0].name;
        document.getElementById("actores").innerText=fullcastRequest.actors[0].name + ", " + fullcastRequest.actors[1].name;
    }
}