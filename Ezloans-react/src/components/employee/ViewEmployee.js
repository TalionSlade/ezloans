import React, {useState, useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import EmployeeService from '../../service/EmployeeService';
import { useAuth } from '../AuthContext';

// ViewEmployee() displays employee details for specific eid
const ViewEmployee = () => {
  const history = useNavigate();
  // Eid is recieved from url using useParams hook
  const {id} = useParams();
  const [employee, setEmployee] = useState({});
  const { isLoggedIn } = useAuth();

  // Used for conditional rendering of page based on login status. If not logged in, redirects to login
  useEffect(() => {
    if(isLoggedIn) {
      // If logged in, get details of specifc employee by eid
      EmployeeService.getEmployeeById(id).then((response) => {
        setEmployee(response.data);
      })
    }
    else {
      alert("Please login first");
      history('/login');
    }
  }, [id]);

  const backEmployee = () => {
    history('/employee')
  }

  return (
    <div>
      <br />
      <div className="registration-container col-md-6 offset-md-3">
        <h2 className="text-center" style={{color: '#1f6e8c'}}>View Employee Details</h2><hr/>
        <div className="card-body">
          {/* Eid */}
          <div className="row">
            <label>Employee Id: </label>
            <div class="text-success fw-bolder"> {employee.eid} </div><hr/>
          </div>
          {/* First Name */}
          <div className="row">
            <label>First Name: </label>
            <div class="text-success fw-bolder"> {employee.fname} </div><hr/>
          </div>
          {/* Last Name */}
          <div className="row">
            <label>Last Name: </label>
            <div class="text-success fw-bolder"> {employee.lname} </div><hr/>
          </div>
          {/* Designation */}
          <div className="row">
            <label>Employee Designation: </label>
            <div class="text-success fw-bolder"> {employee.designation} </div><hr/>
          </div>
          {/* Department */}
          <div className="row">
            <label>Employee Department: </label>
            <div class="text-success fw-bolder"> {employee.department} </div><hr/>
          </div>
          {/* Gender */}
          <div className="row">
            <label>Employee Gender: </label>
            <div class="text-success fw-bolder"> {employee.gender} </div><hr/>
          </div>
          {/* DoB */}
          <div className="row">
            <label>Employee DoB: </label>
            <div class="text-success fw-bolder"> {employee.dob} </div><hr/>
          </div>
          {/* DoJ */}
          <div className="row">
            <label>Employee DoJ: </label>
            <div class="text-success fw-bolder"> {employee.doj} </div><hr/>
          </div>
        </div>
        {/* Button to navigate back to employee */}
        <div className = "row justify-content-center">
          <button className="btn btn-info w-auto" onClick={backEmployee}>Back To Employees</button>
        </div>
      </div>
    </div>
  )
}

export default ViewEmployee;