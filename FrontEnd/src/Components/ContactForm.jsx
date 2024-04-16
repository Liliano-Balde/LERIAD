import React, { useState } from 'react';
import axios from 'axios';
import Modal from './Customer/Modal';
import { useNavigate } from "react-router-dom";

// Contact form conponent
function ContactForm() {
  const [email, setEmail] = useState('');
  const [issue, setIssue] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const navigate = useNavigate();

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Send form data to server
      await axios.post("http://localhost:8082/report/submit", { email, issue });
      // Set modal message and display modal
      setModalMessage('Thank you for your feedback!');
      setShowModal(true);
      return;
      
    } catch (error) {
      console.error('Error reporting issue:', error);
    
    }
  };
  // Handle to close modal
  const handleModalClose = () => {
    setShowModal(false);
  };

  // Navigate to contact page after successful submission
  const handleNavigate = () => {
    if (modalMessage.includes("Issue")) {
      navigate("/contactus");
      setShowModal(false);
      setEmail("");
      setIssue("");
    }};

  return (
    <div className="container mt-4">
      {/* Contact form title */}
      <h2 aria-label="contact form" className="border border-dark p-2 mb-2 border-4 border-dark rounded" style={{ color: "white", fontFamily: "Verdana, sans-serif", width: "1100px", backgroundColor: "#365074" }}>Contact us! <br /> Help us improve our site or collaborate with us. <br />Please let us know about any accessibility issues you encounter.  </h2>
      <form onSubmit={handleSubmit}>
        {/* Email & description input */}
        <div className="mb-3">
            <br />
            <br />
          <label aria-label="email field" className="border border-dark p-2 mb-2 border-4 border-dark rounded" style={{fontSize:"25px", color: "white", fontFamily: "Verdana, sans-serif", width: "100px", backgroundColor: "#365074" }}>Email:</label>
          <input 
            type="email"
            className="form-control"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            style={{ fontSize: "25px" }}
          />
        </div>
        <div className="mb-3">
          <label aria-label="description field" className="border border-dark p-2 mb-2 border-4 border-dark rounded" style={{fontSize:"25px", color: "white", fontFamily: "Verdana, sans-serif", width: "270px", backgroundColor: "#365074" }} htmlFor="issue" >Description:</label>
          <textarea
            className="form-control"
            id="issue"
            value={issue}
            onChange={(e) => setIssue(e.target.value)}
            rows={6} 
            required
            style={{ fontSize: "25px" }}
          />
        </div>
        <div>
        <button aria-label="submit button" type="submit" className="btn btn-light btn-lg" >Submit</button>
        </div>
        
      </form>
      {/* Modal message component */}
      {showModal && (
        <Modal
          open={showModal}
          onClose={handleModalClose}
          message={modalMessage}
          onNavigate={handleNavigate}
        />
      )}
    </div>
  );
}

export default ContactForm;
