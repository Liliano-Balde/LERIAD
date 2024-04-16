import { Link, Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap';
import Navbar from './Components/Navbar';
import Items from './Components/Items/Items';
import EditItems from './Components/Items/EditItems';
import Customers from './Components/Customer/Customer';
import EditCustomer from './Components/Customer/EditCustomer';
import Basket from './Components/Basket/Basket';
import Home from './Components/Home';
import ItemAdmin from './Components/Admin/ItemAdmin';
import Payment from './Components/Basket/Payment';
import ContactForm from './Components/ContactForm';

function App() {
  
  
  return (
    
    <Router>
      <Navbar className="navbar align-content-center " style={{ display: "flex", backgroundColor: "rgba(0, 0, 0, 0)" }} />
      <Routes>
        <Route path='/'element={<Home />} />
        <Route path='/items' element={<Items />} />
        <Route path='/admin' element={<ItemAdmin/>} />
        <Route path='/checkout' element={<Payment/>} />
        <Route path='/customer' element={<Customers />} />
        <Route path='/items/edit/:id' element={<EditItems />} />
        <Route path='/customer/edit/:id' element={<EditCustomer />} />
        <Route path='/customer/existing/:id'/>
        <Route path='/customer/get/:id' element={<Basket/>}/>
        <Route path='/contactus' element={<ContactForm/>}/>
      </Routes>
    </Router>




  );
}

export default App;