import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import '../../styles/Registeration.css';
import AuthenticationService from '../../service/AuthenticationService';
import { useAuth } from '../AuthContext';
import EmployeeService from '../../service/EmployeeService';

// CreateEmployee() used to add a new employee
const CreateEmployee = () => {

  const history = useNavigate();
  // useAuth states for state management
  const { isLoggedIn } = useAuth();
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
  const [errors,setErrors] = useState('');
  const [successMessage,setSuccessMessage] = useState('');
  const [existingEmployees, setExistingEmployees] = useState([]);
  
  // Used to render page contents based on login status. If user is not logged in, redirects to login page
  useEffect(() => {
    if(isLoggedIn) {
      // Get existing employee details in db. Used to check for duplicate employee entry
      EmployeeService.getEmployees().then((res) => {
        setExistingEmployees(res.data);
      })
    }
    else {
      alert("Please login first");
      history('/login');
    }
  }, []);

  // handleChange(): Sets employee object fields based on changes by user
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

  // handleSubmit(): Async function to send request for registration of employee and handle errors
  const handleSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validateForm();
    // Checking if form is validated
    if (Object.keys(validationErrors).length === 0) {
      setErrors('');
      try {
        // Send request for registrationg
        await AuthenticationService.registerEmployee(employee);
        setSuccessMessage('User Added successfully');
        setTimeout(() => {
          history('/employee'); 
        }, 2000);
      } 
      catch (error) {
        // Registration errors
        console.error('Add User error', error);
        setSuccessMessage('An error occurred during add user.');
      }
    } else {
      setErrors(validationErrors);
    }
  };

  // Form validation
  const validateForm = () => {
    let validationErrors = {};

    if (!employee.email) {
      validationErrors.email = 'Email is required.';
    }
    // Checks if admin is adding a duplicate email ID
    if (existingEmployees.some( e => e.email == employee.email)) {
      validationErrors.email = 'Employee with given email already exists';
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
    if (employee.doj < employee.dob) {
      validationErrors.doj = "Date of Joining cannot be less than Date of Birth";
    }
    
    return validationErrors;
  };

  return (
    <div>
      <br/><br/> 
      {/* Conditionally rendering page contents only is user is logged in */}
      {isLoggedIn && 
      <div className="registration-container">
        <form onSubmit={handleSubmit} className="form-grid">
          <h2 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }}>Customer Master Data Details</h2>
          <div className="column">
            {/*First Name*/}
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
            {/* Last Name */}
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
            {/* Email */}
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
            {/* Password */}
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
            {/* Gender */}
            <div className="form-group">
              <label style={{ color: '#1f6e8c'}}>Gender:   </label>
              <select value={employee.gender} name="gender" onChange={handleChange}
                className= 'form-control'>
                <option hidden="hidden">Default</option>
                <option value="Female">Female</option>
                <option value="Male">Male</option>
                <option value="Other">Other</option>
              </select>
              {errors.gender && <p className="error-message">{errors.gender}</p>}
            </div>
          </div>
          <div className="column">
            {/* Designation */}
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
            {/* Department */}
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
            {/* DoB */}
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
            {/* DoJ */}
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
            {/* Submit Button */}
            <button type="submit" className="submit-button" onClick={handleSubmit}>
              Add Data
            </button>
            {successMessage && <p className="success-message">{successMessage}</p>}
          </div>
        </form>
      </div>}
      <br></br>
    </div> 
  )
}

export default CreateEmployee;