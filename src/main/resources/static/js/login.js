const canvas = document.getElementById('canvas');
const headingHolder = document.getElementById('headingHolder');
const loginFormHolder = document.getElementById('loginFormHolder');
const buttonHolder = document.getElementById('buttonHolder');
const loginForm = document.getElementById('loginForm');
const loginButton = document.getElementById('login');
const registerButton = document.getElementById('register');

let detachedLoginFormHolder;
let detachedButtonHolder;

const URL = "http://localhost:8080/login";

const METHOD_POST = "POST";
const METHOD_GET = "GET";
const METHOD_PUT = "PUT";
const METHOD_DELETE = "DELETE";

const errorMessage = "Error occurred while doing AJAX to ";

$(document).ready(function () {
    setUpLoginFormAction();

});

function setUpLoginFormAction() {
    $(loginButton).click(function () {
        $(loginForm).submit();
    });
    $(registerButton).click(registerNewUser);
}


function registerNewUser() {
    detachedLoginFormHolder = detachElement(loginFormHolder);
    detachedButtonHolder = detachElement(buttonHolder);
    drawRegistrationForm();
}


function sendData(url, method, payload) {
    return $.ajax({
        method: method,
        url: url,
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: payload,
        dataType: 'JSON'
    }).fail(function () {
        console.log(errorMessage + url);
    });
}

function fetchData(url, method) {
    return $.ajax({
        method: method,
        url: url,
        dataType: 'JSON'
    }).fail(function () {
        console.log(errorMessage + url);
    });
}

function emptyElement(element) {
    $(element).empty();
}

function removeElement(element) {
    $(element).remove();
}

function detachElement(element) {
    return $(element).detach();
}

function drawRegistrationForm() {
    $("<form>",
        {
            id: 'registrationForm'
        }
    ).addClass('login-form-holder').append(
        $("<input>",
            {
                id: 'firstName',
                type: 'text',
                placeholder: 'First Name',
                name: 'firstname',
            }
        )
    ).append(
        $("<input>",
            {
                id: 'lastName',
                type: 'text',
                placeholder: 'Last Name',
                name: 'lastName',
            }
        )
    ).append(
        $("<input>",
            {
                id: 'username',
                type: 'text',
                placeholder: 'Username',
                name: 'username',
            }
        )
    ).append(
        $("<input>",
            {
                id: 'password',
                type: 'password',
                placeholder: 'Password',
                name: 'password',
            }
        )
    ).append(
        $("<button>",
            {
                id: 'createAccount',
                type: 'submit',
                text: 'Create Account'
            }
        )
    ).appendTo(canvas)
        .submit(registerNewUser);
}