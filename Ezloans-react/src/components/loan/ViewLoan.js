import React, {useState, useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import LoanService from '../../service/LoanService';
import { useAuth } from '../AuthContext';

const ViewLoan = () => {
    const history = useNavigate();

    const {id} = useParams();

    const [loan, setLoan] = useState({});
    const { isLoggedIn, userId } = useAuth();

    useEffect(() => {
        if(isLoggedIn) {
        LoanService.getLoanCardById(id).then((response) => {
            setLoan(response.data);
            console.log(response.data);
        })}
        else {
            alert("Please login first");
            history('/login');
          }

        
    }, [id]);

    const backLoan = () => {
        history('/loan')
    }

    return (
        <div>
        <br />
        {isLoggedIn && 
        <div className="card col-md-6 offset-md-3">
            <h3 className="text-center">View Loan Details</h3><hr/>
            <div className="card-body">
                <div className="row">
                    <label>Loan Id:</label>
                    <div class="text-success fw-bolder">{loan.loanId}</div><hr/>
                </div>
                <div className="row">
                    <label>Loan Type:</label>
                    <div class="text-success fw-bolder">{loan.type}</div><hr/>
                </div>
                <div className="row">
                    <label>Loan Duration:</label>
                    <div class="text-success fw-bolder">{loan.duration}</div><hr/>
                </div>
                </div>
          <div className = "row justify-content-center">
                    <button className="btn btn-info w-auto" onClick={backLoan}>Back To Loans</button>
                </div>
        </div>}
    </div>
    )
}

export default ViewLoan;