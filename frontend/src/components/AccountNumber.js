import React, { useState, useEffect } from "react";
import "./Styles/Withdraw.css";

import { useNavigate, NavLink } from "react-router-dom";

const AccountNumber = () => {
    const navigate = useNavigate();
    
      const [details,setDetails]=  useState([]);
      const loginHandler =  (e) => {
        e.preventDefault();
        localStorage.setItem("acc_no",selectedOption)
        navigate("/transactions", { replace: true });
      
    
       
    
        // if (!formErrors) {
    
        // }
      };
    
    useEffect(() => {
     
       
        let options = {
          method:"GET",
          headers: {
            'Content-Type': 'application/json',
    
          }
          }
        fetch(`http://localhost:8080/banking/user/by-email?emailId=${localStorage.getItem("emailId")}`, options)
        .then((resp)=> resp.json())
        .then((resp) => {
         
        setDetails(resp);
        console.log(resp)
       
          
    
        });
       
      
    }, []);
    
    const [selectedOption, setSelectedOption] = useState("");
    const options = [];
    
    
    {details.map((account, index) => (
        options.push(account.acc_no)
    ))}

    
  const handleOptionChange = (e) => {
    setSelectedOption(e.target.value);
  };
    
  return (
    <div className='body1'>
        <div className="container3">
        <form>

        <select
                id="dropdown"
                className="dropdown2"
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

              <button className="transactBtn" onClick={loginHandler}>Continue</button>


            </form>

        </div>

      
    </div>
  )
}

export default AccountNumber
