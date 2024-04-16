import axios from "axios";
import { useEffect, useState } from "react";
import DisplayItems from "./DisplayItems";

// Items component
function Items (props) {

    const [name, setName] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [items, setItem] = useState([]);
    const [description, setDescription] = useState("");

    // Function to fetch items from the server
    function getItems() {
        axios.get("http://localhost:8082/item/get")
        .then((response) => { setItem(response.data)})
        .catch((err) => console.error(err));
    }
    // Fetch items on component mount
    useEffect(() => {
        getItems();
    }, []);

    // Function to create a new item
    function createItem() {
        axios.post("http://localhost:8082/item/create",{
        name, price, quantity, description})
        .then((response) => { 
            console.log(response);
            // Clear input fields and fetch items again
            setName("");
            setPrice("");
            setQuantity("");
            setDescription("");
            getItems();
        })
        .catch(err => console.error(err));
                // Logic to check if the item already exists
                axios.get("http://localhost:8082/item/get")
                .then(response => {
                console.log(response)
                for (const items of response.data) {
                    if (items.name.toLowerCase() === name.toLowerCase() && items.price.toLowerCase() === price.toLowerCase()) {
                         return;    
                    }
                }
    })
    .catch(err => console.error(err));
}

    return (  
        <div>
          <main>
      <DisplayItems items={items}  getItems={getItems}/> 
      </main>
    </div>
  );
}

export default Items;