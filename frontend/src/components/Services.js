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
  min-height: 100vh;
`;

const Services = () => {
  const services = [
    {
      title: "Check Account Balance",
      description: "View your current account balance and transaction history.",
      iconClass: "fas fa-wallet",
    },
    {
      title: "Transaction History",
      description: "Track your recent transactions and manage your finances.",
      iconClass: "fas fa-history",
    },
    {
      title: "Apply for Credit Card",
      description: "Apply for a credit card and enjoy exclusive benefits.",
      iconClass: "fas fa-credit-card",
    },
    // Add more services here
  ];
  return (
    <>
      <Navbar />
      <CenteredContainer>
        <CardRow>
          {services.map((service, index) => (
            <ServiceCard
              key={index}
              title={service.title}
              description={service.description}
              iconClass={service.iconClass}
            />
          ))}
        </CardRow>
      </CenteredContainer>
    </>
  );
};

export default Services;
