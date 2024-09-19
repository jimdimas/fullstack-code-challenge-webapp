import React from "react";
import axios from "axios";
import { useParams,useNavigate, Link} from "react-router-dom";

export default function Profile(){
    const [user,setUser]=React.useState({
        username:"",
        email:"",
        role:"",
        organization:""
    })
    const {username} = useParams()
    const navigate=useNavigate()

    React.useEffect(()=>{
        const url=`http://localhost:8080/api/v1/user/${username}`;
        axios.get(url,{
                headers:{
                    "Authorization":sessionStorage.getItem("Bearer Token")
                }
            }
        ).then(res=>{setUser(res.data)}
        ).catch(err=>{
            console.log(err)
            navigate("/",{replace:true})
        })
    },[])

    return (
        <div>
            <ul>
                <li>Username: {user.username}</li>
                <li>Email: {user.email}</li>
                <li>Role: {user.role}</li>
                <li>Organization: {user.school}</li>
            </ul>
            <Link to="/problem">View all Problems</Link>
        </div>
    )
}