import axios from 'axios'; 

const EMPLOYEE_ISSUE_REST_API_URL = 'http://localhost:8085/ezloans/api/employeeissue';

// Class component to interact with EmployeeIssue API
class EmployeeIssueService {

	// getEmployeeIssueById(employeeId): Function to send GET request to get employee issue details based on specific eid
    static getEmployeeIssueById(employeeId) {
		try {
			return axios.get(EMPLOYEE_ISSUE_REST_API_URL + '/' + employeeId);
		} catch (error) {
			console.error("Error: ", error);
		}
	}
}

export default EmployeeIssueService