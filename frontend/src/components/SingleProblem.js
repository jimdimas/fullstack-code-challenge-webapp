import React from "react"
import { Link, useNavigate } from "react-router-dom"
import { useAuth } from "../hooks/AuthProvider"
import '../css/table.css'

export default function SingleProblem(props){
    const auth = useAuth()
    const navigate = useNavigate()
    return (<tr>
            <td>{props.question}</td>
            <td>{props.difficulty} </td>
            <td>{props.points} </td> 
            {auth.role==='STUDENT'?<td><Link to={`/problem/${props.problemId}`}> Solve </Link></td>:''}
            <td>{<Link to={`/problem/${props.problemId}/solutions`}> Solutions</Link>}</td>
        </tr>
    )
}