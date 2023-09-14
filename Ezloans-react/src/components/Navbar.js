import React from "react";
import {Link} from 'react-router-dom';
import '../styles/NavBar.css';
const NavBar=()=>{
    return(
        <nav className="navbar">
            <ul className="nav-list">
                <li className="nav-item">
                    <Link to="/register" className="nav-link" >Register</Link>
                
                </li>
                <li className="nav-item">
                    <Link to="/login" className="nav-link" >Login</Link>
                    
                </li>
                <li className="nav-item">
                    <Link to="/aboutus" className="nav-link" >About Us</Link>
                </li>
            </ul>
        </nav>
    );
}

export default NavBar;
