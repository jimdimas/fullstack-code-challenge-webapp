import React from 'react'
import {useNavigate } from 'react-router-dom'
import { useAuth } from '../hooks/AuthProvider'

export default function Auth(props){
    const {isRegistered}=props
    const [credentials,setCredentials] = React.useState({
        username:"",
        password:"",
        email:""
    })
    const auth = useAuth()
    

    function handleChange(event){
        const {name,value,type,checked} = event.target

        setCredentials(oldCredentials=>{
            return {
                ...oldCredentials,
                [name]:value
            }
        })
    }

    function onSubmit(event){
        event.preventDefault()
        auth.access(credentials,isRegistered)
        return;
    }

    return (
        <div>
            <form onSubmit={onSubmit}>
                <label htmlFor="username">Username:</label>
                <input type="text" id="username" name="username" value={credentials.username} onChange={handleChange}/>
                <br/>
                <label htmlFor="password">Password:</label>
                <input type="password" id="password" name="password" value={credentials.password} onChange={handleChange}/>
                <br/>
                {!isRegistered && 
                    <>
                        <label htmlFor="email">Email:</label>
                        <input type="email" id="email" name="email" value={credentials.email} onChange={handleChange}/>
                        <br/>
                    </>
                }
                <button>Submit</button>
            </form>
        </div>
    )
}