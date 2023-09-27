import React,{useEffect, useState} from "react";
import styled from "styled-components";
import Navbar from "./Navbar";
import Cookies from "universal-cookie";
import { Link } from "react-router-dom";
import './Styles/Userdetails.css'; // Import your CSS file for styling
import { useNavigate, NavLink } from "react-router-dom";
const Container = styled.div`
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
  border: 1px solid #ddd;
`;

const TableHeader = styled.th`
  background-color: #0fe3e0;
  color: #fff;
  padding: 12px;
  text-align: center;
  border: 1px solid #ddd;
`;

const TableRow = styled.tr`
  &:nth-child(even) {
    background-color: #f2f2f2;
  }
`;

const TableCell = styled.td`
  padding: 12px;
  border: 1px solid #ddd;
`;

const UserDetails = ({}) => {
  const cookie =new Cookies();
  const [userAccounts, setUserAccounts] = useState([]);
  const [accountStatus, setAccountStatus] = useState([]);
  const [searchEmail, setSearchEmail] = useState("");
  const [cookieValue,setCookieValue] = useState(null);
  const navigate = useNavigate();
    // const toggle = () => {
    //     setCondition(!condition);
    // }


    const handleClick = () => {
      cookie.remove("searchEmail");
      cookie.remove("myCookie")
      cookie.remove("emailId")
      cookie.remove("first")
      cookie.remove("last")
      cookie.remove("status")
      navigate('/')
    }

    useEffect(() => {
      setSearchEmail(cookie.get('searchEmail'))
    }, [])

  useEffect(() => {
 
    if(!cookie.get('myCookie')){
      navigate('/login')
  }
  //  else if(!localStorage.getItem("searchEmail")){
  //   window.location.reload();
  //  }

 
    let options = {
      method:"GET",
      headers: {
        'Content-Type': 'application/json',
        'Authorization' :  `Bearer ${cookie.get("myCookie")}`

      }
    }
    console.log(cookie.get("searchEmail"))
   setCookieValue(cookie.get('searchEmail'));


    if(cookieValue){
      fetch(`http://localhost:8080/admin/useraccounts?emailId=${cookieValue}`, options)
      .then((resp)=> resp.json())
      .then((resp) => {
          console.log(resp)
     
      setUserAccounts(resp);
      setAccountStatus(resp.map(account => account.status))
     
     
        
  
      });
  
    }
    else{
      
    }
  
 

  
}, [cookieValue]);

const handleAnchorClick = (e) => {
  console.log(e.target.textContent)
  localStorage.setItem("acc_no",e.target.textContent)
}


const toggleStatus = (index) => {
  // Update the local state
  const newStatus = [...accountStatus];
  newStatus[index] = !newStatus[index];
  setAccountStatus(newStatus);

  // Make a POST request when status is set to false

    // Replace with your API endpoint and request configuration
    fetch(`http://localhost:8080/admin/setStatus?acc_num=${userAccounts[index].acc_no}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization' :  `Bearer ${cookie.get("myCookie")}`
      },
    })
      .then(response => {
        // Handle the response as needed
        if (response.status === 200) {
          // Successful POST
          console.log("Status Changed")
        } else {
          // Handle errors
          console.log("error changing status")
        }
      })
      .catch(error => {
        // Handle network errors
      });
  
};



  return (
    <>
    
    <nav style={{ backgroundColor: "#f5f5f5", padding: "7px" }}>
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <div>
            <Link
              to="/"
              style={{
                textDecoration: "none",
                color: "#333",
                fontSize: "24px",
                marginLeft: "20px",
              }}
            >
             Admin Dashboard
            </Link>
          </div>
          <div>
            <ul
              style={{
                listStyleType: "none",
                padding: 0,
                display: "flex",
                gap: "20px",
              }}
            >
              
    
                  <li>
                    <Link
                      to="/admin/viewall"
                      style={{ textDecoration: "none", color: "#333" }}
                    >
                      View All Users
                    </Link>
                  </li>

                  <li>
                    <Link
                      to="/"
                      onClick={handleClick}
                      style={{
                        textDecoration: "none",
                        color: "#333",
                        marginRight: "20px",
                      }}
                    >
                      Logout
                    </Link>
                  </li>
                  
                
            </ul>
          </div>
        </div>
      </nav>

      <div className="user-details">
      <div className="user-detail">
        <i className="fas fa-envelope"></i>
        <span>Email :&nbsp; </span>
        <span>{cookie.get("searchEmail")}</span>
      </div>
      <div className="user-detail">
        <i className="fas fa-user"></i>
        <span>Name :&nbsp; </span>
        <span>{cookie.get("first")}</span>
      </div>
      <div className="user-detail">
        <i className="fas fa-phone"></i>
        <span>Phone :&nbsp; </span>
        <span>{userAccounts[0]?userAccounts[0].phone_no:""}</span>
      </div>
      <div className="user-detail">
        <i className="fas fa-id-card"></i>
        <span>Aadhar :&nbsp; </span>
        <span>{userAccounts[0]?userAccounts[0].aadhar_no:""}</span>
      </div>
    </div>
 
      
      <Container>


        <h1 style={{ textAlign: "center" }}>Account Details</h1>

        <Table>
          <thead>
            <TableRow>

              <TableHeader>Account Number</TableHeader>
              <TableHeader>Account Type</TableHeader>
              <TableHeader>Account Balance</TableHeader>
              <TableHeader>Account Open Date</TableHeader>
              <TableHeader>Account Status</TableHeader>
              
             

            </TableRow>
          </thead>
          <tbody>
          {userAccounts.map((account, index) => (
           

           
              <TableRow key={account.acc_no}>

                <TableCell><a href="/admin/transactions" className="accNumber" onClick={handleAnchorClick}>{account.acc_no}</a></TableCell>
                <TableCell>{account.acc_type}</TableCell>
                <TableCell>{account.balance}</TableCell>
                <TableCell>{account.acc_open_date}</TableCell>
               <TableCell>
                <label>
                  <input
                  type = "checkbox"
                   checked = {accountStatus[index]} 
                   onChange = {() => toggleStatus(index) }

                   />

                   {accountStatus[index] ? 'Active' : 'Inactive'}

                  </label>

               </TableCell>
             


              </TableRow>
             
            ))}

           
          </tbody>
        </Table>
      </Container>
    </>
  );
};

export default UserDetails;
