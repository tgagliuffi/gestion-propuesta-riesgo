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

	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/RolAction.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
<script type="text/javascript">
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColNamesOficina  = ['','Codigo','Nombre de Funcion'];
var myDataModelOficina = [  {name : 'codFuncion', 	index : 'codFuncion', 	width : VAL_WIDTH.SMALL ,hidden : true	},
                            {name : 'codFuncion', index : 'codFuncion',width : VAL_WIDTH.XLSMALL},
							{name : 'nombre',    index : 'nombre',   width : VAL_WIDTH.LARGE}
];

function consultarRolFunciones(){
	var formulario = document.getElementById('rolesForm');
	var codRol = formulario.codRol.value;
	jQuery("#listFunciones").GridUnload();
	RolAction.lstRolesFunciones(codRol, function(data){
		mostrarTablaRolFunciones(data);
	});
}

function mostrarTablaRolFunciones(data){
	$('body').append('<div id="paginador_listPlazos" class="grid"></div>'); 
	var paginador = "paginador_listPlazos";
	jQuery("#listFunciones").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Oficinas",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesOficina,
		colModel 	: myDataModelOficina,
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
 	var formulario = document.getElementById('rolesForm');
	formulario.action =rutaContexto+'/rolAction.do?method=listarRoles';
 	formulario.submit();	
}

function comboFunciones() {
	 RolAction.consultarFunciones( function(data){
			dwr.util.removeAllOptions("codFunciones");
			dwr.util.addOptions("codFunciones", data,'codFuncion','nombre');
		  });
}

function btnAsignarFunciones(){
	var formulario = document.getElementById('rolesForm');
	codRol=formulario.codRol.value;
	codFuncion=formulario.codFunciones.value;
	RolAction.saveRolFuncion(codRol,codFuncion, function(msg){
		if(msg=="grabo"){
		consultarRolFunciones();			
		}else{
			alert("La funcion ya fue Asignada seleccione otra");
		}
	});			
}

</script>
</head>
<body onload="comboFunciones();consultarRolFunciones();" >
<html:form styleId="rolesForm" method="post" >
<html:hidden property="codRol" value="${getRolForm.codRol}" styleId="codRol"/>
<br/>
	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Asignacion de Funciones</b></font>
	</div>
	<br/>
	<a href="javascript:btnRegresar();" class="buttonGPR">REGRESAR</a>
	<br/><br/>
	<fieldset style="width: 500px">
   	<legend>
   	<font face="Arial Narrow" size=3 color="#000080">
   	Datos del ROL
   	</font></legend>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>
			<font face="Arial Narrow" size=4 color="#000080"><b></b><br>
			Codigo Rol : <c:out value="${getRolForm.codRol}"/>
			</font><br><br>
			<font face="Arial Narrow" size=4 color="#000080">
			Descripcion : <c:out value="${getRolForm.descripcion}"/>
			</font><br><br>
			</td>
		</tr>
		</table>
		</fieldset>
		<fieldset style="width: 500px">
   	<legend>
   	<font face="Arial Narrow" size=3 color="#000080">
   	Datos de las Funciones
   	</font></legend>
			
		<font face="Arial Narrow" size=4 color="#000080">
			Funciones :  <html:select property="codFunciones" styleId="codFunciones">
	 	    					<html:option value="">Seleccionar</html:option>
		    				</html:select>
			</font><br><br>
			<input type="button" class="buttonGPR" name="btnConsultar" id="btnConsultar" value="ASIGNAR" onclick="btnAsignarFunciones();">
<!-- 			<input type="button" class="buttonGPR" name="btnConsultar" id="btnConsultar" value="ASIGNAR FUNCIONES" onclick="eliminarOficina();"> -->
		<br>
		<br>
		<table id="listFunciones" class="grid">
		</table>
		</fieldset>		
		<br>
</html:form>
<br/>
<br/>
<center>
</center>
</body>
</html>