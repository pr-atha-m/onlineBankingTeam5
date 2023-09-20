import "./App.css";
import Profile from "./components/Profile";
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useState } from "react";
import Forgot from "./components/Forgot";
import Dashboard from "./components/Dashboard";
import OpenAccount from "./components/OpenAccount";
import Services from "./components/Services";
import AccountDetails from "./components/AccountDetails";
import Withdrawal from "./components/Withdrawal";
import Transactions from "./components/Transactions";
import Addfund from "./components/Addfund";
import AccountNumber from "./components/AccountNumber";
import Transfer from "./components/Transfer";
import UserDetails from "./components/UserDetails";
import Admin from "./components/Admin";


function App() {
  const [userstate, setUserState] = useState({});
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Home />}></Route>
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
          <Route path="/dashboard" element={<Dashboard />}></Route>
          <Route path="/createaccount" element={<OpenAccount />}></Route>
          <Route path="/forgotpassword" element={<Forgot />}></Route>

          <Route path="/service" element={<Services />}></Route>

          <Route path="/accountdetails" element={<AccountDetails />}></Route>
          <Route path="/withdraw" element={<Withdrawal />}></Route>
          <Route path="/transactions" element={<Transactions />}></Route>

          <Route path="/deposit" element={<Addfund />}></Route>
          <Route path="/accountno" element={<AccountNumber/>}></Route>
          <Route path="/transfer" element={<Transfer/>}></Route>

          <Route path="/admin" element={<Admin/>}></Route>

          <Route path="/admin/userdetails" element={<UserDetails/>}></Route>

        </Routes>
      </Router>
    </div>
  );
}

export default App;
