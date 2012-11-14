<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bbva.pe.gpr.bean.Banca"%>
<%@ page   import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
		
	<script src="<%=request.getContextPath()%>/js/util.gpr.js" type="text/javascript"></script>
	
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/IngresoSolicitudAction.js'></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/SolicitudMantenimientoAction.js'></script>
	
	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util/formatters.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/script.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/util.gpr.js" type="text/javascript"></script>
	
<script type="text/javascript">
$(function() {
    $( "#fechaIngreso" ).datepicker({dateFormat: 'dd/mm/yy'});
});
function ocultarElementByID(id,tiempo){
	setTimeout("document.getElementById('"+id+"')!=null?document.getElementById('"+id+"').style.display='none':document.getElementById('"+id+"');", tiempo);
}
	ocultarElementByID('divError',4000);
	ocultarElementByID('divAlerta',10000);
	ocultarElementByID('divExito',10000);

//frk: para el caso de IExplorer en el que no 
//funciona bien el "getElementsByName" 

if (jQuery.browser.msie){
	document.getElementsByName = function(name, tag){
	    return getElementsByName_iefix(name, tag);
	};
}

<%
//frk: Parametros con los cuales identificaremos que acción se esta realizando
String codigoSolicitud = request.getParameter("codigoSolicitud");
String asigPrioridadIndividual = request.getParameter("asigPrioridadIndividual");
String asigAnulacionIndividual = request.getParameter("asigAnulacionIndividual");
String indMensaje = (String) request.getAttribute("indMensaje");

%>
var valCampaignGeneric = '';
var valBancaGeneric = '';
var valContratoGeneric = '';
var lastSelProducto = '';
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

$(document).keyup(function(e) {
	  //frk: se presiono el ESC
	  var tecla = (document.all) ? e.keyCode : e.which; 
	  if (tecla == 27) {
		  lastSelProducto = '';
		  deleteTblRowAdded('listProducts');
	  }
	});
var myColNames  = ['','','','', 'Descripción Producto', 'Producto Base','Contrato Vinculado', 'Scoring',  'Cod. Pre Evaluador', 'Campaña', 'Tipo', 'Monto Solicitado', 'Plazo (Meses)', 'Monto Garantizado', 'Total'];
var myDataModel = [
                    {name : 'codProducto',			index : 'codProducto', 			width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true, required:true}, hidden:true},
                    {name : 'valBanca',				index : 'valBanca', 			width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                    {name : 'valMoneda',			index : 'valMoneda', 			width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                    {name : 'valMontoTotal',		index : 'valMontoTotal', 		width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                    {name : 'desProducto',			index : 'desProducto', 			width : VAL_WIDTH.MED , 	editable:true,	edittype:'custom', 	editoptions: {custom_element: desProductoElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: desProductoFormat, unformat: genericUnFormat},                 
                    {name : 'codProdBase',			index : 'codProdBase', 			width : VAL_WIDTH.XLSMALL,	editable:true, 	edittype:'text', 	formatoptions: { disabled: false }, editoptions: {size:10, maxlength: 255}, editrules: {required: true}, align : 'center', readonly: 'readonly'},
                    {name : 'contratoVinculado',	index : 'contratoVinculado',	width : VAL_WIDTH.MED, 		editable:true,	edittype:'custom',  editoptions: {custom_element: contratoVincElementCustom, custom_value: genericComboValueCustom}, editrules:   {required: true}, align : 'center', formatter: contratoVincFormat, unformat: genericUnFormat},
                    {name : 'scoring',				index : 'scoring', 				width : VAL_WIDTH.XLSMALL,  editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 255}, editrules: {required: true}, align : 'center', readonly: 'readonly'},
                    {name : 'codPreEvaluador',		index : 'codPreEvaluador', 		width : VAL_WIDTH.SMALL, 	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 255}, editrules: {required: true}, align : 'center'},
                    {name : 'desCampania',			index : 'desCampania', 			width : VAL_WIDTH.VMED, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: campaniaElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: campaniaFormat, unformat: genericUnFormat},
                    {name : 'desTipo',				index : 'desTipo', 				width : VAL_WIDTH.VMED, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: tipoElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: tipoFormat, unformat: genericUnFormat},
                    {name : 'mtoProducto',			index : 'mtoProducto', 			width : VAL_WIDTH.XLSMALL, 	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 15, style: 'text-align: right', dataEvents: [{ type: 'change', fn: function (){ getMonto(this.value, 1);}}, { type: 'keypress', fn: function (){ ingresoNumeros(event);}}]}, 	editrules: {required: true, number: true, minValue: 0}, align : 'right'},
                    {name : 'plazo',			    index : 'plazo', 				width : VAL_WIDTH.XLSMALL, 	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 15,  style: 'text-align: right',  dataEvents: [{ type: 'keypress', fn: function (){ ingresoNumeros(event);}}]}, editrules: {required: true, integer: true, minValue: 0, maxValue: 999999}, align : 'right'},
                    {name : 'mtoGarantia',			index : 'mtoGarantia', 			width : VAL_WIDTH.SMALL, 	editable:true,	edittype:'text', 	editoptions: {style: 'text-align: right', dataEvents: [{ type: 'change', fn: function (){ getMonto(this.value, 2);}},  { type: 'keypress', fn: function (){ ingresoNumeros(event);}}]}, align : 'right'},
                    {name : 'mtoTotalRow',			index : 'mtoTotalRow', 			width : VAL_WIDTH.XLSMALL, 	editable:true,	edittype:'text', 	editoptions: {style: 'text-align: right', size:15, maxlength: 19}, 	editrules: {required: true, number: true, minValue: 0}, align : 'right', readonly: 'readonly'}
                             
                   ];
var myColSolicituDetalle  = [ 'Descripción Producto', 'Producto Base','Contrato Vinculado', 'Scoring',  'Cod. Pre Evaluador', 'Campaña', 'Tipo', 'Monto Solicitado', 'Plazo (Meses)', 'Monto Garantizado', 'Total'];

