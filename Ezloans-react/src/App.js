import './App.css';
import React, {Component, useState} from "react";
import NavBar from './components/Navbar';
import Registeration from './components/Registeration';
import Login from './components/Login';
import { Container, Col, Carousel } from 'react-bootstrap';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Sidebar from './components/Sidebar';
import 'bootstrap-icons/font/bootstrap-icons.css';
import { ReactSession } from 'react-client-session';
import { AuthProvider } from './components/AuthContext';
import Loan from './components/Loan';
import { Row } from 'react-bootstrap';
import LandingPage from './components/LandingPage';
// import {library} from '@fortawesome/fontawesome-svg-core';
// import {faSignIn, faCameraRetro} from '@fortawesome/free-solid-svg-icons';

// library.add(faSignIn,faCameraRetro);
function App() {
  const [toggle, setToggle] = useState(false);
  var [isLogin, setIsLogin] = useState(false);
  // const [isUser, setIsUser] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const Toggle = () => {
    setToggle(!toggle)
  }
  // ReactSession.setStoreType("localStorage");
  // ReactSession.set("isLogin",false)
  // isLogin = ReactSession.get("isLogin");
  

  return (
    <div className="App">
          <div className='row' style = {{
            backgroundImage: "url(/images/landing.jpg)",
            backgroundSize: "cover",
            backgroundRepeat: "no-repeat",
            minHeight: "94vh",
            minWidth: "100vh"
          }}>
          <AuthProvider>
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
        </AuthProvider>
          </div>
          <footer className="footer">
            <p>
              &copy; All Rights Reserved to Ezloans
            </p>

          </footer>
      
    </div>
    

  );
}

export default App;
