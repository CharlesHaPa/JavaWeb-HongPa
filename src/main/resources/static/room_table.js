"use strict";
angular.module('room_table', [])
    .controller('RoomTableController', ['$scope', '$http',
        function ($scope, $http) {
            Date.prototype.yyyymmdd = function () {
                var yyyy = this.getFullYear().toString();
                var mm = (this.getMonth() + 1).toString(); // getMonth() is zero-based
                var dd = this.getDate().toString();
                return yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-" + (dd[1] ? dd : "0" + dd[0]); // padding
            };
            {
                var tmpDate = new Date();
                $scope.minDate = new Date(tmpDate.getFullYear(), tmpDate.getMonth(), tmpDate.getDate());
                $scope.date = new Date(tmpDate.getFullYear(), tmpDate.getMonth(), tmpDate.getDate());
                tmpDate.setDate(tmpDate.getDate() + 1);
                $scope.nextDate = new Date(tmpDate.getFullYear(), tmpDate.getMonth(), tmpDate.getDate());
                tmpDate = new Date();
                tmpDate.setMonth(tmpDate.getMonth() + 1);
                $scope.nextMonth = new Date(tmpDate.getFullYear(), tmpDate.getMonth(), tmpDate.getDate());
            }
            $scope.roomsInADay = [];
            $scope.reserve_button = false;
            $scope.order_button = false;
            $scope.cancel_reserve = false;
            $scope.cancel_order = false;
            $scope.room_id = {};
            $scope.user_name = null;
            $scope.user_gender = null;
            $scope.user_phone = null;
            $scope.user = {clientID: '', name: '', phone: '', gender: '', idNum: ''};
            $scope.user_idNum = null;
            $scope.order_status = 0;
            $scope.button_status = 0;
            $scope.reserve_id = 0;
            $scope.data = {client: {}};
            $scope.return_status = " ";
            $scope.showOrderButton = true;
            $scope.listRooms = function () {
                $http({
                    method: 'GET',
                    url: 'api/days/' + $scope.date.yyyymmdd() + '/rooms'
                }).then(function (response) {

                    $scope.status = response.status;
                    $scope.data = response.data;
                    <!--   JavaScript    -->
                    $(function () {
                        $scope.showOrderButton = $scope.date.getTime() < $scope.nextDate.getTime();
                    });
                    var rooms = [];
                    for (var x in $scope.data.reserved) {
                        rooms.push({id: $scope.data.reserved[x], status: "reserved", style: "btn btn-danger"});
                    }
                    for (var x in $scope.data.free) {
                        rooms.push({id: $scope.data.free[x], status: "free", style: "btn btn-success"});
                    }
                    for (var x in $scope.data.ordered) {
                        rooms.push({id: $scope.data.ordered[x], status: "ordered", style: "btn btn-free"});
                    }

                    for (var i = 0; i < rooms.length; i++) {
                        for (var j = i; j < rooms.length; j++) {
                            if (rooms[i].id.localeCompare(rooms[j].id) > 0) {
                                var temp = rooms[i];
                                rooms[i] = rooms[j];
                                rooms[j] = temp;
                            }
                        }
                    }
                    $scope.roomsInADay = rooms;
                }, function (response) {
                    $scope.data = response.data || "Request failed";
                    $scope.status = response.status;
                    $scope.return_status = 'Invalid Date！';
                    $('#returnModal').modal({});
                });

            };
            $scope.listRooms();
            $scope.getCustomerInfo = function (room_style, room_id) {
                $http({
                    method: 'GET',
                    url: 'api/days/' + $scope.date.yyyymmdd() + '/rooms/' + room_id
                }).then(function (response) {

                    $scope.status = response.status;
                    $scope.data = response.data;
                    $scope.show_buttons(room_style, room_id);

                }, function (response) {
                    $scope.data = response.data || "Request failed";
                    $scope.status = response.status;
                });

            };

            $scope.show_buttons = function (room_style, room_id) {
                if (room_style == 'btn btn-danger') {
                    $scope.order_button = true;
                    $scope.cancel_reserve = true;
                    $scope.reserve_button = false;
                    $scope.cancel_order = false;
                    $scope.room_id = room_id;
                    $scope.order_status = 1;
                    $scope.user_name = $scope.data.client.name;
                    $scope.user_gender = $scope.data.client.gender;
                    $scope.user_phone = $scope.data.client.phone;
                    $scope.user_idNum = $scope.data.client.idNum;
                    $scope.reserve_id = $scope.data.reserveId;

                }
                else if (room_style == 'btn btn-free') {
                    $scope.cancel_order = true;
                    $scope.order_button = false;
                    $scope.reserve_button = false;
                    $scope.cancel_reserve = false;
                    $scope.room_id = room_id;
                    $scope.order_status = 3;
                    $scope.user_name = $scope.data.client.name;
                    $scope.user_gender = $scope.data.client.gender;
                    $scope.user_phone = $scope.data.client.phone;
                    $scope.user_idNum = $scope.data.client.idNum;
                }
                else if (room_style == 'btn btn-success') {
                    $scope.order_button = true;
                    $scope.reserve_button = true;
                    $scope.cancel_reserve = false;
                    $scope.cancel_order = false;
                    $scope.room_id = room_id;
                    $scope.order_status = 2;
                    $scope.user_name = $scope.user.name;
                    $scope.user_gender = $scope.user.gender;
                    $scope.user_phone = $scope.user.phone;
                    $scope.user_idNum = $scope.user.idNum;
                }
            };

            $scope.addPara1 = function () {
                $scope.button_status = 1;
            };

            $scope.addPara2 = function () {
                $scope.button_status = 2;
            };

            $scope.cancelReserve = function () {
                $scope.response = null;
                $http({method: 'DELETE', url: 'api/reserves/' + $scope.room_id}).then(function (response) {
                    $scope.status = response.status;
                    $scope.data = response.data;
                    $scope.return_status = 'Success！';
                    $('#returnModal').modal({});
                    $scope.listRooms();
                }, function (response) {
                    $scope.data = response.data || "Request failed";
                    $scope.status = response.status;
                    $('#returnModal').modal({});
                    $scope.listRooms();
                });
            };

            $scope.cancelOrder = function () {
                $scope.response = null;
                $http({method: 'DELETE', url: 'api/orders/' + $scope.room_id}).then(function (response) {
                    $scope.status = response.status;
                    $scope.data = response.data;
                    $scope.return_status = 'Success！';
                    $('#returnModal').modal({});
                    $scope.listRooms();
                }, function (response) {
                    $scope.data = response.data || "Request failed";
                    $scope.status = response.status;
                    $scope.listRooms();
                });

            };


        }]);
