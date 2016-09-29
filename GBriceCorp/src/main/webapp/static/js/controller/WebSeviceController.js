'use strict';

App.controller('WSController', ['$scope', function($scope,$http) {

	$.getJSON("http://www.geoplugin.net/currency_symbols.gp?format=json&jsoncallback=?",
			function(data){
				var currencyCode = geoplugin_currencyCode();
				var fromCurr = '';
				var toCurr = '';

				$.each(data, function(i,item){
					if ( currencyCode == i ) {
						fromCurr = fromCurr + "<option value='"+i+"' selected>"+item.name+" "+(item.symbol?item.symbol:'')+"</option>";
					} else {
						fromCurr = fromCurr + "<option value='"+i+"'>"+item.name+" "+(item.symbol?item.symbol:'')+"</option>";
					}
				});          
				$.each(data, function(i,item){
					if ( currencyCode == 'USD' && i == 'EUR' ) {
						toCurr = toCurr + "<option value='"+i+"' selected>"+item.name+" "+(item.symbol?item.symbol:'')+"</option>";
					} else {
						toCurr = toCurr + "<option value='"+i+"'>"+item.name+" "+(item.symbol?item.symbol:'')+"</option>";
					}
				});
				$(fromCurr).appendTo("#gp_from");
				$(toCurr).appendTo("#gp_to");
			}).success(function(data, status) {
			   $scope.fromCurr = data.fromCurr;
			   $scope.toCurr = data.toCurr;
				
			  }).error(function(data, status) {
			    // Some error occurred
			  });
			
}]);
