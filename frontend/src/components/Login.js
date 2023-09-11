import React, { Component } from "react";
import "./Login.css";
import loginpic from "../images/login.jpg";



export class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      serverResponse:"",
      email: "",
      password: "",
      isLoggedIn: false,
    };
  }

  async componentDidMount(){

    let options = {
      method:"POST",
      headers: {
        'Content-Type': 'application/json',
    },
      body:JSON.stringify(
       
        {
            "user_name":this.state.user_name,
            "user_email":this.state.user_email,
            "user_pwd":this.state.user_pwd
        
        }
      
      )

    }
    let p = await fetch("http://localhost:8080/banking/validate", options)
    
    let response  = await p.json();
    this.state.serverResponse = response.message;
    console.log(this.state.serverResponse)

    console.log(response)
  }

  handleInputChange = (e) => {
    const { name, value } = e.target;
    this.setState({ [name]: value });
  };

  handleLogin = () => {
    this.componentDidMount();

    console.log(this.state.serverResponse)
    if(this.state.serverResponse === "Login Successful"){
      setTimeout(() => {
        this.setState({ isLoggedIn: true });
      }, 2000);
    }


    

  };

  render() {
    const { email, password, isLoggedIn } = this.state;
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
                    value={email}
                    placeholder="Email"
                    onChange={this.handleInputChange}
                    required
                  />
                </div>

                <div>
                  <input
                    type="password"
                    id="password"
                    name="password"
                    placeholder="Password"
                    value={password}
                    onChange={this.handleInputChange}
                  />
                </div>

                <button type="button" onClick={this.handleLogin}>
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
}

export default Login;
