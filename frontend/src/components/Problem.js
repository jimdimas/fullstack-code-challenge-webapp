import React from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import SingleProblem from './SingleProblem'

export default function Problem(){
    const [problem,setProblem] = React.useState([])
    const navigate = useNavigate()

    React.useEffect(()=>{
        const url = `${process.env.REACT_APP_API_URL}/problem`;

        axios.get(url,{
            headers:{
                Authorization:localStorage.getItem("token")
            }
        })
        .then(res=>{
            if (res.status==200){
                setProblem(res.data)
                
            } else {
                return navigate("/",{replace:true})
            }
        })
        .catch(err=>{
            console.log(err)
            return navigate("/",{replace:true})
        })    
    },[])

    const elements = problem.map(item=>(
        <SingleProblem 
        key={item.id}
        username={item.uploadedBy.username}
        question={item.question}
        difficulty={item.difficulty}
        />))

    return (
        <div>
            {elements}
        </div>
    )
}