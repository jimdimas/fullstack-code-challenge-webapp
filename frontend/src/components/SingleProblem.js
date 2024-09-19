import React from "react"

export default function SingleProblem(props){

    return (
        <p>{props.question} , {props.username} , {props.difficulty} </p>
    )
}