import React, {useState} from 'react'
import '../styles/Login.css'
import AuthenticationService from '../service/AuthenticationService';
import {useNavigate} from "react-router-dom";
import { useAuth } from './AuthContext';


const Login = () => {
    const history = useNavigate ();
    const [email,setEmail] =  useState('');
    const [password,setPassword] =  useState('');
    const [errorMessage,seterrorMessage] =  useState('');
    const [successMessage,setsuccessMessage] =  useState('');
	const {setIsLoggedIn, setIsUser, setUserId, setUserName} = useAuth();

	
    const handleLogin = async() => {
        if (!email || !password){
            seterrorMessage("Please enter both email and password");
            return;
        }
        const employee={email,password};
		
        try{
            const loginSuccess = await AuthenticationService.login(employee);
            console.log("API response: ", loginSuccess.data);
			setUserName(email);
            if (loginSuccess.isloggedin){
				
				seterrorMessage("");
                setsuccessMessage("Login Successful. Redirecting...");
				setIsLoggedIn(true);
				setUserId(loginSuccess.eid);
				if(email==='admin@ezloans.com') {
					setIsUser(false);
					setTimeout(() => {
						history('/dashboard');  
					}, 200)
				} else {
					setIsUser(true);
					setTimeout(() => {
						history('/dashboard');  
					}, 200);

				}
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
	<div class="section">
		<div class="container">
			<div class="row full-height justify-content-center">
				<div class="col-12 text-center align-self-center py-5">
					<div class="section pb-5 pt-5 pt-sm-2 text-center">
						<h6 class="mb-0 pb-3"><span>Admin </span><span>User</span></h6>

			          	<input class="checkbox" type="checkbox" id="reg-log" name="reg-log"/>
			          	<label for="reg-log"></label>
						
						<div class="card-3d-wrap mx-auto">
							<div class="card-3d-wrapper">
								<div class="card-front">
									<div class="center-wrap">
										<div class="section text-center">
											<h4 style ={{color: "#1f6e8c"}} class="mb-4 pb-3">Log In as Admin</h4>
											<div class="form-group">
											<input type="email" name="logemail" value={email} onChange={(e)=>setEmail(e.target.value)} class="form-style" placeholder="Your Email" id="logemail" autocomplete="off"/>
												<i class="input-icon uil uil-at"></i>
											</div>	
											<div class="form-group mt-2">
											<input type="password" value={password} name="logpass" onChange={(e)=>setPassword(e.target.value)} class="form-style" placeholder="Your Password" id="logpass" autocomplete="off"/>
												<i class="input-icon uil uil-lock-alt"></i>
											</div>
											<button className="btn mt-4" onClick={handleLogin}>Submit</button>
											{errorMessage && <p className="error-message">{errorMessage}</p>}
    										{successMessage && <p className="success-message">{successMessage}</p>}	
												
				      					</div>
			      					</div>
			      				</div>
								<div class="card-back">
									<div class="center-wrap">
										<div class="section text-center">
											<h4  style ={{color: "#1f6e8c"}} class="mb-4 pb-3">Log In as User</h4>
											<div class="form-group mt-2">
												<input type="email" name="logemail" value={email} onChange={(e)=>setEmail(e.target.value)} class="form-style" placeholder="Your Email" id="logemail" autocomplete="off"/>
												<i class="input-icon uil uil-at"></i>
											</div>	
											<div class="form-group mt-2">
												<input type="password" value={password} name="logpass" onChange={(e)=>setPassword(e.target.value)} class="form-style" placeholder="Your Password" id="logpass" autocomplete="off"/>
												<i class="input-icon uil uil-lock-alt"></i>
											</div>
											<button className="btn mt-4"  onClick={handleLogin}>Submit</button>
											{errorMessage && <p className="error-message">{errorMessage}</p>}
    										{successMessage && <p className="success-message">{successMessage}</p>}	
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


