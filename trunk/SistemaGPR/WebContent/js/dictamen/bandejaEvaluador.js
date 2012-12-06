var 
	colHeader = ['codAsignacion',
        'codRol',
 		'codUsuario',
 		'codUsuarioAsigno',
 		'dependiente',
 		'estado',
		'fechaIngresoFin',
		'flagFueraRango',
		'strMensaje',
		'Fecha',
		'Solicitud',
		'Central',
		'Cliente',
		'Moneda',
		'Monto',
		'Riesgo Actual',
		'Riesgo Total',
		'Prioridad',
		'Fuera Rango',
		'Fecha Ven.' ],
	colModel = [
		  {name : 'codAsignacion', index : 'codAsignacion', width : VAL_WIDTH.XLSMALL, hidden : true}    	
		, {name : 'codRol', index : 'codRol', width : VAL_WIDTH.XLSMALL, hidden : true}
		, {name : 'codUsuario', index : 'codUsuario', width : VAL_WIDTH.XLSMALL, hidden : true}
		, {name : 'codUsuarioAsigno', index : 'codUsuarioAsigno', width : VAL_WIDTH.XLSMALL, hidden : true}
		, {name : 'dependiente', index : 'dependiente', width : VAL_WIDTH.XLSMALL, hidden : true}
		, {name : 'estado', index : 'estado', width : VAL_WIDTH.XLSMALL, hidden : true}
		, {name : 'fechaIngresoFin', index : 'fechaIngresoFin', width : VAL_WIDTH.XLSMALL , hidden : true}
		, {name : 'flagFueraRango', index : 'flagFueraRango', width : VAL_WIDTH.XLSMALL, hidden : true}
		, {name : 'strMensaje', index : 'strMensaje', width : VAL_WIDTH.XLSMALL, hidden : true}
		// , {name : 'fechaAsignacion', index : 'fechaAsignacion', width : 90, formatter:'date', formatoptions: {srcformat:"Y-m-d", newformat:"d-M-Y"}, align: 'center'}
		, {name : 'strFechaAsignacion', index : 'strFechaAsignacion', width : 90, align: 'center'}
		, {name : 'nroSolicitud', index : 'nroSolicitud', width : 90, align: 'center'}
		, {name : 'codCentral', index : 'codCentral', width : 90, align: 'center'}
		, {name : 'nombre', index : 'nombre', width : 250}
		, {name : 'codMoneda', index : 'codMoneda', width : 70, align: 'center'}
		, {name : 'mtoDelegacionMax', index : 'mtoDelegacionMax', width : 150, align: 'right'}
		, {name : 'riesgoActual', index : 'riesgoActual', width : 150, align: 'right'}
		, {name : 'riesgoTotal', index : 'riesgoTotal', width : 150, align: 'right'}
		, {name : 'prioridad', index : 'prioridad', width : 90, align: 'center'}
		, {name : 'flagFueraRangoSi', index : 'flagFueraRangoSi', width : VAL_WIDTH.XLSMALL, align: 'center'}
		, {name : 'fechaIngresoIni', index : 'fechaIngresoIni', width : 90, formatter:'date', formatoptions: {srcformat:"Y-m-d", newformat:"d-M-Y"}, align: 'center'}
		];

atenderSolicitud = function(rowid, iRow, iCol, e){
	if($("#id_usuario").val() == "-1" || $("#monto_delegacion").val() == "-1") {
		alert($("#error").val());
		return;
	}
	
	rowid = $("#listSolicitud").jqGrid('getGridParam','selrow');
    if(rowid){
    	row = $("#listSolicitud").jqGrid('getRowData', rowid);
        window.open(obtenerContexto() + "dictamen.do?method=index&nroSolicitud=" + row.nroSolicitud + "&codAsignacion=" + row.codAsignacion, '_self');
    } else { 
        alert("Seleccione una solicitud para atender.");
    }
};

buscarSolicitud = function(){
	if($("#id_usuario").val() == "-1" || $("#monto_delegacion").val() == "-1") {
		alert($("#error").val());
		return;
	}
	
	_fechaIngresoIni = $("#inifechaSolicitud").val().length == 0 ? null : new Date($("#inifechaSolicitud").val());
	_fechaIngresoFin = $("#finfechaSolicitud").val().length == 0 ? null : new Date($("#finfechaSolicitud").val());
	
	parameters = {
		codUsuario: $("#id_usuario").val(),
		nroSolicitud: $("#numSolicitud").val(), 
	    codCentral: $("#codigoCentral").val(),
	    fechaIngresoIni: _fechaIngresoIni,
	    fechaIngresoFin: _fechaIngresoFin,
	    flagFueraRangoSi: $("#stFueraRango").attr("checked") ? 'SI' : 'NO',
	    mtoDelegacionMax: $("#monto_delegacion").val()
	};

	optionSolicitud = {
	    height: 290,
	    width: 1000,
	    colNames: colHeader,
	    colModel: colModel,
	    caption: "&nbsp;&nbsp;&nbsp;Solicitudes Asignadas",
	    ondblClickRow: atenderSolicitud
	};
	
	BandejaEvaluacionAction.buscarSolicitud(parameters, function(data){
		if(data.status) {
			$("#monto_delegacion").val(data.monto_delegacion);
			configurarGrid("listSolicitud", data.rows, optionSolicitud);
		} else {
			$("#monto_delegacion").val("-1");
			alert(data.error);
		}
		
	});
};

$(document).ready(function(){
	$(".buttonGPR").addClass("cmd");
	$(".cmd").removeClass("buttonGPR");
	$(".cmd").button();
	
	$("#inifechaSolicitud, #finfechaSolicitud").datepicker({
		dateFormat : 'dd/mm/yy'
	});
	
	$("#btnConsultar").bind("click", buscarSolicitud);
	$("#btnAtender").bind("click", atenderSolicitud);
	
	buscarSolicitud();
});