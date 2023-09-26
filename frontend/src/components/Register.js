import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, NavLink } from "react-router-dom";
import signuppic from '../images/signup.jpg'
import Footer from "./Footer";
import './Styles/Register.css'
const Register = () => {
  const navigate = useNavigate();

  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const [showPopup, setShowPopup] = useState(false);
  const [popupContent, setPopupContent] = useState({ type: null, message: '' });
  const [user, setUserDetails] = useState({
    fname: "",
    lname: "",
    email: "",
    password: "",
    cpassword: "",
  });

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUserDetails({
      ...user,
      [name]: value,
    });
  };
  const showAlertPopup = (type, message) => {
    setPopupContent({ type, message });
    setShowPopup(true);
  };

  // Function to hide the alert popup
  const hideAlertPopup = () => {
    setShowPopup(false);
    navigate("/", { replace: true });
  };

  const validateForm = (values) => {

    const error = {};
    const regex = /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;
    if (!values.fname) {
      error.fname = "First Name is required";
    }
    else if (!values.lname) {
      error.lname = "Last Name is required";
    }
    else if (!values.email) {
      error.email = "Email is required";
    } else if (!regex.test(values.email)) {
      error.email = "This is not a valid email format!";
    }
    else if (!values.password) {
      error.password = "Password is required";
    } else if (values.password.length < 4) {
      error.password = "Password must be more than 4 characters";
    } else if (values.password.length > 10) {
      error.password = "Password cannot exceed more than 10 characters";
    }
    else if (!values.cpassword) {
      error.cpassword = "Confirm Password is required";
    } else if (values.cpassword !== values.password) {
      error.cpassword = "Confirm password and password should be same";
    }
    return error;
  };
  const signupHandler = (e) => {
    e.preventDefault();
    setFormErrors(validateForm(user));
    setIsSubmit(true);
    // if (!formErrors) {
    //   setIsSubmit(true);
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
              "first_name":user.fname,
              "last_name":user.lname,
              "user_email":user.email,
              "user_pwd":user.password
          
          }
        
        )
  
      }
      fetch("http://localhost:8080/banking/user/register", options)
      .then((resp)=>  resp.json())
      .then((resp) => {
       
       
        showAlertPopup('success', "Account Opened Successfully");
        

      });
    }
  }, [formErrors]);
  return (
    <>
    <div className="body1">
    <div>
      <div className="container1">
        <img className="signupimg" src={signuppic} alt="" />
        <div class="v2"></div>

        <div className="signupdetails">
         
<form>
          <p className="register-title">Create your account</p>
          <input
            type="text"
            name="fname"
            id="fname"
            placeholder="First Name"
            onChange={changeHandler}
            value={user.fname}
            className="registerInputs"
          />
          <p className="formerros">{formErrors.fname}</p>
          <input
            type="text"
            name="lname"
            id="lname"
            placeholder="Last Name"
            onChange={changeHandler}
            value={user.lname}
            className="registerInputs"
          />
          <p className="formerros">{formErrors.lname}</p>
          <input
            type="email"
            name="email"
            id="email"
            placeholder="Email"
            onChange={changeHandler}
            value={user.email}
            className="registerInputs"
          />
          <p className="formerros">{formErrors.email}</p>
          <input
            type="password"
            name="password"
            id="password"
            placeholder="Password"
            onChange={changeHandler}
            value={user.password}
            className="registerInputs"
          />
          <p className="formerros">{formErrors.password}</p>
          <input
            type="password"
            name="cpassword"
            id="cpassword"
            placeholder="Confirm Password"
            onChange={changeHandler}
            value={user.cpassword}
            className="registerInputs"
          />
          <p className="formerros">{formErrors.cpassword}</p>
          <button  onClick={signupHandler}>
            Register
          </button>
        </form>
        <div className= "registered">
        <NavLink to="/login">Already registered?</NavLink>
        </div>
        
        </div>
      </div>
    </div>
    {showPopup && (
        <div className="popup-container">
          <div className="popup">
            <span className="close-button" onClick={hideAlertPopup}>
              &times;
            </span>
            {popupContent.type === 'success' ? (
              <p className="success">{popupContent.message}</p>
            ) : (
              <p className="error">{popupContent.message}</p>
            )}
          </div>
        </div>
      )}
  </div>

  <Footer/>
  </>
  );
};
export default Register;

