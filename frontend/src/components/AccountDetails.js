import React from "react";
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
  const accountData = [
    {
      accountNumber: "1234567890",
      accountType: "Savings",
      balance: "999999",
      accountOpenDate: "2023-01-15",
    },
    {
      accountNumber: "1828187199",
      accountType: "Salary",
      balance: "138811",
      accountOpenDate: "2022-11-20",
    },
    // Add more accounts here
  ];

  return (
    <>
      <Navbar />
      <Container>
        <h1 style={{ textAlign: "center" }}>Account Details</h1>
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
            {accountData.map((account, index) => (
              <TableRow key={index}>
                <TableCell>{account.accountNumber}</TableCell>
                <TableCell>{account.accountType}</TableCell>
                <TableCell>{account.balance}</TableCell>
                <TableCell>{account.accountOpenDate}</TableCell>
              </TableRow>
            ))}
          </tbody>
        </Table>
      </Container>
    </>
  );
};

export default AccountDetails;
