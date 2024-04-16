import { useState } from "react";
import './Modal.css';
import Modal from './Modal';

// Component to test Modal functionality
function TryModal() {
  const [openModal, setOpenModal] = useState(false);



  return (
    <div>
      {/* Button to open the modal */}
      <button 
      onClick={() => setOpenModal(true)} 
      className='modalButton'>
        Modal
      </button>
      <Modal 
      open={openModal} 
      onClose={() => setOpenModal(false)} />
      </div>
  );
}

export default TryModal;