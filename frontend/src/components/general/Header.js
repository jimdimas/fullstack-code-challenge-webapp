import React from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../../hooks/AuthProvider'
import '../../css/header.css'

export default function Header(){
    const auth = useAuth()

    const header = (auth.token!=='' && auth.username!=='')?
        (<>
            <li class="active" id="tests"><Link to="/test">View all Tests</Link></li>
            <li class="active" id="problems"> <Link to="/problem"> View all Problems </Link> </li>

            {auth.role==="ADMIN" && <>
                <li class="active"><Link to="/students">Students</Link></li>
                <li class="active"><Link to="/supervisors">Supervisors</Link></li>
            </>}

            {auth.role==='SUPERVISOR' &&
            <>
                <li class="active" id="uploadProblem"><Link to="/problem/upload">Upload Problem</Link></li>
                <li class="active" id="uploadTest"><Link to="/test/upload">Upload Test</Link></li>
            </>}

            <li class="active" id="profile"> <Link to={`/profile/${auth.username}`}> My Profile </Link> </li>

            { auth.role==='STUDENT' &&  <>
                    <li class="active" id="solutions"> <Link to={`/profile/${auth.username}/solutions`}>My Solutions</Link></li>
                    <li class="active" id="results"> <Link to={`/results/${auth.username}`}>My Test Results</Link></li>
                </>
                }

            <li class="active" id="logout" onClick={() => auth.logout()}> <Link> Logout </Link></li>
        </>):
        (<>
            <li class="active" id="register"> <Link to="/register"> Register </Link> </li>
            <li class="active" id="login"> <Link to="/login"> Login </Link> </li>
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
                <Link to="/"><h4>Code<span>Challenge</span></h4></Link>
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