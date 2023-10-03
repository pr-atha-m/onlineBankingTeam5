import React from "react";
import styled from "styled-components";

// Styled components for the card container
const CardContainer = styled.div`
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 16px;
  margin: 16px;
  max-width: 300px;
  text-align: center;
`;

const ServiceTitle = styled.h2`
  font-size: 24px;
  color: #333;
  margin-bottom: 8px;
`;

const ServiceDescription = styled.p`
  font-size: 16px;
  color: #666;
`;

const Icon = styled.i`
  font-size: 36px;
  color: #007bff;
  margin-bottom: 16px;
`;

const ServiceCard = ({ title, description, iconClass }) => {
  return (
    <CardContainer>
      <Icon className={iconClass}></Icon>
      <ServiceTitle>{title}</ServiceTitle>
      <ServiceDescription>{description}</ServiceDescription>
    </CardContainer>
  );
};
export default ServiceCard;
