formaterDate = function(date){
	var monthName = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre' , 'Diciembre']
		, monthShortName = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Set', 'Oct', 'Nov' , 'Dic']
		, monthNumName = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']
		, day = date.getDate()
		, month = date.getMonth()
		, year = date.getYear()
		, fullYear = date.getFullYear();
	
	day = day < 9 ? '0' + day : day;
	
	return day + '/' + monthNumName[month] + '/' + fullYear;
};
configurarGrid = function(id, data, _options){
	$("#panel_"+id).html("<div id='paginador_" + id + "'/><table id='" + id + "'/>");
	options = $.extend({
        datatype: "local",
        data: data,
        // rowList: [5,10,15,20,25],
        rowNum: 5,
        rownumbers: true,
        width: "100%",
        height: 250,
        recordtext: "{0} - {1} de {2} elementos",
        emptyrecords: 'No hay resultados',
        pgtext: 'Pag: {0} de {1}',
        pager: "#paginador_" + id,
        viewrecords: true,
		scrollOffset: 1,
		multiselect: false,			
		subGrid: false,
		footerrow: false,
        loadonce: true,
        shrinkToFit: false,
        loadError: function(xhr, st, err) {
            alert("Type: "+st+"; Response: "+ xhr.status + " " + xhr.statusText + "; Error:" + err);
        }
    }, _options);
	
	$("#"+id).jqGrid(options);
};

obtenerContexto = function() {
	var url = document.URL;
	var tmp = url.split("/SistemaGPR");
	return tmp[0] + "/SistemaGPR/";
};

contenidocombo = function (data){
    var options = '<option value="9999999999">SELECCIONAR</option>';
    $.each(data, function(i , columns){
        var value = columns.codElemento; 
        var label = columns.strValor;		
        options +='<option value="' + value + '">' + label + '</option>';
    });	
    return options;
}