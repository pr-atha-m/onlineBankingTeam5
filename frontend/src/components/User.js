import React, { useEffect } from 'react'
import Cookies from 'js-cookie';




export const User = () => {
    useEffect(() => {

     
        let options = {
            method:"GET",
            headers: {
              'Content-Type': 'application/json',
              'Authorization' :  `Bearer ${Cookies.get("myCookie")}`
      
            }
          }
          fetch(`http://localhost:8080/admin/useraccounts?emailId=${Cookies.get("emailId")}`, options)
          .then((resp)=> resp.json())
          .then((resp) => {
              console.log(resp)
            Cookies.set("first",resp.first_name);
            Cookies.set("last",resp.last_name);
            Cookies.set("status",resp.status);
    
         
            
      
          });
      
    }, [])
  return (
    <div>
      
    </div>
  )
}

export default User
