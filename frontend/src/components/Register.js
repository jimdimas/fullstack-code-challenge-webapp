import React from 'react'
import axios from 'axios'

export default function Register(){

    const [user,setUser] = React.useState({
        "username":"",
        "password":"",
        "email":""
    })

    const [submit,setSubmit] = React.useState(false)

    const [result,setResult] = React.useState({
        "name":""
    })

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
            axios.post(`http://localhost:8080/api/v1/auth/register`, user)
            .then(res => {
                console.log(res.data);
      })
        }
    },[submit])

    return (
        <div>
            <form onSubmit={onSubmit}>
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value={user.username} onChange={handleChange}/>
                <br/>
                <label for="password">Password:</label>
                <input type="text" id="password" name="password" value={user.password} onChange={handleChange}/>
                <br/>
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" value={user.email} onChange={handleChange}/>
                <br/>
                <button>Submit</button>
            </form>
        </div>
    )
}