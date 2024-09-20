import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../hooks/AuthProvider";

const AuthorizedRoute = () => {
  const user = useAuth();
  if (!user.token) return <Navigate to="/" />;
  return <Outlet />;
};

export default AuthorizedRoute;