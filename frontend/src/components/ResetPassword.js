import React from "react";
import { useApi } from "../hooks/useApi";
import { useSearchParams } from "react-router-dom";
import '../css/Auth.css'

export default function ResetPassword(){
    const [data,setData] = React.useState({
        password:"",
        confirmPassword:""
    })
    const [submit,setSubmit] = React.useState(false)
    const [message,setMessage] = React.useState()
    const [searchParams,setSearchParams] = useSearchParams()
    const api = useApi()

    React.useEffect(()=>{
        let tempMessage=[]

        if (!checkPassword(data.password)) tempMessage.push('Password must be at least 8 characters long.')
        if (!checkPasswordsMatch(data.password,data.confirmPassword)) tempMessage.push('Passwords must match.')
        
        if (tempMessage.length===0){
            setMessage('')
            if (submit && data.password.length>=8){
                const url = '/auth/resetPassword'
                const token = searchParams.get('token')
                setSubmit(false)
                api.request('post',url,false,{
                    password:data.password,
                    token:token
                }).then(res=>{
                    setMessage(<div>{res.data.message}</div>)
                }).catch(err=>{
                    if (err.response){
                        setMessage(err.response.data.message)
                        return;
                    }
                    setMessage(<div>{'Something went wrong , try again'}</div>)
                })
            }
        } else {
            let msg = tempMessage.map(elem=>{
                return <>
                    {elem}
                    <br/>
                </>
            })
    
            setMessage(<div>
                {msg}
            </div>)
        }
    },[submit,data])

    function checkPassword(pass){
        return (pass.length>=8 || pass.length==0);
    }

    function checkPasswordsMatch(pass1,pass2){
        return pass1==pass2;
    }

    function handleChange(e){
        const {name,value} = e.target
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

    return (<div class="access">
        <h3>{'Enter your new password below to reset your password:'}</h3>
        <div class="access-form">
            <form class="form" onSubmit={handleSubmit}>
                <fieldset class="password">
                    <input type="password" placeholder="Password" required={true} id="password" name="password" value={data.password} onChange={handleChange}/>
                </fieldset>
                <fieldset class="password">
                    <input type="password" placeholder="Confirm Password" required={true} id="confirmPassword" name="confirmPassword" value={data.confirmPassword} onChange={handleChange}/>
                </fieldset>
                <button type="submit" class="btn">Submit</button>
            </form>
        </div>
        {message}
    </div>)
}