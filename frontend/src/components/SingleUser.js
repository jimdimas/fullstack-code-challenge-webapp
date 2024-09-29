import React from "react";

export default function SingleUser(props){
    return (
        <tr>
            <td>{props.username}</td>
            <td>{props.email}</td>
            {props.mode==="STUDENT"?<>
                <td>{props.school}</td>
                <td>{props.level}</td>
                <td>{props.ranking}</td>
            </>:
            <>
                <td>{props.organization}</td>
                <td>{props.expertise}</td>
            </>
            }
            
        </tr>
    )
}