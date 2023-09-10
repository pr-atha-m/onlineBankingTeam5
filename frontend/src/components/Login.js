import React, { Component } from "react";
import "./Login.css";
import loginpic from "../images/login.jpg";



export class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
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
            "user_name":"pratham",
            "user_email":"pb@gmail.com",
            "user_pwd":"4523"
        
        }
      
      )

    }
    let p = await fetch("http://localhost:8080/banking/validate", options)
    
    let response  = await p.json();

    console.log(response)
  }

  handleInputChange = (e) => {
    const { name, value } = e.target;
    this.setState({ [name]: value });
  };

  handleLogin = () => {
    if (
      this.state.email === "Pratham" &&
      this.state.password === "wellsfargo"
    ) {
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
