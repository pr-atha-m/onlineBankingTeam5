import React,{useEffect, useState} from "react";
import styled from "styled-components";
import Navbar from "./Navbar";
import Cookies from "js-cookie";
import './Styles/Userdetails.css'; // Import your CSS file for styling
import { useNavigate, NavLink } from "react-router-dom";
const Container = styled.div`
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
  border: 1px solid #ddd;
`;

const TableHeader = styled.th`
  background-color: #0fe3e0;
  color: #fff;
  padding: 12px;
  text-align: center;
  border: 1px solid #ddd;
`;

const TableRow = styled.tr`
  &:nth-child(even) {
    background-color: #f2f2f2;
  }
`;

const TableCell = styled.td`
  padding: 12px;
  border: 1px solid #ddd;
`;

const UserDetails = ({}) => {
  const [userAccounts, setUserAccounts] = useState([]);
  const [accountStatus, setAccountStatus] = useState([]);
  const navigate = useNavigate();
    // const toggle = () => {
    //     setCondition(!condition);
    // }


   


  useEffect(() => {
 
    if(!Cookies.get('myCookie')){
      navigate('/login')
  }
   
    let options = {
      method:"GET",
      headers: {
        'Content-Type': 'application/json',
        'Authorization' :  `Bearer ${Cookies.get("myCookie")}`

      }
    }
    fetch(`http://localhost:8080/admin/useraccounts?emailId=${Cookies.get("email")}`, options)
    .then((resp)=> resp.json())
    .then((resp) => {
        console.log(resp)
   
    setUserAccounts(resp);
    setAccountStatus(resp.map(account => account.status))
   
   
      

    });
   
  
}, []);

const handleAnchorClick = (e) => {
  console.log(e.target.textContent)
  localStorage.setItem("acc_no",e.target.textContent)
}


const toggleStatus = (index) => {
  // Update the local state
  const newStatus = [...accountStatus];
  newStatus[index] = !newStatus[index];
  setAccountStatus(newStatus);

  // Make a POST request when status is set to false

    // Replace with your API endpoint and request configuration
    fetch(`http://localhost:8080/admin/setStatus?acc_num=${userAccounts[index].acc_no}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization' :  `Bearer ${Cookies.get("myCookie")}`
      },
    })
      .then(response => {
        // Handle the response as needed
        if (response.status === 200) {
          // Successful POST
          console.log("Status Changed")
        } else {
          // Handle errors
          console.log("error changing status")
        }
      })
      .catch(error => {
        // Handle network errors
      });
  
};



  return (
    <>
    
      <Navbar isLoggedIn={true}/>

      <div className="user-details">
      <div className="user-detail">
        <i className="fas fa-envelope"></i>
        <span>Email :&nbsp; </span>
        <span>{localStorage.getItem("email")}</span>
      </div>
      <div className="user-detail">
        <i className="fas fa-user"></i>
        <span>Name :&nbsp; </span>
        <span>{localStorage.getItem("first")}</span>
      </div>
      <div className="user-detail">
        <i className="fas fa-phone"></i>
        <span>Phone :&nbsp; </span>
        <span>{userAccounts[0]?userAccounts[0].phone_no:""}</span>
      </div>
      <div className="user-detail">
        <i className="fas fa-id-card"></i>
        <span>Aadhar :&nbsp; </span>
        <span>{userAccounts[0]?userAccounts[0].aadhar_no:""}</span>
      </div>
    </div>
 
      
      <Container>


        <h1 style={{ textAlign: "center" }}>Account Details</h1>

        <Table>
          <thead>
            <TableRow>

              <TableHeader>Account Number</TableHeader>
              <TableHeader>Account Type</TableHeader>
              <TableHeader>Account Balance</TableHeader>
              <TableHeader>Account Open Date</TableHeader>
              <TableHeader>Account Status</TableHeader>
              
             

            </TableRow>
          </thead>
          <tbody>
          {userAccounts.map((account, index) => (
           

           
              <TableRow key={account.acc_no}>

                <TableCell><a href="/admin/transactions" className="accNumber" onClick={handleAnchorClick}>{account.acc_no}</a></TableCell>
                <TableCell>{account.acc_type}</TableCell>
                <TableCell>{account.balance}</TableCell>
                <TableCell>{account.acc_open_date}</TableCell>
               <TableCell>
                <label>
                  <input
                  type = "checkbox"
                   checked = {accountStatus[index]} 
                   onChange = {() => toggleStatus(index) }

                   />

                   {accountStatus[index] ? 'Active' : 'Inactive'}

                  </label>

               </TableCell>
             


              </TableRow>
             
            ))}

           
          </tbody>
        </Table>
      </Container>
    </>
  );
};

export default UserDetails;
