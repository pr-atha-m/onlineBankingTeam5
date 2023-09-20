import React, { useState } from 'react';

import { Link } from "react-router-dom";

import './Styles/Admin.css'; // Import your CSS file for styling

function Admin() {
    const [email, setEmail] = useState('');
    const [userData, setUserData] = useState(null);

    const fetchUserData = () => {
        // You can replace this URL with your backend API endpoint
        fetch(`/api/users?email=${email}`)
            .then(response => response.json())
            .then(data => {
                setUserData(data);
            })
            .catch(error => {
                console.error('Error fetching user data:', error);
                setUserData(null);
            });
    };

    return (
        <div>
        <nav style={{ backgroundColor: "#f5f5f5", padding: "7px" }}>
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <div>
            <Link
              to="/"
              style={{
                textDecoration: "none",
                color: "#333",
                fontSize: "24px",
                marginLeft: "20px",
              }}
            >
             Admin Dashboard
            </Link>
          </div>
          <div>
            <ul
              style={{
                listStyleType: "none",
                padding: 0,
                display: "flex",
                gap: "20px",
              }}
            >
              
    
                  <li>
                    <Link
                      to="/profile"
                      style={{ textDecoration: "none", color: "#333" }}
                    >
                      Profile
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/"
                      style={{
                        textDecoration: "none",
                        color: "#333",
                        marginRight: "20px",
                      }}
                    >
                      Logout
                    </Link>
                  </li>
                
            </ul>
          </div>
        </div>
      </nav>

<div>
    <div className = "body1">
<div className="container">
  <img className="loginimg"  alt="" />
  <div class="vl"></div>

  <div className="searchDetails">
    <p className="login-title">Search User</p>
    <form>

<input
type="email"
name="email"
id="email"
placeholder="Email"
// onChange={changeHandler}
// value={user.email}
className="registerInputs"
/>
{/* <p className="formerros" >{formErrors.email}</p> */}


<button className= "searchBtn">
Search
</button>
    </form>
  </div>
</div>
</div>
</div>
</div>
    );
}

export default Admin;