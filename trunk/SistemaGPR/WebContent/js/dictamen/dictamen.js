optionAnalisis = {
	height: 140,
	width: 450,
	colNames: [ "Proceso"
	   , "Motivo"
	   , "codMutlProceso"
	   , "codMultMotivo"
	   , "codAnalisis"
	   , "nroSolicitud"
	   , "comentario"
	   , "estado"
	   , "codAsignacion"
	   , "codCentral"
	   , "codUsuario"
	   , "strMensaje" ],
	colModel: [
	      {name: "desMutlProceso"	, index: "desMutlProceso"	, width: 150}
	    , {name: "desMultMotivo"	, index: "desMultMotivo"	, width: 150}
		, {name: "codMutlProceso"	, index: "codMutlProceso"	, width: 1, hidden: true}
		, {name: "codMultMotivo"	, index: "codMultMotivo"	, width: 1, hidden: true}
		, {name: "codAnalisis"		, index: "codAnalisis"		, width: 1, hidden: true}
		, {name: "nroSolicitud"		, index: "nroSolicitud"		, width: 1, hidden: true}
		, {name: "comentario"		, index: "comentario"		, width: 1, hidden: true}
		, {name: "estado"			, index: "estado"			, width: 1, hidden: true}
		, {name: "codAsignacion"	, index: "codAsignacion"	, width: 1, hidden: true}
		, {name: "codCentral"		, index: "codCentral"		, width: 1, hidden: true}
		, {name: "codUsuario"		, index: "codUsuario"		, width: 1, hidden: true}
		, {name: "strMensaje"		, index: "strMensaje"		, width: 1, hidden: true} ],
	onSelectRow: function(id) {
		row = $(this).getRowData(id);
		$("#textMensaje").val(row.comentario);
	}
};
optionSolicitudDetalle = {
	height: 140,
	width: 855,
	colNames: ["Producto"
	    , "Contrato Vinculado"
	    , "Cod. Pre Evaluador"
	    , "Campa\u00F1a"
	    , "Tipo"
	    , "Monto"
	    , "Plazo"
	    , "codDtSolicitud"
	    , "nroSolicitud"
	    , "indice"
	    , "codProducto"
	    , "codProdBase"
	    , "scoring"
	    , "desGarantia"
	    , "estado"
	    , "codCentral"
	    , "plazoGarantia"
	    , "codMultPlazo"
	    , "codMultCampania"
	    , "codGarantia"],
	colModel: [
		  {name: "desProducto"		, index: "desProducto"		, width: 200}
		, {name: "contratoVinculado", index: "contratoVinculado", width: 130, align: 'center'}
		, {name: "codPrevaluador"	, index: "codPrevaluador"	, width: 130}
		, {name: "desMultCampania"	, index: "desMultCampania"	, width: 150}
		, {name: "tipo"				, index: "tipo"				, width: 90}
		, {name: "monto"			, index: "monto"			, width: 100, align: 'right'}
		, {name: "desMultPlazo"		, index: "desMultPlazo"		, width: 150}
		, {name: "codDtSolicitud"	, index: "codDtSolicitud"	, width: 1, hidden: true}
		, {name: "nroSolicitud"		, index: "nroSolicitud"		, width: 1, hidden: true}
		, {name: "indice"			, index: "indice"			, width: 1, hidden: true}
		, {name: "codProducto"		, index: "codProducto"		, width: 1, hidden: true}
		, {name: "codProdBase"		, index: "codProdBase"		, width: 1, hidden: true}
		, {name: "scoring"			, index: "scoring"			, width: 1, hidden: true}
		, {name: "desGarantia"		, index: "desGarantia"		, width: 1, hidden: true}
		, {name: "estado"			, index: "estado"			, width: 1, hidden: true}
		, {name: "codCentral"		, index: "codCentral"		, width: 1, hidden: true}
		, {name: "plazoGarantia"	, index: "plazoGarantia"	, width: 1, hidden: true}
		, {name: "codMultPlazo"		, index: "codMultPlazo"		, width: 1, hidden: true}
		, {name: "codMultCampania"	, index: "codMultCampania"	, width: 1, hidden: true}
		, {name: "codGarantia"		, index: "codGarantia"		, width: 1, hidden: true}
	],
	caption: "&nbsp;&nbsp;&nbsp;Producto"
};

