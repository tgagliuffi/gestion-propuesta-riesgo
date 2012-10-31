function ocultarElementByID(id,tiempo){
	setTimeout("document.getElementById('"+id+"')!=null?document.getElementById('"+id+"').style.display='none':document.getElementById('"+id+"');", tiempo);
}