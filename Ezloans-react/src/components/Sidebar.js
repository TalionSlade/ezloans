import React, { Component } from 'react';
// import '../styles/NavBar.css';
import '../styles/Sidebar.css';
import { useAuth } from './AuthContext';
import {Link, NavLink} from 'react-router-dom';



const Sidebar=() => {
    const { authUser, setAuthUser, isLoggedIn, setIsLoggedIn, isUser,
        setIsUser} = useAuth();

    const changeNavLinks = () => {
        if(isUser) {
            // setIsUser(true);
            return(
                <div>
                    <NavLink to="/dashboard" className="nav-link">
                        Dashboard
                    </NavLink>
                    <NavLink to="/loan" className="nav-link">
                        View Loan
                    </NavLink>
                    <NavLink to="/dashboard" className={({ isActive }) => isActive ? "activeLink" : ""}>
                        Apply for Loan
                    </NavLink>
                    <NavLink to="/dashboard" className={({ isActive }) => isActive ? "activeLink" : ""}>
                        View Items Purchased
                    </NavLink>
                
                {/* <a href="/dashboard" className={({ isActive }) => isActive ? "activeLink" : "" }>Dashboard</a> */}
                {/* <a href="/loan" className={({ isActive }) => isActive ? "activeLink" : ""} >View Loan</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >Apply for Loan</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >View Items Purchased</a> */}
            </div>
            )
        }
        else {
            // setIsUser(true);
            return(
                <div>
                    <NavLink to="/dashboard" className={({ isActive }) => isActive ? "activeLink" : ""}>
                        Dashboard
                    </NavLink>
                    <NavLink to="/addemp" className={({ isActive }) => isActive ? "activeLink" : ""}>
                        Customer Data Management
                    </NavLink>
                    <NavLink to="/addemp" className={({ isActive }) => isActive ? "activeLink" : ""}>
                        Loan Card Management
                    </NavLink>
                    <NavLink to="/addemp" className={({ isActive }) => isActive ? "activeLink" : ""}>
                        Items Master Management
                    </NavLink>
                {/* <a href="/dashboard" className={({ isActive }) => isActive ? "activeLink" : "" }>Dashboard</a> */}
                {/* <a href="/addemp" className={({ isActive }) => isActive ? "activeLink" : ""} >Customer Data Management</a> */}
                {/* <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >Loan Card Management</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >Items Master Data</a> */}
            </div>
            )
        }
    }

    return (
        <div className="sidebar">
            <div style={{height: "60px"}}></div>
            {/* {isUser &&  <div>
                
                <a href="/dashboard" className={({ isActive }) => isActive ? "activeLink" : "" }>Dashboard</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >View Loan</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >Apply for Loan</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >View Items Purchased</a>
            </div>
            } */}

            {/* {!isUser && 
            <div>
                <a href="/dashboard" className={({ isActive }) => isActive ? "activeLink" : "" }>Dashboard</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >Customer Data Management</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >Loan Card Management</a>
                <a href="#" className={({ isActive }) => isActive ? "activeLink" : ""} >Items Master Data</a>
            </div>
            } */}
            {changeNavLinks()}
        </div>
    );
  


}

export default Sidebar;