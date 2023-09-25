import React, {useState, useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import ItemService from '../../service/ItemService';
import Item from './Item';

const ViewItem = () => {
    const history = useNavigate();

    const {id} = useParams();

    const [item, setItem] = useState({});

    useEffect(() => {
        ItemService.getItemById(id).then((response) => {
            setItem(response.data);
        })
        
    }, [id]);

    const backItem = () => {
        history('/item')
    }

    return (
        <div>
        <br />
        <div className="card col-md-6 offset-md-3">
            <h3 className="text-center">View Item Details</h3><hr/>
            <div className="card-body">
                <div className="row">
                    <label>Item Id:</label>
                    <div class="text-success fw-bolder">{item.itemId}</div><hr/>
                </div>
                <div className="row">
                    <label>Item Description:</label>
                    <div class="text-success fw-bolder">{item.desc}</div><hr/>
                </div>
                <div className="row">
                    <label>Item Status:</label>
                    <div class="text-success fw-bolder">{item.status}</div><hr/>
                </div>
                <div className="row">
                    <label>Item Make:</label>
                    <div class="text-success fw-bolder">{item.make}</div><hr/>
                </div>
                <div className="row">
                    <label>Item Category:</label>
                    <div class="text-success fw-bolder">{item.category}</div><hr/>
                </div>
                <div className="row">
                    <label>Item Valuation:</label>
                    <div class="text-success fw-bolder">{item.valuation}</div><hr/>
                </div>
            </div>
            <div className = "row justify-content-center">
                    <button className="btn btn-info w-auto" onClick={backItem}>Back To Items</button>
                </div>
        </div>
    </div>
    )
}

export default ViewItem;