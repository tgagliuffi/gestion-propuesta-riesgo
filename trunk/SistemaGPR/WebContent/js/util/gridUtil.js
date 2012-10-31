var arrCuartoNivel= new Array();
var jsonPadreSeleccion = "{idPadre:'',selectAll:'', selecciones:'', elimina:''}";
var objPadreSeleccion = eval("(" + jsonPadreSeleccion + ")");
var nivelSeleccion=0;
var VAL_WIDTH = { XSMALL: 25, VSMALL: 50, LSMALL: 80, XLSMALL: 85, SMALL: 130, VMED: 170, LMED: 180, MED: 230, BMED: 280, LARGE: 350, VLARGE: 500, VL: 650};
/**
 * M�todo que determina el nivel en base a los id's de las tablas que contienen los grids
 * 
 * @returns el nivel del grid
 */
function getNivel(){
 		var idGrid = arguments[0];
 		var array = idGrid.split("_");		
	var contador = 2;		
	for(var i =0; i< array.length; i++){			
		if(array[i] == "t"){
			contador++;
			}
	}	
	return contador;
}

function getAltura(){
   		var tabla = arguments[0];
   		return (tabla.rows.length*22);
}
function getIdTablas(){
   		var idTablas = new Array();
   		var idTablaArray = arguments[0].split('_');
   		var idTabla = "";
   		
   		for (var i = 0; i < idTablaArray.length; i++){
   			idTabla += idTablaArray[i]+"_";
   			if (idTablaArray[i] == "t" ){
   				if ( idTablas.length > 0){
   					idTablas.push(idTablas[idTablas.length-1]+idTabla.substr(0,idTabla.length-1));
   				} else {
   					idTablas.push(idTabla.substr(0,idTabla.length-1));
   				}
   				idTabla = "_";
 			}
 		}
   		return idTablas;
 }
function redimensionarAlturaGrids(){
   		var idGrids = getIdTablas(arguments[0]);
   		
   		var numeroPixelesTotal = 0;
   		var pixeles = 0;
   		for (var i = idGrids.length-1; i >= 0; i--){
   			pixeles = getAltura(document.getElementById(idGrids[i]));
   			numeroPixelesTotal += pixeles;
   			jQuery("#"+idGrids[i]).jqGrid('setGridHeight',numeroPixelesTotal);
   		} 
}

function redimensionarAnchoGrids(){
		var idGrids = getIdTablas(arguments[0]);
		var numeroPixelesTotal = arguments[1];
		var idTableForm = arguments[2];
		var pixeles = 0;
		
		for (var i = idGrids.length-1; i >= 0; i--){
			numeroPixelesTotal += numeroPixelesTotal;
			if ( i != idGrids.length-1 ){
				var div = document.getElementById('gview_'+idGrids[i]).childNodes[2];
				pixeles = jQuery('#'+idGrids[i]).parent().width();
				div.style.width= (pixeles+numeroPixelesTotal)+"px";
			}
		}
		
		try{
			var div = document.getElementById('gview_'+idTableForm).childNodes[2];
			pixeles = jQuery("#"+idTableForm).parent().width();
			div.style.width= (pixeles+numeroPixelesTotal)+"px";
		}catch(e){}
}

