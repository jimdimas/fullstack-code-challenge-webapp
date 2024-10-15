import React from "react";
import { useParams } from "react-router-dom";
import { useApi } from "../hooks/useApi";

export default function SolveTest(){
    const [test,setTest] = React.useState()
    const [answers,setAnswers] = React.useState()
    const {title} = useParams()
    const api = useApi()

    React.useEffect(()=>{
        const url = `/test/${title}`

        api.request('get',url,true).then(res=>{
            setTest(res.data)
        })
    },[])

    function handleChange(e){
        const {name,type,value,checked} = e.target
        console.log(name)
        setAnswers(oldAnswers=>{
            return {
                ...oldAnswers,
                [name]:value
            }
        })
    }

    let questions;
    if (!api.isLoading){
        questions = test.questions.map(question=>{
            return (<>
                <p>{question.content}</p>
                <br/>
                <input type="radio" name={question.content} id={question.answers[0]} value={question.answers[0]} onChange={handleChange}/>
                <label htmlFor={question.answers[0]}> {question.answers[0]} </label>
                <input type="radio" name={question.content} id={question.answers[1]} value={question.answers[1]} onChange={handleChange}/>
                <label htmlFor={question.answers[1]}> {question.answers[1]} </label>
                <input type="radio" name={question.content} id={question.answers[2]} value={question.answers[2]} onChange={handleChange}/>
                <label htmlFor={question.answers[2]}> {question.answers[2]} </label>
                <br/><br/>
            </>)
        })
    }

    return (api.isLoading?<>...Loading</>:<div>
        <br/>
        <h2>{test.title}</h2>
        <br/>
        <form>
            {questions}
            <button type="submit" class="button-36">Submit</button>
        </form>
    </div>)
}