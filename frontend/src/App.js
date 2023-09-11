import "./App.css";
import Profile from "./components/Profile";
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useState } from "react";
import Forgot from "./components/Forgot";

function App() {
 
  const [userstate, setUserState] = useState({});
  return (
    <div className="App">
      <Router>
        <Routes>
        <Route
            path="/"
            element={<Home/>}
          ></Route>
          <Route
            path="/login"
            element={
              userstate && userstate._id ? (
                <Profile
                  setUserState={setUserState}
                  username={userstate.fname}
                />
              ) : (
                <Login setUserState={setUserState} />
              )
            }
          ></Route>
          
          <Route path="/signup" element={<Register />}></Route>

          <Route
            path="/forgotpassword"
            element={<Forgot/>}>

            </Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;