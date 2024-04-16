import React, { useState, useEffect } from "react";

// Counter component
function Counter({ value, onChange }) {
    // State to manage the count value
    const [count, setCount] = useState(value || 1);

    // Syncs count state with the value prop
    useEffect(() => {
        if (value !== count) {
            setCount(value);
        }
    }, [value]);
    // Function to increment the count value
    const incrementCount = () => {
        if (count < 100) {
            // Update count state
            setCount(count + 1);
            onChange(count + 1);
        }
    };
    // Function to decrement the count value
    const decrementCount = () => {
        if (count > 1) {
            // Update count state
            setCount(count - 1);
            onChange(count - 1);
        }
    };
    // Function to clear the count value
    const clearCount = () => {
        // Reset count to 0
        setCount(0);
        onChange(0);
    };

    // Render the Counter component
    return (
        <div>
            <input
                className="form-control border-3 border-primary rounded"
                style={{ width: "250px", height: "31px" }}
                type="number"
                readOnly
                value={count}
            />
            <br />
            <button aria-label="descrease button" style={{ marginRight: "15px" }} className="btn btn-primary" type="button" onClick={decrementCount}>-</button>
            <button aria-label="clear button" className="btn btn-primary" type="button" onClick={clearCount}>Clear</button>
            <button aria-label="increase button" style={{ margin: "15px" }} className="btn btn-primary" type="button" onClick={incrementCount}>+</button>
        </div>
    );
}

export default Counter;
