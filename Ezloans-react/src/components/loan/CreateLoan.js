import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import LoanService from '../../service/LoanService';
import { useAuth } from '../AuthContext';
import '../../styles/Registeration.css';

// Function to create or update Loan Cards for specific eid
function CreateLoan() {

	const history = useNavigate();
	// Setting id with eid from url using useParams hook
	const { id } = useParams(); 
	const { isLoggedIn } = useAuth();
	const [errors,setErrors] = useState('');
	const [successMessage,setSuccessMessage] = useState('');
	const [type, setType] = useState('');
	const [existingTypes, setExistingTypes] = useState([]);
	const [duration, setDuration] = useState(0);

	// Used for conditional rendering of page based on login status. If not logged in, redirects to login
	useEffect(() => {
		if(isLoggedIn) {
      // Get all loan cards in db
			LoanService.getLoanCardTypes().then((res) => {
				setExistingTypes(res.data);
			})
			if (id !== '_add') {
        // Update a loan card using loanservice
				LoanService.getLoanCardById(id).then((response) => {
					const loanCard = response.data;
					setType(loanCard.type);
					setDuration(loanCard.duration);
				})
      }
    }
		else {
			alert("Please login first");
      history('/login');
		}
	}, [id]);

  // Function to save or update loan based on request
	const saveOrUpdateLoan = (event) => {
		event.preventDefault();
    // Creating loan card object
		const loanCard = { type, duration };
		const validationErrors = validateForm();
    // Checking form validation
		if (Object.keys(validationErrors).length === 0) {
			setErrors('');
      // Create new loan card
			if (id === '_add') {
				LoanService.createLoan(loanCard).then(() => {
					setSuccessMessage('Loan Added successfully');
					setTimeout(() => {
						history('/loan'); 
					}, 2000);
				});
			}
      // Update loan card
			else {
				LoanService.updateLoanCard(loanCard, id).then(() => {
					setSuccessMessage('Loan Updated successfully');
					setTimeout(() => {
						history('/loan'); 
					}, 2000);
				});
			}
		} 
		else {
      setErrors(validationErrors);
		}
	};

  // Handlers to handle input field changes
	const changeTypeHandler = (event) => {
		setType(event.target.value);
	};
	const changeDurationHandler = (event) => {
		setDuration(event.target.value);
	};

  // Function to go back to loan
	const cancel = () => {
		history('/loan');
	};

  // Function to change title based on request
	const getTitle = () => {
		if (id === '_add') {
			return <h1 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }} className="text-center">Add Loan</h1>;
		} else {
			return <h1 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }} className="text-center">Update Loan</h1>;
		}
	};
  // Form validations
  const validateForm = () => {
    let validationErrors = {};

    if (!type) {
      validationErrors.type = 'Type is required.';
    }
    // Check for duplicate item type
    if (id === '_add' && existingTypes.includes(type)) {
      validationErrors.type = 'Type already exists';
    }
    if (!duration) {
      validationErrors.duration = 'Duration is required.';
    }
    return validationErrors;
  };

	return (
		<div>
			<br></br> {isLoggedIn && 
      <div className="registration-container">
        <div className="container">
          <div className="row justify-content-center">
            <div className="form-outline col-12 mb-4">
              {getTitle()}
              <div className="card-body">
                {/* Form for loan card details */}
                <form className="form-grid">
                  {/* Type */}
                  <div className="form-group">
                    <label style={{ color: '#1f6e8c'}}> Type: </label>
                    <input type ="text" placeholder="Loan Type" className="form-control" desc="type" class={errors.type && 'error'} value={type} onChange={changeTypeHandler} />
                    {errors.type && <p className="error-message">{errors.type}</p>}
                  </div>
                  {/* Duration */}
                  <div className="form-group">
                    <label style={{ color: '#1f6e8c'}}>Duration: </label>
                    <input type ="number" placeholder="Loan Duration" className="form-control" desc="duration" class={errors.duration && 'error'} value={duration} onChange={changeDurationHandler} />
                    {errors.duration && <p className="error-message">{errors.duration}</p>}	
                    
                  </div>
                  {/* Buttons to add/go back */}
                  <button className="btn btn-success" onClick={saveOrUpdateLoan}>Save</button>
                  <button className="btn btn-danger" onClick={cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
                </form>
                {successMessage && <p className="success-message">{successMessage}</p>}
              </div>
            </div>
          </div>
        </div>
      </div>}
    </div>

	)
}

export default CreateLoan;