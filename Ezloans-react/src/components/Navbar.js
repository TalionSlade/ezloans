import React from "react";
import { NavLink, useNavigate } from 'react-router-dom';
import '../styles/NavBar.css';
import {IoMenuOutline as MenuIcon} from 'react-icons/io5';
import { useAuth } from './AuthContext';



const NavBar = ({Toggle}) => {

    const history = useNavigate ();
    const { isLoggedIn, setIsLoggedIn, userName} = useAuth();
    const handleLogout = () => {
        setIsLoggedIn(false);
        setTimeout(() => {
            history('/',);  
        }, 200)
    }
    return(
    <header>
        <div className="navbar navbar-expand-lg">
            <div className="container-fluid">
                <div className=" navbar-brand logo">
                    <NavLink to="/">EZLoans</NavLink>
                </div>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" style={{border: "1px solid red"}}>
                    <span className="navbar-toggler-icon"></span>
                </button> 
                {isLoggedIn && <div className="sideToggleBtn nav-link" onClick={Toggle}>
                    <MenuIcon size={30} ></MenuIcon>
                </div>}
                <div className="collapse navbar-collapse links" id="navbarContent">
                    {isLoggedIn &&
                    <ul className="navbar-nav ml-auto mb-2 mb-lg-0">
                        <li className="nav-item nav-link welcome-user">
                            Hi, {userName} 
                        </li>
                        <li className="nav-item">
                        <NavLink to = "/" className="nav-link" activeclassname="active" onClick={() => { handleLogout(); Toggle();}}>Logout</NavLink>
                        </li>
                    </ul>}
                    {!isLoggedIn &&
                    <ul className="navbar-nav ml-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                        <NavLink to = "/login" className="nav-link" activeclassname="active">Login</NavLink>
                        </li>
                    </ul>}
                </div>
            </div>
        </div>
    </header>
        )
    }



export default NavBar;
