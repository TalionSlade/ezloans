import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import EmployeeService from '../../service/EmployeeService';
import { IoTrash as DeleteIcon, IoPencil as EditIcon, IoEye as ViewIcon} from 'react-icons/io5';

// Employee() displays all existing employees and renders links for View/Delete/Update Employees
function Employee() {
  
	const history = useNavigate();
	const [employees, setEmployees] = useState([]);
	const [message, setMessage] = useState([]);
  const { isLoggedIn } = useAuth();

  // Used for conditional rendering of page based on login status
	useEffect(() => {
    // if logged in, we fetch all employee details
    if(isLoggedIn) fetchEmployees();
    else {
        alert("Please login first");
        history('/login');
    }
	}, []);

  // Fetching all employee details from DB
	const fetchEmployees = () => {
		EmployeeService.getEmployees().then((response) => {
			setEmployees(response.data); 
		});
	}

  // Navigate to edit Employee page with eid passed as parameter
	const editEmployee = (id) => {
		history(`/editEmployee/${id}`) 
	}

  // Function to deleted employee based on eid
	const deleteEmployee = (id) => {
		EmployeeService.deleteEmployee(id).then(() => {
			fetchEmployees();  
			setMessage("Employee deleted successfully");
			setTimeout(() => {
				setMessage('');
			}, 2000);
		})
	}

  // Navigate to view employee details with eid as parameter
	const viewEmployee = (id) => {
		history(`/employee/${id}`);
	}

  // Navigate to add employee
  const navigateToAddEmployee = () => {
      history('/addEmployee')
  }

	return (
    <div>
      <br/> 
      {/* Conditional rendering of page contents based on login status */}
      {isLoggedIn && 
      <div className='container' >
        <h2>Customer Master Data Details</h2>
        <div className='row justify-content-center'>
          <div className='column'>
              <button className="btn" onClick={navigateToAddEmployee}>Add Employee</button>
          </div>
          <div className='column'>
            <div className='registration-container' style={{maxWidth: "80%"}}>
              <div className='row justify-content-center'>
                {/* Table to display all employees present */}
              <table className="table w-auto" >
                <thead>
                  <tr>
                      <th> Employee Id </th>
                      <th> First Name </th>
                      <th> Last Name </th>
                      <th> Designation </th>
                      <th> Department </th>
                      <th> Gender </th>
                      <th> DOB </th>
                      <th> DOJ </th>
                      <th> Action </th>
                  </tr>
                </thead>
                <tbody style={{color: "black"}}>
                  {/* Using map function to map employees to table */}
                  {employees.map(
                    emp => 
                    <tr key={emp.eid}>
                      <td> {emp.eid} </td>
                      <td> {emp.fname} </td>
                      <td> {emp.lname} </td>
                      <td> {emp.designation} </td> 
                      <td> {emp.department} </td>
                      <td> {emp.gender} </td>
                      <td> {emp.dob} </td>
                      <td> {emp.doj} </td>
                      <td>
                      <span className='tableIcon' onClick={()=>editEmployee(emp.eid)}><EditIcon></EditIcon></span>
                      &nbsp;
                      <span className='tableIcon' onClick={()=>deleteEmployee(emp.eid)} ><DeleteIcon></DeleteIcon></span>
                      &nbsp;
                      <span className='tableIcon' onClick={()=>viewEmployee(emp.eid)}><ViewIcon></ViewIcon></span>
                      </td>
                    </tr>
                  )
                  }
                </tbody>
              </table>
              </div>
              {message && <p className="success-message">{message}</p>}
            </div>
          </div>
        </div>
      </div>}
    </div>
	)
}

export default Employee