import React from "react";

export default function SingleSolution(props){

    return (
        <p> {!props.showUser && props.forProblem.question} {props.content} {props.showUser && props.solvedBy.username}</p>
    )
}