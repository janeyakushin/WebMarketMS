(function () {
    angular
        .module('market-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .when('/order_pay/:orderId', {
                templateUrl: 'order_pay/order_pay.html',
                controller: 'orderPayController'
            })
             .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.springWebUser) {
            try {
                let jwt = $localStorage.springWebUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.springWebUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            if ($localStorage.springWebUser) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
            }
        }
        if (!$localStorage.springWebGuestCartId) {
            $http.get('http://localhost:5555/cart/api/v1/cart/generate')
                .then(function successCallback(response) {
                    $localStorage.springWebGuestCartId = response.data.value;
                });
        }

    }
})();

angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {

    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};
                    $rootScope.loginusername = $localStorage.springWebUser.username;

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.springWebGuestCartId + '/merge')
                        .then(function successCallback(response) {
                        });

                    $location.path('/');
                }
            }, function errorCallback(response) {
            if (response.data.code == 'AUTH_SERVICE_INCORRECT_USERNAME_OR_PASSWORD') {
                        alert("???????????? ?? ?????????? ?????? ????????????!");
            } else {
                alert("??????-???? ?????????? ???? ??????!")
            }
            });
    };

    $scope.tryToReg = function () {
        $http.post('http://localhost:5555/auth/reg', $scope.regUser)
            .then(function successCallback(response) {
               if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.regUser.username, token: response.data.token};
                    $rootScope.loginusername = $localStorage.springWebUser.username;

                    $scope.regUser.username = null;
                    $scope.regUser.password = null;
                }
            }, function errorCallback(response) {
            if (response.data.code == 'REG_SERVICE_USERNAME_IS_BUSY') {
                        alert("???????????????????????? ?? ?????????? ???????????? (??????????????) ?????? ????????!");
            } else if (response.data.code == 'REG_SERVICE_EMAIL_IS_BUSY') {
                        alert("?????????? ?????????????????????? ?????????? ?????? ????????????????????????!");
            } else if (response.data.code == 'AUTH_SERVICE_INCORRECT_USERNAME_OR_PASSWORD') {
                        alert("???????????? ?? ?????????? ?????? ????????????!");
            } else {
                alert("??????-???? ?????????? ???? ??????!")
            }
           });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.loginusername = $localStorage.springWebUser.username;

});