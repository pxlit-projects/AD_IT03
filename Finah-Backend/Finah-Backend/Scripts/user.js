var ViewModel = function () {
    var self = this;
    self.user = ko.observableArray();
    self.error = ko.observable();
    self.detail = ko.observable();
    self.usertype = ko.observableArray();
    self.newUser = {
        login: ko.observable(),
        firstname: ko.observable(),
        lastname: ko.observable(),
        email: ko.observable(),
        street: ko.observable(),
        town: ko.observable(),
        zipcode: ko.observable(),
        birthdate: ko.observable()
    }

    var userUri = '/api/user/';
    var userTypeUri = '/api/usertype/';

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
        var user = {
            Login: self.newUser.login(),
            Firstname: self.newUser.firstname(),
            Lastname: self.newUser.lastname(),
            Email: self.newUser.email(),
            Street: self.newUser.street(),
            Town: self.newUser.town(),
            Zipcode: self.newUser.zipcode(),
            Birthdate: self.newUser.birthdate()
        };

        ajaxHelper(userUri, 'POST', user).done(function (item) {
            self.user.push(item);
        });
    }

    // Fetch the initial data.
    getAllUsers();
    getAllUserTypes();
};

ko.applyBindings(new ViewModel());