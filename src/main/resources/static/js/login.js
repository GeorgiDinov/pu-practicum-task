const canvas = document.getElementById('canvas');
const headingHolder = document.getElementById('headingHolder');
const loginFormHolder = document.getElementById('loginFormHolder');
const buttonHolder = document.getElementById('buttonHolder');
const loginForm = document.getElementById('loginForm');
const loginButton = document.getElementById('login');
const registerButton = document.getElementById('register');

let detachedLoginFormHolder;
let detachedRegistrationFormHolder;
let isRegistrationFormDrawn = false;
let isLoginFormDetached = false;

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
    $(registerButton).click(showRegistrationForm);
}


function showRegistrationForm() {
    detachedLoginFormHolder = detachElement(loginFormHolder);
    isLoginFormDetached = true;
    if (isRegistrationFormDrawn) {
        detachedRegistrationFormHolder.appendTo(canvas);
    } else {
        drawRegistrationForm();
    }

    let backToLoginButton = document.getElementById('backToLogIn');
    backToLoginButton.addEventListener('click', displayLoginForm);

    let createAccountButton = document.getElementById('createAccount');
    createAccountButton.addEventListener('click', createUserAccount);

}

function displayLoginForm(event) {
    event.preventDefault();
    if (isLoginFormDetached) {
        detachedRegistrationFormHolder = detachElement($('#registrationFormHolder'));
        detachedLoginFormHolder.appendTo(canvas);
    }

}

function createUserAccount(event) {
    event.preventDefault();
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
    $("<div>",
        {
            id: 'registrationFormHolder'
        }
    ).addClass('login-form-holder').append(
        $("<form>",
            {
                id: 'registrationForm'
            }
        ).addClass('form-basic').append(
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
        ).append($("<div>",
            {
                id: 'regFormButtonHolder'
            }).addClass('button-holder').append($("<button>",
            {
                id: 'backToLogIn',
                text: 'Back'
            })
            ).append($("<button>", {
                id: 'createAccount',
                text: 'Register'
            }))
        )
    ).appendTo(canvas);
    isRegistrationFormDrawn = true;
}