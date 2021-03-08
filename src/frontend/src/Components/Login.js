import React, {useState} from 'react';
import axios from 'axios';
import {Button, Col, Form} from "react-bootstrap";


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

    const LOGIN = "/login";

    const handleLogin = (e) => {
        e.preventDefault();
        console.log(username + " " + password);
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
        <div className='col'>
            <Form onSubmit={event => handleLogin(event)}>
                <Form.Row>
                    <Form.Group as={Col} controlId="formGroupEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" placeholder="Your email" onChange={event => onChangeUsername(event)}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="formGroupPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password"
                                      onChange={event => onChangePassword(event)}/>
                    </Form.Group>
                </Form.Row>
                <Button variant="outline-info" type="submit">Login</Button>
            </Form>
        </div>
    )
}


export default Login