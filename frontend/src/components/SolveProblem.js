import React from "react";
import { useAuth } from '../hooks/AuthProvider'
import { useApi } from "../hooks/useApi"; 
import { useParams , useNavigate } from "react-router-dom";

export default function SolveProblem(){
    const auth = useAuth()
    const api = useApi()
    const navigate = useNavigate()
    const { problemId } = useParams()

    const [problem,setProblem] = React.useState()
    const [solution,setSolution] = React.useState({
        problemId:problemId || '',
        solvedBy:{
            username:auth.username || ''
        },
        content:''
    })
    const [submit,setSubmit] = React.useState(false)

    function onSubmit(event){
        event.preventDefault()
        setSubmit(true)
    }

    function handleChange (event){
        const {name,value,type,checked} = event.target

        setSolution(oldSolution=>{
            return {
                ...oldSolution,
                [name]:value
            }
        })
    }

    React.useEffect(()=>{
        let uri=`/problem/${problemId}`

        if (!submit){
            api.request('get',uri,true).then(res=>{
                setProblem(res.data)
                return;
            })
        } else {
            uri=`${uri}/solutions`
            api.request('post',uri,true,solution).then(res=>{
                navigate(`/profile/${auth.username}`,{replace:true})
                return;
            })
        }
        
    },[submit])

    return (api.isLoading?<>Loading...</>:
        <div>
            <form onSubmit={onSubmit}>
                <br></br>
                <h2>{problem.question}</h2>
                <textarea id="content" name="content" type="text" onChange={handleChange} value={solution.content} rows="4" cols="50"/>
                <br/>
                <button>Submit Solution</button>
            </form>
        </div>
    )

}