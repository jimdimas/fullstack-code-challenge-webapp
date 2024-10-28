import React from "react"
import { useNavigate } from "react-router-dom"
import { useAuth } from "../../hooks/AuthProvider"
import '../../css/table.css'

export default function SingleProblem(props){
    const auth = useAuth()
    const navigate = useNavigate()

    function goToSolve(e){
        navigate(`/problem/${props.problemId}`,{ replace:true })
    }

    function goToSolutions(e){
        navigate(`/problem/${props.problemId}/solutions`,{ replace:true })
    }

    return (<tr>
            <td>{props.question}</td>
            <td>{props.difficulty} </td>
            <td>{props.points} </td> 
            {auth.role==='STUDENT'?<td><button class="table-btn edit-btn" onClick={goToSolve}>Solve</button></td>:<>
                <td><button class="table-btn edit-btn">Edit</button></td>
                <td><button class="table-btn delete-btn">Delete</button></td>
            </>}
            <td><button class="table-btn general-btn" onClick={goToSolutions}>Solutions</button></td>
        </tr>
    )
}