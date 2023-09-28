import React, {useState, useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import EmployeeIssueService from '../../service/EmployeeIssueService';
import ItemService from '../../service/ItemService';
import { useAuth } from '../AuthContext.js';
import EmployeeService from '../../service/EmployeeService';

const EmployeeIssue = () => {
    const history = useNavigate();
    const { isLoggedIn, userId } = useAuth();

    const {id} = useParams();

    const [items, setItems] = useState([]);
    const [department, setDepartment] = useState();
    const [designation, setDesignation] = useState();

    useEffect(() => {
      if(isLoggedIn) {
        EmployeeIssueService.getEmployeeIssueById(id).then((response) => {
            setItems(response.data);
        EmployeeService.getEmployeeById(id).then((response) => {
            setDepartment(response.data.department);
            setDesignation(response.data.designation);
        })
        });}
        else {
          alert("Please login first");
                history('/login');
        }
        
    }, [id]);

  return (
    <div>
        <br /> {isLoggedIn &&
        <div className="registration-container">
            <h2 className="text-center">Items Purchased</h2><hr/>
            <div className='row justify-content-center' style={{color: "black"}}>
                <div className='col-md-4'>
                    Employee Id: {userId}
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
                    <th> Issue Id</th>
                    <th> Description</th>
                    <th> Category</th>
                    <th> Make</th>
                    <th> Valuation</th>
                  </tr>
                </thead>
                <tbody style={{color: "black"}}>
                  {items.map(
                    ei =>
                      <tr key={ei.issueId}>
                        <td> {ei.issueId} </td>
                        <td> {ei.item.desc} </td>
                        <td> {ei.item.category} </td>
                        <td> {ei.item.make} </td>
                        <td> {ei.item.valuation} </td>
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