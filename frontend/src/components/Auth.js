import React from 'react'
import { useAuth } from '../hooks/AuthProvider'
import '../css/Auth.css'
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
        <div class="access">
            <div class="access-form">
                <form class="form" onSubmit={onSubmit}>
                    <fieldset class="username">
                        <input type="text" placeholder="Username" id="username" name="username" value={credentials.username} onChange={handleChange}/>
                    </fieldset>
                    { props.isRegistered?'':
                        <fieldset class="email">
                            <input type="email" placeholder="Email" id="email" name="email" value={credentials.email} onChange={handleChange}/>
                        </fieldset>
                    }
                    <fieldset class="password">
                        <input type="password" placeholder="Password" id="password" name="password" value={credentials.password} onChange={handleChange}/>
                    </fieldset>
                    <button type="submit" class="btn" id="submit">{props.isRegistered?"Login":"Register"}</button>
                </form>
            </div>
        </div>
    )
}