var myDataModelSolicitudDetalle = [ {name : 'desProducto',      	index : 'desProducto',       	width : 200      ,sortable:false},
                                    {name : 'desProdBase',      	index : 'desProdBase',       	width : 120      ,sortable:false},
                                    {name : 'contratoVinculado',  	index : 'contratoVinculado',  	width : 150      ,sortable:false},
                                    {name : 'scoring',        		index : 'scoring',         		width : 70      ,sortable:false},
                                    {name : 'codPreEvaluador',    	index : 'codPreEvaluador',      width : 100      ,sortable:false},
                                    {name : 'desCampania',      	index : 'desCampania',       	width : 120      ,sortable:false},
                                    {name : 'desTipo',        		index : 'desTipo',         		width : 80      ,sortable:false},
                                    {name : 'mtoProducto',      	index : 'mtoProducto',       	width : 100      ,sortable:false, editoptions: {style: 'text-align: right'}},
                                    {name : 'plazo',          		index : 'plazo',         		width : 80      ,sortable:false},
                                    {name : 'mtoGarantia',      	index : 'mtoGarantia',       	width : 100      ,sortable:false, editoptions: {style: 'text-align: right'}},
                                    {name : 'mtoTotalRow',      	index : 'mtoTotalRow',       	width : 60      ,sortable:false, editoptions: {style: 'text-align: right'}}
                        ];
function setEventsValidationProducto(){
    jQuery("select[name=desProducto]").bind("change",function (evnt){
    	getProductoBaseElementText(this);
    	document.getElementsByName("scoring")[0].value = '';
    	changeContratoByProduct(this);    	
    });
}

function setEventsValidationContrato(){
    jQuery("select[name=contratoVinculado]").bind("change",function (evnt){
    	getScoringElementText(this);
    });
}

function changeContratoByProduct(obj){
	var indice = obj.id.split("desProducto");
	dwr.util.removeAllOptions(indice[0]+"contratoVinculado");
	
	IngresoSolicitudAction.cargarContratosVincPorProducto(obj.value, function(data){
		dwr.util.addOptions(indice[0]+"contratoVinculado", data, 'indxContrato','codContrato');
	  	setElementSelected(dwr.util.byId(indice[0]+"contratoVinculado"), valContratoGeneric);
	});
}

function desProductoElementCustom(valElement, options) {
 var codBanca = document.getElementById("codBanca");
	  IngresoSolicitudAction.consultarAjax(codBanca.value, function(data){
	  	dwr.util.addOptions(options.id, data, 'codProducto','descripcion');
	  	setElementSelected(dwr.util.byId(options.id), valElement);
	 });
	return document.createElement("select");
}

function campaniaElementCustom(valElement, options) {
  valCampaignGeneric = valElement;  
  IngresoSolicitudAction.cargarCampaniasAjax(function(data){
	  	dwr.util.addOptions(options.id, data, 'codCampania','nombre');
	  	setElementSelected(dwr.util.byId(options.id), valElement);
  });
  return document.createElement("select");
}

function tipoElementCustom(valElement, options){
	IngresoSolicitudAction.cargarTipos(function(data){
		dwr.util.addOptions(options.id, data,'codElemento','strValor');
		setElementSelected(dwr.util.byId(options.id), valElement);
	});
	return document.createElement("select");
}

function contratoVincElementCustom(valElement, options) {
	valContratoGeneric=valElement;
	  var codProducto = document.getElementById("codBanca");
	  if(codProducto.value == '1'){ //frk: en el caso de Banca Personas, sera un combobox
		  IngresoSolicitudAction.cargarContratosVincPorProducto(codProducto, function(data){
		  	dwr.util.addOptions(options.id, data, 'indxContrato','codContrato');
			setElementSelected(dwr.util.byId(options.id), valElement);
		  });
		  
		  return document.createElement("select");  
	  }else{
		  var valReal = valElement.split("<input");
		  var el = document.createElement("input");
		  el.type="text";
		  el.value = valReal[0];
		  return el;
	  }
}

function getProductoBaseElementText(obj){
	var codProducto = obj.value;
	 IngresoSolicitudAction.getProductoBaseAjax(codProducto,  function(msg){
		document.getElementsByName("codProdBase")[0].value = msg;
	});
	 
}
function getScoringElementText(obj){
	var codContrato = obj.value;
	IngresoSolicitudAction.getScoring(codContrato,  function(msg){
		document.getElementsByName("scoring")[0].value = msg;
	});
}

function consultarProductos(operation, message){
  jQuery("#listProducts").GridUnload();
	IngresoSolicitudAction.consultarProductoBancaAjax(function(data){
		mostrarTabla(data);
		if(operation == 'logicDelete'){
			alert(message);
		}
	});
}


