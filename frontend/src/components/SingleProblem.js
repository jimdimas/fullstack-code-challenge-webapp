import React from "react"
import { Link } from "react-router-dom"
export default function SingleProblem(props){

    return (
        <p>{props.question} , {props.username} , {props.difficulty} , {<Link to={`/problem/${props.problemId}`}> View</Link>} , {<Link to={`/problem/${props.problemId}/solutions`}> Solutions</Link>}</p>
    )
}