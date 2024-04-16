import React, { Component, useState } from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css'


// Navbar Component
function Navbar() {

  return (
    <nav role="navigation">
    <div className="main-navbar shadow-sm sticky-top">
      <div className="top-navbar"  style={{backgroundColor: "#22334a"}}>
        <div className="container-fluid">
          <div className="row align-items-center">
            <div className="col-md-auto my-auto">
              {/* Logo link */}
              <Link to="/">
                <img role="logo" src="/Logo2.png" alt="Logo Icon" title="link to homepage" aria-label="link to homepage" className="navbar-brand" width="200px" />
              </Link>
            </div>
            <div className="col-md-4 my-auto">
              {/* Search Bar */}
              <form role="search">
                <div className="input-group">
                  <input type="search" placeholder="Search" className="form-control" title="Search Bar" aria-label="Search Bar" style={{ width: "200px", marginRight: "10px" }} />
                  <button className="btn bg-white" type="submit" id="submit" aria-label="Search Button">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-search" viewBox="0 0 16 16">
                      <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
                    </svg>
                  </button>
                </div>
              </form>
            </div>
            <div className="col-md-5 my-auto">
              {/* Navigation Links */}
              <ul className="nav">
                <li className="nav-item">
                  <Link to="/customer" className="nav-link" title="link to registration page" aria-label="link to registration page" style={{  fontFamily: "Verdana, sans-serif", fontSize: "30px", color: "white" }}>
                    Sign up
                  </Link>
                </li>
                <li className="nav-item">
                  <Link to="/items" className="nav-link" title="link to item page" aria-label="link to item page" style={{ fontFamily: "Verdana, sans-serif", fontSize: "30px", color: "white" }}>
                    Products
                  </Link>
                </li>
                <li className="nav-item">
                  <Link to="/contactus" className="nav-link"  title="link to admin page" aria-label="link to contactus" style={{ fontFamily: "Verdana, sans-serif", fontSize: "30px", color: "white" }}>
                    Contact us
                  </Link>
                </li>
              </ul>
            </div>
            <div className="col-md-auto my-auto">
              {/* Basket Link */}
              <Link to="/customer/get/1" className="nav-link"  title="link to basket" aria-label="link to basket" style={{ fontFamily: "Verdana, sans-serif", color: "white", textDecoration: "underline" }}>
                <svg id="basket" xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="white" className="bi bi-cart" viewBox="0 0 19 19">
                  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2" />
                  {/* <circle cx="15" cy="3.5" r="3.5" fill="red"></circle> */}
                  {/* <text x="15" y="6" textAnchor="middle" fill="white" fontSize="7">{basket.length}</text> */}
                </svg>
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
    </nav>
  );
}

export default Navbar;
