
import PropTypes from "prop-types";
import Card from "react-bootstrap/Card";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import productImages from "../../productsImage.json"
import { useParams } from "react-router-dom";
import { useState } from "react";
import Modal from "../Customer/Modal";

function ItemCard(props) {
  const params = useParams("");
  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [itemID, setItemID] = useState("");
  const navigate = useNavigate();

  // Function to get image URL based on product name
  function getImageUrl(productName) {
    const productNameLower = productName.toLowerCase();
    if (productNameLower in productImages) {
      return productImages[productNameLower];
    } else {
      return "/default.png";
    }
  }
  // Function to add item to cart
  function addToCart() {
    const itemID = props.id
    if (!itemID) {
      console.error("Item ID is null or undefined.");
      return;
    }
    axios.post("http://localhost:8082/item/addItem/1", { id: itemID })
      .then(response => {
        console.log(response);
        props.getItems();
        setModalMessage("Item added to cart, please adjust quantity in the basket");
        setShowModal(true);
      }).catch(err => console.error(err))
  }
  // Close modal
  const handleModalClose = () => {
    setShowModal(false);
  };
  // Navigate to items page after adding to cart
  const handleNavigate = () => {
    navigate("/items"); 
    setShowModal(false); 
  };

  return (
    <>
    {/* Item card */}
      <Card style={{ width: "300px" }} aria-label="item card" className="col-sm-6 col-md-4 col-lg-3 m-4">
        <div className="card-body ">
          <h4 className="card-title">
            {" "}
            {/* Item image */}
            <img
              src={getImageUrl(props.name)}
              alt="avatar"
              className="card-person"
              style={{ maxWidth: '50%', height: '50%' }}
            />
            {/* Item details */}
            <p>Product: {props.name}</p>
            <p>Description: {props.description}</p>
          <p>Price: £{props.price}</p>
          </h4>
          {/* Button to add item to cart */}
          <button style={{ marginTop: "10px", marginRight: "15px" }} className="btn btn-success btn-md" onClick={addToCart}>Add to Cart</button>
        </div>
      </Card>

      {/* Modal message component */}
      {showModal && (
        <Modal
          open={showModal}
          onClose={handleModalClose}
          message={modalMessage}
          onNavigate={handleNavigate}
        />
      )}
    </>
  );
}
ItemCard.propTypes = {
  id: PropTypes.number.isRequired,
  name: PropTypes.string.isRequired,
  price: PropTypes.number.isRequired,
  quantity: PropTypes.number.isRequired,
  description: PropTypes.string.isRequired
};

export default ItemCard;