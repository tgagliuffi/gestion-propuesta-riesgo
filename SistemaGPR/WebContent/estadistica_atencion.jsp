<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />

	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script type="text/javascript" src='<%=request.getContextPath()%>/dwr/interface/JDate.js'></script>
	<script type="text/javascript" src='<%=request.getContextPath()%>/dwr/interface/EstadisticaAction.js'></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/gridUtil.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/01_jquery.layout.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/02_grid.locale-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/03_ui.multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/04_jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/05_jquery.tablednd.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/06_jquery.contextmenu.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/07_grid.addons.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/08_grid.postext.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/09_grid.setcolumns.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/10_jquery.searchFilter.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/dictamen/general.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/dictamen/estadisticaAtencion.js"></script>
	<style type="text/css">
		* {font-family: Tahoma !important;}
	</style>
</head>
<body>

<form name="formEstadicticaAtencion" method="post">
	<div style="background-color: #0066bb;height: 20px;line-height: 20px;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b style="font-size: 12px;">&nbsp;&nbsp;&nbsp;Módulo de Estadísticas de atención de solicitudes</b></font>
	</div>
	<br/>
	<div style="padding: 5px;">
		<table style="width: 900px" border="0" cellspacing="0" cellpadding="0">
		<tr>
	       <td valign="middle">
	       		<font class="fontText"><b>Banca</b></font>
	       </td>
	       <td valign="middle">       
		   		<select id="bancaCliente" name="bancaCliente">
					<option value="-1">TODOS</option>
				</select>
		   </td>
		   <td valign="middle">
	       		<font class="fontText"><b>Dictaminado por</b></font>
	       </td>
	       <td valign="middle">       
		   		<select id="slctDictamen" name="slctDictamen">
					<option value="-1">TODOS</option>
				</select>
		   </td>
		</tr>
		<tr>
	       <td valign="middle">
	       		<font class="fontText"><b>Territorio</b></font>
	       </td>
	       <td colspan="3" valign="middle">
				<input type="text" name="codTerritorio" class="cajaTexto" id="codTerritorio" size="10" maxlength="10">
				<input type="text" name="desTerritorio" class="cajaTexto" id="desTerritorio" size="78" maxlength="78">&nbsp;&nbsp;
		   </td>
		</tr>
		<tr>
	       <td valign="middle">
	       		<font class="fontText"><b>Oficina</b></font>
	       </td>
	       <td colspan="3" valign="middle">
				<input type="text" name="codOficina" class="cajaTexto" id="codOficina" size="10" maxlength="10">
				<input type="text" name="desOficina" class="cajaTexto" id="desOficina" size="78" maxlength="78">&nbsp;&nbsp;
		   </td>
		</tr>
		
		<tr>
			<td valign="middle">
				<font class="fontText"><b>Fecha Solicitud</b></font>
			</td>
			<td valign="middle">
				<input type="text" name="inifechaSolicitud" class="cajaTexto" id="inifechaSolicitud" size="14" maxlength="14">&nbsp;al&nbsp;
				<input type="text" name="finfechaSolicitud" class="cajaTexto" id="finfechaSolicitud" size="14" maxlength="14">
			</td>
			<td valign="middle">
				<input type="button" class="buttonGPR"  name="btnConsultar" id="btnConsultar" value="Consultar">
				<input type="button" class="buttonGPR"  name="btnExcel" id="btnExcel" value="Excel">
				<input type="button" class="buttonGPR"  name="btnPDF" id="btnPDF" value="PDF" style="display: none">
			</td>	
		</tr>
		</table>
		<br/>
		<div id="tabsEstadisticas" style="width: 900px;">
			<ul>
				<li><a href="#tabs-1">Graficas</a></li>
				<li><a href="#tabs-2">Detalle</a></li>
			</ul>
			<div id="tabs-1">
				<table id="panelGraf" align="center"></table>	
			</div>
			<div id="tabs-2" style="margin: 3px; padding: 3px;">
				<div id="panel_listDetallado">
					<div id="paginador_listDetallado"></div>
					<table id="listDetallado"></table>
				</div>
			</div>
		</div>
	</div>
</form>

</body>
</html>