function mantieneEstadoPaginado(subGridId, nuevoGridId, idSelect) {
	var subGrid = "";
	if(nivelSeleccion == 1){
		subGrid = "cb_" + subGridId;
	}else{
		subGrid = "jqg_" + subGridId;
	}
	
	for ( var i = 0; i < arrCuartoNivel.length; i++) {
		if (arrCuartoNivel[i] != undefined && arrCuartoNivel[i].idPadre != undefined) {
			if (arrCuartoNivel[i].idPadre == subGrid) {
				if (arrCuartoNivel[i].selectAll == "/") {
					var arrayHijos = jQuery("#" + nuevoGridId).getDataIDs();
					var elimindados = new Array();
					if (arrCuartoNivel[i].elimina.length > 2) {
						elimindados = arrCuartoNivel[i].elimina.split("$$");
					}
					for ( var j = 0; j < arrayHijos.length; j++) {
						var bandera = false;
						for ( var k = 0; k < elimindados.length; k++) {
							if (jQuery("#" + nuevoGridId).getRowData(
									arrayHijos[j])[idSelect] == elimindados[k]) {// cambiar por idSelect
								bandera = true;
							}
						}
						if (bandera == false) {
							jQuery("#" + nuevoGridId).setSelection(
									arrayHijos[j], true);
						}

					}
				} else if (arrCuartoNivel[i].selecciones.length > 2) {
					var select = new Array();
					select = arrCuartoNivel[i].selecciones.split("$$");
					var arrayHijos = jQuery("#" + nuevoGridId).getDataIDs();
					for ( var i = 0; i < arrayHijos.length; i++) {
						var bandera = false;
						for ( var j = 0; j < select.length; j++) {
							if (jQuery("#" + nuevoGridId).getRowData(
									arrayHijos[i])[idSelect] == select[j]) {// cambiar por idSelect
								bandera = true;
							}

						}
						if (bandera == true) {
							jQuery("#" + nuevoGridId).setSelection(
									arrayHijos[i], true);
						}
					}
				}
			}
		}
	}
}

function agregarEncabezados(){
	var gridId = arguments[0];
	var encabezados = arguments[1];
	var nivelPaginador = arguments[2];
	var elementosBlanco = arguments[3];
	
	for (var i = 0 ; i < encabezados.length; i++){
		var gridTabla = document.getElementById("gview_"+gridId).childNodes[1].childNodes[0].children[0];
		
		var row=gridTabla.insertRow(0);
		row.className='ui-jqgrid-labels';
		row.setAttribute('role','rowheader');
		row.style.height='22px';
		
		/*PERMITE DIBUJAR UN BLANCO ARRIBA DEL CHECK*/
		var celdaVacia1 =row.insertCell(0);
		celdaVacia1.className='ui-state-default ui-th-column ui-th-ltr';
		celdaVacia1.style.width= '23px';
		celdaVacia1.innerHTML='&nbsp;';

		var contadorCelda = 1;
		
		/*DIBUJA EL ESPACIO DEL EXPANSOR*/
		if ( !nivelPaginador ){
			var celdaVacia2 = row.insertCell(contadorCelda);
			celdaVacia2.className='ui-state-default ui-th-column ui-th-ltr';
			celdaVacia2.style.width= '23px';
			celdaVacia2.innerHTML='&nbsp;';
			contadorCelda++;
		}
		
		/*DIBUJA LOS ELEMENTOS EN BLANCO ENVIADOS*/		
		for(var j=0; j<elementosBlanco; j++){
			var celdaVacia3 = row.insertCell(contadorCelda);
			celdaVacia3.className='ui-state-default ui-th-column ui-th-ltr';
			celdaVacia3.innerHTML='&nbsp;';
			contadorCelda++;
		}
		
		var objsEncabezado = encabezados[i];
		
		for ( var j = 0; j < objsEncabezado.length; j++){
			var encabezadoObj = objsEncabezado[j];
			var celdaEncabezado = row.insertCell(contadorCelda);
			celdaEncabezado.className='ui-state-default ui-th-column ui-th-ltr';
			celdaEncabezado.colSpan = encabezadoObj.colspan;
			celdaEncabezado.innerHTML=encabezadoObj.descripcion;
			contadorCelda++;
		}
		
	}
}


function eliminaElementoPaginado(elemento){
	var elmt="jqg_"+elemento; // agregamos el elemento para saber el check padre
	
	var i=0;
	for(i=0; i<arrCuartoNivel.length; i++){
		var idPadre=arrCuartoNivel[i].idPadre;
		
		var posicion=idPadre.search(elmt);//validamos si el idPadre tiene coincidencia con el elemento cerrado
		if(posicion==0){// si la coincidencia se da en la primera pocision borramos el elemento
			arrCuartoNivel.splice(i, 1);
		}
	}
}

function deleteTblRowAdded(idTableForm){
	
	var tabla = document.getElementById(idTableForm);
  	var tablaBody = tabla.getElementsByTagName("tbody")[0];
  	var filas = tablaBody.getElementsByTagName("tr");

  	for(var i=0; i<filas.length; i++){
  		if(filas[i].id == 'undefined'){
  			jQuery("#"+idTableForm).jqGrid('delRowData',filas[i].id);
  			break;
  		}
  	}
}

