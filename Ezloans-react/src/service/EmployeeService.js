import axios from 'axios'; 

// EMPLOYEE_REST_API_URL = 'http://localhost:8085/ezloans/api/employees';

class EmployeeService {
    static  getEmployees() {
		try {
			return axios.get('http://localhost:8085/ezloans/api/employees');
		} catch (error) {
			console.error("Get Error: ", error);

		}

	}
}

export default EmployeeService