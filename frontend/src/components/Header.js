import React from 'react'
import { Link } from 'react-router-dom'

export default function Header(){

    return (
        <div>
            <header>
                <h2>E-Learning App</h2>
                <Link to="/register">Register</Link>
                <Link to="/login">Login</Link>
                <Link to="/problem">View all Problems</Link>
            </header>
        </div>
    )
}