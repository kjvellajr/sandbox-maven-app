(function() {
    'use strict';

    angular
        .module('app.settings')
        .run(appRun);

    appRun.$inject = ['routehelper']

    /* @ngInject */
    function appRun(routehelper) {
        routehelper.configureRoutes(getRoutes());
    }

    function getRoutes() {
        return [
            {
                url: '/settings',
                config: {
                    templateUrl: 'app/settings/settings.html',
                    controller: 'Settings',
                    controllerAs: 'vm',
                    title: 'settings',
                    settings: {
                        nav: 3,
                        content: '<i class="fa fa-lock"></i> Settings'
                    }
                }
            }
        ];
    }
})();
