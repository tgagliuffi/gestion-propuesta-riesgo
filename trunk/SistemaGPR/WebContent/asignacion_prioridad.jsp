<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />

	<script src="<%=request.getContextPath()%>/js/util.gpr.js" type="text/javascript"></script>
	
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/BusquedaSolicitudAction.js'></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/SolicitudMantenimientoAction.js'></script>	
	
	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util/formatters.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/script.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util.gpr.js" type="text/javascript"></script>

<script type="text/javascript">

$(function() {
    $( "#inifechaSolicitud").datepicker({dateFormat: 'dd/mm/yy'});
    $( "#finfechaSolicitud").datepicker({dateFormat: 'dd/mm/yy'});
});

var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColNames  = ['','Solicitud', 'Fecha', 'Central', 'Cliente', 'Moneda', 'Monto Sol.', 'Riesgo Act.', 'Riesgo Tot.', 'Prioridad', /*'Fuera de Rango', */'Estado','Editar', 'Anular'];

var myDataModel = [ {name : 'nroSolicitud',				width : VAL_WIDTH.SMALL, hidden : true	},
                    {name : 'nroSolicitud',				index : 'nroSolicitud', 		width : VAL_WIDTH.XLSMALL      ,sortable:false},
                    {name : 'strFechaIngreso',			index : 'strFechaIngreso', 		width : VAL_WIDTH.XLSMALL		, sortable:false},                    
                    {name : 'codCentral',				index : 'codCentral', 			width : VAL_WIDTH.XLSMALL		, sortable:false},
                    {name : 'desSolicitante',			index : 'desSolicitante', 		width : VAL_WIDTH.XLSMALL		, sortable:false},
                    {name : 'desMultMoneda',			index : 'codMultMoneda', 		width : VAL_WIDTH.XLSMALL		, sortable:false},
                    {name : 'mtoSolicitud',			index : 'mtoSolicitud', 		width : VAL_WIDTH.XLSMALL		, sortable:false},
                    {name : 'riesgoActual',				index : 'riesgoActual', 		width : VAL_WIDTH.XLSMALL		, sortable:false},
                    {name : 'riesgoTotal',				index : 'riesgoTotal', 			width : VAL_WIDTH.XLSMALL		, sortable:false},
                    {name : 'prioridad',			index : 'prioridad', 			width : VAL_WIDTH.XLSMALL,  formatter: prioridadFormat, sortable: false},
                    //{name : 'fueraRango',			index : 'fueraRango', 			width : VAL_WIDTH.XLSMALL	},
                    {name : 'estado',			index : 'estado', 			width : VAL_WIDTH.XLSMALL,  	formatter: estadoFormat, sortable: false, align:'center'},
                    {name : 'nroSolicitud',			index : 'hdnCodigo', 			width : VAL_WIDTH.XLSMALL,  	formatter: btnOpcionFormatEdit, sortable: false, align:'center'},                                        
                    {name : 'nroSolicitud',			index : 'hdnCodigo', 			width : VAL_WIDTH.XLSMALL,  	formatter: btnOpcionFormatAnular, sortable: false, align:'center'}                                        
                    
                    ];
                   
function btnOpcionFormatEdit(cellvalue, options, rowObject){
	return "<a title='Prioridad Individual' href='javascript:editPrioridad("+cellvalue+");'><img src='imagenes/OpmDetalle.png' border='0' height='18'></a>";
}
function btnOpcionFormatAnular(cellvalue, options, rowObject){
	return "<a title='Anulación Individual' href='javascript:editAnular("+cellvalue+");'><img src='imagenes/editclear.png' border='0' height='18'></a>";
}
function estadoFormat(cellvalue, options, rowObject)
{	
	if(cellvalue == '1')
		return "<img src='imagenes/boton_verde.png' border='0' height='18'/><input type='hidden' id='hidEstado_" + rowObject['nroSolicitud'] + "' value='" + cellvalue + "'/>";
	else if(cellvalue == '0')
		return "<img src='imagenes/boton_rojo.png' border='0' height='18'/><input type='hidden' id='hidEstado_" + rowObject['nroSolicitud'] + "' value='" + cellvalue + "'/>";
	else 
		return "";			
}

function prioridadFormat(cellvalue, options, rowObject){
	var slctPrioridad = "<select id='cmbPrioridad_" + rowObject['nroSolicitud'] + "'>";
	if(cellvalue == 'ALTA')
	{
		slctPrioridad += "<option selected='selected' value='ALTA'>ALTA</option>"+
		"<option value='NORMAL'>NORMAL</option>"+			
		"<option value='BAJA'>BAJA</option>";		
	}
	else if(cellvalue == 'NORMAL')
	{
		slctPrioridad += "<option value='ALTA'>ALTA</option>"+
		"<option selected='selected' value='NORMAL'>NORMAL</option>"+			
		"<option value='BAJA'>BAJA</option>";		
	}	
	else if(cellvalue == 'BAJA')
	{
		slctPrioridad += "<option value='ALTA'>ALTA</option>"+
		"<option value='NORMAL'>NORMAL</option>"+			
		"<option selected='selected' value='BAJA'>BAJA</option>";		
	}	
			
	slctPrioridad += "</select>";	
	
	slctPrioridad += "<input type='hidden' id='hidPrioridad_" + rowObject['nroSolicitud'] + "' value='" + cellvalue + "'/>";
	
	return slctPrioridad;
}

function mostrarData(data){
	$('body').append('<div id="paginador_listSolicitudes" class="grid"></div>'); 
	var paginador = "paginador_listSolicitudes";
	jQuery("#listSolicitud").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Solicitudes",
		data		: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNames,
		colModel 	: myDataModel,
        width 		: 1200,
        rowList 	: [5,10,15,20,25],
		rowNum 		: 10, 
		pager 		: paginador,
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		jsonReader : { repeatitems: false },
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
}

