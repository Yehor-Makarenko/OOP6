import React, { useEffect, useState } from 'react';
import axios from 'axios';
import qs from 'qs';
import '../styles/PaymentForm.css';

const PaymentForm = () => {
  const [cards, setCards] = useState([]);
  const [selectedCard, setSelectedCard] = useState('');
  const [amount, setAmount] = useState('');
  const [description, setDescription] = useState('');
  useEffect(() => {
    const fetchCards = async () => {
      const token = localStorage.getItem('clientToken');
      if (!token) {
        alert('No token found. Please log in.');
        return;
      }

      try {
        const response = await axios.get('http://localhost:8080/server-1.0-SNAPSHOT/auth/card', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        setCards(response.data);
      } catch (error) {
        alert('Failed to fetch cards. Please try again.');
      }
    };

    fetchCards();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('clientToken');
    if (!token) {
      alert('No token found. Please log in.');
      return;
    }

    const data = qs.stringify({ card_number: selectedCard, amount, description });

    try {
      const response = await axios.post(
        'http://localhost:8080/server-1.0-SNAPSHOT/auth/payment',
        data,
        {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.status === 200) {
        alert('Payment successful!');
        setAmount('');
        setDescription('');
        setSelectedCard('');
      }
    } catch (error) {
      alert('Not enough money or card is blocked');
    }
  };

  return (
    <div className="payment-form-container">
      <h2 className="text-center">Make a Payment</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="cardSelect">Select Card</label>
          <select
            id="cardSelect"
            className="form-control"
            value={selectedCard}
            onChange={(e) => setSelectedCard(e.target.value)}
            required
          >
            <option value="" disabled>Select a card</option>
            {cards.map((card, index) => (
              <option key={index} value={card.number}>
                {card.number}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="amount">Amount</label>
          <input
            type="number"
            className="form-control"
            id="amount"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="description">Description</label>
          <input
            type="text"
            className="form-control"
            id="description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Submit Payment</button>
      </form>
    </div>
  );
};

export default PaymentForm;
