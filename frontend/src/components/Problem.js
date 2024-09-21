import React from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import SingleProblem from './SingleProblem'
import { useAuth } from '../hooks/AuthProvider'
import { useApi } from '../hooks/useApi'

export default function Problem(){
    const [problem,setProblem] = React.useState([])
    const auth = useAuth()
    const api = useApi()

    React.useEffect(()=>{
        const url = `${process.env.REACT_APP_API_URL}/problem`;
        const headers = {
                Authorization: auth.token
        }   

        api.request('get',url,headers).then((res)=>{
            console.log(res)
            setProblem(res.data)})
    },[])
    
    let elements;
    if (!api.isLoading) {
        elements = problem.map(item=>(
            <SingleProblem 
                key={item.problemId}
                username={item.uploadedBy.username}
                question={item.question}
                difficulty={item.difficulty}/>
        ))
    }

    return (
        <div>
            {api.isLoading?"Page is loading...":elements}
        </div>
    )
}