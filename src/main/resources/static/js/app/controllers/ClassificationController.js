'use strict';

angular.module('crudApp').controller('ClassificationController',
    ['ClassificationService', '$scope',  function(ClassificationService, $scope) {

        var self = this;
        
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        self.selectedGroup = null;
        
        self.vars = [["2", "20", "210", "2300"], ["3", "26", "290", "3100"], ["4", "36", "300", "4200"], 
            	["5", "40", "520", "5600"], ["6", "65", "700", "7600"], ["8", "85", "950", "10200"], 
            	["11", "120", "1300", "13800"], ["15", "150", "1700", "18500"]];
        
		self.getVariablesNames = getVariablesNames;
		self.uploadKvvpFile = uploadKvvpFile;
		self.classify = classify;
        
        function getVariablesNames(){
            return self.vars;
        }
        
        function uploadKvvpFile() {
    		var f = document.getElementById('file').files[0];
    		ClassificationService.readKvvvExcel(f)
                .then(
                    function(){
                        showKvvv(ClassificationService.getKvvv());
                    },
                    function(errResponse){
                    }
                );
        }
        
        function classify() {
        	var kvvv = new Object();
        	kvvv.d2 = document.getElementById("valD2").value;
        	kvvv.d3 = document.getElementById("valD3").value;
        	kvvv.d4 = document.getElementById("valD4").value;
        	kvvv.d5 = document.getElementById("valD5").value;
        	kvvv.d6 = document.getElementById("valD6").value;
        	kvvv.d8 = document.getElementById("valD8").value;
        	kvvv.d11 = document.getElementById("valD11").value;
        	kvvv.d15 = document.getElementById("valD15").value;
        	kvvv.d20 = document.getElementById("valD20").value;
        	kvvv.d26 = document.getElementById("valD26").value;
        	kvvv.d36 = document.getElementById("valD36").value;
        	kvvv.d40 = document.getElementById("valD40").value;
        	kvvv.d65 = document.getElementById("valD65").value;
        	kvvv.d85 = document.getElementById("valD85").value;
        	kvvv.d120 = document.getElementById("valD120").value;
        	kvvv.d150 = document.getElementById("valD150").value;
        	kvvv.d210 = document.getElementById("valD210").value;
        	kvvv.d290 = document.getElementById("valD290").value;
        	kvvv.d300 = document.getElementById("valD300").value;
        	kvvv.d520 = document.getElementById("valD520").value;
        	kvvv.d700 = document.getElementById("valD700").value;
        	kvvv.d950 = document.getElementById("valD950").value;
        	kvvv.d1300 = document.getElementById("valD1300").value;
        	kvvv.d1700 = document.getElementById("valD1700").value;
        	kvvv.d2300 = document.getElementById("valD2300").value;
        	kvvv.d3100 = document.getElementById("valD3100").value;
        	kvvv.d4200 = document.getElementById("valD4200").value;
        	kvvv.d5600 = document.getElementById("valD5600").value;
        	kvvv.d7600 = document.getElementById("valD7600").value;
        	kvvv.d10200 = document.getElementById("valD10200").value;
        	kvvv.d13800 = document.getElementById("valD13800").value;
        	kvvv.d18500 = document.getElementById("valD18500").value;
        	
			ClassificationService.classify(kvvv);
        }
        
        function showKvvv(kvvv) {
        	document.getElementById("valD2").value = kvvv.d2;
        	document.getElementById("valD3").value = kvvv.d3;
        	document.getElementById("valD4").value = kvvv.d4;
        	document.getElementById("valD5").value = kvvv.d5;
        	document.getElementById("valD6").value = kvvv.d6;
        	document.getElementById("valD8").value = kvvv.d8;
        	document.getElementById("valD11").value = kvvv.d11;
        	document.getElementById("valD15").value = kvvv.d15;
        	document.getElementById("valD20").value = kvvv.d20;
        	document.getElementById("valD26").value = kvvv.d26;
        	document.getElementById("valD36").value = kvvv.d36;
        	document.getElementById("valD40").value = kvvv.d40;
        	document.getElementById("valD65").value = kvvv.d65;
        	document.getElementById("valD85").value = kvvv.d85;
        	document.getElementById("valD120").value = kvvv.d120;
        	document.getElementById("valD150").value = kvvv.d150;
        	document.getElementById("valD210").value = kvvv.d210;
        	document.getElementById("valD290").value = kvvv.d290;
        	document.getElementById("valD300").value = kvvv.d300;
        	document.getElementById("valD520").value = kvvv.d520;
        	document.getElementById("valD700").value = kvvv.d700;
        	document.getElementById("valD950").value = kvvv.d950;
        	document.getElementById("valD1300").value = kvvv.d1300;
        	document.getElementById("valD1700").value = kvvv.d1700;
        	document.getElementById("valD2300").value = kvvv.d2300;
        	document.getElementById("valD3100").value = kvvv.d3100;
        	document.getElementById("valD4200").value = kvvv.d4200;
        	document.getElementById("valD5600").value = kvvv.d5600;
        	document.getElementById("valD7600").value = kvvv.d7600;
        	document.getElementById("valD10200").value = kvvv.d10200;
        	document.getElementById("valD13800").value = kvvv.d13800;
        	document.getElementById("valD18500").value = kvvv.d18500;
        }
    }
]);