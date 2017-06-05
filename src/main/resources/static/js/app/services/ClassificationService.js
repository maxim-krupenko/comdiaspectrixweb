'use strict';

angular.module('crudApp').factory('ClassificationService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
            	readKvvvExcel: readKvvvExcel,
            	getKvvv: getKvvv,
            	classify: classify
            };

            return factory;
            
            function classify(kvvv) {
                var deferred = $q.defer();
                $http.post(urls.CLASSIFICATION_SERVICE_API, kvvv)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                            console.log(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while creating DiagnosticGroup : '+errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function readKvvvExcel(file) {    
            	var formData=new FormData();
         		formData.append("file", file, file.name);
         		
         		var deferred = $q.defer();
				$http({
                  method: 'POST',
                  url: urls.CLASSIFICATION_SERVICE_API + "uploadFile",
                  headers: {'Content-Type': undefined},
                  data:  formData
                })
                .success(function(data, status) {
                	$localStorage.kvvp = data;
                	deferred.resolve(data);
                })
                .error(function(data, status) {
                });
                return deferred.promise;
            }
            
            function getKvvv () {
            	return $localStorage.kvvp;
            }
        }
    ]);