function getMonto(value, call){
	
	var pdeudaDirecta = "00.00";  
		if(document.getElementsByName('deudaDirecta')[0]!='undefined')
		{pdeudaDirecta = document.getElementsByName('deudaDirecta')[0].value;}
	
	var pdeudaIndirecta = "00.00";
		if(document.getElementsByName('deudaIndirecta')[0]!='undefined')
		{pdeudaIndirecta=document.getElementsByName('deudaIndirecta')[0].value;}
	
	var pdeudaCastigo = "00.00";
		if( document.getElementsByName('castigo')[0]!='undefined')
		{pdeudaCastigo = document.getElementsByName('castigo')[0].value;}
	
	var pdeudaSisFinanciero = "00.00";
		if(document.getElementsByName('deudaSistemaFinanciero')[0]!='undefined')
		{pdeudaSisFinanciero = document.getElementsByName('deudaSistemaFinanciero')[0].value;}
	
	var pRiesgoGrupal = "00.00";
		if(document.getElementsByName('riesgoGrupal')[0]!='undefined')
		{ pRiesgoGrupal = document.getElementsByName('riesgoGrupal')[0].value;}
	
	var pMtoGarantia = "00.00";
		if(document.getElementsByName('mtoGarantia')[0]!='undefined')
		{pMtoGarantia = document.getElementsByName('mtoGarantia')[0].value;}
	
	var pMtoProducto = "00.00";
		if(document.getElementsByName('mtoProducto')[0]!='undefined')
		{pMtoProducto = document.getElementsByName('mtoProducto')[0].value;}
	
	var pOtroRiesgo = "00.00";
		if(document.getElementsByName('otroRiesgo')[0]!='undefined'){
		pOtroRiesgo = document.getElementsByName('otroRiesgo')[0].value;
	}
	
	var pMtoTotalProd = "00.00";
		if(document.getElementsByName('mtoTotal')[0]!='undefined')
		{pMtoTotalProd = document.getElementsByName('mtoTotal')[0].value;}


	
	if(call==1){
		IngresoSolicitudAction.changeMtoTotalRowAjax(value, pMtoGarantia, function(msg){
			document.getElementsByName("mtoTotalRow")[0].value = msg;
			pMtoTotalProd=msg;
			IngresoSolicitudAction.changeMtoTotalAjax(value, function(msg){
				document.getElementsByName("mtoTotal")[0].value = msg;
			});
			IngresoSolicitudAction.changeRiesgoTotalAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, pRiesgoGrupal, pMtoTotalProd, function(msg){
				document.getElementsByName("riesgoTotal")[0].value = msg;
			});		
		});
		
	}if(call==2){
		IngresoSolicitudAction.changeMtoTotalRowAjax(value, pMtoProducto, function(msg){
			document.getElementsByName("mtoTotalRow")[0].value = msg;
		});
		IngresoSolicitudAction.changeOtroRiesgoAjax(value, function(msg){
			document.getElementsByName("otroRiesgo")[0].value = msg;
			pOtroRiesgo=msg;
			IngresoSolicitudAction.changeRiesgoActualAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, pRiesgoGrupal,  function(msg){
				document.getElementsByName("riesgoActual")[0].value = msg;
			});	
			IngresoSolicitudAction.changeRiesgoTotalAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, pRiesgoGrupal, pMtoTotalProd, function(msg){
				document.getElementsByName("riesgoTotal")[0].value = msg;
			});	
		});
		
	}if(call==3){
			IngresoSolicitudAction.changeRiesgoActualAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, value,  function(msg){
				document.getElementsByName("riesgoActual")[0].value = msg;
		});	
		IngresoSolicitudAction.changeRiesgoTotalAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, value, pMtoTotalProd, function(msg){
			document.getElementsByName("riesgoTotal")[0].value = msg;
		});	
	}

}

function mostrarTabla(data){
	
	var idTableForm = 'listProducts';
	$('body').append('<div id="paginador_'+idTableForm+'" class="grid"></div>'); 
	var paginador = "paginador_"+idTableForm;
	
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
	       		    	lastSelProducto = '';
	       		    },
	       		    aftersavefunc: function(rowid, response){
	       		    	lastSelProducto = '';
	       		    	var idGenerado = response.responseText;
	       		    	flushTblRowAdded(idTableForm, '', 0, 1, idGenerado);
	       		    }
	       		});
				
				setEventsValidationProducto();
				setEventsValidationContrato();
				
			}		
		},
		ondblClickRow: function(id, indexRow, indexCol, e){
			
			if(id && id!==lastSelProducto){
				
				if(lastSelProducto != ''){
		        	jQuery('#'+idTableForm).restoreRow(lastSelProducto,true);
				}
				
		    	var desProducto = document.getElementsByName("desProducto")[0];
		    	if(desProducto != undefined){
		    		if($.trim(desProducto.value) == ''){
		    			
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
    	        		    	lastSelProducto = '';
    	        		    },
    	        		    aftersavefunc: function(rowid, response){
    	        		    	lastSelProducto = '';
    	        		    }
    	        		});
		    			
		    			setEventsValidationProducto();
						setEventsValidationContrato();

		    			
		    			lastSelProducto=id;
    			        if(lastSelProducto == 'undefined'){
    			        	lastSelProducto = '';
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
			       		    	lastSelProducto = '';
			       		    	var idGenerado = response.responseText;
			       		    	flushTblRowAdded(idTableForm, '', 0, 1, idGenerado);
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
            		    	lastSelProducto = '';
            		    },
            		    aftersavefunc: function(rowid, response){
            		    	lastSelProducto = '';
            		    }
            		});
		    		
		    		setEventsValidationProducto();
					setEventsValidationContrato();
				
    		        
		    		lastSelProducto=id;
    		        if(lastSelProducto == 'undefined'){
    		        	lastSelProducto = '';
			        }
		    	}
		    }
		},
		onPaging: function(pgButton) {
			deleteTblRowAdded(idTableForm);
        },
		editurl		: rutaContexto+'/ingresoSolicitud.do?method=guardarProductoAjax',
		caption		: "Listado de Productos",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNames,
		colModel 	: myDataModel,
		rowList 	: [5,10,15,20,25],
		width 		: 1260,
		rowNum 		: 15, 
		pager 		: paginador,
		scrollOffset: 1,
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
	$("#listProducts").closest(".ui-jqgrid-bdiv").attr("style",
			$("#listProducts").closest(".ui-jqgrid-bdiv").attr("style") + " overflow-y: scroll; ");
}

function consultarDetalle(){
	var formulario = document.getElementById('formSolicitudIngreso');
	var nroSolicitud = formulario.mantener.value;
	jQuery("#listProductsDetalle").GridUnload();
	SolicitudMantenimientoAction.consultarSolicitudDetalleAjax(nroSolicitud, function(data){
		mostrarTablaDetalle(data);
	});
}
function mostrarTablaDetalle(data){
	
	$('body').append('<div id="paginador_listPlazos" class="grid"></div>'); 
	
	jQuery("#listProductsDetalle").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Usuarios",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColSolicituDetalle,
		colModel 	: myDataModelSolicitudDetalle,
		rowList 	: [5,10,15,20],
		rowNum 		: 10, 
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		jsonReader : { repeatitems: false },
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
	$("#listProductsDetalle").closest(".ui-jqgrid-bdiv").attr("style",
			$("#listProductsDetalle").closest(".ui-jqgrid-bdiv").attr("style") + " overflow-y: scroll; ");
}

