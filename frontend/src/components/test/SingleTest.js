import React from "react";
import { Link } from "react-router-dom";

export default function SingleTest(props){
    return <>
        <tr>
            <td>{props.title}</td>
            <td>{props.points}</td>
            <td><Link to={`/test/${props.title}`}> Solve </Link></td>
        </tr>
    </>
}