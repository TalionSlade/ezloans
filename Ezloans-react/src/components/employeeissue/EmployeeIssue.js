import React, {useState, useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import EmployeeIssueService from '../../service/EmployeeIssueService';
import { useAuth } from '../AuthContext.js';
import EmployeeService from '../../service/EmployeeService';

// Function to display existing employee issues based on employee eid
const EmployeeIssue = () => {
  const history = useNavigate();
  const { isLoggedIn, userId } = useAuth();
  // Setting id with eid from url using useParams hook
  const {id} = useParams();
  const [items, setItems] = useState([]);
  const [department, setDepartment] = useState();
  const [designation, setDesignation] = useState();
  // Used for conditional rendering of page based on login status. If not logged in, redirects to login
  useEffect(() => {
    if(isLoggedIn) {
      // If employee is logged in, get employee card details along with department and designation
      EmployeeIssueService.getEmployeeIssueById(id).then((response) => {
          setItems(response.data);
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
        <h2 className="text-center">Items Purchased</h2><hr/>
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
                  <th> Issue ID</th>
                  <th> Description</th>
                  <th> Category</th>
                  <th> Make</th>
                  <th> Valuation</th>
                  <th> Return Date</th>
                </tr>
              </thead>
              {/* Displaying existing employee issues for logged in user */}
              <tbody style={{color: "black"}}>
                {items.map(
                  ei =>
                    <tr key={ei.issueId}>
                      <td> {ei.issueId} </td>
                      <td> {ei.item.desc} </td>
                      <td> {ei.item.category} </td>
                      <td> {ei.item.make} </td>
                      <td> {ei.item.valuation} </td>
                      <td> {ei.returnDate} </td>
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

export default EmployeeIssue