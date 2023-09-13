import React, { useState } from 'react';
const AccountOpen = () => {
    const [formData, setFormData] = useState({
        email: '',
        phoneNumber: '',
        fatherName: '',
        adhaarNumber: '',
        dateOfBirth: '',
        residentialAddress: '',
        permanentAddress: '',
        occupationType: '',
        grossAnnualIncome: '',
        sourceOfIncome: '',
        debitStatus: '',
        netBanking: false,
      });
    
      const [errors, setErrors] = useState({});
    
      const validateEmail = (email) => {
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return emailRegex.test(email);
      };
    
      const validatePhoneNumber = (phoneNumber) => {
        const phoneRegex = /^\d{10}$/;
        return phoneRegex.test(phoneNumber);
      };
    
      const validateAdhaarNumber = (adhaarNumber) => {
        const adhaarRegex = /^\d{12}$/;
        return adhaarRegex.test(adhaarNumber);
      };
    
      const validateGrossAnnualIncome = (income) => {
        const incomeRegex = /^[0-9]+(\.[0-9]{1,2})?$/;
        return incomeRegex.test(income);
      };
    
      const handleInputChange = (event) => {
        const { name, value, type, checked } = event.target;
        const newValue = type === 'checkbox' ? checked : value;
        setFormData({ ...formData, [name]: newValue });
      };
    
      const handleSubmit = (event) => {
        event.preventDefault();
        const newErrors = {};
    
        // Validate email
        if (!validateEmail(formData.email)) {
          newErrors.email = 'Invalid email address';
        }
    
        // Validate phone number
        if (!validatePhoneNumber(formData.phoneNumber)) {
          newErrors.phoneNumber = 'Invalid phone number';
        }
    
        // Validate Aadhaar number
        if (!validateAdhaarNumber(formData.adhaarNumber)) {
          newErrors.adhaarNumber = 'Invalid Aadhaar number';
        }
    
        // Validate gross annual income
        if (!validateGrossAnnualIncome(formData.grossAnnualIncome)) {
          newErrors.grossAnnualIncome = 'Invalid income amount';
        }
    
        // Set validation errors
        setErrors(newErrors);
    
        // If there are no errors, you can proceed with form submission
        if (Object.keys(newErrors).length === 0) {
          // Example: send formData to your API or perform other actions
        }
      };

  return (
    <div>
    <form onSubmit={handleSubmit}>
      <label>
        User Email:
        <input
          type="email"
          name="email"
          value={formData.email}
          onChange={handleInputChange}
          required
        />
        {errors.email && <span className="error">{errors.email}</span>}
      </label>

      <label>
        Phone Number:
        <input
          type="tel"
          name="phoneNumber"
          value={formData.phoneNumber}
          onChange={handleInputChange}
          required
        />
        {errors.phoneNumber && (
          <span className="error">{errors.phoneNumber}</span>
        )}
      </label>

      {/* Add the rest of the input fields and validations similarly */}
      
      <button type="submit">Submit</button>
    </form>
  </div>
  )
}

export default AccountOpen
