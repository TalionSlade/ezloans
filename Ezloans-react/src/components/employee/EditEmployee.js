import React, {useState, useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import EmployeeService from '../../service/EmployeeService';
import { useAuth } from '../AuthContext';

// Used for updating details of existing employees
const EditEmployee = () => {

  const history = useNavigate();

  // Gets eid from URL
  const {id} = useParams();
  const { isLoggedIn } = useAuth();

  // States for updating fields
  const [fname, setFname] = useState();
	const [lname, setLname] = useState();
  const [email, setEmail] = useState();
  const [gender, setGender] = useState();
	const [department, setDepartment] = useState();
	const [designation, setDesignation] = useState();
  const [dob, setDob] = useState();
  const [doj, setDoj] = useState();
  const [message, setMessage] = useState('');
    
  // Contionally renders page for logged in employee. If not logged in, redirects to login url
  useEffect(() => {
    if(isLoggedIn) {
      // Sets existing employee data 
      EmployeeService.getEmployeeById(id).then((response) => {
        const emp = response.data;
        setFname(emp.fname);
        setLname(emp.lname);
        setEmail(emp.email);
        setGender(emp.gender);
        setDepartment(emp.department);
        setDesignation(emp.designation);
        setDob(emp.dob);
        setDoj(emp.doj);
      })
    }
    else {
      alert("Please login first");
      history('/login');
    }
  }, [id]);

  // Edits employee details if form fields are changed and sends request for updation
  const editEmployee = (event) => {
    event.preventDefault();
    const employee = { fname, lname, email, gender, designation, department, dob, doj };
    EmployeeService.updateEmployee(employee, id).then(() => {
      setMessage("Employee updated successfully");
      // If employee update is successful, redired to /employee page
      setTimeout(() => {
      history('/employee');
      }, 2000);
      });
  };

  // Handlers for form field changes
  const changeFnameHandler = (event) => {
		setFname(event.target.value);
	};
  const changeLnameHandler = (event) => {
		setLname(event.target.value);
	};
  const changeDesignationHandler = (event) => {
		setDesignation(event.target.value);
	};
  const changeDepartmentHandler = (event) => {
		setDepartment(event.target.value);
	};
  const changeDobHandler = (event) => {
		setDob(event.target.value);
	};
  const changeDojHandler = (event) => {
		setDoj(event.target.value);
	};

  // Function to navigate back to employee
  const navigateToEmployee = () => {
    history('/employee/all')
  }

  return (
    <div>
      <br/>
      <div className="registration-container">
        <h2>Edit Employee Details</h2>
        {/* Form for employee details. The fields are automatically populated with existing details */}
        <form>
          {/* First Name */}
          <div className="form-group">
            <label> First Name: </label>
            <input placeholder="First Name" name="fname" className="form-control" 
              value={fname} onChange={changeFnameHandler} />
          </div>
          {/* Last Name */}
          <div className="form-group">
            <label> Last Name: </label>
            <input placeholder="Last Name" name="lname" className="form-control"
              value={lname} onChange={changeLnameHandler} />
          </div>
          {/* Department */}
          <div className="form-group">
            <label> Department: </label>
            <input placeholder="Employee Department" name="department" className="form-control"
              value={department} onChange={changeDepartmentHandler} />
          </div>
          {/* Designation */}
          <div className="form-group">
            <label> Designation: </label>
            <input placeholder="Designation" name="designation" className="form-control"
              value={designation} onChange={changeDesignationHandler} />
          </div>
          {/* DoB */}
          <div className="form-group">
            <label> Date of Birth: </label>
            <input type="date" placeholder="Dob" name="dob" className="form-control"
              value={dob} onChange={changeDobHandler} />
          </div>
          {/* DoJ */}
          <div className="form-group">
            <label> Date of Joining: </label>
            <input type = "date" placeholder="Doj" name="doj" className="form-control"
              value={doj} onChange={changeDojHandler} />
          </div>
          {/* Buttons for Save and Cancel */}
          <button className="btn btn-success" onClick={editEmployee}>Save</button>
          <button className="btn btn-danger" onClick={navigateToEmployee} style={{ marginLeft: "10px" }}>Cancel</button>
          {message && <p className="success-message">{message}</p>}			
        </form>
      </div>
    </div>
  )
}

export default EditEmployee