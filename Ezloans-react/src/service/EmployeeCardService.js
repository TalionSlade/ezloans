import axios from 'axios'; 

const EMPLOYEE_CARD_REST_API_URL = 'http://localhost:8085/ezloans/api/employeecard';

class EmployeeCardService {
   

    static getEmployeeCardById(employeeId) {
		try {
			return axios.get(EMPLOYEE_CARD_REST_API_URL + '/' + employeeId);
		} catch (error) {
			console.error("Error: ", error);
		}
	}

}

export default EmployeeCardService