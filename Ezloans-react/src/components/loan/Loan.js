import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import LoanService from '../../service/LoanService';
import { IoTrash as DeleteIcon, IoPencil as EditIcon, IoEye as ViewIcon} from 'react-icons/io5';
// import '../styles/Loan.css';

// const Loan = () => {
//   const history = useNavigate();
//    //defining state
//    const [loanCard, setloanCard] = useState({
//     type: '',
//     duration: 1,
//   });
//   const [errors,setErrors] = useState('');
//   const [successMessage,setSuccessMessage] = useState('');

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//       setloanCard((prevloanCard) => ({
//         ...prevloanCard,
//         [name]: value
//   })); 
//   };

// const handleSubmit = async (e) => {
//   // e.preventDefault();
//   // const validationErrors = validateForm();
//   // if (Object.keys(validationErrors).length === 0) {
//   //   try {
//       await AuthenticationService.addLoan(loanCard);
//       setSuccessMessage('Added Successfully!');
//       alert("Added Successfully!");
//       // setTimeout(() => {
//       //   history('/login'); // navigates to Login Component
//       // }, 3000);
    
//     // } 
    
//     // catch (error) {
//     //   console.error('Error', error);
//     //   setSuccessMessage('An error when adding a loan.');
//     // }
//   // } 
//   // else {
//   //   setErrors(validationErrors);
//   // }
// };
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
		history(`/editLoan/${id}`) 
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
    // <div className="loan-container">
    //  <form>
    //     <h2 style={{ color: '#1f6e8c', gridColumn: '1 / span 2' }}>Loan Card Master Data Details</h2>
    //     <br>
    //     </br>

    //     <div className="form-group">
         
    //     <label style={{ color: '#1f6e8c'}}>Loan Type: </label>
    //     &nbsp; &nbsp;
    //     <select value={loanCard.type} name="type" onChange={handleChange}
    //         className={errors.type && 'error'}>
    //         <option value="Default">Default</option>
    //         <option value="Furniture">Furniture</option>
    //         <option value="Crockery">Crockery</option>
    //         <option value="Stationary">Stationary</option>
    //       </select>
    //     {loanCard.type && <p className="error-message">{errors.type}</p>}
    //   </div>

    //   <div className="form-group">
    //       <label style={{ color: '#1f6e8c'}}>Duration:  </label>
    //       &nbsp; &nbsp;
    //       <input
    //         type="number"
    //         name="duration"
    //         value={loanCard.duration}
    //         onChange={handleChange}
    //         className={loanCard.duration && 'error'}
    //       />
    //       {loanCard.duration && <p className="error-message">{errors.duration}</p>}
    //     </div>
    //     <br></br>
    //     <div className="form-group">
    //     <button type="submit" className="submit-button" onClick={handleSubmit}>
    //       Add Loan
    //     </button>
    //     {successMessage && <p className="success-message">{successMessage}</p>}
    //   </div>
      
    //     </form>
    //     </div>
        
  )
}

export default Loan;