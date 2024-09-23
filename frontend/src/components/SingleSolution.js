import React from "react";
import { useAuth } from "../hooks/AuthProvider";
import { useApi } from "../hooks/useApi";

export default function SingleSolution(props){
    const auth = useAuth()
    const api = useApi()
    const [accept,setAccept] = React.useState()
    const [submit,setSubmit] = React.useState(false)

    React.useEffect(()=>{
        if (submit){
            const uri=`/solution/${props.solutionId}?result=${accept}`

            api.request('post',uri,true).then(res=>{
                console.log('accepted')
                setAccept(false)
            })
        }
    },[submit])

    return (
        <p> {!props.showUser?props.forProblem.question:''} 
        {props.content} 
        {props.showUser?props.solvedBy.username:''}
        {auth.role==='SUPERVISOR'?
        <><button onClick={()=>{setAccept(true); setSubmit(true); window.location.reload()}}>Accept</button>
        <button onClick={()=>{setAccept(false); setSubmit(true); window.location.reload()}}>Reject</button></>:''}
        </p>
    )
}