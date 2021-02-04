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