optionOperacion = {
		height: 140,
		width: 855,
		colNames: ["Codigo Central"
		    , "Cod. Multi Operacion"
		    , "Cod Solicitud Operacion"
		    , "Descr. Operacion"
		    , "Estado"
		    , "Nro. Solicitud"],
		colModel: [
			  {name: "codCentral",			index: "codCentral",			width: 150}
			  ,{name: "codMultOperacion",		index: "codMultOperacion",		width: 150}
			  ,{name: "codSolicitudOperacion",	index: "codSolicitudOperacion",	width: 150}
			  ,{name: "desOperacion",			index: "desOperacion",			width: 150}
			  ,{name: "estado",				index: "estado",				width: 150}
			  ,{name: "nroSolicitud",			index: "nroSolicitud",			width: 150}
		],
		caption: "&nbsp;&nbsp;&nbsp;Producto"
	};




dictamen = function(d){
	if(d != null || d != undefined) {
		habilitaCheckRating(!(d.ctrlRating == 'I'));
		$("#slctDictamen").val(d.codMultDictamen);
	    $("#slctNivAprob").val(d.codMultNivel);
	    $("#tipoMoneda").val(d.codMultMoneda);
		$("#riesgoTotal").val(d.riesgoTotal);
		$("#montoAprobado").val(d.mtoAprobado);
		$("#rating_dictamen").val(d.ctrlRating);
		$("#montoCualitativo").attr("checked", d.cual);
		$("#montoCuantitativo").attr("checked", d.cuant);
		$("#montoICom").attr("checked", d.icon);
		$("#montoCAlerta").attr("checked", d.calerta);
		$("#slctProactividad").val(d.proactividad);
		$("#valOtr").val(d.otro);
		$("#ctrlScoring_dictamen").val(d.ctrlScoring);
		$("#fecVencimiento").val(d.fechaVencimiento);
		$("#textCondicionantes").val(d.condicionantes);
		$("#textGarantia").val(d.garantias);
		$("#alReembolso").val(d.plazo_reembolso);
		$("#alVencimiento").val(d.plazo_vencimiento);
		
		if($("#nivel").val() != '3') {
			$("#slctDictamen").attr("readonly", true);
			$("#slctNivAprob").attr("readonly", true);
			$("#tipoMoneda").attr("readonly", true);
			$("#montoAprobado").attr("readonly", true);
			$("#rating_dictamen").attr("readonly", true);
			$("#montoCualitativo").attr("readonly", true);
			$("#montoCuantitativo").attr("readonly", true);
			$("#montoICom").attr("readonly", true);
			$("#montoCAlerta").attr("readonly", true);
			$("#slctProactividad").attr("readonly", true);
			$("#valOtr").attr("readonly", true);
			$("#ctrlScoring_dictamen").attr("readonly", true);
			$("#fecVencimiento").attr("readonly", true);
			$("#textCondicionantes").attr("readonly", true);
			$("#textGarantia").attr("readonly", true);
			$("#alReembolso").attr("readonly", true);
			$("#alVencimiento").attr("readonly", true);
		}
	} else {
		habilitaCheckRating(true);
	}
};

habilitaCheckRating = function(disabled) {
	$("#montoCualitativo").attr("disabled", disabled);
	$("#montoCuantitativo").attr("disabled", disabled);
	$("#montoICom").attr("disabled", disabled);
	$("#montoCAlerta").attr("disabled", disabled);
};

validaRating = function(){
	rating = $("#rating_dictamen").val().toUpperCase();
	
	if(rating == "C" || rating == "I" || rating == "O") {
		$("#ctrlScoring_dictamen").attr("disabled", true);
	} else if(rating.length == 0) {
		$("#ctrlScoring_dictamen").attr("disabled", false);
	} else {
		$("#ctrlScoring_dictamen").val("");
		alert("Solo puede ingresar los siguientes caracteres C/I/O");
	}
	if(rating == "I" ) {
		habilitaCheckRating(false);
	} else {
		habilitaCheckRating(true);
	}
};