function flushTblRowAdded(idTableForm, nameFieldCode, indexTDCode, indexTDAdded, idGenerado){
	
	var tabla = document.getElementById(idTableForm);
  	var tablaBody = tabla.getElementsByTagName("tbody")[0];
  	var filas = tablaBody.getElementsByTagName("tr");
	var contador = 1;

  	for(var i=0; i<filas.length; i++){
  		if(filas[i].id == 'undefined'){
  			
  			if(i > 0){
  				contador = parseFloat(filas[i-1].id);	
  			}
  			
  			if(isNaN(contador)){
  				filas[i].id	= 1;
  			}else{
  				filas[i].id = contador+1;
  			}
  			
  			var celdaValue = filas[i].getElementsByTagName("td")[indexTDAdded].title;
  			document.getElementById("jqg_"+idTableForm+"_undefined").id = "jqg_"+idTableForm+"_"+filas[i].id;
  			
  			if(celdaValue == '-1'){
  				if(indexTDCode == 0){
  	  				filas[i].getElementsByTagName("td")[indexTDAdded].title = idGenerado;
  	  				
	  	  			if(nameFieldCode != ''){
	  					filas[i].getElementsByTagName("td")[indexTDAdded].innerHTML = idGenerado+
	  	  				"<input type='hidden' name='"+nameFieldCode+"' value='"+idGenerado+"' />";
	  				}else{
	  					filas[i].getElementsByTagName("td")[indexTDAdded].innerHTML = idGenerado;
	  				}
  				}else{
  					var titleText = filas[i].getElementsByTagName("td")[indexTDCode].title;
  	  				filas[i].getElementsByTagName("td")[indexTDAdded].title = titleText;
  	  				
	  	  			if(nameFieldCode != ''){
	  					filas[i].getElementsByTagName("td")[indexTDAdded].innerHTML = titleText+
	  	  				"<input type='hidden' name='"+nameFieldCode+"' value='"+titleText+"' />";
	  				}else{
	  					filas[i].getElementsByTagName("td")[indexTDAdded].innerHTML = titleText;
	  				}
  				}  				  				
  			}
  			
  			break;
  		}
  	}
}

function flushTblRowEdited(idTableForm, nameFieldCode, indexTDCode, indexTDEdited, indexRowEdited){
	
	var tabla = document.getElementById(idTableForm);
  	var tablaBody = tabla.getElementsByTagName("tbody")[0];
  	var filas = tablaBody.getElementsByTagName("tr");
  	var nIndexRow = parseFloat(indexRowEdited);
  	
  	if(parseFloat(indexRowEdited) >= 0){
  	
		var celdaValue = filas[nIndexRow].getElementsByTagName("td")[indexTDEdited].title;
		var titleText  = filas[nIndexRow].getElementsByTagName("td")[indexTDCode].title;
		if(celdaValue != titleText){
			
			filas[nIndexRow].getElementsByTagName("td")[indexTDEdited].title = titleText;
			if(nameFieldCode != ''){
				filas[nIndexRow].getElementsByTagName("td")[indexTDEdited].innerHTML = titleText+
				"<input type='hidden' name='"+nameFieldCode+"' value='"+titleText+"' />";
			}else{
				filas[nIndexRow].getElementsByTagName("td")[indexTDEdited].innerHTML = titleText;
			}
		}
	}
}

function encabezado(){
	this.descripcion = "";
	this.colspan = "";
}

function getElementsByName_iefix(name, tag) {
    
	if(!tag){
        tag = '*';
    }
	
    var elem = document.getElementsByTagName(tag);
    var arr = [];
    for(var i = 0; i < elem.length; i++) {
         att = elem[i].getAttribute("name");
         if(att == name) {
        	 arr.push(elem[i]);
         }
    }
    return arr;
}

function setElementSelected(obj, valElement){
	for ( var i = 0; i < obj.length; i++) {
		if(obj[i].value == valElement){
			obj[i].selected = true;
		}
	}
}