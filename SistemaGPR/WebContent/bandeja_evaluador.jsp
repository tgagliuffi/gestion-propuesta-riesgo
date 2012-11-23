<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />

	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/JDate.js'></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/BandejaEvaluacionAction.js'></script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/gridUtil.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js"></script>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js"></script> --%>
	
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/dictamen/bandejaEvaluador.js"></script>
	
	<style>
		input.cajaTexto {
			padding-left: 3px;
		}
	</style>
</head>
<body>
	<form name="formBandejaEval" method="post">
		<input type="hidden" name="id_usuario" id="id_usuario" value="${requestScope.id_usuario}"/>
		<input type="hidden" name="monto_delegacion" id="monto_delegacion" value="${requestScope.monto_delegacion}"/>
		<input type="hidden" name="error" id="error" value="${requestScope.error}"/>
		
		<br />
		<div class="ui-widget ui-widget-content ui-corner-all" style="width: 920px;margin: 3px;">
			<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
			<label>Datos de la Solicitud</label>
			</div>
		<table class="ui-widget" style="width: 900px; padding: 5px;" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="middle"><font class="fontText" size="3"
					color="#000080">Código Central</font></td>
				<td valign="middle">
					<input type="text" name="codigoCentral" class="cajaTexto" id="codigoCentral" size="14" maxlength="14" value="${requestScope.codCliente}">
				</td>
				<td valign="middle"><font class="fontText" size="3"
					color="#000080">Número Solicitud</font></td>
				<td valign="middle"><input type="text" name="numSolicitud"
					class="cajaTexto" id="numSolicitud" size="14" maxlength="14">
				</td>
				<td align="right" valign="middle"><input type="checkbox"
					name="stFueraRango" class="cajaTexto" id="stFueraRango">&nbsp;
					<font class="fontText" size="3" color="#000080">Solicitudes Fuera de Rango</font></td>
			</tr>
			<tr>
				<td valign="middle"><font class="fontText" size="3"
					color="#000080">Fecha Solicitud</font></td>
				<td valign="middle"><input type="text" name="inifechaSolicitud"
					class="cajaTexto" id="inifechaSolicitud" size="14" maxlength="14">&nbsp;al&nbsp;
					<input type="text" name="finfechaSolicitud" class="cajaTexto"
					id="finfechaSolicitud" size="14" maxlength="14"></td>
				<td colspan="3" align="right" valign="middle">
					<input type="button" class="buttonGPR" name="btnConsultar" id="btnConsultar" value="Consultar">
					<input type="button" class="buttonGPR" name="btnAtender" id="btnAtender" value="Atender">
				</td>
			</tr>
		</table></div>
		<br />
		<div id="panel_listSolicitud" style="padding-left: 5px;">
			<table id="paginador_listSolicitud" class="grid"></table>
			<table id="listSolicitud" class="grid"></table>
		</div>
	</form>
</body>
</html>