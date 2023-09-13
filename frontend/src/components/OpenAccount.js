import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, NavLink } from "react-router-dom";
import createAccountpic from '../images/createAccount.jpg'
import './Styles/OpenAccount.css'
const Register = () => {
  const navigate = useNavigate();

  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const  min= 100000000000;
  const max = 999999999999;
const [selectedOption, setSelectedOption] = useState('');
  let acc_no = Math.floor(Math.random()* (max-min+1)) + min;
  const options = [
    'Savings',
    'Salary',
    'Current',
    'NRI'
  ]
  const [user, setUserDetails] = useState({
    email: '',
    phoneNumber: '',
    accountType: '',
    fatherName: '',
    adhaarNumber: '',
    dateOfBirth: '',
    residentialAddress: '',
    permanentAddress: '',
    occupationType: '',
    grossAnnualIncome: '',
    sourceOfIncome: '',
    debitStatus: '',
    netBanking: false,
  });

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUserDetails({
      ...user,
      [name]: value,
    });
  };

  const handleOptionChange = (e) =>{
    setSelectedOption(e.target.value);
  }

  const validateForm = (values) => {
    console.log(values)
    const error = {};
    const regexEmail= /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;
   const phoneRegex = /^\d{10}$/
   const adhaarRegex = /^\d{12}$/;
   const incomeRegex = /^[0-9]+(\.[0-9]{1,2})?$/;
    if (!values.email) {

        error.email = "Email is required";
      } else if (!regexEmail.test(values.email)) {
        error.email = "This is not a valid email format!";
      }
      else if(!values.phoneNumber){
        error.phoneNumber = "Phone Number is required!"
      }
      else if(!phoneRegex.test(values.phoneNumber)){
        error.phoneNumber = "Please Enter a Valid Phone Number!"
      }else if(!values.fatherName){
        error.fatherName = "Please Enter Your Father's Name";
      }

      else if(!values.adhaarNumber){
        error.adhaarNumber = "Adhaar Number Required!"
      }
       else if(!adhaarRegex.test(values.adhaarNumber)){
        error.adhaarNumber = "Please Enter a Valid Adhaar Number!"

       }
       else if(!values.dateOfBirth){
        error.dateOfBirth = "Date Of Birth is Required!"

       }
       else if(!values.residentialAddress){
        error.residentialAddress = "Residential Address Required!"

       }
       else if(!values.permanentAddress){
        error.permanentAddress = "Permanent Address is Required!"

       }
       else if(!values.occupationType){
        error.occupationType = "Occupation Type is required"

       }
       else if(!values.grossAnnualIncome){
        error.grossAnnualIncome = "Gross Anuual income is required!"

       }
       else if(!values.sourceOfIncome){
        error.sourceOfIncome = "Source of Income is required!"

       }
    // const phoneRegex = /^\d{10}$/;
    // if (!values.email) {
    //   error.fname = "First Name is required";
    // }
    // else if (!values.lname) {
    //   error.lname = "Last Name is required";
    // }
    // else if (!values.email) {

    //   error.email = "Email is required";
    // } else if (!regexEmail.test(values.email)) {
    //   error.email = "This is not a valid email format!";
    // }
    // else if (!values.password) {
    //   error.password = "Password is required";
    // } else if (values.password.length < 4) {
    //   error.password = "Password must be more than 4 characters";
    // } else if (values.password.length > 10) {
    //   error.password = "Password cannot exceed more than 10 characters";
    // }
    // else if (!values.cpassword) {
    //   error.cpassword = "Confirm Password is required";
    // } else if (values.cpassword !== values.password) {
    //   error.cpassword = "Confirm password and password should be same";
    // }
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
                "user_email":user.email,
                "acc_no":"",
                "acc_type":selectedOption,
                "phone_no":user.phoneNumber,
                "father_name":user.fatherName,
                "aadhar_no":user.adhaarNumber,
                "dob":user.dateOfBirth,
                "res_addr":user.residentialAddress,
                "perm_addr":user.permanentAddress,
                "occ_type":user.occupationType,
                "gross_annual_income":user.grossAnnualIncome,
                "source_of_income":user.sourceOfIncome,
                "debit_status":false,
                "net_banking":false
            
            }
        
        )
  
      }
      fetch("http://localhost:8080/banking/user/createaccount", options)
      .then((resp)=>  resp.json())
      .then((resp) => {
       
        alert("Account Opened")
        
        // navigate("/dashboard", { replace: true });;

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
          <p className="accountDetails">Please fill in the following details.</p>

   <select id="dropdown" value={selectedOption} onChange={handleOptionChange}>

    <option value="">Select An Account Type</option>
    {options.map((option,index) => (
        <option key={index} value={option}>

            {option}
        </option>
    ))}


   </select>
          <input
            type="email"
            name="email"
            id="email"
            placeholder="Email"
            onChange={changeHandler}
            value={user.email}
            className="openinputs"
          />
           <p className="formerros" >{formErrors.accountType}</p>

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
           <p className="formerros" >{formErrors.phoneNumber}</p>


<input
            type="text"
            name="fatherName"
            id="fatherName"
            placeholder="Father's Name"
            onChange={changeHandler}
            value={user.fatherName}
            className="openinputs"
          />
 <p className="formerros" >{formErrors.fatherName}</p>

          <input
            type="number"
            name="adhaarNumber"
            id="adhaarNumber"
            placeholder="Adhaar Number"
            onChange={changeHandler}
            value={user.adhaarNumber}
            className="openinputs"
          />
 <p className="formerros" >{formErrors.adhaarNumber}</p>
       
          <input
            type="date"
            name="dateOfBirth"
            id="dateOfBirth"
            placeholder="Date Of Birth"
            onChange={changeHandler}
            value={user.dateOfBirth}
            className="openinputs"
          />
          <p className="formerros" >{formErrors.dateOfBirth}</p>
         
          
          <input
            type="text"
            name="residentialAddress"
            id="residentialAddress"
            placeholder="Residential Address"
            onChange={changeHandler}
            value={user.residentialAddress}
            className="openinputs"
          />
           <p className="formerros" >{formErrors.residentialAddress}</p>
          
          <input
            type="text"
            name="permanentAddress"
            id="permanentAddress"
            placeholder="Permanent Address"
            onChange={changeHandler}
            value={user.permanentAddress}
            className="openinputs"
          />
<p className="formerros" >{formErrors.permanentAddress}</p>

          <input
            type="text"
            name="occupationType"
            id="occupationType"
            placeholder="Occupation Type"
            onChange={changeHandler}
            value={user.occupationType}
            className="openinputs"
          />
<p className="formerros" >{formErrors.occupationType}</p>
<input
            type="number"
            name="grossAnnualIncome"
            id="grossAnnualIncome"
            placeholder="Gross Annual Income"
            onChange={changeHandler}
            value={user.grossAnnualIncome}
            className="openinputs"
          />
<p className="formerros" >{formErrors.grossAnnualIncome}</p>
<input
            type="text"
            name="sourceOfIncome"
            id="sourceOfIncome"
            placeholder="Source Of Income"
            onChange={changeHandler}
            value={user.sourceOfIncome}
            className="openinputs"
          />
          <p className="formerros" >{formErrors.sourceOfIncome}</p>
        
          <button  className="openAccountBtn" onClick={signupHandler}>
            Submit
          </button>
        </form>
        
        
        </div>
      </div>
    </div>
  </div>
  );
};
export default Register;

