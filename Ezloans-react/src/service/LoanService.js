import axios from "axios";

const LOAN_REST_API_URL = 'http://localhost:8085/ezloans/api/addloancard';
const CRUDLOAN_REST_API_URL = 'http://localhost:8085/ezloans/api/loancards';

class LoanService {

	static  getLoans() {
		try {
			return axios.get(CRUDLOAN_REST_API_URL);
		} catch (error) {
			console.error("Get Error: ", error);
		}
	}

	static createLoan(loanCard) {
        try {
            return axios.post(LOAN_REST_API_URL, loanCard);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

	static getLoanCardById(loanId) {
        try {
            return axios.get(CRUDLOAN_REST_API_URL + '/' + loanId);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

	static updateLoanCard(loanCard, loanId) {
        try {
            return axios.put(CRUDLOAN_REST_API_URL + '/' + loanId, loanCard);
        } catch (error) {
            console.error("Error: ", error);
        }
	}


	static deleteLoan(loanId) {
        try {
			return axios.delete(CRUDLOAN_REST_API_URL + '/' + loanId);
		} 
		catch (error) {
            console.error("Error: ", error);
        }
		
	}

}

export default LoanService;