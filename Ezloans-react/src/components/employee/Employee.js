import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import EmployeeService from '../../service/EmployeeService';
import { IoTrash as DeleteIcon, IoPencil as EditIcon, IoEye as ViewIcon} from 'react-icons/io5';

function Employee() {

	const history = useNavigate();

	const [employees, setEmployees] = useState([]);
	const [message, setMessage] = useState([]);
    const { isLoggedIn } = useAuth();

	useEffect(() => {
        if(isLoggedIn)
		fetchEmployees();
        else {
            alert("Please login first");
            history('/login');
          }
	}, []
    );

   

	const fetchEmployees = () => {
		EmployeeService.getEmployees().then((response) => {
			setEmployees(response.data); 
		});

	}

	const editEmployee = (id) => {
		history(`/editEmployee/${id}`) 
	}

	const deleteEmployee = (id) => {
		EmployeeService.deleteEmployee(id).then(() => {
			fetchEmployees();  
			setMessage("Employee deleted successfully");
			setTimeout(() => {
				setMessage('');
			}, 2000);
		})
	}

	const viewEmployee = (id) => {
		history(`/employee/${id}`);
	}

    const navigateToEmployee = () => {
        history('/addEmployee')
      }

	return (
        <div>
            <br/> 
            {isLoggedIn && 
            <div className='container' >
                <h2>Customer Master Data Details</h2>
                <div className='row justify-content-center'>
                    <div className='column'>
                        <button className="btn" onClick={navigateToEmployee}>Add Employee</button>
                    </div>
                    <div className='column'>
                        <div className='registration-container' style={{maxWidth: "80%"}}>
                        <div className='row justify-content-center'>
                        <table className="table w-auto" >
                            <thead>
                                <tr className="">
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
                                                <button className='btn btn-success tblBtn' onClick={()=>editEmployee(emp.eid)}>
                                                    <span><EditIcon></EditIcon></span>
                                                </button>
                                                &nbsp;
                                                <button className='btn btn-danger tblBtn' onClick={()=>deleteEmployee(emp.eid)}>
                                                    <span><DeleteIcon></DeleteIcon></span>
                                                </button>
                                                &nbsp;
                                                
                                                <button className='btn btn-primary tblBtn' onClick={()=>viewEmployee(emp.eid)}>
                                                    <span><ViewIcon></ViewIcon></span>
                                                </button>
                                                </td> 
                                            
                                            </tr>
                                        )
                                    }
                            </tbody>
                            </table>
                        </div>
                        </div>
                    </div>
                </div>
            </div>}
        </div>
	)
}

export default Employee