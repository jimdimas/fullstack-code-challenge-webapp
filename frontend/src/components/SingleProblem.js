import React from "react"
import { Link } from "react-router-dom"
import { useAuth } from "../hooks/AuthProvider"

export default function SingleProblem(props){
    const auth = useAuth()

    return (
        <p>{props.question} , 
        {props.username} , 
        {props.difficulty} , 
        {auth.role==='STUDENT'?<Link to={`/problem/${props.problemId}`}> Solve</Link>:''} , 
        {<Link to={`/problem/${props.problemId}/solutions`}> Solutions</Link>}</p>
    )
}