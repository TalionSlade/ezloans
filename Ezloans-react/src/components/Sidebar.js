import React from 'react';
import '../styles/Sidebar.css';
import { useAuth } from './AuthContext';
import { NavLink } from 'react-router-dom';

// SideBar() used to display Sidebar with links to all Admin and User functionalities
const Sidebar=() => {
  // AuthContext states for admin vs user login status
  const { isUser, userId } = useAuth();

  // Function to change Nav links based on User vs Admin login
  const changeNavLinks = () => {
    if(isUser) {
      return(
        <div >
          <NavLink to="/dashboard" activeclassname="active">
            Dashboard
          </NavLink>
          <NavLink to={`/viewLoan/emp/${userId}`} activeclassname="active">
            View Loan
          </NavLink>
          <NavLink to="/applyLoan" activeclassname="active">
            Apply for Loan
          </NavLink>
          <NavLink to={`/viewItem/emp/${userId}`} activeclassname="active">
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