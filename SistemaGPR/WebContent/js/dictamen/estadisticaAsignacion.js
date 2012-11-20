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
		console.log(data);
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
		
		k = 0;
		html = "<tr>";
		$.each(data.data, function(i, row){
			k++;
			html += "<td><img src='" + obtenerContexto() + "estadisticas.do?method=generarPieChartAsignacion&index=" + i + "'/></td>";
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
	
	buscarEstadisticaAsignacion();
});
