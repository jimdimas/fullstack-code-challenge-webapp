import React from 'react'
import SingleProblem from './SingleProblem'
import { useApi } from '../../hooks/useApi'
import { useAuth } from '../../hooks/AuthProvider'
import '../../css/table.css'

export default function Problem(){
    const [problem,setProblem] = React.useState([])
    const api = useApi()
    const auth = useAuth()

    React.useEffect(()=>{
        const url = '/problem';   

        api.request('get',url,true).then((res)=>{
            setProblem(res.data)
        })
    },[])
    
    let elements;
    if (!api.isLoading) {
        elements = problem.map(item=>(
            <SingleProblem 
                key={item.problemId}
                problemId={item.problemId}
                username={item.uploadedBy.username}
                question={item.question}
                difficulty={item.difficulty}
                points={item.points}
                />
        ))
    }

    return (
        <div>
            <div class="container">
            <div class="row">
                <div class="col-xs-12">
                <div class="table-responsive" data-pattern="priority-columns">
                    <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Question</th>
                            <th data-priority="1">Difficulty</th>
                            <th data-priority="2">Points</th>
                            {auth.role==='STUDENT'?<th></th>:
                                <>
                                    <th></th><th></th>
                                </>
                            }
                            <th></th>
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