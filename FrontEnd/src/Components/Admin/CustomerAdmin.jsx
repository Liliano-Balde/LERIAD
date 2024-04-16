import axios from "axios";
import { useEffect,useState } from "react";

import CustomerList from "../Customer/CustomerList";

function CustomerAdmin(props) {
    // Function to fetch the list of customers
    function getCustomer() {
        axios.get("http://localhost:8082/customer/get")
        .then((response)=>{setCustomer(response.data)})
        .catch(console.log)        
    }
    // Fetch the list of customers when the component mounts
    useEffect(()=>{getCustomer()},[])
    const [customers, setCustomer] = useState([])
    // Render the CustomerList component
    return (
        <div >   
            {/* <div><NewCustomer getCustomer={getCustomer}/></div> */}
            <br></br>
            <div><CustomerList listCustomer={customers} getCustomer={getCustomer} /></div>
        </div>);
}

export default CustomerAdmin;