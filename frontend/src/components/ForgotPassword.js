import React from "react";
import { useApi } from "../hooks/useApi";
import '../css/Auth.css'

export default function ForgotPassword(){
    const api = useApi()
    const [data,setData] = React.useState({
        email:""
    })
    const [message,setMessage] = React.useState()
    const [submit,setSubmit] = React.useState(false)

    React.useEffect(()=>{
        if (submit){
            const url = `/auth/forgotPassword`
            api.request('post',url,false,data).then(res=>{
                setMessage(res.data.message)
            }).catch(err=>{
                if (err.response){
                    setMessage(err.response.data.message)
                    return;
                }
                setMessage('Something went wrong , try again.')
            })
        }
    },[submit])

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
                    <button type="submit" class="btn">Submit</button>
                </form>
            </div>
            <> {message}</>
        </div>)
        
}