import React from "react";
import axios from "axios";
import { useParams,useNavigate} from "react-router-dom";
import ProfileInfo from "./ProfileInfo";
import NotFound from "./NotFounds";

export default function Profile(){
    const [user,setUser]=React.useState({
        username:"",
        email:"",
        role:"",
        organization:""
    })
    const [authenticated,setAuthenticated] = React.useState(false)
    const [userExists,setUserExists] = React.useState(false)
    const {username} = useParams()
    const navigate=useNavigate()

    React.useEffect(()=>{
        const url=`http://localhost:8080/api/v1/user/${username}`;
        axios.get(url,{
                headers:{
                    "Authorization":localStorage.getItem("token")
                }
            }
        ).then(res=>{
            if (res.status===200 || res.status===400) 
                {
                    setAuthenticated(true)
                    if (res.status==200){
                        setUserExists(true)
                        setUser(res.data)
                    }
                } 
        }
        ).catch(err=>{
            if (err.status===400){
                setAuthenticated(true)
                return
            }
            console.log(err)
        })
    },[])

    return authenticated?(userExists?<ProfileInfo {...user}/>:<NotFound message="user"/>):navigate("/",{replace:true})
}