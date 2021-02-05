import React, {useState} from 'react';
import axios from 'axios';

const Login = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const onChangeUsername = (e) => {
        const username = e.target.value;
        setUsername(username);
    }

    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    }

    const LOGIN = "login";

    const handleLogin = (e) => {
        e.preventDefault();
        return axios
            .post(LOGIN, {
                username,
                password
            })
            .then((response) => {
                console.log("Response header = " + response.headers.authorization);
                if (response.headers.authorization) {
                    console.log("Inside If Statement");
                    localStorage.setItem('token', response.headers.authorization);
                    localStorage.setItem('loggedInUser', JSON.stringify(response.data));
                }

                console.log(response.data)
                return response.data;
            })
            .catch((response) => {
                console.log("Login Error = " + response.status);
            });

    }

    return (
        <div className="container">
            <div className="col">
                <form onSubmit={handleLogin}>
                    <input
                        className="row"
                        type="text"
                        placeholder="Username"
                        onChange={(e) => onChangeUsername(e)}
                    />
                    <input
                        className="row"
                        type="password"
                        placeholder="Password"
                        onChange={(e) => onChangePassword(e)}
                    />
                    <input
                        type="submit"
                        value="Login"
                        className="btn btn-primary"
                    />
                </form>
            </div>
        </div>
    )
}

export default Login