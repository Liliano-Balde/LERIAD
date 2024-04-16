import PropTypes from "prop-types";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Card from "react-bootstrap/Card";

// Function to delete customer
function DeleteCustomer(props) {

    const navigate = useNavigate();
    function deleteCustomer (){
        axios.delete("http://localhost:8082/customer/delete/" + props.id)
        // Refresh customer list after deletion
        .then(response => {props.getCustomer()})
        .catch(err => console.error(err))
        }

    return (
        // Render the customer card with delete button
        <div className="col-12 col-md-6 col-lg-4 my-2">
            <Card aria-label="customer card" style={{  marginLeft:"50px", width: "250px" }}>
                <div className="card-body" >
                    <img style={{ width: '50%' }} src="/Customer.PNG" alt="" />
                    <h4>{props.username}</h4>
                    <div className="row">
                        <button onClick={() => navigate("/customer/edit/" + props.id)} style={{ width: "100px", margin: "10px" }} aria-label="edit customer" className="btn btn-warning" id="edit">Edit</button>
                        <button style={{ width: "100px", margin: "10px" }}  className="btn btn-danger" aria-label="delete customer" onClick={deleteCustomer} id="delete">Delete</button>
                        {/* <button onClick={() => navigate("/customer/get/" + props.id)} style={{ width: "100px", margin: "10px" }} className="btn btn-success" >Basket</button> */}
                    </div>
                </div>
            </Card>
        </div>
    

    )
}
// PropTypes for the customer properties
DeleteCustomer.propTypes = {
    name: PropTypes.string.isRequired,
    address: PropTypes.string.isRequired,
    email: PropTypes.string.isRequired,
    phone: PropTypes.string.isRequired,
    username: PropTypes.string.isRequired,
}

export default DeleteCustomer;