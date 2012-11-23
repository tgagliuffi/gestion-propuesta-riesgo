<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" media="screen" 	href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" 	href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen"  	href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" 	href="<%=request.getContextPath()%>/css/buttonGPR.css" />
	
	<script src="<%=request.getContextPath()%>/js/util.gpr.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" 	type="text/javascript"></script>

	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util/formatters.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/script.js" 	type="text/javascript"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%String valor=(String)request.getAttribute("listaLog"); %>
<script type="text/javascript">
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

function cargaMasiva(){
	var formulario = document.getElementById('usuarioForm');
    formulario.action =rutaContexto+'/usuarioAction.do?method=cargarRegistroMasivo';
	formulario.submit();
}

function descargarPlantilla(){	
	var formulario = document.getElementById('usuarioForm');
    formulario.action =rutaContexto+'/usuarioAction.do?method=descargarPlantillaExcel';
	formulario.submit();
}

function descargarLogError(){	
	var formulario = document.getElementById('usuarioForm');
    formulario.action =rutaContexto+'/usuarioAction.do?method=descargarLogError';
	formulario.submit();
}

function regresar(){	
	var formulario = document.getElementById('usuarioForm');
    formulario.action =rutaContexto+'/usuarioAction.do?method=listarUsuarios';
	formulario.submit();
}
</script>

<title>Insert title here</title>
</head>
<body>
<html:form styleId="usuarioForm" enctype="multipart/form-data">
     <a href="javascript:regresar();" class="buttonGPR">REGRESAR</a>
     <br>
     <br>
    <fieldset style="width: 830px">
   	<legend>
   	<font class="fontText">
   	Carga Masiva
   	</font>
   	</legend>
   	     <input type="button" class="buttonGPR" name="btnConsultar" id="btnConsultar" value="DESCARGAR PLANTILLA" onclick="descargarPlantilla();">
   	    <br/>
   	    <br/>
   		<html:file property="file" />
   <br/>
   <br/>
   <% if(valor!=null){ %>
   <% if(valor.equals("1")){ %>
   <input type="button" class="buttonGPR" name="btnConsultar" id="btnConsultar" value="DESCARGAR LOG DE ERRORES" onclick="descargarLogError();">
   <%} %> 
   <%} %>
   <br/>
   <br/>   
   <input type="button" class="buttonGPR" name="btnConsultar" id="btnConsultar" value="CARGA MASIVA" onclick="cargaMasiva();">
    <br/>
    <br/>

 	</fieldset>
</html:form>
</body>
</html>