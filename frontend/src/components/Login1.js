import React, { useEffect, useState } from 'react'
import "./Login.css";
import Alert from 'react-bootstrap/Alert';
import loginpic from "../images/login.jpg";
export const Login1 = () => {
    const [value1,setValue1] = useState("");
    const [value2,setValue2] = useState("");
    const [userpwd,Setuserpwd] = useState("");
    const [username,Setusername] = useState("");
    const [useremail,Setuseremail] = useState("");
    const [message,Setmessage] = useState("");
    const [isLoggedIn,SetisLoggedIn] = useState(false);
    const [showAlert,setShowAlert] = useState(false);
     const handleShowAlert = () =>{
        setShowAlert(true);
     }

     const handleCloseAlert = () => {
        setShowAlert(false);
     }



    useEffect(() => {
        let options = {
            method:"POST",
            headers: {
              'Content-Type': 'application/json',
          },
            body:JSON.stringify(
             
              {
                  "user_email":useremail,
                  "user_pwd":userpwd
              
              }
            
            )
      
          }
          fetch("http://localhost:8080/banking/validate", options)
          .then((resp)=>  resp.json())
          .then((resp) => {

            console.log(resp)
                Setmessage(resp.message);

          });
          
          
    },[userpwd,useremail]);

     const handleInputChange = (e) => {

        e.preventDefault();
        setFor
        let value  = e.target.value;
        let inputName = e.target.name;
        if(inputName === "email"){
            setValue1(value);
            Setuseremail(value);
            
        }else{
        setValue2(value)
        Setuserpwd(value)
        }
      };

     
   

      const handleLogin = () => {
        

        if(message === "Login Successful"){
          setTimeout(() => {
           SetisLoggedIn(true)
          }, 2000);
        }
        else if(message  === "Password is incorrect"){

            alert("Pasword is incorrect!")
        }
        else if(message.includes("User not found for this email")){
            alert("User not found for this email please create a new account")
        }

        else if (message === "Invalid email "){
            alert("Please enter a valid email!")
        }    
    
        
    
      };
    

     
        
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
                    <div>
                      <input
                        type="email"
                        id="email"
                        name="email"
                        value={value1}
                      
                        onChange={handleInputChange}
                        required
                      />
                    </div>
    
                    <div>
                      <input
                        type="password"
                        id="password"
                        name="password"
                        value={value2}
                        onChange={handleInputChange}
                      />
                    </div>
    
                    <button type="button" onClick={handleLogin}>
                      Login
                    </button>
    
                    <div className="leftmargin">
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
      
}

export default Login1
