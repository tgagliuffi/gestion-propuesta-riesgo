function genericUnFormat(cellvalue, options, cell){
	return $('input', cell).attr('value');
}

function genericValueCustom(elem, operation, value) {
    if(operation === 'get') {
       var valActual   = $(elem).val();
       if(valActual == undefined){
    	   return "";
       }else{
    	   return valActual;
       }
    } else if(operation === 'set') {
       $(elem).val(value);
    }
}

function genericComboValueCustom(elem, operation, value) {
	if(operation === 'get') {
       var valCode = $(elem).val();
       var valID   = $(elem)[0].id; //frk: considerando que solamente existe un campo en la celda
       var valText = $('#'+valID+' option:selected').html();//frk: obteniendo el texto del combobox
       
       if(valText == null || valText == undefined){
    	   valText = valCode;
       }
       
       if(valCode == undefined){
    	   return "";
       }else{
    	   return valCode+"|"+valText;
       }
    } else if(operation === 'set') {
       $(elem).val(value);
    }
}

////////////////////////////////////////////////////////
//ingreso_solicitud_modified.jsp
////////////////////////////////////////////////////////

function tipoFormat(cellvalue, options, rowObject){
	if(cellvalue == null || cellvalue == undefined || cellvalue == 'null' || cellvalue == ''){
		return "&nbsp;<input type='hidden' name='arrayTipo' value='"+cellvalue+"' />";
	}else{
		
		//frk: spliteamos la cadena concatenada del 
		//combo para obtener el texto y el valor
		
		var arrayCell = cellvalue.split("|");
		if(arrayCell.length > 1){
			return arrayCell[1]+"<input type='hidden' name='arrayTipo' value='"+arrayCell[0]+"' />";
		}else{
			return cellvalue+"<input type='hidden' name='arrayTipo' value='"+cellvalue+"' />";
		}	
	}		
}

function desProductoFormat(cellvalue, options, rowObject){
	if(cellvalue == null || cellvalue == undefined || cellvalue == 'null' || cellvalue == ''){
		return "&nbsp;<input type='hidden' name='arrayDesProducto' value='"+cellvalue+"' />";
	}else{
		//frk: spliteamos la cadena concatenada del 
		//combo para obtener el texto y el valor
		
		var arrayCell = cellvalue.split("|");
		if(arrayCell.length > 1){
			return arrayCell[1]+"<input type='hidden' name='arrayDesProducto' value='"+arrayCell[0]+"' />";
		}else{
			return cellvalue+"<input type='hidden' name='arrayDesProducto' value='"+cellvalue+"' />";
		}
	}
}

function contratoVincFormat(cellvalue, options, rowObject){
	if(cellvalue == null || cellvalue == undefined || cellvalue == 'null' || cellvalue == ''){
		return "&nbsp;<input type='hidden' name='arrayContratoVinc' value='"+cellvalue+"' />";
	}else{
		//frk: spliteamos la cadena concatenada del 
		//combo para obtener el texto y el valor
		
		var arrayCell = cellvalue.split("|");
		if(arrayCell.length > 1){
			return arrayCell[1]+"<input type='hidden' name='arrayContratoVinc' value='"+arrayCell[0]+"' />";
		}else{
			return cellvalue+"<input type='hidden' name='arrayContratoVinc' value='"+cellvalue+"' />";
		}
	}
}

function campaniaFormat(cellvalue, options, rowObject){
	if(cellvalue == null || cellvalue == undefined || cellvalue == 'null' || cellvalue == ''){
		return "&nbsp;<input type='hidden' name='arrayCampaign' value='"+cellvalue+"' />";
	}else{
		//frk: spliteamos la cadena concatenada del 
		//combo para obtener el texto y el valor
		
		var arrayCell = cellvalue.split("|");
		if(arrayCell.length > 1){
			return arrayCell[1]+"<input type='hidden' name='arrayCampaign' value='"+arrayCell[0]+"' />";
		}else{
			return cellvalue+"<input type='hidden' name='arrayCampaign' value='"+cellvalue+"' />";
		}
	}
}

////////////////////////////////////////////////////////
//analisis_dictamen.jsp
////////////////////////////////////////////////////////

function codProcesoFormat(cellvalue, options, rowObject){
	return cellvalue+"<input type='hidden' name='arrayCodProceso' value='"+cellvalue+"' />";
}

////////////////////////////////////////////////////////
//asignacion_prioridad.jsp
////////////////////////////////////////////////////////

function btnOpcionFormat(cellvalue, options, rowObject){
	return "<a title='Prioridad Individual' href='javascript:editPrioridad("+cellvalue+");'><img src='imagenes/detalle.gif' border='0' height='18'></a>&nbsp;"+
	"<a title='Anulación Individual' href='javascript:editAnular("+cellvalue+");'><img src='imagenes/detalle.gif' border='0' height='18'></a>";
}

////////////////////////////////////////////////////////
//consulta_solicitud.jsp
////////////////////////////////////////////////////////

function btnOpcionConsultaFormat(cellvalue, options, rowObject){
	return "<a title='Detalle Consulta' href='javascript:showDetalle("+cellvalue+");'><img src='imagenes/detalle.gif' border='0' height='18'></a>";
}

////////////////////////////////////////////////////////
//asignacion_individual.jsp
////////////////////////////////////////////////////////

function prioridadFormat(cellvalue, options, rowObject){
	
	var slctPrioridad = "<select name='arrayPrioridad'>";
	if(cellvalue == '1'){
		slctPrioridad += "<option selected='selected' value='1'>ALTA</option>"+
		"<option value='2'>NORMAL</option>"+			
		"<option value='3'>BAJA</option>";
	}else if(cellvalue == '2'){
		slctPrioridad += "<option value='1'>ALTA</option>"+
		"<option selected='selected' value='2'>NORMAL</option>"+			
		"<option value='3'>BAJA</option>";
	}else if(cellvalue == '3'){
		slctPrioridad += "<option value='1'>ALTA</option>"+
		"<option value='2'>NORMAL</option>"+			
		"<option selected='selected' value='3'>BAJA</option>";
	}
	
	slctPrioridad += "</select>";	
	return slctPrioridad;
}