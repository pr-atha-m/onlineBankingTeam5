import React from "react";
import { Link } from "react-router-dom";

const Navbar = ({ isLoggedIn }) => {
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
            <li>
              <Link
                to="/service"
                style={{ textDecoration: "none", color: "#333" }}
              >
                Services
              </Link>
            </li>
            {isLoggedIn ? (
              <>
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
                    to="/dashboard"
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
