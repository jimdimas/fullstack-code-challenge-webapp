import React from "react";
import { useApi } from '../hooks/useApi'
import { useAuth } from "../hooks/AuthProvider";
import { useNavigate } from "react-router-dom";

export default function UploadTest(){
    const [test,setTest] = React.useState({
        title:'',
        questions:[],
        points:''
    })
    const [question,setQuestion] = React.useState({
        content:"",
        answers:["","",""],
        correctAnswer:""
})
    const [submit,setSubmit] = React.useState(false)
    const api = useApi()
    const auth=useAuth()
    const navigate=useNavigate()

    React.useEffect(()=>{
        if (submit){
            const url = '/test'
            api.request('post',url,true,test).then(res=>{
                navigate(`/profile/${auth.username}`,{ replace: true })
            })
        }
    },[submit])

    function handleAddQuestion(e){
        e.preventDefault()
        setTest(prevTest=>{
            return {
                ...prevTest,
                questions:[
                    ...prevTest.questions,
                    question
                ]
            }
        })
        console.log(test)
    }

    function handleQuestionChange(e){
        const {name,value,type,checked} = e.target
        if (name=="content"){
            setQuestion(oldQuestion=>{
                return {
                    ...oldQuestion,
                    [name]:value
                }
            })
        } else if (name.includes("textbox-")){
            const index=Number(name.replace("textbox-",''))
            const newAnswers = question.answers.map((answer,i)=>{
                if (index==i){
                    return value
                }
                return answer
            })
            setQuestion(oldQuestion=>{
                return {
                    ...oldQuestion,
                    answers:newAnswers
                }
            })
        } else if (type=="radio"){
            setQuestion(oldQuestion=>{
                return {
                    ...oldQuestion,
                    [name]:value
                }
            })
        }
    }

    function handleTestChange(e){
        const {name,value,type,checked} = e.target
        setTest(oldTest=>{
            return {
                ...oldTest,
                [name]:value
            }
        })
    }

    function handleSubmit(e){
        e.preventDefault()
        setSubmit(true)
    }

    const questionElements = test.questions.map(question=>{
        return (<>
            <p>Question:{question.content}</p>
            <br/>
            <input type="radio" name={question.content} id={question.answers[0]} value={question.answers[0]}/>
            <label htmlFor={question.answers[0]}> {question.answers[0]} </label>
            <input type="radio" name={question.content} id={question.answers[1]} value={question.answers[1]}/>
            <label htmlFor={question.answers[1]}> {question.answers[1]} </label>
            <input type="radio" name={question.content} id={question.answers[2]} value={question.answers[2]}/>
            <label htmlFor={question.answers[2]}> {question.answers[2]} </label>
            <br/><br/>
        </>)
    })

    return (<div>
        <label htmlFor="title">Test Title:</label>
        <input type="textbox" id="title" name="title" value={test.title} onChange={handleTestChange}></input>
        <br/><br/>Questions:<br/><br/>
        {questionElements}
        <form onSubmit={handleAddQuestion}>
            <label htmlFor="content">Question:</label>
            <input type="textbox" id="content" name="content" value={question.content} onChange={handleQuestionChange}></input>
            <ul>
                <li>
                    <label htmlFor="textbox-0">1st Answer:</label>
                    <input type="textbox" id="textbox-0" name="textbox-0" value={question.answers[0]} onChange={handleQuestionChange}></input>
                    <input type="radio" id="correctAnswer" name="correctAnswer" value={0} checked={question.correctAnswer=='0'} onChange={handleQuestionChange}></input>
                </li>
                <li>
                    <label htmlFor="textbox-1">2nd Answer:</label>
                    <input type="textbox" id="textbox-1" name="textbox-1" value={question.answers[1]} onChange={handleQuestionChange}></input>
                    <input type="radio" id="correctAnswer" name="correctAnswer" value={1} checked={question.correctAnswer=='1'} onChange={handleQuestionChange}></input>
                </li>
                <li>
                <label htmlFor="textbox-2">3rd Answer:</label>
                    <input type="textbox" id="textbox-2" name="textbox-2" value={question.answers[2]} onChange={handleQuestionChange}></input>
                    <input type="radio" id="correctAnswer" name="correctAnswer" value={2} checked={question.correctAnswer=='2'} onChange={handleQuestionChange}></input>
                </li>
                <li>
                    <button>Add</button>
                </li>
            </ul>
            <br/><br/>
            <label htmlFor="points">Points:</label>
            <input type="number" id="points" name="points" min="0" max="500" value={test.points} onChange={handleTestChange}></input>
            <br/><br/>
            <button onClick={handleSubmit}>Submit</button>
        </form>
    </div>)
}