validaCondicionesScoring = function(){
	scoring = $("#ctrlScoring_dictamen").val().toUpperCase();
	if(scoring == "I" && $("#slctDictamen").val() == "14004") {
		$("#btnCondiciones").button("option", "disabled", false);
		$("#btnCondicionesScoring").button("option", "disabled", false);
	} else {
		$("#btnCondicionesScoring").button("option", "disabled", true);
		$("#btnCondiciones").button("option", "disabled", !($("#slctDictamen").val() == "14004"));
	}
};

validaScoring = function(){
	scoring = $("#ctrlScoring_dictamen").val().toUpperCase();
	
	if(scoring == "C" || scoring == "I" || scoring == "O") {
		$("#rating_dictamen").attr("disabled", true);
		habilitaCheckRating(true);
		validaCondicionesScoring();
	} else if(scoring.length == 0) {
		$("#rating_dictamen").attr("disabled", false);
	} else {
		$("#rating_dictamen").val("");
		alert("Solo puede ingresar los siguientes caracteres C/I/O");
	}
};

buscarSolicitud = function(){
	
	if($("#id_usuario").val() == "-1" || $("#monto_delegacion").val() == "-1") {
		$("#formAnalisisDictamen").css({"display": "none"});
		alert($("#error").val());
		return;
	}
	
	parameters = {
		nroSolicitud: $("#nroSolicitud").val(),
		//nroSolicitud :  '402',
		codUsuarioAsigno: $("#id_usuario").val()
	};
		
	
	DictamenAction.buscarSolicitud(parameters, function(data){	
		
		if(data.status) {
			
			configurarGrid("listProducts", data.solicitudDetalle, optionSolicitudDetalle);
			configurarGrid("listAnalisis", data.analisis, optionAnalisis);
			configurarGrid("listLogProceso", data.operaciones, optionOperacion);
			dictamen(data.dictamen);
			
			s = data.solicitud;
			
			if(data.monto_delegacion != null || data.monto_delegacion != undefined) {
				$("#monto_delegacion").val();
				if(data.monto_delegacion > s.riesgoTotal) {
					$("#semaforoRiesgo").attr("src", "imagenes/boton_verde.png");
					$("#btnDictaminar").val("Dictaminar");
				} else {
					$("#semaforoRiesgo").attr("src", "imagenes/boton_rojo.png");
					$("#btnDictaminar").val("Dictaminen Superior");
				}
			} else {
				$("#monto_delegacion").val(0);
			}
			
			$("#codigoCentral").val(s.codCentral);
			$("#tipoPersona").val(s.desMultTipoPersona);
			$("#rucDni").val(s.numeroDocumento);
			$("#razonSocial").val(s.desSolicitante);
			$("#codOficina").val(s.codOficina);
			$("#desOficina").val(s.desOficina);
			$("#codGestor").val(s.gestorCod);
			$("#desGestor").val(s.gestorNom);
			$("#codEmpleador").val(s.empleadorCod);
			$("#desEmpleador").val(s.empleadorNom);
			$("#fechaHoy").val(data.hoy);
			
			$("#rating").val(s.rating);
			$("#scorating").val(s.scorating); 
			$("#clasifCliente").val(s.clasificacion);
			if($.isArray(s.arrayReelevancia)){
				$("#relevPublica1").val(s.arrayReelevancia[0]);
				$("#relevPublica2").val(s.arrayReelevancia[1]);
				$("#relevPublica3").val(s.arrayReelevancia[2]);
				$("#relevPublica4").val(s.arrayReelevancia[3]);
				$("#relevPublica5").val(s.arrayReelevancia[4]);
			}
			$("#deudaDirecta").val(s.deudaDirecta);
			$("#deudaIndirecta").val(s.deudaIndirecta);
			$("#deudaCastigo").val(s.castigo);
			$("#deudaSF").val(s.deudaSistemaFinanciero);
			$("#otrosRiesgos").val(0); // No hay ?
			$("#riesgoGrupal").val(s.riesgoGrupal);
			$("#riesgoActual").val(s.riesgoActual);
			$("#riesgoTotal1").val(s.riesgoTotal);
			$("#riesgoTotal").val(s.riesgoTotal);
			
			$("#tipoMoneda").attr("readonly", false);
			if(s.codMultMoneda != null) {
				codMultMoneda = s.codMultMoneda.split("-"); 
				$("#tipoMoneda").val(codMultMoneda[1]);
				$("#tipoMoneda").attr("readonly", true);
				
				$("#tipoMoneda2").val(codMultMoneda[1]);
			}

			$("#codCuentaEjecutivo").val(s.ejecutivoCtaCod);
			$("#desCuentaEjecutivo").val(s.ejecutivoCtaNom);
			$("#fechaIngresoOfic").val(s.strFechaAsignacion);
			$("#codOficinaAlta").val(s.oficinaAltaCod);
			$("#desOficinaAlta").val(s.oficinaAltaNom);
			$("#codGerenciaTerrit").val(s.gerenciaTerritorialCod);
			$("#desGerenciaTerrit").val(s.gerenciaTerritorialNom);
		
			validaCondicionesScoring();
		} else {
			
			alert(data.error);/*
			$("select").attr("disabled", true);
			$("input:text").attr("disabled", true);
			$("input:checkbox").attr("disabled", true);
			$("#btnAgregarAnalisis").button("option", "disabled", true);
			$("#btnEliminarAnalisis").button("option", "disabled", true);
			$("#btnDictaminar").button("option", "disabled", true);*/
		}
	});
};

