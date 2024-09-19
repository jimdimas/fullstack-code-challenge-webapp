import React from 'react'
import axios from 'axios'
import {useNavigate } from 'react-router-dom'

export default function Auth(props){

    const {isRegistered}=props
    const [user,setUser] = React.useState({
        username:"",
        password:"",
        email:""
    })
    const navigate = useNavigate()
    const [submit,setSubmit] = React.useState(false)

    function handleChange(event){
        const {name,value,type,checked} = event.target

        setUser(oldUser=>{
            return {
                ...oldUser,
                [name]:value
            }
        })
    }

    function onSubmit(event){
        event.preventDefault()
        setSubmit(true)
    }

    React.useEffect(()=>{
        if (submit){
            const url = `http://localhost:8080/api/v1/auth/${isRegistered?"login":"register"}`;

            axios
                .post(url, user)
                .then( response => {
                    if (response.data=="Failed authentication"){
                        navigate(`/${isRegistered?"/login":"/register"}`, { replace:true })
                    }else {
                        sessionStorage.setItem("Bearer Token",response.data)
                        return navigate(`/profile/${user.username}`, { replace:true })
                    }})
                .catch(err=>{
                    console.log(err)
                    navigate("/", { replace:true })
                })
        }
    },[submit])

    return (
        <div>
            <form onSubmit={onSubmit}>
                <label htmlFor="username">Username:</label>
                <input type="text" id="username" name="username" value={user.username} onChange={handleChange}/>
                <br/>
                <label htmlFor="password">Password:</label>
                <input type="password" id="password" name="password" value={user.password} onChange={handleChange}/>
                <br/>
                {!isRegistered && 
                    <>
                        <label htmlFor="email">Email:</label>
                        <input type="email" id="email" name="email" value={user.email} onChange={handleChange}/>
                        <br/>
                    </>
                }
                <button>Submit</button>
            </form>
        </div>
    )
}