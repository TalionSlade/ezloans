import React, { Component } from 'react';
import '../styles/Sidebar.css';
import { useAuth } from './AuthContext';
import {Link, NavLink} from 'react-router-dom';



const Sidebar=() => {
    const { isUser, isLoggedIn} = useAuth();

    const changeNavLinks = () => {
        if(isUser) {
            return(
                <div >
                    <NavLink to="/dashboard" activeClassName="active">
                        Dashboard
                    </NavLink>
                    <NavLink to="/loan" activeClassName="active">
                        View Loan
                    </NavLink>
                    <NavLink to="/applyloan" activeClassName="active">
                        Apply for Loan
                    </NavLink>
                    <NavLink to="/viewitem" activeClassName="active">
                        View Items Purchased
                    </NavLink>
            </div>
            )
        }
        else {
            return(
                <div>
                    <NavLink to="/dashboard" activeClassName="active">
                        Dashboard
                    </NavLink>
                    <NavLink to="/addemp" activeClassName="active" >
                        Customer Data Management
                    </NavLink>
                    <NavLink to="/addloan" activeClassName="active">
                        Loan Card Management
                    </NavLink>
                    <NavLink to="/additem" activeClassName="active">
                        Items Master Management
                    </NavLink>
            </div>
            )
        }
    }

    return (
         <div className="sidebar">
            
            <div style={{height: "60px"}}></div>
            {changeNavLinks()}

        </div>
    );
}

export default Sidebar;