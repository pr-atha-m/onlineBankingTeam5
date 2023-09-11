import React, {useState} from 'react'
import "./Styles/Forgot.css"
export const Forgot = () => {

    const [userId, setUserId] = useState('');
    const [otp, setOtp] = useState('');

    const handleuserIdChange = (e) =>{
        setUserId(e.target.value);
    }


    const handleOtpChange = (e)=>{
setOtp(e.target.value);
    };
    
    const handleSubmit =(e) => {
        e.preventDefault();
    }
    
    return (
        <div className="body1">

        
    <div className="container3">
        

        <form onSubmit={handleSubmit}>
        <h1>Reset Your Password!</h1>
            <div className='forgotInput'>
              
                <input type="text" name = "userId" id = "userId" value= {userId} onChange={handleuserIdChange} placeholder='Enter User ID'/>

            </div>

            <div className='forgotInput'>
                
                <input type="text" name="otp" id = "otp" value={otp} onChange={handleOtpChange} placeholder='Enter OTP'/>

            </div>
            
            
            <button className="forgotButton" type="submit">Submit</button>
           

           <a href="/login">Back</a>
                

        </form>


      
    </div>
    </div>
  )
}

export default Forgot
