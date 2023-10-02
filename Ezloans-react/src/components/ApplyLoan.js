import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Registeration.css';
import { useAuth } from './AuthContext';
import EmployeeService from '../service/EmployeeService';
import ItemService from '../service/ItemService';
import LoanService from '../service/LoanService';

// ApplyLoan() to enable user to apply for a loan
const ApplyLoan = () => {

  const history = useNavigate();
  // authContext hooks for state management
  const { isLoggedIn, userId } = useAuth();
  // States defined for apply loan
  const [errors, setErrors] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const [descriptions, setDescriptions] = useState([]);
  const [valuation, setValuation] = useState(0);
  const [make, setMake] = useState('');
  const [loanCardCategories, setLoanCardCategories] = useState([]);
  const [employeeCard, setEmployeeCard] = useState({
    eid: userId,
    category: '',
    description: '',
    value: valuation,
    make: make
  });

  // Used to condtionally render page status. If user is not logged in, it redirects to login page
  useEffect(() => {
    if(isLoggedIn) {
      // If user is logged in, get loan card types present in database
      LoanService.getLoanCardTypes().then((res) => {
        const loanCategories = res.data;
        setLoanCardCategories(loanCategories);
      });
    }
    else {
      alert("Please login first");
      history('/login');
    }
    
  }, []);

  // Conditionally set Description dropdown after user selects Category
  const handleCategoryChange = async (e) => {
    e.preventDefault();
    employeeCard.category = e.target.value;
    const response = await ItemService.getDescriptions(e.target.value);
    setDescriptions(response.data);
    console.log("getdescriptions res: ",response.data);
  }

  // Conditionally set Value and Make based on Item Description selected by user
  const handleDescriptionChange = async (e) => {
    e.preventDefault();
    employeeCard.description = e.target.value;
    const idx = descriptions.findIndex((desc) => desc.description == e.target.value);
    const value = descriptions[idx].Valuation;
    const makee = descriptions[idx].Make;
    setValuation(value);
    setMake(makee);
    console.log("idx: ",descriptions[idx]);
  }
  
  // Form validation
  const validateForm = () => {
    let validationErrors = {};
    if (!descriptions) {
      validationErrors.descriptions = 'Description is required.';
    }
    if (!valuation) {
      validationErrors.valuation = 'Valuation is required.';
    }
    if (!employeeCard.category) {
      validationErrors.category = 'Category is required.';
    }
    if (!employeeCard.description) {
      validationErrors.description = 'Description is required.';
    }
    if (!make) {
      validationErrors.make = 'Make is required.';
    }
    return validationErrors;
  };

  // Applies for Loan after user enters all details
  const handleSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validateForm();
    // Check for validation
		if (Object.keys(validationErrors).length === 0) {
      try {
        await EmployeeService.applyLoan(employeeCard);
        setErrors('');
        setSuccessMessage('Loan added successful!y');
        setTimeout(() => {
          history(`/viewItem/emp/${userId}`); 
        }, 3000);
      } 
      catch (error) {
        console.error('Apply Loan error', error);
        setSuccessMessage('An error occurred during apply loan.');
      }
    }
    else {
      setErrors(validationErrors);
    }
  };

  return (
  <div>
    <br/><br/>
    {/* Conditional rendering of page based on login status */}
    {isLoggedIn && 
    <div className="registration-container">
      {/* Form for apply loan */}
      <form onSubmit={handleSubmit} className="form-grid">
        <div style={{ gridColumn: '1 / span 2' }}>
            <h2 style = {{color: '#1f6e8c'}}> Apply Loan </h2>
            <hr/>
            <h6 style = {{color: 'black'}}> Employee ID: {userId} </h6>
            <hr/>
        </div>
        <div className='column'>
          <div className="form-group">
            {/* Displaying item categories present in DB */}
            <label style={{ color: '#1f6e8c'}}>Category:</label>
            <select class={errors.category && 'error'} className="form-control" placeholder= "Item Category" desc="category" value={employeeCard.category} name="category" onChange={handleCategoryChange}>
              <option hidden="hidden">Default</option>
                { loanCardCategories.map(
                  loanCard => 
                  <option value={loanCard}>{loanCard}</option>
                )
                }
            </select>
            {errors.category && <p className="error-message">{errors.category}</p>}
          </div>
          <div className="form-group">
            {/* Make is automatically set after User selects item description */}
            <label style={{ color: '#1f6e8c'}}>Make:</label>
            <input
              class={errors.make && 'error'}
              type="text"
              name="make"
              value={make} />
            {errors.make && <p className="error-message">{errors.make}</p>}
          </div>
        </div>
        <div className='column'>
          <div className="form-group">
            {/* Item descriptions are conditionally renderred based on item category */}
            <label style={{ color: '#1f6e8c'}}>Description:</label>
            <select class={errors.description && 'error'} className="form-control" placeholder= "Item Description" desc="description" value={employeeCard.description} onChange={handleDescriptionChange}>
              <option hidden="hidden">Default</option>
                { descriptions.map(
                  desc => 
                  <option key = {desc.id} value={desc.description}>{desc.description}</option> )
                }
            </select>
            {errors.description && <p className="error-message">{errors.description}</p>}
          </div> 
          <div className="form-group">
            <label style={{ color: '#1f6e8c'}}>Value:   </label>
            <input
              class={errors.valuation && 'error'}
              type="number"
              name="value"
              value={valuation} />
            {errors.valuation && <p className="error-message">{errors.valuation}</p>}
          </div>
        </div>
        <div className="form-group" style={{ gridColumn: '1 / span 2' }}>
          <button type="submit" className="submit-button" onClick={handleSubmit}>
            Apply
          </button>
          {successMessage && <p className="success-message">{successMessage}</p>}
        </div>
      </form>
    </div>}
    <br/>
  </div> 
  )
}

export default ApplyLoan;