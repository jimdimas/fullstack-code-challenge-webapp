import React from "react";

export default function SingleTestResult(props){

    function formatResult(result){
        return (result*100).toString().slice(0,3).replace('.','')+"%"
    }

    return ( 
    <tr>
        <td>{props.title}</td>
        <td>{props.solvedAt}</td>
        <td>{formatResult(props.result)}</td>
    </tr>
    )
}