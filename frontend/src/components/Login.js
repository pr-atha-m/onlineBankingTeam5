import React, { useState, useEffect } from "react";
import "./Styles/Login.css";

import loginpic from "../images/login.jpg";
import Cookies from "universal-cookie";

import { useNavigate } from "react-router-dom";

export const Login = () => {
  const [showPopup, setShowPopup] = useState(false);
  const [popupContent, setPopupContent] = useState("");
  const cookie = new Cookies();
  const navigate = useNavigate();
  const [formErrors, setFormErrors] = useState({});

  const [isSubmit, setIsSubmit] = useState(false);

  const [user, setUserDetails] = useState({
    email: "",
    password: "",
  });

  const showAlertPopup = (message) => {
    setPopupContent(message);
    setShowPopup(true);
  };

  // Function to hide the alert popup
  const hideAlertPopup = () => {
    setShowPopup(false);
  };

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUserDetails({
      ...user,
      [name]: value,
    });
  };
  const validateForm = (values) => {
    const error = {};
    const regex = /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;
    if (!values.email) {
      error.email = "Email is required";
    } else if (!regex.test(values.email)) {
      error.email = "Please enter a valid email address";
    }
    if (!values.password) {
      error.password = "Password is required";
    }
    return error;
  };

  const loginHandler = (e) => {
    e.preventDefault();
    setFormErrors(validateForm(user));
    setIsSubmit(true);
  };

  useEffect(() => {
    if (cookie.get("myCookie")) {
      navigate("/service");
    }
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      console.log(user);

      let options = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: user.email,
          pwd: user.password,
        }),
      };

      try {
        fetch("http://localhost:8080/authentication/login", options)
          .then((resp) => {
            if (!resp.ok) {
              showAlertPopup("Invalid Username or Password !");
            }
            return resp.json();
          })
          .then((resp) => {
            cookie.set("emailId", user.email, { path: "/" });
            cookie.set("myCookie", resp.token, { path: "/" });
            if (user.email === "admin@gmail.com") {
              navigate("/admin", { replace: true });
            } else {
              navigate("/service", { replace: true });
            }
          })
          .catch((error) => {
            console.error(error);
          });
      } catch (error) {
        console.error(error);
      }
    }
  }, [formErrors]);

  return (
    <div className="body1">
      <div>
        <div className="container">
          <img className="loginimg" src={loginpic} alt="" />
          <div class="vl"></div>

          <div className="logindetails">
            <p className="login-title">Login Page</p>
            <form>
              <input
                type="email"
                name="email"
                id="email"
                placeholder="Email"
                onChange={changeHandler}
                value={user.email}
                className="loginInputs"
              />
              <p className="formerros">{formErrors.email}</p>
              <input
                type="password"
                name="password"
                id="password"
                placeholder="Password"
                onChange={changeHandler}
                value={user.password}
                className="loginInputs"
              />
              <p className="formerros">{formErrors.password}</p>
              <button onClick={loginHandler}>Login</button>

              <div>
                <div className="createaccount">
                  <a href="/signup">Create Account</a>
                </div>

                <div className="forgotpassword">
                  <a href="/forgotpassword">Forgot Password?</a>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>

      {showPopup && (
        <div className="popup-container">
          <div className="popup">
            <span className="close-button" onClick={hideAlertPopup}>
              &times;
            </span>

            <p className="error">{popupContent}</p>
          </div>
        </div>
      )}
    </div>
  );
};
export default Login;
