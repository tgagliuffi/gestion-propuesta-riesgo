<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


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

<title>Insert title here</title>
</head>
<script type="text/javascript" charset="UTF-8">

var lastSel = '';
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColNames  = ['','Codigo', 'Descripcion','Referencia'];
var myDataModel = [ {name : 'codRolHdn',	index : 'codRolHdn'	,	width : VAL_WIDTH.SMALL,	editable:true, editrules: {edithidden:true, required:true}, hidden:true	},
                    {name : 'codRol',		index : 'codRol'		,	width : VAL_WIDTH.VSMALL,editable:true, edittype:'text', editoptions: {size:23, maxlength: 2}, editrules: {required: true, integer: true, minValue: 1}},
                    {name : 'descripcion',		index : 'descripcion'		,	width : VAL_WIDTH.MED,		editable:true, edittype:'text', editoptions: {size:40, maxlength: 100}, editrules: {required: true}},                          
                    {name : 'referencia',		index : 'referencia'		,	width : VAL_WIDTH.MED,		editable:true, edittype:'text', editoptions: {size:40, maxlength: 100}, editrules: {required: true}}                              
                    ];

if (jQuery.browser.msie){
	document.getElementsByName = function(name, tag){
	    return getElementsByName_iefix(name, tag);
	};
}

//frk: si damos agregar y luego ESC se debe eliminar la fila agregada
$(document).keyup(function(e) {
  //frk: se presiono el ESC
  var tecla = (document.all) ? e.keyCode : e.which; 
  if (tecla == 27) {
	  lastSel = '';
	  deleteTblRowAdded('listCanales');
  }
});

function consultar(){
	jQuery("#listCanales").GridUnload();
	RolAction.getLstRoles(function(data){
		mostrarTabla(data);
	});
}

