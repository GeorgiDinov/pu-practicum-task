import {NavLink} from "react-router-dom";

const Navigation = () => {
    return (
        <nav className="navbar">
            <div className='col'>
                <NavLink exact activeClassName="active-link" to="/">Welcome</NavLink>
            </div>
            <div className='col'>
                <NavLink exact activeClassName="active-link" to="/login">Login</NavLink>
            </div>
            <div className='col'>
                <NavLink exact activeClassName="active-link" to="/register">Register</NavLink>
            </div>
        </nav>
    )
}

export default Navigation