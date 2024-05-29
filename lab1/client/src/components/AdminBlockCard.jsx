import React, { useState } from 'react';
import axios from 'axios';
import '../styles/AdminBlockCard.css';

const AdminBlockCard = () => {
  const [cardNumber, setCardNumber] = useState('');
  const [reason, setReason] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = new URLSearchParams({
      card_number: cardNumber,
      reason: reason,
    });

    try {
      const response = await axios.post('http://localhost:8080/server-1.0-SNAPSHOT/auth/account/block', data, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Authorization': `Bearer ${localStorage.getItem('clientToken')}`,
        },
      });

      if (response.status === 200) {
        alert('Card successfully blocked.');
      } else {
        alert('Failed to block card.');
      }
    } catch (error) {
      alert('Error blocking card. Please try again.');
    }
  };

  return (
    <div className="admin-block-card">
      <h2>Block Card</h2>
      <form onSubmit={handleSubmit}>
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
        <div className="form-group">
          <label>Reason</label>
          <textarea
            className="form-control"
            value={reason}
            onChange={(e) => setReason(e.target.value)}
            required
          />
        </div>
        <button className="btn btn-danger" type="submit">Block Card</button>
      </form>
    </div>
  );
};

export default AdminBlockCard;
