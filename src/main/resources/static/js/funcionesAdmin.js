function editarUsuario() {
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

    $modal.find('input[id="nombre-cine"]').val(cine.nombre);
    $modal.find('input[id="direccion-cine"]').val(cine.direccion);
    $modal.find('input[id="hora-apertura"]').val(cine.hora_apertura);
    $modal.find('input[id="hora-cierre"]').val(cine.hora_cierre);
    $modal.find('input[id="festivoscierre"]').val(festivos_cierre);
    $modal.find('input[id="coordenadas"]').val(cine.coordenadas);
    for (const dia of cine.dias_apertura) 
        $modal.find(`input[id="${dia}"]`).prop("checked", true);    
    
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

    console.log($modal.find(`option`));
    
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

    let $modal = $('#modalSesion').clone().removeAttr("id");

    $modal.find(`select[name="pelicula"]`).val(pelicula_id);
    $modal.find('input[id="fecha"]').val(dia);
    $modal.find('input[id="hora"]').val(hora);
    $modal.find(`select[name="cine"]`).val(cine_id);

    $modal.find(`select[name="sala"]`).find('option').hide();
    $modal.find(`select[name="sala"]`).find(`option[cine="${cine_id}"]`).show();
    $modal.find(`select[name="sala"]`).val(sala_id);
    $modal.find(`select[name="sala"]`).prop('disabled', false);


    $modal.find(`select[name="cine"]`).change(() => {
        const nuevo_cine = $modal.find(`select[name="cine"]`).val();

        $modal.find(`select[name="sala"]`).find('option').hide();
        $modal.find(`select[name="sala"]`).find(`option[cine="${nuevo_cine}"]`).show();
        $modal.find(`select[name="sala"]`).val("seleccion");
    });

    $modal.modal('show');  
}

