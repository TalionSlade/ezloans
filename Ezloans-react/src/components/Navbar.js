import React, {Component, useState} from "react";
import {Link, NavLink} from 'react-router-dom';
import '../styles/NavBar.css';
import {IoMenuOutline as MenuIcon} from 'react-icons/io5';
import Sidebar from "./Sidebar";

const NavBar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const openSidebar = () => {
    setIsOpen(true)
  }

  const closeSidebar = () => {
    setIsOpen(false)
  }

  return(
    // <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    //   <a class="navbar-brand" href="#"><img id="ezloans-logo"
    //   src = "https://logos-world.net/wp-content/uploads/2021/02/Simple-Emblem.png"
    //   alt="EZLoans Logo"
    //   draggable="false" height="30" /></a>
    //   <div class="collapse navbar-collapse" id="navbarCollapse">
    //     <ul class="navbar-nav">
    //       <li class="nav-item">
    //         <Link to="/aboutus" className="nav-link d-flex align-items-center" >About Us</Link>
    //       </li>
    //       <li class="nav-item">
    //         <Link to="/login" className="nav-link d-flex align-items-center" >Login</Link>
    //       </li>
    //       <li class="nav-item">
    //         <Link to="/dashboard" className="nav-link d-flex align-items-center" >Dashboard</Link>
    //       </li>
    //     </ul>
    //   </div>
    // </nav>
    <header>
    <div className="navbar">
      <div className="logo">
        <NavLink to="/">EZLoans</NavLink>
      </div>
      <div className="links">
        <NavLink to="/dashboard" className={({ isActive }) => isActive ? "activeLink" : ""}>Dashboard</NavLink>
        <NavLink to="/login" className={({ isActive }) => isActive ? "activeLink" : ""}>Login</NavLink>
      </div>

      <div className="menuBtn" onClick={openSidebar}>
        <MenuIcon size={30} ></MenuIcon>
      </div>
    </div>
    <Sidebar isOpen={isOpen} closeSidebar = {closeSidebar} />
    </header>

    )
    }



export default NavBar;
