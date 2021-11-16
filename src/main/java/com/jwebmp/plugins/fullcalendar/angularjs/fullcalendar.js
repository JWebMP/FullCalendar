JW_APP_NAME.directive('fullcalendar', ['$rootScope', '$interval', '$timeout', function ($rootScope, $interval, $timeout) {
    return {
        restrict: 'A',
        scope: {
            options: '@options',

            externalEvents: '@externalEvents',
            eventSource: '@eventSource',
            resources: '@resources',

            dateClick: '&dateClick',
            eventClick: '&eventClick',
            eventReceive: '&eventReceive',
            eventResize: '&eventResize',
            eventDrop: '&eventDrop',

            select: '&select',
            unselect: '&unselect',
            navLinkDayClick: '&navLinkDayClick',
            navLinkWeekClick: '&navLinkWeekClick',

            websocketGroup: '@websocket'
        },

        controllerAs: 'vm',
        //bindToController: true,
        controller: ['$http', '$rootScope', '$scope', function ($http, $rootScope, $scope) {
            var vm = this;

            $scope.events = [];

            vm.extractInfoObject = function (infoObject) {
                alert('extracting time calendar information');

                infoObject.view = undefined;
                infoObject.jsEvent = undefined;

                var copy = JSON.parse(JSON.stringify(infoObject));
                return copy;
            }

            $scope.ctrlFn = function (element) {
                var id = element.attr('id');

                if ($scope.externalEvents !== undefined) {
                    $scope.containerEL = document.getElementById($scope.externalEvents);
                }
                $scope.calendarEL = document.getElementById(id);
                var options;


                if ($scope.options !== undefined) {
                    options = JSON.parse($scope.options);
                } else {
                    options = {};
                }

                if ($scope.resources !== undefined) {
                    options.resources = JSON.parse($scope.resources);
                }

                if ($scope.eventSource !== undefined) {
                    if ($('#' + id + ':visible')) {
                    //    alert('binding event source');
                        options.events = function (info, successCallback, failureCallback) {
                            $.get($scope.eventSource, function (data) {
                                $scope.events = data;
                                successCallback( $scope.events);
                                return $scope.events;
                            })
                        }
                    }//end if visible
                    else {
                        return $scope.events;
                    }
                }

                if ($scope.dateClick !== undefined) {
                    $scope.options.dateClick = function (infoObject) {

                   //     alert('Clicked on: ' + info.dateStr);
                //        alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
                //        alert('Current view: ' + info.view.type);

                        info.dayEl.style.backgroundColor = 'red';
                        $scope.$event = infoObject.event;
                        var copy = vm.extractInfoObject(infoObject);
                 //       alert('Copy made: ' + copy);
                        $scope.eventData = copy;
                        jwCntrl.perform($event, ['headers'], $scope.dateClick, $scope.dateClick);
                    //    $scope.dateClick();
                    }
                }


                if ($scope.eventClick !== undefined) {
                    $scope.options.eventClick = $scope.eventClick;
                }

                if ($scope.eventReceive !== undefined) {
                    $scope.options.eventReceive = $scope.eventReceive;
                }

                if ($scope.eventResize !== undefined) {
                    $scope.options.eventResize = $scope.eventResize;
                }

                if ($scope.eventDrop !== undefined) {
                    $scope.options.eventDrop = $scope.eventDrop;
                }

                if ($scope.select !== undefined) {
                    $scope.options.select = $scope.select;
                }

                if ($scope.unselect !== undefined) {
                    $scope.options.unselect = $scope.unselect;
                }

                if ($scope.navLinkDayClick !== undefined) {
                    $scope.options.navLinkDayClick = $scope.navLinkDayClick;
                }

                $scope.calendar = new FullCalendar.Calendar($scope.calendarEL, options);
                $scope.calendar.render();



            };
        }],

        link: function ($scope, element, attrs) {
            $scope.ctrlFn(element);

            element.on('$destroy', function () {
               // alert('destroy calendar');
                $scope.calendar.destroy();
             //   alert('destroyed calendar');
            });
        }

    };
}]);
