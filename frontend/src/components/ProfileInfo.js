import React from 'react'
import '../css/profile.css'
import { useAuth } from '../hooks/AuthProvider'

export default function ProfileInfo(props){
    const auth = useAuth()
    return (
        <div class="container">
            <ul class="profile-ul">
                <li class="profile-li">Username: {props.username}</li>
                <li class="profile-li">Email: {props.email}</li>
                <li class="profile-li" id="role">Role: {props.role}</li>
                {<li class="profile-li">{auth.role==='SUPERVISOR'?'Organization':'School'}: {props.school}</li>}
                {auth.role==='STUDENT'?<li class="profile-li">Ranking: {props.ranking}</li>:''}
            </ul>
        </div>
    )
}