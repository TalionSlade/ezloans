import axios from 'axios'; 

const EMPLOYEE_CARD_REST_API_URL = 'http://localhost:8085/ezloans/api/employeecard';

// Class component to interact with EmployeeCard API
class EmployeeCardService {

	// getEmployeeCardById(employeeId): Function to send GET request for employee card details for a specific eid
    static getEmployeeCardById(employeeId) {
		try {
			return axios.get(EMPLOYEE_CARD_REST_API_URL + '/' + employeeId);
		} catch (error) {
			console.error("Error: ", error);
		}
	}
}

export default EmployeeCardService