import React from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../hooks/AuthProvider'

export default function Header(){
    const auth = useAuth()

    const header = (auth.token!=='' && auth.username!=='')?
        (<>
            <Link to="/problem"> View all Problems </Link>
            <Link to={`/profile/${auth.username}`}> My Profile </Link>
            <button onClick={() => auth.logout()}> Logout </button>
        </>):
        (<>
            <Link to="/register"> Register </Link>
            <Link to="/login"> Login </Link>
        </>)

    return (
        <div>
            <header>
                <h2>E-Learning App</h2>
                { header }
            </header>
        </div>
    )
}