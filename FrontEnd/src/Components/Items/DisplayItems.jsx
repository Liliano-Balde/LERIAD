import ItemCard from "./ItemCard";

// Component to display items
function DisplayItems(props) {

  const itemArray = [];
  for (const item of props.items) {
    itemArray.push(
      <ItemCard
        key={item.name + " " + item.quantity}
        id={item.id}
        name={item.name}
        price={item.price}
        quantity={item.quantity}
        description={item.description}
        getItems={props.getItems}
      />
    );
  }

  return (
    
    <div><h2 aria-label="products page" className="border border-dark p-2 mb-2 border-4 border-dark rounded" style={{marginLeft:"50px", marginTop:"50px", width: "180px", color: "white", fontFamily: "Verdana, sans-serif", backgroundColor: "#365074"}} >Products</h2>
      {/* Container for item cards */}
      <div className="item-container" style={{marginLeft:"50px"}}>
        {/* Row for displaying item cards */}
        <div  className="row">{itemArray}</div>
      </div>
    </div>
  );
}


export default DisplayItems; 