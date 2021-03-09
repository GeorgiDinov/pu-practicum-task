import React, {useState} from 'react';
import axios from 'axios';
import {Button, Col, Form} from "react-bootstrap";


const SignIn = () => {

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

    function clearSignInFormFields() {
        document.getElementById('signInForm').reset();
    }


    const handleLogin = (e) => {
        e.preventDefault();
        console.log(username + " " + password);
        return axios
            .post(LOGIN, {
                username,
                password
            })
            .then((response) => {
                if (response.headers.authorization) {
                    clearSignInFormFields();
                    localStorage.setItem('token', response.headers.authorization);
                    localStorage.setItem('loggedInUser', JSON.stringify(response.data));
                }
                return response.data;
            })
            .catch((response) => {
                console.log("SignIn Error = " + response.status);
            });

    }

    return (
        <div className='col'>
            {/*<Message{id}/>*/}
            <Form id='signInForm' onSubmit={event => handleLogin(event)}>
                <Form.Row>
                    <Form.Group as={Col} controlId="formGroupEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" placeholder="Your email"
                                      onChange={event => onChangeUsername(event)}/>
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
    );
}

export default SignIn