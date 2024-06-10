// two way binding
import { useState } from "react";

const courses = [
  {
    id: 1,
    name: "HTML, CSS",
  },
  {
    id: 2,
    name: "JSS",
  },
  {
    id: 3,
    name: "SPRINGBOOT",
  },
];

function IndexComponentBindingTwoWay() {
  const [check, setCheck] = useState();
  const [name, setName] = useState(1);
  return (
    <div>
      <div>
        <input value={name} onChange={(e) => setName(e.target.value)}></input>
        <button onClick={() => setName("Nguuyeenx Văn B")}>Change</button>

        <input type="radio"></input>
      </div>

      <div>
        {courses.map((course) => (
          <div key={course.id}>
            <input
              checked={check === course.id}
              onChange={() => {
                setCheck(course.id);
              }}
              type="radio"
            ></input>
            {course.name}
          </div>
        ))}
      </div>
    </div>
  );
}
export default IndexComponentBindingTwoWay;