function addProducto(){
	
	var codBanca = document.getElementById("codBanca");
	var codMultMoneda = document.getElementById("codMultMoneda");
	var mtoTotal = document.getElementById("mtoTotal");
	var deudaDirecta = document.getElementById("deudaDirecta").value;
	var deudaIndirecta = document.getElementById("deudaIndirecta").value;
	var castigo = document.getElementById("castigo").value;
	var deudaSistemaFinanciero = document.getElementById("deudaSistemaFinanciero").value;
	var riesgoGrupal = document.getElementById("riesgoGrupal").value;
	var otroRiesgo = document.getElementById("otroRiesgo").value;
	
	if(codBanca.value == '-1'){
		alert("Debe seleccionar una banca para poder agregar un registro.");
	}else if(codMultMoneda.value == '-1'){
		alert("Debe seleccionar una moneda para poder agregar un registro.");
	}else{
		var rowid = "-1";
		var mydataadd = 
			  [{
				codProducto			: rowid,
				valBanca			: codBanca.value,
                valMoneda			: codMultMoneda.value,
                valMontoTotal		: mtoTotal.value,
                valDeudaDirecta		: deudaDirecta,
                valDeudaIndirecta	: deudaIndirecta,
                valCastigo			: castigo,
                valDeudaSisFinan	: deudaSistemaFinanciero,
                valRiesgoGrupal		: riesgoGrupal,
                valOtroRiesgo	    : otroRiesgo,
                desProducto 		: "",
                codProdBase			: "",
                contratoVinculado 	: "",
                scoring				: "",			
				codPreEvaluador 	: "",
				campania  			: "",
				tipo    			: "",
				mtoProducto  		: "00.00",
				plazo  				: "",
				mtoGarantia  		: "00.00",
				mtoTotalRow			: "00.00"
				
				
			  }];	
		
		if(lastSelProducto != ''){
			jQuery('#listProducts').restoreRow(lastSelProducto,true);
		}
		
		lastSelProducto = '';
		
		var subProducto = $("#undefined_desProducto");
		if(subProducto.length == 0){
			valBancaGeneric = codBanca.value;
			jQuery("#listProducts").addRowData(rowid, mydataadd, 'first');
		}		
		
	}
}

function deleteProducto(){
	var selecciones = buscarSelecciones("listProducts");
	if (selecciones.length == 0){
		alert('No ha seleccionado ningún elemento para la eliminación.');
	}else{
		
		var ans = confirm('¿Esta seguro que desea eliminar los registros seleccionados?');
		if(ans){
			
			IngresoSolicitudAction.eliminarProductoAjax(selecciones, function(msg){
				
				var paramDelete = '';
				if(msg != ''){
					paramDelete = 'logicDelete';
				}
				
				consultarProductos(paramDelete, msg);
			});			
		}
	}
}

function changeBankListProducts(obj){
	if(valBancaGeneric == ''){
		valBancaGeneric = obj.value;
	}else if(valBancaGeneric != obj.value){
		var tabla = document.getElementById("listProducts");
	  	var tablaBody = tabla.getElementsByTagName("tbody")[0];
	  	var filas = tablaBody.getElementsByTagName("tr");
	  	if(filas.length > 1){
	  		var ans = confirm('Se cambio la banca seleccionada, se eliminarán los registros agregados. ¿Esta seguro que desea continuar?');
			if(ans){
				//frk: aca se limpia la grilla para volver añadir los 
				//registros relacionados a la nueva banca en seleccion
				valBancaGeneric = obj.value;
				consultarProductos('','');
			}else{
				obj.value = valBancaGeneric;
				
			}
	  	}
	}	
}


function showBodyForm(){
	loadInfoClient();
}

function loadInfoClient(){
	var formulario = document.getElementById('formSolicitudIngreso');
	if(formulario.codCentral.value!=''){
		formulario.action = rutaContexto+'/ingresoSolicitud.do?method=showClient';
		formulario.submit();
	}else{
		alert('Ingrese código central.');
		formulario.codCentral.focus();
	}
}

function guardarSolicitud(){
	 var formulario = document.getElementById('formSolicitudIngreso');
		formulario.action = rutaContexto+'/ingresoSolicitud.do?method=insertSolicitud';;
		formulario.submit();
		consultarProductos('','');
	
}

function backAsignacionPrioridad(){
	window.open('asignacion_prioridad.jsp','_self');
}


function init(){
	var formulario = document.getElementById('formSolicitudIngreso');
	consultarProductos('','');
	mostrarPopUp();
	consultarProductos();
	if(formulario.mantener.value!=''){
		consultarDetalle();
	}
}
function mostrarPopUp(){
	var formulario = document.getElementById('formSolicitudIngreso');
	if(formulario.flagPopUP.value=='mostrarContinuar'){
		formulario.condicion.value = '1';
		window.open('ingreso_solicitud_mensaje.jsp','windowAsigMensaje', "scrollbars=0,scrolling=no,top=" +
				(screen.height - 500) + ",height=180,width=300,left=" + ((screen.width - 800)/2) + ",resizable=no");
	}if(formulario.flagPopUP.value=='envioRiesgos'){
		formulario.condicion.value = '2';
		window.open('ingreso_solicitud_mensaje.jsp','EnviarRiesgos', "scrollbars=0,scrolling=no,top=" +
				(screen.height - 500) + ",height=180,width=380,left=" + ((screen.width - 800)/2) + ",resizable=no");
	}	
}
function submitPopUp(){
	 var formulario = document.getElementById('formSolicitudIngreso');
		formulario.action = rutaContexto+'/ingresoSolicitud.do?method=init&param=continuar';;
		formulario.submit();
}
function submitEnviarRiesgos(){
	 var formulario = document.getElementById('formSolicitudIngreso');
	 	formulario.condicion.value='ER';
		formulario.action = rutaContexto+'/ingresoSolicitud.do?method=insertSolicitud';
		formulario.submit();
}
function submitRechazar(){
	 var formulario = document.getElementById('formSolicitudIngreso');
	 	formulario.condicion.value='R';
		formulario.action = rutaContexto+'/ingresoSolicitud.do?method=insertSolicitud';
		formulario.submit();
}
function limpiaForm(miForm) {

}
function update(parametro){
var formulario = document.getElementById('formSolicitudIngreso');
var nroSolicitud = formulario.mantener.value;
	if(parametro == 'priorizar'){
		var var_prioridad =formulario.prioridad.value;
		jQuery("#listProductsDetalle").GridUnload();
		SolicitudMantenimientoAction.priorizarSolicitud(nroSolicitud,var_prioridad,  function(data){
			mostrarTablaDetalle(data);
		});
	}else{
		var flag = formulario.chkAnular.checked;
		if(flag==true)
		if(confirm("¿Seguro que desea anular la solicitud?")){
			jQuery("#listProductsDetalle").GridUnload();
			SolicitudMantenimientoAction.anularSolicitud(nroSolicitud, function(data){
				mostrarTablaDetalle(data);
			});
		}
	
	}
}
	
