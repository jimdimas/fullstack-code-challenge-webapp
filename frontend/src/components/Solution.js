import React from 'react'
import { useApi } from '../hooks/useApi'
import { useNavigate , useParams } from 'react-router-dom'
import SingleSolution from './SingleSolution'

export default function Solution(props){
    const [solutions,setSolutions] = React.useState()
    const navigate = useNavigate()
    const api = useApi()
    const params = useParams()

    React.useEffect(()=>{
        let uri;
        if (Object.hasOwn(props,'profile')){
            const username = params.username
            uri = `/student/${username}/solutions`
        } else if (Object.hasOwn(props,'problem')){
            const problemId = params.problemId
            uri = `/problem/${problemId}/solutions`
        } else navigate('/',{ replace:true })

        api.request('get',uri,true).then((res)=>{
            setSolutions(res.data)
        })
    },[])

    let result;
    
    if (!api.isLoading){
        result = solutions.map(solution=>{
        return <SingleSolution
            key={solution.solutionId}
            solutionId={solution.solutionId}
            showUser={Object.hasOwn(props,'problem')}
            {...solution}      
        />
    })}
    return (api.isLoading?<>Loading...</>:
        <div>
            {result}
        </div>
    )
}