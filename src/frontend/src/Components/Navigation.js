import React from 'react';
import {NavLink} from "react-router-dom";
import {Image} from "react-bootstrap";
import logoPhoto from "../Images/ms-icon-70x70.png"

const Navigation = () => {
    return (
        <nav className="navbar bg-light justify-content-start">
            <div className='col-1 nav-pills'>
                <NavLink exact activeClassName="active-link" to="/">
                    <Image src={logoPhoto} roundedCircle/>
                </NavLink>
            </div>
            <div className='col-1 nav-pills'>
                <NavLink exact activeClassName="active-link" to="/login">
                    LogIn
                </NavLink>
            </div>
            <div className='col-1 nav-pills'>
                <NavLink exact activeClassName="active-link" to="/signup">SighUp</NavLink>
            </div>

        </nav>
    )
}




export default Navigation