import axios from "axios";

const API_URL = "http://localhost:8080/";
const REGISTER = "register";
const LOGIN = "login";
const LOGOUT = "logout";

const register = (firstName, lastName, username, password) => {
    return axios
        .post(API_URL + REGISTER, {
            firstName,
            lastName,
            username,
            password,
        }).then((response) => {
            let regUserDTO = {
                "id": response.data.id,
                "username": response.data.username
            }
            console.log(regUserDTO);
            localStorage.setItem("regUserDTO", JSON.stringify(regUserDTO));
            return response.data;
        });
};

const login = (username, password) => {
    return axios
        .post(API_URL + LOGIN, {
            username,
            password,
        }).then((response) => {
            if (response.data.accessToken) {
                localStorage.setItem("token", JSON.stringify(response.data.accessToken));
            }

            let regUserDTO = {
                "id": response.data.id,
                "username": response.data.username
            }
            console.log(regUserDTO);
            localStorage.setItem("regUserDTO", JSON.stringify(regUserDTO));
            return response.data;
        });
};

const logout = () => {
    return axios
        .get(API_URL + LOGOUT)
        .then((response) => {
            if (response.status === 200) {
                localStorage.removeItem("token");
            }
            return response.data;
        });
};

export default {
    register,
    login,
    logout,
};