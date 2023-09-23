import React, {useState, useRef, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Registeration.css';
import { Container, Col, Carousel, Row } from 'react-bootstrap';
import AuthenticationService from '../service/AuthenticationService';
import EmployeeService from '../service/EmployeeService';

const Registration = () => {

  const history = useNavigate();
   //defining state
   const [employee, setEmployee] = useState({
    email: '',
    fname: '',
    lname: '',
    designation: '',
    department:'',
    password: '',
    dob: '',
    doj: '',
    gender: '',
  });
  const [allEmployees, setAllEmployees] = useState([]);
  useEffect(() => {
		fetchAllEmployees();
	}, []);

	const fetchAllEmployees = () => {
		EmployeeService.getEmployees().then((response) => {
			setAllEmployees(response.data); // setting response to state - products
		});
	}
  const [errors,setErrors] = useState('');
  const [successMessage,setSuccessMessage] = useState('');

const handleChange = (e) => {
  const { name, value } = e.target;
  if (name.includes('.')) {   
    const [parent, child] = name.split('.');
    setEmployee((prevEmployee) => ({
      ...prevEmployee,
      [parent]: {
        ...prevEmployee[parent],
        [child]: value
      }
    }));
  } else {
    setEmployee((prevEmployee) => ({
      ...prevEmployee,
      [name]: value
    }));
  }
};

const handleSubmit = async (e) => {
  e.preventDefault();
  const validationErrors = validateForm();
  if (Object.keys(validationErrors).length === 0) {
    try {
      await AuthenticationService.registerEmployee(employee);
      setSuccessMessage('Registration successful!');
      alert("Registration Successfull");
      setTimeout(() => {
        history('/login'); // navigates to Login Component
      }, 3000);
    
    } 
    
    catch (error) {
      console.error('Registration error', error);
      setSuccessMessage('An error occurred during registration.');
    }
  } else {
    setErrors(validationErrors);
  }
};

const validateForm = () => {
  let validationErrors = {};

  if (!employee.email) {
    validationErrors.email = 'Email is required.';
  }
  if (!employee.fname) {
    validationErrors.fname = 'First Name is required.';
  }
  else if (!/^[a-zA-Z]*$/.test(employee.fname)) {
    validationErrors.fname = 'Enter Alphabets Only';
  }
  if (!employee.lname) {
    validationErrors.lname = 'Last Name is required.';
  }
  else if (!/^[a-zA-Z]*$/.test(employee.lname)) {
    validationErrors.lname = 'Enter Alphabets Only';
  }
  if (!employee.designation) {
    validationErrors.designation = 'Designation is required.';
  }
  if (!employee.department) {
    validationErrors.department = 'Department is required.';
  }
  if (!employee.gender) {
    validationErrors.gender = 'Gender is required.';
  }
  if (!employee.password) {
    validationErrors.password = 'Password is required.';
  } else if (employee.password.length < 6) {
    validationErrors.password = 'Password must be at least 6 characters.';
  }
  if (!employee.dob) {
    validationErrors.dob = 'Date of Birth is required.';
  } 
  if (!employee.doj) {
    validationErrors.doj = 'Date of Joining is required.';
  } 
  return validationErrors;
};

const myRef = useRef(null)

const executeScroll = () => myRef.current.scrollIntoView() 

  return (
    <div>
    <br />
    <div className="container">
      <h2>Customer Data Management</h2>
      <div className='row justify-content-center'>
        <div className='column'>
          <button>Add Customer Data</button>
        </div>
        <div className='column'>
          <button onClick={executeScroll}>View and Edit Customer Data</button>
        </div>
      </div>
    </div>
    <br></br>
    <div className="registration-container">
      <form onSubmit={handleSubmit} className="form-grid">
        <h2 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }}>Customer Registeration</h2>
        <div className="column">
      <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Email:</label>
          <input
            type="email"
            name="email"
            value={employee.email}
            onChange={handleChange}
            className={errors.email && 'error'}
          />
          {errors.email && <p className="error-message">{errors.email}</p>}
        </div>
        <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Password:</label>
          <input
            type="password"
            name="password"
            value={employee.password}
            onChange={handleChange}
            className={errors.password && 'error'}
          />
          {errors.password && <p className="error-message">{errors.password}</p>}
      </div>

        <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>First Name:</label>
          <input
            type="text"
            name="fname"
            value={employee.fname}
            onChange={handleChange}
            className={errors.fname && 'error'}
          />
          {errors.fname && <p className="error-message">{errors.fname}</p>}
        </div>

        <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Last Name:</label>
          <input
            type="text"
            name="lname"
            value={employee.lname}
            onChange={handleChange}
            className={errors.lname && 'error'}
          />
          {errors.lname && <p className="error-message">{errors.lname}</p>}
        </div>
    
        <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Gender:   </label>
          <select value={employee.gender} name="gender" onChange={handleChange}
            className={errors.gender && 'error'}>
            <option value="Default">Default</option>
            <option value="Female">Female</option>
            <option value="Male">Male</option>
            <option value="Other">Other</option>
          </select>
          {/* <input
            type="text"
            name="gender"
            value={employee.gender}
            onChange={handleChange}
            className={errors.gender && 'error'}
          /> */}
          {errors.gender && <p className="error-message">{errors.gender}</p>}
        </div>
 
      </div>
      <div className="column">
        <div className="form-group">
       
          <label style={{ color: '#1f6e8c'}}>Designation:</label>
          <input
            type="text"
            name="designation"
            value={employee.designation}
            onChange={handleChange}
            className={errors.designation && 'error'}
          />
          {errors.designation && <p className="error-message">{errors.designation}</p>}
        </div>

        <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Department:</label>
          <input
            type="text"
            name="department"
            value={employee.department}
            onChange={handleChange}
            className={errors.department && 'error'}
          />
          {errors.department && <p className="error-message">{errors.department}</p>}
        </div>

        <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Date of Birth:</label>
          <input
          type="date"
          name="dob"
          value={employee.dob}
          onChange={handleChange}
          className={errors.dob && 'error'}
        />
        {errors.dob && <p className="error-message">{errors.dob}</p>}
      </div>

      <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Date of Joining:</label>
          <input
          type="date"
          name="doj"
          value={employee.doj}
          onChange={handleChange}
          className={errors.doj && 'error'}
        />
        {errors.doj && <p className="error-message">{errors.doj}</p>}
      </div>
      </div>

      <div className="form-group" style={{ gridColumn: '1 / span 2' }}>
          <button type="submit" className="submit-button" onClick={handleSubmit}>
            Register
          </button>
          {successMessage && <p className="success-message">{successMessage}</p>}
        </div>
      </form>
      </div>

    <br></br>
      <div className="registration-container" >
      <h2 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }}>View All Customers</h2>
      <div className='row justify-content-center' ref={myRef}>
      <table className="table w-auto" style={{color: "rgb(31, 110, 140)"}}>
         <thead>
            <tr className="">
                <th> Employee Id </th>
                <th> Employee Name </th>
                <th> Designation </th>
                <th> Department </th>
                <th> Gender </th>
                <th> DOB </th>
                <th> DOJ </th>
                <th> Action </th>
            </tr>
        </thead>
        <tbody style={{color: "black"}}>
                {allEmployees.map(
                        emp => 
                        <tr key={emp.eid}>
                            <td> {emp.eid} </td>
                            <td> {emp.fname} </td>
                            <td> {emp.designation} </td>
                            <td> {emp.department} </td>
                            <td> {emp.gender} </td>
                            <td> {emp.dob} </td>
                            <td> {emp.doj} </td>
                            {/* <td>
                              <button className='btn btn-success' onClick={()=>editProduct(emp.pid)}>
                                <span><FontAwesomeIcon icon="edit"></FontAwesomeIcon></span>
                              </button>
                              &nbsp;
                              <button className='btn btn-danger' onClick={()=>deleteProduct(emp.pid)}>
                                <span><FontAwesomeIcon icon="trash"></FontAwesomeIcon></span>
                              </button>
                              &nbsp;
                              <button className='btn btn-primary' onClick={()=>viewProduct(emp.pid)}>
                                <span><FontAwesomeIcon icon="list"></FontAwesomeIcon></span>
                              </button>
                            </td> */}
                          
                        </tr>
                    )
                }
        </tbody>
        </table>
      </div>
      </div></div> 
  )
}

export default Registration;