<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@page import="bbva.pe.gpr.bean.*"%>
<%@ page import="java.util.List"%>
<%
List<Banca> getLstBanca = (List<Banca>)request.getAttribute("getLstBanca");
List<Rol> getLstRoles = (List<Rol>)request.getAttribute("getLstRoles");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonOHC.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
	
	<script src="<%=request.getContextPath()%>/js/util/formatters.js" type="text/javascript"></script>
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

var myColNamesUsuario  = ['','Codigo Usuario','Nombre','SubBancas','Roles','Editar', 'Eliminar'];
var myDataModelUsuario= [  
                     {name : 'codigoUsuario', 	index : 'codigoUsuario', width : VAL_WIDTH.SMALL	,hidden : true},
                     {name : 'codigoUsuario', 	index : 'codigoUsuario', width : VAL_WIDTH.XLSMALL	},
                     {name : 'nombres',			index : 'nombres', 		 width : VAL_WIDTH.SMALL	},
                     {name : 'codUsuarioCreacion',	index : 'codUsuarioCreacion',width : VAL_WIDTH.LMED},
                     {name : 'codUsuarioModificacion',index : 'codUsuarioModificacion', width : VAL_WIDTH.SMALL },
                     {name : 'codigoUsuario',	index : 'codigoUsuario', width : VAL_WIDTH.VSMALL,  	formatter: accionesFormatEditar, sortable: false, align:'center'},
                     {name : 'codigoUsuario',	index : 'codigoUsuario', width : VAL_WIDTH.VSMALL,  	formatter: accionesFormatEliminar, sortable: false, align:'center'}
                      
                     ];

function accionesFormatEditar(cellvalue, options, rowObject){
	return "<a title=\"Ver Detalle\" href=\"javascript:verDetalleReg('"+cellvalue+"');\"><img src=\"imagenes/OpmDetalle.png\" border=\"0\" height=\"18\"></a>&nbsp;";
}
function accionesFormatEliminar(cellvalue, options, rowObject){
	return "<a title=\"Eliminar\" href=\"javascript:anularRegistro('"+cellvalue+"');\"><img src=\"imagenes/editclear.png\" border=\"0\" height=\"18\"></a>";
}
function verDetalleReg(code){
	var formulario = document.getElementById('usuarioForm');
	formulario.action =rutaContexto+'/usuarioAction.do?method=configuracionUsuario&codUsuarios='+code;
	formulario.submit();
}

function anularRegistro(code){
	var ans = confirm('¿Esta seguro que desea eliminar el registro seleccionados?');
	if(ans){
 	UsuarioAction.deleteUsuario(code, function(msg){
 		consultarUsuario();
 	});
	}
 }

function consultarUsuario(){
	var formulario = document.getElementById('usuarioForm');
	var codUsuario = formulario.codUsuario.value;
	var bancaUsuario = formulario.codBanca.value;
	var rolsuario = formulario.codCargo.value;
	var nombreUsuario = formulario.nombre.value;
	jQuery("#listUsuarios").GridUnload();
	UsuarioAction.consultarAjax(codUsuario,nombreUsuario,bancaUsuario,rolsuario, function(data){
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

function limpiarConsultarUsuario(){
	var formulario = document.getElementById('usuarioForm');
	formulario.codUsuario.value="";
	formulario.codBanca.value="";
	formulario.codCargo.value="";
	formulario.nombre.value="";
	consultarUsuario();
}

function nuevoUsuario(){
	var formulario = document.getElementById('usuarioForm');
	formulario.action =rutaContexto+'/usuarioAction.do?method=configuracionUsuario';
	formulario.submit();
}

function cargaMasiva(){
	var formulario = document.getElementById('usuarioForm');
	formulario.action =rutaContexto+'/usuarioAction.do?method=registroMasivo';
	formulario.submit();		
}
</script>
</head>
<body onload="consultarUsuario();">
<html:form styleId="usuarioForm" method="post" enctype="multipart/form-data">

	<br/>
	<div class="ui-widget ui-widget-content ui-corner-all" style="width: 920px;margin: 3px;">
			<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
				<label>Datos de la Solicitud</label>
			</div>
			<table style="width: 900px;padding: 5px;" border="0" cellspacing="0">
				
	<tr>
		<td valign="middle">
			<font class="fontText">Registro</font>
		</td>
		<td valign="middle">
 		<html:text property="codUsuario" styleId="codUsuario" size="14" maxlength="14"/>
		</td>
		<td valign="middle">
			<font class="fontText">SubBanca</font>
		</td>
		<td valign="middle">
		 	<html:select property="codBanca" styleId="codBanca">
	 	     <html:option value="">--Seleccionar--</html:option>
				 <%if(getLstBanca!=null){   
				 for(int i=0; i<getLstBanca.size();i++){ %>
		     <html:option value="<%=getLstBanca.get(i).getCodBanca().toString()%>"><%=getLstBanca.get(i).getNombre()%></html:option>
  		   		<%}
				 }
  		   		%>
		</html:select>	
	 	</td>
		<td align="right" valign="middle">
			<font class="fontText">Roles</font>&nbsp;			
		</td>
		<td valign="middle">
	  	<html:select property="codCargo" styleId="codCargo">
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
		<td valign="middle">
			<font class="fontText">Nombres</font>
		</td>
		<td valign="middle">
		   	<html:text property="nombre"  styleId="nombre" size="28" maxlength="255"/>				
		</td>
		<td valign="middle">
<!-- 			<font class="fontText">A. Paterno</font> -->
		</td>
		<td valign="middle">
<%-- 		   	<html:text property="apePaterno"  styleId="apePaterno" size="28" maxlength="255"/>				 --%>
		</td>
		<td align="right" valign="middle">
<!-- 			<font class="fontText">A. Materno</font>&nbsp; -->
		</td>
		<td valign="middle">
<%-- 		   	<html:text property="apeMaterno"  styleId="apeMaterno" size="28" maxlength="255"/>				 --%>
		</td>
	</tr>
	<tr>
	<td>&nbsp;</td>
	</tr>
	</table>
	</div>
	<br>
			<a href="javascript:consultarUsuario();" class="buttonGPR">BUSCAR</a>
<!-- 			<a href="javascript:nuevoUsuario();" class="buttonGPR">NUEVO</a> -->
			<a href="javascript:limpiarConsultarUsuario();" class="buttonGPR">LIMPIAR</a>
			<a href="javascript:cargaMasiva();" class="buttonGPR">CARGA MASIVA</a>
	<br/>
	<br>
</html:form>
<table id="listUsuarios"></table>
</body>
</html>