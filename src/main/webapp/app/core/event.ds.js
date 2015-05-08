(function() {
    'use strict';

    angular
        .module('app.core')
        .factory('event.ds', eventDs);

    /* @ngInject */
    function eventDs($http, $location, $q, exception, logger) {
        var isPrimed = false;
        var primePromise;

        var service = {
			getEvents: getEvents,
            ready: ready
        };

        return service;

        function getEvents() {
            var events = [
                {id: '0', title: 'Tony Stark / Iron Man'},
                {id: '1', title: 'Thor'},
                {id: '2', title: 'Steve Rogers / Captain America'},
                {id: '3', title: 'Bruce Banner / The Hulk'},
                {id: '4', title: 'Natasha Romanoff / Black Widow'},
                {id: '5', title: 'Clint Barton / Hawkeye'},
                {id: '6', title: 'Pepper Potts'},
                {id: '7', title: 'Nick Fury'},
                {id: '8', title: 'Jarvis'},
                {id: '9', title: 'Loki'},
                {id: '10', title: 'Agent Phil Coulson'}
            ];
            return $q.when(events);
        }

        function prime() {
            // This function can only be called once.
            if (primePromise) {
                return primePromise;
            }

            primePromise = $q.when(true).then(success);
            return primePromise;

            function success() {
                isPrimed = true;
                logger.info('Primed data');
            }
        }

        function ready(nextPromises) {
            var readyPromise = primePromise || prime();

            return readyPromise
                .then(function() { return $q.all(nextPromises); })
                .catch(exception.catcher('"ready" function failed'));
        }

    }
})();
