import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import ItemService from '../../service/ItemService';


function CreateItem() {

	const navigate = useNavigate();

	const { id } = useParams(); 

	const [desc, setDesc] = useState('NA');
	const [status, setStatus] = useState('Yes');
	const [make, setMake] = useState('Other');
	const [category, setCategory] = useState('Other');
    const [valuation, setValuation] = useState(0);

	useEffect(() => {
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
	}, [id]);

	const saveOrUpdateItem = (event) => {
		event.preventDefault();
		const item = { desc, status, make, category, valuation };

		if (id === '_add') {
			ItemService.createItem(item).then(() => {
				navigate('/item');
			});
		}
		else {
			ItemService.updateItem(item, id).then(() => {
				navigate('/item');
			});
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
		navigate('/item');
	};

	const getTitle = () => {
		if (id === '_add') {
			return <h1 className="text-center">Add Item</h1>;
		} else {
			return <h1 className="text-center">Update Item</h1>;
		}
	};

	return (
		<div>
			<br></br>
			<div className="container">
				<div className="row justify-content-center">
					<div className="form-outline col-12 mb-4">
						{getTitle()}
						<div className="card-body">
							<form>
								<div className="form-group">
									<label> Description: </label>
									<input placeholder="Item Description" desc="desc" className="form-control"
										value={desc} onChange={changeDescHandler} />
								</div>
								<div className="form-group">
									<label> Status: </label>
                                    <select className="form-control" placeholder= "Item Status" desc="status" value={status} onChange={changeStatusHandler}>
                                        <option value="Yes">Yes</option>
                                        <option value="No">No</option>
                                    </select>
								</div>
								<div className="form-group">
									<label> Make: </label>
									<select className="form-control" placeholder= "Item Make" desc="make" value={make} onChange={changeMakeHandler}>
                                        <option value="Wooden">Wooden</option>
                                        <option value="Glass">Glass</option>
                                        <option value="Plastic">Plastic</option>
                                        <option value="Other">Other</option>
                                    </select>
								</div>
								<div className="form-group">
									<label> Category: </label>
									<select className="form-control" placeholder= "Item Category" desc="category" value={category} onChange={changeCategoryHandler}>
                                        <option value="Furniture">Furniture</option>
                                        <option value="Stationary">Stationary</option>
                                        <option value="Crockery">Crockery</option>
                                        <option value="Other">Other</option>
                                    </select>
								</div>
                                <div className="form-group">
									<label> Valuation: </label>
									<input type="number" placeholder="Valuation" desc="valutaion" className="form-control"
										value={valuation} onChange={changeValuationHandler} />
								</div>

								<button className="btn btn-success" onClick={saveOrUpdateItem}>Save</button>
								<button className="btn btn-danger" onClick={cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>

	)
}

export default CreateItem