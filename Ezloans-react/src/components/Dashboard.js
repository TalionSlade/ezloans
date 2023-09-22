import React from 'react';
import '../styles/Dashboard.css'
import { useAuth } from './AuthContext';

const Dashboard = () => {
  const { isLoggedIn } = useAuth();

  return (
    <div>
        {isLoggedIn && <div class="container-fluid">
          
        <div class="row">
          <div class="col-md-12">
            <h4 style={{padding: "10px"}}>
              Dashboard 
              {/* {authUser.Name} */}
              </h4>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 mb-6">
            <div class="card h-100 count-card">
              <div class="card-header">Total Loans Taken</div>
              <div class="card-body py-5">3</div>
            </div>
          </div>
          <div class="col-md-6 mb-6">
            <div class="card h-100 count-card">
              <div class="card-header py-5">Total Items Bought</div>
              <div class="card-body py-5">3</div>
            </div>
          </div>

        </div>
        <br></br>
        <div class="row">
          <div class="col-md-6 mb-3">
            <div class="card h-100">
              <div class="card-header">
                <span class="me-2"><i class="bi bi-bar-chart-fill"></i></span>
                Sample Chart (Loan amount vs loan id)
              </div>
              <div class="card-body">
                <canvas class="chart" width="400" height="200"></canvas>
              </div>
            </div>
          </div>
          <div class="col-md-6 mb-3">
            <div class="card h-100">
              <div class="card-header">
                <span class="me-2"><i class="bi bi-bar-chart-fill"></i></span>
                Sample Chart (Loan type vs loan count)
              </div>
              <div class="card-body">
                <canvas class="chart" width="400" height="200"></canvas>
              </div>
            </div>
          </div>
        </div>
      </div>}
    </div>
  );
}

export default Dashboard