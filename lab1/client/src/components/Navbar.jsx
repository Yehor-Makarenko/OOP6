import React, { useState, useEffect } from 'react';
import { NavLink } from 'react-router-dom';
import '../styles/Navbar.css';

const Navbar = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('clientToken');
    const adminStatus = localStorage.getItem('isAdmin') === 'true';
    setIsAuthenticated(!!token);
    setIsAdmin(adminStatus);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('clientToken');
    localStorage.removeItem('isAdmin');
    setIsAuthenticated(false);
    setIsAdmin(false);
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <NavLink className="navbar-brand" to="/">Payment System</NavLink>
      <div className="navbar-collapse" id="navbarNav">
        <ul className="navbar-nav ml-auto">
          {isAuthenticated ? (
            <>
              {!isAdmin && (
              <>
              <li className="nav-item">
                <NavLink className="nav-link" to="/cards">Cards</NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/payment">Make Payment</NavLink>
              </li>
              </>
              )}
              {isAdmin && (
                <>
                <li className="nav-item">
                  <NavLink className="nav-link" to="/admin-block-card">Block Card</NavLink>
                </li>
                <li className="nav-item">
                  <NavLink className="nav-link" to="/unblock-card">Unblock Card</NavLink>
                </li>
                </>
              )}
              <li className="nav-item">
                <NavLink className="nav-link" to="/" onClick={handleLogout}>Logout</NavLink>
              </li>
            </>
          ) : (
            <>
              <li className="nav-item">
                <NavLink className="nav-link" to="/register">Register</NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/login">Login</NavLink>
              </li>
            </>
          )}
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
