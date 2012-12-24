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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/solicitud/ingresoSolicitud.js"></script>
	
<script type="text/javascript">
//frk: para el caso de IExplorer en el que no 
//funciona bien el "getElementsByName" 

if (jQuery.browser.msie){
	document.getElementsByName = function(name, tag){
	    return getElementsByName_iefix(name, tag);
	};
}

optionDialog = {
	width: 420,
	autoOpen: false,
    modal: true,
    buttons: {
        "Aceptar": function() {
        	document.getElementById('hdnStrMensaje').value=$('#strMensaje').val();
        	$(this).dialog("close");
        },
        "Cancelar": function() {
        	$(this).dialog("close");
        }
    },
    close: function() {
    }
}; 
	
$(function() {
	$("#tabsPrincipal").tabs();
    $("#fechaIngreso").datepicker({dateFormat: 'dd/mm/yy'});
    $("#dialog-form").dialog(optionDialog);
});

function ocultarElementByID(id,tiempo){
	setTimeout("document.getElementById('"+id+"')!=null?document.getElementById('"+id+"').style.display='none':document.getElementById('"+id+"');", tiempo);
}
	ocultarElementByID('divError',4000);
	ocultarElementByID('divAlerta',10000);
	ocultarElementByID('divExito',10000);

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
var myColNames  = ['','','','','', 'Descripción Producto','','Garantía','Contrato Vinculado', 'Scoring',  'Cod. Pre Evaluador', 'Campaña', 'Tipo', 'Mto Solicitado', 'Plazo (Meses)', 'Mto Garantizado', ''];
var myDataModel = [
                   { name : 'codProducto',			index : 'codProducto', 			editable:true,	editrules: {edithidden:true, required:true}, hidden:true},
                   { name : 'indice',				index : 'indice', 				editable:true,  hidden:true},
                   { name : 'valBanca',				index : 'valBanca', 			width : 140, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                   { name : 'valMoneda',			index : 'valMoneda', 			width : 140, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                   { name : 'valMontoTotal',		index : 'valMontoTotal', 		width : 140, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                   { name : 'desProducto',			index : 'desProducto', 			width : 210, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: desProductoElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: desProductoFormat, unformat: genericUnFormat},                 
                   { name : 'codProdBase',			index : 'codProdBase', 			editable:true,	hidden:true},
                   { name : 'desGarantia',			index : 'desGarantia', 			width : 200,  	editable:true,	edittype:'text', 	editoptions: {size:25, maxlength: 40, readonly: 'readonly'}, align : 'center'},
                   { name : 'contratoVinculado',	index : 'contratoVinculado',	width : 210,	editable:true,	edittype:'custom',  editoptions: {size:18, custom_element: contratoVincElementCustom, custom_value: genericComboValueCustom}, editrules:   {required: true}, align : 'center', formatter: contratoVincFormat, unformat: genericUnFormat},
                   { name : 'scoring',				index : 'scoring', 				width : 90,  	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 40, readonly: 'readonly'}, align : 'center'},
                   { name : 'codPreEvaluador',		index : 'codPreEvaluador', 		width : 140, 	editable:true,	edittype:'text', 	editoptions: {style: 'background-color: #F2F5A9', size:15, maxlength: 20, dataEvents: [{ type: 'blur',     fn: function (){ blurChangeColor(this);}}, 
                	   																																																		   { type: 'change',   fn: function (){ changeCodPreEvaludador(this.value);}},
                   																																																			   { type: 'keyup',   fn: function (){ changeCodPreEvaludador(this.value);}}]}, align : 'center'},
                   { name : 'desCampania',			index : 'desCampania', 			width : 180, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: campaniaElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: campaniaFormat, unformat: genericUnFormat},
                   { name : 'desTipo',				index : 'desTipo', 				width : 180, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: tipoElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: tipoFormat, unformat: genericUnFormat},
                   { name : 'mtoProducto',			index : 'mtoProducto', 			width : 140, 	editable:true,	edittype:'text', 	editoptions: {size:14, maxlength: 18, style: 'text-align: right; background-color: #F2F5A9',  dataEvents: [ { type: 'change',   fn: function (){ getMonto(this.value, 1);}}, 
                                          			                       			            				              	                 	                                                                                                           { type: 'keypress', fn: function (){ ingresoNumeros(event);}}, 	
                                          			                       			            				              	                 	                                                                                                  		   { type: 'blur',     fn: function (){ blurChangeColor(this);}}
                                          			                       			            				              	                 	                                                                                                           ]},   editrules: {required: true, minValue: 1}, align : 'right'},
                   { name : 'plazo',			    index : 'plazo', 				width : 90, 	editable:true,	edittype:'text', 	editoptions: {size:9, maxlength: 15, style: 'text-align: right; background-color: #F2F5A9', dataEvents: [ { type: 'keypress', fn: function (){ ingresoNumeros(event);}},
                                    			                     				            				              	                 	                                                                                                           { type: 'blur',     fn: function (){ blurChangeColor(this);}}
                                    			                     				            				              	                 	                                                                                                       	   ]}, editrules: {required: true, integer: true, minValue: 1, maxValue: 90}, align : 'right'},
                   { name : 'mtoGarantia',			index : 'mtoGarantia', 			width : 140, 	editable:true,	edittype:'text', 	editoptions: {style: 'text-align: right; text-align: right; background-color: #F2F5A9', size:14, maxlength: 19, dataEvents: [{ type: 'change', fn: function (){ getMonto(this.value, 2);}},
                                          			                       			             				              	                 	                                                                                                                             { type: 'keypress', fn: function (){ ingresoNumeros(event);}},
                                          			                       			             				              	                 	                                                                                                                    		 { type: 'blur',     fn: function (){ blurChangeColor(this);}}]}, align : 'right'},
                   { name : 'mtoTotalRow',			index : 'mtoTotalRow', 			width : 90, 	hidden:true, editable:true,	edittype:'text', 	editoptions: {style: 'text-align: right; text-align: right', size:14, maxlength: 19, readonly: 'readonly'},align : 'right'}
                             
                   ];
var myColSolicituDetalle  = [ 'Descripción Producto', 'Producto Base','Contrato Vinculado', 'Scoring',  'Cod. Pre Evaluador', 'Campaña', 'Tipo', 'Monto Solicitado', 'Plazo (Meses)', 'Monto Garantizado', 'Total'];

var myDataModelSolicitudDetalle = [ {name : 'desProducto',      	index : 'desProducto',       	width : 200      ,sortable:false},
                                    {name : 'desProdBase',      	index : 'desProdBase',       	width : 120      ,sortable:false},
                                    {name : 'contratoVinculado',  	index : 'contratoVinculado',  	width : 150      ,sortable:false},
                                    {name : 'scoring',        		index : 'scoring',         		width : 70       ,sortable:false},
                                    {name : 'codPreEvaluador',    	index : 'codPreEvaluador',      width : 100      ,sortable:false},
                                    {name : 'desCampania',      	index : 'desCampania',       	width : 120      ,sortable:false},
                                    {name : 'desTipo',        		index : 'desTipo',         		width : 80       ,sortable:false},
                                    {name : 'mtoProducto',      	index : 'mtoProducto',       	width : 100      ,sortable:false, editoptions: {style: 'text-align: right'}},
                                    {name : 'plazo',          		index : 'plazo',         		width : 80       ,sortable:false},
                                    {name : 'mtoGarantia',      	index : 'mtoGarantia',       	width : 100      ,sortable:false, editoptions: {style: 'text-align: right'}},
                                    {name : 'mtoTotalRow',      	index : 'mtoTotalRow',       	width : 60       ,sortable:false, editoptions: {style: 'text-align: right'}}
                        ];

function changeDesGarantiaByProducto(param){
	IngresoSolicitudAction.getGarantiaByProductoAjax(param.value, function(msg){
		document.getElementsByName("desGarantia")[0].value = msg;		
	 });
}
function setEventsValidationProducto(){
    jQuery("select[name=desProducto]").bind("change",function (evnt){
    	getProductoBaseElementText(this);
    	document.getElementsByName("scoring")[0].value = '';
    	changeContratoByProduct(this);    
    	changeDesGarantiaByProducto(this);
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
		  //var valReal = valElement.split("<input");
		  var el = document.createElement("input");
		  el.type="text";
		  el.value = '';
		  return el;
	  }
}

function getProductoBaseElementText(obj){
	var codProducto = obj.value;
	 IngresoSolicitudAction.getProductoBaseAjax(codProducto,  function(msg){
		if(msg==0){
			document.getElementsByName("codProdBase")[0].value = "";
		}else{
			document.getElementsByName("codProdBase")[0].value = msg;	
		}
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
function mtoMayorCero(objeto){
	var numero=parseFloat(objeto.value);
	 if(objeto.value != ''){
		 if(isNaN(numero)){
		    alert('Ingrese número correcto');
		    return false;
		  }else{
		    if(numero == 0){
	    		 alert('Ingrese valor superior a Cero.');
	    		 objeto.focus();
	    		 return false;
		    }
		  }
	 }
}

function formato_numero(numero, decimales, separador_decimal, separador_miles){ // v2007-08-06
	 numero=parseFloat(numero);
    if(isNaN(numero)){
        return "";
    }
    if(decimales!==undefined){
        // Redondeamos
        numero=numero.toFixed(decimales);
    }
    // Convertimos el punto en separador_decimal
    numero=numero.toString().replace(".", separador_decimal!==undefined ? separador_decimal : ",");
    if(separador_miles){
        // Añadimos los separadores de miles
        var miles=new RegExp("(-?[0-9]+)([0-9]{3})");
        while(miles.test(numero)) {
            numero=numero.replace(miles, "$1" + separador_miles + "$2");
        }
    }
   return numero;
}
function getMonto(value, call){
	var pdeudaDirecta = "00.00";  
		if(document.getElementsByName('deudaDirecta').length>0)
		{pdeudaDirecta = document.getElementsByName('deudaDirecta')[0].value;}
	
	var pdeudaIndirecta = "00.00";
		if(document.getElementsByName('deudaIndirecta').length>0)
		{pdeudaIndirecta=document.getElementsByName('deudaIndirecta')[0].value;}
	
	var pdeudaCastigo = "00.00";
		if( document.getElementsByName('castigo').length>0)
		{pdeudaCastigo = document.getElementsByName('castigo')[0].value;}
	
	var pdeudaSisFinanciero = "00.00";
		if(document.getElementsByName('deudaSistemaFinanciero').length>0)
		{pdeudaSisFinanciero = document.getElementsByName('deudaSistemaFinanciero')[0].value;}
	
	var pRiesgoGrupal = "00.00";
		if(document.getElementsByName('riesgoGrupal').length>0)
		{ pRiesgoGrupal = document.getElementsByName('riesgoGrupal')[0].value;}
	
	var pMtoGarantia = "00.00";
		if(document.getElementsByName('mtoGarantia').length>0)
		{pMtoGarantia = document.getElementsByName('mtoGarantia')[0].value;}
	
	var pMtoProducto = "00.00";
		if(document.getElementsByName('mtoProducto').length>0)
		{pMtoProducto = document.getElementsByName('mtoProducto')[0].value;}
	
	var pOtroRiesgo = "00.00";
		if(document.getElementsByName('otroRiesgo').length>0){
		pOtroRiesgo = document.getElementsByName('otroRiesgo')[0].value;
	}
	
	var pMtoTotalProd = "00.00";
		if(document.getElementsByName('mtoTotal').length>0)
		{pMtoTotalProd = document.getElementsByName('mtoTotal')[0].value;}

	if(call==1){
		IngresoSolicitudAction.changeMtoTotalRowAjax(value, pMtoGarantia, function(msg){
			document.getElementsByName("mtoTotalRow")[0].value = formato_numero(msg, 2, '.', ',');
			pMtoTotalProd=msg;
			IngresoSolicitudAction.changeMtoTotalAjax(value, function(msg){
				document.getElementsByName("mtoTotal")[0].value = formato_numero(msg, 2, '.', ',');
			});
			IngresoSolicitudAction.changeRiesgoTotalAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, pRiesgoGrupal, pMtoTotalProd, function(msg){
				document.getElementsByName("riesgoTotal")[0].value = formato_numero(msg, 2, '.', ',');
			});		
		});
		//document.getElementsByName("mtoProducto")[0].value = formato_numero(pMtoProducto, 2, '.', ',');
		
	}if(call==2){
		IngresoSolicitudAction.changeMtoTotalRowAjax(value, pMtoProducto, function(msg){
			document.getElementsByName("mtoTotalRow")[0].value = formato_numero(msg, 2, '.', ',');
		});
		IngresoSolicitudAction.changeOtroRiesgoAjax(value, function(msg){
			document.getElementsByName("otroRiesgo")[0].value = formato_numero(msg, 2, '.', ',');
			pOtroRiesgo=msg;
			IngresoSolicitudAction.changeRiesgoActualAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, pRiesgoGrupal,  function(msg){
				document.getElementsByName("riesgoActual")[0].value = formato_numero(msg, 2, '.', ',');
			});	
			IngresoSolicitudAction.changeRiesgoTotalAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, pRiesgoGrupal, pMtoTotalProd, function(msg){
				document.getElementsByName("riesgoTotal")[0].value = formato_numero(msg, 2, '.', ',');
			});	
		});
		
	}if(call==3){
			IngresoSolicitudAction.changeRiesgoActualAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, value,  function(msg){
				document.getElementsByName("riesgoActual")[0].value = formato_numero(msg, 2, '.', ',');
		});	
		IngresoSolicitudAction.changeRiesgoTotalAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, value, pMtoTotalProd, function(msg){
			document.getElementsByName("riesgoTotal")[0].value = formato_numero(msg, 2, '.', ',');
		});	
	}if(call==4){
		
	}

}

function mostrarTabla(data){

	var idTableForm = 'listProducts';
	$('body').append('<div id="paginador_'+idTableForm+'" class="grid"></div>'); 
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
		height   	: 120,
		weight 	 	: 1000,
		colNames 	: myColNames,
		colModel 	: myDataModel,
		rowList 	: [5,10,15,20,25],
		width 		: 1260,
		rowNum 		: 15, 
		scrollOffset: 0,
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
	var codSubBanca =  document.getElementById("subBanca");

	if(codBanca.value == '-1'){
		alert("Debe seleccionar una banca para poder agregar un producto.");		
	}else{
		if(codSubBanca.value == '-1' || codSubBanca.value == 'null'){
			alert("Debe seleccionar una subBanca para poder agregar un producto.");			
		}else{
			if(codMultMoneda.value == '-1'){
				alert("Debe seleccionar una moneda para poder agregar un producto.");				
			}else{
				IngresoSolicitudAction.setIndice(function(msg){
					document.getElementsByName("indice")[0].value  = msg;
				});
				var rowid = "-1";
				var mydataadd = 
					  [{
						
						codProducto			: rowid,
		                desProducto 		: "",
		                codProdBase			: "",
		                contratoVinculado 	: "-1",
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
	}
}

function deleteProducto(){
	var selecciones = buscarSelecciones("listProducts");

	var pOtroRiesgo = "00.00";
	var pMtoTotalProd = "00.00";
		
	if (selecciones.length == 0){
		alert('No ha seleccionado ningún elemento para la eliminación.');
	}else{
		
		var ans = confirm('¿Esta seguro que desea eliminar los registros seleccionados?');
		if(ans){
			selecciones= selecciones.split('**1-')+'';
			IngresoSolicitudAction.eliminarProductoAjax(selecciones, function(msg){
				
				var paramDelete = '';
				if(msg != ''){
					paramDelete = 'logicDelete';
				}
				
				consultarProductos(paramDelete, msg);
				IngresoSolicitudAction.changeMtoTotalAjax(pMtoTotalProd, function(msg){
					document.getElementsByName("mtoTotal")[0].value = msg;
				});
				IngresoSolicitudAction.changeOtroRiesgoAjax(pOtroRiesgo, function(msg){
					document.getElementsByName("otroRiesgo")[0].value = msg;
				});	
			});			
		}
	}
}

function changeSubanca(obj){
	var formulario = document.getElementById('formSolicitudIngreso');
	formulario.hdnSubBanca.value = obj.value;
}
function changeBankListProducts(obj){
	var codBanca = obj.value;
	if(valBancaGeneric == ''){
		valBancaGeneric = codBanca;
	}else if(valBancaGeneric != codBanca){
		var tabla = document.getElementById("listProducts");
	  	var tablaBody = tabla.getElementsByTagName("tbody")[0];
	  	var filas = tablaBody.getElementsByTagName("tr");
	  	if(filas.length > 1){
	  		var ans = confirm('Se cambio la banca seleccionada, se eliminarán los registros agregados. ¿Esta seguro que desea continuar?');
			if(ans){
				valBancaGeneric = obj.value;
				jQuery("#listProducts").GridUnload();
				IngresoSolicitudAction.removerListaAjax(function(data){
					mostrarTabla(data);
				});
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
		formulario.action = rutaContexto+'/ingresoSolicitud.do?method=insertSolicitud';
		formulario.submit();
		consultarProductos('','');
	
}

function backAsignacionPrioridad(){
	window.open('asignacion_prioridad.jsp','_self');
}


function init(){
	var formulario = document.getElementById('formSolicitudIngreso');
	formulario.codCentral.value=formulario.hdnCodCentral.value;
	consultarProductos('','');
	mostrarPopUp();
	consultarProductos();
	if(formulario.mantener.value!=''){
		consultarDetalle();
	}
	setSubBanca();

		if(document.getElementsByName('deudaDirecta').length>0)
		{document.getElementsByName('deudaDirecta')[0].value = formato_numero(document.getElementsByName('deudaDirecta')[0].value, 2, '.', ',');}
	
		if(document.getElementsByName('deudaIndirecta').length>0)
		{document.getElementsByName('deudaIndirecta')[0].value = formato_numero(document.getElementsByName('deudaIndirecta')[0].value, 2, '.', ',');}
	
		if( document.getElementsByName('castigo').length>0)
		{document.getElementsByName('castigo')[0].value = formato_numero(document.getElementsByName('deudaDirecta')[0].value, 2, '.', ',');}
	
		if(document.getElementsByName('deudaSistemaFinanciero').length>0)
		{document.getElementsByName('deudaSistemaFinanciero')[0].value = formato_numero(document.getElementsByName('deudaSistemaFinanciero')[0].value, 2, '.', ',');}
	
		if(document.getElementsByName('riesgoGrupal').length>0)
		{document.getElementsByName('riesgoGrupal')[0].value = formato_numero(document.getElementsByName('riesgoGrupal')[0].value, 2, '.', ',');}
	
		if(document.getElementsByName('otroRiesgo').length>0){
		pOtroRiesgo = document.getElementsByName('otroRiesgo')[0].value = formato_numero(document.getElementsByName('otroRiesgo')[0].value, 2, '.', ',');}
	
		if(document.getElementsByName('riesgoTotal').length>0)
		{document.getElementsByName('riesgoTotal')[0].value = formato_numero(document.getElementsByName('riesgoTotal')[0].value, 2, '.', ',');}
		
		if(document.getElementsByName('riesgoActual').length>0)
		{document.getElementsByName('riesgoActual')[0].value = formato_numero(document.getElementsByName('riesgoActual')[0].value, 2, '.', ',');}

}
function mostrarPopUp(){
	var formulario = document.getElementById('formSolicitudIngreso');
	if(formulario.flagPopUP.value=='mostrarContinuar'){
		formulario.condicion.value = '1';
		$("#mensaje_cliente").html($("#strMensajePopUP").val());
		$("#valCondicionCliente").dialog("open");		
	}if(formulario.flagPopUP.value=='envioRiesgos'){
		formulario.condicion.value = '2';
		$("#valMontoPlazos").dialog("open");
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
	  $(miForm).find(':input').each(function() {
          switch(this.type) {
              case 'password':
              case 'select-multiple':
              case 'select-one':
              case 'text':
              case 'textarea':
                  $(this).val('');
                  break;
              case 'checkbox':
              case 'radio':
                  this.checked = false;
          }
      });
	  jQuery("#listProducts").GridUnload();
	  IngresoSolicitudAction.removerListaAjax(function(data){
			mostrarTabla(data);
		});
		 dwr.util.removeAllOptions("codSubBanca");
		 dwr.util.removeAllOptions("codBanca");
		 dwr.util.removeAllOptions("codMultMoneda");
	  
}
function update(parametro){
	var formulario = document.getElementById('formSolicitudIngreso');
	var nroSolicitud = formulario.mantener.value;
		if(parametro == 'priorizar'){
			var var_prioridad =formulario.prioridad.value;
			jQuery("#listProductsDetalle").GridUnload();
			SolicitudMantenimientoAction.priorizarSolicitud(nroSolicitud,var_prioridad,  function(msg){
				consultarDetalle();
				alert(msg);
			});
		}else{
			var flag = formulario.chkAnular.checked;
			if(flag==true){
			if(confirm("¿Seguro que desea anular la solicitud?")){
				jQuery("#listProductsDetalle").GridUnload();
				SolicitudMantenimientoAction.anularSolicitud(nroSolicitud, function(msg){
					consultarDetalle();
					formulario.btnAnular.disabled = true;
					alert(msg);
					
				});
			}
			}else{
				alert('Debe seleccionar check antes de Anular.');
			}
		
		}
}

function changeBancSubBanca(codBanca, codSubBanca){	
	var formulario = document.getElementById('formSolicitudIngreso');
	formulario.hdnBanca.value = codBanca;
	 IngresoSolicitudAction.getLstSubBanca(codBanca, function(data) {
	
		 dwr.util.removeAllOptions("subBanca");
		 dwr.util.addOptions("subBanca", data,'codSubanca','descripcion');
		 if(codSubBanca!=null) {
			 setValueComboBox(codSubBanca);
		 }
	});
}
function setValueComboBox(value){
	 $("#subBanca").val(value);
}
function setSubBanca(){
	var formulario = document.getElementById('formSolicitudIngreso');
	if(formulario.hdnBanca.value!='-1' && formulario.hdnBanca.value!=''){
		var banca = formulario.hdnBanca.value;
		var subBanca = formulario.hdnSubBanca.value;
		changeBancSubBanca(banca, subBanca);
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
	<input type="hidden" id="hdnSubBanca" name="hdnSubBanca" value='${solicitudForm.hdnSubBanca}'></input>
	<input type="hidden" id="hdnBanca" name="hdnBanca" value='${solicitudForm.hdnBanca}'></input>
	<input type="hidden" id="hdnStrMensaje" name="hdnStrMensaje" value='${solicitudForm.hdnStrMensaje}'></input>
	<input type="hidden" id="reelevancia" name="reelevancia" value='${solicitudForm.reelevancia}'></input>
	<input type="hidden" id="condicion" name="condicion" value=''></input>
	<input type="hidden" id="referencia" name="referencia" value=''></input>	
	
	<%if(asigPrioridadIndividual != null){%>
	<br/>
	<input type="button" class="buttonGPR"  name="btnRegresar" id="btnRegresar" onclick="backAsignacionPrioridad();" value="Regresar">&nbsp;
	<input type="button" class="buttonGPR"  name="btnPriorizar" id="btnPriorizar"   value="Asignar Prioridad" onclick="update('priorizar');">
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
			<input id="codCentral" class="cajaTexto" name="codCentral" size="10" maxlength="8" readonly="readonly" value="${Solicitud.codCentral}"><html:errors property="codCentral"/>
			<%}else{%>
			<input id="codCentral" class="cajaTexto" name="codCentral" size="10" maxlength="8" value="" onkeypress="ingresoNumeros(event);" value='${solicitudForm.codCentral}' style="background-color: #F2F5A9" onblur="blurChangeColor(this);">
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
						<logic:equal name="Solicitud" property="prioridad" value="1"><option selected value="1">ALTA</option></logic:equal>
						<logic:notEqual   name="Solicitud" property="prioridad" value="1"><option value="1">ALTA</option></logic:notEqual>
						<logic:equal   name="Solicitud" property="prioridad" value="2"><option selected value="2">NORMAL</option></logic:equal>
						<logic:notEqual   name="Solicitud" property="prioridad" value="2"><option value="2">NORMAL</option></logic:notEqual>
						<logic:equal   name="Solicitud" property="prioridad" value="3"><option selected value="3">BAJA</option></logic:equal>
						<logic:notEqual  name="Solicitud" property="prioridad" value="3"><option value="3">BAJA</option></logic:notEqual>
				</select>
			<%}%>
			
			<%if(asigAnulacionIndividual != null){%>
				<font class="fontText">ANULAR</font>&nbsp;
				<input type="checkbox" name="chkAnular" id="chkAnular">
			<%}%>
			
		</td>
		<%}%>
	</tr>
	</table>
	<br/>
	
	<div class="ui-widget ui-widget-content ui-corner-all" style="width: 860px;margin: 3px;">
	<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
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
				<input type="text" name="desMultTipoPersona" class="cajaTexto" id="desMultTipoPersona" size="20" maxlength="60" value="${solicitudForm.desMultTipoPersona}" readonly="readonly">&nbsp;
			<%}%>			
			
			<font class="fontText">Ruc / DNI *</font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>  
				<input id="numeroDocumento" class="cajaTexto" name="numeroDocumento" size="27" maxlength="27" value="${Solicitud.numeroDocumento}" readonly="readonly" />
				<%}else{%>
				<input id="numeroDocumento" class="cajaTexto" name="numeroDocumento" size="27" maxlength="27" value="${solicitudForm.numeroDocumento}" readonly="readonly"/>
			<%}%>
			
			
       </td>
    </tr>
    <tr>
       <td colspan="2" valign="middle">
       		<font class="fontText">Razon Social / Apellidos y Nombres *</font>&nbsp;
       		
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
       		<input id="desSolicitante" class="cajaTexto" name="desSolicitante" size="76" maxlength="76" value="${Solicitud.desSolicitante}" readonly="readonly"/>
			<%}else{%>
			<input id="desSolicitante" class="cajaTexto" name="desSolicitante" size="76" maxlength="76" value="${solicitudForm.desSolicitante}" readonly="readonly"/>
			<%}%>
       </td>   
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText">Oficina Principal *</font>&nbsp;
       		
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
       		<input id="codOficina" class="cajaTexto" name="codOficina" size="10" maxlength="10" value="${Solicitud.codOficina}" readonly="readonly" />
       		<input id="desOficina" class="cajaTexto" name="desOficina" size="40" maxlength="20" value="${Solicitud.desOficina}" readonly="readonly" />&nbsp;&nbsp;
			<%}else{%>
			<input id="codOficina" class="cajaTexto" name="codOficina" size="10" maxlength="10" value="${solicitudForm.codOficina}" readonly="readonly"/>
       		<input id="desOficina" class="cajaTexto" name="desOficina" size="40" maxlength="20" value="${solicitudForm.desOficina}" readonly="readonly"/>&nbsp;&nbsp;
		
		    <%}%>
	   </td>
	   <td align="right" valign="middle">
			<font class="fontText">Gestor *</font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input id="gestorCod" class="cajaTexto" name="gestorCod" size="10" maxlength="10" value="${Solicitud.gestorCod}" readonly="readonly" />
       		<input id="gestorNom" class="cajaTexto" name="gestorNom" size="40" maxlength="20" value="${Solicitud.gestorNom}" readonly="readonly" />
			<%}else{%>
			<input id="gestorCod" class="cajaTexto" name="gestorCod" size="10" maxlength="10" value="${solicitudForm.gestorCod}" readonly="readonly"/>
       		<input id="gestorNom" class="cajaTexto" name="gestorNom" size="40" maxlength="20" value="${solicitudForm.gestorNom}" readonly="readonly"/>
			<%}%>
       </td>
	</tr>
	<tr>
	   <td align="left">&nbsp;</td>
       <td align="right" valign="middle">
			<font class="fontText">Empleador *</font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input id="empleadorCod" class="cajaTexto" name="empleadorCod" size="14" maxlength="10" value="${Solicitud.empleadorCod}" readonly="readonly" />
       		<input id="empleadorNom" class="cajaTexto" name="empleadorNom" size="40" maxlength="20" value="${Solicitud.empleadorNom}" readonly="readonly" />
			<%}else{%>
			<input id="empleadorCod" class="cajaTexto" name="empleadorCod" size="14" maxlength="10" value="${solicitudForm.empleadorCod}" readonly="readonly"/>
       		<input id="empleadorNom" class="cajaTexto" name="empleadorNom" size="40" maxlength="20" value="${solicitudForm.empleadorNom}" readonly="readonly"/>
			<%}%>
       </td>
	</tr>
	</table>
	</div>

   	<div class="ui-widget ui-widget-content ui-corner-all" style="width: 860px;margin: 3px; margin-top: 10px;">
	<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
	<label>Datos de la Oficina y Ejecutivo</label>
	</div>
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
			<input type="text" name="ejecutivoCtaCod" class="cajaTexto" id="ejecutivoCtaCod" size="10" maxlength="10" value="${solicitudForm.ejecutivoCtaCod}" readonly="readonly">
			<input type="text" name="ejecutivoCtaNom" class="cajaTexto" id="ejecutivoCtaNom" size="40" maxlength="20" value="${solicitudForm.ejecutivoCtaNom}" readonly="readonly">
			<%}%>
			
	   </td>
	   <td align="right" valign="middle">
			<font class="fontText">Fecha de Ingreso Oficina *</font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="strFechaIngreso" class="cajaTexto" id="strFechaIngreso" size="20" maxlength="20" value="${Solicitud.strFechaIngreso}" readonly="readonly">
			<%}else{%>
			<input type="text" name="strFechaIngreso" class="cajaTexto" id="strFechaIngreso" size="30"  maxlength="30" value="${solicitudForm.strFechaIngreso}" readonly="readonly">
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
	</div>
	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<div class="ui-widget ui-widget-content ui-corner-all" style="width: 1260px;margin: 3px;">
			<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
			<label>Datos drl Producto</label></div>
				<table id="listProductsDetalle" class="grid" width="1300px;">
				</table>
			</div>
	<%}else{%>
	<div class="ui-widget ui-widget-content ui-corner-all" style="width: 1260px;margin: 3px;margin-top: 10px;">
	<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
	<label>Datos drl Producto</label></div>
	<table style="width: 1260px;">	
	<tr>
       <td align="left" valign="middle">
       <font class="fontText">Banca *</font>&nbsp;
       <html:select property="codBanca" styleId="codBanca" onchange="changeBankListProducts(this);changeBancSubBanca(this.value, null);">
				<html:option value="-1" >[   SELECCIONE   ]</html:option>
				<c:if test="${lstBancas != null}">
					<c:forEach var="banca" items="${lstBancas}">
						<html:option value="${banca.codBanca}">
							<c:out value="${banca.nombre}" />
						</html:option>
					</c:forEach>
				</c:if>
			</html:select>
	   </td>
	    <td align="left" valign="middle">
       <font class="fontText">Sub Banca *</font>&nbsp;
       <html:select property="subBanca" styleId="subBanca" onchange="changeSubanca(this);">
				<html:option value="-1" >[   SELECCIONE   ]</html:option>
			</html:select>
	   </td>
	   <td align="center" valign="middle">
			<font class="fontText">Moneda *</font>&nbsp;       
	   		<html:select property="codMultMoneda" styleId="codMultMoneda">
				<html:option value="-1" >[   SELECCIONE   ]</html:option>
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

	<a href="javascript:deleteProducto();" class="buttonGPR">Eliminar</a>&nbsp;
	<a href="javascript:addProducto();" class="buttonGPR">Agregar</a>
	
	<br/><br/>
	<table id="listProducts" class="grid" width="1300px;">
	</table>
	</div>
		<%}%>
	
   	<div class="ui-widget ui-widget-content ui-corner-all" style="width: 1260px;margin: 3px;margin-top: 10px;"></div>
	<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
	<label>Datos de Riesgo del Cliente</label></div>
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
			<input type="text" name="riesgoGrupal" class="cajaTexto"  id="riesgoGrupal" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${solicitudForm.riesgoGrupal}' onchange="getMonto(this.value, 3);" style="background: #F2F5A9;text-align: right;" onblur="format(this.value);blurChangeColor(this);">
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
		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
		<%}else{%>
			<td colspan="5"><input type="button" class="buttonGPR"  name="btnGuardar" id="btnGuardar" onclick="guardarSolicitud();" value="Guardar Solicitud">&nbsp;
			<input type="button" class="buttonGPR"  name="btnReset" id="btnReset" onclick='limpiaForm($("#formSolicitudIngreso"));' value="Limpiar Solicitud">&nbsp;
			<input type="button"   id="btnCondiciones"  class="buttonGPR" value="Añadir Observacion" onclick="llamarPopup();">&nbsp;
		<%}%>
		<div id="dialog-form" title="Agregar Observacion" style="width: 400px">
		<form>
	        <center>
	        	<textarea id="strMensaje" name="strMensaje" rows="10" cols="40" style="width: 390px; height: 140px;"></textarea>
	        </center>
		</form>
	</div>
	</tr>
	</table>
	<div id="valCondicionCliente"  title="Condiciones del Cliente" style="width: 400px">
	<form>
			<div class="ui-widget ui-widget-content ui-corner-all" style="margin: 2px;">
				<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
		            <label>&nbsp;&nbsp;&nbsp;Condiciones del Cliente</label>
		        </div>
		<table style="text-align: center;">
			<tr><td class="txt-titulo" colspan="2" align="left">Cliente No cumplio las siguientes validaciones : </td></tr>
			<tr><td colspan="2" id="mensaje_cliente"> </td></tr>			
		</table></div></form>
	</div>
	<div id="valMontoPlazos"  title="Condiciones de la solicitud" style="width: 400px">
	<form>
			<div class="ui-widget ui-widget-content ui-corner-all" style="margin: 2px;">
				<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
		            <label>&nbsp;&nbsp;&nbsp;Validacion de Monto y Plazos</label>
		        </div>
		<table style="text-align: center;">
			<tr><td class=".txt-titulo">La solicitud no cumplio las siguientes validaciones : </td></tr>
			<tr>
				<td>Solicitud será enviada a riesgos</td>
			</tr>
			
		</table></div></form>
	</div>
</html:form>
<script type="text/javascript">
function llamarPopup(){
	$( "#dialog-form" ).dialog( "open" );
}
</script>
</body>
</html>