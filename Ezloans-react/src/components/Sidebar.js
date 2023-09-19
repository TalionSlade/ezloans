import React, { Component } from 'react';
import { Link, NavLink } from 'react-router-dom';
import { Collapse } from 'react-bootstrap';
import { IoClose as CloseIcon } from 'react-icons/io5';
import '../styles/NavBar.css';
import '../styles/Sidebar.css';

const Sidebar=({isOpen, closeSidebar}) => {


    return (
        <div className={`sidebar ${isOpen && "open"}`}>
            <span className='closeIcon' onClick={closeSidebar}>
                <CloseIcon size={30}/>
            </span>
            <div>
                <NavLink to="/dashboard" className={({ isActive }) => isActive ? "activeLink" : "" } onClick= {closeSidebar}>Dashboard</NavLink>
                <NavLink to="/login" className={({ isActive }) => isActive ? "activeLink" : ""} onClick= {closeSidebar}>Login</NavLink>
            </div>
        </div>
    );
  


}

export default Sidebar;