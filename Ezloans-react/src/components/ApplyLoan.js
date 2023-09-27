import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Registeration.css';
import { useAuth } from './AuthContext';
import EmployeeService from '../service/EmployeeService';
import ItemService from '../service/ItemService';

const ApplyLoan = () => {

    const { isLoggedIn, userId } = useAuth();

  const history = useNavigate();
 
  const [successMessage,setSuccessMessage] = useState('');
  const [descriptions, setDescriptions] = useState([]);
  const [valuation, setValuation] = useState(0);
  const [employeeCard, setEmployeeCard] = useState({
    eid: userId,
    category: '',
    description: '',
    value: valuation,
    make:''
  });
  const handleChange = (e) => {
    const { name, value } = e.target;
    
      setEmployeeCard((prevEmployeeCard) => ({
        ...prevEmployeeCard,
        [name]: value
      }));
    
  };

  const handleCategoryChange = async (e) => {
    e.preventDefault();
    employeeCard.category = e.target.value;
    const response = await ItemService.getDescriptions(e.target.value);
    setDescriptions(response.data);
    console.log("getdescriptions res: ",response.data);
  }

  const handleDescriptionChange = async (e) => {
    e.preventDefault();
    employeeCard.description = e.target.value;
    const idx = descriptions.findIndex((desc) => desc.description == e.target.value);
    const value = descriptions[idx].valuation;
    setValuation(value);
    console.log("idx: ",descriptions[idx]);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Employee Card obj: ", employeeCard);
      try {
        await EmployeeService.applyLoan(employeeCard);
        setSuccessMessage('Loan added successful!y');
        setTimeout(() => {
          history(`/viewItem/emp/${userId}`); 
        }, 3000);
      } 
      catch (error) {
        console.error('Apply Loan error', error);
        setSuccessMessage('An error occurred during apply loan.');
      }
  };

  return (
    <div>
    <br/><br/>
    <div className="registration-container">
      <form onSubmit={handleSubmit} className="form-grid">
        <div style={{ gridColumn: '1 / span 2' }}>
            <h2 style = {{color: '#1f6e8c'}}> Apply Loan </h2>
            <hr/>
            <h6 style = {{color: 'black'}}> Employee ID: {userId} </h6>
            <hr/>
        </div>
        <div className='column'>
            <div className="form-group">
                <label style={{ color: '#1f6e8c'}}>Category:</label>
                <select className="form-control" placeholder= "Item Category" desc="category" value={employeeCard.category} name="category" onChange={handleCategoryChange}>
                        <option hidden="hidden">Default</option>
                        <option value="Furniture">Furniture</option>
                        <option value="Stationary">Stationary</option>
                        <option value="Crockery">Crockery</option>
                        <option value="Other">Other</option>
                </select>
            </div>
            <div className="form-group">
                <label style={{ color: '#1f6e8c'}}>Make:</label>
                <select className="form-control" placeholder= "Item Make" desc="make" value={employeeCard.make} onChange={handleChange}>
                <option hidden="hidden">Default</option>
                    <option value="Wooden">Wooden</option>
                    <option value="Glass">Glass</option>
                    <option value="Plastic">Plastic</option>
                    <option value="Other">Other</option>
                </select>
            </div>
        </div>
        <div className='column'>
            <div className="form-group">
                <label style={{ color: '#1f6e8c'}}>Description:</label>
                <select className="form-control" placeholder= "Item Description" desc="description" value={employeeCard.description} onChange={handleDescriptionChange}>
                <option hidden="hidden">Default</option>
                   { descriptions.map(
                    desc => 
                    <option key = {desc.id} value={desc.description}>{desc.description}</option>
                   )
                   }
                </select>

            </div> 
            <div className="form-group">
                <label style={{ color: '#1f6e8c'}}>Value:   </label>
                <input
                    type="number"
                    name="value"
                    value={valuation}
                    />
            </div>
        </div>
        <div className="form-group" style={{ gridColumn: '1 / span 2' }}>
          <button type="submit" className="submit-button" onClick={handleSubmit}>
            Apply
          </button>
          {successMessage && <p className="success-message">{successMessage}</p>}
        </div>
      </form>
    </div>
    <br/>
    </div> 
  )
}

export default ApplyLoan;