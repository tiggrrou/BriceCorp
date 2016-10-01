
App.service('translationService', function($resource) {  
        this.getTranslation = function($scope, language) {
            var languageFilePath = 'static/i18n/angular-locale_' + language + '.json';
            $resource(languageFilePath).get(function (data) {
                $scope.translation = data;
            	console.log(data);
            });
        };
    });