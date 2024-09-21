import React from 'react'
import { useAuth } from '../hooks/AuthProvider'
import { Navigate , Outlet } from 'react-router-dom'

export default function PublicRoute(){
    const auth = useAuth();
    console.log(auth.token)
    if (auth.token!=='' && auth.username!=='') return ( <Navigate to={`profile/${auth.username}`} /> );
    return ( <Outlet /> );
}