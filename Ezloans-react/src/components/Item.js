import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Registeration.css';
import ItemService from '../service/ItemService';

const ItemRegistration = () => {

  const history = useNavigate();
   //defining state
//    this.desc = desc;
// 		this.status = status;
// 		this.make = make;
// 		this.category = category;
// 		this.valuation = valuation;
   const [item, setItem] = useState({
    desc: '',
    status: '',
    make: '',
    category: '',
    valuation:1,
  });
  const [errors,setErrors] = useState('');
  const [successMessage,setSuccessMessage] = useState('');

const handleChange = (e) => {
  const { name, value } = e.target;
    // if(name === "valuation") {
    //     setItem([valuat)
    // }
    // setItem(desc) = e.target.value
    setItem((prevItem) => ({
      ...prevItem,
      [name]: value
    }));
  }


const handleSubmit = async (e) => {
  e.preventDefault();
//   const validationErrors = validateForm();
//   if (Object.keys(validationErrors).length === 0) {
//     try {
      await ItemService.addItem(item);
      setSuccessMessage('Registration successful!');
      alert("Registration Successfull");
    
    // } 
    
    // catch (error) {
    //   console.error('Registration error', error);
    //   setSuccessMessage('An error occurred during registration.');
    // }
//   } else {
//     setErrors(validationErrors);
//   }
};

// const validateForm = () => {
//   let validationErrors = {};

//   if (!item.email) {
//     validationErrors.email = 'Email is required.';
//   }
//   if (!item.fname) {
//     validationErrors.fname = 'First Name is required.';
//   }
//   else if (!/^[a-zA-Z]*$/.test(item.fname)) {
//     validationErrors.fname = 'Enter Alphabets Only';
//   }
//   if (!item.lname) {
//     validationErrors.lname = 'Last Name is required.';
//   }
//   else if (!/^[a-zA-Z]*$/.test(item.lname)) {
//     validationErrors.lname = 'Enter Alphabets Only';
//   }
//   if (!item.designation) {
//     validationErrors.designation = 'Designation is required.';
//   }
//   if (!item.department) {
//     validationErrors.department = 'Department is required.';
//   }
//   if (!item.gender) {
//     validationErrors.gender = 'Gender is required.';
//   }
//   if (!item.password) {
//     validationErrors.password = 'Password is required.';
//   } else if (item.password.length < 6) {
//     validationErrors.password = 'Password must be at least 6 characters.';
//   }
//   if (!item.dob) {
//     validationErrors.dob = 'Date of Birth is required.';
//   } 
//   if (!item.doj) {
//     validationErrors.doj = 'Date of Joining is required.';
//   } 
//   return validationErrors;
// };

  return (
    <div>
    <br />
    <div className="registration-container">
      <form onSubmit={handleSubmit} className="form-grid">
        <h2 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }}>Item Master Data Details</h2>
        <div className="column">
      <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Item Description:</label>
          <input
            type="text"
            name="desc"
            value={item.desc}
            onChange={handleChange}
            className={errors.desc && 'error'}
          />
          {errors.desc && <p className="error-message">{errors.desc}</p>}
        </div>
        <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Item Status:</label>
          <input
            type="text"
            name="status"
            value={item.status}
            onChange={handleChange}
            className={errors.status && 'error'}
          />
          {errors.status && <p className="error-message">{errors.status}</p>}
      </div>

        <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Item Category</label>
          <input
            type="text"
            name="category"
            value={item.category}
            onChange={handleChange}
            className={errors.category && 'error'}
          />
          {errors.category && <p className="error-message">{errors.category}</p>}
        </div>

        

        
 
      </div>

      <div className="column">
      <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Item Value:</label>
          <input
            type="number"
            name="valuation"
            value={item.valuation}
            onChange={handleChange}
            className={errors.valuation && 'error'}
          />
          {errors.valuation && <p className="error-message">{errors.valuation}</p>}
        </div>
    
        <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Item Make: </label>
          <input
            type="text"
            name="make"
            value={item.make}
            onChange={handleChange}
            className={errors.make && 'error'}
          />
          {errors.make && <p className="error-message">{errors.make}</p>}
        </div>

      </div>

      <div className="form-group" style={{ gridColumn: '1 / span 2' }}>
          <button type="submit" className="submit-button" onClick={handleSubmit}>
            Add Data
          </button>
          {successMessage && <p className="success-message">{successMessage}</p>}
        </div>
      
      </form>
      </div>
      </div>
  )
}

export default ItemRegistration;