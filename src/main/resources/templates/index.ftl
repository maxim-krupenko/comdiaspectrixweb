<!DOCTYPE html>

<html lang="en" ng-app="crudApp">
<head>
    <title>${title}</title>
    <script src="js/lib/angular.min.js" ></script>
    <script src="js/lib/angular-ui-router.min.js" ></script>
    <script src="js/lib/localforage.min.js" ></script>
    <script src="js/lib/ngStorage.min.js"></script>
    <script src="js/lib/jquery-3.2.1.min.js"></script>
    <script src="js/lib/bootstrap.min.js"></script>
    <script src="js/app/app.js"></script>
    <script src="js/app/services/HospitalStaffService.js"></script>
    <script src="js/app/controllers/HospitalStaffController.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet"/>
    <link href="css/app.css" rel="stylesheet"/>
</head>
<body>
<div class="header-panel" style="height: 10%; width: 100%; text-align: center">
    <div class="generic-container">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Диаспектрикс</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="#">Link</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">Страницы<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Доктора</a></li>
                                <li><a href="#">Пациенты</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Link</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>
<div ui-view class="parentbox"></div>
</body>
</html>