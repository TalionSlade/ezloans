import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from './AuthContext';
import EmployeeService from '../service/EmployeeService';
import ItemService from '../service/ItemService';
import LoanService from '../service/LoanService';
import EmployeeCardService from '../service/EmployeeCardService';
import EmployeeIssueService from '../service/EmployeeIssueService';
import '../styles/Dashboard.css'

// Dashbord() if used to render dashboard after user/admin login
const Dashboard = () => {
  
  // Used for navigation between different routes
  const history = useNavigate();

  // Custom useAuth returns custom states defined for checking user/admin login
  // isLoggedIn: Boolean to check if employee is logged in
  // isUser: Boolean to check is logged in employee is User or Admin
  const { isLoggedIn, isUser, userId } = useAuth();
  
  // States defined for dashboard cards
  const [employeeCount, setEmployeeCount] = useState(0);
  const [itemCount, setItemCount] = useState(0);
  const [loanCardCount, setLoanCardCount] = useState(0);
  const [employeeCardCount, setEmployeeCardCount] = useState(0);
  const [employeeIssueCount, setEmployeeIssueCount] = useState(0);

  // Route protection by checking login status. If a user is not logged in, they are redirected to the login page
  // Fetches details to be displayed on dashboard cards
  useEffect(() => {
    if(isLoggedIn) {
      EmployeeService.getEmployees().then((res) => {
        setEmployeeCount(res.data.length); 
      });
      ItemService.getItems().then((res) => {
        setItemCount(res.data.length);
      });
      LoanService.getLoans().then((res) => {
        setLoanCardCount(res.data.length);
      });
      EmployeeCardService.getEmployeeCardById(userId).then((res) => {
        setEmployeeCardCount(res.data.length);
      })
      EmployeeIssueService.getEmployeeIssueById(userId).then((res) => {
        setEmployeeIssueCount(res.data.length);
      })
    }
    else {
      alert("Please login first");
      history('/login');
    }
	}, []);

  return (
    <div>
      {/* Dashboard is conditionally displayed based on login status */}
        {isLoggedIn && <div className="container-fluid">
          <br/>
        <div className="row">
          <div className="col-md-12">
            <h2 style={{padding: "10px", color:"white", marginTop: "10px"}}>
              Dashboard 
              </h2>
              <br/>
          </div>
        </div>
        {!isUser && 
        <div className="row">
          <div className="col-md-4">
            <div className="registration-container" style={{maxWidth: "300px", color: "black"}}>
              <div className="dashboard-card-title">Total Users</div>
              <div className="">{employeeCount}</div>
            </div>
          </div>
          <div className="col-md-4">
            <div className="registration-container" style={{maxWidth: "300px", color: "black"}}>
              <div className="dashboard-card-title">Total Items</div>
              <div className="">{itemCount}</div>
            </div>
          </div>
          <div className="col-md-4">
            <div className="registration-container" style={{maxWidth: "300px", color: "black"}}>
              <div className="dashboard-card-title">Total Loan Cards</div>
              <div className="">{loanCardCount}</div>
            </div>
          </div>
        </div>}
        {isUser && 
        <div className="row">
          <div className="col-md-6">
            <div className="registration-container" style={{maxWidth: "300px", color: "black"}}>
              <div className="dashboard-card-title">Total Items applied for</div>
              <div className="">{employeeIssueCount}</div>
            </div>
          </div>
          <div className="col-md-6">
            <div className="registration-container" style={{maxWidth: "300px", color: "black"}}>
              <div className="dashboard-card-title">Total Loan Cards availed</div>
              <div className="">{employeeCardCount}</div>
            </div>
          </div>
        </div>
        }
        <br></br>
      </div>}
    </div>
  );
}

export default Dashboard