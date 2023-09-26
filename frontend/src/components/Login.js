import React, { useState, useEffect } from "react";
import "./Styles/Login.css";
import loginpic from "../images/login.jpg";
import Cookies from 'js-cookie';


import { useNavigate, NavLink } from "react-router-dom";
import User from "./User";
export const Login = () => {
  const navigate = useNavigate();
  const [formErrors, setFormErrors] = useState({});
  const [isLoggedIn, setIsLoggedIn] = useState(false)
  const [isSubmit, setIsSubmit] = useState(false);
  
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

  const loginHandler = async(e) => {
    e.preventDefault();
    setFormErrors(validateForm(user));
    setIsSubmit(true);

   


    // if (!formErrors) {

    // }
  };

  useEffect(() => {
    if(Cookies.get("myCookie")){
        navigate("/service")
    }
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      console.log(user);
      
      let options = {
        method:"POST",
        headers: {
          'Content-Type': 'application/json',
      },
        body:JSON.stringify(
         
          {
              "email":user.email,
              "pwd":user.password
          
          }
        
        )
  
      }
      fetch("http://localhost:8080/authentication/login", options)

      .then((resp) => resp.json())
      .then((resp) => {
        console.log(resp)
          if(resp.token){
            // localStorage.setItem("emailId", user.email);
            Cookies.set('emailId', user.email)
            Cookies.set('myCookie', resp.token, {expires:1});

         
          
       
            navigate('/service')
          }
          else{
            alert("Invalid Credentials")
          }
  
     

      })


      
     

      
        

    
     
    }
  }, [formErrors]);


  if(isLoggedIn){
    return <div>logged in</div>
  }
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
        <p className="formerros" >{formErrors.email}</p>
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
        <button onClick={loginHandler}>
          Login
        </button>
    
                  
    
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
          </div>
    
  );
};
export default Login;
