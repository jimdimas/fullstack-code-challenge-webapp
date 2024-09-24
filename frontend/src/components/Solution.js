import React from 'react'
import { useApi } from '../hooks/useApi'
import { useNavigate , useParams } from 'react-router-dom'
import SingleSolution from './SingleSolution'
import { useAuth } from '../hooks/AuthProvider'
import '../css/table.css'

export default function Solution(props){
    const [solutions,setSolutions] = React.useState()
    const navigate = useNavigate()
    const api = useApi()
    const params = useParams()
    const auth = useAuth()

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

    let elements;
    
    if (!api.isLoading){
        elements = solutions.map(solution=>{
        return <SingleSolution
            key={solution.solutionId}
            solutionId={solution.solutionId}
            showUser={Object.hasOwn(props,'problem')}
            {...solution}      
        />
    })}
    return (api.isLoading?<>Loading...</>:
        <div>
            <div class="container">
            <div class="row">
                <div class="col-xs-12">
                <div class="table-responsive" data-pattern="priority-columns">
                    <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            {Object.hasOwn(props,'profile')?<th>Question</th>:''}
                            <th>Solution</th>
                            <th data-priority="1">Uploaded at</th>
                            {Object.hasOwn(props,'problem')?<th data-priority="2">User</th>:''}
                            {auth.role==='SUPERVISOR'?<><th></th><th></th></>:''}
                        </tr>
                    </thead>
                    <tbody>
                        {elements}
                    </tbody>
                    </table>
                </div>
                </div>
            </div>
            </div>
        </div>
    )
}