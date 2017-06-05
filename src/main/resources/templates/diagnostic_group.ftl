<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Diagnostic Group</span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                
                	<div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" for="diagGroup">Diagnostic group</label>
                            <div class="col-md-7">
                                <select class="form-control" name="diagGroup" 
                                	id="diagGroup" ng-model="group" 
                                	ng-change="ctrl.diagGroupSelected(group)">
                                	<option style="display:none" value="">Select group</option>
                                	<option ng-repeat="group in ctrl.getAllGroups()" value="{{group}}">{{group.groupName}}</option>
                				</select>
                            </div>
                            
                            <div class="form-actions floatRight">
                            	<form ng-submit="ctrl.deleteGroup()" name="myForm" class="form-horizontal">
                            		<input type="submit" value="Delete" class="btn btn-primary btn-sm"/>
                            	</form>
                            </div>
                            
                            <div class="form-actions floatRight">
                            	<form ng-submit="ctrl.updateDiagnosticGroup()" name="myForm112" class="form-horizontal">                   
                            		<input type="submit" value="Save" class="btn btn-primary btn-sm">
                            	</form>
                            </div>
                            
                            <div class="form-actions floatRight">
                            	<form ng-submit="ctrl.startDiscriminantAnalysis()" name="myForm11222" class="form-horizontal">                   
                            		<input type="submit" value="Start Analysis" class="btn btn-primary btn-sm">
                            	</form>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                        	<form ng-submit="ctrl.updateDiagnosticGroup()" name="myForm11" class="form-horizontal">
                            	<div class="row">
                        			<div class="form-group col-md-12">
                            			<label class="col-md-2 control-label" for="currentGroupName">Group Name</label>
                            			<div class="col-md-7">
                            				<input type="text" ng-model="ctrl.selectedGroup.groupName" id="currentGroupName"
                                       			class="form-control input-sm" placeholder="" required
                                       			ng-minlength="3"/>
                                        </div>
                                    </div>
                               	</div>
                                       
                            	<div class="row">
                        			<div class="form-group col-md-12">
                            			<label class="col-md-2 control-label" for="currentGroupDescription">Description</label>
                            			<div class="col-md-7">
                            				<input type="text" ng-model="ctrl.selectedGroup.description" id="currentGroupDescription"
                                       			class="form-control input-sm" placeholder="" required
                                       			ng-minlength="3"/>
                                       	</div>
                                    </div>
                               	</div>
							</form>
						</div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                        	<div class="form-actions floatRight">
                				<form id="fileForm" method="POST" enctype="multipart/form-data" 
									ng-submit="ctrl.uploadKvvpFile()" name="fileForm" class="form-horizontal"> 
    								<input type="submit" value="Upload" class="btn btn-primary btn-sm">
                				</form>
                			</div>
                        	<div class="form-actions floatRight">
                    			<input type="file" id="file">
                			</div>
                			
                		</div>
                	</div>
                	
                	
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                    		<div class="form-actions floatRight">
                            	<form ng-submit="ctrl.clearTable()" name="myForm1122" class="form-horizontal">                   
                            		<input type="submit" value="Clear table" class="btn btn-primary btn-sm">
                            	</form>
                            </div>
                            
                    		<div class="form-actions floatRight">
                            	<form ng-submit="ctrl.deleteRows()" name="myForm1122" class="form-horizontal">                   
                            		<input type="submit" value="Remove row" class="btn btn-primary btn-sm">
                            	</form>
                            </div>
                            
                    		<div class="form-actions floatRight">
                            	<form ng-submit="ctrl.addRow()" name="myForm9" class="form-horizontal">
                            		<input type="submit" value="Add row" class="btn btn-primary btn-sm"/>
                            	</form>
                            </div>
                   		</div>
                    </div>
                    
					<div class="container">
  						<div id="table" class="table-editable">
  							<div style="width:100%; height:300px; overflow:auto;">
  							<table class="table">
  								<tr>
    								<th></th>
  									<th>№</th>
  									<th ng-repeat="varName in ctrl.getVariablesNames()">{{varName}}</th>
  								</tr>
   								<tr ng-repeat="kvvp in ctrl.getKvvvForGroup()">
    								<td><input type="checkbox" id="delete_checkbox_{{$index + 1}}"></td>
									<td id="kvvp_row_{{$index + 1}}">{{$index + 1}}</td>
									<td contenteditable="true" id="d2_{{$index + 1}}">{{kvvp.d2}}</td>
									<td contenteditable="true" id="d3_{{$index + 1}}">{{kvvp.d3}}</td>
									<td contenteditable="true" id="d4_{{$index + 1}}">{{kvvp.d4}}</td>
									<td contenteditable="true" id="d5_{{$index + 1}}">{{kvvp.d5}}</td>
									<td contenteditable="true" id="d6_{{$index + 1}}">{{kvvp.d6}}</td>
									<td contenteditable="true" id="d8_{{$index + 1}}">{{kvvp.d8}}</td>
									<td contenteditable="true" id="d11_{{$index + 1}}">{{kvvp.d11}}</td>
									<td contenteditable="true" id="d15_{{$index + 1}}">{{kvvp.d15}}</td>
									<td contenteditable="true" id="d20_{{$index + 1}}">{{kvvp.d20}}</td>
									<td contenteditable="true" id="d26_{{$index + 1}}">{{kvvp.d26}}</td>
									<td contenteditable="true" id="d36_{{$index + 1}}">{{kvvp.d36}}</td>
									<td contenteditable="true" id="d40_{{$index + 1}}">{{kvvp.d40}}</td>
									<td contenteditable="true" id="d65_{{$index + 1}}">{{kvvp.d65}}</td>
									<td contenteditable="true" id="d85_{{$index + 1}}">{{kvvp.d85}}</td>
									<td contenteditable="true" id="d120_{{$index + 1}}">{{kvvp.d120}}</td>
									<td contenteditable="true" id="d150_{{$index + 1}}">{{kvvp.d150}}</td>
									<td contenteditable="true" id="d210_{{$index + 1}}">{{kvvp.d210}}</td>
									<td contenteditable="true" id="d290_{{$index + 1}}">{{kvvp.d290}}</td>
									<td contenteditable="true" id="d300_{{$index + 1}}">{{kvvp.d300}}</td>
									<td contenteditable="true" id="d520_{{$index + 1}}">{{kvvp.d520}}</td>
									<td contenteditable="true" id="d700_{{$index + 1}}">{{kvvp.d700}}</td>
									<td contenteditable="true" id="d950_{{$index + 1}}">{{kvvp.d950}}</td>
									<td contenteditable="true" id="d1300_{{$index + 1}}">{{kvvp.d1300}}</td>
									<td contenteditable="true" id="d1700_{{$index + 1}}">{{kvvp.d1700}}</td>
									<td contenteditable="true" id="d2300_{{$index + 1}}">{{kvvp.d2300}}</td>
									<td contenteditable="true" id="d3100_{{$index + 1}}">{{kvvp.d3100}}</td>
									<td contenteditable="true" id="d4200_{{$index + 1}}">{{kvvp.d4200}}</td>
									<td contenteditable="true" id="d5600_{{$index + 1}}">{{kvvp.d5600}}</td>
									<td contenteditable="true" id="d7600_{{$index + 1}}">{{kvvp.d7600}}</td>
									<td contenteditable="true" id="d10200_{{$index + 1}}">{{kvvp.d10200}}</td>
									<td contenteditable="true" id="d13800_{{$index + 1}}">{{kvvp.d13800}}</td>
									<td contenteditable="true" id="d18500_{{$index + 1}}">{{kvvp.d18500}}</td>
  								<tr>
							</table>
							</div>
  						</div>
  					</div>		
			</div>
		</div>
	</div>
</div>