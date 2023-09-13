import React, {useState} from 'react'
import '../styles/Login.css'
import AuthenticationService from '../service/AuthenticationService';
import {useNavigate} from "react-router-dom";

const Login = () => {
    const history = useNavigate ();
    const [email,setEmail] =  useState('');
    const [password,setPassword] =  useState('');
    const [errorMessage,seterrorMessage] =  useState('');
    const [successMessage,setsuccessMessage] =  useState('');

    const handleLogin = async() => {
        if (!email || !password){
            seterrorMessage("Please enter both email and password");
            return;
        }
        const employee={email,password};
        try{
            const loginSuccess = await AuthenticationService.login(employee);
            console.log("API response: ", loginSuccess.data);
            if (loginSuccess){
                setsuccessMessage("Login Successful. Redirecting...");
                setTimeout(() => {
                  history('/product');  
                }, 200);
            }
            else{
                seterrorMessage("Invalid email or password");
            }
            
        }
        catch(error){
            console.log("Login Error: ", error);
            seterrorMessage("Error occurred during login");
        }
    }
  return (
    <div> <br></br>
        <div className='container'>
            <h2 style={{color:'green'}}>
                Employee Login
            </h2>
            <div className='form-group'>
                <label>Email: </label>
                <input type='email' className='form-control' value={email} onChange={(e)=>setEmail(e.target.value)}></input>
            </div>
            <div className='form-group'>
                <label>Password: </label>
                <input type='password' className='form-control'  value={password} onChange={(e)=>setPassword(e.target.value)}></input>
            </div>
            <button className='btn btn-primary' onClick={handleLogin}>Login</button>
            {errorMessage && <p className="error-message">{errorMessage}</p>}
            {successMessage && <p className="success-message">{successMessage}</p>}
        </div>
    </div>
  );
}

export default Login;