import React from "react";
import ServiceCard from "./ServiceCard";
import styled from "styled-components";
import Navbar from "./Navbar";

const CardRow = styled.div`
  display: flex;
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
      title: "Check Account Balance",
      description: "View your current account balance and transaction history.",
      iconClass: "fas fa-wallet",
      link:"/accountdetails"
    },
    {
      title: "Transaction History",
      description: "Track your recent transactions and manage your finances.",
      iconClass: "fas fa-history",
      link:"/transaction"
    },
    {
      title: "Apply for Debit Card",
      description: "Apply for a Debit card and enjoy exclusive benefits.",
      iconClass: "fas fa-credit-card",
      link:"/creditcard"
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
