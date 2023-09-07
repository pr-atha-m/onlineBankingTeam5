import React, {Component} from 'react'
import './Login.css'

export class Login extends Component{
    constructor(props){
        super(props);
        this.state = {
            username : '',
            password : '',
            isLoggedIn: false,
        };
    }

    handleInputChange = (e) =>{
        const {name,value} = e.target;
        this.setState({ [name]: value});
        };

        handleLogin = () => {
            if(this.state.username ==="Pratham" && this.state.password==="wellsfargo"){
            setTimeout(() => {
                this.setState({isLoggedIn: true});
            }, 2000);
        }
        
        };

        render(){
            const {username , password , isLoggedIn} = this.state;
            if (isLoggedIn){
                return <div>You are logged in</div>
            }

            return (
                <div class ="container">
      <h1>
        Login Page
      </h1> 

      <div >

    
      <form>
        <div>
            <label htmFor="Username"> Username: </label>
            <input type = "text"
            id = "username"
            name = "username"
            value = {username}
            onChange = {this.handleInputChange}/>
        
        </div>

        <div>

        <label htmFor="password"> Password: </label>
            <input type = "password"
            id = "password"
            name = "password"
            value = {password}
            onChange = {this.handleInputChange}/>

        </div>

        <button type = "button" onClick={this.handleLogin}>
            Login
        </button>
      </form>
      </div>
    </div>
            )
        }
}

export default Login
