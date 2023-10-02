import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import LoanService from '../../service/LoanService';
import { useAuth } from '../AuthContext';
import { IoTrash as DeleteIcon, IoPencil as EditIcon, IoEye as ViewIcon} from 'react-icons/io5';
// Loan() displays all existing loans and renders links for View/Delete/Update loans
function Loan() {

	const history = useNavigate();
  const { isLoggedIn } = useAuth();
	const [loan, setLoan] = useState([]);
	const [message, setMessage] = useState('');
  
  // Used for conditional rendering of page based on login status. If not logged in, redirects to login
	useEffect(() => {
    // Fetch existing loan card details
    if(isLoggedIn) fetchLoan();
    else {
      alert("Please login first");
      history('/login');
    }
	}, []);

  // Function to get all loan cards existing in DB
	const fetchLoan = () => {
		LoanService.getLoans().then((response) => {
			setLoan(response.data); 
		});
	}

  // Navigate to add loan
	const addLoan = () => {
		history('/addLoan/_add'); 
	}

  // Navigate to update loan
	const editLoan = (id) => {
		history(`/addLoan/${id}`) 
	}

  // Function to delete loan card based on loan card ID
	const deleteLoan = (id) => {
		LoanService.deleteLoan(id).then(() => {
      // Update loan card list after deletion
			fetchLoan(); 
			setMessage("Loan deleted successfully");
			setTimeout(() => {
				setMessage('');
			}, 2000);
		})
	}

  // Navigate to view loan
	const viewLoan = (id) => {
		history(`/viewLoan/${id}`);
	}
  return (
    <div>
      {/* Conditional rendering of page based on login status */}
      <br />{isLoggedIn &&
      <div className='container'>
        <h2>Loan Card Master Data Details</h2>
        <br />
        <div className="row justify-content-center">
          {/* Add loan button */}
          <div className='column'>
            <button className="btn w-auto" onClick={addLoan}>Add Loan</button>
          </div>
        </div>
        <div className="column" >
          <div className='registration-container' style={{maxWidth: "80%"}}>
            <h2>View All Loans</h2>
            <div className='row justify-content-center'>
              {/* Table to display all existing loan cards */}
              <table className="table w-auto">
                <thead>
                  <tr>
                    <th> Loan ID</th>
                    <th> Loan Type</th>
                    <th> Duration</th>
                    <th> Actions</th>
                  </tr>
                </thead>
                <tbody style={{color: "black"}}>
                  {/* Using map function to display all loan cards */}
                  {loan.map(
                    loan =>
                      <tr key={loan.loanId}>
                        <td> {loan.loanId} </td>
                        <td> {loan.type} </td>
                        <td> {loan.duration} </td>
                        {/* Buttons for add/view/delete */}
                        <td>
                          <span className='tableIcon' onClick={() => editLoan(loan.loanId)}><EditIcon/></span>
                          &nbsp;
                          <span className='tableIcon' onClick={() => deleteLoan(loan.loanId)}><DeleteIcon/></span>
                          &nbsp;
                          <span className='tableIcon' onClick={() => viewLoan(loan.loanId)}><ViewIcon/></span>
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

export default Loan;