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
      title: "10 Tips for  Managing Your Finances",
      description: "Read our expert advice on managing your money effectively",
      iconClass: "fas fa-coins",
      link:"/accountdetails"
    },
    {
      title: "Introducing Finshot Mobile App",
      description: "Discover how we keep your financial information safe",
      iconClass: "fas fa-mobile",
      link:"/transaction"
    },
    {
      title: "Investing with Finshot Made Easy",
      description: "Explore investment oppurtunities with our expert guidance",
      iconClass: "fas fa-arrow-trend-up",
      link:"/creditcard"
    },
    // Add more services here
  ];
  return (
    <>
      <Navbar isLoggedIn={false} />
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
