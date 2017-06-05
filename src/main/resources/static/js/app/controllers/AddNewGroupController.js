'use strict';

angular.module('crudApp').controller('AddNewGroupController',
    ['DiagnosticGroupService', '$scope',  function(DiagnosticGroupService, $scope) {

        var self = this;
        
        self.successMessage = '';
        self.errorMessage = '';
        
		self.submit = submit;
        
        function submit() {
        	createDiagnosticGroup(self.newDiagnosticGroup);
        }
        
        function createDiagnosticGroup(group) {
            DiagnosticGroupService.createDiagnosticGroup(group)
                .then(
                    function (response) {
                        self.successMessage = 'Diagnostic Group created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.newDiagnosticGroup={};
                    },
                    function (errResponse) {
                        self.errorMessage = 'Error while creating Diagnostic Group: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
    }
]);