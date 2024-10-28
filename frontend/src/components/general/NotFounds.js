import React from 'react'
import { Link } from 'react-router-dom'

export default function NotFound(props){
    return (
        <div>
            <h1>
                Oops , {props.message} doesn't exist... :( 
            </h1>
            <Link to="/">Back to Home Page</Link>
        </div>
    )
}