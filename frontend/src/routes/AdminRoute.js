import React from "react"
import { useAuth } from '../hooks/AuthProvider'
import { Navigate , Outlet } from "react-router-dom"

export default function AdminRoute(){
    const auth = useAuth()

    return (auth.role==="ADMIN"?<Outlet/>:<Navigate to="/" />);
}