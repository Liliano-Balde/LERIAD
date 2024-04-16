
import PropTypes from "prop-types";
import Card from "react-bootstrap/Card";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import productImages from "../../productsImage.json"
import { useParams } from "react-router-dom";
import { useState } from "react";
import Modal from "../Customer/Modal";


// Component to display and manage individual items
function UpdateCard(props) {
  const params = useParams("");
  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [itemID, setItemID] = useState("");
  const navigate = useNavigate();

  // Function to delete an item
  function deleteItem() {
    axios.delete("http://localhost:8082/item/delete/" + props.id)
      .then(response => { 
        props.getItems() 
      })
      .catch(err => console.error(err))
  }

  // Function to get image URL for a product
  function getImageUrl(productName) {
    const productNameLower = productName.toLowerCase();
    if (productNameLower in productImages) {
      return productImages[productNameLower];
    } else {
      return "/default.png";
    }
  }
  // Function to add an item to the cart
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
  // Function to handle modal close event
  const handleModalClose = () => {
    setShowModal(false);
  };
  // Function to handle navigation after adding item to cart
  const handleNavigate = () => {
    navigate("/items"); // Navigate to items page after adding to cart
  };

  // Render the UpdateCard component
  return (
    <>
      <Card aria-label="item card" style={{ width: "300px", fontFamily: "Verdana, sans-serif" }} className="col-sm-6 col-md-4 col-lg-3 m-4">
        <div className="card-body ">
          <h4 className="card-title">
            {" "}
            <img
              src={getImageUrl(props.name)}
              alt="avatar"
              className="card-person"
              style={{ maxWidth: '50%', height: '50%' }}
              aria-label="item image"
            />
            <p aria-label="item name"> {props.name}</p>
            <p aria-label="item description"> {props.description}</p>

          <p aria-label="item price">Price: £{props.price}</p>
          </h4>
          <button onClick={() =>
            navigate("/items/edit/" + props.id)
          } style={{ marginTop: "10px" }} type="submit"  id="edititem" aria-label="edit item button" className="btn btn-warning btn-md">
            {" "}
            Edit Item{" "}
          </button>
          
 <button style={{ marginLeft:"10px", marginTop: "10px" }} className="btn btn-danger" aria-label="delete item button" onClick={deleteItem}>Delete Item</button>
        
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
UpdateCard.propTypes = {
  id: PropTypes.number.isRequired,
  name: PropTypes.string.isRequired,
  price: PropTypes.number.isRequired,
  quantity: PropTypes.number.isRequired,
  description: PropTypes.string.isRequired
};

export default UpdateCard;