
var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/diaspectrixweb',
    USER_SERVICE_API : 'http://localhost:8080/diaspectrxiweb/api/hospital-staff/',
    HOSPITAL_STAFF_SERVICE_API : 'http://localhost:8080/diaspectrixweb/api/hospital-staff/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'HospitalStaffController',
                controllerAs:'ctrl',
                resolve: {
                    hospitalStaffs: function ($q, HospitalStaffService) {
                        console.log('Load all hospitalStaffs');
                        var deferred = $q.defer();
                        HospitalStaffService.loadAllHospitalStaffs().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);