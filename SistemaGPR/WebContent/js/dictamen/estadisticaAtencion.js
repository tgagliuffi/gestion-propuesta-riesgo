buscarOficina = function(){
	if($("#codTerritorio").val() == "-1") {
		options = '<option value="-1">TODOS</option>';
		$("#codOficina").html(options);
	} else {
		EstadisticaAction.cargarComboOficina(function(data){
			options = '<option value="-1">TODOS</option>';
			$.each(data, function(i , columns){
			    var value = columns.codOficina; 
			    var label = columns.nombre;		
			    options +='<option value="' + value + '">' + label + '</option>';
			});	
			$("#codOficina").html(options);
		});	
	}
};

buscarEstadisticaAtencion = function(){
	estadistica = {
		codBanca: $("#bancaCliente").val(),
		codOficina: $("#codOficina").val(),
		codTerritorio: $("#codTerritorio").val(),
		orgDictamen: $("#slctDictamen").val()
	};
	d1 = $("#inifechaSolicitud").val();
	d2 = $("#finfechaSolicitud").val();

	if(d1.length > 0 && d2.length > 0) {
		if(compareDate(d1, d2)){
			fechaInicio = $("#finfechaSolicitud").val();
			fechaFin = $("#inifechaSolicitud").val();
		} else {
			fechaInicio = $("#inifechaSolicitud").val();
			fechaFin = $("#finfechaSolicitud").val();
		}	
	} else {
		alert("Ingrese un rando de fechas.");
		$("#inifechaSolicitud").focus();
		return;
	}

	EstadisticaAction.listarEstadisticasAtencion(estadistica, fechaInicio, fechaFin, function(data){
		
		html  = "<table cellpadding=0 cellspacing=0>";
		html += "<tr>";
		$.each(data.colsName, function(i, col){
			html += "<th class='ui-widget ui-state-default' style='" + (i == 0 ? "" : "border-left: 0px;") + " height: 20px; line-height: 20px; padding-left: 5px; padding-right: 5px; margin-left: 5px; margin-right: 5px;'>";
			html += col;
			html += "</th>";
		});
		html += "</tr>";
		
		$.each(data.data, function(i, row){
			html += "</tr>";
			$.each(data.colsModel, function(j, col){
				html += "<td " + (j == 0 ? "style='padding: 5px; border-left: 1px solid #C5DBEC; border-right: 1px solid #C5DBEC;border-bottom: 1px solid #C5DBEC;'" : "style='border-right: 1px solid #C5DBEC;border-bottom: 1px solid #C5DBEC; text-align: right; padding: 5px;'") + ">";
				html += eval("row." + col.name);
				html += "</td>";
			});
			html += "</tr>";
		});
		html += "</table>";
		
		$("#panel_listDetallado").html(html);
		$("#panelGraf").html("Cargando...");
		
		k = 0;
		html = "<tr>";
		time = (new Date()).getTime();
		$.each(data.data, function(i, row){
			k++;
			html += "<td><img src='" + obtenerContexto() + "estadisticas.do?method=generarPieChartAsignacion&index=" + i + "&time=" + time + "'/></td>";
			if(k == 2){
				html += "</tr><tr>";
				k = 0;
			}
		});
		html += "</tr>";
		$("#panelGraf").html(html);
	});
};

$(function(){
	$("#tabsEstadisticas").tabs();
});

$(document).ready(function(){
	$(".buttonGPR").addClass("cmd");
	$(".cmd").removeClass("buttonGPR");
	$(".cmd").button();
	
	$("#inifechaSolicitud").val(formaterDate(new Date()));
	$("#finfechaSolicitud").val(formaterDate(new Date()));
	$("#inifechaSolicitud, #finfechaSolicitud").datepicker({
		dateFormat : 'dd/mm/yy'
	});

	EstadisticaAction.cargarComboTerritorio(function(data){
		options = '<option value="-1">TODOS</option>';
		$.each(data, function(i , columns){
		    var value = columns.codTerritorio; 
		    var label = columns.nombre;		
		    options +='<option value="' + value + '">' + label + '</option>';
		});	
		$("#codTerritorio").html(options);
	});
	
	EstadisticaAction.cargarComboBanca(function(data){
		options = '<option value="-1">TODOS</option>';
	    $.each(data, function(i , columns){
	        var value = columns.codBanca; 
	        var label = columns.descripcion;		
	        options +='<option value="' + value + '">' + label + '</option>';
	    });	
		$("#bancaCliente").html(options);
	});
	
	buscarEstadisticaAtencion();
	
	$("#codTerritorio").bind("change", buscarOficina);
	$("#btnConsultar").bind("click", buscarEstadisticaAtencion);
	$("#btnExcel").bind("click", function(){
		d1 = $("#inifechaSolicitud").val();
		d2 = $("#finfechaSolicitud").val();

		if(d1.length > 0 && d2.length > 0) {
			if(compareDate(d1, d2)){
				fechaInicio = $("#finfechaSolicitud").val();
				fechaFin = $("#inifechaSolicitud").val();
			} else {
				fechaInicio = $("#inifechaSolicitud").val();
				fechaFin = $("#finfechaSolicitud").val();
			}	
		} else {
			alert("Ingrese un rando de fechas.");
			$("#inifechaSolicitud").focus();
			return;
		}
		
		url = obtenerContexto() + "estadisticas.do?method=generarExcelAsignacion&codBanca=" + $("#bancaCliente").val() + "&fecInicio=" + fechaInicio + "&fecFin=" + fechaFin + "&title=Solicitudes Atendidas";
		window.open(url, "_blank");
	});
	$("#btnPDF").bind("click", function(){
		d1 = $("#inifechaSolicitud").val();
		d2 = $("#finfechaSolicitud").val();

		if(d1.length > 0 && d2.length > 0) {
			if(compareDate(d1, d2)){
				fechaInicio = $("#finfechaSolicitud").val();
				fechaFin = $("#inifechaSolicitud").val();
			} else {
				fechaInicio = $("#inifechaSolicitud").val();
				fechaFin = $("#finfechaSolicitud").val();
			}	
		} else {
			alert("Ingrese un rando de fechas.");
			$("#inifechaSolicitud").focus();
			return;
		}
		
		url = obtenerContexto() + "estadisticas.do?method=generarPDF&codBanca=" + $("#bancaCliente").val() + "&fecInicio=" + fechaInicio + "&fecFin=" + fechaFin + "&title=Solicitudes Atendidas";
		window.open(url, "_blank");
	});
});
