<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@page import="bbva.pe.gpr.bean.*"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonOHC.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
	
	<script src="<%=request.getContextPath()%>/js/script.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>	
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/AsignarOficinaAction.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>

<%
List<Banca> getLstBanca = (List<Banca>)request.getAttribute("getLstBanca");
List<Rol> getLstRoles = (List<Rol>)request.getAttribute("getLstRoles");
%>
<script type="text/javascript">
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColNamesUsuario  = ['','Codigo Usuario','Nombre','SubBancas','Roles'];
var myDataModelUsuario= [  {name : 'codigoUsuario', 	index : 'codigoUsuario', 	width : VAL_WIDTH.SMALL	,hidden : true},
                     {name : 'codigoUsuario', 	index : 'codigoUsuario', 	width : VAL_WIDTH.XLSMALL	},
                     {name : 'nombres',		index : 'nombres', 	width : VAL_WIDTH.SMALL	},
                     {name : 'codUsuarioCreacion',		index : 'codUsuarioCreacion',		width : VAL_WIDTH.LMED},
                     {name : 'codUsuarioModificacion',		index : 'codUsuarioModificacion',		width : VAL_WIDTH.SMALL },
//                      {name : 'genero',		index : 'genero',		width : VAL_WIDTH.SMALL	},
//                      {name : 'codCargo',	index : 'codCargo',	width : VAL_WIDTH.SMALL	}
                    ];

function consultarUsuario(){
	var formulario = document.getElementById('asignarOficinaForm');
	var codUsuario = formulario.codUsuario.value;
	var bancaUsuario = formulario.bancaUsuario.value;
	var rolsuario = formulario.cargoUsuario.value;
	var nombreUsuario = formulario.nombreUsuario.value;
	jQuery("#listUsuarios").GridUnload();
	AsignarOficinaAction.consultarAjax(codUsuario,nombreUsuario,bancaUsuario,rolsuario, function(data){
		mostrarTabla(data);
	});
}

function mostrarTabla(data){
	
	$('body').append('<div id="paginador_listPlazos" class="grid"></div>'); 
	var paginador = "paginador_listPlazos";
	jQuery("#listUsuarios").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Usuarios",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesUsuario,
		colModel 	: myDataModelUsuario,
		rowList 	: [5,10,15,20],
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

function showAsignacion(){
	var formulario = document.getElementById('asignarOficinaForm');
	var selecciones = buscarSelecciones('listUsuarios');
	if (selecciones.length == 0){
		alert ("No ha seleccionado ningún Registro para la Asignacion");
		return false;
	}else{
		var arrayCod = selecciones.split('**');
		if(arrayCod.length > 2){
			alert ("Solo se debe seleccionar un Registro para la Asignacion");
		}else{
			var codUsuario1=arrayCod[1].substring(2);
			formulario.action =rutaContexto+'/asignarOficina.do?method=listarTerritoriOficina'+'&codUsuario1='+codUsuario1;
			formulario.submit();
		}
	}
}

function limpiarUsuario(){
		var formulario = document.getElementById('asignarOficinaForm');
		formulario.codUsuario.value="";
		formulario.bancaUsuario.value="";
		formulario.cargoUsuario.value="";
		formulario.nombreUsuario.value="";
		consultarUsuario();
}
</script>
</head>
<body onload="consultarUsuario()">
<html:form styleId="asignarOficinaForm" action="asignarOficina.do?method=listarAsignarOficina" >
	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF" class=""><b>&nbsp;Parametria de Evaluadores/Dictaminadores </b></font>
	</div>
	<br/>
	<fieldset style="width: 830px">
   	<legend>
   	<font class="fontText">
   	
   	</font></legend>
	<table style="width: 800px" border="0" cellspacing="0" cellpadding="0">
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText"><b>Registro</b></font>
       </td>
       <td align="left" valign="middle">       
	   		<html:text property="codUsuario" styleId="codUsuario" style="width: 150px" maxlength="8" onkeypress="ingresoNumeros(event);"/>				
	   </td>
	  <td align="left" valign="middle">
       		<font class="fontText"><b>SubBanca</b></font>
      </td>
      <td align="left" valign="middle">       
	   	<html:select property="bancaUsuario" styleId="bancaUsuario">
	 	     <html:option value="">--Seleccionar--</html:option>
				 <% if(getLstBanca!=null){   
				 for(int i=0; i<getLstBanca.size();i++){ %>
		     <html:option value="<%=getLstBanca.get(i).getCodBanca().toString()%>"><%=getLstBanca.get(i).getNombre()%></html:option>
  		   		<%}
				 }
  		   		%>
		</html:select>
	   </td>
	   <td align="left" valign="middle">
       		<font class="fontText"><b>Roles</b></font>
       </td>
       <td align="left" valign="middle">       
	   	<html:select property="cargoUsuario" styleId="cargoUsuario">
	 	     <html:option value="">--Seleccionar--</html:option>
	 		 <%if(getLstRoles!=null){   
			      for(int i=0; i<getLstRoles.size();i++){ %>	
		     <html:option value="<%=getLstRoles.get(i).getCodRol().toString()%>"><%=getLstRoles.get(i).getDescripcion()%></html:option>
  			<%}
			}
  			%>
			</html:select>
	   </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText"><b>Nombres</b></font>
       </td>
       <td align="left" valign="middle">
		   	<html:text property="nombreUsuario"  styleId="nombreUsuario" style="width: 150px" onkeypress="ingresoLetras(event)"/>				
	   </td>
	   <td align="left" valign="middle">
<!--        		<font class="fontText"><b>A. Paterno</b></font> -->
       </td>
       <td align="left" valign="middle">       
<%-- 		   	<html:text property="nombreUsuario"  styleId="nombreUsuario" style="width: 150px"/>				 --%>
	   </td>
	    <td align="left" valign="middle">
<!--        		<font class="fontText"><b>A. Materno</b></font> -->
       </td>
       <td align="left" valign="middle">       
<%-- 		   	<html:text property="nombreUsuario"  styleId="nombreUsuario" style="width: 150px"/>				 --%>
	   </td>
	</tr>
	</table>
	</fieldset>
	<br/>
	<a href="javascript:consultarUsuario();" class="buttonGPR">CONSULTAR</a>
	<a href="javascript:showAsignacion();" class="buttonGPR">ASIGNAR OFICINAS</a>
	<a href="javascript:limpiarUsuario();" class="buttonGPR">LIMPIAR</a>
	<br/><br/>
</html:form>
<table id="listUsuarios"></table>
</body>
</html>