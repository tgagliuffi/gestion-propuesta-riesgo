<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>

<%@page import="bbva.pe.gpr.bean.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>:: Listado de Asignaciones del Evaluador ::</title>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonOHC.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>	
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/UsuarioAction.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
	<script src="<%=request.getContextPath()%>/js/script.js" type="text/javascript"></script>
<script type="text/javascript">
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColNamesFuncionesRoles  = ['','Codigo Funcion','Nombre de Funcion'];
var myDataModelFuncionesRoles = [  {name : 'codFuncion', 	index : 'codFuncion', 	width : VAL_WIDTH.SMALL ,hidden : true	},
                            {name : 'codFuncion', index : 'codFuncion',width : VAL_WIDTH.LSMALL},
							{name : 'descripcion',    index : 'descripcion',   width : VAL_WIDTH.LARGE}
];

function consultarFuncionesRoles(){
	var formulario = document.getElementById('usuarioForm');
	var codRol = formulario.codRol.value;
	jQuery("#listOficinas").GridUnload();
	UsuarioAction.getLstFuncionRol(codRol, function(data){
		mostrarFuncionesRoles(data);
	});
}

function mostrarFuncionesRoles(data){
	$('body').append('<div id="paginador_listPlazos" class="grid"></div>'); 
	var paginador = "paginador_listPlazos";
	jQuery("#listOficinas").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Funciones Roles",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesFuncionesRoles,
		colModel 	: myDataModelFuncionesRoles,
		rowList 	: [5,10],
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

function btnRegresar(){
 	var formulario = document.getElementById('usuarioForm');
 	codUsuario2=formulario.codUsuario.value;
	formulario.action =rutaContexto+'/usuarioAction.do?method=listarRol'+'&codUsuarios='+codUsuario2;
 	formulario.submit();	
}

function comboFunciones() {
	UsuarioAction.consultarFuncionAjax( function(data){
		dwr.util.removeAllOptions("idFunciones");
		dwr.util.addOptions("idFunciones", data,'codFuncion','nombre');
	  });
}

function agregarFuncion(){
	var formulario = document.getElementById('usuarioForm');
	var codRol = formulario.codRol.value;
	var codFuncion=formulario.idFunciones.value;
	UsuarioAction.saveFuncionRolAjax(codRol,codFuncion, function(msg){
		   if(msg == "Grabo"){
			   consultarFuncionesRoles();
				 }	
			});
}
</script>
</head>
<body onload="comboFunciones();consultarFuncionesRoles();">
<html:form styleId="usuarioForm" method="post" >
<html:hidden property="codUsuario" styleId="codUsuario"/>
<html:hidden property="codRol" styleId="codRol" value="${getRol.codRol}"/>
<br/>
	
	<br/>
	<a href="javascript:btnRegresar();" class="buttonGPR">REGRESAR</a>
	<br/><br/>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>
			<font face="Arial Narrow" size=4 color="#000080"><b></b><br>
			Codigo Rol : <c:out value="${getRol.codRol}"/>
			</font><br><br>
			<font face="Arial Narrow" size=4 color="#000080">
			Descripcion Rol : <c:out value="${getRol.descripcionRol}"/>
			</font><br><br>
			<font face="Arial Narrow" size=4 color="#000080">
			Funciones :  <html:select property="idFunciones" styleId="idFunciones">
	 	    	<html:option value="">Seleccionar</html:option>
		    </html:select>
			</font><br><br>
			<br>
			<a href="javascript:agregarFuncion();" class="buttonGPR">AGREGAR</a>
			<br>
			<br>
			<table id="listOficinas" class="grid">
			</table>
		</td>
		</tr>
		</table>
</html:form>
<br/>
<br/>
<center>
</center>
</body>
</html>