habilitarBotonAnalisis = function(show){
	$("#panel_listAnalisis").css({"display": !show ? "none" : "inline"});
	$("#analisisEdit").css({"display": show ? "none" : "inline"});
	$("#btnAgregarAnalisis").css({"display": !show ? "none" : "inline"});
	$("#btnEliminarAnalisis").css({"display": !show ? "none" : "inline"});
	$("#btnCancelarAnalisis").css({"display": show ? "none" : "inline"});
	$("#btnGrabarAnalisis").css({"display": show ? "none" : "inline"});
	$("#textMensaje").attr("readonly", show);
};

optionDialog = {
	width: 420,
	autoOpen: false,
    modal: true,
    buttons: {
        "Aceptar": function() {
        	$(this).dialog("close");
        },
        "Cancelar": function() {
        	$(this).dialog("close");
        }
    },
    close: function() {
    }
}; 

eliminarAnalisis = function(){
	if(confirm("\u00BFEst\u00E1 seguro que desea eliminar este proceso del an\u00E1lisis\u003F")){
		rowId = $("#listAnalisis").jqGrid('getGridParam','selrow');
		if(rowId != null || rowId != undefined) {
			row = $("#listAnalisis").jqGrid('getRowData', rowId);
			analisis = {
				codAnalisis: row.codAnalisis,
				codMutlProceso: row.codMutlProceso,
				codMultMotivo: row.codMultMotivo,
				comentario: row.comentario,
				estado: 1,
				codAsignacion: row.codAsignacion,
				codCentral: row.codCentral,
				codUsuario: $("#codEvaluador").val(),
				nroSolicitud: row.nroSolicitud
			};
			DictamenAction.eliminarAnalisis(analisis, function(data){
				configurarGrid("listAnalisis", data.analisis, optionAnalisis);
				alert(data.error);
			});	
		} else {
			alert("Seleccione el proceso a elimimar");
		}
	}
};

agregarAnalisis = function(){
	habilitarBotonAnalisis(false);
	$("#cboProceso").val("9999999999");
	$("#cboMotivo").val("9999999999");
	$("#textMensaje").val("");
};

grabarAnalisis = function(){
	if($("#cboProceso").val()=="9999999999" || $("#cboMotivo").val()=="9999999999") {
		alert("Debe seleccionar el proceso y motivo");
		return;
	}
	if(confirm("\u00BFEst\u00E1 seguro que desea grabar este proceso del an\u00E1lisis\u003F")){
		analisis = {
			codMutlProceso: $("#cboProceso").val(),
			codMultMotivo: $("#cboMotivo").val(),
			comentario: $("#textMensaje").val(),
			estado: 1,
			codAsignacion: $("#codAsignacion").val(),
			codCentral: $("#codigoCentral").val(),
			codUsuario: $("#codEvaluador").val(),
			nroSolicitud: $("#nroSolicitud").val()
		};
		DictamenAction.agregarAnalisis(analisis, function(data){
			configurarGrid("listAnalisis", data.analisis, optionAnalisis);
			alert(data.error);
		});
		habilitarBotonAnalisis(true);
	}
};

