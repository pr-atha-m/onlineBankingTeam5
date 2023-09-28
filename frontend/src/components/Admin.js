import React, { useState, useEffect } from "react";

import { Link } from "react-router-dom";
import searchimg from "../images/search.jpeg";
import Cookies from "universal-cookie";
import "./Styles/Admin.css"; // Import your CSS file for styling
import { useNavigate, NavLink } from "react-router-dom";

function Admin() {
  const cookie = new Cookies();
  const navigate = useNavigate();

  const [formErrors, setFormErrors] = useState({});
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  // const [email,setEmail] useState();
  const [isSubmit, setIsSubmit] = useState(false);
  const [user, setUserDetails] = useState({
    email: "",
  });

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUserDetails({
      ...user,
      [name]: value,
    });
  };
  const handleClick = () => {
    cookie.remove("searchEmail");
    cookie.remove("myCookie");
    cookie.remove("emailId");
    cookie.remove("first");
    cookie.remove("acc_no");
    console.log("hello");
    cookie.remove("last");
    cookie.remove("status");
    navigate("/");
  };
  const validateForm = (values) => {
    const error = {};
    const regex = /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;
    if (!values.email) {
      error.email = "Email is required";
    } else if (!regex.test(values.email)) {
      error.email = "Please enter a valid email address";
    }

    return error;
  };

  useEffect(() => {
    if (!cookie.get("myCookie")) {
      navigate("/login");
    }

    if (Object.keys(formErrors).length === 0 && isSubmit) {
      const options = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${cookie.get("myCookie")}`,
        },
      };
      fetch(`http://localhost:8080/admin/userdetails/${user.email}`, options)
        .then((resp) => resp.json())
        .then((resp) => {
          console.log(resp);

          cookie.set("searchEmail", user.email, { path: "/" });
          cookie.set("first", resp.first_name, { path: "/" });
          // localStorage.setItem("last", resp.last_name);

          // localStorage.setItem("searchEmail", user.email);
          // localStorage.setItem("first", resp.first_name);
          // localStorage.setItem("last", resp.last_name);
          //  localStorage.setItem("status", user.s);
        });

      navigate("/admin/userdetails", { replace: true });

      // if (!formErrors) {
    }
  }, [formErrors]);

  const loginHandler = async (e) => {
    // e.preventDefault();
    // console.log("hello");

    setFormErrors(validateForm(user));
    setIsSubmit(true);

    console.log(user);

    // }
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
                  to="/admin/viewall"
                  style={{ textDecoration: "none", color: "#333" }}
                >
                  View All Users
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
            </ul>
          </div>
        </div>
      </nav>

      <div>
        <div className="body1">
          <div className="container">
            <img className="loginimg" src={searchimg} alt="" />
            <div class="vl"></div>

            <div className="searchDetails">
              <p className="login-title">Search User</p>

              <div className="searchForm">
                <form>
                  <input
                    type="email"
                    name="email"
                    id="email"
                    placeholder="Email"
                    onChange={changeHandler}
                    value={user.email}
                    className="registerInputs"
                  />

                  <button onClick={loginHandler} className="searchBtn">
                    Search
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Admin;
