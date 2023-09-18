import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Registeration.css';

import AuthenticationService from '../service/AuthenticationService';

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
  const [errors,setErrors] = useState('');
  const [successMessage,setSuccessMessage] = useState('');
  
  /*
The JavaScript spread operator (...) allows us to quickly copy all or 
part of an existing array or object into another array or object.
*/
//Updates the state of a empolyee Object when user enters data in the input fields
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

  return (
    <div> <br></br>
    <div className='registration-container'>
      <h2 style={{color: 'green'}}>Employee Registration</h2>
      <br></br>
      {successMessage && <p className='success-message'>{successMessage}</p>}
      <form onSubmit={handleSubmit}>
      <div className="form-group">
          <label>Email:</label>
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
          <label>First Name:</label>
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
          <label>Last Name:</label>
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
          <label>Designation:</label>
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
          <label>Department:</label>
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
          <label>Gender: </label>
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


        <div className="form-group">
          <label>Date of Birth:</label>
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
          <label>Date of Joining:</label>
          <input
          type="date"
          name="doj"
          value={employee.doj}
          onChange={handleChange}
          className={errors.doj && 'error'}
        />
        {errors.doj && <p className="error-message">{errors.doj}</p>}
      </div>

      <div className="form-group">
          <label>Password:</label>
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
          <button type="submit" className="submit-button" onClick={handleSubmit}>
            Register
          </button>
          {successMessage && <p className="success-message">{successMessage}</p>}
        </div>
      </form>
    </div>   
    </div>
  )
}

export default Registration;