function crearUsuario() {
    let $modal = $('#modalUsuario').clone().removeAttr("id");

    $modal.find('input[id="userimg"]').change(() => {
        const userimg = $modal.find('input[id="userimg"]')[0].files[0];
        readImageFileData(userimg, $modal.find('img[id="previewimg"]')[0]);
        $modal.find('img[id="previewimg"]').show();
    });

    let action = $modal.find('form').attr('action');

    $modal.find('form').submit(function(event) {
        event.preventDefault();
        
        const $form = $(this);

        const username = $form.find('input[id="username"]').val();

        const email = $form.find('input[id="email"]').val();

        const password = $form.find('input[id="password"]').val();
        const pass2 = $form.find('input[id="confirm_password"]').val();

        const firstName = $form.find('input[id="nombre"]').val();

        const lastName = $form.find('input[id="apellidos"]').val();

        const birthDate = $form.find('input[id="fecha"]').val();
        
        let data = { username, email, password, pass2, firstName, lastName, birthDate}

        go(action, "POST", data).then((response) => {
            action = action.replace('-1', response.id);

            let imgSrc = $form.find('img[id="previewimg"]').attr('src');
            if (imgSrc) 
                postImage($form.find('img[id="previewimg"]')[0], action + "/pic", "photo", response.id+".jpg").then(() => {
                    location.reload();
                }).catch((error) => {
                    if (error.text) {
                        const errMsg = JSON.parse(error.text);
                        alert(errMsg.message);
                    }
                    else location.reload();
                });
            else location.reload();
            }).catch((error) => {
                if (error.text) {
                    const errMsg = JSON.parse(error.text);
                    alert(errMsg.message);
                }
            });

    });

    $modal.modal('show');   
}

function crearPelicula() {
    let $modal = $('#modalPelicula').clone().removeAttr("id");

    $modal.find('input[id="peliculaimg"]').change(() => {
        const peliculaimg = $modal.find('input[id="peliculaimg"]')[0].files[0];
        readImageFileData(peliculaimg, $modal.find('img[id="previewimg"]')[0]);
        $modal.find('img[id="previewimg"]').show();
    });

    const action = $modal.find('form').attr('action');

    $modal.find('form').submit(function(event) {
        event.preventDefault();
        
        const $form = $(this);

        const titulo = $form.find('input[id="titulo"]').val();
        const duracion = $form.find('input[id="duracion"]').val();
        const genero = $form.find('input[id="genero"]').val();

        let data = {titulo, duracion, genero};

        go(action, "POST", data).then((response) => {
            action = action.replace('-1', response.id);

            let imgSrc = $form.find('img[id="previewimg"]').attr('src');
            if (imgSrc) 
                postImage($form.find('img[id="previewimg"]')[0], action + "/pic", "photo", id+".jpg").then(() => {
                    location.reload();
                }).catch((error) => {
                    if (error.text) {
                        const errMsg = JSON.parse(error.text);
                        alert(errMsg.message);
                    }
                    else location.reload();
                });
            else location.reload();
        }).catch((error) => {
            if (error.text) {
                const errMsg = JSON.parse(error.text);
                alert(errMsg.message);
            }
        });
    });

    $modal.modal('show');  
}

