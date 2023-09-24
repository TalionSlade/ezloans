import React, { useState, useEffect } from 'react';
import '../styles/Dashboard.css'
import { useAuth } from './AuthContext';
import EmployeeService from '../service/EmployeeService';

const Dashboard = () => {
  const { isLoggedIn } = useAuth();
  const [employeeCount, setEmployeeCount] = useState();
  useEffect(() => {
		EmployeeService.getEmployeeCount().then((response) => {
      console.log("emp response ",response);
			setEmployeeCount(response.data); 
		});
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
        <div className="row">
          <div className="col-md-4">
            <div className="registration-container" style={{maxWidth: "300px", color: "black"}}>
              <div className="">Total Users</div>
              <div className="">{employeeCount}</div>
            </div>
          </div>

        </div>
        <br></br>
      </div>}
    </div>
  );
}

export default Dashboard