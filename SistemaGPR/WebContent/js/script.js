var lLetras=' ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyzáéíóúÁÉÍÓÚ';
var lLetrasSinEspacio='ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyzáéíóúÁÉÍÓÚ';
var lAdicional1='.';
var lAdicional2='-';
var lAdicional3='+';
var lAdicional4='*#';
var lNumeros='1234567890';
var lExtraDecimales=',.';
var lMantParametros='-/=+.\?:&';
var lRpm = 'CB#*';

function ingresoMantParametros(e){
	var key;
	var valid = '' + lLetras + lNumeros + lMantParametros;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
} 
function ingresoLetrasNumeros(e){
	var key;
	var valid = '' + lLetras + lNumeros;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1){
			e.keyCode = 0;
			
		}
			
	}
}
function ingresoRpm(e){
	var key;
	var valid = lRpm  + lNumeros;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function ingresoLetrasNumerosGuion(e){
	var key;
	var valid = '' + lLetras + lNumeros + lAdicional2;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function ingresoLetrasNumerosGuionMas(e){
	var key;
	var valid = '' + lLetras + lNumeros + lAdicional2 + lAdicional3;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function ingresoLetrasNumerosGuionMasPunto(e){
	var key;
	var valid = '' + lLetras + lNumeros + lAdicional2 + lAdicional3 + lAdicional1;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function ingresoLetrasNumerosGuionPunto(e){
	var key;
	var valid = '' + lLetras + lNumeros + lAdicional2 + lAdicional1;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function ingresoNumeros(e){
	var key;
	var valid = '' + lNumeros + lExtraDecimales;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function ingresoNumerosAsteriscoNumeral(e){
	var key;
	var valid = '' + lNumeros + lAdicional4;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function ingresoSoloNumeros(e){
	var key;
	var valid = '' + lNumeros;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function ingresoNumerosSinEspacio(e){

	var key;
	var valid = '' + lNumeros;
	
	
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function ingresoLetras(e){
	var key;
	var valid = '' + lLetras;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function ingresoLetrasNumerosSinEspacio(e){
	var key;
	var valid = '' + lLetrasSinEspacio + lNumeros;
		
	if(e.which){
		key = String.fromCharCode(e.which);
		if (valid.indexOf("" + key) == -1)
			e.preventDefault();
	}
	else if(e.keyCode){
		key = String.fromCharCode(e.keyCode);
		if (valid.indexOf("" + key) == -1)
			e.keyCode = 0;
	}
}
function esLetrasNumerosSinEspacio(o){
	var valid = '' + lLetrasSinEspacio + lNumeros + lExtraDecimales;
		
	for(i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function esLetras(o){
	var valid = '' + lLetras;
	for(var i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function esNumeros(o){
	var valid = '' + lNumeros + lExtraDecimales;
	for(i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function esLetrasNumeros(o){
	
	var valid = '' + lLetras + lNumeros;
	
	for(i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function esLetrasNumerosGuion(o){

	var valid = '' + lLetras + lNumeros + lAdicional2;
	
	for(i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function esLetrasNumerosGuionMas(o){

	var valid = '' + lLetras + lNumeros + lAdicional2 + lAdicional3;
	
	for(i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function esLetrasNumerosGuionMasPunto(o){

	var valid = '' + lLetras + lNumeros + lAdicional2 + lAdicional3 + lAdicional1;
	for(i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function esLetrasNumerosGuionPunto(o){

	var valid = '' + lLetras + lNumeros + lAdicional2 + lAdicional1;
	
	for(i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function esNumerosSinEspacio(o){

	var valid = '' + lNumeros;
	for(i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function esNumeroRpm(o){

	var valid = lRpm + lNumeros;
	for(i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function esNumerosAsteriscoNumeralSinEspacio(o) {
	var valid = '' + lNumeros + lAdicional4;
	for (var i = 0; i < o.length; i++) {
		if (valid.indexOf(o.substring(i, i + 1)) < 0) {
			return false;
		}
	}
	return true;
}
function esMantParametros(o){

	var valid = '' + lLetras + lNumeros + lMantParametros;
	for(i=0; i<o.length; i++){
		if(valid.indexOf(o.substring(i,i+1))<0)
			return false;
	}
	return true;
}
function trim(cadena)
{
            for(i=0; i<cadena.length; )
            {
                    if(cadena.charAt(i)==" "){
                            cadena=cadena.substring(i+1, cadena.length);
                    }else{
                            break;
                    }        
            }
            for(i=cadena.length-1; i>=0; i=cadena.length-1)
            {
                    if(cadena.charAt(i)==" "){
                            cadena=cadena.substring(0,i);
                    }else{
                            break;
                    }        
            }
            return cadena;
}
function ocultarElementByID(id,tiempo){
	setTimeout("document.getElementById('"+id+"')!=null?document.getElementById('"+id+"').style.display='none':document.getElementById('"+id+"');", tiempo);
}
function abrePopUp(varUrl,width,height)
{	 
		var myURL1 = document.URL;
		var myURL2 = window.location.protocol + "//" + (window.location.host);
		var idx1 = myURL1.indexOf(myURL2) + myURL2.length;
		var idx2 = myURL1.indexOf('/SEGVID/');
		
		if(idx1<idx2)
		{  
			var carpeta = myURL1.substr(idx1,idx2-idx1);
     	 	var wind = window.open(myURL2 + carpeta + varUrl,'mywindow','width='+width+',height='+height);
     	 	wind.focus();
     	 	
		}else
		{  
			
   		 	var ventana = window.open(myURL2 + varUrl,'mywindow','width='+width+',height='+height);
   		 	ventana.focus();
     	 			
		}
} 
/*###################################### VALIDANDO FECCHAS #########################################################*/
function fechaMayorOIgualQue(fec0, fec1){  

    var bRes = false;  
    var sDia0 = fec0.substr(0, 2);  
    var sMes0 = fec0.substr(3, 2);  
    var sAno0 = fec0.substr(6, 4);  
    var sDia1 = fec1.substr(0, 2);  
    var sMes1 = fec1.substr(3, 2);  
    var sAno1 = fec1.substr(6, 4);
   
    if (sAno0 > sAno1) 
    	bRes = true;  
    else{  
     if (sAno0 == sAno1){  
      if (sMes0 > sMes1) 
    	  bRes = true;  
      else{  
       if (sMes0 == sMes1)  
        if (sDia0 >= sDia1) 
        	bRes = true;  
      }  
     }  
    }  
  
    return bRes;  
}
function calcular_edad(obj)
{ 
	var dia = obj.substr(0, 2);  
    var mes = obj.substr(3, 2);  
    var ano = obj.substr(6, 4);  
  
    ano = parseInt(ano);
			
    //Calculo la fecha de hoy 
    hoy = new Date(); 
	    
    //El año de la fecha que recibo solo tiene 2 cifras hay que cambiarlo a 4 
    //ano = 1900 + ano;

    //Resto los años de las dos fechas 
    //-1 porque no se si ha cumplido años ya este año 
    var edad = hoy.getYear()- ano - 1; 
    
    //Si resto los meses y me da menor que 0 entonces no ha cumplido años. 
    //Si da mayor si ha cumplido 
    //+ 1 porque los meses empiezan en 0 
   if ( hoy.getMonth() + 1 - mes < 0) {
    	return edad;
    }
      
	if ( hoy.getMonth() + 1 - mes > 0){
		return edad+1;
	} 
        

    if ( hoy.getUTCDate() - dia >= 0) {
    	return edad + 1; 
    }
      

    return edad;
} 

function tildes_unicode(str){
	
	str = str.replace('á','\u00e1');
	str = str.replace('é','\u00e9');
	str = str.replace('í','\u00ed');
	str = str.replace('ó','\u00f3');
	str = str.replace('ú','\u00fa');

	str = str.replace('Á','\u00c1');
	str = str.replace('É','\u00c9');
	str = str.replace('Í','\u00cd');
	str = str.replace('Ó','\u00d3');
	str = str.replace('Ú','\u00da');

	str = str.replace('ñ','\u00f1');
	str = str.replace('Ñ','\u00d1');
	return str;
}

function IsFechaValida(fecha){
    if (fecha != undefined && fecha.value != "" ){
        if (!/^\d{2}\/\d{2}\/\d{4}$/.test(fecha.value)){
            alert("formato de fecha no válido (dd/mm/aaaa)");
            fecha.value="";
            fecha.focus();
            return false;
        }
        var dia  =  parseInt(fecha.value.substring(0,2),10);
        var mes  =  parseInt(fecha.value.substring(3,5),10);
        var anio =  parseInt(fecha.value.substring(6),10);
 
    switch(mes){
        case 1:
        case 3:
        case 5:
        case 7:
        case 8: 
        case 10:
        case 12:
            numDias=31;
            break;
        case 4: case 6: case 9: case 11:
            numDias=30;
            break;
        case 2:
            if (comprobarSiBisisesto(anio)){ 
            	numDias=29; 
            	}else{ numDias=28;
            }
            break;
        default:
            alert("Fecha introducida errónea");
            fecha.value="";
            fecha.focus();
            return false;
    }
 
        if (dia>numDias || dia==0){
            alert("Fecha introducida errónea");
            fecha.value="";
            fecha.focus();
            return false;
        }
        return true;
    }
} 


function comprobarSiBisisesto(anio){
if ( ( anio % 100 != 0) && ((anio % 4 == 0) || (anio % 400 == 0))) {
    return true;
    }
else {
    return false;
    }
} 
/* fecha = a y fecha2 =b
 * 	a>b = TRUE
	a=b = FALSE
	a<b = FALSE
* */
function compare_dates(fecha, fecha2)   
  {   
	
    var xMonth=fecha.substring(3, 5);   
    var xDay=fecha.substring(0, 2);   
    var xYear=fecha.substring(6,10);   
    var yMonth=fecha2.substring(3,5);
    var yDay=fecha2.substring(0, 2);
    var yYear=fecha2.substring(6,10);  
    
    if (xYear > yYear){   
        return(true);   
    }else{   
      if (xYear == yYear){    
	        if (xMonth > yMonth){   
	        		return(true);  
	        }else{    
		         if (xMonth == yMonth){   
		           if (xDay> yDay)   
		              return(true);   
		            else  
		             return(false);   
		         }else{
		        	 return(false);    
		         } 
	       }   
      }else{
    	  return(false);  
      }  
         
    }   
} 
/*
$(document).ready(function(){
	$(".buttonGPR").addClass("cmd");
	$(".cmd").removeClass("buttonGPR");
	$(".cmd").button();
});
*/

