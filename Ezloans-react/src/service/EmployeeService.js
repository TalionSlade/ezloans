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
		try {
			return axios.get(EMPLOYEE_REST_API_URL + '/' + employeeId);
		} catch (error) {
			console.error("Error: ", error);
		}
	}

    static deleteEmployee(employeeId) {
		try {
			return axios.delete(EMPLOYEE_REST_API_URL + '/' + employeeId);
		} catch (error) {
			console.error("Error: ", error);
		}
	}

    static updateEmployee(employee, employeeId) {
		try {
			return axios.put(EMPLOYEE_REST_API_URL + '/' + employeeId, employee);
		} catch (error) {
			console.error("Error: ", error);
		}
	}

	static async applyLoan(loanObj){
        try{
            const response=await axios.post('http://localhost:8085/ezloans/api/applyloan', loanObj);
            return response.data;
        }
        catch(error){
            console.error('Apply Loan Error: ', error);
        }
    }

}

export default EmployeeService