</script>
	
</head>
<body onload="init();">
<html:form method="post" action="ingresoSolicitud.do?method=init" styleId="formSolicitudIngreso">
<!-- <form name="formIngresoSolicitud" method="post"> -->
	<input type="hidden" id="flagPopUP" name="flagPopUP" value='${solicitudForm.flagPopUP}'></input>
	<input type="hidden" id="hdnCodCentral" name="hdnCodCentral" value='${solicitudForm.hdnCodCentral}'></input>
	<input type="hidden" id="strMensajePopUP" name="strMensajePopUP" value='${solicitudForm.strMensajePopUP}'></input>
	<input type="hidden" id="codMultTipoPersona" name="codMultTipoPersona" value='${solicitudForm.codMultTipoPersona}'></input>
	<input type="hidden" id="mantener" name="mantener" value='${Solicitud.nroSolicitud}'></input>
	
	<input type="hidden" id="condicion" name=condicion value=''></input>	
	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF">&nbsp;Módulo de Ingreso de la Solicitud</font>
	</div>

	<%if(asigPrioridadIndividual != null){%>
	<br/>
	<input type="button" class="buttonGPR"  name="btnRegresar" id="btnRegresar" onclick="backAsignacionPrioridad();" value="Regresar">&nbsp;
	<input type="button" class="buttonGPR"  name="btnPriorizar" id="btnPriorizar"   value="Asiganar Prioridad" onclick="update('priorizar');">
	<br/>
	<%}%>
	<%if(asigAnulacionIndividual != null){%>
	<br/>
	<input type="button" class="buttonGPR"  name="btnRegresar" id="btnRegresar" onclick="backAsignacionPrioridad();" value="Regresar">&nbsp;
	<input type="button" class="buttonGPR"  name="btnAnular" id="btnAnular"  class="buttonGPR"  value="Anular" onclick="update('anular');">
	<br/>
	<%}%>
	<br/>
	<table width="1200px" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>	
		<td>  <%if(indMensaje != null){ //error%>
				<%if(indMensaje.equals("1")){//error%>
				<div id="divExito" style="text-align: center;">
				<html:text property="outputExito"  styleId="outputExito" styleClass="outputExito" style="width:100%;" value="${strMensaje}"></html:text>
				</div>
				<%}if(indMensaje.equals("-1")){%>
				<div id="divAlerta" style="text-align: center;">
				<html:text property="outputAlerta" styleClass="outputAlerta" styleId="outputAlerta" style="width:100%;" value="${strMensaje}"></html:text>
				</div>
				<%}if(indMensaje.equals("0")){%>
				<div id="divError" style="text-align: center;">
				<html:text property="outputError" styleClass="outputError" styleId="outputError" style="width:100%;" value="${strMensaje}"></html:text>				
				</div>
				<% } }%>
			</td>
	</tr>
	</table>
	<table width="740px" height="100%" border="0" cellspacing="0" cellpadding="0">
	
	<tr>
		<td>
			<font class="txt-titulo">Código Central</font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<html:text property="codCentral" styleClass="cajaTexto" styleId="codigoCentral" size="10" maxlength="8" readonly="readonly" value="${Solicitud.codCentral}"></html:text><html:errors property="codCentral"/>
			<%}else{%>
			<html:text property="codCentral" styleClass="cajaTexto" styleId="codigoCentral" size="10" maxlength="8" value="" onkeypress="ingresoNumeros(event);" ></html:text><html:errors property="codCentral"/>
			<%}%>
			
			<%if(asigPrioridadIndividual == null && asigAnulacionIndividual == null){%>
			&nbsp;<a href="javascript:showBodyForm();"><img src="imagenes/lupa.gif" border="0" height="18"></a>
			<%}%>
		</td>
		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
		<td align="right">
			
			<%if(asigPrioridadIndividual != null){%>
				<font class="fontText">Prioridad</font>&nbsp;
				<select id="prioridad" name="prioridad">
					<option value="1">Alta</option>			
					<option value="2">Normal</option> <!-- frk: valor seleccionado por defecto -->
					<option value="3">Baja</option>
				</select>
			<%}%>
			
			<%if(asigAnulacionIndividual != null){%>
				<font class="fontText">Anular</font>&nbsp;
				<input type="checkbox" name="chkAnular" id="chkAnular">
			<%}%>
			
		</td>
		<%}%>
	</tr>
	</table>
	<br/>
	
	<div class="ui-widget ui-widget-content ui-corner-all" style="width: 860px;margin: 3px;">
	<div  align="center" class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
	<label>Datos del Cliente</label>
	</div>
	<table>	
	<tr>
		<td colspan="2" valign="middle">
        	<font class="fontText">Nro. Solicitud</font>&nbsp;
        	
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="nroSolicitud" class="cajaTexto" id="nroSolicitud" size="19" maxlength="19" value="${Solicitud.nroSolicitud}" readonly="readonly">&nbsp;
			<%}else{%>
			<input type="text" name="nroSolicitud" class="cajaTexto" id="nroSolicitud" size="19" maxlength="19" value='${solicitudForm.nroSolicitud}' readonly="readonly">&nbsp;
			<%}%>
			
			<font class="fontText">Tipo Persona *</font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
				<input type="text" name="desMultTipoPersona" class="cajaTexto" id="desMultTipoPersona" size="20" maxlength="8"  value="${Solicitud.desMultTipoPersona}" readonly="readonly">&nbsp;
				<%}else{%>
				<input type="text" name="desMultTipoPersona" class="cajaTexto" id="desMultTipoPersona" size="20" maxlength="60" value='${solicitudForm.desMultTipoPersona}' readonly="readonly">&nbsp;
			<%}%>			
			
			<font class="fontText">Ruc / DNI *</font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>  
				<html:text property="numeroDocumento" styleClass="cajaTexto" styleId="numeroDocumento" size="27" maxlength="27" value="${Solicitud.numeroDocumento}" readonly="readonly" />
				<%}else{%>
				<html:text property="numeroDocumento" styleClass="cajaTexto" styleId="numeroDocumento" size="27" maxlength="27" value='${solicitudForm.numeroDocumento}' readonly="readonly"/>
			<%}%>
			
			
       </td>
    </tr>
    <tr>
       <td colspan="2" valign="middle">
       		<font class="fontText">Razon Social / Apellidos y Nombres *</font>&nbsp;
       		
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
       		<html:text property="desSolicitante" styleClass="cajaTexto" styleId="desSolicitante" size="76" maxlength="76" value="${Solicitud.desSolicitante}" readonly="readonly" />
			<%}else{%>
			<html:text property="desSolicitante" styleClass="cajaTexto" styleId="desSolicitante" size="76" maxlength="76" value='${solicitudForm.desSolicitante}' readonly="readonly"/>
			<%}%>
       </td>   
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText">Oficina Principal *</font>&nbsp;
       		
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
       		<html:text property="codOficina" styleClass="cajaTexto" styleId="codOficina" size="10" maxlength="10" value='${Solicitud.codOficina}' readonly="readonly" />
       		<html:text property="desOficina" styleClass="cajaTexto" styleId="desOficina" size="40" maxlength="20" value='${Solicitud.desOficina}' readonly="readonly" />&nbsp;&nbsp;
			<%}else{%>
			<html:text property="codOficina" styleClass="cajaTexto" styleId="codOficina" size="10" maxlength="10" value='${solicitudForm.codOficina}' readonly="readonly"/>
       		<html:text property="desOficina" styleClass="cajaTexto" styleId="desOficina" size="40" maxlength="20" value='${solicitudForm.desOficina}' readonly="readonly"/>&nbsp;&nbsp;
		
		    <%}%>
	   </td>
	   <td align="right" valign="middle">
			<font class="fontText">Gestor *</font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<html:text property="gestorCod" styleClass="cajaTexto" styleId="gestorCod" size="10" maxlength="10" value='${Solicitud.gestorCod}' readonly="readonly" />
       		<html:text property="gestorNom" styleClass="cajaTexto" styleId="gestorNom" size="40" maxlength="20" value='${Solicitud.gestorNom}' readonly="readonly" />
			<%}else{%>
			<html:text property="gestorCod" styleClass="cajaTexto" styleId="gestorCod" size="10" maxlength="10" value='${solicitudForm.gestorCod}' readonly="readonly"/>
       		<html:text property="gestorNom" styleClass="cajaTexto" styleId="gestorNom" size="40" maxlength="20" value='${solicitudForm.gestorNom}' readonly="readonly"/>
			<%}%>
       </td>
	</tr>
	<tr>
	   <td align="left">&nbsp;</td>
       <td align="right" valign="middle">
			<font class="fontText">Empleador *</font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<html:text property="empleadorCod" styleClass="cajaTexto" styleId="empleadorCod" size="14" maxlength="10" value='${Solicitud.empleadorCod}' readonly="readonly" />
       		<html:text property="empleadorNom" styleClass="cajaTexto" styleId="empleadorNom" size="40" maxlength="20" value='${Solicitud.empleadorNom}' readonly="readonly" />
			<%}else{%>
			<html:text property="empleadorCod" styleClass="cajaTexto" styleId="empleadorCod" size="14" maxlength="10" value='${solicitudForm.empleadorCod}' readonly="readonly"/>
       		<html:text property="empleadorNom" styleClass="cajaTexto" styleId="empleadorNom" size="40" maxlength="20" value='${solicitudForm.empleadorNom}' readonly="readonly"/>
			<%}%>
       </td>
	</tr>
	</table>
	</div>
	
	<fieldset style="width: 860px; margin-top:10px;">
   	<legend>
   	<font class="txt-titulo">
   	Datos de la Oficina y Ejecutivo
   	</font></legend>
	<table>	
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText">Ejecutivo de Cuenta *</font>
       </td>
       <td align="left" valign="middle">
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="ejecutivoCtaCod" class="cajaTexto" id="ejecutivoCtaCod" size="10" maxlength="10" value="${Solicitud.ejecutivoCtaCod}"  readonly="readonly">
			<input type="text" name="ejecutivoCtaNom" class="cajaTexto" id="ejecutivoCtaNom" size="40" maxlength="20" value="${Solicitud.ejecutivoCtaNom}"  readonly="readonly">
			<%}else{%>
			<input type="text" name="ejecutivoCtaCod" class="cajaTexto" id="ejecutivoCtaCod" size="10" maxlength="10" value='${solicitudForm.ejecutivoCtaCod}' readonly="readonly">
			<input type="text" name="ejecutivoCtaNom" class="cajaTexto" id="ejecutivoCtaNom" size="40" maxlength="20" value='${solicitudForm.ejecutivoCtaNom}' readonly="readonly">
			<%}%>
			
	   </td>
	   <td align="right" valign="middle">
			<font class="fontText">Fecha de Ingreso Oficina *</font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="strFechaIngreso" class="cajaTexto" id="strFechaIngreso" size="20" maxlength="20" value="${Solicitud.strFechaIngreso}" readonly="readonly">
			<%}else{%>
			<input type="text" name="strFechaIngreso" class="cajaTexto" id="strFechaIngreso" size="30"  maxlength="30" value='${solicitudForm.strFechaIngreso}' readonly="readonly">
			<%}%>
			
       </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText">Oficina de Alta *</font>
       </td>
       <td align="left" valign="middle">
       
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="oficinaAltaCod" class="cajaTexto" id="oficinaAltaCod" size="10" maxlength="10" value='${Solicitud.oficinaAltaCod}' readonly="readonly">
			<input type="text" name="oficinaAltaNom" class="cajaTexto" id="oficinaAltaNom" size="40" maxlength="20" value='${Solicitud.oficinaAltaNom}' readonly="readonly">&nbsp;&nbsp;
			<%}else{%>
			<input type="text" name="oficinaAltaCod" class="cajaTexto" id="oficinaAltaCod" size="10" maxlength="10" value='${solicitudForm.oficinaAltaCod}' readonly="readonly">
			<input type="text" name="oficinaAltaNom" class="cajaTexto" id="oficinaAltaNom" size="40" maxlength="100" value='${solicitudForm.oficinaAltaNom}' readonly="readonly">&nbsp;&nbsp;
			<%}%>
			
	   </td>
	   <td align="right" valign="middle">&nbsp;</td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText">Gerencia Territorial *</font>
       </td>
       <td align="left" valign="middle">
       
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="gerenciaTerritorialCod" class="cajaTexto" id="gerenciaTerritorialCod" size="10" maxlength="10" value='${Solicitud.gerenciaTerritorialCod}' readonly="readonly">
			<input type="text" name="gerenciaTerritorialNom" class="cajaTexto" id="gerenciaTerritorialNom" size="40" maxlength="20" value='${Solicitud.gerenciaTerritorialNom}' readonly="readonly">&nbsp;&nbsp;
			<%}else{%>
			<input type="text" name="gerenciaTerritorialCod" class="cajaTexto" id=gerenciaTerritorialCod size="10" maxlength="10" value='${solicitudForm.gerenciaTerritorialCod}' readonly="readonly">
			<input type="text" name="gerenciaTerritorialNom" class="cajaTexto" id="gerenciaTerritorialNom" size="40" maxlength="60" value='${solicitudForm.gerenciaTerritorialNom}' readonly="readonly">&nbsp;&nbsp;
			<%}%>
			
	   </td>
	   <td align="right" valign="middle">&nbsp;</td>
	</tr>
	</table>
	</fieldset>
	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
	<fieldset style="width:1260px; margin-top:10px;">
   	<legend>
   	<font class="txt-titulo">
   	Datos del Producto
   	</font></legend>
		<table id="listProductsDetalle" class="grid" width="1300px;">
		</table></fieldset>
	<%}else{%>
	<fieldset style="width:1260px; margin-top:10px;">
   	<legend>
   	<font class="txt-titulo">
   	Datos del Producto
   	</font></legend>
	<table style="width: 1260px;">	
	<tr>
       <td align="left" valign="middle">
       <font class="fontText">Banca</font>&nbsp;
       <html:select property="codBanca" styleId="codBanca" onchange="changeBankListProducts(this);">
				<html:option value="-1" >TODOS</html:option>
				<c:if test="${lstBancas != null}">
					<c:forEach var="banca" items="${lstBancas}">
						<html:option value="${banca.codBanca}">
							<c:out value="${banca.nombre}" />
						</html:option>
					</c:forEach>
				</c:if>
			</html:select>
	   </td>
	   <td align="center" valign="middle">
			<font class="fontText">Moneda</font>&nbsp;       
	   		<html:select property="codMultMoneda" styleId="codMultMoneda">
				<html:option value="-1" >TODOS</html:option>
				<c:if test="${lstMonedas != null}">
					<c:forEach var="moneda" items="${lstMonedas}">
						<html:option value="${moneda.codElemento}">
							<c:out value="${moneda.strValor}" />
						</html:option>
					</c:forEach>
				</c:if>
			</html:select>
       </td>
       <td align="right" valign="middle">
			<font class="fontText">Monto total</font>&nbsp;
	   		<input type="text"  name="mtoTotal" class="cajaTexto" id="mtoTotal" size="20" maxlength="20" value='${solicitudForm.mtoTotal}' readonly="readonly" style="text-align: right;">
	   		
       </td>
	</tr>
	</table>

	<a href="javascript:deleteProducto();" class="buttonGPR">ELIMINAR</a>&nbsp;
	<a href="javascript:addProducto();" class="buttonGPR">AGREGAR</a>
	
	<br/><br/>
	<table id="listProducts" class="grid" width="1300px;">
	</table>
	</fieldset>
		<%}%>
	<fieldset style="width: 1200px; margin-top: 10px;" >
   	<legend>
   	<font class="txt-titulo">
   	Datos de Riesgo del Cliente
   	</font></legend>
   	
   	<table>
   	<tr>
   	
   	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font class="fontText">Rating*</font>
        </td>
        <td valign="middle">
        	
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="rating" class="cajaTexto" id="rating" size="19" maxlength="19"  value='${Solicitud.rating}' readonly="readonly">
			<%}else{%>
			<input type="text" name="rating" class="cajaTexto" id="rating" size="19" maxlength="19" value='${solicitudForm.rating}' readonly="readonly">
			<%}%>        
			
        </td>
    </tr>
    <tr>
		<td>
        	<font class="fontText">Scorating*</font>
        </td>
        <td valign="middle">
        	
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="scorating" class="cajaTexto" id="scorating" size="19" maxlength="19" value='${Solicitud.scorating}' readonly="readonly">
			<%}else{%>
			<input type="text" name="scorating" class="cajaTexto" id="scorating" size="19" maxlength="19" value='${solicitudForm.scorating}' style="background-color: #F2F5A9">
			<%}%>        
			
        </td>
    </tr>
    <tr>
       <td valign="middle">
       		<font class="fontText">Clasificación del Cliente *</font>
       </td>
       <td valign="middle">
       		
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="clasificacion" class="cajaTexto" id="clasificacion" size="19" maxlength="19" value='${Solicitud.clasificacion}' readonly="readonly" >
			<%}else{%>
			<input type="text" name="clasificacion" class="cajaTexto" id="clasificacion" size="19" maxlength="19" value='${solicitudForm.clasificacion}' readonly="readonly" >
			<%}%>
			
       </td>
	</tr>
	</table>
	</td>
	
	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font class="fontText">Relevancia Pública *</font><br/>
        	
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="relevPublica1" class="cajaTexto" id="relevPublica1" size="60" maxlength="19" value='${Solicitud.relevPublica1}' readonly="readonly"><br/>
			<input type="text" name="relevPublica2" class="cajaTexto" id="relevPublica2" size="60" maxlength="19" value='${Solicitud.relevPublica2}' readonly="readonly"><br/>
			<input type="text" name="relevPublica3" class="cajaTexto" id="relevPublica3" size="60" maxlength="19" value='${Solicitud.relevPublica3}' readonly="readonly"><br/>
			<input type="text" name="relevPublica4" class="cajaTexto" id="relevPublica4" size="60" maxlength="19" value='${Solicitud.relevPublica4}' readonly="readonly"><br/>
			<input type="text" name="relevPublica5" class="cajaTexto" id="relevPublica5" size="60" maxlength="19" value='${Solicitud.relevPublica5}' readonly="readonly">
			<%}else{%>
			<input type="text" name="relevPublica1" class="cajaTexto" id="relevPublica1" size="60" maxlength="100"  value='${solicitudForm.relevPublica1}' readonly="readonly"><br/>
			<input type="text" name="relevPublica2" class="cajaTexto" id="relevPublica2" size="60" maxlength="100"  value='${solicitudForm.relevPublica2}' readonly="readonly"><br/>
			<input type="text" name="relevPublica3" class="cajaTexto" id="relevPublica3" size="60" maxlength="100"  value='${solicitudForm.relevPublica3}' readonly="readonly"><br/>
			<input type="text" name="relevPublica4" class="cajaTexto" id="relevPublica4" size="60" maxlength="100"  value='${solicitudForm.relevPublica4}' readonly="readonly"><br/>
			<input type="text" name="relevPublica5" class="cajaTexto" id="relevPublica5" size="60" maxlength="100"  value='${solicitudForm.relevPublica5}' readonly="readonly">
			<%}%>
			
       </td>
    </tr>
	</table>
	</td>
	
	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font class="fontText">Deuda Directa *</font>
        </td>
        <td valign="middle">
        
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="deudaDirecta" class="cajaTexto" id="deudaDirecta" size="19" maxlength="19" value='${Solicitud.deudaDirecta}' readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="deudaDirecta" class="cajaTexto" id="deudaDirecta" size="19" maxlength="19" value='${solicitudForm.deudaDirecta}' readonly="readonly" style="text-align: right;">
			<%}%>
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Deuda Indirecta *</font>
		</td>
		<td valign="middle">
		
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="deudaIndirecta" class="cajaTexto" id="deudaIndirecta" size="19" maxlength="19" value='${Solicitud.deudaIndirecta}' readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="deudaIndirecta" class="cajaTexto" id="deudaIndirecta" size="19" maxlength="19" value='${solicitudForm.deudaIndirecta}' readonly="readonly" style="text-align: right;">
			<%}%>
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Deuda Castigo *</font>
		</td>
		<td valign="middle">
		
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="castigo" class="cajaTexto" id="castigo" size="19" maxlength="19" value='${Solicitud.castigo}' readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="castigo" class="cajaTexto" id="castigo" size="19" maxlength="19" value='${solicitudForm.castigo}' readonly="readonly" style="text-align: right;">
			<%}%>
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Deuda en el Sistema Financiero *</font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="deudaSistemaFinanciero" class="cajaTexto" id="deudaSistemaFinanciero" size="19" maxlength="19" value='${Solicitud.deudaSistemaFinanciero}' readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="deudaSistemaFinanciero" class="cajaTexto" id="deudaSistemaFinanciero" size="19" maxlength="19" value='${solicitudForm.deudaSistemaFinanciero}' readonly="readonly" style="text-align: right;">
			<%}%>
			
       </td>
    </tr>
	</table>
	</td>
	
	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font class="fontText">Otros Riesgos</font>
        </td>
        <td valign="middle">
        
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="otroRiesgo" class="cajaTexto" id="otroRiesgo" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${Solicitud.otroRiesgo}' readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="otroRiesgo" class="cajaTexto" id="otroRiesgo" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${solicitudForm.otroRiesgo}' readonly="readonly" style="text-align: right;">
			<%}%>        
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Riesgo Grupal</font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="riesgoGrupal" class="cajaTexto" id="riesgoGrupal" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${Solicitud.riesgoGrupal}'  readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="riesgoGrupal" class="cajaTexto"  id="riesgoGrupal" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${solicitudForm.riesgoGrupal}' onchange="getMonto(this.value, 3);" style="background: #F2F5A9;text-align: right;">
			<%}%>
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Riesgo Actual</font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="riesgoActual" class="cajaTexto" id="riesgoActual" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${Solicitud.riesgoActual}' readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="riesgoActual" class="cajaTexto" id="riesgoActual" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${solicitudForm.riesgoActual}' style="text-align: right;">
			<%}%>
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Riesgo Total</font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="riesgoTotal" class="cajaTexto" id="riesgoTotal" size="19" maxlength="19"   value='${Solicitud.riesgoTotal}'  readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="riesgoTotal" class="cajaTexto" id="riesgoTotal" size="19" maxlength="19"  value='${solicitudForm.riesgoTotal}'  onkeypress="ingresoNumeros(event);" readonly="readonly" style="text-align: right;">
			<%}%>
       </td>
    </tr>
	</table>
	</td>
	
	</tr>
	<tr>
		<td><input type="button" class="buttonGPR"  name="btnGuardar" id="btnGuardar" onclick="guardarSolicitud();" value="Guardar Solicitud">&nbsp;
		<input type="button" class="buttonGPR"  name="btnReset" id="btnReset" onclick='limpiaForm($("#formSolicitudIngreso"));' value="Limpiar Solicitud">&nbsp;</td>
	
	</tr>
	</table>
	</fieldset>
</html:form>

</body>
</html>