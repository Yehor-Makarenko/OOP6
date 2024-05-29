import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../styles/CreditCards.css';

const CreditCards = () => {
  const [cards, setCards] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
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

    fetchData();
  }, []);

  const handleCardClick = (number) => {
    navigate(`/card/${number}`);
  };

  return (
    <div className="cards-container">
      <h2 className="text-center">My Credit Cards</h2>
      <div className="cards-list">
        {cards.map((card, index) => (
          <div className="card" key={index} onClick={() => handleCardClick(card.number)}>
            <div className="card-body">
              <h5 className="card-title">Card Number: {card.number}</h5>
              <p className="card-text">Expiration Date: {card.expirationDate}</p>
              <p className="card-text">CVV: {card.cvv}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CreditCards;
