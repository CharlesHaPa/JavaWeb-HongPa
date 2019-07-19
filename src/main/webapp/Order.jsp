<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html ng-app="Order_Form">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width,minimum-scale=1,initial-scale=1">
  <title>OrderForm</title>
  <link href="assets/css/bootstrap.min.css" rel="stylesheet">
  <style type="text/css">
    #reset_button {
      width: 40px;
      height: 40px;
      -moz-border-radius: 50%;
      -webkit-border-radius: 50%;
      border-radius: 50%;
    }

    .list-group-item {
      position: relative;
      display: block;
      padding: 10px 15px;
      margin-top: 10px;
      margin-bottom: -1px;
      background-color: #FFFCEC;
      border: 1px solid #ddd;
    }
  </style>
</head>
<body ng-controller="FormController as orderf">
<div class="page-header">
  <h1 style="margin: 1%"> HongPa Apartment
    <small> Management System</small>
  </h1>
</div>
<div class="row" id="mainContent">
  <div class="col-sm-2 text-center" id="leftMenu">
    <h2>Menu</h2>
    <div class="panel-group" id="accordion">
      <div>
        <ul class="list-group nav nav-pills nav-stacked">
          <li class="list-group-item"><a href="index.html">List Of Rooms</a></li>
          <li class="list-group-item" style="margin-top:15px"><a href="updateClient.html">Update Client</a></li>
        </ul>
      </div>
    </div>
  </div>

  <!--Information Form -->
  <div class="col-sm-6" id="all_form" style="margin-top:50px">
    <form class="form-horizontal" name="order_form" id="order_form" novalidate>
      <div class="form-group">
        <label class="col-sm-3 control-label">Client ID</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="clientID" name="clientID" placeholder="Client ID"
                 ng-model="user.clientID" ng-disabled="true" required/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label">Client Name</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="clientName" name="clientName" placeholder="Client Name"
                 ng-model="user.name" ng-disabled="customerInfoReadOnly" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label">Gender</label>
        <div class="col-sm-8" style="margin-top: 5px">
          <input type="radio" name="clientGender" id="clientMale" ng-model="user.gender"
                 ng-disabled="customerInfoReadOnly" value="M" required>
          <label class="control-label" for="clientMale">Male</label>

          <input type="radio" name="clientGender" id="clientFemale" ng-model="user.gender"
                 ng-disabled="customerInfoReadOnly" value="F" style="margin-left: 15px" required>
          <label class="control-label" for="clientFemale">Female</label>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-3 control-label">ID_Num</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="clientIdentity" name="clientIdentity"
                 ng-disabled="customerInfoReadOnly || (order_status=='2'&& button_status=='1')==true"
                 placeholder="Client ID_Num"
                 ng-model="user.idNum"
                 ng-pattern="/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/"
                 maxlength="18" required/>
        </div>
      </div>
      <div class="alert alert-danger col-sm-8 col-sm-offset-3" ng-show="order_form.clientIdentity.$dirty
             && order_form.clientIdentity.$error.pattern"
           role="alert">
        Wrong Format!
      </div>


      <div class="form-group">
        <label class="col-sm-3 control-label">Client Telephone</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="clientTele" name="clientTele"
                 ng-disabled="customerInfoReadOnly" placeholder="Client Telephone"
                 ng-model="user.phone" ng-pattern="/^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/"
                 maxlength="11" required/>
        </div>
      </div>

      <div class="alert alert-danger col-sm-8 col-sm-offset-3" ng-show="order_form.clientTele.$dirty
             && order_form.clientTele.$error.pattern"
           role="alert">
        Wrong Format!
      </div>

      <h3 class="col-sm-8" style="margin-left:150px;margin-top:20px;margin-bottom:30px">Room Information <span
              class="label label-default">read only</span></h3>


      <div class="form-group">
        <label class="col-sm-3 control-label ">Room ID</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="roomID" name="roomID" placeholder="Room ID"
                 ng-model="room_id" required disabled/>
        </div>
      </div>


      <input type="hidden" class="form-control" id="order_status" name="order_status" ng-model="order_status"
             required disabled/>

      <input type="hidden" class="form-control" id="button_status" name="button_status" ng-model="button_status"
             required disabled/>

      <div class="form-group">
        <label class="col-sm-3 control-label">Date</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="date" name="date" ng-model="date"
                 required disabled/>
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
          <button style="margin-left: 15px;margin-top:15px" class="btn btn-primary" ng-click="reserve()"
                  ng-show="order_status=='2'&& button_status=='1'" ng-disabled="order_form.clientName.$invalid ||
				  order_form.clientGender.$invalid || order_form.clientTele.$invalid">Reserve
          </button>
          <button style="margin-left: 15px;margin-top:15px" class="btn btn-primary" ng-click="searchClient()"
                  ng-disabled="order_form.clientName.$invalid &&
				   order_form.clientIdentity.$invalid && order_form.clientTele.$invalid ">
            SearchClient
          </button>
          <button style="margin-left: 15px;margin-top:15px" class="btn btn-primary" ng-click="addClient()"
                  ng-show="button_status=='2'" ng-disabled="order_form.clientName.$invalid ||
				  order_form.clientGender.$invalid || order_form.clientIdentity.$invalid || order_form.clientTele.$invalid ">
            AddClient
          </button>
          <button style="margin-left: 15px;margin-top:15px" class="btn btn-primary" ng-click="order()"
                  ng-show="order_status=='2'&& button_status=='2'" ng-disabled="order_form.$invalid">Order
          </button>
          <button style="margin-left: 15px;margin-top:15px" class="btn btn-primary" ng-click="orderAReservedRoom()"
                  ng-show="order_status=='1'" ng-disabled="order_form.$invalid">Order
          </button>
          <button style="margin-left: 15px;margin-top:15px" class="btn btn-primary"
                  onclick="javascript:history.back(-1);">
            Return
          </button>
          <button id="reset_button" name="reset_button" style="margin-left:15px;margin-top:15px" class="btn btn-primary"
                  ng-click="reset()">
            R
          </button>
        </div>
      </div>

    </form>
  </div>

  <div class="panel panel-info col-sm-3" id="customer_buttons"
       style="margin-left:40px;margin-top:50px">
    <div class="alert alert-success col-sm-12" role="alert">
      Need more Information of the client!
    </div>
    <div class="panel-heading">Historical Customer</div>

    <div class="btn-group btn-group-lg " ng-repeat="customer in foundUsers">
      <button style="margin-top:10px"
              id="customer_button" name="customer_button" class="btn  btn-success"
              type="submit" onclick="return false;"
              ng-click="addCustomerInfo(customer.id,
		     customer.name,customer.gender,customer.phone,customer.idNum)"
      >{{customer.name+" "+customer.phone}}
      </button>
    </div>
    <div style="margin-top: 50px" class="col-sm-12">
      <img src="assets/images/room.jpg" class="img-responsive">
    </div>

  </div>


  <!-- Modal -->
  <div class="modal fade" id="returnModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                  aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">The result of your operation：</h4>
        </div>
        <div class="modal-body">
          {{return_status+data.message}}
        </div>
      </div>
    </div>
  </div>

