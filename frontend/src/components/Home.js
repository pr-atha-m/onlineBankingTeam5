
import React from "react";
import "./Styles/Home.css";
function Home() {
  return (
    <>
    <nav className='navbar'>
      <div className = "logo">FinShot</div>

      <ul className='nav-links'>
      <li><a href='/about'>About</a></li>
        <li><a href='/login'>Login</a></li>
        <li><a href='/signup'>Register</a></li>
        
        

      </ul>


    </nav>

{/* 
<section>
    <div className='home-title'>
        <h1>Welcome to FinShot</h1>
        </div>
</section> */}

</>
  );
}

export default Home