import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, NavLink } from "react-router-dom";
import createAccountpic from "../images/createAccount.jpg";
import Cookies from "js-cookie";
import "./Styles/OpenAccount.css";
const Register = () => {
  const navigate = useNavigate();

  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
 
  const [selectedOption, setSelectedOption] = useState("");

  const [showPopup, setShowPopup] = useState(false);
  const [popupContent, setPopupContent] = useState({ type: null, message: '' });
  
  const options = ["Savings", "Salary", "Current", "NRI"];
  const [user, setUserDetails] = useState({
    emailId: "",
    phoneNumber: "",
    accountType: "",
    fatherName: "",
    adhaarNumber: "",
    dateOfBirth: "",
    residentialAddress: "",
    permanentAddress: "",
    occupationType: "",
    grossAnnualIncome: "",
    sourceOfIncome: "",
    debitStatus: "",
    netBanking: false,
  });


  const showAlertPopup = (type, message) => {
    setPopupContent({ type, message });
    setShowPopup(true);
  };

  // Function to hide the alert popup
  const hideAlertPopup = () => {
    setShowPopup(false);
    navigate("/service", { replace: true });
  };
  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUserDetails({
      ...user,
      [name]: value,
    });
  };

  const handleOptionChange = (e) => {
    setSelectedOption(e.target.value);
  };

  const validateForm = (values) => {
    console.log(values);
    const error = {};
    const regexEmail = /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;
    const phoneRegex = /^\d{10}$/;
    const adhaarRegex = /^\d{12}$/;
    const incomeRegex = /^[0-9]+(\.[0-9]{1,2})?$/;
    if(!selectedOption){
      error.accountType = "Please Select An Account Type";
    }
    else if (!values.emailId) {
      error.emailId = "Email is required";
    } else if (!regexEmail.test(values.emailId)) {
      error.emailId = "This is not a valid email format!";
    } else if (!values.phoneNumber) {
      error.phoneNumber = "Phone Number is required!";
    } else if (!phoneRegex.test(values.phoneNumber)) {
      error.phoneNumber = "Please Enter a Valid Phone Number!";
    } else if (!values.fatherName) {
      error.fatherName = "Please Enter Your Father's Name";
    } else if (!values.adhaarNumber) {
      error.adhaarNumber = "Adhaar Number Required!";
    } else if (!adhaarRegex.test(values.adhaarNumber)) {
      error.adhaarNumber = "Please Enter a Valid Adhaar Number!";
    } else if (!values.dateOfBirth) {
      error.dateOfBirth = "Date Of Birth is Required!";
    } else if (!values.residentialAddress) {
      error.residentialAddress = "Residential Address Required!";
    } else if (!values.permanentAddress) {
      error.permanentAddress = "Permanent Address is Required!";
    } else if (!values.occupationType) {
      error.occupationType = "Occupation Type is required";
    } else if (!values.grossAnnualIncome) {
      error.grossAnnualIncome = "Gross Anuual income is required!";
    } else if (!values.sourceOfIncome) {
      error.sourceOfIncome = "Source of Income is required!";
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
    if(!Cookies.get('myCookie')){
      navigate('/login')
  }
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      console.log(user);
      let options = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          'Authorization' :  `Bearer ${Cookies.get("myCookie")}`
        },
        body: JSON.stringify({
          "emailId": user.emailId,
          "acc_type": selectedOption,
          "phone_no": user.phoneNumber,
          "father_name": user.fatherName,
          "aadhar_no": user.adhaarNumber,
          "dob": user.dateOfBirth,
          "res_addr": user.residentialAddress,
          "perm_addr": user.permanentAddress,
          "occ_type": user.occupationType,
          "gross_annual_income": user.grossAnnualIncome,
          "source_of_income": user.sourceOfIncome,
          "debit_status": false,
          "net_banking": false,
        }),
      };
      fetch("http://localhost:8080/banking/user/open", options)
        .then((resp) => resp.json())
        .then((resp) => {
          console.log(resp);
       
        // Replace with your actual success/error logic
        if (resp.status !== 400) {
          showAlertPopup('success', "Account Opened Successfully");
         
           // Update with the actual new balance
        } else {
          showAlertPopup('error', "Error");
        }
 

        });
    }
  }, [formErrors]);
  return (
    <div className="body1">
      <div>
        <div className="container4">
          <img className="accountimg" src={createAccountpic} alt="" />
          <div class="v3"></div>

          <div className="signupdetails">
            <form>
              <p className="openAccount-title">Open Savings Account</p>
              <p className="accountDetails">
                Please fill in the following details.
              </p>

              <select
                id="dropdown"
                value={selectedOption}
                className="dropdown"
                onChange={handleOptionChange}
              >
                <option value="">Select An Account Type</option>
                {options.map((option, index) => (
                  <option key={index} value={option}>
                    {option}
                  </option>
                ))}
              </select>

              <p className="formerros">{formErrors.accountType}</p>
              <input
                type="email"
                name="emailId"
                id="emailId"
                placeholder="Email"
                onChange={changeHandler}
                value={user.emailId}
                className="openinputs"
              />
              <p className="formerros">{formErrors.emailId}</p>

              {/* <input
            type="accountType"
            name="accountType"
            id="accountType"
            placeholder="Account Type"
            onChange={changeHandler}
            value={user.accountType}
            className="openinputs"
          /> */}
              {/* <p className="formerros" >{formErrors.email}</p> */}

              <input
                type="tel"
                name="phoneNumber"
                id="phoneNumber"
                placeholder="Phone Number"
                onChange={changeHandler}
                value={user.phoneNumber}
                className="openinputs"
              />
              <p className="formerros">{formErrors.phoneNumber}</p>

              <input
                type="text"
                name="fatherName"
                id="fatherName"
                placeholder="Father's Name"
                onChange={changeHandler}
                value={user.fatherName}
                className="openinputs"
              />
              <p className="formerros">{formErrors.fatherName}</p>

              <input
                type="number"
                name="adhaarNumber"
                id="adhaarNumber"
                placeholder="Adhaar Number"
                onChange={changeHandler}
                value={user.adhaarNumber}
                className="openinputs"
              />
              <p className="formerros">{formErrors.adhaarNumber}</p>

              <input
                type="date"
                name="dateOfBirth"
                id="dateOfBirth"
                placeholder="Date Of Birth"
                onChange={changeHandler}
                value={user.dateOfBirth}
                className="openinputs"
              />
              <p className="formerros">{formErrors.dateOfBirth}</p>

              <input
                type="text"
                name="residentialAddress"
                id="residentialAddress"
                placeholder="Residential Address"
                onChange={changeHandler}
                value={user.residentialAddress}
                className="openinputs"
              />
              <p className="formerros">{formErrors.residentialAddress}</p>

              <input
                type="text"
                name="permanentAddress"
                id="permanentAddress"
                placeholder="Permanent Address"
                onChange={changeHandler}
                value={user.permanentAddress}
                className="openinputs"
              />
              <p className="formerros">{formErrors.permanentAddress}</p>

              <input
                type="text"
                name="occupationType"
                id="occupationType"
                placeholder="Occupation Type"
                onChange={changeHandler}
                value={user.occupationType}
                className="openinputs"
              />
              <p className="formerros">{formErrors.occupationType}</p>
              <input
                type="number"
                name="grossAnnualIncome"
                id="grossAnnualIncome"
                placeholder="Gross Annual Income"
                onChange={changeHandler}
                value={user.grossAnnualIncome}
                className="openinputs"
              />
              <p className="formerros">{formErrors.grossAnnualIncome}</p>
              <input
                type="text"
                name="sourceOfIncome"
                id="sourceOfIncome"
                placeholder="Source Of Income"
                onChange={changeHandler}
                value={user.sourceOfIncome}
                className="openinputs"
              />
              <p className="formerros">{formErrors.sourceOfIncome}</p>

              <button className="openAccountBtn" onClick={signupHandler}>
                Submit
              </button>
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
            {popupContent.type === 'success' ? (
              <p className="success">{popupContent.message}</p>
            ) : (
              <p className="error">{popupContent.message}</p>
            )}
          </div>
        </div>
      )}
    </div>
  );
};
export default Register;
