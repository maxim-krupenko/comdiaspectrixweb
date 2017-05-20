<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Specific User </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.hospitalStaff.idHs"/>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="hsLogin">Login</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.hospitalStaff.login" id="hsLogin"
                                       class="username form-control input-sm" placeholder="Enter your login" required
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="hsPassword">Password</label>
                            <div class="col-md-7">
                                <input type="password" ng-model="ctrl.hospitalStaff.pass" id="hsPassword"
                                       class="password-field form-control input-sm" placeholder="Enter your password"
                                       required
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="hsFIO">FIO</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.hospitalStaff.HSFIO" id="hsFIO"
                                       class="username form-control input-sm" placeholder="Enter your FIO"
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit" value="{{!ctrl.hospitalStaff.idHs ? 'Add' : 'Update'}}"
                                   class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm"
                                    ng-disabled="myForm.$pristine">Reset Form
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Hospital Staff</span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>IDHS</th>
                        <th>LOGIN</th>
                        <th>PASSWORD</th>
                        <th>FIO</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="hs in ctrl.getAllHospitalStaffs()">
                        <td>{{hs.idHs}}</td>
                        <td>{{hs.login}}</td>
                        <td>{{hs.pass}}</td>
                        <td>{{hs.HSFIO}}</td>
                        <td>
                            <button type="button" ng-click="ctrl.editHospitalStaff(hs.idHs)"
                                    class="btn btn-success custom-width">
                                Edit
                            </button>
                        </td>
                        <td>
                            <button type="button" ng-click="ctrl.removeHospitalStaff(hs.idHs)"
                                    class="btn btn-danger custom-width">
                                Remove
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>