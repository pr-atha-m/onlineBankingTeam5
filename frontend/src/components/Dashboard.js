import React from 'react'
import "./Styles/Home.css";

const Dashboard = () => {
  return (
    <>
    <nav className='navbar'>
      <div className = "logo">Dashboard</div>

      <ul className='nav-links'>
        <li><a href='/services'>Services</a></li>
        <li><a href='/netbanking'>Net banking</a></li>
        <li><a href='/'>Logout</a></li>
        

      </ul>


    </nav>


<section>
    <div className='home-title'>
        <h1>Welcome to FinShot</h1>
        </div>
</section>

</>
  )
}

export default Dashboard
