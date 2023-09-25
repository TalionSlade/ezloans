import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ItemService from '../../service/ItemService';
import { IoTrash as DeleteIcon, IoPencil as EditIcon, IoEye as ViewIcon} from 'react-icons/io5';

function Item() {

	const history = useNavigate();

	const [items, setItems] = useState([]);
	const [message, setMessage] = useState([]);
	useEffect(() => {
		fetchItems();
	}, []);

	const fetchItems = () => {
		ItemService.getItems().then((response) => {
			setItems(response.data); 
		});
	}

	const addItem = () => {
		history('/addItem/_add'); 
	}

	const editItem = (id) => {
		history(`/addItem/${id}`) 
	}

	const deleteItem = (id) => {
		ItemService.deleteItem(id).then(() => {
			fetchItems(); 
			setMessage("Item deleted successfully");
			setTimeout(() => {
				setMessage('');
			}, 2000);
		})
	}

	const viewItem = (id) => {
		history(`/viewItem/${id}`);
	}

	return (
		<div>
			<br />
      <div className='container'>
			  <h2>Item Master Data Details</h2>
			  <br />
        <div className="row justify-content-center">
          <div className='column'>
            <button className="btn w-auto" onClick={addItem}>Add Item</button>
          </div>
          </div>
          <div className="column" >
            <div className='registration-container' style={{maxWidth: "80%"}}>
              <h2>View All Items</h2>
              <div className='row justify-content-center'>
              <table className="table w-auto">
                <thead>
                  <tr>
                    <th> Item Id</th>
                    <th> Description</th>
                    <th> Status</th>
                    <th> Make</th>
                    <th> Category</th>
                    <th> Valuation</th>
                    <th> Actions</th>
                  </tr>
                </thead>
                <tbody style={{color: "black"}}>
                  {items.map(
                    item =>
                      <tr key={item.itemId}>
                        <td> {item.itemId} </td>
                        <td> {item.desc} </td>
                        <td> {item.status} </td>
                        <td> {item.make} </td>
                        <td> {item.category} </td>
                        <td> {item.valuation} </td>
                        <td>
                          <button className='btn btn-success tblBtn' onClick={() => editItem(item.itemId)}>
                            <span><EditIcon/></span>
                          </button>
                          &nbsp;
                          <button className='btn btn-danger tblBtn' onClick={() => deleteItem(item.itemId)}>
                            <span><DeleteIcon/></span>
                          </button>
                          &nbsp;
                          <button className='btn btn-primary tblBtn' onClick={() => viewItem(item.itemId)}>
                            <span><ViewIcon/></span>
                          </button>
                        </td>

                      </tr>
                  )
                  }
                </tbody>
              </table>

              </div>
              {message && <div className="alert alert-success">{message}</div>}
            </div>
          </div>
          
		  </div>
    </div>
	)
}

export default Item