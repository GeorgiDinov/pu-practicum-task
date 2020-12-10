const URL = "http://localhost:8080/";
const URL_ITEMS = "/items"
const URL_NEW_ITEM = "/newItem";

let form = document.getElementById('itemform');
let items = document.getElementById('items');

let logoutForm = document.getElementById('logoutForm');
let logoutButton = document.getElementById('logout');
let userNamePlaceHolder = document.getElementById('user');

let loggedInUserId;
let loggedInUsername;

logoutButton.addEventListener('click', logout);
form.addEventListener('submit', addNewItem);
getRegisteredUserCommand();
refreshItemList();


function logout(event) {
    event.preventDefault();
    logoutForm.submit();
}

function refreshItemList() {
    items.innerHTML = "";
    loadItemList();
}

function getRegisteredUserCommand() {
    console.log("Inside getWelcomeInfo()");
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("GET", "/userinfo", true);
    xmlHttpRequest.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE) {
            if (this.status === 200) {
                let welcomeInfo = JSON.parse(this.responseText);
                loggedInUserId = "/" + welcomeInfo.id;
                loggedInUsername = welcomeInfo.username;
                userNamePlaceHolder.innerText = " " + loggedInUsername;
            }
        }
    }
    xmlHttpRequest.send();
}

function loadItemList() {
    console.log("Inside loadItems()");
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("GET", URL_ITEMS, true);
    xmlHttpRequest.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE) {
            if (this.status === 200) {
                let items = JSON.parse(this.responseText);
                for (let i = 0; i < items.length; i++) {
                    createNewItem(items[i]);
                }
            }
        }
    }
    xmlHttpRequest.send();
}

function addNewItem(event) {
    console.log("Inside addNewItem()");
    let itemName = document.getElementById('itemname').value;
    let quantity = document.getElementById('itemquantity').value;
    document.getElementById('itemname').value = "";
    document.getElementById('itemquantity').value = "";

    let newItem = JSON.stringify({"name": itemName, "quantity": quantity});

    let xmlHttpRequest = new XMLHttpRequest();
    let url = URL_ITEMS + URL_NEW_ITEM;
    xmlHttpRequest.open("POST", url, true);
    xmlHttpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xmlHttpRequest.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE) {
            if (this.status === 200) {
                refreshItemList();
            } else {
                requestsAlertMessage(this.status);
            }
        }
    }
    xmlHttpRequest.send(newItem);
    event.preventDefault();
}

function createNewItem(returnedItem) {
    let itemContainer = document.createElement("div");
    itemContainer.setAttribute("id", returnedItem.id);
    itemContainer.innerText = returnedItem.name + (returnedItem.quantity === 0 ? "" : " - " + returnedItem.quantity);
    itemContainer.classList.add("item");
    if (returnedItem.selected) {
        itemContainer.classList.add("cross");
    }
    itemContainer.addEventListener('click', manipulateItem);
    items.appendChild(itemContainer);
}

function manipulateItem(event) {
    if (this.classList.contains("cross")) {
        deleteItemById(this);
    } else {
        changeItemSelectionState(this);
    }
    event.preventDefault();
}

function deleteItemById(item) {
    let itemId = "/" + item.id;
    let deleted = false;
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("DELETE", URL_ITEMS + itemId, true);
    xmlHttpRequest.onreadystatechange = function () {
        if (!deleted) {
            if (this.status === 200) {
                deleted = true;
                refreshItemList();
            } else {
                alertMessage(item);
            }
        }
    }
    xmlHttpRequest.send();
}

function changeItemSelectionState(item) {
    let itemId = "/" + item.id;
    let isSelected = false;
    let itemToUpdate = extractItemJSON(item);

    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("PUT", URL_ITEMS + itemId, true);
    xmlHttpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xmlHttpRequest.onreadystatechange = function () {
        if (!isSelected) {
            if (this.status === 200) {
                isSelected = true;
                refreshItemList();
            } else {
                alertMessage(item);
            }
        }
    }
    xmlHttpRequest.send(itemToUpdate);
}

function extractItemJSON(item) {
    let id = item.id;
    let name;
    let quantity;
    let itemData = item.textContent;

    let dash = " - ";

    if (itemData.includes(dash)) {
        let itemArray = itemData.split(dash);
        name = itemArray[0];
        quantity = itemArray[1];
    } else {
        name = itemData;
        quantity = 0;
    }
    return JSON.stringify({"id": id, "name": name, "quantity": quantity});
}

//todo implement error message with div container or pop up
function alertMessage(item) {
    alert("Problem deleting selected item!");
    item.classList.remove("cross");
}

//todo implement error message with div container or pop up
function requestsAlertMessage(message) {
    let errorMessage = "Error " + message + "\n" + "Invalid Data";
    alert(errorMessage);
}

function addNewItemErrorMessage() {
    //todo implementation
}

function deleteItemErrorMessage(item) {
    item.classList.remove("cross");
    //todo implementation
}