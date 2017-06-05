'use strict';

angular.module('crudApp').controller('DiagnosticGroupController',
    ['DiagnosticGroupService', '$scope',  function(DiagnosticGroupService, $scope) {

        var self = this;
        
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        self.selectedGroup = null;
        
		self.getAllGroups = getAllGroups;
		self.diagGroupSelected = diagGroupSelected;
		self.getVariablesNames = getVariablesNames;
		self.getKvvvForGroup = getKvvvForGroup;
		self.deleteGroup = deleteGroup;
		self.uploadKvvpFile = uploadKvvpFile;
		self.updateDiagnosticGroup = updateDiagnosticGroup;
		self.addRow = addRow;
		self.clearTable = clearTable;
		self.deleteRows = deleteRows;
		self.startDiscriminantAnalysis = startDiscriminantAnalysis;
		
		function getAllGroups(){
            return DiagnosticGroupService.getAllGroups();
        }
        
        function getVariablesNames(){
        	var res = ["2", "3", "4", "5", "6", "8", "11", "15",
                "20", "26", "36", "40", "65", "85", "120", "150", "210", "290",
                "300", "520", "700", "950", "1300", "1700", "2300", "3100", "4200",
                "5600", "7600", "10200", "13800", "18500"];
            return res;
        }
        
		function diagGroupSelected(groupJson){
			var group = JSON.parse(groupJson);
			DiagnosticGroupService.getKvvvForGroup(group.idDg, self)
				.then(
                    function (response) {
                    	self.errorMessage = '';
                    	self.successMessage='';
                    	self.selectedGroup = group;
                    },
                    function (errResponse) {
                        self.errorMessage = "Selected diagnostic group does not exist";
                        self.successMessage='';
                    }
                );
		}
        
        function getKvvvForGroup() {
        	return self.kvvp;
        }
        
        function updateDiagnosticGroup() {
        	if (!(self.selectedGroup === null) && !(self.selectedGroup.idDg === undefined || self.selectedGroup.idDg === null)) {
        		if(self.selectedGroup.groupName === null || self.selectedGroup.groupName == null) {
        			self.errorMessage='Group name is empty';
        			return;
        		}
        		if(self.selectedGroup.description === null || self.selectedGroup.description == null) {
        			self.errorMessage='Description is empty';
        			return;
        		}
                DiagnosticGroupService.updateDiagnosticGroup(self.selectedGroup)
                .then(
                    function (response) {
                        self.done = true;
                        self.newDiagnosticGroup={};
                        self.errorMessage = '';
                        $scope.myForm.$setPristine();
                        
                        if(saveKvvpData() && self.errorMessage == '') {
                			self.successMessage = 'Diagnostic Group updated successfully';
                    		/*self.errorMessage='';*/
                    	}
                    },
                    function (errResponse) {
                        self.errorMessage = 'Error while creating Diagnostic Group: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
            }
            else {
            	self.errorMessage='Group to save is not selected';
            }
        }
        
        function deleteGroup(){
            if(self.selectedGroup === undefined || self.selectedGroup === null || self.selectedGroup.idDg === undefined) {
            	self.errorMessage = 'Group to delete is not chosen';
            	return;
            }
            var groupId = self.selectedGroup.idDg;
            DiagnosticGroupService.removeDiagnosticGroup(groupId)
                .then(
                    function(){
                    	self.selectedGroup = null;
                        console.log('DiagnosticGroup '+ groupId + ' removed successfully');
                    },
                    function(errResponse){
                        self.errorMessage = 'Error while removing DiagnosticGroup '+ groupId +', Error :'+ errResponse.data;
                    }
                );
        }
        
        function saveKvvpData() {
        	var index = 1;
        	var kvvp = new Array(0);
        	while(document.getElementById('kvvp_row_' + index) !== undefined 
        		&& document.getElementById('kvvp_row_' + index) !== null) {
        		var kvvpRow = new Object();
        		kvvpRow.idKv = null;
        		kvvpRow.d2 = document.getElementById('d2_' + index).textContent;
        		kvvpRow.d3 = document.getElementById('d3_' + index).textContent;
        		kvvpRow.d4 = document.getElementById('d4_' + index).textContent;
        		kvvpRow.d5 = document.getElementById('d5_' + index).textContent;
				kvvpRow.d6 = document.getElementById('d6_' + index).textContent;
				kvvpRow.d8 = document.getElementById('d8_' + index).textContent;
				kvvpRow.d11 = document.getElementById('d11_' + index).textContent;
				kvvpRow.d15 = document.getElementById('d15_' + index).textContent;
				kvvpRow.d20 = document.getElementById('d20_' + index).textContent;
				kvvpRow.d26 = document.getElementById('d26_' + index).textContent;
				kvvpRow.d36 = document.getElementById('d36_' + index).textContent;
				kvvpRow.d40 = document.getElementById('d40_' + index).textContent;
				kvvpRow.d65 = document.getElementById('d65_' + index).textContent;
				kvvpRow.d85 = document.getElementById('d85_' + index).textContent;
				kvvpRow.d120 = document.getElementById('d120_' + index).textContent;
				kvvpRow.d150 = document.getElementById('d150_' + index).textContent;
				kvvpRow.d210 = document.getElementById('d210_' + index).textContent;
				kvvpRow.d290 = document.getElementById('d290_' + index).textContent;
				kvvpRow.d300 = document.getElementById('d300_' + index).textContent;
				kvvpRow.d520 = document.getElementById('d520_' + index).textContent;
				kvvpRow.d700 = document.getElementById('d700_' + index).textContent;
				kvvpRow.d950 = document.getElementById('d950_' + index).textContent;
				kvvpRow.d1300 = document.getElementById('d1300_' + index).textContent;
				kvvpRow.d1700 = document.getElementById('d1700_' + index).textContent;
				kvvpRow.d2300 = document.getElementById('d2300_' + index).textContent;
				kvvpRow.d3100 = document.getElementById('d3100_' + index).textContent;
				kvvpRow.d4200 = document.getElementById('d4200_' + index).textContent;
				kvvpRow.d5600 = document.getElementById('d5600_' + index).textContent;
				kvvpRow.d7600 = document.getElementById('d7600_' + index).textContent;
				kvvpRow.d10200 = document.getElementById('d10200_' + index).textContent;
				kvvpRow.d13800 = document.getElementById('d13800_' + index).textContent;
				kvvpRow.d18500 = document.getElementById('d18500_' + index).textContent;
				
				kvvp.push(kvvpRow);
        		index ++;
        	}
        	
        	if(!checkKvvpData(kvvp)){
        		console.log("Invalid kvvv");
        		return false;
        	}
        	
        	saveKvvvToDatabase(kvvp);
            return true;
        }
        
        function saveKvvvToDatabase(kvvp) {
        	DiagnosticGroupService.saveKvvpData(self.selectedGroup.idDg, kvvp)
                .then(
                    function (response) {
                        self.done = true;
                        self.newDiagnosticGroup={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while saving kvvp');
                        self.errorMessage = 'Error while saving kvvp: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
        
        function checkKvvpData(kvvv) {
        	var res = true;
        	for(var i = 0; i<kvvv.length; i++) {
        		res &= checkKvvvValue(kvvv[i].d2, i, "2");
        		res &= checkKvvvValue(kvvv[i].d3, i, "3");
        		res &= checkKvvvValue(kvvv[i].d4, i, "4");
        		res &= checkKvvvValue(kvvv[i].d5, i, "5");
        		res &= checkKvvvValue(kvvv[i].d6, i, "6");
        		res &= checkKvvvValue(kvvv[i].d8, i, "8");
        		res &= checkKvvvValue(kvvv[i].d11, i, "11");
        		res &= checkKvvvValue(kvvv[i].d15, i, "15");
        		res &= checkKvvvValue(kvvv[i].d20, i, "20");
        		res &= checkKvvvValue(kvvv[i].d26, i, "26");
        		res &= checkKvvvValue(kvvv[i].d36, i, "36");
        		res &= checkKvvvValue(kvvv[i].d40, i, "40");
        		res &= checkKvvvValue(kvvv[i].d65, i, "65");
        		res &= checkKvvvValue(kvvv[i].d85, i, "85");
        		res &= checkKvvvValue(kvvv[i].d120, i, "120");
        		res &= checkKvvvValue(kvvv[i].d150, i, "150");
        		res &= checkKvvvValue(kvvv[i].d210, i, "210");
        		res &= checkKvvvValue(kvvv[i].d290, i, "290");
        		res &= checkKvvvValue(kvvv[i].d300, i, "300");
        		res &= checkKvvvValue(kvvv[i].d520, i, "520");
        		res &= checkKvvvValue(kvvv[i].d700, i, "700");
        		res &= checkKvvvValue(kvvv[i].d950, i, "950");
        		res &= checkKvvvValue(kvvv[i].d1300, i, "1300");
        		res &= checkKvvvValue(kvvv[i].d1700, i, "1700");
        		res &= checkKvvvValue(kvvv[i].d2300, i, "2300");
        		res &= checkKvvvValue(kvvv[i].d3100, i, "3100");
        		res &= checkKvvvValue(kvvv[i].d4200, i, "4200");
        		res &= checkKvvvValue(kvvv[i].d5600, i, "5600");
        		res &= checkKvvvValue(kvvv[i].d7600, i, "7600");
        		res &= checkKvvvValue(kvvv[i].d10200, i, "10200");
        		res &= checkKvvvValue(kvvv[i].d13800, i, "13800");
        		res &= checkKvvvValue(kvvv[i].d18500, i, "18500");
        	}
        	return res;
        }
        
        function checkKvvvValue(value, i, varName) {
        	var arr = value.split(".");
        	if(value === undefined || value === null 
        			|| value == "") {
        		setErrorMessage(varName, i, value);
        		return false;
        	}
        	
        	var regex = new RegExp('^(([0-9])+)$');
        	if(arr.length == 1) {
        		if(regex.test(arr[0])) {
        			return true;
        		} else {
        			setErrorMessage(varName, i, value);
        			return false;
        		}
        	}
        	
        	if(arr.length == 2) {
        		if(regex.test(arr[0]) && regex.test(arr[1])) {
        			return true;
        		} else {
        			setErrorMessage(varName, i, value);
        			return false;
        		}
        	}
        	
        	setErrorMessage(varName, i, value);
        	return false;
        }
        
        function setErrorMessage(varName, i, value) {
        	self.errorMessage = "Can not save KVVV, kvvv variable " + varName 
        		+ " in row " + i + " has incorrect value: " + value;
        }
        
        function uploadKvvpFile() {
        	if(self.selectedGroup === undefined || self.selectedGroup === null
        			|| self.selectedGroup.idDg === undefined 
        			|| self.selectedGroup.idDg === null) {
        		self.errorMessage = "Diagnostic group is not selected";
        		return;
        	}
        	
    		var f = document.getElementById('file').files[0];
    		if(f === undefined || f === null) {
    			self.errorMessage = "File is not selected";
        		return;
    		}
    		
    		DiagnosticGroupService.uploadKvvpFile(self.selectedGroup.idDg, f, self);
    	}
    	
    	function clearTable() {
    		self.kvvp = new Array(0);
    	}
    	
    	function deleteRows() {
    		var index = 1;
    		var indToRemove = 1;
        	while(document.getElementById('delete_checkbox_' + index) !== undefined 
        		&& document.getElementById('delete_checkbox_' + index) !== null) {
        		var isCheckedForDelete = document.getElementById('delete_checkbox_' + index).checked;
        		if(isCheckedForDelete) {
        			self.kvvp.splice(indToRemove - 1, 1);
        		} else {
        			indToRemove++;
        		}
        		index ++;
        	}
    	}
    	
    	function addRow() {
    		if(self.selectedGroup === undefined || self.selectedGroup === null || self.selectedGroup.idDg === undefined) {
            	return;
            }
    		var newRow = new Object();
    		newRow.d2 = '';
    		newRow.d3 = '';
			newRow.d4 = '';
			newRow.d5 = '';
			newRow.d6 = '';
			newRow.d8 = '';
			newRow.d11 = '';
			newRow.d15 = '';
			newRow.d20 = '';
			newRow.d26 = '';
			newRow.d36 = '';
			newRow.d40 = '';
			newRow.d65 = '';
			newRow.d85 = '';
			newRow.d120 = '';
			newRow.d150 = '';
			newRow.d210 = '';
			newRow.d290 = '';
			newRow.d300 = '';
			newRow.d520 = '';
			newRow.d700 = '';
			newRow.d950 = '';
			newRow.d1300 = '';
			newRow.d1700 = '';
			newRow.d2300 = '';
			newRow.d3100 = '';
			newRow.d4200 = '';
			newRow.d5600 = '';
			newRow.d7600 = '';
			newRow.d10200 = '';
			newRow.d13800 = '';
			newRow.d18500 = '';
			
			
			if(self.kvvp.length === undefined || self.kvvp.length === null || self.kvvp.length == 0) {
				self.kvvp = new Array(1);
				return;
			}
			self.kvvp.push(newRow);
    	}
    	
    	function startDiscriminantAnalysis() {
    		DiagnosticGroupService.startDiscriminantAnalysis()
                .then(
                    function (response) {
                        self.successMessage = 'Analysis started successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.newDiagnosticGroup={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while starting DA');
                        self.errorMessage = 'Error while starting DA: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
                return true;
    	}
    }
]);