// one way binding
import { useState } from "react";

const gifts = ["CPU i9", "RAM 16 GB", "ROM 258GB"];
function IndexComponentBinding() {
  const [gift, setGift] = useState();

  const handle = () => {
    const index = Math.floor(Math.random() * gifts.length);
    setGift(gifts[index]);
    console.log(gifts[index]);
  };
  return (
    <div>
      <h1>{gift || "Chua co phan thuong nao"}</h1>
      <button onClick={handle}>nhan vao de nhan thuong</button>
    </div>
  );
}
export default IndexComponentBinding;
