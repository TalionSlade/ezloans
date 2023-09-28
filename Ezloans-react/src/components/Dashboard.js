import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import '../styles/Dashboard.css'
import { useAuth } from './AuthContext';
import EmployeeService from '../service/EmployeeService';
import ItemService from '../service/ItemService';
import LoanService from '../service/LoanService';

const Dashboard = () => {
  const history = useNavigate();
  const { isLoggedIn, isUser } = useAuth();
  const [employeeCount, setEmployeeCount] = useState(0);
  const [itemCount, setItemCount] = useState(0);
  const [loanCardCount, setLoanCardCount] = useState(0);
  useEffect(() => {
    if(isLoggedIn) {
		EmployeeService.getEmployees().then((res) => {
			setEmployeeCount(res.data.length); 
		});
    ItemService.getItems().then((res) => {
      setItemCount(res.data.length);
    });
    LoanService.getLoans().then((res)=> {
      setLoanCardCount(res.data.length);
    });
  }
    else {
      alert("Please login first");
      history('/login');
    }
	}, []);

  return (
    <div>
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
        <br></br>
      </div>}
    </div>
  );
}

export default Dashboard