import { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

export function useApi(){
    const [isLoading,setIsLoading] = useState(false)
    const request = async (method,url,headers=null,data=null) => {
        setIsLoading(true)

        try{
            const _response = await axios({
                method:method,
                url:url,
                headers:headers,
                data:data
            })
            setIsLoading(false)
            return _response
        } catch (err){
            console.log(err)
            return err
        }
    }

    return {isLoading , request}
}