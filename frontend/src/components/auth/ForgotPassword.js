import React from "react";
import { useApi } from "../../hooks/useApi";
import '../../css/Auth.css'

export default function ForgotPassword(){
    const api = useApi()
    const [data,setData] = React.useState({
        email:""
    })
    const [message,setMessage] = React.useState()
    const [submit,setSubmit] = React.useState(false)
    
    function checkEmail(email){
        const emailPattern = /^(|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,})$/;
        return emailPattern.test(email);
    }

    React.useEffect(()=>{
        if (!checkEmail(data.email)) setMessage(<>{'Email format is not valid (example@mail.com)'}</>)
        else setMessage('')

        if (submit && checkEmail(data.email)){
            const url = `/auth/forgotPassword`
            setSubmit(false)
            api.request('post',url,false,data).then(res=>{
                if (res){
                    setMessage(res.data.message)
                }
            }).catch(err=>{
                if (err.response){
                    setMessage(err.response.data.message)
                    return;
                }
                setMessage('Something went wrong , try again.')
            })
        }
    },[submit,data])

    function handleChange(event){
        const {name,value} = event.target

        setData(oldData=>{
            return {
                ...oldData,
                [name]:value
            }
        })
    }

    function handleSubmit(e){
        e.preventDefault()
        setSubmit(true)
    }

    return (
        <div class="access">
            <h3>{'Enter your email below to reset your password:'}</h3>
            <div class="access-form">
                <form class="form" onSubmit={handleSubmit}>
                    <fieldset class="username">
                        <input type="text" placeholder="Email" required={true} id="email" name="email" value={data.email} onChange={handleChange}/>
                    </fieldset>
                    <button type="submit" class="btn">Reset</button>
                </form>
            </div>
            <p id="message">{message}</p>
        </div>)
        
}