function mostrarTabla(data){
	
	var idTableForm = 'listCanales';
	$('body').append('<div id="paginador_listCanales" class="grid"></div>'); 
	var paginador = "paginador_listCanales";
	
	jQuery("#"+idTableForm).jqGrid(
	{
		beforeSelectRow: function(){},
		afterInsertRow: function(id, rowdata, rowelem){
			
			if(id == undefined){
				jQuery("#"+idTableForm).jqGrid('editRow', id,
		        { 
	       		    keys : true,
	       		    restoreAfterError : true,
	       		    errorfunc: function(rowid, response) {
	       		    	
	      		    	var mensaje = response.responseText;
	      		    	deleteTblRowAdded(idTableForm);
	      		    	
	   		    		if (mensaje != "" && mensaje != undefined) {
	   		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
  		    		    }else{
  		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
  		    		    }   		    		
	       		    },
	       		    afterrestorefunc: function(rowid) {
	       		    	lastSel = '';
	       		    },
	       		    aftersavefunc: function(rowid, response){
	       		    	lastSel = '';
	       		    	flushTblRowAdded(idTableForm, '', 2, 1, '');
	       		    }
	       		});
			}		
		},
		ondblClickRow: function(id, indexRow, indexCol, e){
			
			if(id && id!==lastSel){
				
				if(lastSel != ''){
		        	jQuery('#'+idTableForm).restoreRow(lastSel,true);
		        }
				
		        var descriRol = document.getElementsByName("descripcion")[0];
		    	var refeRol = document.getElementsByName("referencia")[0];
		    	
		    	if(descriRol != undefined && refeRol != undefined){
		    		if($.trim(descriRol.value) == '' || $.trim(refeRol.value) == ''){
		    			
		    			deleteTblRowAdded(idTableForm);
		    			jQuery('#'+idTableForm).jqGrid('editRow',id,
    			        { 
    	        		    keys : true,
    	        		    restoreAfterError : true,
    	        		    errorfunc: function(rowid, response) {
    	        		    	
    	       		    		var mensaje = response.responseText;
    	    		    		if (mensaje != "" && mensaje != undefined) {
    	    		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
    	   		    		    }else{
    	   		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
    	   		    		    }
    	        		    },
    	        		    afterrestorefunc: function(rowid) {
    	        		    	lastSel = '';
    	        		    },
    	        		    aftersavefunc: function(rowid, response){
    	        		    	lastSel = '';
    	        		    }
    	        		});
    			        
    			        lastSel=id;
    			        if(lastSel == 'undefined'){
    			        	lastSel = '';
    			        }    			        
		    			        
		    		}else{
		    			jQuery("#"+idTableForm).jqGrid('saveRow', "undefined",
		    			{
			       		    restoreAfterError : false,
			       		    errorfunc: function(rowid, response) {
			       		    	
			      		    	var mensaje = response.responseText;
			      		    	deleteTblRowAdded(idTableForm);
			      		    	
			   		    		if (mensaje != "" && mensaje != undefined) {
			   		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
		  		    		    }else{
		  		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
		  		    		    }   		    		
			       		    },
			       		    aftersavefunc: function(rowid, response){
			       		    	lastSel = '';
			       		    	flushTblRowAdded(idTableForm, '', 2, 1, '');
			       		    }
			       		});
		    		}			        
		    	}else{
		    		
		    		jQuery('#'+idTableForm).jqGrid('editRow',id,
    		        { 
            		    keys : true,
            		    restoreAfterError : true,
            		    errorfunc: function(rowid, response) {
            		    	
           		    		var mensaje = response.responseText;
        		    		if (mensaje != "" && mensaje != undefined) {
        		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
       		    		    }else{
       		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
       		    		    }
            		    },
            		    afterrestorefunc: function(rowid) {
            		    	lastSel = '';
            		    },
            		    aftersavefunc: function(rowid, response){
            		    	lastSel = '';
            		    }
            		});
    		        
    		        lastSel=id;
    		        if(lastSel == 'undefined'){
			        	lastSel = '';
			        }
		    	}
		    }
		},
		onPaging: function(pgButton) {
			deleteTblRowAdded(idTableForm);
        },
		editurl		: rutaContexto+'/rolAction.do?method=guardarCanalAjax',
	    caption		: "Listado de Roles",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNames,
		colModel 	: myDataModel,
		rowList 	: [5,10,15,20,25],
		rowNum 		: 15, 
		pager 		: paginador,
		viewrecords : true,
		loadonce	: true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
}

function addCanal(){
	var rowid = "-1";
	var mydataadd = 
		  [{
			  codRolHdn: rowid,
			  codRol  : "",
			  descripcion  : "",
			  referencia  : ""
		  }];
	
	if(lastSel != ''){
		jQuery('#listCanales').restoreRow(lastSel,true);
	}
	
	lastSel = '';
	
	var codCanal = $("#undefined_codCanal");
	if(codCanal.length == 0){
		jQuery("#listCanales").addRowData(rowid, mydataadd, 'first');
	}
}

function asignarFunciones(){
	
	var formulario = document.getElementById('rolesForm');
	var selecciones = buscarSelecciones('listCanales');
	if (selecciones.length == 0){
		alert ("No ha seleccionado ningún Registro para la Asignacion");
		return false;
	}else{
		var arrayCod = selecciones.split('**');
		if(arrayCod.length > 2){
			alert ("Solo se debe seleccionar un Registro para la Asignacion");
		}else{
			var codRolAsignar=arrayCod[1].substring(2);
			formulario.action =rutaContexto+'/rolAction.do?method=listarRolesFunciones'+'&codRolAsignar='+codRolAsignar;
			formulario.submit();
		}
	}
}

</script>
<body onload="consultar();">
<html:form styleId="rolesForm" action="rolAction.do?method=listarRoles" >
    <fieldset style="width: 830px">
   	<legend>
   	<font class="fontText">
   	Roles
   	</font></legend>		
		<table width="100%">
			<tr>
				<td align="left" valign="middle">
				<input type="button" class="buttonGPR" name="btnConsultar" id="btnConsultar" value="CREAR ROLES" onclick="addCanal();">
				<input type="button" class="buttonGPR" name="btnConsultar" id="btnConsultar" value="ASIGNAR FUNCIONES" onclick="asignarFunciones();">
			</tr>
		</table>
		</fieldset>

		<p></p>		
    <fieldset style="width: 830px">
   	<legend>
   	<font class="fontText">
   	Lista de Roles
   	</font></legend>		
		<table id="listCanales" class="grid">
		</table>
		</fieldset>
</html:form>
</body>
</html>