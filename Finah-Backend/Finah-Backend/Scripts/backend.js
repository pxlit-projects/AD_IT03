var ViewModel = function () {
    var self = this;
    self.user = ko.observableArray();
    self.question = ko.observableArray();
    self.theme = ko.observableArray();
    self.usertype = ko.observableArray();
    self.answer = ko.observableArray();
    self.error = ko.observable();
    self.detail = ko.observable();
    self.newUser = {
        login: ko.observable(),
        password: ko.observable(),
        firstname: ko.observable(),
        lastname: ko.observable(),
        email: ko.observable(),
        type: ko.observable(),
        street: ko.observable(),
        town: ko.observable(),
        zipcode: ko.observable(),
        birthdate: ko.observable()
    }
    
    self.newQuestion = {
        title: ko.observable(),
        description: ko.observable(),
        theme: ko.observable(),
        choice: ko.observable(),
    }

    var userUri = '/api/user/';
    var userTypeUri = '/api/usertype/';
    var questionUri = '/api/question/';
    var themeUri = '/api/theme/';
    var answerUri = '/api/answer/';

    function ajaxHelper(uri, method, data) {
        self.error(''); // Clear error message
        return $.ajax({
            type: method,
            url: uri, 
            dataType: 'json',
            contentType: 'application/json',
            data: data ? JSON.stringify(data) : null
        }).fail(function (jqXHR, textStatus, errorThrown) {
            self.error(errorThrown);
        });
    }

    function getAllUsers() {
        ajaxHelper(userUri, 'GET').done(function (data) {
            self.user(data);
        });
    }

    function getAllUserTypes() {
        ajaxHelper(userTypeUri, 'GET').done(function (data) {
            self.usertype(data);
        });
    }

    self.getUserDetail = function (item) {
        ajaxHelper(userUri + item.id, 'GET').done(function (data) {
            self.detail(data);
        });
    }

    self.addUser = function (formElement) {
        var nUser = {
            Type: self.newUser.type().id,
            Login: self.newUser.login(),
            Password: self.newUser.password(),
            Firstname: self.newUser.firstname(),
            Lastname: self.newUser.lastname(),
            Email: self.newUser.email(),
            Street: self.newUser.street(),
            Town: self.newUser.town(),
            Zipcode: self.newUser.zipcode(),
            Birthdate: self.newUser.birthdate()
        };

        ajaxHelper(userUri, 'POST', nUser).done(function (item) {
            self.user.push(item);
        });

    }
    function getAllQuestions() {
        ajaxHelper(questionUri, 'GET').done(function (data) {
            self.question(data);
        });
    }

    function getAllThemes() {
        ajaxHelper(themeUri, 'GET').done(function (data) {
            self.theme(data);
        });
    }

    self.getQuestionDetail = function (item) {
        ajaxHelper(questionUri + item.id, 'GET').done(function (data) {
            self.detail(data);
        });
    }

    self.addQuestion = function (formElement) {
        var nQuestion = {
            Title: self.newQuestion.title(),
            Description: self.newQuestion.description(),
            Theme: self.newQuestion.theme(),
            Choice: self.newQuestion.choice()
        };

        ajaxHelper(questionUri, 'POST', nQuestion).done(function (item) {
            self.question.push(item);
        });

    }

    function getAllAnswers() {
        ajaxHelper(answerUri, 'GET').done(function (data) {
            self.answer(data);
        });
    }

    // Fetch the initial data.
    getAllQuestions();
    getAllThemes();
    getAllUserTypes();
    getAllUsers();
    getAllAnswers();
};

ko.applyBindings(new ViewModel());