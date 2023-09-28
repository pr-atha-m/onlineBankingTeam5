import React, { useEffect, useState } from "react";
import styled from "styled-components";
import Navbar from "./Navbar";
import Cookies from "universal-cookie";
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
  const [details, setDetails] = useState([]);
  const navigate = useNavigate();
  const cookie = new Cookies();
  useEffect(() => {
    if (!cookie.get("myCookie")) {
      navigate("/login");
    }
    let options = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${cookie.get("myCookie")}`,
      },
    };
    fetch(
      `http://localhost:8080/transaction/transactionHistory/${cookie.get(
        "acc_no"
      )}`,
      options
    )
      .then((resp) => resp.json())
      .then((resp) => {
        setDetails(resp);
      });
  }, []);

  const keys = Object.keys(details);

  console.log(details);
  if (details.length != 0) {
    return (
      <>
        <Navbar isLoggedIn={true} />
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
                <TableHeader>Status</TableHeader>
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
                  <TableCell>
                    {console.log(cookie.get("acc_no"))}
                    {console.log(account.sender_account)}
                    {cookie.get("acc_no") == account.sender_account ? (
                      <span style={{ color: "red" }}>Debited</span>
                    ) : (
                      <span style={{ color: "green" }}>Credited</span>
                    )}
                  </TableCell>
                </TableRow>
              ))}
            </tbody>
          </Table>
        </Container>
      </>
    );
  } else {
    return (
      <>
        <Navbar isLoggedIn={true} />
        <h1>No transaction record found</h1>
      </>
    );
  }
};

export default Transactions;
