<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">New Diagnostic Group</span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="newGroupName">Group name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.newDiagnosticGroup.groupName" id="newGroupName"
                                       class="form-control input-sm" placeholder="Enter new group name" required
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="newGroupDescr">Description</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.newDiagnosticGroup.description" id="newGroupDescr"
                                       class="form-control input-sm" placeholder="Enter new group description"
                                       required
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit" value="Add"
                                   class="btn btn-primary btn-sm"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>