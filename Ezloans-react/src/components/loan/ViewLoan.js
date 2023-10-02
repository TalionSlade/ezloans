import React, {useState, useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import LoanService from '../../service/LoanService';
import { useAuth } from '../AuthContext';

// Function to view item details based on loanCardID
const ViewLoan = () => {

  const history = useNavigate();
  const { isLoggedIn } = useAuth();
  // Get eid from url using useParams
  const {id} = useParams();
  const [loan, setLoan] = useState({});
  
  // Used for conditional rendering of page based on login status. If not logged in, redirects to login
  useEffect(() => {
    if(isLoggedIn) {
      // Get all loan cards based on eid
      LoanService.getLoanCardById(id).then((response) => {
        setLoan(response.data);
      })
    }
    else {
      alert("Please login first");
      history('/login');
    }
  }, [id]);

  // Navigate back to loan
  const backLoan = () => {
    history('/loan')
  }

  return (
    <div>
      <br />
      {/* Conditional display of page based on login status */}
      {isLoggedIn && 
      <div className="card col-md-6 offset-md-3">
        <br/>
        <h3 className="text-center" style={{color: "#1f6e8c"}}>View Loan Details</h3><hr/>
        <div className="card-body">
          {/* Loan ID */}
          <div className="row">
            <label>Loan ID:</label>
            <div class="text-success fw-bolder">{loan.loanId}</div><hr/>
          </div>
          {/* Loan Type */}
          <div className="row">
            <label>Loan Type:</label>
            <div class="text-success fw-bolder">{loan.type}</div><hr/>
          </div>
          {/* Loan Duration */}
          <div className="row">
            <label>Loan Duration:</label>
            <div class="text-success fw-bolder">{loan.duration}</div><hr/>
          </div>
        </div>
        <div className = "row justify-content-center">
          {/* Button to navigate back to loans */}
          <button className="btn btn-info w-auto" onClick={backLoan}>Back To Loans</button>
        </div>
      </div>}
    </div>
  )
}

export default ViewLoan;