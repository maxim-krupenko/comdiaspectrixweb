<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Выбранный доктор</span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.hospitalStaff.idHs"/>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="hsLogin">Логин</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.hospitalStaff.login" id="hsLogin"
                                       class="username form-control input-sm" placeholder="Enter your login"
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="hsPassword">Пароль</label>
                            <div class="col-md-7">
                                <input type="password" ng-model="ctrl.hospitalStaff.pass" id="hsPassword"
                                       class="password-field form-control input-sm" placeholder="Enter your password"
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="hsfio">ФИО</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.hospitalStaff.hsfio" id="hsfio"
                                       class="username form-control input-sm" placeholder="Enter your FIO"
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="bDay">Дата рождения</label>
                            <div class="col-md-7">
                                <input type="date" ng-model="ctrl.hospitalStaff.bDay" id="bDay"
                                       class="username form-control input-sm" placeholder="Enter your birthday"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit" value="{{!ctrl.hospitalStaff.idHs ? 'Добавить' : 'Сохранить'}}"
                                   class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm"
                                    ng-disabled="myForm.$pristine">Сбросить ввод
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Список докторов</span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Логин</th>
                        <th>Пароль</th>
                        <th>ФИО</th>
                        <th>Дата рождения</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="hs in ctrl.getAllHospitalStaffs()">
                        <td>{{hs.idHs}}</td>
                        <td>{{hs.login}}</td>
                        <td>{{hs.pass}}</td>
                        <td>{{hs.hsfio}}</td>
                        <td>{{hs.bDay}}</td>
                        <td>
                            <button type="button" ng-click="ctrl.editHospitalStaff(hs.idHs)"
                                    class="btn btn-success custom-width" style="text-align:center;">Изменить
                            </button>
                        </td>
                        <td>
                            <button type="button" ng-click="ctrl.removeHospitalStaff(hs.idHs)"
                                    class="btn btn-danger custom-width" style="text-align:center;">Удалить
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>