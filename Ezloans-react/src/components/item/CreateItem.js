import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import ItemService from '../../service/ItemService';
import LoanService from '../../service/LoanService';
import { useAuth } from '../AuthContext';
import '../../styles/Registeration.css';

function CreateItem() {

	const history = useNavigate();
	const [errors,setErrors] = useState('');
	const [successMessage,setSuccessMessage] = useState('');
	const { id } = useParams(); 
	const { isLoggedIn } = useAuth();
	const [desc, setDesc] = useState('');
	const [status, setStatus] = useState('');
	const [make, setMake] = useState('');
	const [category, setCategory] = useState('');
    const [valuation, setValuation] = useState(0);
	const [loanCardCategories, setLoanCardCategories] = useState([]);

	useEffect(() => {
		if(isLoggedIn) {
		LoanService.getLoanCardTypes().then((res) => {
		  const loanCategories = res.data;
		  console.log("Loancard res: ",res.data);
		  setLoanCardCategories(loanCategories);
		});
		if (id !== '_add') {
			ItemService.getItemById(id).then((response) => {
				const item = response.data;
				setDesc(item.desc);
				setStatus(item.status);
				setMake(item.make);
				setCategory(item.category);
                setValuation(item.valuation);
			})
		}
	}
		else {
		  alert("Please login first");
		  history('/login');
		}
		
	  }, [id]);

	const validateForm = () => {
		let validationErrors = {};
	
		if (!desc) {
		  validationErrors.desc = 'Description is required.';
		}
		
		if (!status) {
			validationErrors.status = 'Status is required.';
		  }

		if (!make) {
			validationErrors.make = 'Make is required.';
		}

		if (!category) {
			validationErrors.category = 'Category is required.';
		}
		
		if (!valuation) {
			validationErrors.valuation = 'Valuation is required.';
		}
		
		return validationErrors;
	  };

	const saveOrUpdateItem = (event) => {
		event.preventDefault();
		const item = { desc, status, make, category, valuation };
		const validationErrors = validateForm();
		if (Object.keys(validationErrors).length === 0){
			setErrors('');
			if (id === '_add') {
				ItemService.createItem(item).then(() => {
					setSuccessMessage('Item Added Successfully');
						setTimeout(() => {
							history('/item'); 
						  }, 3000);
				});
			}
			else {
				ItemService.updateItem(item, id).then(() => {
					setSuccessMessage('Item Updated Successfully');
						setTimeout(() => {
							history('/item'); 
						  }, 3000);
				});
			}
		}
		else{
			setErrors(validationErrors);
		}
		
	};

	const changeDescHandler = (event) => {
		setDesc(event.target.value);
	};

	const changeStatusHandler = (event) => {
		setStatus(event.target.value);
	};

	const changeMakeHandler = (event) => {
		setMake(event.target.value);
	};

	const changeCategoryHandler = (event) => {
		setCategory(event.target.value);
	};

    const changeValuationHandler = (event) => {
		setValuation(event.target.value);
	};


	const cancel = () => {
		history('/item');
	};

	const getTitle = () => {
		if (id === '_add') {
			return <h1 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }}  className="text-center">Add Item</h1>;
		} else {
			return <h1 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }}  className="text-center">Update Item</h1>;
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
							<form className="form-grid">
							<div className="column">
								<div className="form-group">
									<label style={{ color: '#1f6e8c'}}> Description: </label>
									<input placeholder="Item Description" desc="desc" className="form-control"
										value={desc} onChange={changeDescHandler} />
									{errors.desc && <p className="error-message">{errors.desc}</p>}
								</div>
								<div className="form-group">
									<label style={{ color: '#1f6e8c'}}> Status: </label>
                                    <select className="form-control" placeholder= "Item Status" desc="status" value={status} onChange={changeStatusHandler}>
										<option hidden="hidden">Default</option>
                                        <option value="Yes">Yes</option>
                                        <option value="No">No</option>
                                    </select>
									{errors.status && <p className="error-message">{errors.status}</p>}
								</div>
								<div className="form-group">
									<label style={{ color: '#1f6e8c'}}> Make: </label>
									<select className="form-control" placeholder= "Item Make" desc="make" value={make} onChange={changeMakeHandler}>
										<option hidden="hidden">Default</option>
										<option value="Wooden">Wooden</option>
                                        <option value="Glass">Glass</option>
                                        <option value="Plastic">Plastic</option>
                                        <option value="Other">Other</option>
                                    </select>
									{errors.make && <p className="error-message">{errors.make}</p>}
								</div>
								</div> 
								<div className="column">
								<div className="form-group">
									<label style={{ color: '#1f6e8c'}}> Category: </label>
									<select className="form-control" placeholder= "Item Category" desc="category" value={category} onChange={changeCategoryHandler}>
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
									<label style={{ color: '#1f6e8c'}}> Valuation: </label>
									<input class={errors.valuation && 'error'} type="number" placeholder="Valuation" desc="valutaion" className="form-control"
										value={valuation} onChange={changeValuationHandler} />
									{errors.valuation && <p className="error-message">{errors.valuation}</p>}
								</div>

								</div>
								<br></br>
								<div className="form-group" style={{ gridColumn: '1 / span 2' }}>
								<button className="btn btn-success" onClick={saveOrUpdateItem}>Add Data</button>
								<button className="btn btn-danger" onClick={cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
								{successMessage && <p className="success-message">{successMessage}</p>}
							</div>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
		</div>
	)
}

export default CreateItem