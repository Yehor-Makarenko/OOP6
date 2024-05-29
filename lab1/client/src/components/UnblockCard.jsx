import React, { useState } from 'react';
import axios from 'axios';
import '../styles/UnblockCard.css';

const UnblockCard = () => {
  const [cardNumber, setCardNumber] = useState('');

  const handleUnblockCard = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('clientToken');
    if (!token) {
      alert('No token found. Please log in.');
      return;
    }

    const data = new URLSearchParams({
      card_number: cardNumber,
    });

    try {
      const response = await axios.post(
        'http://localhost:8080/server-1.0-SNAPSHOT/auth-admin/account/unblock',
        data,
        {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.status === 200) {
        alert('Card successfully unblocked.');
      }
    } catch (error) {
      alert('Failed to unblock card. Please try again.');
    }
  };

  return (
    <div className="unblock-card-container">
      <h2 className="text-center">Unblock Card</h2>
      <form onSubmit={handleUnblockCard}>
        <div className="form-group">
          <label>Card Number</label>
          <input
            type="text"
            className="form-control"
            value={cardNumber}
            onChange={(e) => setCardNumber(e.target.value)}
            required
          />
        </div>
        <button className="btn btn-primary" type="submit">Unblock Card</button>
      </form>
    </div>
  );
};

export default UnblockCard;
