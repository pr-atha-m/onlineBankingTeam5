import React from "react";
import ServiceCard from "./ServiceCard";
import styled from "styled-components";
import Navbar from "./Navbar";

const CardRow = styled.div`
  display: flex; 
  flex-wrap:wrap;
  justify-content: center;
  align-items: center;
  gap: 16px;
`;

const CenteredContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
`;



const Services = () => {
  const services = [
    {
      title: "Open an Account",
      description: "Open an Account with Us and Take the First step towards a Secure future",
      iconClass: "fas fa-credit-card",
      link:"/createaccount"
    },
    {
      title: "Check Account Balance",
      description: "View your current account balance and transaction history.",
      iconClass: "fas fa-wallet",
      link:"/accountdetails"
    },
    {
      title: "Transaction History",
      description: "Track your recent transactions and manage your finances.",
      iconClass: "fas fa-history",
      link:"/accountno"
    },
    {
      title: "Add Funds",
      description: "Add Funds Today and watch Your Savings Soar!",
      iconClass: "fas fa-dollar-sign",
      link:"/deposit"
    },
    {
      title: "Transfer Money",
      description: "Open an Account with Us and Take the First step towards a Secure future",
      iconClass: "fas fa-credit-card",
      link:"/transfer"
    },
    {
      title: "Withdraw Money",
      description: "Unlock Your Fortune with swift withdrawals -Your Money, Your Way!",
      iconClass: "fas fa-dollar-sign",
      link:"/withdraw"
    },
   
    // Add more services here
  ];
  return (
    <>
      <Navbar isLoggedIn={true} />
      <CenteredContainer>
        <CardRow>
        
          {services.map((service, index) => (
            <a href={service.link}>
            <ServiceCard
              key={index}
           title={service.title}
              description={service.description}
              iconClass={service.iconClass}
            />
            </a>
          ))}
          
        </CardRow>
      </CenteredContainer>
    </>
  );
};

export default Services;
