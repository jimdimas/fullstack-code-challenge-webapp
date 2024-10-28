import React from "react";
import { useAuth } from "../../hooks/AuthProvider";
import { useApi } from "../../hooks/useApi";
import { useNavigate } from "react-router-dom";

export default function UploadProblem(){
    const [problem,setProblem] = React.useState({
        question:"",
        difficulty:"",
        points:0
    })
    const [submit,setSubmit] = React.useState(false)
    const auth = useAuth()
    const api = useApi()
    const navigate = useNavigate()

    React.useEffect(()=>{
        if (auth.role!=='SUPERVISOR'){
            navigate('/',{replace:true})
        }

        if (submit){
            const uri = '/problem'
            api.request('post',uri,true,problem).
            then(res=>navigate(`/profile/${auth.username}`,{ replace:true }))
        }
    },[submit])

    function handleChange(e){
        const {name,value,type,checked} = e.target
        console.log(name)
        console.log(value)
        setProblem(oldProblem=>{
            return {
                ...oldProblem,
                [name]:value
            }
        })
    }

    function handleSubmit(e){
        e.preventDefault()
        setSubmit(true)
    }

    return (<div>
        <br/>
        <form onSubmit={handleSubmit}>
            <h3>Question: </h3>
            <br/>
            <textarea placeholder="Question" id="question" name="question" rows="4" cols="50" value={problem.question} onChange={handleChange}></textarea>
            <br/><br/>
            <label for="difficulty">Choose difficulty: </label>
            <select id="difficulty" value={problem.difficulty}  onChange={handleChange} name="difficulty">
                <option value="">---</option>
                <option value="Beginner">Beginner</option>
                <option value="Amateur">Amateur</option>
                <option value="Experienced">Experienced</option>
                <option value="Expert">Expert</option>
            </select>
            <br/><br/>
            <label for="points">Points: </label>
            <input type="number" id="points" name="points" value={problem.points} onChange={handleChange}></input>
            <br/><br/>
            <button type="submit">Submit</button>
        </form>
    </div>)
}