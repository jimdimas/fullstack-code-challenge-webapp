import React from 'react'

export default function ProfileInfo(props){
    return (
        <div>
            <ul>
                <li>Username: {props.username}</li>
                <li>Email: {props.email}</li>
                <li>Role: {props.role}</li>
                <li>Organization: {props.school}</li>
            </ul>
        </div>
    )
}