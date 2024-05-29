import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Modal, Button, Form } from 'react-bootstrap';
import '../styles/CardDetail.css';

const CardDetail = () => {
  const { number } = useParams();
  const [cardDetails, setCardDetails] = useState(null);
  const [payments, setPayments] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [blockReason, setBlockReason] = useState('');

  useEffect(() => {
    const fetchCardDetails = async () => {
      const token = localStorage.getItem('clientToken');
      if (!token) {
        alert('No token found. Please log in.');
        return;
      }

      try {
        const response = await axios.get(
          `http://localhost:8080/server-1.0-SNAPSHOT/auth/card/number?number=${number}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        setCardDetails(response.data);
      } catch (error) {
        alert('Failed to fetch card details. Please try again.');
      }
    };

    const fetchPayments = async () => {
      const token = localStorage.getItem('clientToken');
      if (!token) {
        alert('No token found. Please log in.');
        return;
      }

      try {
        const response = await axios.get(
          `http://localhost:8080/server-1.0-SNAPSHOT/payments`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
            params: {
              number,
            },
          }
        );

        setPayments(response.data.reverse());
      } catch (error) {
        alert('Failed to fetch payments. Please try again.');
      }
    };

    fetchCardDetails();
    fetchPayments();
  }, [number]);

  const handleBlockCard = async () => {
    const token = localStorage.getItem('clientToken');
    if (!token) {
      alert('No token found. Please log in.');
      return;
    }

    const data = new URLSearchParams({
      card_number: number,
      reason: blockReason,
    });

    try {
      const response = await axios.post(
        'http://localhost:8080/server-1.0-SNAPSHOT/auth/account/block',
        data,
        {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.status === 200) {
        setCardDetails({ ...cardDetails, blocked: true });
        setShowModal(false);
        setBlockReason('');
      }
    } catch (error) {
      alert('Failed to block card. Please try again.');
    }
  };

  if (!cardDetails) {
    return <div>Loading...</div>;
  }

  return (
    <div className="card-detail-container">
      <div className="card-detail">
        <h2 className="text-center">Card Details</h2>
        <p><strong>Card Number:</strong> {cardDetails.cardNumber}</p>
        <p><strong>Expiration Date:</strong> {cardDetails.expirationDate}</p>
        <p><strong>CVV:</strong> {cardDetails.cvv}</p>
        <p><strong>Balance:</strong> ${cardDetails.balance}</p>
        <p><strong>Is Blocked:</strong> {cardDetails.blocked ? 'Yes' : 'No'}</p>
        {!cardDetails.blocked && (
          <Button variant="danger" onClick={() => setShowModal(true)}>
            Block Card
          </Button>
        )}
      </div>

      <div className="payments-detail">
        <h2 className="text-center">Payments</h2>
        {payments.length === 0 ? (
          <p>No payments found.</p>
        ) : (
          <ul className="list-group">
            {payments.map((payment, index) => (
              <li className="list-group-item" key={index}>
                <p><strong>Amount:</strong> ${payment.amount}</p>
                <p><strong>Date:</strong> {payment.paymentDate}</p>
                <p><strong>Description:</strong> {payment.description}</p>
              </li>
            ))}
          </ul>
        )}
      </div>

      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Block Card</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="blockReason">
              <Form.Label>Reason for Blocking</Form.Label>
              <Form.Control
                type="text"
                value={blockReason}
                onChange={(e) => setBlockReason(e.target.value)}
                required
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>
            Cancel
          </Button>
          <Button variant="danger" onClick={handleBlockCard}>
            Block Card
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default CardDetail;
