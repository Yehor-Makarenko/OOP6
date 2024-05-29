import React from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import RegistrationForm from './components/RegistrationForm';
import LoginForm from './components/LoginForm';
import HomePage from './components/HomePage';
import CreditCards from './components/CreditCards';
import './App.css';
import CardDetail from './components/CardDetail';
import PaymentForm from './components/PaymentForm';
import Navbar from './components/Navbar';
import UnblockCard from './components/UnblockCard';
import AdminBlockCard from './components/AdminBlockCard';

function App() {
  return (
    <div className="App">      
      <BrowserRouter>
        <Navbar/>
        <Routes>    
          <Route path="/register" element={<RegistrationForm/>} />
          <Route path="/login" element={<LoginForm/>} />
          <Route path="/cards" element={<CreditCards/>} />
          <Route path='/card/:number' element={<CardDetail/>} />
          <Route path="/payment" element={<PaymentForm/>} />
          <Route path="/unblock-card" element={<UnblockCard/>} />
          <Route path="/admin-block-card" element={<AdminBlockCard/>} />
          <Route path="*" element={<HomePage/>} />    
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
