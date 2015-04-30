(function() {
    'use strict';

    angular
        .module('app.settings')
        .controller('Settings', Settings);

    /* @ngInject */
    function Settings(dataservice, logger) {
        /*jshint validthis: true */
        var vm = this;
        vm.settings = [];
        vm.title = 'Settings';

        activate();

        function activate() {
//            Using a resolver on all routes or dataservice.ready in every controller
//            var promises = [getSettings()];
//            return dataservice.ready(promises).then(function(){
            return getSettings().then(function() {
                logger.info('Activated Settings View');
            });
        }

        function getSettings() {
//            return dataservice.getSettings().then(function(data) {
//                vm.settings = data;
//                return vm.settings;
//            });
        }
    }
})();