function consultarSolicitud()
{
	var formulario = document.getElementById('formAsigPrioridad');
	var codCentral = formulario.codigoCentral.value;
	var nroSolicitud = formulario.numSolicitud.value;
	var fechaIngresoIni = formulario.inifechaSolicitud.value;
	var fechaIngresoFin = formulario.finfechaSolicitud.value;
		
	jQuery("#listSolicitud").GridUnload();
	BusquedaSolicitudAction.consultarSolicitudAjax('','','',codCentral,nroSolicitud,fechaIngresoIni,fechaIngresoFin,'','','', '', function(data){
		mostrarData(data);
	});
}

function editPrioridad(codigo){
	//window.open('ingreso_solicitud.jsp?asigPrioridadIndividual=true&codigoSolicitud='+codigo,'_self');
	window.open('solicitudMantenimientoAction.do?asigPrioridadIndividual=true&method=detalleSolicitud&codigoSolicitud='+codigo,'_self');
}

function editAnular(codigo){
	window.open('solicitudMantenimientoAction.do?asigAnulacionIndividual=true&method=detalleSolicitud&codigoSolicitud='+codigo,'_self');
}




function procesarPrioridad()
{	
	var selecciones = buscarSelecciones('listSolicitud');	 
	
	if (selecciones.length == 0){
		alert ("No ha seleccionado ningún registro para la actualización masiva de prioridades.");
		return false;
	}else
	{
		var arrayCod = selecciones.split('**');
		var prioridades = "";
		var cmbNombre = "";
		var hidNombre = "";
		var codigo = "";
		
		for(var index = 1; index < arrayCod.length; index++)
		{
			codigo = arrayCod[index].substring(2);
			cmbNombre = "#cmbPrioridad_" + codigo;
			hidNombre = "#hidPrioridad_" + codigo;
			
			if($(cmbNombre).val() != $(hidNombre).val())
			{
				if(prioridades.length == 0)
				{
					prioridades = codigo + "-" + $(cmbNombre).val();
				}	
				else
				{
					prioridades = prioridades + "|" + codigo + "-" + $(cmbNombre).val();
				}
			}			
		}
		
		if(prioridades.length == 0)
			alert('No se realizó ninguna modificación de prioridad');
		else
		{
			SolicitudMantenimientoAction.actualizarPrioridadMasiva(prioridades, function(data){
				if(data.STATUS == 'EXITO')
				{
					consultarSolicitud();
				}
			});
		}
		
	}
	
}

function procesarAnulacion()
{
	
	var selecciones = buscarSelecciones('listSolicitud');	 
	
	if (selecciones.length == 0){
		alert ("No ha seleccionado ningún registro para la anulación masiva.");
		return false;
	}else
	{
		var arrayCod = selecciones.split('**');
		var solicitudes = "";
		var hidNombre = "";
		var codigo = "";
		
		for(var index = 1; index < arrayCod.length; index++)
		{
			codigo = arrayCod[index].substring(2);
			hidNombre = "#hidEstado_" + codigo;
			
			if($(hidNombre).val() != "0")
			{
				if(solicitudes.length == 0)
				{
					solicitudes = codigo;
				}	
				else
				{
					solicitudes = solicitudes + "|" + codigo;
				}
			}			
		}
		
		if(solicitudes.length == 0)
			alert('No se seleccionó solicitudes activas');
		else
		{
			SolicitudMantenimientoAction.actualizarAnulacionMasiva(solicitudes, function(data){
				if(data.STATUS == 'EXITO')
				{
					consultarSolicitud();
				}
			});
		}
		
	}
}

</script>
	
</head>
<body onload="consultarSolicitud();">

<form id="formAsigPrioridad" method="post">

	
	<br/>
	<div class="ui-widget ui-widget-content ui-corner-all" style="width: 920px;margin: 3px;">
	<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
	<label>Datos de la Solicitud</label>
	</div>
	<table style="width: 900px;padding: 5px;" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="middle">
			<font class="fontText">Código Central</font>
		</td>
		<td valign="middle">
			<input type="text" name="codigoCentral" class="cajaTexto" id="codigoCentral" size="10" maxlength="8">
		</td>
		<td valign="middle">
			<font class="fontText">Número Solicitud</font>
		</td>
		<td valign="middle">
			<input type="text" name="numSolicitud" class="cajaTexto" id="numSolicitud" size="10" maxlength="8">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Fecha Solicitud</font>
		</td>
		<td valign="middle">
			<input type="text" name="inifechaSolicitud" class="cajaTexto" id="inifechaSolicitud" size="10" maxlength="8">&nbsp;al&nbsp;
			<input type="text" name="finfechaSolicitud" class="cajaTexto" id="finfechaSolicitud" size="10" maxlength="8">
		</td>
		<td colspan="2" align="left" valign="middle">
			<input type="button" class="buttonGPR"  name="btnConsultar" id="btnConsultar" value="Consultar" onclick="consultarSolicitud();">
		</td>	
	</tr>
	</table></div>
	
	<br/>
	<table id="listSolicitud" class="grid">
	</table>
	<br/>
	
	<input type="button" class="buttonGPR"  name="btnProcesarPrioridad" id="btnProcesarPrioridad" value="Prioridad Masiva" onclick="procesarPrioridad();">&nbsp;
	<input type="button" class="buttonGPR"  name="btnAnularSolicitud" id="btnAnularSolicitud" value="Anulación Masiva" onclick="procesarAnulacion();">
	
</form>

</body>
</html>