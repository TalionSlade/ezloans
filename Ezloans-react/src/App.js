import './App.css';
import NavBar from './components/Navbar';
import Registeration from './components/Registeration';
import Login from './components/Login';
import { Container, Col, Carousel } from 'react-bootstrap';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Sidebar from './components/Sidebar';
import { Row } from 'react-bootstrap';
import LandingPage from './components/LandingPage';
// import {library} from '@fortawesome/fontawesome-svg-core';
// import {faSignIn, faCameraRetro} from '@fortawesome/free-solid-svg-icons';

// library.add(faSignIn,faCameraRetro);
function App() {
  return (
   <div className="App">
      <section>
      
        <div style={{
          backgroundImage: "url(/images/landing.jpg)",
          backgroundSize:"cover",
          backgroundRepeat:"no-repeat",
          minHeight:"94vh",
          minWidth:"100vh"
        }}> 
        
        <Router>
          <NavBar/>
            <Routes>
            <Route path="" element={<LandingPage/>}/>
            <Route path="/register" element={<Registeration/>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/dashboard" element={<Dashboard/>}/>
          </Routes>
          
        </Router>
        
      
        </div>
      </section>
      
      
     

      
        
    
          
         
        
    
      <footer className="footer">
        <p>
          &copy; All Rights Reserved to Ezloans
        </p>

      </footer>
      </div>
    
  );
}

export default App;
