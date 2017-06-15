<div class="col-md-6 col-md-offset-3">
    <h2>Login</h2>
    <form name="form" ng-submit="vm.login()" role="form">
        <div class="form-group" ng-class="{ 'has-error': form.username.$dirty && form.username.$error.required }">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" class="form-control" ng-model="vm.username" required />
        </div>
        <div class="form-group" ng-class="{ 'has-error': form.password.$dirty && form.password.$error.required }">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" class="form-control" ng-model="vm.password" required />
        </div>
        <div class="form-actions">
            <button type="submit" ng-disabled="form.$invalid || vm.dataLoading" class="btn btn-primary">Login</button>
        </div>
    </form>
</div>