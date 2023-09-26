import React from "react";
import "./Styles/Animate.css";
import { Link, useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
const Navbar = ({ isLoggedIn }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    Cookies.remove("myCookie")
    Cookies.remove("emailId")
    Cookies.remove("first")
    Cookies.remove("last")
    Cookies.remove("status")
 
    console.log("hello")
    navigate('/')
  }
  return (
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
            Finshot
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
                
            
            {Cookies.get("myCookie") ? (
              <>

<span className="animate-text"> Welcome, {localStorage.getItem("first")} {localStorage.getItem("last")} ! 
                 
                 </span>
           
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
                  onClick={handleClick}
                    style={{
                      textDecoration: "none",
                      color: "#333",
                      marginRight: "20px",
                    }}
                  >
                    Logout
                  </Link>
                </li>
              </>
            ) : (
              <>
                <li>
                  <Link
                  
                   to="/login"
                    style={{
                      textDecoration: "none",
                      color: "#333",
                    }}
                  >
                    Login
                  </Link>
                </li>
                <li>
                  <Link
                    to="/signup"
                    style={{
                      textDecoration: "none",
                      color: "#333",
                      marginRight: "20px",
                    }}
                  >
                    Sign Up
                  </Link>
                </li>
              </>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
