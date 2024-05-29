import React, { useState } from 'react';
import axios from 'axios';
import { useHistory, useNavigate } from 'react-router-dom';
import '../styles/LoginForm.css';

const LoginForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (url, isAdmin) => {
    try {
      const response = await axios.post(url, new URLSearchParams({
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
      alert('Login failed. Please try again.');
    }
  };

  const handleClientLogin = (e) => {
    e.preventDefault();
    handleLogin('http://localhost:8080/server-1.0-SNAPSHOT/client/login', false);
  };

  const handleAdminLogin = (e) => {
    e.preventDefault();
    handleLogin('http://localhost:8080/server-1.0-SNAPSHOT/admin/login', true);
  };

  return (
    <div className="login-container">
      <h2 className="text-center">Login</h2>
      <form>
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
        <button className="btn btn-primary" onClick={handleClientLogin}>Login as Client</button>
        <button className="btn btn-secondary ml-2" onClick={handleAdminLogin}>Login as Admin</button>
      </form>
    </div>
  );
};

export default LoginForm;
