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

    /*global $, document, window, setTimeout, navigator, console, location*/



  return (
    <div> <br></br>


        {/* <div className='container'>
            <h2 style={{color:'green'}}>
                Employee Login
            </h2>
            <br></br>
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
        </div> */}

<a href="https://front.codes/" class="logo" target="_blank">
		<img src="https://assets.codepen.io/1462889/fcy.png" alt=""/>
	</a>

	<div class="section">
		<div class="container">
			<div class="row full-height justify-content-center">
				<div class="col-12 text-center align-self-center py-5">
					<div class="section pb-5 pt-5 pt-sm-2 text-center">
						<h6 class="mb-0 pb-3"><span>Log In </span><span>Sign Up</span></h6>
			          	<input class="checkbox" type="checkbox" id="reg-log" name="reg-log"/>
			          	<label for="reg-log"></label>
						<div class="card-3d-wrap mx-auto">
							<div class="card-3d-wrapper">
								<div class="card-front">
									<div class="center-wrap">
										<div class="section text-center">
											<h4 class="mb-4 pb-3">Log In</h4>
											<div class="form-group">
												<input type="email" name="logemail" class="form-style" placeholder="Your Email" id="logemail" autocomplete="off"/>
												<i class="input-icon uil uil-at"></i>
											</div>	
											<div class="form-group mt-2">
												<input type="password" name="logpass" class="form-style" placeholder="Your Password" id="logpass" autocomplete="off"/>
												<i class="input-icon uil uil-lock-alt"></i>
											</div>
											<a href="#" class="btn mt-4">submit</a>
                            				<p class="mb-0 mt-4 text-center"><a href="#0" class="link">Forgot your password?</a></p>
				      					</div>
			      					</div>
			      				</div>
								<div class="card-back">
									<div class="center-wrap">
										<div class="section text-center">
											<h4 class="mb-4 pb-3">Sign Up</h4>
											<div class="form-group">
												<input type="text" name="logname" class="form-style" placeholder="Your Full Name" id="logname" autocomplete="off"/>
												<i class="input-icon uil uil-user"></i>
											</div>	
											<div class="form-group mt-2">
												<input type="email" name="logemail" class="form-style" placeholder="Your Email" id="logemail" autocomplete="off"/>
												<i class="input-icon uil uil-at"></i>
											</div>	
											<div class="form-group mt-2">
												<input type="password" name="logpass" class="form-style" placeholder="Your Password" id="logpass" autocomplete="off"/>
												<i class="input-icon uil uil-lock-alt"></i>
											</div>
											<a href="#" class="btn mt-4">submit</a>
				      					</div>
			      					</div>
			      				</div>
			      			</div>
			      		</div>
			      	</div>
		      	</div>
	      	</div>
	    </div>
	</div>    



    </div>
  );
}

export default Login;


