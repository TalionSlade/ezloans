import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import LoanService from '../../service/LoanService';
import { useAuth } from '../AuthContext';
import '../../styles/Registeration.css';

function CreateLoan() {

	const navigate = useNavigate();
	const { id } = useParams(); 
	const { isLoggedIn } = useAuth();

	const [errors,setErrors] = useState('');
  	const [successMessage,setSuccessMessage] = useState('');
	const [type, setType] = useState('');
	const [existingTypes, setExistingTypes] = useState([]);
	const [duration, setDuration] = useState(0);

	useEffect(() => {
		if(isLoggedIn) {
			LoanService.getLoanCardTypes().then((res) => {
				setExistingTypes(res.data);
				console.log("Esisting type: ", res.data);
			  })
			if (id !== '_add') {
				LoanService.getLoanCardById(id).then((response) => {
					console.log(response);
					const loanCard = response.data;
					setType(loanCard.type);
					setDuration(loanCard.duration);
				})
		}}
		else {
			alert("Please login first");
            navigate('/login');
		}
	}, [id]);

	const saveOrUpdateLoan = (event) => {
		event.preventDefault();
		const loanCard = { type, duration };
		const validationErrors = validateForm();
		if (Object.keys(validationErrors).length === 0) {
			setErrors('');
			
			if (id === '_add') {
				
				LoanService.createLoan(loanCard).then(() => {
					setSuccessMessage('Loan Added successfully');
					setTimeout(() => {
						navigate('/loan'); 
					  }, 2000);
				});
			}
			else {
				LoanService.updateLoanCard(loanCard, id).then(() => {
					setSuccessMessage('Loan Updated successfully');
					setTimeout(() => {
						navigate('/loan'); 
					  }, 2000);
				});
			}
		} 
		else {
		  setErrors(validationErrors);
		}
	};

	const changeTypeHandler = (event) => {
		setType(event.target.value);
	};

	const changeDurationHandler = (event) => {
		setDuration(event.target.value);
	};

	const cancel = () => {
		navigate('/loan');
	};

	const getTitle = () => {
		if (id === '_add') {
			return <h1 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }} className="text-center">Add Loan</h1>;
		} else {
			return <h1 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }} className="text-center">Update Loan</h1>;
		}
	};

const validateForm = () => {
    let validationErrors = {};

    if (!type) {
      validationErrors.type = 'Type is required.';
    }
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
							<form className="form-grid">
								<div className="form-group">
									<label style={{ color: '#1f6e8c'}}> Type: </label>
									<input type ="text" placeholder="Loan Type" className="form-control" desc="type" class={errors.type && 'error'} value={type} onChange={changeTypeHandler} />
									{errors.type && <p className="error-message">{errors.type}</p>}
								</div>
								<div className="form-group">
									<label style={{ color: '#1f6e8c'}}>Duration: </label>
                                    <input type ="number" placeholder="Loan Duration" className="form-control" desc="duration" class={errors.duration && 'error'} value={duration} onChange={changeDurationHandler} />
									{errors.duration && <p className="error-message">{errors.duration}</p>}	
									
								</div>
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