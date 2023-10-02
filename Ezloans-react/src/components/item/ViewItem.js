import React, {useState, useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import ItemService from '../../service/ItemService';
import { useAuth } from '../AuthContext';

// Function to view item details based on itemID
const ViewItem = () => {
  const history = useNavigate();
  const { isLoggedIn } = useAuth();
  // Get ItemId from url with useParams Hook
  const {id} = useParams();
  const [item, setItem] = useState({});

  // Used for conditional rendering of page based on login status. If not logged in, redirects to login
  useEffect(() => {
    if(isLoggedIn) {
      // Get specific item details and set item object define as state
      ItemService.getItemById(id).then((response) => {
      setItem(response.data);
    })}
    else {
      alert("Please login first");
      history('/login');
    }
  }, [id]);

  // Function to navigate back
  const backItem = () => {
    history('/item')
  }

  return (
    <div>
      {/* Conditional rendering of page contents based on login status */}
      <br /> {isLoggedIn && 
      <div className="card col-md-6 offset-md-3">
        <br/>
        <h3 className="text-center" style={{color: "#1f6e8c"}}>View Item Details</h3><hr/>
        <div className="card-body">
          {/* Item ID */}
          <div className="row">
            <label>Item ID: </label>
            <div class="text-success fw-bolder">{item.itemId}</div><hr/>
          </div>
          {/* Description */}
          <div className="row">
            <label>Item Description: </label>
            <div class="text-success fw-bolder">{item.desc}</div><hr/>
          </div>
          {/* Status */}
          <div className="row">
            <label>Item Status: </label>
            <div class="text-success fw-bolder">{item.status}</div><hr/>
          </div>
          {/* Make */}
          <div className="row">
            <label>Item Make: </label>
            <div class="text-success fw-bolder">{item.make}</div><hr/>
          </div>
          {/* Category */}
          <div className="row">
            <label>Item Category:</label>
            <div class="text-success fw-bolder">{item.category}</div><hr/>
          </div>
          {/* Valuation */}
          <div className="row">
            <label>Item Valuation: </label>
            <div class="text-success fw-bolder">{item.valuation}</div><hr/>
          </div>
        </div>
        <div className = "row justify-content-center">
          {/* Button to navigate back */}
          <button className="btn btn-info w-auto" onClick={backItem}>Back To Items</button>
        </div>
      </div>}
    </div>
  )
}

export default ViewItem;