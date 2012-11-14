<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
	<script src="<%=request.getContextPath()%>/js/util.gpr.js" type="text/javascript"></script>
<script type="text/javascript">
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

function loadInfoClient(){
	window.opener.submitPopUp();
	window.close();
}

function init(){
	var nroMsj = window.opener.condicion.value;
	if(nroMsj=='1'){
		document.getElementById('accionRptaCondClient').style.display='';
	}else{
		document.getElementById('accionGrabar').style.display='';
	}
}

function enviarRiesgos(){
	window.opener.submitEnviarRiesgos();
	window.close();
}

function rechazar(){
	window.opener.submitRechazar();
	window.close();
}
function obtener(){
	return window.opener.strMensajePopUP.value;
}

function cancelar(){
	window.close();
}

</script>	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="init();" style="babackground-color:url('imagenes/template/arg_fondo_ex.gif');">
	<div id="accionRptaCondClient" style="display: none;">
		<table style="text-align: center;">
			<tr><td class="txt-titulo" colspan="2" align="left">Cliente No cumplio las siguientes validaciones : </td></tr>
			<tr><td colspan="2"> <script> document.write(obtener());</script> </td></tr>
			<tr>
				<td></td>
			</tr>
			<tr align="center">
				<td align="right"><input type="button" class="buttonGPR" value="Continuar" onclick="window.close();"></input></td>
				<td align="left"><input type="button" class="buttonGPR" value="Cancelar"  onclick="loadInfoClient();"></input></td>
			</tr>
		</table>
	</div>
	<div id="accionGrabar" style="display: none;">
		<table style="text-align: center;">
			<tr><td class=".txt-titulo">Cliente No cumplio las siguientes validaciones : </td></tr>
			<tr>
				<td>Solicitud será enviada a riesgos</td>
			</tr>
			<tr align="center">
				<td align="center">
					<input type="button" class="buttonGPR" value="Enviar a Riesgos" onclick="enviarRiesgos();"></input>
					<input type="button" class="buttonGPR" value="Rechazar"  onclick="rechazar();"></input>
					<input type="button" class="buttonGPR" value="Cancelar"  onclick="cancelar();"></input>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>