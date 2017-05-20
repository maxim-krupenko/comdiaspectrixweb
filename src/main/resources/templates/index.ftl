<!DOCTYPE html>

<html lang="en" ng-app="crudApp">
<head>
    <title>${title}</title>
    <script src="js/lib/angular.min.js" ></script>
    <script src="js/lib/angular-ui-router.min.js" ></script>
    <script src="js/lib/localforage.min.js" ></script>
    <script src="js/lib/ngStorage.min.js"></script>
    <script src="js/app/app.js"></script>
    <script src="js/app/services/HospitalStaffService.js"></script>
    <script src="js/app/controllers/HospitalStaffController.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet"/>
    <link href="css/app.css" rel="stylesheet"/>
</head>
<body>

<div ui-view class="parentbox"></div>
</body>
</html>