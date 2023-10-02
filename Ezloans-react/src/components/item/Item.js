import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ItemService from '../../service/ItemService';
import { useAuth } from '../AuthContext';
import { IoTrash as DeleteIcon, IoPencil as EditIcon, IoEye as ViewIcon} from 'react-icons/io5';

// Item() displays all existing items and renders links for View/Delete/Update Items
function Item() {

	const history = useNavigate();
  const { isLoggedIn } = useAuth();
	const [items, setItems] = useState([]);
	const [message, setMessage] = useState('');
  
  // Used for conditional rendering of page based on login status. If not logged in, redirects to login
	useEffect(() => {
    if(isLoggedIn) { 
      // Fetching existing items in DB
      fetchItems(); 
    }
    else {
			alert("Please login first");
      history('/login');
		}

	}, []);

  // Functino to get all items
	const fetchItems = () => {
		ItemService.getItems().then((response) => {
			setItems(response.data); 
		});
	}

  // Navigate to add item page
	const addItem = () => {
		history('/addItem/_add'); 
	}

  // Navigate to update item
	const editItem = (id) => {
		history(`/addItem/${id}`) 
	}

  // Delete item based on item id. Sends delete request via item service
	const deleteItem = (id) => {
		ItemService.deleteItem(id).then(() => {
			fetchItems(); 
			setMessage("Item deleted successfully");
			setTimeout(() => {
				setMessage('');
			}, 2000);
		})
	}

  // Navigate to view specific item details
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
                    <th> Item ID</th>
                    <th> Description</th>
                    <th> Status</th>
                    <th> Make</th>
                    <th> Category</th>
                    <th> Valuation</th>
                    <th> Actions</th>
                  </tr>
                </thead>
                <tbody style={{color: "black"}}>
                  {/* Displaying items existing in DB */}
                  {items.map(
                    item =>
                      <tr key={item.itemId}>
                        <td> {item.itemId} </td>
                        <td> {item.desc} </td>
                        <td> {item.status} </td>
                        <td> {item.make} </td>
                        <td> {item.category} </td>
                        <td> {item.valuation} </td>
                        {/* Links to view/update/delete */}
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
		  </div> }
    </div>
	)
}

export default Item