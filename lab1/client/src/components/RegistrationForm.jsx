import React, { useState } from 'react';
import axios from 'axios';
import { useHistory, useNavigate } from 'react-router-dom';
import '../styles/RegistrationForm.css';

const RegistrationForm = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleRegistration = async (url, isAdmin) => {
    try {
      const response = await axios.post(url, new URLSearchParams({
        username,
        email,
        password
      }), {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      });

      if (response.status === 200) {
        const token = response.data.token;
        localStorage.setItem('clientToken', token);
        localStorage.setItem('isAdmin', isAdmin);
        navigate('/');
        window.location.reload();
      }
    } catch (error) {
      alert('Registration failed. Please try again.');
    }
  };

  const handleClientRegistration = (e) => {
    e.preventDefault();
    handleRegistration('http://localhost:8080/server-1.0-SNAPSHOT/client/registration', false);
  };

  const handleAdminRegistration = (e) => {
    e.preventDefault();
    handleRegistration('http://localhost:8080/server-1.0-SNAPSHOT/admin/registration', true);
  };

  return (
    <div className="registration-container">
      <h2 className="text-center px-3">Registration</h2>
      <form>
        <div className="form-group">
          <label>Username</label>
          <input
            type="text"
            className="form-control"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Email</label>
          <input
            type="email"
            className="form-control"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Password</label>
          <input
            type="password"
            className="form-control"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button className="btn btn-primary" onClick={handleClientRegistration}>Register as Client</button>
        <button className="btn btn-secondary ml-2" onClick={handleAdminRegistration}>Register as Admin</button>
      </form>
    </div>
  );
};

export default RegistrationForm;
