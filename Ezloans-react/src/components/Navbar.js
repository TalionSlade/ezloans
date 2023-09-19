import React, {Component, useState} from "react";
import {Link, NavLink} from 'react-router-dom';
import '../styles/NavBar.css';
import {IoMenuOutline as MenuIcon} from 'react-icons/io5';
import Sidebar from "./Sidebar";

const NavBar = ({Toggle}) => {

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

        <div className="sideToggleBtn nav-link" onClick={Toggle}>
        <MenuIcon size={30} ></MenuIcon>
        </div>
        <div class="collapse navbar-collapse links" id="navbarContent">
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="/about">About Us</a>
                    {/* <NavLink to="/dashboard" className={({ isActive }) => isActive ? "activeLink" : ""}>Dashboard</NavLink> */}
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
            </ul>
        </div>
    </div>

      
    </div>
    </header>

    )
    }



export default NavBar;
