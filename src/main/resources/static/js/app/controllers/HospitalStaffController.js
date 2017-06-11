'use strict';

angular.module('crudApp').controller('HospitalStaffController',
    ['HospitalStaffService', '$scope', '$state',  function(HospitalStaffService, $scope, $state) {

        var self = this;
        self.hospitalStaff = {};
        self.hospitalStaffs=[];

        self.submit = submit;
        self.getAllHospitalStaffs = getAllHospitalStaffs;
        self.createHospitalStaff = createHospitalStaff;
        self.updateHospitalStaff = updateHospitalStaff;
        self.removeHospitalStaff = removeHospitalStaff;
        self.editHospitalStaff = editHospitalStaff;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.hospitalStaff.idHs === undefined || self.hospitalStaff.idHs === null) {
                console.log('Saving New HospitalStaff', self.hospitalStaff);
                createHospitalStaff(self.hospitalStaff);
            } else {
                updateHospitalStaff(self.hospitalStaff, self.hospitalStaff.idHs);
                console.log('HospitalStaff updated with idHs ', self.hospitalStaff.idHs);
            }
        }

        function createHospitalStaff(hospitalStaff) {
            console.log('About to create hospitalStaff');
            HospitalStaffService.createHospitalStaff(hospitalStaff)
                .then(
                    function (response) {
                        console.log('HospitalStaff created successfully');
                        self.successMessage = 'HospitalStaff created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.hospitalStaff={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating HospitalStaff');
                        self.errorMessage = 'Error while creating HospitalStaff: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateHospitalStaff(hospitalStaff, idHs){
            console.log('About to update hospitalStaff');
            HospitalStaffService.updateHospitalStaff(hospitalStaff, idHs)
                .then(
                    function (response){
                        console.log('HospitalStaff updated successfully');
                        self.successMessage='HospitalStaff updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating HospitalStaff');
                        self.errorMessage='Error while updating HospitalStaff '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeHospitalStaff(idHs){
            console.log('About to remove HospitalStaff with idHs '+idHs);
            HospitalStaffService.removeHospitalStaff(idHs)
                .then(
                    function(){
                        console.log('HospitalStaff '+idHs + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing hospitalStaff '+idHs +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllHospitalStaffs(){
            return HospitalStaffService.getAllHospitalStaffs();
        }

        function editHospitalStaff(idHs) {
            self.successMessage='';
            self.errorMessage='';
            HospitalStaffService.getHospitalStaff(idHs).then(
                function (hospitalStaff) {
                    self.hospitalStaff = hospitalStaff;
                },
                function (errResponse) {
                    console.error('Error while removing hospitalStaff ' + idHs + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.hospitalStaff={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);