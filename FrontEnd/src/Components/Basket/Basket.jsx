import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import Counter from "./Counter";
import productImages from "../../productsImage.json";
import { useNavigate } from "react-router-dom";

// Basket component to manage items in the basket and display them
function Basket(props) {
    const params = useParams();
    const [basket, setBasket] = useState([]);
    const navigate = useNavigate();
  
  // Fetch customer basket on component mount
    useEffect(() => {
      getCustomer();
  }, []);
  // Function to remove item from the basket
    function removeFromBasket(itemID) {
      console.log("Removing item with ID:", itemID)
      axios.patch("http://localhost:8082/item/removeItem/" + itemID)
      .then(() => {
        getCustomer()
    })
    .catch(err => console.error(err));
}

    // Function to fetch customer basket from the server
    function getCustomer() {
        axios.get("http://localhost:8082/customer/get/" + params.id)
            .then((response) => setBasket(response.data.items))
            .catch(console.log);
    }
    // Function to handle quantity change for an item in the basket
    function quantityChange(index, newQuantity) {
        const updatedBasket = [...basket];
        updatedBasket[index].quantity = newQuantity;
        setBasket(updatedBasket);
    }

    // Generate JSX for basket items
    const basketItems = basket.map((basketItem, index) => (

        <div className="d-inline-flex" style={{ maxWidth: "20%", margin: "20px", fontFamily: "Verdana, sans-serif" }} key={index}>
            <div className="card">
                <div className="card-body">
                    <img
                        src={getImageUrl(basketItem.name)}
                        alt="avatar"
                        className="card-person"
                        style={{ maxWidth: '100px', height: '100px' }}
                    />
                    <h5>{basketItem.name}</h5>
                    <h8>Price: £ {basketItem.price.toFixed(2)}</h8>
                    <Counter
                        value={basketItem.quantity}
                        onChange={(newQuantity) => quantityChange(index, newQuantity)}
                    />
                    <h6>Total: £ {basketItem.price.toFixed(2) * (basketItem.quantity || 0).toFixed(2)}</h6>
                    
                </div>
            </div>
        </div>
    ));
      // Calculate total price of items in the basket
    const basketTotal = basket.reduce((total, item) => {
        return total + (Number(item.price) * Number(item.quantity));
      }, 0).toFixed(2);
      // Function to get image URL for a product
    function getImageUrl(productName) {
        const productNameLower = productName.toLowerCase();
        return productImages[productNameLower] || "/default.png";
    }
    // Render the Basket component
    return (
        <div>
          
          <header>
             <h2 aria-label="basket page" class="border border-dark p-2 mb-2 border-4 border-dark rounded" style={{ color: "white",  fontFamily: "Verdana, sans-serif", backgroundColor: "#365074", width: "150px"}}>Basket: </h2>
             </header>
             <main>
      
      <table className="table" aria-label="table" style={{fontFamily: "Verdana, sans-serif", fontSize: "20px" }}>
        <thead>
          <tr>
            <th aria-label="image">Image</th>
            <th aria-label="name">Name</th>
            <th aria-label="price">Price</th>
            <th aria-label="quantity">Quantity</th>
            <th aria-label="item price total">Total</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {basket.map((basketItem, index) => (
            <tr key={index}>
              <td>
                <img
                  src={getImageUrl(basketItem.name)}
                  alt="avatar"
                  className="card-person"
                  style={{ maxWidth: '100px', height: '100px' }}
                />
              </td>
              <td>{basketItem.name}</td>
              <td>£ {basketItem.price}</td>
              <td>
                <Counter
                  value={basketItem.quantity}
                  onChange={(newQuantity) => quantityChange(index, newQuantity)}
                />
              </td>
              <td className="text-decoration-underline"  style={{color: "blue"}}>£ {(basketItem.price * (basketItem.quantity || 0)).toFixed(2)}</td>
              <td><button className="btn btn-primary" aria-label="Remove product button" onClick={() => removeFromBasket(basketItem.id)}>Remove</button></td>
            </tr>
          ))}
        </tbody>
      </table><br></br>
            <div><h2 aria-label="basket total" className="border border-dark p-2 mb-2 border-4 border-dark rounded" style={{  color: "white",  fontFamily: "Verdana, sans-serif", backgroundColor: "#365074", width: "350px"}}>Basket Total : £{basketTotal}</h2></div>
            <button  className="btn btn-primary btn-lg" type="button" onClick={() => navigate('/checkout')} aria-label="Checkout button">Checkout</button>
            </main>
        </div>
    );
}

export default Basket;
