JW_APP_NAME.directive('fullcalendar', ['$rootScope', '$interval', '$timeout', function ($rootScope, $interval, $timeout) {
    return {
        restrict: 'A',
        scope: {
            options: '@options',

            externalEvents: '@externalEvents',
            eventSource: '@eventSource',
            resources: '@resources',

            dateClick: '@dateClick',
            eventClick: '@eventClick',
            eventReceive: '@eventReceive',
            eventResize: '@eventResize',
            eventDrop: '@eventDrop',

            select: '@select',
            unselect: '@unselect',
            navLinkDayClick: '&navLinkDayClick',
            navLinkWeekClick: '&navLinkWeekClick'
        },

        controllerAs: 'vm',
        //bindToController: true,
        controller: ['$http', '$rootScope', '$scope', function ($http, $rootScope, $scope) {
            var vm = this;

            $scope.events = [];

            vm.extractInfoObject = function (infoObject) {
                var details = {};
                details.allDay = infoObject.allDay;
                details.startStr = infoObject.startStr;
                details.start = infoObject.start;
                details.endStr = infoObject.endStr;
                details.end = infoObject.end;
                details.dateStr = infoObject.dateStr;

                details.resource = {};
                details.resource.businessHours = infoObject.resource._resource.businessHours;
                details.resource.extendedProps = infoObject.resource._resource.extendedProps;
                details.resource.id = infoObject.resource.id;
                details.resource.title = infoObject.resource._resource.title;
                details.resource.eventClassNames = infoObject.resource.eventClassNames;
                details.resource.eventTextColor = infoObject.resource.eventTextColor;
                details.resource.eventBorderColor = infoObject.resource.eventBorderColor;
                details.resource.eventBackgroundColor = infoObject.resource.eventBackgroundColor;
                details.resource.eventAllow = infoObject.resource.eventAllow;
                details.resource.eventOverlap = infoObject.resource.eventOverlap;


                return details;
            }

            $scope.updateTimesheetCallback;
            $scope.timer = undefined;

            function updateTimeSheets() {
                if ($('#' + $scope.id).is(':visible')) {
                    $.get($scope.eventSource, function (data) {
                        $scope.events = data;
                        $scope.updateTimesheetCallback($scope.events);
                        return $scope.events;
                    })
                } else {
                    return $scope.events;
                }
            }

            $scope.ctrlFn = function (element) {
                var id = element.attr('id');
                $scope.id = id;

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
                    //    alert('binding event source');
                    options.events = function (info, successCallback, failureCallback) {
                        $scope.updateTimesheetCallback = successCallback;
                        $scope.timer = $interval(updateTimeSheets, 1100);
                        return $scope.events;
                    }

                    element.on('$destroy', function () {
                        try {
                            $interval.cancel($scope.timer);
                        } catch (e) {

                        }
                    });
                }

                if ($scope.dateClick !== undefined) {
                    options.dateClick = function (infoObject) {
                        var eventObj = {};
                        eventObj.currentTarget = {};
                        eventObj.target = {};
                        eventObj.target.id = id;
                        eventObj.currentTarget.id = id;
                        var copy = vm.extractInfoObject(infoObject);
                        eventObj.infoObj = copy;
                        jw.env.controller.perform(eventObj, ['headers'], $scope.dateClick, $scope.dateClick);
                    }
                }

                if ($scope.eventClick !== undefined) {
                    options.eventClick = function (infoObject) {
                        var eventObj = {};
                        eventObj.currentTarget = {};
                        eventObj.target = {};
                        eventObj.target.id = id;
                        eventObj.currentTarget.id = id;
                        var copy = vm.extractInfoObject(infoObject);
                        eventObj.infoObj = copy;
                        jw.env.controller.perform(eventObj, ['headers'], $scope.eventClick, $scope.eventClick);
                    }
                }

                if ($scope.eventReceive !== undefined) {
                    options.eventReceive = function (infoObject) {
                        var eventObj = {};
                        eventObj.currentTarget = {};
                        eventObj.target = {};
                        eventObj.target.id = id;
                        eventObj.currentTarget.id = id;
                        var copy = vm.extractInfoObject(infoObject);
                        eventObj.infoObj = copy;
                        jw.env.controller.perform(eventObj, ['headers'], $scope.eventReceive, $scope.eventReceive);
                    }
                }

                if ($scope.eventResize !== undefined) {
                    options.eventResize = function (infoObject) {
                        var eventObj = {};
                        eventObj.currentTarget = {};
                        eventObj.target = {};
                        eventObj.target.id = id;
                        eventObj.currentTarget.id = id;
                        var copy = vm.extractInfoObject(infoObject);
                        eventObj.infoObj = copy;
                        jw.env.controller.perform(eventObj, ['headers'], $scope.eventResize, $scope.eventResize);
                    }
                }

                if ($scope.eventDrop !== undefined) {
                    options.eventDrop = function (infoObject) {
                        var eventObj = {};
                        eventObj.currentTarget = {};
                        eventObj.target = {};
                        eventObj.target.id = id;
                        eventObj.currentTarget.id = id;
                        var copy = vm.extractInfoObject(infoObject);
                        eventObj.infoObj = copy;
                        jw.env.controller.perform(eventObj, ['headers'], $scope.eventDrop, $scope.eventDrop);
                    }
                }

                if ($scope.select !== undefined) {
                    options.select = function (infoObject) {
                        var eventObj = {};
                        eventObj.currentTarget = {};
                        eventObj.target = {};
                        eventObj.target.id = id;
                        eventObj.currentTarget.id = id;
                        var copy = vm.extractInfoObject(infoObject);
                        eventObj.infoObj = copy;
                        jw.env.controller.perform(eventObj, ['headers'], $scope.select, $scope.select);
                    }
                }

                if ($scope.unselect !== undefined) {
                    options.unselect = function (infoObject) {
                        var eventObj = {};
                        eventObj.currentTarget = {};
                        eventObj.target = {};
                        eventObj.target.id = id;
                        eventObj.currentTarget.id = id;
                        var copy = vm.extractInfoObject(infoObject);
                        eventObj.infoObj = copy;
                        jw.env.controller.perform(eventObj, ['headers'], $scope.unselect, $scope.unselect);
                    }
                }

                if ($scope.navLinkDayClick !== undefined) {
                    options.navLinkDayClick = function (infoObject) {
                        var eventObj = {};
                        eventObj.currentTarget = {};
                        eventObj.target = {};
                        eventObj.target.id = id;
                        eventObj.currentTarget.id = id;
                        var copy = vm.extractInfoObject(infoObject);
                        eventObj.infoObj = copy;
                        jw.env.controller.perform(eventObj, ['headers'], $scope.navLinkDayClick, $scope.navLinkDayClick);
                    }
                }

                $scope.calendar = new FullCalendar.Calendar($scope.calendarEL, options);
                $scope.calendar.render();

            };
        }],

        link: function ($scope, element, attrs) {
            $scope.ctrlFn(element);
            element.on('$destroy', function () {
                try {
                    $scope.calendar.destroy();
                } catch (e) {

                }
            });
        }
    };
}]);
