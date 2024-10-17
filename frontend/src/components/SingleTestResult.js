import React from "react";

export default function SingleTestResult(props){
    return ( 
    <tr>
        <td>{props.title}</td>
        <td>{props.solvedAt}</td>
        <td>{props.result*100+"%"}</td>
    </tr>
    )
}