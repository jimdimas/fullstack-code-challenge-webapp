import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../hooks/AuthProvider";

const AuthorizedRoute = () => {
  const auth = useAuth();
  if (auth.token==='' || auth.username==='') return <Navigate to="/" />;
  return <Outlet />;
};

export default AuthorizedRoute;