import React from "react";
import { useSearchParams } from "react-router-dom";
import {useApi} from '../hooks/useApi'

export default function VerifyEmail(){
    const [searchParams,setSearchParams] = useSearchParams()
    const api = useApi()
    const [message,setMessage] = React.useState()

    React.useEffect(()=>{
        const token = searchParams.get('token')
        const url = `/auth/verifyEmail?token=${token}`

        api.request('get',url,false).then(res=>{
            if (res.status===200){
                setMessage(res.data.message)
            }
        }).catch(()=>{
            setMessage('Something went wrong with the email verification , refresh the page or try to create an account again.')
        }).finally(()=>{})
    },[])

    return (<div>
        {message}
    </div>)
}
