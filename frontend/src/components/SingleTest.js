import React from "react";

export default function SingleTest(props){
    return <>
        <tr>
            <td>{props.title}</td>
            <td>{props.points}</td>
        </tr>
    </>
}