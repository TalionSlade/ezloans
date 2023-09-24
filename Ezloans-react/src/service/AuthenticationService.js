import axios from 'axios'; 

class AuthenticationService {
    
    static async login(employee){
        try{
            const response=await axios.post('http://localhost:8085/ezloans/api/login', employee);
                console.log('REST API Response: ', response.data);
                const isLoggedIn = response.data.isloggedin;
                const eid = response.data.eid;
            
            if(isLoggedIn === true){
                return response.data;
            }
            else {return false;}

        }
        catch(error){
            console.error('Login Error: ', error);
        }
    }
    static async registerEmployee(employee){
        try{
            const response=await axios.post('http://localhost:8085/ezloans/api/empreg', employee);
            return response.data;
        }
        catch(error){
            console.error('Registeration Error: ', error);
        }
    }

    
}


export default AuthenticationService;
