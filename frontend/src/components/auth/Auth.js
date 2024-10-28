import React from 'react'
import { useAuth } from '../../hooks/AuthProvider'
import { Link } from 'react-router-dom'
import '../../css/Auth.css'

export default function Auth(props){
    const {isRegistered}=props
    const [credentials,setCredentials] = React.useState({
        username:"",
        password:"",
        email:"",
        school:"",
        confirmPassword:""
    })
    const [message,setMessage] = React.useState()
    const auth = useAuth()

    function checkUsername(username){
        return (username.length>=4 || username.length==0);
    }
    function checkEmail(email){
        const emailPattern = /^(|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,})$/;
        return emailPattern.test(email);
    }

    function checkPassword(pass){
        return (pass.length>=8 || pass.length==0);
    }

    function checkPasswordsMatch(pass1,pass2){
        return pass1==pass2;
    }

    React.useEffect(()=>{
        if (!props.isRegistered){
            let tempMessage=[];

            if (!checkUsername(credentials.username)) tempMessage.push('Username must be at least 4 characters long.')
            if (!checkEmail(credentials.email)) tempMessage.push('Email format is not valid (example@mail.com)');
            if (!checkPassword(credentials.password)) tempMessage.push('Password must be at least 8 characters long.');
            if (!checkPasswordsMatch(credentials.password,credentials.confirmPassword)) tempMessage.push('Passwords dont match');

            let msg = tempMessage.map(elem=>{
                return <>
                    {elem}
                    <br/>
                </>
            })

            setMessage(<div>
                {msg}
            </div>)
        } else {
            setMessage('')
        }
    },[credentials,isRegistered])
    
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
        auth.access(credentials,isRegistered,setMessage)
        return;
    }

    return (
        <div class="access">
            <div class="access-form">
                <form class="form" onSubmit={onSubmit}>
                    <fieldset class="username">
                        <input type="text" placeholder="Username" required={true} id="username" name="username" value={credentials.username} onChange={handleChange}/>
                        
                    </fieldset>
                    <fieldset class="password">
                        <input type="password" placeholder="Password" required={true} id="password" name="password" value={credentials.password} onChange={handleChange}/>
                    </fieldset>
                    { props.isRegistered?<div>
                        <p>Forgot your password? Click <Link to="/forgotPassword">here</Link> to reset it.</p>
                    </div>: //if user wants to register , show extra register fields
                    <>
                        <fieldset>
                            <input type="password" required={true} placeholder='Confirm Password' id="confirmPassword" name="confirmPassword" 
                            value={credentials.confirmPassword} onChange={handleChange}/>
                        </fieldset>
                        <fieldset class="email">
                            <input type="email" placeholder="Email" required={true} id="email" name="email" value={credentials.email} onChange={handleChange}/>
                        </fieldset>
                        <fieldset>
                            <input type="text" placeholder='School' required={true} id="school" name="school" value={credentials.school} onChange={handleChange}/>
                        </fieldset>
                    </>
                    }
                    <button type="submit" class="btn" id="submit">{props.isRegistered?"Login":"Register"}</button>
                </form>
                <div class="message" id="message">
                    {message}
                </div>
            </div>
        </div>
    )
}