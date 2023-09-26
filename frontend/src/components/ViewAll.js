import React,{useEffect, useState} from "react";
import styled from "styled-components";
import Navbar from "./Navbar";
import { Link } from "react-router-dom";
import Cookies from "js-cookie";
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

const Transactions = () => {
  const navigate = useNavigate();
  const [details,setDetails]=  useState([])
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
    fetch("http://localhost:8080/banking/userdetails", options)
  
    .then((resp)=> resp.json())
    .then((resp) => {
     
    setDetails(resp);

   
      

    });
   
  
}, []);



const keys = Object.keys(details);
console.log(details)
console.log(keys[0]);
console.log(details[keys[0]]);

console.log(details)
if(details.length!=0){

  return ( 
    <>
    <nav style={{ backgroundColor: "#f5f5f5", padding: "7px" }}>
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <div>
            <Link
              to="/"
              style={{
                textDecoration: "none",
                color: "#333",
                fontSize: "24px",
                marginLeft: "20px",
              }}
            >
             Admin Dashboard
            </Link>
          </div>
          <div>
            <ul
              style={{
                listStyleType: "none",
                padding: 0,
                display: "flex",
                gap: "20px",
              }}
            >
              
    
               
                   
                  
                  <li>
                    <Link
                      to="/admin"
                      style={{ textDecoration: "none", color: "#333" }}
                    >
                        Search User
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/"
                      style={{
                        textDecoration: "none",
                        color: "#333",
                        marginRight: "20px",
                      }}
                    >
                      Logout
                    </Link>
                  </li>
                
            </ul>
          </div>
        </div>
      </nav>

  <Container>

    <h1 style={{ textAlign: "center" }}>Transaction History</h1>

    <Table>
      <thead>
        <TableRow>
          <TableHeader>Email</TableHeader>

          <TableHeader>Account Number</TableHeader>
          <TableHeader>Balance</TableHeader>
          <TableHeader>Account Type</TableHeader>
          <TableHeader>Phone</TableHeader>
          
          <TableHeader>Residential Address </TableHeader>

          <TableHeader>Occupation</TableHeader>
          <TableHeader>Account Status</TableHeader>

        </TableRow>
      </thead>
      <tbody>
        {details.map((account, index) => (
          <TableRow key={index}>

            <TableCell>{account.emailId}</TableCell>
            <TableCell>{account.acc_no}</TableCell>
            <TableCell>{account.balance}</TableCell>
            <TableCell>{account.acc_type}</TableCell>
            <TableCell>{account.phone_no}</TableCell>
            
            <TableCell>{account.res_addr}</TableCell>
            <TableCell>{account.occ_type}</TableCell>
            <TableCell>{account.status===true? (
              <span style={{color : 'green'}}>Active</span>

            ): (
              <span style={{color : 'red'}}>Inactive</span>
            )}</TableCell>


          </TableRow>
        ))}
      </tbody>
    </Table>
  </Container>
  </>)
}
else{
  return (
    <>
    <Navbar isLoggedIn={true}/><h1>No transaction record found</h1>
    </>)
}
     
  
  

};

export default Transactions;
