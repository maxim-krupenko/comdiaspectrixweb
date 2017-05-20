'use strict';

angular.module('crudApp').factory('HospitalStaffService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllHospitalStaffs: loadAllHospitalStaffs,
                getAllHospitalStaffs: getAllHospitalStaffs,
                getHospitalStaff: getHospitalStaff,
                createHospitalStaff: createHospitalStaff,
                updateHospitalStaff: updateHospitalStaff,
                removeHospitalStaff: removeHospitalStaff
            };

            return factory;

            function loadAllHospitalStaffs() {
                console.log('Fetching all hospitalStaffs');
                var deferred = $q.defer();
                $http.get(urls.HOSPITAL_STAFF_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all hospitalStaffs');
                            $localStorage.hospitalStaffs = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading hospitalStaffs');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllHospitalStaffs(){
                return $localStorage.hospitalStaffs;
            }

            function getHospitalStaff(id) {
                console.log('Fetching HospitalStaff with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.HOSPITAL_STAFF_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully HospitalStaff with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading hospitalStaff with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createHospitalStaff(hospitalStaff) {
                console.log('Creating HospitalStaff');
                var deferred = $q.defer();
                $http.post(urls.HOSPITAL_STAFF_SERVICE_API, hospitalStaff)
                    .then(
                        function (response) {
                            loadAllHospitalStaffs();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while creating HospitalStaff : '+errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateHospitalStaff(hospitalStaff, id) {
                console.log('Updating HospitalStaff with id '+id);
                var deferred = $q.defer();
                $http.put(urls.HOSPITAL_STAFF_SERVICE_API + id, hospitalStaff)
                    .then(
                        function (response) {
                            loadAllHospitalStaffs();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating HospitalStaff with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeHospitalStaff(id) {
                console.log('Removing HospitalStaff with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.HOSPITAL_STAFF_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllHospitalStaffs();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing HospitalStaff with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);