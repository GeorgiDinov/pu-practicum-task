import React, {useState} from 'react';
import axios from "axios";
import {Button, Col, Form} from "react-bootstrap";

const SignUp = (props) => {

    const REGISTRATION_URL = "/register";

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");


    const onChangeFirstName = (e) => {
        const firstName = e.target.value;
        setFirstName(firstName);
    }

    const onChangeLastName = (e) => {
        const lastName = e.target.value;
        setLastName(lastName);
    }

    const onChangeEmail = (e) => {
        const email = e.target.value;
        setEmail(email);
    }

    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    }

    function createRequestObject() {
        return {
            firstName,
            lastName,
            user_credentials: {
                email,
                password
            }
        };
    }

    function clearRegistrationFields() {
        document.getElementById('registrationForm').reset();
    }


    const handleRegister = (e) => {
        e.preventDefault();
        let request = createRequestObject();
        console.log(request);

        return axios
            .post(REGISTRATION_URL, request)
            .then((response) => {
                console.log("Status = " + response.status);
                console.log("Data = " + JSON.stringify(response.data));
                clearRegistrationFields();
                // window.location.href = "/signin";
                props.history.push("/signin");

            }).catch((response) => {
                console.log(response.status);
            });
    };

    return (
        <div className='col'>
            <Form id="registrationForm" onSubmit={event => handleRegister(event)}>
                <Form.Row>
                    <Form.Group as={Col} controlId="formGridFirstName">
                        <Form.Label>First Name</Form.Label>
                        <Form.Control type="text" placeholder="First name"
                                      onChange={event => onChangeFirstName(event)}/>
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridLastName">
                        <Form.Label>Last Name</Form.Label>
                        <Form.Control type="text" placeholder="Last name" onChange={event => onChangeLastName(event)}/>
                    </Form.Group>
                </Form.Row>

                <Form.Row>
                    <Form.Group as={Col} controlId="formGridEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" placeholder="Your email" onChange={event => onChangeEmail(event)}/>
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Your password"
                                      onChange={event => onChangePassword(event)}/>
                    </Form.Group>
                </Form.Row>


                <Button variant="outline-info" type="submit">
                    Register
                </Button>
            </Form>
        </div>
    )
}


export default SignUp