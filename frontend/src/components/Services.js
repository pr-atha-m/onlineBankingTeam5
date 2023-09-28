import React, { useEffect, useState } from "react";
import ServiceCard from "./ServiceCard";
import styled from "styled-components";
import Navbar from "./Navbar";
import Footer from "./Footer";
import Cookies from "universal-cookie";
import { useNavigate, NavLink } from "react-router-dom";
const CardRow = styled.div`
  display: flex;
  flex-wrap: wrap;
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
  const navigate = useNavigate();
  const cookie = new Cookies();
  const services = [
    {
      title: "Open an Account",
      description:
        "Open an Account with Us and Take the First step towards a Secure future",
      iconClass: "fas fa-credit-card",
      link: "/createaccount",
    },
    {
      title: "Check Account Balance",
      description: "View your current account balance and transaction history.",
      iconClass: "fas fa-wallet",
      link: "/accountdetails",
    },
    {
      title: "Transaction History",
      description: "Track your recent transactions and manage your finances.",
      iconClass: "fas fa-history",
      link: "/accountno",
    },
    {
      title: "Add Funds",
      description: "Add Funds Today and watch Your Savings Soar!",
      iconClass: "fas fa-dollar-sign",
      link: "/deposit",
    },
    {
      title: "Transfer Money",
      description:
        "Open an Account with Us and Take the First step towards a Secure future",
      iconClass: "fas fa-credit-card",
      link: "/transfer",
    },

    {
      title: "Withdraw Money",
      description:
        "Unlock Your Fortune with swift withdrawals -Your Money, Your Way!",
      iconClass: "fas fa-credit-card",
      link: "/withdraw",
    },

    // Add more services here
  ];

  const [first, setFirst] = useState("");
  const [last, setLast] = useState("");

  useEffect(() => {
    if (!cookie.get("myCookie")) {
      navigate("/login");
    }

    // if (!cookie.get("first")) {
    //   window.location.reload();
    // }
    let options = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${cookie.get("myCookie")}`,
      },
    };
    fetch(
      `http://localhost:8080/admin/userdetails/${cookie.get("emailId")}`,
      options
    )
      .then((resp) => resp.json())
      .then((resp) => {
        console.log(resp);

        setFirst(resp.first_name);
        setLast(resp.last_name);
        cookie.set("first", resp.first_name, { path: "/" });
        cookie.set("last", resp.last_name, { path: "/" });
        cookie.set("status", resp.status, { path: "/" });
      });
  }, []);

  return (
    <>
      <Navbar isLoggedIn={true} first={first} last={last} />
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
      <Footer />
    </>
  );
};

export default Services;
