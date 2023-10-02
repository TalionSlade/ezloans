import axios from "axios";

const LOAN_REST_API_URL = 'http://localhost:8085/ezloans/api/addloancard';
const CRUDLOAN_REST_API_URL = 'http://localhost:8085/ezloans/api/loancards';

// Class component to interact with Loan API
class LoanService {

    // getLoans(): Function to send GET request to get details of all loans
	static  getLoans() {
		try {
			return axios.get(CRUDLOAN_REST_API_URL);
		} catch (error) {
			console.error("Get Error: ", error);
		}
	}

    // createLoan(loanCard): Function to send POST request to create a new loan card
    // It sends a loanCard object which contains the following details: 
    // loanCard = { 
    //     type: <String>, 
    //     duration: <Number> 
    // }
	static createLoan(loanCard) {
        try {
            return axios.post(LOAN_REST_API_URL, loanCard);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

    // getLoanCardById(loanId): Function to send GET request to get details of specific loan card based on loanID
	static getLoanCardById(loanId) {
        try {
            return axios.get(CRUDLOAN_REST_API_URL + '/' + loanId);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

    // updateLoanCard(loanCard, loanId): Function to send PUT request to update details of a specific loan card based on LoanID
    // It sends a loanCard object with the updated fields for loan card
	static updateLoanCard(loanCard, loanId) {
        try {
            return axios.put(CRUDLOAN_REST_API_URL + '/' + loanId, loanCard);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

    // deleteLoan(loanId): Function to send DELETE request to delete specific loan card based on loanID
	static deleteLoan(loanId) {
        try {
			return axios.delete(CRUDLOAN_REST_API_URL + '/' + loanId);
		} 
		catch (error) {
            console.error("Error: ", error);
        }
	}

    // getLoanCardTypes(): Function to send GET request to fetch a list of all types of loan cards in database
    static getLoanCardTypes() {
        try {
            return axios.get(CRUDLOAN_REST_API_URL + '/types')
        } catch(error) {
            console.error("Error: ", error);
        }
    }

}

export default LoanService;