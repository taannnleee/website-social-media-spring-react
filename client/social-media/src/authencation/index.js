import React, { useState, useEffect } from "react";
import axios from "axios";

function YourComponent() {
  const [customer, setCustomer] = useState(null);

  useEffect(() => {
    async function fetchCustomer() {
      try {
        const response = await axios.get("http://localhost:8080/api/index");
        setCustomer(response.data.data); // Đảm bảo response.data.data là dữ liệu bạn mong muốn từ API
      } catch (error) {
        console.error("Error fetching customer:", error);
      }
    }

    fetchCustomer();
  }, []);

  return (
    <div>
      {customer ? (
        <div>
          <h2>Customer Details</h2>
          <p>First Name: {customer.firstName}</p>
          <p>Last Name: {customer.lastName}</p>
          <p>Email: {customer.email}</p>
          {/* Thêm các trường thông tin khác tùy theo cấu trúc dữ liệu của Customer */}
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}

export default YourComponent;
