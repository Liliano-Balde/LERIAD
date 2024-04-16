
import { useState } from "react";
import Modal from "../Customer/Modal";
import confetti from "canvas-confetti";
import { useNavigate } from "react-router-dom";

// Payment component
function Payment() {
  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [name, setName] = useState("");
  const [cardNumber, setCardNumber] = useState("");
  const [expiryDate, setExpiryDate] = useState("");
  const [cvc, setCVC] = useState("");
  const [postcode, setPostcode] = useState("");


  const navigate = useNavigate();

  // Function to handle form submission
  const handleSubmit = (event) => {
    event.preventDefault();
    setModalMessage("Your order is now complete");
    handleConfetti();
    setShowModal(true);
    setName("");
    setCardNumber("");
    setExpiryDate("");
    setCVC("");
    setPostcode("");
  };
  // Function to generate confetti effect
  const handleConfetti = () => {
    confetti({
      particleCount: 500,
      spread: 320,
    });
  };
  // Function to close the modal
  const handleModalClose = () => {
    setShowModal(false);
  };
  // Function to navigate to another page
  const handleNavigate = () => {
    navigate("/checkout"); 
    setShowModal(false); 
  };

  {/* Payment form */}
  return (
    <div>
    <header>
      <h1 aria-label="payment page" className="border border-dark p-2 mb-2 border-4 border-dark rounded" style={{ marginLeft: "50px", marginTop: "50px", width: "380px", color: "white", fontFamily: "Verdana, sans-serif", backgroundColor: "#365074" }}>Payment Details</h1>
    </header>
    <main>
      <br />
      <form onSubmit={handleSubmit}>
        <div className="border border-dark p-2 mb-2 border-4 border-dark rounded" style={{ color: "white", fontFamily: "Verdana, sans-serif", fontSize: "20px", marginTop: "50px", marginLeft: "50px", backgroundColor: "#365074", width: "450px" }}>
          <br />
          <label htmlFor="fn">Name: As it appears on the card &nbsp;</label>
          <input  
            required 
            className="form-control border-3 border-dark rounded" 
            aria-label="name field" 
            style={{ width: "250px", height: "31px" }} 
            type="text" 
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <br />
          <label htmlFor="fn">Long Card Number: PAN &nbsp;</label>
          <input  
            required 
            className="form-control border-3 border-dark rounded" 
            aria-label="card field" 
            style={{ width: "250px", height: "31px" }} 
            type="text" 
            value={cardNumber}
            onChange={(e) => setCardNumber(e.target.value)}
          />
          <br />
          <label htmlFor="fn">Card Expiry Date: MM/YY &nbsp;</label>
          <input  
            required 
            className="form-control border-3 border-dark rounded" 
            aria-label="expiry date field" 
            style={{ width: "250px", height: "31px" }} 
            type="text" 
            value={expiryDate}
            onChange={(e) => setExpiryDate(e.target.value)}
          />
          <br />
          <label htmlFor="fn">CVC &nbsp;</label>
          <input  
            required 
            className="form-control border-3 border-dark rounded" 
            aria-label="cvc field" 
            style={{ width: "250px", height: "31px" }} 
            type="text" 
            value={cvc}
            onChange={(e) => setCVC(e.target.value)}
          />
          <br />
          <label htmlFor="fn">Postcode &nbsp;</label>
          <input  
            required 
            className="form-control border-3 border-dark rounded" 
            aria-label="postcode field" 
            style={{ width: "250px", height: "31px" }} 
            type="text" 
            value={postcode}
            onChange={(e) => setPostcode(e.target.value)}
          />
          <br />
          <button aria-label="submit button" className="btn btn-light btn-lg" type="submit">Submit</button>
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
      </main>
    </div>
    
  );
}

export default Payment;