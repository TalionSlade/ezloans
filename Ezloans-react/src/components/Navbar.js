import React, {Component, useState} from "react";
import {Link, NavLink, useNavigate} from 'react-router-dom';
import '../styles/NavBar.css';
import {IoMenuOutline as MenuIcon} from 'react-icons/io5';
import { useAuth } from './AuthContext';
import Sidebar from "./Sidebar";



const NavBar = ({Toggle}) => {

    const history = useNavigate ();
    const { isLoggedIn, setIsLoggedIn} = useAuth();
    const handleLogout = () => {
        isLoggedIn(false);
        setTimeout(() => {
            history('/');  
        }, 200)
    }
    return(
    <header>
        <div className="navbar navbar-expand-lg">
            <div class="container-fluid">
                <div className=" navbar-brand logo">
                    <NavLink to="/">EZLoans</NavLink>
                </div>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" style={{border: "1px solid red"}}>
                    <span class="navbar-toggler-icon"></span>
                </button> 
                {isLoggedIn && <div className="sideToggleBtn nav-link" onClick={Toggle}>
                    <MenuIcon size={30} ></MenuIcon>
                </div>}
                <div class="collapse navbar-collapse links" id="navbarContent">
                    {isLoggedIn &&
                    <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                        <NavLink to = "/" className="nav-link" activeClassName="active" onClick={handleLogout}>Logout</NavLink>

                            {/* <a class="nav-link" href="/">Logout</a> */}
                        </li>
                    </ul>}
                    {!isLoggedIn &&
                    <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                        <NavLink to = "/login" className="nav-link" activeClassName="active">Login</NavLink>
                            {/* <a class="nav-link" href="/login">Login</a> */}
                        </li>
                    </ul>}
                </div>
            </div>
        </div>
    </header>
        )
    }



export default NavBar;
