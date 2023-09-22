import React from 'react';

import '../styles/LandingPage.css';
import axios from 'axios';

const LandingPage = () => {

  const handleTestClick=async()=>{

     const response=await axios.get('http://localhost:8085/ezloans/api/employeecardsofemployee/103');
     console.log(response);
  }
  return (
    <div className='landing'>
    <h1>Welcome to EZLoans</h1>
    <br></br>
    <h3>Your one step solution to loan management</h3>
    <button onClick={handleTestClick}>Trial button</button>
    </div>
  )};

  export default LandingPage;