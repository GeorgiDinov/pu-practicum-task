import React, {useState} from 'react';
import axios from "axios";

const Registration = () => {

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [successful, setSuccessful] = useState(false);


    const onChangeFirstName = (e) => {
        const firstName = e.target.value;
        setFirstName(firstName);
    }

    const onChangeLastName = (e) => {
        const lastName = e.target.value;
        setLastName(lastName);
    }

    const onChangeUsername = (e) => {
        const username = e.target.value;
        setUsername(username);
    }

    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    }

    const REGISTER = "register";


    const handleRegister = (e) => {
        e.preventDefault();

        setSuccessful(false);
        return axios
            .post(REGISTER, {
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
                setSuccessful(true);
                return response.data;
            }).catch((response) => {
                console.log(response.status);
            });
    };

    return (
        <div className="container">
            <div className="col">
                <form style={formStyle} onSubmit={handleRegister}>
                    <input
                        className="row"
                        type="text"
                        placeholder="First Name"
                        onChange={(e) => onChangeFirstName(e)}
                    />
                    <input
                        className="row"
                        type="text"
                        placeholder="Last Name"
                        onChange={(e) => onChangeLastName(e)}
                    />
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
                        value="Register"
                        className="btn btn-primary"
                    />
                </form>
            </div>
        </div>
    )
}

const formStyle = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center'
}

export default Registration