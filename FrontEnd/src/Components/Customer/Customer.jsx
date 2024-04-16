import axios from "axios";
import { useEffect,useState } from "react";
import NewCustomer from "./NewCustomer";
import CustomerDisplay from "./CustomerList";

// Customer component
function Customers(props) {
    
    // Function to fetch customer data from the server
    function getCustomer() {
        axios.get("http://localhost:8082/customer/get")
        .then((response)=>{setCustomer(response.data)})
        .catch(console.log)        
    }
    // useEffect hook to fetch customer data upon component mounting
    useEffect(()=>{getCustomer()},[])
    const [customers, setCustomer] = useState([])

    return (
        <div >
            {/* Render the NewCustomer component to allow adding new customers */}   
            <div><NewCustomer getCustomer={getCustomer}/></div>
            <br></br>
        </div>);
}

export default Customers;