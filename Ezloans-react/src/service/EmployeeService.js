import axios from 'axios'; 

const EMPLOYEE_REST_API_URL = 'http://localhost:8085/ezloans/api/employee';

// Class component to interact with Employee API
class EmployeeService {

	// getEmployees(): Function to send GET request for all employee details
    static getEmployees() {
		try {
			return axios.get(EMPLOYEE_REST_API_URL);
		} catch (error) {
			console.error("Get Error: ", error);
		}
	}

	// getEmployeeById(employeeId): Function to send GET request for specific employee based on eid
    static getEmployeeById(employeeId) {
		try {
			return axios.get(EMPLOYEE_REST_API_URL + '/' + employeeId);
		} catch (error) {
			console.error("Error: ", error);
		}
	}

	// deleteEmployee(employeeId): Function to send DELETE request to delete specific employee based on eid
    static deleteEmployee(employeeId) {
		try {
			return axios.delete(EMPLOYEE_REST_API_URL + '/' + employeeId);
		} catch (error) {
			console.error("Error: ", error);
		}
	}

	// updateEmployee(employee, employeeId): Function to send PUT request to update specific employee details based on eid
	// It sends an employee object containing all details to be updated
    static updateEmployee(employee, employeeId) {
		try {
			return axios.put(EMPLOYEE_REST_API_URL + '/' + employeeId, employee);
		} catch (error) {
			console.error("Error: ", error);
		}
	}

	// applyLoan(loanObj): Function to send POST request to apply for a loan
	// It sends a loan Object which contains the following fields
	// loanObj = {
	// 	eid: <Number>,
	// 	category: <String>,
	// 	description: <String>,
	// 	value: <Number>,
	// 	make: <String>
	// }
	static async applyLoan(loanObj) {
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