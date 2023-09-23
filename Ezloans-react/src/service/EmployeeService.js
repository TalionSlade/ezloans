import axios from 'axios'; 

const EMPLOYEE_REST_API_URL = 'http://localhost:8085/ezloans/api/employee';

class EmployeeService {
    static  getEmployees() {
		try {
			return axios.get(EMPLOYEE_REST_API_URL);
		} catch (error) {
			console.error("Get Error: ", error);

		}

	}

    static getEmployeeById(employeeId) {
		return axios.get(EMPLOYEE_REST_API_URL + '/' + employeeId);
	}

    static deleteEmployee(employeeId) {
		return axios.delete(EMPLOYEE_REST_API_URL + '/' + employeeId);
	}

    static updateEmployee(employee, employeeId) {
		return axios.put(EMPLOYEE_REST_API_URL + '/' + employeeId, employee);
	}

}

export default EmployeeService