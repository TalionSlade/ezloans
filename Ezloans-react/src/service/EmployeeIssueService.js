import axios from 'axios'; 

const EMPLOYEE_ISSUE_REST_API_URL = 'http://localhost:8085/ezloans/api/employeeissue';

class EmployeeIssueService {
   

    static getEmployeeIssueById(employeeId) {
		try {
			return axios.get(EMPLOYEE_ISSUE_REST_API_URL + '/' + employeeId);
		} catch (error) {
			console.error("Error: ", error);
		}
	}

}

export default EmployeeIssueService