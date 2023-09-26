import React from 'react';
import './Styles/Footer.css'; // Import your CSS file for styling
import footer from '../images/footer.png'


function Footer() {
  return (
    <footer className="footer">
      <div className="footer-content">
        <div className="footer-logo">
      
          <img src={footer} alt="Bank Logo" />
        
        </div>
        <div className="footer-links">
          <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">About Us</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="#">Contact</a></li>
          </ul>
        </div>
        <div className="footer-social">
          <ul>
            <li><a href="#"><i className="fab fa-facebook"></i></a></li>
            <li><a href="#"><i className="fab fa-twitter"></i></a></li>
            <li><a href="#"><i className="fab fa-linkedin"></i></a></li>
          </ul>
        </div>
      </div>
    </footer>
  );
}

export default Footer;

