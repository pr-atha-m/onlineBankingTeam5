import React, { useState } from 'react';
import './Styles/Admin.css'; // Import your CSS file for styling

function Admin() {
    const [email, setEmail] = useState('');
    const [userData, setUserData] = useState(null);

    const fetchUserData = () => {
        // You can replace this URL with your backend API endpoint
        fetch(`/api/users?email=${email}`)
            .then(response => response.json())
            .then(data => {
                setUserData(data);
            })
            .catch(error => {
                console.error('Error fetching user data:', error);
                setUserData(null);
            });
    };

    return (
        <div className="admin-card">
            <h1>Admin Dashboard</h1>
            <div className="search-container">
                <input
                    type="text"
                    placeholder="Enter Email ID"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <button onClick={fetchUserData}>Search</button>
            </div>
            {userData && (
                <div className="user-data">
                    <h2>User Information</h2>
                    <p><strong>Email:</strong> {userData.email}</p>
                    {/* Add more user data fields as needed */}
                </div>
            )}
        </div>
    );
}

export default Admin;