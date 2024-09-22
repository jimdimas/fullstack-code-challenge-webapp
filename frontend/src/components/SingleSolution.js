import React from "react";
import { useAuth } from "../hooks/AuthProvider";
import { useApi } from "../hooks/useApi";

export default function SingleSolution(props){
    const auth = useAuth()
    const api = useApi()
    const [accept,setAccept] = React.useState(false)

    React.useEffect(()=>{
        if (accept){
            const uri=`/solution/${props.solutionId}`

            api.request('post',uri,true).then(res=>{
                console.log('accepted')
                setAccept(false)
            })
        }
    },[accept])

    return (
        <p> {!props.showUser?props.forProblem.question:''} 
        {props.content} 
        {props.showUser?props.solvedBy.username:''}
        {auth.role==='SUPERVISOR'?<button onClick={()=>{setAccept(true); window.location.reload()}}>Accept</button>:''}
        </p>
    )
}