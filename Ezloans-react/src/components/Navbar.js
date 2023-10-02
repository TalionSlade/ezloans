import React from "react";
import { NavLink, useNavigate } from 'react-router-dom';
import '../styles/NavBar.css';
import {IoMenuOutline as MenuIcon} from 'react-icons/io5';
import { useAuth } from './AuthContext';

// NavBar() renders navigation bar
// Accepts Toggle() defined in App.js for toggling sidebar
const NavBar = ({Toggle}) => {
  const history = useNavigate ();
  // Custom states for login state management
  const { isLoggedIn, setIsLoggedIn, userName, setToggle } = useAuth();

  // handleLogout(): Function to handle logout 
  // It sets authContect states to false and navigates to landing page
  const handleLogout = () => {
    setIsLoggedIn(false);
    setToggle(false);
    setTimeout(() => {
        history('/',);  
    }, 200)
  }

  return(
  <header>
    <div className="navbar navbar-expand-lg">
      <div className="container-fluid">
        {/* NavBar logo which on click, redirects to landing page */}
        <div className="navbar-brand logo">
            <NavLink to="/">EZLoans</NavLink>
        </div>
        {/* Button to handle responsive navbar toggle */}
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
        </button> 
        {/* Conditional display of sidebar icon based on login status. OnClick, it toggles sidebar */}
        {isLoggedIn && <div className="sideToggleBtn nav-link" onClick={Toggle}>
            <MenuIcon size={30} ></MenuIcon>
        </div>}
        <div className="collapse navbar-collapse links" id="navbarContent">
            {isLoggedIn &&
            // Displays username of logged in user
            <ul className="navbar-nav ml-auto mb-2 mb-lg-0">
                <li className="nav-item nav-link welcome-user">
                    Hi, {userName} 
                </li>
                <li className="nav-item">
                <NavLink to = "/" className="nav-link" activeclassname="active" onClick={() => { handleLogout(); }}>Logout</NavLink>
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
