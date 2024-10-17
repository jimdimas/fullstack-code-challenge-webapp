import React from "react";
import { useParams , useNavigate } from "react-router-dom";
import { useApi } from "../hooks/useApi";
import Swal from "sweetalert2";
export default function SolveTest(){
    const [test,setTest] = React.useState()
    const [answers,setAnswers] = React.useState()
    const [testResult,setTestResult] = React.useState()
    const [submit,setSubmit] = React.useState(false)
    const {title} = useParams()
    const navigate = useNavigate()
    const api = useApi()

    React.useEffect(()=>{
        let url;
        
        if (submit){
            url=`/result/${title}`
            api.request('post',url,true,{
                result:testResult
            }).then(res=>{
                if (res.status===200){
                    navigate('/test',{ replace:true })
                }
            }
            )
        } else {
            url=`/test/${title}`
            api.request('get',url,true).then(res=>{
                setTest(res.data)
            })
        }
    },[submit])

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

    function handleSubmit(e){
        e.preventDefault()
        checkTestResults()
    }

    function checkTestResults(){
        let correct=0
        test.questions.forEach(element => {
            if (answers[element.content]===element.answers[element.correctAnswer]) {
                correct+=1;
            }
        });
        if (correct/test.questions.length>=0.5){
            /*Swal.fire(
                'Success! :)',
                `You have passed the test with ${correct} out of ${test.questions.length} correct answers!`,
                'success'
            )
            */
            setTestResult(correct/test.questions.length)
            setSubmit(true)
        } else {
            /*
            Swal.fire(
                'Fail! :(',
                `You have not passed the test , you only had ${correct} out of ${test.questions.length} correct answers...`,
                'error'
            )
            */
            navigate('/test',{replace:true})
        }
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
        <form onSubmit={handleSubmit}>
            {questions}
            <button type="submit" class="button-36">Submit</button>
        </form>
    </div>)
}