  import logo from './logo.svg'
  import { useState } from 'react';
  import './App.css';

  const orders = [100,200,300];

  function App() {
  
    const [count, setCount] = useState(()=>{
      const total = orders.reduce((total, cur)=>{
        return (total + cur);
      })
      return total;
    });
    function handle(){
      // setCount(count +1);
      setCount(prevState => prevState + 1);
    }

    return (
      <div className="App">
      <h1>{count}</h1>
      <button onClick={handle}>nhấn vào</button>
      </div>
    );
  }

  export default App;
