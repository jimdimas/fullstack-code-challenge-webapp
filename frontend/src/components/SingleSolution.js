import React from "react";
import { useAuth } from "../hooks/AuthProvider";
import { useApi } from "../hooks/useApi";
import { Link } from "react-router-dom";

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
        <tr>
        {!props.showUser?props.forProblem.question:''} 
        <td>{props.content} </td>
        <td>{props.solvedAt}</td>
        {props.showUser?<td>{props.solvedBy.username}</td>:''}
        {auth.role==='SUPERVISOR'?
        <><td><Link onClick={()=>{setAccept(true); setSubmit(true); window.location.reload()}}>Accept</Link></td>
        <td><Link onClick={()=>{setAccept(false); setSubmit(true); window.location.reload()}}>Reject</Link></td></>:''}
        </tr>
    )
}