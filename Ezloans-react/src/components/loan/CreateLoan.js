import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import LoanService from '../../service/LoanService';
import Loan from './Loan';
import '../../styles/Registeration.css';

function CreateLoan() {

	const navigate = useNavigate();

	const { id } = useParams(); 

	const [type, setType] = useState('NA');
	const [duration, setDuration] = useState(0);

	useEffect(() => {
		if (id !== '_add') {
			LoanService.getLoanCardById(id).then((response) => {
				const loanCard = response.data;
				setType(loanCard.type);
				setDuration(loanCard.duration);
			})
		}
	}, [id]);

	const saveOrUpdateLoan = (event) => {
		event.preventDefault();
		const loanCard = { type, duration};

		if (id === '_add') {
			LoanService.createLoan(loanCard).then(() => {
				navigate('/loan');
			});
		}
        else {
			LoanService.updateLoanCard(loanCard, id).then(() => {
				navigate('/loan');
			});
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

	return (
		<div>
			<br></br>
            <div className="registration-container">
			<div className="container">
				<div className="row justify-content-center">
					<div className="form-outline col-12 mb-4">
						{getTitle()}
						<div className="card-body">
							<form lassName="form-grid">
								<div className="form-group">
									<label style={{ color: '#1f6e8c'}}> Type: </label>
                                    <select className="form-control" placeholder= "Loan Type" desc="type" value={type} onChange={changeTypeHandler}>
                                    <option value="Default">Default</option>
                                    <option value="Furniture">Furniture</option>
                                    <option value="Crockery">Crockery</option>
                                    <option value="Stationary">Stationary</option>
                                    </select>
								</div>
								<div className="form-group">
									<label style={{ color: '#1f6e8c'}}>Duration: </label>
                                    <input type ="number" placeholder="Loan Duration" desc="duration" className="form-control"
										value={duration} onChange={changeDurationHandler} />
								</div>
								
								
                                
								<button className="btn btn-success" onClick={saveOrUpdateLoan}>Save</button>
								<button className="btn btn-danger" onClick={cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
        </div>

	)
}

export default CreateLoan;