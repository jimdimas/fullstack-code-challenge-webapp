import React from "react";
import { useParams } from "react-router-dom";
import ProfileInfo from "./ProfileInfo";
import { useApi } from "../hooks/useApi";
import { useAuth } from "../hooks/AuthProvider";
import SingleProblem from "./SingleProblem";

export default function Profile(){
    const [userInfo,setUserInfo] = React.useState()
    const [suggestedProblem,setSuggestedProblem] = React.useState()
    const [problemRetrieved,setProblemRetrieved] = React.useState(false)
    const {username} = useParams()
    const api = useApi()
    const auth = useAuth()

    React.useEffect(()=>{
        const url = `/user/${username}`

        api.request('get',url,true).then(res=>{
            setUserInfo(res.data)})
        if (auth.role==='STUDENT'){
            const problemUrl = '/problem/suggested'
            api.request('get',problemUrl,true).then(res=>{
                if (res.data){
                    setSuggestedProblem(res.data)
                    setProblemRetrieved(true)
                }
            })
        }
    },[])

    let elements;
    if (!api.isLoading && problemRetrieved) {
        elements = 
            <SingleProblem 
                key={suggestedProblem.problemId}
                problemId={suggestedProblem.problemId}
                username={suggestedProblem.uploadedBy.username}
                question={suggestedProblem.question}
                difficulty={suggestedProblem.difficulty}
                points={suggestedProblem.points}
                />
        
    }

    return (api.isLoading?<>"Loading..."</>:<div>
        <ProfileInfo {...userInfo}/>
        {auth.role==='STUDENT'?<div>
            <br/>
                <h3>Based on your stats , we would recommend the following problem for you:</h3>
                <br/>
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
                                <th></th>
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
            </div>:''}
        </div>)
}