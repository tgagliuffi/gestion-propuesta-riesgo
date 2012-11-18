optionEstadistica = {
	height: 140,
	width: 900 /*,
	onSelectRow: function(id) {
		row = $(this).getRowData(id);
		$("#textMensaje").val(row.comentario);
	}*/
};

buscarEstadisticaAsignacion = function(){
	estadistica = {};
	fechaInicio = "17/11/2012 00:00:00";
	fechaFin = "17/11/2012 23:59:59";
	EstadisticaAction.listarEstadisticasAsignacion(estadistica, fechaInicio, fechaFin, function(data){
		optionEstadistica = $.extend(optionEstadistica, {
			colsName: data.colsName,
			colsModel: data.colsModel
		});
		console.log(optionEstadistica);
		configurarGrid("listDetallado", data.data, optionEstadistica);
		console.log(data);
	});
};

$(document).ready(function(){
	buscarEstadisticaAsignacion();
});