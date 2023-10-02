import React, {useState, useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import EmployeeCardService from '../../service/EmployeeCardService';
import { useAuth } from '../AuthContext.js';
import EmployeeService from '../../service/EmployeeService';

// Function to display existing employee cards for specific user based on eid
const EmployeeCard = () => {
    const history = useNavigate();
    const { isLoggedIn, userId } = useAuth();
    // Getting eid from url using useParams hook
    const {id} = useParams();
    const [employeeCards, setEmployeeCards] = useState([]);
    const [department, setDepartment] = useState();
    const [designation, setDesignation] = useState();

    // Used for conditional rendering of page based on login status. If not logged in, redirects to login
    useEffect(() => {
      if(isLoggedIn) {
        // If employee is logged in, get employee card details along with department and designation
        EmployeeCardService.getEmployeeCardById(id).then((response) => {
          setEmployeeCards(response.data);
        EmployeeService.getEmployeeById(id).then((response) => {
          setDepartment(response.data.department);
          setDesignation(response.data.designation);
        })
        });
      }
      else {
        alert("Please login first");
        history('/login');
      }
    }, [id]);

  return (
    <div>
      {/* Conditional display of page contents based on login status */}
      <br /> {isLoggedIn && 
      <div className="registration-container">
        <h2 className="text-center">Loan Cards Availed</h2><hr/>
        <div className='row justify-content-center' style={{color: "black"}}>
          <div className='col-md-4'>
              Employee ID: {userId}
          </div>
          <div className='col-md-4'>
              Department: {department}
          </div>
          <div className='col-md-4'>
              Designation: {designation}
          </div>
        </div><hr/>
        <div className='row justify-content-center'>
          <div className='col-md-12'>
            <table className="table w-auto">
              <thead>
                <tr>
                  <th> Loan Card ID</th>
                  <th> Loan Type</th>
                  <th> Duration</th>
                  <th> Card Issue Date</th>
                </tr>
              </thead>
              {/* Populating table based on existing employee cards */}
              <tbody style={{color: "black"}}>
                {employeeCards.map(
                  ec =>
                    <tr key={ec.cardId}>
                      <td> {ec.cardId} </td>
                      <td> {ec.loanId.type} </td>
                      <td> {ec.loanId.duration} </td>
                      <td> {ec.issueDate} </td>
                    </tr>
                )
                }
              </tbody>
            </table>
          </div>
        </div>
      </div>}
    </div>
  )
}

export default EmployeeCard