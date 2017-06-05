'use strict';

angular.module('crudApp').factory('DiagnosticGroupService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
            	loadAllDiagnosticGroups: loadAllDiagnosticGroups,
            	getAllGroups: getAllGroups,
            	getKvvvForGroup: getKvvvForGroup,
            	createDiagnosticGroup: createDiagnosticGroup,
            	removeDiagnosticGroup: removeDiagnosticGroup,
            	updateDiagnosticGroup: updateDiagnosticGroup,
            	saveKvvpData: saveKvvpData,
            	uploadKvvpFile: uploadKvvpFile,
            	startDiscriminantAnalysis: startDiscriminantAnalysis
            };

            return factory;

            function getAllGroups(){
            	console.log('fetched ' + $localStorage.diagnosticGroups.length);
                return $localStorage.diagnosticGroups;
            }
            
            function loadAllDiagnosticGroups() {
                console.log('Fetching all groups');
                var deferred = $q.defer();
                $http.get(urls.DIAGNOSTIC_GROUP_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all diagnosticGroups');
                            $localStorage.diagnosticGroups = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading diagnosticGroups');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getKvvvForGroup(groupId, ctrl) {
                var deferred = $q.defer();
                $http.get(urls.KVVV_SERVICE_API + "group/" + groupId)
                    .then(
                        function (response) {
                            console.log('Fetched successfully kvvv ' + response.data.length);
                            ctrl.kvvp = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading kvvp ' + errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                
                return deferred.promise;
            }
            
            function removeDiagnosticGroup(id) {
                console.log('Removing DiagnosticGroup with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.DIAGNOSTIC_GROUP_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllDiagnosticGroups();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing DiagnosticGroup with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function createDiagnosticGroup(group) {
                var deferred = $q.defer();
                $http.post(urls.DIAGNOSTIC_GROUP_SERVICE_API, group)
                    .then(
                        function (response) {
                            loadAllDiagnosticGroups();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while creating DiagnosticGroup : '+errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function updateDiagnosticGroup(group) {
                var deferred = $q.defer();
                var id = group.idDg;
                $http.put(urls.DIAGNOSTIC_GROUP_SERVICE_API + id, group)
                    .then(
                        function (response) {
                            loadAllDiagnosticGroups();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating DiagnosticGroup : '+errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function saveKvvpData(groupId, kvvp) {
            	var deferred = $q.defer();
            	kvvp.idKv = null;
                $http.post(urls.KVVV_SERVICE_API + "group/" + groupId, kvvp)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while saving kvvp : '+errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function uploadKvvpFile(groupId, file, ctrl) {
            	var formData=new FormData();
         		formData.append("data", groupId);
         		formData.append("file", file, file.name);
         
				$http({
                  method: 'POST',
                  url: urls.DIAGNOSTIC_GROUP_SERVICE_API + "uploadFile",
                  headers: {'Content-Type': undefined},
                  data:  formData
                })
                .success(function(data, status) {
                	ctrl.successMessage = "File uploaded successfully";
                })
                .error(function(data, status) {
                	ctrl.errorMessage = "Error while uploading file: " + data.errorMessage;
                });
            }
            
            function startDiscriminantAnalysis() {
            	var deferred = $q.defer();
                $http.get("http://localhost:8080/DiaspectrixWeb/api/diagnostic-group-startDiscriminantAnalysis")
                    .then(
                        function (response) {
                            loadAllDiagnosticGroups();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while starting DA : '+errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]);