import React from "react";
import { useParams } from "react-router-dom";
import ProfileInfo from "./ProfileInfo";
import NotFound from "./NotFounds";
import { useApi } from "../hooks/useApi";

export default function Profile(){
    const [userInfo,setUserInfo] = React.useState()
    const {username} = useParams()
    const api = useApi()

    React.useEffect(()=>{
        const url = `/user/${username}`

        api.request('get',url,true).then(res=>{
            setUserInfo(res.data)})
    },[])

    return (api.isLoading?<>"Loading..."</>:<ProfileInfo {...userInfo}/>)
}