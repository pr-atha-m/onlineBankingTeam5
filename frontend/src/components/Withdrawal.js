import React, { useState, useEffect } from "react";
import "./Styles/Withdraw.css";
import Cookies from "universal-cookie";

import withdrawpic from "../images/withdrawal.jpeg";

import { useNavigate, NavLink } from "react-router-dom";
export const Withdrawal = ({ setUserState }) => {
  const cookie = new Cookies();
  const navigate = useNavigate();
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const [user, setUserDetails] = useState({
    amount: "",
  });

  const [showPopup, setShowPopup] = useState(false);
  const [popupContent, setPopupContent] = useState({ type: null, message: "" });

  // Function to show the alert popup with the provided content
  const showAlertPopup = (type, message) => {
    setPopupContent({ type, message });
    setShowPopup(true);
  };

  // Function to hide the alert popup
  const hideAlertPopup = () => {
    setShowPopup(false);
    navigate("/service", { replace: true });
  };

  const loginHandler = async (e) => {
    e.preventDefault();

    setFormErrors(validateForm(user));
    console.log(user, selectedOption);
    setIsSubmit(true);

    // if (!formErrors) {

    // }
  };
  const [details, setDetails] = useState([]);
  useEffect(() => {
    if (!cookie.get("myCookie")) {
      navigate("/login");
    }

    let options = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${cookie.get("myCookie")}`,
      },
    };
    fetch(
      `http://localhost:8080/banking/user/by-email?emailId=${cookie.get(
        "emailId"
      )}`,
      options
    )
      .then((resp) => resp.json())
      .then((resp) => {
        setDetails(resp);
        console.log(resp);
      });

    if (Object.keys(formErrors).length === 0 && isSubmit) {
      const options = {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${cookie.get("myCookie")}`,
        },
      };

      fetch(
        `http://localhost:8080/banking/withdraw?acc_no=${selectedOption}&amount=${user.amount}`,
        options
      ).then((resp) =>
        resp.json().then((resp) => {
          console.log(resp);
          setTimeout(() => {
            // Replace with your actual success/error logic
            if (resp.status !== 400) {
              showAlertPopup(
                "success",
                `Withdrawal successful. ${resp.message}`
              ); // Update with the actual new balance
            } else {
              showAlertPopup("error", resp.message);
            }
          }, 500);
        })
      );
    }
  }, [formErrors]);

  const [selectedOption, setSelectedOption] = useState("");
  const options = [];

  {
    details.map((account, index) => options.push(account.acc_no));
  }

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
    const error = {};
    const regex = /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;
    if (!selectedOption) {
      error.account = "Please select a account number";
    } else if (!values.amount) {
      error.amount = "Please enter a Amount";
    } else if (values.amount <= 0) {
      error.amount = "Please Enter A valid Amount";
    }
    return error;
  };

  return (
    <div className="body1">
      <div>
        <div className="container5">
          <img className="loginimg" src={withdrawpic} alt="" />
          <div class="vl"></div>

          <div className="logindetails">
            <p className="withdraw-title">Withdraw Money</p>
            <form>
              <select
                id="dropdown"
                value={selectedOption}
                onChange={handleOptionChange}
              >
                <option value="">Select An Account Number</option>
                {options.map((option, index) => (
                  <option key={index} value={option}>
                    {option}
                  </option>
                ))}
              </select>

              <p className="formerros">{formErrors.account}</p>
              <input
                type="number"
                name="amount"
                id="amount"
                placeholder="Enter Amount"
                onChange={changeHandler}
                value={user.amount}
                className="amountInput"
              />

              <p className="formerros">{formErrors.amount}</p>
              <button className="withdrawBtn" onClick={loginHandler}>
                Withdraw
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
            {popupContent.type === "success" ? (
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
export default Withdrawal;
