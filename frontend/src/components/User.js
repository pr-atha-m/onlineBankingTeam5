import React, { useEffect } from "react";
import Cookies from "universal-cookie";

export const User = () => {
  const cookie = new Cookies();
  useEffect(() => {
    let options = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${cookie.get("myCookie")}`,
      },
    };
    fetch(
      `http://localhost:8080/admin/useraccounts?emailId=${cookie.get(
        "emailId"
      )}`,
      options
    )
      .then((resp) => resp.json())
      .then((resp) => {
        console.log(resp);
        cookie.set("first", resp.first_name);
        cookie.set("last", resp.last_name);
        cookie.set("status", resp.status);
      });
  }, []);
  return <div></div>;
};

export default User;
