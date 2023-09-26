import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, NavLink } from "react-router-dom";
import transferpic from "../images/transfer.jpeg";
import "./Styles/OpenAccount.css";
import Cookies from "js-cookie";
const Transfer = () => {
  const navigate = useNavigate();

  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);

  const [showPopup, setShowPopup] = useState(false);
  const [popupContent, setPopupContent] = useState({ type: null, message: '' });
  const [selectedOption, setSelectedOption] = useState("");

  const [selectedOption1, setSelectedOption1] = useState("");


 
  const options = ["NEFT", "IMPS", "RTGS"];
  const [user, setUserDetails] = useState({
    receiver_account: "",
    amount: "",
    maturity_remarks: "",
    instructions: "",
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
    navigate("/service", { replace: true });
  };
  const handleOptionChange = (e) => {
    setSelectedOption(e.target.value);
  };
  const handleOptionChange1 = (e) => {
    setSelectedOption1(e.target.value);
  };


  const [details,setDetails]=  useState([]);
  
//   useEffect(() => {
 
   
    
   
  
// }, []);


const options1 = [];


  {details.map((account, index) => (
      options1.push(account.acc_no)
  ))}


  const validateForm = (values) => {
    console.log(values);
    const error = {};
    const regexEmail = /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;
    const phoneRegex = /^\d{10}$/;
    const adhaarRegex = /^\d{12}$/;

    if(!selectedOption){
        error.trans_mode = "Please select Transaction Mode"
      }
      else if (!selectedOption1) {
        error.sender_account = "Please select an Account Number";
      }
      else if (!values.amount) {
        error.amount = "Please enter a Amount";
      }else if(values.amount <=0){
        error.amount = "Please Enter A valid Amount";
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
            sender_account: selectedOption1,
            receiver_account: user.receiver_account ,
            amount: user.amount,
            maturity_remarks: user.maturity_remarks,
            trans_mode: selectedOption,
            instructions: user.instructions,
    
        }),
      };
      fetch("http://localhost:8080/transaction/execute", options)
        .then((resp) => resp.json())
        .then((resp) => {
        
          if (resp.status !== 400) {
            showAlertPopup('success', "Transaction Success");
           
             // Update with the actual new balance
          } else {
            showAlertPopup('Transaction Failed', resp.message);
          }

          // navigate("/dashboard", { replace: true });;
        });
    }

    let options = {
        method:"GET",
        headers: {
          'Content-Type': 'application/json',
  
        }
        }
      fetch(`http://localhost:8080/banking/user/by-email?emailId=${Cookies.get("emailId")}`, options)
      .then((resp)=> resp.json())
      .then((resp) => {
       
      setDetails(resp);
      console.log(resp)
     
        
  
      });
  }, [formErrors]);
  return (
    <div className="body1">
      <div>
        <div className="container1">
          <img className="accountimg" src={transferpic} alt="" />
          <div class="v2"></div>

          <div className="signupdetails">
            <form>
              <p className="openAccount-title">Transfer Money</p>
              <p className="accountDetails">
                Please fill in the following details.
              </p>

              <select
                id="dropdown"
                value={selectedOption}
                className="dropdown"
                onChange={handleOptionChange}
              >
                <option value="">Select Mode of Transaction ⋆</option>
                {options.map((option, index) => (
                  <option key={index} value={option}>
                    {option}
                  </option>
                ))}
              </select>
              <p className="formerros">{formErrors.trans_mode}</p>
              <select
                id="dropdown"
                className="dropdown1"
                value={selectedOption1}
                onChange={handleOptionChange1}
              >
                <option value="">Sender Account Number ⋆</option>
                {options1.map((option, index) => (
                  <option key={index} value={option}>
                    {option}
                  </option>
                ))}
              </select>
              <p className="formerros">{formErrors.sender_account}</p>

              {/* <input
            type="accountType"
            name="accountType"
            id="accountType"
            placeholder="Account Type"
            onChange={changeHandler}
            value={user.accountType}
            className="registerInputs"
          /> */}
              {/* <p className="formerros" >{formErrors.email}</p> */}
              <input
                type="number"
                name="receiver_account"
                id="receiver_account"
                placeholder="Receiver Account ⋆"
                onChange={changeHandler}
                value={user.receiver_account}
                className="registerInputs"
              />
              <p className="formerros">{formErrors.receiver_account}</p>
            

              <input
                type="number"
                name="amount"
                id="amount"
                placeholder="Amount ⋆"
                onChange={changeHandler}
                value={user.amount}
                className="registerInputs"
              />
              <p className="formerros">{formErrors.amount}</p>

              <input
                type="text"
                name="maturity_remarks"
                id="maturity_remarks"
                placeholder="Maturity Remarks"
                onChange={changeHandler}
                value={user.maturity_remarks}
                className="registerInputs"
              />
              <p className="formerros">{formErrors.maturity_remarks}</p>


              <input
                type="text"
                name="instructions"
                id="instructions"
                placeholder="Instructions"
                onChange={changeHandler}
                value={user.instructions}
                className="registerInputs"
              />
              <p className="formerros">{formErrors.instructions}</p>
              

              <button className="openAccountBtn" onClick={signupHandler}>
                Transfer
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
export default Transfer;
