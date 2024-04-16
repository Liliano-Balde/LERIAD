import { useState } from "react";
import axios from 'axios';
import { useNavigate, useParams } from "react-router-dom";
import Modal from "../Customer/Modal";

// Component for registering new customers
function NewCustomer(props) {

    const navigate = useNavigate();
    const params = useParams();
    const [name, setName] = useState("")
    const [address, setAddress] = useState("")
    const [email, setEmail] = useState("")
    const [phone, setPhone] = useState("")
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [showModal, setShowModal] = useState(false);
    const [modalMessage, setModalMessage] = useState("");
    
    
    // Function to check if customer already exists, then create new customer
    function CheckCustomer() {

        axios.get("http://localhost:8082/customer/get").then(response => {
            console.log(response)
            for (const customer of response.data) {
                if (customer.username.toLowerCase() === username.toLowerCase()) {
                    // If customer already exists, show modal
                    setModalMessage("Customer already exists");
                    setShowModal(true)
                    return;
                }
            }
            // If customer does not exist, create new customer
            axios.post("http://localhost:8082/customer/create",
                { name, address, email, phone, username, password })
                .then(response => {
                    console.log(response);
                    // Reset form fields and fetch updated customer list
                    setName("");
                    setAddress("");
                    setEmail("");
                    setPhone("");
                    setUsername("");
                    setPassword("");
                    props.getCustomer();
                    setModalMessage("Registered! Redirecting to the login page");
                    setShowModal(true);
                }).catch(err => console.error(err))
        })


    }
    // Function to handle navigation after modal is closed
    const handleNavigate = () => {
        navigate("/");
        setShowModal(false);
    };
    // Function to handle closing modal
    const handleModalClose = () => {
        setShowModal(false);
      };

    return (
        <main role="main">

            <form onSubmit={e => {
                e.preventDefault();
                CheckCustomer();

            }}>

                <br></br>
                <h1 aria-label="registration page" className="border border-dark p-2 mb-2 border-4  border-dark  rounded" style={{ color: "White", fontFamily: "Verdana, sans-serif", width: "200px", backgroundColor: "#365074" }}>Sign up</h1>
                <div className="border  border-dark  p-2 mb-2 border-4  border-dark  rounded" style={{ color: "white", fontFamily: "Verdana, sans-serif", fontSize: "20px", marginTop: "30px", marginLeft: "200px", backgroundColor: "#365074", width: "350px" }}>
                    {/* Form inputs */}
                    <label  htmlFor="name">Name</label>
                    <br /><input aria-label="name field" className="form-control border-3  border-dark  rounded" style={{ width: "250px", height: "31px" }}
                        id="name"
                        name="name"
                        type="text"
                        value={name}
                        onChange={e => setName(e.target.value)}
                        required
                    />
                    <br /><label htmlFor="address">Address</label>
                    <br /><input aria-label="address field" className="form-control border-3 border-dark rounded" style={{ width: "250px", height: "31px" }}
                        id="address"
                        address="address"
                        type="text"
                        value={address}
                        onChange={e => setAddress(e.target.value)}
                        required
                    />
                    <br /><label htmlFor="email">Email</label>
                    <br /><input aria-label="email field" className="form-control border-3 border-dark rounded" style={{ width: "250px", height: "31px" }}
                        id="email"
                        email="email"
                        type="text"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                        required
                    />
                    <br /><label htmlFor="phone">Phone</label>
                    <br /><input aria-label="phone field" className="form-control border-3 border-dark rounded" style={{ width: "250px", height: "31px" }}
                        id="phone"
                        phone="phone"
                        type="text"
                        value={phone}
                        onChange={e => setPhone(e.target.value)}
                        required
                    />
                    <br /><label htmlFor="username">Username</label>
                    <br /><input aria-label="username field" className="form-control border-3 border-dark rounded" style={{ width: "250px", height: "31px" }}
                        id="username"
                        username="username"
                        type="text"
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                        required
                    />
                    <br /><label htmlFor="password">Password</label>
                    <br /><input aria-label="password field" className="form-control border-3 border-dark rounded" style={{ width: "250px", height: "31px" }}
                        id="password"
                        password="password"
                        type="password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        required
                    />
                    <div className="mt-2">
                        {/* <label htmlFor="al">Submit Button:</label> */}
                        <button
                            className="btn btn-light btn-lg" type="submit" id="al" aria-label="Submit Button">Submit</button>
                    </div>


                </div>
                {/* Modal message component */}
                {showModal && (
                    <Modal
                        open={showModal}
                        onClose={handleModalClose}
                        message={modalMessage}
                        onNavigate={handleNavigate}
                    />
                )}
            </form>
        </main>
    );
}

export default NewCustomer;