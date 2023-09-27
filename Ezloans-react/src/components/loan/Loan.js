import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import LoanService from '../../service/LoanService';
import { IoTrash as DeleteIcon, IoPencil as EditIcon, IoEye as ViewIcon} from 'react-icons/io5';

function Loan() {

	const history = useNavigate();

	const [loan, setLoan] = useState([]);
	const [message, setMessage] = useState([]);
	useEffect(() => {
		fetchLoan();
	}, []);

	const fetchLoan = () => {
		LoanService.getLoans().then((response) => {
			setLoan(response.data); 
		});
	}

	const addLoan = () => {
		history('/addLoan/_add'); 
	}

	const editLoan = (id) => {
		history(`/addLoan/${id}`) 
	}

	const deleteLoan = (id) => {
		LoanService.deleteLoan(id).then(() => {
			fetchLoan(); 
			setMessage("Loan deleted successfully");
			setTimeout(() => {
				setMessage('');
			}, 2000);
		})
	}

	const viewLoan = (id) => {
		history(`/viewLoan/${id}`);
	}
  return (

    <div>
    <br />
    <div className='container'>
      <h2>Loan Card Master Data Details</h2>
      <br />
      <div className="row justify-content-center">
        <div className='column'>
          <button className="btn w-auto" onClick={addLoan}>Add Loan</button>
        </div>
        </div>
        <div className="column" >
          <div className='registration-container' style={{maxWidth: "80%"}}>
            <h2>View All Loans</h2>
            <div className='row justify-content-center'>
            <table className="table w-auto">
              <thead>
                <tr>
                  <th> Loan Id</th>
                  <th> Loan Type</th>
                  <th> Duration</th>
                  <th> Actions</th>
                </tr>
              </thead>
              <tbody style={{color: "black"}}>
                {loan.map(
                  loan =>
                    <tr key={loan.loanId}>
                      <td> {loan.loanId} </td>
                      <td> {loan.type} </td>
                      <td> {loan.duration} </td>
                      <td>
                        <button className='btn btn-success tblBtn' onClick={() => editLoan(loan.loanId)}>
                          <span><EditIcon/></span>
                        </button>
                        &nbsp;
                        <button className='btn btn-danger tblBtn' onClick={() => deleteLoan(loan.loanId)}>
                          <span><DeleteIcon/></span>
                        </button>
                        &nbsp;
                        <button className='btn btn-primary tblBtn' onClick={() => viewLoan(loan.loanId)}>
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

export default Loan;