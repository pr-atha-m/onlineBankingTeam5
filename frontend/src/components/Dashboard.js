import React from "react";
import "./Styles/Home.css";
import Navbar from "./Navbar";

const Dashboard = () => {
  return (
    <>
      <Navbar />

      <section>
        <div className="home-title">
          <h1>Welcome to FinShot</h1>
        </div>
      </section>
    </>
  );
};

export default Dashboard;
