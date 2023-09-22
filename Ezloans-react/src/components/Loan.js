import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Loan.css';

import AuthenticationService from '../service/AuthenticationService';
const Loan = () => {
  const history = useNavigate();
   //defining state
   const [loanCard, setloanCard] = useState({
    type: '',
    duration: 1,
  });
  const [errors,setErrors] = useState('');
  const [successMessage,setSuccessMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
      setloanCard((prevloanCard) => ({
        ...prevloanCard,
        [name]: value
  })); 
  };

const handleSubmit = async (e) => {
  // e.preventDefault();
  // const validationErrors = validateForm();
  // if (Object.keys(validationErrors).length === 0) {
  //   try {
      await AuthenticationService.addLoan(loanCard);
      setSuccessMessage('Added Successfully!');
      alert("Added Successfully!");
      // setTimeout(() => {
      //   history('/login'); // navigates to Login Component
      // }, 3000);
    
    // } 
    
    // catch (error) {
    //   console.error('Error', error);
    //   setSuccessMessage('An error when adding a loan.');
    // }
  // } 
  // else {
  //   setErrors(validationErrors);
  // }
};

  return (
    <div className="loan-container">
     <form>
        <h2 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }}>Loan Card Master Data Details</h2>
        <br>
        </br>

        <div className="form-group">
         
        <label style={{ color: '#1f6e8c'}}>Loan Type: </label>
        &nbsp; &nbsp;
        <select value={loanCard.type} name="type" onChange={handleChange}
            className={errors.type && 'error'}>
            <option value="Default">Default</option>
            <option value="Furniture">Furniture</option>
            <option value="Crockery">Crockery</option>
            <option value="Stationary">Stationary</option>
          </select>
        {loanCard.type && <p className="error-message">{errors.type}</p>}
      </div>

      <div className="form-group">
          <label style={{ color: '#1f6e8c'}}>Duration:  </label>
          &nbsp; &nbsp;
          <input
            type="number"
            name="duration"
            value={loanCard.duration}
            onChange={handleChange}
            className={loanCard.duration && 'error'}
          />
          {loanCard.duration && <p className="error-message">{errors.duration}</p>}
        </div>
        <br></br>
        <div className="form-group">
        <button type="submit" className="submit-button" onClick={handleSubmit}>
          Add Loan
        </button>
        {successMessage && <p className="success-message">{successMessage}</p>}
      </div>
      
        </form>
        </div>
        
  )
}

export default Loan;