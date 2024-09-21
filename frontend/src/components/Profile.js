import React from "react";
import axios from "axios";
import { useParams,useNavigate} from "react-router-dom";
import ProfileInfo from "./ProfileInfo";
import NotFound from "./NotFounds";
import { useAuth } from "../hooks/AuthProvider";
import { useApi } from "../hooks/useApi";

export default function Profile(){
    const auth = useAuth()
    const [userInfo,setUserInfo] = React.useState()
    const {username} = useParams()
    const api = useApi()

    React.useEffect(()=>{
        const url = `${process.env.REACT_APP_API_URL}/user/${username}`
    
        const headers = { Authorization: auth.token }
        api.request('get',url,headers).then(res=>{
            console.log(res)
            setUserInfo(res.data)})
        
    },[])

    return (api.isLoading?<>"Loading..."</>:<ProfileInfo {...userInfo}/>)
}