dictamenSuperior = function(dictamen) {
	solicitud = {
		nroSolicitud: $("#nroSolicitud").val(),
		gestorCod: $("#id_usuario").val()
	};
	DictamenAction. dictaminarSuperior(dictamen, solicitud, function(data){
		if(data.warn != null || data.warn != undefined) {
			alert(data.warn);
		}
		alert(data.error);
	});
};

dictamenEvaluador = function(dictamen) {
	DictamenAction.dictaminar(dictamen, 0, function(data){
		if(data.type == -2) {
			if(confirm(data.plazo)){
				dictamenSuperior(dictamen);
			}
		} else {
			if(data.warn != null || data.warn != undefined) {
				alert(data.warn);
			}
			alert(data.error);
		}
	});
};

function evaluarPerfil (obj){
	if($("#nivel").val() == '2' && $(obj).val()== '4') {
		$("#idDictamen").css({"display": ""});
	}else{
		$("#idDictamen").css({"display": "none"});
	}
};

dictaminar = function(){
	rating = 0;
	if($("#rating_dictamen").val().toUpperCase() == "I") {
		rating += $("#montoCualitativo").attr("checked") == "checked" ? 1 : 0;
		rating += $("#montoCuantitativo").attr("checked") == "checked" ? 1 : 0;
		rating += $("#montoICom").attr("checked") == "checked" ? 1 : 0;
		rating += $("#montoCAlerta").attr("checked") == "checked" ? 1 : 0;
		
		if(rating == 0) {
			alert("Debe marcar por lo menos una opci\u00F3n.");
			return;
		} else if(rating < 1 || rating > 3) {
			alert("Solo puede marcar de una a tres opciones.");
			return;
		}
	}
	
	dictamen={				
		codMultDictamen: $("#slctDictamen").val(),
		codMultNivel: $("#slctNivAprob").val(),
		codMultMoneda: $("#tipoMoneda").val(),
		riesgoTotal: $("#riesgoTotal").val(),
		mtoAprobado: $("#montoAprobado").val(),
		ctrlRating: $("#rating_dictamen").val(),
		cual: $("#montoCualitativo").attr("checked"),
		cuant: $("#montoCuantitativo").attr("checked"),
		icon: $("#montoICom").attr("checked"),
		calerta: $("#montoCAlerta").attr("checked"),
		proactividad: $("#slctProactividad").val(),
		otro: $("#valOtr").val(),
		ctrlScoring: $("#ctrlScoring_dictamen").val(),
		fechaVencimiento: $("#fecVencimiento").val(),
		nroSolicitud: $("#nroSolicitud").val(),
		codCentral: $("#codigoCentral").val(),
		estado: 1,
		condicionantes: $("#textCondicionantes").val(),
		garantias: $("#textGarantia").val(),
		plazo_reembolso: $("#alReembolso").val(),
		plazo_vencimiento: $("#alVencimiento").val(),
		listaDocumentos: $("#listaDocumentos").val()		
	};

	if($(this).val()=='Dictaminen Superior') {
		dictamenSuperior(dictamen);
	} else {
		dictamenEvaluador(dictamen);
	}
};

