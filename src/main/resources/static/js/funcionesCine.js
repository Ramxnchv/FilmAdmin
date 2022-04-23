$(function () {
    $('.editarcine').click(function() {
        const $btn = $(this);
        const id = $btn.attr('data-id');
        const nombre = $btn.attr('data-nombre');
        const direccion = $btn.attr('data-direccion');
        const hora_apertura = $btn.attr('data-hora_apertura');
        const hora_cierre = $btn.attr('data-hora_cierre');
        const coordenadas = $btn.attr('data-coordenadas');

        const data_dias_apertura = $btn.attr('data-dias_apertura');
        let dias_apertura = JSON.stringify(data_dias_apertura).slice(2,-2).split(',');
        dias_apertura = dias_apertura.map(dia => dia.trim());
        if (dias_apertura[0] === '')
            dias_apertura.splice(0);

        const data_festivos_cierre = $btn.attr('data-festivos_cierre');
        let festivos_cierre_array = JSON.stringify(data_festivos_cierre).slice(2,-2).split(',');
        festivos_cierre_array = festivos_cierre_array.map(festivo => festivo.trim());
        if (festivos_cierre_array[0] === '')
            festivos_cierre_array.splice(0);
        else 
            festivos_cierre_array = festivos_cierre_array.map(festivo => {
                festivo = festivo.replace(/-/gi,'');
                const mes = '/' + festivo.substring(0,2);
                festivo = festivo.substring(2);
                festivo += mes;
                return festivo;
            });
        const festivos_cierre = festivos_cierre_array.join(', ');

        let $modal = $('#modalCine').clone().removeAttr("id");

        $modal.find('input[id="nombre-cine"]').val(nombre);
        $modal.find('input[id="direccion-cine"]').val(direccion);
        $modal.find('input[id="hora-apertura"]').val(hora_apertura);
        $modal.find('input[id="hora-cierre"]').val(hora_cierre);
        $modal.find('input[id="festivoscierre"]').val(festivos_cierre);
        $modal.find('input[id="coordenadas"]').val(coordenadas);

        for (const dia of dias_apertura) 
            $modal.find(`input[id="${dia}"]`).prop("checked", true);    
        
        $modal.modal('show');
    });
});

