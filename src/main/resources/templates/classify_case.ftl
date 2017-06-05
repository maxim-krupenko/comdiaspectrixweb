<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Diagnostic</span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                
<table border=0>
<tr ng-repeat="varNameLine in ctrl.getVariablesNames()">
<td ng-repeat="varName in varNameLine">
<label class="col-md-2 control-label" for="valD{{varName}}">D{{varName}}</label>
<div class="col-md-7">
	<input type="text" id="valD{{varName}}" class="username form-control input-sm" required/>
</div>
</td>
</tr>
</table>

<form id="fileForm" method="POST" enctype="multipart/form-data" ng-submit="ctrl.uploadKvvpFile()" name="fileForm" class="form-horizontal"> 
	<input type="file" id="file">
	<input type="submit" value="Upload" class="btn btn-primary btn-sm">
</form>

<div class="form-actions floatRight">
<form id="classifyForm" ng-submit="ctrl.classify()" class="form-horizontal"> 
	<input type="submit" value="Diagnose" class="btn btn-primary btn-sm">
</form>
</div>
            </div>
        </div>
    </div>
</div>