function crearCine() {
    let $modal = $('#modalCine').clone().removeAttr("id");

    $modal.find('input[id="cineimg"]').change(() => {
        const cineimg = $modal.find('input[id="cineimg"]')[0].files[0];
        readImageFileData(cineimg, $modal.find('img[id="previewimg"]')[0]);
        $modal.find('img[id="previewimg"]').show();
    });

    const action = $modal.find('form').attr('action');
        
    $modal.find('form').submit(function(event) {
        event.preventDefault();
        
        const $form = $(this);

        const nombre = $modal.find('input[id="nombre-cine"]').val();
        const ciudad = $modal.find('input[id="ciudad"]').val();
        const direccion = $modal.find('input[id="direccion-cine"]').val();
        const hora_apertura = $modal.find('input[id="hora-apertura"]').val();
        const hora_cierre = $modal.find('input[id="hora-cierre"]').val();
        const coordenadas = $modal.find('input[id="coordenadas"]').val();
        const festivoscierreString = $modal.find('input[id="festivoscierre"]').val();

        const festivoscierreArray = festivoscierreString.replace(/\s+/g,'').split(",");
        const festivoscierre = festivoscierreArray.map(festivo => {
            festivo = festivo.replace(/\//gi,'');
            const dia = '-' + festivo.substring(0,2);
            festivo = '--' + festivo.substring(2,4) + dia;
            return festivo;
        });

        const dias_apertura = [];
        $modal.find(`input[type="checkbox"]`).each(function(){
            if($(this).prop('checked'))
                dias_apertura.push(parseInt($(this).val()));
        });
        
        let data = {nombre, ciudad, direccion, hora_apertura, hora_cierre, festivoscierre, coordenadas, dias_apertura};

        go(action, "POST", data).then((response) => {
            action = action.replace('-1', response.id);

            let imgSrc = $form.find('img[id="previewimg"]').attr('src');
            if (imgSrc) 
                postImage($form.find('img[id="previewimg"]')[0], action + "/pic", "photo", id+".jpg").then(() => {
                    location.reload();
                }).catch((error) => {
                    if (error.text) {
                        const errMsg = JSON.parse(error.text);
                        alert(errMsg.message);
                    }
                    else location.reload();
                });
            else location.reload();
        }).catch((error) => {
            if (error.text) {
                const errMsg = JSON.parse(error.text);
                alert(errMsg.message);
            }
        });
    });
    
    
    $modal.modal('show');  
}

function crearSala() {
    let $modal = $('#modalSala').clone().removeAttr("id");

    const action = $modal.find('form').attr('action');

    $modal.find('form').submit(function(event) {
        event.preventDefault();
        
        const $form = $(this);

        const nombre = $modal.find('input[id="nombre-sala"]').val();
        const filas = $modal.find('input[id="filas"]').val();
        const columnas = $modal.find('input[id="columnas"]').val();
        const cine_id = $modal.find('select[id="seleccion-cine"]').val();
        
        const data = {nombre, filas, columnas, cine_id};

        go(action, "POST", data).then(() => {
            location.reload();
        }).catch((error) => {
            if (error.text) {
                const errMsg = JSON.parse(error.text);
                alert(errMsg.message);
            }
        });
    });
    
    $modal.modal('show');  
}

function crearSesion() {
    let $modal = $('#modalSesion').clone().removeAttr("id");

    $modal.find(`select[name="cine"]`).change(() => {
        const nuevo_cine = $modal.find(`select[name="cine"]`).val();

        $modal.find(`select[name="sala"]`).find('option').hide();
        $modal.find(`select[name="sala"]`).find(`option[cine="${nuevo_cine}"]`).show();
        $modal.find(`select[name="sala"]`).val("seleccion");
        $modal.find(`select[name="sala"]`).prop('disabled', false);
    });

    const action = $modal.find('form').attr('action');

    $modal.find('form').submit(function(event) {
        event.preventDefault();
        
        const $form = $(this);

        if (!$modal.find(`select[name="sala"]`).val())
            alert("Selecciona una sala.");
        else {
            const pelicula_id = $modal.find('select[name="pelicula"]').val();
            const cine_id = $modal.find('select[name="cine"]').val();
            const sala_id = $modal.find('select[name="sala"]').val();
            const dia = $modal.find('input[id="fecha"]').val();
            const hora = $modal.find('input[id="hora"]').val();

            const dia_hora = dia + 'T' + hora;

            const precio = $modal.find('input[id="precio"]').val();
            
            const data = {pelicula_id, cine_id, sala_id, dia_hora, precio};
    
            go(action, "POST", data).then(() => {
                location.reload();
            }).catch((error) => {
                if (error.text) {
                    const errMsg = JSON.parse(error.text);
                    alert(errMsg.message);
                }
            });  
        }
    });

    $modal.modal('show');  
}

function editarUsuario() {
    const $btn = $(this);
    const id = $btn.attr('data-id');
    const username = $btn.attr('data-username');
    const email = $btn.attr('data-email');
    const firstname = $btn.attr('data-firstname');
    const lastname = $btn.attr('data-lastname');
    const birthdate = $btn.attr('data-birthdate');

    let $modal = $('#modalUsuario').clone().removeAttr("id");

    $modal.find('input[id="userimg"]').change(() => {
        const userimg = $modal.find('input[id="userimg"]')[0].files[0];
        readImageFileData(userimg, $modal.find('img[id="previewimg"]')[0]);
        $modal.find('img[id="previewimg"]').show();
    });

    $modal.find('input[id="username"]').val(username);
    $modal.find('input[id="email"]').val(email);
    $modal.find('input[id="nombre"]').val(firstname);
    $modal.find('input[id="apellidos"]').val(lastname);
    $modal.find('input[id="fecha"]').val(birthdate);

    $modal.find('input[id="password"]').prop('required', false);
    $modal.find('input[id="confirm_password"]').prop('required', false);

    const action = $modal.find('form').attr('action').replace('-1', id);
    $modal.find('form').attr('action', action);

    $modal.find('form').submit(function(event) {
        event.preventDefault();
        
        const $form = $(this);

        const username = $form.find('input[id="username"]').val();
        const email = $form.find('input[id="email"]').val();
        const firstName = $form.find('input[id="nombre"]').val();
        const lastName = $form.find('input[id="apellidos"]').val();
        const birthDate = $form.find('input[id="fecha"]').val();

        let data = {username, email, firstName, lastName, birthDate};

        const password = $form.find('input[id="password"]').val();
        const pass2 = $form.find('input[id="confirm_password"]').val();
        if (password && pass2) data = {...data, password, pass2};
        else if ((password && !pass2) || (!password && pass2)) 
            alert("Para cambiar la contraseña es necesario rellenar los dos campos correspondientes.")
    
        if ((password && pass2) || (!password && !pass2)) {
            go(action, "POST", data).then(() => {
                
                let imgSrc = $form.find('img[id="previewimg"]').attr('src');
                if (imgSrc) 
                    postImage($form.find('img[id="previewimg"]')[0], action + "/pic", "photo", id+".jpg").then(() => {
                        location.reload();
                    }).catch((error) => {
                        if (error.text) {
                            const errMsg = JSON.parse(error.text);
                            alert(errMsg.message);
                        }
                        else location.reload();
                    });
                else location.reload();
            }).catch((error) => {
                if (error.text) {
                    const errMsg = JSON.parse(error.text);
                    alert(errMsg.message);
                }
            });
        }
    });

    $modal.modal('show');    
}

function editarPelicula(event) {
    const $btn = $(this);
    const id = $btn.attr('data-id');

    const pelicula = event.data.peliculas.find(p => p.id == id);

    let $modal = $('#modalPelicula').clone().removeAttr("id");

    $modal.find('input[id="peliculaimg"]').change(() => {
        const peliculaimg = $modal.find('input[id="peliculaimg"]')[0].files[0];
        readImageFileData(peliculaimg, $modal.find('img[id="previewimg"]')[0]);
        $modal.find('img[id="previewimg"]').show();
    });
    
    $modal.find('input[id="titulo"]').val(pelicula.titulo);
    $modal.find('input[id="duracion"]').val(pelicula.duraccion);
    $modal.find('input[id="genero"]').val(pelicula.genero);
   
    $modal.find('input[id="peliculaimg"]').prop('required', false);

    const action = $modal.find('form').attr('action').replace('-1', id);
    $modal.find('form').attr('action', action);

    $modal.find('form').submit(function(event) {
        event.preventDefault();
        
        const $form = $(this);

        const titulo = $form.find('input[id="titulo"]').val();
        const duracion = $form.find('input[id="duracion"]').val();
        const genero = $form.find('input[id="genero"]').val();

        let data = {titulo, duracion, genero};

        go(action, "POST", data).then(() => {
            let imgSrc = $form.find('img[id="previewimg"]').attr('src');
            if (imgSrc) 
                postImage($form.find('img[id="previewimg"]')[0], action + "/pic", "photo", id+".jpg").then(() => {
                    location.reload();
                }).catch((error) => {
                    if (error.text) {
                        const errMsg = JSON.parse(error.text);
                        alert(errMsg.message);
                    }
                    else location.reload();
                });
            else location.reload();
        }).catch((error) => {
            if (error.text) {
                const errMsg = JSON.parse(error.text);
                alert(errMsg.message);
            }
        });
    });

    $modal.modal('show');  
}

function editarCine(event) {
    const $btn = $(this);
    const id = $btn.attr('data-id');

    const cine = event.data.cines.find(c => c.id == id);

    let festivos_cierre_array = cine.festivos_cierre.map(festivo => {
        festivo = festivo.replace(/-/gi,'');
        const mes = '/' + festivo.substring(0,2);
        festivo = festivo.substring(2);
        festivo += mes;
        return festivo;
    });
    const festivos_cierre = festivos_cierre_array.join(', ');

    let $modal = $('#modalCine').clone().removeAttr("id");

    $modal.find('input[id="cineimg"]').change(() => {
        const cineimg = $modal.find('input[id="cineimg"]')[0].files[0];
        readImageFileData(cineimg, $modal.find('img[id="previewimg"]')[0]);
        $modal.find('img[id="previewimg"]').show();
    });

    $modal.find('input[id="nombre-cine"]').val(cine.nombre);
    $modal.find('input[id="ciudad"]').val(cine.ciudad);
    $modal.find('input[id="direccion-cine"]').val(cine.direccion);
    $modal.find('input[id="hora-apertura"]').val(cine.hora_apertura);
    $modal.find('input[id="hora-cierre"]').val(cine.hora_cierre);
    $modal.find('input[id="festivoscierre"]').val(festivos_cierre);
    $modal.find('input[id="coordenadas"]').val(cine.coordenadas);
    for (const dia of cine.dias_apertura) 
        $modal.find(`input[id="${dia}"]`).prop("checked", true);

    const action = $modal.find('form').attr('action').replace('-1', id);
    $modal.find('form').attr('action', action);
        
    $modal.find('form').submit(function(event) {
        event.preventDefault();
        
        const $form = $(this);

        const nombre = $modal.find('input[id="nombre-cine"]').val();
        const ciudad = $modal.find('input[id="ciudad"]').val();
        const direccion = $modal.find('input[id="direccion-cine"]').val();
        const hora_apertura = $modal.find('input[id="hora-apertura"]').val();
        const hora_cierre = $modal.find('input[id="hora-cierre"]').val();
        const coordenadas = $modal.find('input[id="coordenadas"]').val();
        const festivoscierreString = $modal.find('input[id="festivoscierre"]').val();

        const festivoscierreArray = festivoscierreString.replace(/\s+/g,'').split(",");
        const festivoscierre = festivoscierreArray.map(festivo => {
            festivo = festivo.replace(/\//gi,'');
            const dia = '-' + festivo.substring(0,2);
            festivo = '--' + festivo.substring(2,4) + dia;
            return festivo;
        });

        const dias_apertura = [];
        $modal.find(`input[type="checkbox"]`).each(function(){
            if($(this).prop('checked'))
                dias_apertura.push(parseInt($(this).val()));
        });
        
        let data = {nombre, ciudad, direccion, hora_apertura, hora_cierre, festivoscierre, coordenadas, dias_apertura};

        go(action, "POST", data).then(() => {
            let imgSrc = $form.find('img[id="previewimg"]').attr('src');
            if (imgSrc) 
                postImage($form.find('img[id="previewimg"]')[0], action + "/pic", "photo", id+".jpg").then(() => {
                    location.reload();
                }).catch((error) => {
                    if (error.text) {
                        const errMsg = JSON.parse(error.text);
                        alert(errMsg.message);
                    }
                    else location.reload();
                });
            else location.reload();
        }).catch((error) => {
            if (error.text) {
                const errMsg = JSON.parse(error.text);
                alert(errMsg.message);
            }
        });
    });
    
    
    $modal.modal('show');  
}

function editarSala(event) {
    const $btn = $(this);
    const id = $btn.attr('data-id');
    const cine_id = $btn.attr('data-cineid');

    const sala = event.data.salas.find(s => s.id == id);

    let $modal = $('#modalSala').clone().removeAttr("id");

    $modal.find('input[id="nombre-sala"]').val(sala.nombre);
    $modal.find('input[id="filas"]').val(sala.filas);
    $modal.find('input[id="columnas"]').val(sala.columnas);
    $modal.find(`option[value=${cine_id}]`).attr('selected','selected'); 

    const action = $modal.find('form').attr('action').replace('-1', id);
    $modal.find('form').attr('action', action);

    $modal.find('form').submit(function(event) {
        event.preventDefault();
        
        const $form = $(this);

        const nombre = $modal.find('input[id="nombre-sala"]').val();
        const filas = $modal.find('input[id="filas"]').val();
        const columnas = $modal.find('input[id="columnas"]').val();
        const cine_id = $modal.find('select[id="seleccion-cine"]').val();
        
        const data = {nombre, filas, columnas, cine_id};

        go(action, "POST", data).then(() => {
            location.reload();
        }).catch((error) => {
            if (error.text) {
                const errMsg = JSON.parse(error.text);
                alert(errMsg.message);
            }
        });
    });
    
    $modal.modal('show');  
}

function editarSesion(event) {
    const $btn = $(this);
    const id = $btn.attr('data-id');
    const pelicula_id = $btn.attr('data-peliculaid');
    const cine_id = $btn.attr('data-cineid');
    const sala_id = $btn.attr('data-salaid');
    const dia_hora = $btn.attr('data-diahora');

    const dia = dia_hora.substring(0,10);
    const hora = dia_hora.substring(11) + ':00';

    const precio = $btn.attr('data-precio');

    let $modal = $('#modalSesion').clone().removeAttr("id");

    $modal.find(`select[name="cine"]`).change(() => {
        const nuevo_cine = $modal.find(`select[name="cine"]`).val();

        $modal.find(`select[name="sala"]`).find('option').hide();
        $modal.find(`select[name="sala"]`).find(`option[cine="${nuevo_cine}"]`).show();
        $modal.find(`select[name="sala"]`).val("seleccion");
    });

    $modal.find(`select[name="pelicula"]`).val(pelicula_id);
    $modal.find('input[id="fecha"]').val(dia);
    $modal.find('input[id="hora"]').val(hora);
    $modal.find(`select[name="cine"]`).val(cine_id);
    $modal.find('input[id="precio"]').val(precio);

    $modal.find(`select[name="sala"]`).find('option').hide();
    $modal.find(`select[name="sala"]`).find(`option[cine="${cine_id}"]`).show();
    $modal.find(`select[name="sala"]`).val(sala_id);
    $modal.find(`select[name="sala"]`).prop('disabled', false);

    const action = $modal.find('form').attr('action').replace('-1', id);
    $modal.find('form').attr('action', action);

    $modal.find('form').submit(function(event) {
        event.preventDefault();
        
        const $form = $(this);

        const pelicula_id = $modal.find('select[name="pelicula"]').val();
        const cine_id = $modal.find('select[name="cine"]').val();
        const sala_id = $modal.find('select[name="sala"]').val();
        const dia = $modal.find('input[id="fecha"]').val();
        const hora = $modal.find('input[id="hora"]').val();
        const precio = $modal.find('input[id="precio"]').val();
        
        const dia_hora = dia + 'T' + hora;
        
        const data = {pelicula_id, cine_id, sala_id, dia_hora, precio};

        go(action, "POST", data).then(() => {
            location.reload();
        }).catch((error) => {
            if (error.text) {
                const errMsg = JSON.parse(error.text);
                alert(errMsg.message);
            }
        });
    });

    $modal.modal('show');  
}

function eliminarUsuario(event){
    const $btn = $(this);
    const id = $btn.attr('data-id');
    go(`${config.rootUrl}/user/${id}/delete`, "POST").then((response) => {
        location.reload();
    }).catch((error) => {
        if (error.text) {
            const errMsg = JSON.parse(error.text);
            alert(errMsg.message);
        }
    });
}

