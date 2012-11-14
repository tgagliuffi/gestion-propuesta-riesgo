<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%-- <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%> --%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />xt/css" media="screen" href="<%=request.getContextPath()%>/css/buttonOHC.css" />

	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>	
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>

<script type="text/javascript">

$(function() {
    $( "#inifechaSolicitud" ).datepicker({dateFormat: 'dd/mm/yy'});
    $( "#finfechaSolicitud" ).datepicker({dateFormat: 'dd/mm/yy'});
});

var myColNamesSol  = ['','Banca', 'Dict.', 'Territorio', 'Oficina', 'Total', 'Aprobados', 'Rechazados', 'Anuladas', 'Priorizadas'];
var myDataModelSol = [ {name : 'codigo',				width : VAL_WIDTH.SMALL, hidden : true	},
                    {name : 'banca',			index : 'banca', 			width : VAL_WIDTH.LMED		},
                    {name : 'dictaminado',		index : 'dictaminado',		width : VAL_WIDTH.XLSMALL	},
                    {name : 'territorio',		index : 'territorio', 		width : VAL_WIDTH.XLSMALL	},
                    {name : 'oficina',			index : 'oficina', 			width : VAL_WIDTH.XLSMALL	},
                    {name : 'total',			index : 'total', 			width : VAL_WIDTH.XLSMALL	},
                    {name : 'aprobado',			index : 'aprobado', 		width : VAL_WIDTH.XLSMALL	},
                    {name : 'rechazado',		index : 'rechazado', 		width : VAL_WIDTH.XLSMALL	},
                    {name : 'anulada',			index : 'anulada', 			width : VAL_WIDTH.XLSMALL	},
                    {name : 'priorizada',		index : 'priorizada', 		width : VAL_WIDTH.XLSMALL	}
                   ];

function consultarDetallado(){
	
	jQuery("#listDetallado").GridUnload();
	/* ProductoAction.consultarAjax(function(data){
		mostrarTablaDetallado(data);
	}); */
	
	var dataTable = [
	{"codigo":"1", "banca":"Banca Empresas", "dictaminado":"Dictaminador 1", "territorio":"Territorio 1", "oficina":"Oficina 1", "total":"30,000", "aprobado":"30,000", "rechazado":"30,000", "anulada":"30,000", "priorizada":"30,000"},
	{"codigo":"2", "banca":"Banca Personas", "dictaminado":"Dictaminador 2", "territorio":"Territorio 2", "oficina":"Oficina 2", "total":"50,000", "aprobado":"30,000", "rechazado":"30,000", "anulada":"30,000", "priorizada":"45,000"},
	{"codigo":"3", "banca":"Banca Pymes", 	 "dictaminado":"Dictaminador 3", "territorio":"Territorio 3", "oficina":"Oficina 3", "total":"70,000", "aprobado":"30,000", "rechazado":"30,000", "anulada":"30,000", "priorizada":"47,000"}
	];
	
	mostrarTablaDetallado(dataTable);
}

function mostrarTablaDetallado(data){
	
	$('body').append('<div id="paginador_listDetallado" class="grid"></div>'); 
	var paginador = "paginador_listDetallado";
	
	jQuery("#listDetallado").jqGrid(
	{
		beforeSelectRow: function(){},
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesSol,
		colModel 	: myDataModelSol,
		rowList 	: [5,10,15,20,25],
		rowNum 		: 15, 
		pager 		: paginador,
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
}

</script>
	
</head>
<body onload="consultarDetallado();">

<form name="formEstadicticaAtencion" method="post">

	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Módulo de Estadísticas de atención de solicitudes</b></font>
	</div>

	<br/>
	<table style="width: 600px" border="0" cellspacing="0" cellpadding="0">
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText"><b>Banca</b></font>
       </td>
       <td align="left" valign="middle">       
	   		<select id="bancaCliente" name="bancaCliente">
				<option value="TD">Todos</option>
				<option value="BP">Banca Personas</option>
				<option value="BC">Banca Corporativa</option>			
				<option value="BM">Banca Mayorista</option>
				<option value="BE">Banca Empresas</option>
			</select>
	   </td>
	   <td align="left" valign="middle">
       		<font class="fontText"><b>Dictaminado por</b></font>
       </td>
       <td align="left" valign="middle">       
	   		<select id="dictaminado" name="dictaminado">
				<option value="TD">Todos</option>
			</select>
	   </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText"><b>Territorio</b></font>
       </td>
       <td align="left" colspan="3" valign="middle">
			<input type="text" name="codTerritorio" class="cajaTexto" id="codTerritorio" size="10" maxlength="10">
			<input type="text" name="desTerritorio" class="cajaTexto" id="desTerritorio" size="78" maxlength="78">&nbsp;&nbsp;
	   </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText"><b>Oficina</b></font>
       </td>
       <td align="left" colspan="3" valign="middle">
			<input type="text" name="codOficina" class="cajaTexto" id="codOficina" size="10" maxlength="10">
			<input type="text" name="desOficina" class="cajaTexto" id="desOficina" size="78" maxlength="78">&nbsp;&nbsp;
	   </td>
	</tr>
	
	<tr>
		<td align="left" valign="middle">
			<font class="fontText"><b>Fecha Solicitud</b></font>
		</td>
		<td align="left" valign="middle">
			<input type="text" name="inifechaSolicitud" class="cajaTexto" id="inifechaSolicitud" size="14" maxlength="14">&nbsp;al&nbsp;
			<input type="text" name="finfechaSolicitud" class="cajaTexto" id="finfechaSolicitud" size="14" maxlength="14">
		</td>
		<td align="left" valign="middle">
			<input type="button" class="buttonGPR"  name="btnConsultar" id="btnConsultar" value="Consultar">
		</td>	
	</tr>
	</table>
	
	<fieldset style="width: 950px">
   	<legend>
   	<font class="fontText">
   	Detallado
   	</font></legend>

		<table id="listDetallado" class="grid">
		</table>
		
	</fieldset>
	
</form>

</body>
</html>