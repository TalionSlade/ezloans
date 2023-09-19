import React, { Component } from 'react';
import { Link, NavLink } from 'react-router-dom';
import { Collapse } from 'react-bootstrap';
import { IoClose as CloseIcon } from 'react-icons/io5';
import '../styles/NavBar.css';
import '../styles/Sidebar.css';
import { useAuth } from './AuthContext';


const Sidebar=() => {
    const { authUser, setAuthUser, isLoggedIn, setIsLoggedIn, isUser,
        setIsUser} = useAuth();

    return (
        <div className="sidebar">
            <div style={{height: "60px"}}></div>
            {isUser &&  <div>
                
                <a href="/dashboard" className={({ isActive }) => isActive ? "activeLink" : "" }>Dashboard</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >View Loan</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >Apply for Loan</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >View Items Purchased</a>
            </div>
            }

            {!isUser && 
            <div>
                <a href="/">Admin nav</a>
            </div>
            }
        </div>
    );
  


}

export default Sidebar;