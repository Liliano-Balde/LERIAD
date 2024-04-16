import ExistingCustomer from "./Customer/ExistingCustomer";
import Modal from "./Customer/Modal";
import { useState } from "react";


// Home component
function Home() {

  const [openModal, setOpenModal] = useState(false);
  return (
    <div style={{ position: "absolute", top: "100px", bottom: "0px", left: "50px", right: "50px", margin: "auto" }}>
      {/* Header section */}
      <header role="banner">
        <h1 className="border  border-dark p-4 mb-6 border-5 border-dark rounded" aria-label="slogan" style={{ maxWidth: "1500px", fontWeight: "bold", textAlign: "center", color: "white", fontFamily: "Verdana, sans-serif", backgroundColor: "#365074", marginTop: "80px", marginBottom: "80px" }}>"An accessible web is a wave everyone can ride." -  Flipper, the Finclusive Dolphin </h1>

      </header>
      {/* Main content section */}
      <main role="main">
        <br />
        <ExistingCustomer />
      </main>
    </div>
  );
}

export default Home;