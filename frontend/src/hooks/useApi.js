import { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import { useAuth } from './AuthProvider'

export function useApi(){
    const [isLoading,setIsLoading] = useState(true)
    const auth = useAuth()
    const navigate = useNavigate()
    const url = `${process.env.REACT_APP_API_URL}`
    const headers = {
        Authorization:auth.token
    }
    const request = async (method,uri,includeToken=false,data=null) => {
        try{
            const _response = await axios({
                method:method,
                url:`${url}${uri}`,
                headers:includeToken?headers:{},
                data:data
            })
            setIsLoading(false)
            return _response
        } catch (err){
            if (err.response){
                if (err.response.status===403){
                    auth.logout()
                    return
                } else {
                    return err.response
                }
            } else {
                navigate('/',{replace:true})
            }
        }
    }

    return {isLoading , request}
}