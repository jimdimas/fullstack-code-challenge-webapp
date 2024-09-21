import React from 'react'
import SingleProblem from './SingleProblem'
import { useApi } from '../hooks/useApi'

export default function Problem(){
    const [problem,setProblem] = React.useState([])
    const api = useApi()

    React.useEffect(()=>{
        const url = '/problem';   

        api.request('get',url,true).then((res)=>{
            setProblem(res.data)})
    },[])
    
    let elements;
    if (!api.isLoading) {
        elements = problem.map(item=>(
            <SingleProblem 
                key={item.problemId}
                problemId={item.problemId}
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