</div>
</div>

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/angular.min.js"></script>

<script>
  angular.module('Order_Form', [])
          .controller('FormController', ['$scope', '$http',
            function ($scope, $http) {
              <%
                  String button_status= request.getParameter("button_status");
                  String order_status= request.getParameter("order_status");
                  String room_id= request.getParameter("room_id");
                  String date=request.getParameter("date");
                  String user_gender= request.getParameter("user_gender");
                  String user_name= request.getParameter("user_name");
                  String user_phone= request.getParameter("user_phone");
                  String user_idNum= request.getParameter("user_idNum");
                  String reserve_id= request.getParameter("reserve_id");
               %>
              $scope.user = {clientID: '', name: '', phone: '', gender: '', idNum: ''};
              $scope.user.name = "<%=user_name%>";
              $scope.user.phone = "<%=user_phone%>";
              $scope.user.gender = "<%=user_gender%>";
              $scope.user.idNum = "<%=user_idNum%>";
              $scope.foundUsers = [];
              $scope.date = "<%=date%>";
              $scope.room_id = "<%=room_id%>";
              $scope.order_status = "<%=order_status%>";
              $scope.button_status = "<%=button_status%>";
              $scope.reserve_id = "<%=reserve_id%>";
              $scope.customerInfoReadOnly = false;
              $scope.hasFoundCustomers = false;
              $scope.return_status = '';
              $scope.hasNotAdded = true;

              $scope.reserve = function () {
                $scope.response = null;
                $http({
                  method: 'POST',
                  url: 'api/days/' + $scope.date + '/rooms/' + $scope.room_id + '/reserve',
                  data: $scope.user
                }).then(function (response) {
                  $scope.status = response.status;
                  $scope.data = response.data;
                  $scope.return_status = 'Success！ Skip after 3 Seconds';
                  $('#returnModal').modal({});
                  setTimeout(function () {
                    window.location = "index.html";
                  }, 3000);
                }, function (response) {
                  $scope.data = response.data || "Request failed";
                  $scope.status = response.status;
                  $scope.return_status = 'Fail！';
                  $('#returnModal').modal({});
                });

              };

              $scope.addCustomerInfo = function (id, name, gender, phone, idNum) {
                $scope.user.clientID = id;
                $scope.user.name = name;
                $scope.user.gender = gender;
                $scope.user.phone = phone;
                $scope.user.idNum = idNum;
                $scope.customerInfoReadOnly = true;
                $scope.hasNotAdded = false;
              };


              $scope.orderAReservedRoom = function () {
                $scope.response = null;

                $http({
                  method: 'POST', url: 'api/reserves/' + $scope.reserve_id + '/rooms/order'
                  , data: $scope.user
                }).then(function (response) {
                  $scope.status = response.status;
                  $scope.data = response.data;
                  $scope.return_status = 'Success！ Skip after 3 Seconds';
                  $('#returnModal').modal({});
                  setTimeout(function () {
                    window.location = "index.html";
                  }, 3000);
                }, function (response) {
                  $scope.data = response.data || "Request failed";
                  $scope.status = response.status;
                  $scope.return_status = 'Fail！';
                  $('#returnModal').modal({});
                });

              };

              $scope.order = function () {
                $scope.response = null;
                $http({
                  method: 'POST',
                  url: 'api/rooms/' + $scope.room_id + '/order',
                  data: {"clientID": $scope.user.clientID}
                }).then(function (response) {
                  $scope.status = response.status;
                  $scope.data = response.data;
                  $scope.return_status = 'Success！ Skip after 3 Seconds';
                  $('#returnModal').modal({});
                  setTimeout(function () {
                    window.location = "index.html";
                  }, 3000);
                }, function (response) {
                  $scope.data = response.data || "Request failed";
                  $scope.status = response.status;
                  $scope.return_status = 'Fail！';
                  $('#returnModal').modal({});
                });

              };

              $scope.searchClient = function () {
                $scope.response = null;
                $http({
                  method: 'GET',
                  url: 'api/clients',
                  params: {"name": $scope.user.name, "phone": $scope.user.phone, "id_num": $scope.user.idNum}
                }).then(function (response) {
                  $scope.status = response.status;
                  $scope.data = response.data;
                  $scope.foundUsers = $scope.data;
                }, function (response) {
                  $scope.data = response.data || "Request failed";
                  $scope.status = response.status;
                  $scope.return_status = 'Fail！';
                  $('#returnModal').modal({});

                });

              };

              $scope.addClient = function () {
                $scope.response = null;
                $http({method: 'POST', url: 'api/clients', data: $scope.user}).then(function (response) {
                  $scope.status = response.status;
                  $scope.data = response.data;
                  $scope.user.clientID = $scope.data.clientID;
                  $scope.return_status = 'Success！';
                  $('#returnModal').modal({});
                  $scope.hasNotAdded = false;
                  $scope.customerInfoReadOnly = true;
                }, function (response) {
                  $scope.data = response.data || "Request failed";
                  $scope.status = response.status;
                  $scope.return_status = 'Fail！';
                  $('#returnModal').modal({});
                });

              };

              $scope.reset = function () {
                $scope.customerInfoReadOnly = false;
                $scope.user.clientID = null;
                $scope.user.name = null;
                $scope.user.phone = null;
                $scope.user.gender = null;
                $scope.user.idNum = null;

              };
            }]);

</script>
</body>
</html>
