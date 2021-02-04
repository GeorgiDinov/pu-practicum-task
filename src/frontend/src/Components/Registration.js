import React, {useState} from 'react'
import authService from "../Services/auth-service"

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

    const handleRegister = (e) => {
        e.preventDefault();

        setSuccessful(false);

        authService.register(firstName, lastName, username, password)
            .then(() => {
                setSuccessful(true);
            })
            .catch(() => {
                setSuccessful(false);
            });
    };

    return (
        <div className="container">
        </div>
    )
}

export default Registration