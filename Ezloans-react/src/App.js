import './App.css';
import NavBar from './components/Navbar';
import Registeration from './components/Registeration';
import Login from './components/Login';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Sidebar from './components/Sidebar';
// import {library} from '@fortawesome/fontawesome-svg-core';
// import {faSignIn, faCameraRetro} from '@fortawesome/free-solid-svg-icons';

// library.add(faSignIn,faCameraRetro);
function App() {
  return (
    <div className="App">
      <section>
        <div style={{
          backgroundSize:"cover",
          backgroundRepeat:"no-repeat",
          minHeight:"100vh",
          minWidth:"100vh"
        }}>
        <Router>
          <NavBar/>

            <Routes>
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
