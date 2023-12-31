import React, { useEffect, useState } from "react";
import styled from "styled-components";
import Cookies from "universal-cookie";
import Navbar from "./Navbar";
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
  text-align: left;
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

const AccountDetails = () => {
  const cookie = new Cookies();
  const navigate = useNavigate();
  const [details, setDetails] = useState([]);
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
    try {
      fetch(
        `http://localhost:8080/banking/user/by-email?emailId=${cookie.get(
          "emailId"
        )}`,
        options
      )
        .then((resp) => resp.json())
        .then((resp) => {
          setDetails(resp);
          console.log(resp);
        })
        .catch((error) => {
          console.error(error);
        });
    } catch (error) {
      console.error(error);
    }
  }, []);

  console.log(details);

  return (
    <>
      <Navbar isLoggedIn={true} />
      <Container>
        <h1 style={{ textAlign: "center" }}>Account Statement</h1>
        <Table>
          <thead>
            <TableRow>
              <TableHeader>Account Number</TableHeader>
              <TableHeader>Account Type</TableHeader>
              <TableHeader>Balance</TableHeader>
              <TableHeader>Account Open Date</TableHeader>
            </TableRow>
          </thead>
          <tbody>
            {details.map((account, index) => (
              <TableRow key={index}>
                <TableCell>{account.acc_no}</TableCell>
                <TableCell>{account.acc_type}</TableCell>
                <TableCell>{account.balance}</TableCell>
                <TableCell>{account.acc_open_date}</TableCell>
              </TableRow>
            ))}
          </tbody>
        </Table>
      </Container>
    </>
  );
};

export default AccountDetails;
