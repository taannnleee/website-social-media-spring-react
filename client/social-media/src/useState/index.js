import { useState } from "react";

const orders = [100, 200, 300];

function IndexComponent() {
  const [count, setCount] = useState(() => {
    const total = orders.reduce((total, cur) => {
      return total + cur;
    });
    return total;
  });

  function handle() {
    setCount((prevState) => prevState + 1);
  }

  return (
    <div>
      <h1>{count}</h1>
      <button onClick={handle}>Nhấn vào</button>
    </div>
  );
}

export default IndexComponent;
