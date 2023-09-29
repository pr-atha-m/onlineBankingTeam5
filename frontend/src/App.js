import "./App.css";
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { useState } from "react";
import Forgot from "./components/Forgot";
import OpenAccount from "./components/OpenAccount";
import Services from "./components/Services";
import AccountDetails from "./components/AccountDetails";
import Withdrawal from "./components/Withdrawal";
import Transactions from "./components/Transactions";
import Addfund from "./components/Addfund";
import AccountNumber from "./components/AccountNumber";
import Transfer from "./components/Transfer";
import UserDetails from "./components/UserDetails";
import ViewAll from "./components/ViewAll";
import Admin from "./components/Admin";
import AdminTransactions from "./components/AdminTransactions";

function App() {
  const [userstate, setUserState] = useState(false);
  // const navigate = useNavigate();

  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Home />}>
            {" "}
          </Route>
          <Route path="/login" element={<Login />}></Route>

          <Route path="/signup" element={<Register />}></Route>

          <Route path="/createaccount" element={<OpenAccount />}></Route>
          <Route path="/forgotpassword" element={<Forgot />}></Route>

          <Route path="/service" element={<Services />}></Route>

          <Route path="/accountdetails" element={<AccountDetails />}></Route>
          <Route path="/withdraw" element={<Withdrawal />}></Route>
          <Route path="/transactions" element={<Transactions />}></Route>

          <Route path="/deposit" element={<Addfund />}></Route>
          <Route path="/accountno" element={<AccountNumber />}></Route>
          <Route path="/transfer" element={<Transfer />}></Route>

          <Route path="/admin" element={<Admin />}></Route>

          <Route path="/admin/userdetails" element={<UserDetails />}></Route>
          <Route
            path="/admin/transactions"
            element={<AdminTransactions />}
          ></Route>
          <Route path="/admin/viewall" element={<ViewAll />}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
