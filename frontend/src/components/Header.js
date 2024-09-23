import React from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../hooks/AuthProvider'
import '../css/header.css'

export default function Header(){
    const auth = useAuth()

    const header = (auth.token!=='' && auth.username!=='')?
        (<>
            <li class="active"> <Link to="/problem"> View all Problems </Link> </li>
            <li class="active"> <Link to={`/profile/${auth.username}`}> My Profile </Link> </li>
            { auth.role==='STUDENT'?
                <li class="active"> <Link to={`/profile/${auth.username}/solutions`}>My Solutions</Link></li>:''}
            <li class="active" onClick={() => auth.logout()}> <Link> Logout </Link></li>
        </>):
        (<>
            <li class="active"> <Link to="/register"> Register </Link> </li>
            <li class="active"> <Link to="/login"> Login </Link> </li>
        </>)


    return (
    <>
        <nav class="navbar">
        <div class="container">
            <div class="navbar-header">
            <button class="navbar-toggler" data-toggle="open-navbar1">
                <span></span>
                <span></span>
                <span></span>
            </button>
            <a href="#">
                <Link to="/"><h4>My<span>App</span></h4></Link>
            </a>
            </div>

            <div class="navbar-menu" id="open-navbar1">
            <ul class="navbar-nav">
                {header}
            </ul>
            </div>
            </div>
        </nav>    
    </>        
    )
}