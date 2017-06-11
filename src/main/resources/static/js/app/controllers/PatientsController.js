'use strict';

angular.module('crudApp').controller('PatientsController',
    ['PatientsService', '$scope',  function(PatientsService, $scope) {

        var self = this;
        self.patient = {};
        self.patients=[];

        self.submit = submit;
        self.getAllPatients = getAllPatients;
        self.createPatient = createPatient;
        self.updatePatient = updatePatient;
        self.removePatient = removePatient;
        self.editPatient = editPatient;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.patient.idHs === undefined || self.patient.idHs === null) {
                console.log('Saving New Patient', self.patient);
                createPatient(self.patient);
            } else {
                updatePatient(self.patient, self.patient.idHs);
                console.log('Patient updated with idHs ', self.patient.idHs);
            }
        }

        function createPatient(patient) {
            console.log('About to create patient');
            PatientsService.createPatient(patient)
                .then(
                    function (response) {
                        console.log('Patient created successfully');
                        self.successMessage = 'Patient created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.patient={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Patient');
                        self.errorMessage = 'Error while creating Patient: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updatePatient(patient, idHs){
            console.log('About to update patient');
            PatientsService.updatePatient(patient, idHs)
                .then(
                    function (response){
                        console.log('Patient updated successfully');
                        self.successMessage='Patient updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Patient');
                        self.errorMessage='Error while updating Patient '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removePatient(idHs){
            console.log('About to remove Patient with idHs '+idHs);
            PatientsService.removePatient(idHs)
                .then(
                    function(){
                        console.log('Patient '+idHs + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing patient '+idHs +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllPatients(){
            return PatientsService.getAllPatients();
        }

        function editPatient(idHs) {
            self.successMessage='';
            self.errorMessage='';
            PatientsService.getPatient(idHs).then(
                function (patient) {
                    self.patient = patient;
                },
                function (errResponse) {
                    console.error('Error while removing patient ' + idHs + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.patient={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);