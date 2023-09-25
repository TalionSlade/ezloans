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
                    <NavLink to="/dashboard" activeclassname="active">
                        Dashboard
                    </NavLink>
                    <NavLink to="/loan" activeclassname="active">
                        View Loan
                    </NavLink>
                    <NavLink to="/applyloan" activeclassname="active">
                        Apply for Loan
                    </NavLink>
                    <NavLink to="/viewItem" activeclassname="active">
                    {/* history(`/viewItem/${id}`); */}
                        View Items Purchased
                    </NavLink>
            </div>
            )
        }
        else {
            return(
                <div>
                    <NavLink to="/dashboard" activeclassname="active">
                        Dashboard
                    </NavLink>
                    <NavLink to="/employee" activeclassname="active" >
                        Customer Data Management
                    </NavLink>
                    <NavLink to="/loan" activeclassname="active">
                        Loan Card Management
                    </NavLink>
                    <NavLink to="/item" activeclassname="active">
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