import './App.css';
import React, {Component, useState} from "react";
import NavBar from './components/Navbar';
import Registeration from './components/Registeration';
import Login from './components/Login';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Sidebar from './components/Sidebar';
import 'bootstrap-icons/font/bootstrap-icons.css';
import { AuthProvider } from './components/AuthContext';
import Loan from './components/Loan';
import LandingPage from './components/LandingPage';
import { useAuth} from './components/AuthContext';

function App() {
  const [toggle, setToggle] = useState(false);
  const Toggle = () => { setToggle(!toggle) };
  // const {isLoggedIn, setIsUser} = useAuth();

  return (
    <AuthProvider>
    <div className="App">
      <div className='row' style = {{
        backgroundImage: "url(/images/landing.jpg)",
        backgroundSize: "cover",
        backgroundRepeat: "no-repeat",
        minHeight: "94vh",
        minWidth: "100vh",
        margin: 0 }}>
       
          <Router>
          { toggle && <div className='col-2 vh-100' style={{padding:0}}>
            <Sidebar Toggle={Toggle}/>
            </div>}
            <div className='col' style={{padding:0}}>
              <NavBar Toggle={Toggle}/>
              <Routes>
                <Route path="" element={<LandingPage/>}/>
                <Route path="/addemp" element={<Registeration/>}/>
                <Route path="/login" element={<Login />}/>
                <Route path="/dashboard" element={<Dashboard/>}/>
                <Route path="/loan" element={<Loan/>}/>
              </Routes>
            </div>
          
          </Router>
        
      </div>
      <footer className="footer">
        <p>
          &copy; All Rights Reserved to Ezloans
        </p>
        
      </footer>
    </div>
    
    </AuthProvider>
  );
}

export default App;
