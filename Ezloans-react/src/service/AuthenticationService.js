import axios from 'axios'; 

// Class component to track user login and employee registeration. It interacts with the backend APIs using Axios
// All component use try catch blocks for exception handling
class AuthenticationService{

    // login(employee): Async function to login an employee. 
    // It sends an employee object containing user email and password to API
    // and returns response containing login status and eid of logged in employee
    static async login(employee) {
        try {
            const response=await axios.post('http://localhost:8085/ezloans/api/login', employee);
            console.log('REST API Response: ', response.data);
            const isLoggedIn = response.data.isloggedin;
            if(isLoggedIn === true){
                return response.data;
            }
            else { return false; }
        }
        catch(error) {
            console.error('Login Error: ', error);
        }
    }

    // registerEmployee(employee): Async function to register a new employee
    // It sends an employee object containing details of new employee to API 
    // and returns a response containing status of new registerred employee
    // employee = {
    //     email: <String>,
    //     fname: <String>,
    //     lname: <String>,
    //     designation: <String>,
    //     department:<String>,
    //     password: <String>,
    //     dob: <Date>,
    //     doj: <Date>,
    //     gender: <String>,
    //  }
    static async registerEmployee(employee) {
        try {
            const response=await axios.post('http://localhost:8085/ezloans/api/empreg', employee);
            return response.data;
        }
        catch(error){
            console.error('Registeration Error: ', error);
        }
    }

}

export default AuthenticationService;