function changeCondicionesScoring(obj){
	listaDoc = $("#listaDocumentos").val();
	listaDoc = listaDoc.replace(/^\s*|\s*$/g,"");
	checkValue = $(obj).val();
	check = $(obj).attr('checked');
	
	if(listaDoc.length == 0 && check == 'checked'){
		$("#listaDocumentos").attr('value',checkValue);
	}else if(listaDoc.length == 0 && check == undefined){		
		$("#listaDocumentos").attr('value','');
	}else if(listaDoc.length > 0 && check == 'checked'){
		$("#listaDocumentos").attr('value',listaDoc+','+checkValue);
	}else if(listaDoc.length > 0 && check == undefined){		
		listaDoc =  listaDoc.replace(checkValue,"");
		listaDoc =  listaDoc.replace(",,",",");
		varR = listaDoc.substring(listaDoc.length-1);
		varL = listaDoc.substring(0,1);		
		if(varR == ','){
			listaDoc = listaDoc.substring(0,listaDoc.length-1);			
		}
		if(varL == ','){
			listaDoc = listaDoc.substring(1);			
		}
		
		$("#listaDocumentos").attr('value',listaDoc);
	}
	
}

function consultarLog(){
	
	//var formulario = document.getElementById('asigacionForm');
	//var codRol = formulario.codRol.value;
	jQuery("#listEvaluador").GridUnload();
	AsignacionAction.consultarUsuarioAjax(codRol, function(data){
		mostrarTablaEvaluador(data);		
	});
}


$(function(){	
	$("#tabsDictamen").tabs();
	$("#dialog-scoring").dialog(optionDialog);
	$("#dialog-mensaje").dialog(optionDialog);
	$("#dialog-form").dialog(optionDialog);
});

$(document).ready(function(){

	$(".buttonGPR").addClass("cmd");
	$(".cmd").removeClass("buttonGPR");
	$(".cmd").button();
	
	DictamenAction.cargarComboProceso(function(data){
		$("#cboProceso").html(contenidocombo(data));
	});
	DictamenAction.cargarComboMotivo(function(data){
		$("#cboMotivo").html(contenidocombo(data));
	});
	DictamenAction.cargarComboDictamen(function(data){
		$("#slctDictamen").html(contenidocombo(data));
	});
	DictamenAction.cargarComboProactividad(function(data){
		$("#slctProactividad").html(contenidocombo(data));
	});
	DictamenAction.cargarComboNivelAprobacion(function(data){
		$("#slctNivAprob").html(contenidocombo(data));
	});
	DictamenAction.cargarComboMoneda(function(data){
		$("#tipoMoneda").html(contenidocombo(data));
	});
	DictamenAction.cargarComboBanca(function(data){
		 options = '<option value="9999999999">SELECCIONAR</option>';
	    $.each(data, function(i , columns){
	        var value = columns.codBanca; 
	        var label = columns.descripcion;		
	        options +='<option value="' + value + '">' + label + '</option>';
	    });	
		$("#bancaCliente").html(options);
	});
	DictamenAction.cargarComboCondicionesScoring(function(data){
		options = "";
		
		$.each(data, function(i , columns){ 
			
		    options +='<tr><td><input type="checkbox" value="' + columns.codElemento + '" id="_' + columns.codElemento + '" onchange="changeCondicionesScoring(this);">&nbsp;&nbsp;' + columns.strValor + "</td></td>";
		});
		$("#listCondiciones").html(options);
	});
	
	$("#btnEliminarAnalisis").bind("click", eliminarAnalisis);
	$("#btnAgregarAnalisis").bind("click", agregarAnalisis);
	$("#btnGrabarAnalisis").bind("click", grabarAnalisis);
	$("#btnCancelarAnalisis").bind("click", function(){
		habilitarBotonAnalisis(true);
	});
	
	
		$("#btnCondiciones").button("option", "disabled", true);
		$("#btnCondicionesScoring").button("option", "disabled", true);
		
		$("#btnCondiciones").bind("click", function() {
	        $("#dialog-form").dialog("open");
	    });
		
		$("#btnCondicionesScoring").bind("click", function() {
	        $("#dialog-scoring").dialog("open");
	    });		
		
		$("#slctDictamen").bind("change", validaCondicionesScoring);
		
		$("#fecVencimiento").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		
		$("#btnDictaminar").bind("click", dictaminar);
		$("#rating_dictamen").bind("change", validaRating);
		$("#ctrlScoring_dictamen").bind("change", validaScoring);
		
	if($("#nivel").val() != '3') {

		$("#idDictamen").css({"display": "none"});
		$("#cboProceso").attr("onchange", "evaluarPerfil(this);");
	} 

	buscarSolicitud();
});