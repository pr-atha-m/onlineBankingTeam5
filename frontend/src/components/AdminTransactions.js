import React,{useEffect, useState} from "react";
import styled from "styled-components";
import Navbar from "./Navbar";

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

const AdminTransactions = () => {
  const [details,setDetails]=  useState([])
  useEffect(() => {
 
   
    let options = {
      method:"GET",
      headers: {
        'Content-Type': 'application/json',

      }
    }
    fetch(`http://localhost:8080/transaction/transactionHistory/${localStorage.getItem("acc_no")}`, options)
  
    .then((resp)=> resp.json())
    .then((resp) => {
     
    setDetails(resp);
    console.log(resp)
   
      

    });
   
  
}, []);


console.log(details)



  return (
    <>
    
      <Navbar isLoggedIn={true}/>
      <Container>

        <h1 style={{ textAlign: "center" }}>Transaction History</h1>

        <Table>
          <thead>
            <TableRow>
              <TableHeader>Transaction Id</TableHeader>

              <TableHeader>Sender Account</TableHeader>
              <TableHeader>Receiver Account</TableHeader>
              <TableHeader>Amount</TableHeader>
              <TableHeader>Transaction Date</TableHeader>
              <TableHeader>Maturity Remakrs</TableHeader>
              <TableHeader>Transaction mode</TableHeader>

            </TableRow>
          </thead>
          <tbody>
            {details.map((account, index) => (
              <TableRow key={index}>

                <TableCell>{account.trans_id}</TableCell>
                <TableCell>{account.sender_account}</TableCell>
                <TableCell>{account.receiver_account}</TableCell>
                <TableCell>{account.amount}</TableCell>
                <TableCell>{account.trans_date}</TableCell>
                <TableCell>{account.maturity_remarks}</TableCell>
                <TableCell>{account.trans_mode}</TableCell>


              </TableRow>
            ))}
          </tbody>
        </Table>
      </Container>
    </>
  );
};

export default AdminTransactions;
