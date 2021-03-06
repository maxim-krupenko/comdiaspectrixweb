
var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/diaspectrixweb',
    USER_SERVICE_API : 'http://localhost:8080/diaspectrxiweb/api/hospital-staff/',
    HOSPITAL_STAFF_SERVICE_API : 'http://localhost:8080/diaspectrixweb/api/hospital-staff/',
    PATIENT_SERVICE_API: 'http://localhost:8080/diaspectrixweb/api/patient/',
    DIAGNOSTIC_GROUP_SERVICE_API : 'http://localhost:8080/DiaspectrixWeb/api/diagnostic-group/',
    KVVV_SERVICE_API : 'http://localhost:8080/DiaspectrixWeb/api/kvvv/',
    CLASSIFICATION_SERVICE_API : 'http://localhost:8080/DiaspectrixWeb/api/classification/'
    
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
                    patients: function ($q, HospitalStaffService) {
                        console.log('Load all patientss');
                        var deferred = $q.defer();
                        HospitalStaffService.loadAllHospitalStaffs().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })
            // .state({
            //     name: 'about',
            //     url: '/about',
            //     template: '<h3>Its the UI-Router hello world app!</h3>'
            // });
            .state('patients', {
                url: '/patients',
                templateUrl: 'partials/patients',
                controller:'PatientsController',
                controllerAs:'ctrl',
                resolve: {
                    patients: function ($q, PatientsService) {
                        console.log('Load all patientss');
                        var deferred = $q.defer();
                        PatientsService.loadAllPatients().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })
            .state('diagnosticGroup', {
                url: '/diagnosticGroup',
                templateUrl: 'partials/diagnostic_group',
                controller:'DiagnosticGroupController',
                controllerAs:'ctrl',
                resolve: {
                    diagnosticGroups: function ($q, DiagnosticGroupService) {
                        console.log('Load all diagnosticGroups');
                        var deferred = $q.defer();
                        DiagnosticGroupService.loadAllDiagnosticGroups().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            }).state('classification', {
                url: '/classification',
                templateUrl: 'partials/classify_case',
                controller:'ClassificationController',
                controllerAs:'ctrl',
                resolve: {}
            }).state('addNewGroup', {
                url: '/addNewGroup',
                templateUrl: 'partials/add_new_group',
                controller:'AddNewGroupController',
                controllerAs:'ctrl',
                resolve: {}
            });
        $urlRouterProvider.otherwise('/');
    }]);

/*
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
 */