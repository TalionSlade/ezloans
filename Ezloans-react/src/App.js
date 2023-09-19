import './App.css';
import React, {Component, useState} from "react";
import NavBar from './components/Navbar';
import Registeration from './components/Registeration';
import Login from './components/Login';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Sidebar from './components/Sidebar';
import 'bootstrap-icons/font/bootstrap-icons.css';
// import {library} from '@fortawesome/fontawesome-svg-core';
// import {faSignIn, faCameraRetro} from '@fortawesome/free-solid-svg-icons';

// library.add(faSignIn,faCameraRetro);
function App() {
  const [toggle, setToggle] = useState(false);
  const Toggle = () => {
    setToggle(!toggle)
  }
  return (
    <div className="App">
          <div className='row'>
          
          <Router>
          { toggle && <div className='col-2 vh-100' style={{padding:0}}>
            <Sidebar Toggle={Toggle}/>
          </div>}
            <div className='col' style={{padding:0}}>
            <NavBar Toggle={Toggle}/>
            <Routes>
              
            <Route path="/register" element={<Registeration/>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/dashboard" element={<Dashboard/>}/>
          </Routes>
          <footer className="footer">
        <p>
          &copy; All Rights Reserved to Ezloans
        </p>

      </footer>
          </div>
          
        </Router>
          </div>
        
      
    </div>
    

  );
}

export default App;
