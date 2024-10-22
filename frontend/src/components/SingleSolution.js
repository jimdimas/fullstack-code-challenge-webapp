import React from "react";
import { useAuth } from "../hooks/AuthProvider";
import { useApi } from "../hooks/useApi";
import { Link , useNavigate } from "react-router-dom";
import '../css/table.css'

export default function SingleSolution(props){
    const auth = useAuth()
    const api = useApi()
    const navigate = useNavigate()
    const [accept,setAccept] = React.useState()
    const [submit,setSubmit] = React.useState(false)

    React.useEffect(()=>{
        if (submit){
            const uri=`/solution/${props.solutionId}?result=${accept}`

            api.request('post',uri,true).then(res=>{
                console.log('accepted')
                setAccept(false)
                navigate(`/profile/${auth.username}`,{replace:true})
            })
        }
    },[submit])


    return (
        <tr>
        {!props.showUser?<td>{props.forProblem.question}</td>:''} 
        <td>{props.content} </td>
        <td>{props.solvedAt}</td>
        {auth.role==='STUDENT'?<>
            <td>{props.points}</td>
            <td>{props.isAccepted}</td>
            </>:''}
        {props.showUser?<td>{props.solvedBy.username}</td>:''}
        {auth.role==='SUPERVISOR'?
        <><td><Link onClick={()=>{setAccept(true); setSubmit(true);}}>Accept</Link></td>
        <td><Link onClick={()=>{setAccept(false); setSubmit(true);}}>Reject</Link></td></>:''}
        </tr>
    )
}