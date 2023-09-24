import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Registeration.css';
import { useAuth } from './AuthContext';
import EmployeeService from '../service/EmployeeService';

const ApplyLoan = () => {

    const { isLoggedIn, userId } = useAuth();

  const history = useNavigate();
   const [employeeCard, setEmployeeCard] = useState({
    eid: userId,
    category: '',
    description: '',
    value: 0,
    make:''
  });
  const [successMessage,setSuccessMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    
      setEmployeeCard((prevEmployeeCard) => ({
        ...prevEmployeeCard,
        [name]: value
      }));
    
  };

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
                <select className="form-control" placeholder= "Item Category" desc="category" value={employeeCard.category} name="category" onChange={handleChange}>
                        <option value="Furniture">Furniture</option>
                        <option value="Stationary">Stationary</option>
                        <option value="Crockery">Crockery</option>
                        <option value="Other">Other</option>
                </select>
            </div>
            <div className="form-group">
                <label style={{ color: '#1f6e8c'}}>Make:</label>
                <select className="form-control" placeholder= "Item Make" desc="make" value={employeeCard.make} onChange={handleChange}>
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
                <input
                type="text"
                name="description"
                value={employeeCard.description}
                onChange={handleChange}
                />
            </div>
            <div className="form-group">
                <label style={{ color: '#1f6e8c'}}>Value:   </label>
                <input
                    type="text"
                    name="value"
                    value={employeeCard.value}
                    onChange={handleChange}
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