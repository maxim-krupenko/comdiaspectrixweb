<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Выбранный пациент</span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.patient.idP"/>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="nMedCard">Номер медкарты</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.patient.login" id="nMedCard"
                                       class="username form-control input-sm"
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="pFio">ФИО</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.patient.fio" id="pFio"
                                       class="username form-control input-sm"
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="gender">Пол</label>
                            <div class="col-md-7">
                                <input type="date" ng-model="ctrl.patient.gender" id="gender"
                                       class="username form-control input-sm"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="bDay">Дата рождения</label>
                            <div class="col-md-7">
                                <input type="date" ng-model="ctrl.patient.bDay" id="bDay"
                                       class="username form-control input-sm"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="accDate">Дата посещения</label>
                            <div class="col-md-7">
                                <input type="date" ng-model="ctrl.patient.accDate" id="accDate"
                                       class="username form-control input-sm"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="disease">Диагноз</label>
                            <div class="col-md-7">
                                <input type="date" ng-model="ctrl.patient.gender" id="disease"
                                       class="username form-control input-sm"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit" value="{{!ctrl.patient.idP ? 'Добавить' : 'Сохранить'}}"
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
        <div class="panel-heading"><span class="lead">Список пациентов</span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Номер медкарты</th>
                        <th>ФИО</th>
                        <th>Пол</th>
                        <th>Дата рождения</th>
                        <th>Дата посещения</th>
                        <th>Диагноз</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="pat in ctrl.getAllPatients()">
                        <td>{{pat.idP}}</td>
                        <td>{{pat.nMedCard}}</td>
                        <td>{{pat.fio}}</td>
                        <td>{{pat.gender}}</td>
                        <td>{{pat.bDay}}</td>
                        <td>{{pat.accDate}}</td>
                        <td>{{pat.disease}}</td>
                        <td>
                            <button type="button" ng-click="ctrl.editPatient(pat.idP)"
                                    class="btn btn-success custom-width" style="text-align:center;">Изменить
                            </button>
                        </td>
                        <td>
                            <button type="button" ng-click="ctrl.removePatient(pat.idP)"
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