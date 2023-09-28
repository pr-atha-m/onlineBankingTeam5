import React from "react";
import "./Styles/Animate.css";
import { Link, useNavigate } from "react-router-dom";
import Cookies from "universal-cookie";
const Navbar = (props) => {
  const navigate = useNavigate();
  const cookie = new Cookies();
  const handleClick = () => {
    cookie.remove("myCookie", { path: "/" });
    cookie.remove("emailId", { path: "/" });
    cookie.remove("first", { path: "/" });
    cookie.remove("last", { path: "/" });
    cookie.remove("acc_no", { path: "/" });
    cookie.remove("status", { path: "/" });
    cookie.remove("searchEmail", { path: "/" });

    console.log("hello");
    navigate("/");
  };
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
            {cookie.get("myCookie") ? (
              <>
                <span className="animate-text">
                  {" "}
                  Welcome,{" "}
                  {cookie.get("first") ? cookie.get("first") : props.first}{" "}
                  {cookie.get("last") ? cookie.get("last") : props.last} !
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
