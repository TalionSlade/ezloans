import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ItemService from '../../service/ItemService';
import { useAuth } from '../AuthContext';
import { IoTrash as DeleteIcon, IoPencil as EditIcon, IoEye as ViewIcon} from 'react-icons/io5';

function Item() {

	const history = useNavigate();

	const [items, setItems] = useState([]);
	const [message, setMessage] = useState('');
  const { isLoggedIn } = useAuth();
	useEffect(() => {
    if(isLoggedIn) {
		  fetchItems();
    }
    else {
			alert("Please login first");
      history('/login');
		}

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
			<br /> {isLoggedIn && 
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
                          <span className='tableIcon' onClick={() => editItem(item.itemId)}><EditIcon/></span>
                          &nbsp;
                          <span className='tableIcon' onClick={() => deleteItem(item.itemId)}><DeleteIcon/></span>
                          &nbsp;
                          <span className='tableIcon' onClick={() => viewItem(item.itemId)}><ViewIcon/></span>
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
		  </div>}
    </div>
	)
}

export default Item