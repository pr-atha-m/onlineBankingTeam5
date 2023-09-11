import React, { useState, useEffect } from "react";
import "./Login.css";
import loginpic from "../images/login.jpg";
import axios from "axios";
import { useNavigate, NavLink } from "react-router-dom";
export const Login = ({ setUserState }) => {
  const navigate = useNavigate();
  const [isLoggedIn,SetisLoggedIn] = useState(false);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const [message,Setmessage] = useState("");

  const [user, setUserDetails] = useState({
    email: "",
    password: "",
  });

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
    if(message === "Login Successful"){
      setTimeout(() => {
       SetisLoggedIn(true)
      }, 2000);
    }
    else if(message  === "Password is incorrect"){

        console.log("Pasword is incorrect!")
    }
    else if(message.includes("User not found for this email")){
        alert("User not found for this email please create a new account")
    }

    else if (message === "Invalid email "){
        alert("Please enter a valid email!")
    }    
    // if (!formErrors) {

    // }
  };

  useEffect(() => {
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      console.log(user);
      let options = {
        method:"POST",
        headers: {
          'Content-Type': 'application/json',
      },
        body:JSON.stringify(
         
          {
              "user_email":user.email,
              "user_pwd":user.password
          
          }
        
        )
  
      }
      fetch("http://localhost:8080/banking/validate", options)
      .then((resp)=>  resp.json())
      .then((resp) => {
        Setmessage(resp.message)
        setUserState(resp.user);
        navigate("/", { replace: true });;

      });
     
    }
  }, [formErrors]);

    
  if (isLoggedIn) {
    return <div>You are logged in</div>;
  }


  return (
    <div>
            <div>
              <div className="container">
                <img className="loginimg" src={loginpic} alt="" />
                <div class="vl"></div>
    
                <div className="logindetails">
                  <h1>Login Page</h1>
                  <form>
     
        <input
          type="email"
          name="email"
          id="email"
          placeholder="Email"
          onChange={changeHandler}
          value={user.email}
        />
        <p >{formErrors.email}</p>
        <input
          type="password"
          name="password"
          id="password"
          placeholder="Password"
          onChange={changeHandler}
          value={user.password}
        />
        <p >{formErrors.password}</p>
        <button onClick={loginHandler}>
          Login
        </button>
                    <div >
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
          </div>
    
  );
};
export default Login;
