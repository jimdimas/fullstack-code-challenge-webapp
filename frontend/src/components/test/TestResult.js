import React from "react";
import { useApi } from "../../hooks/useApi";
import { useParams } from "react-router-dom";
import SingleTestResult from "./SingleTestResult";

export default function TestResult(){
    const [testResults,setTestResults] = React.useState()
    
    const api = useApi()
    const {username} = useParams()

    React.useEffect(()=>{
        const url = `/result?username=${username}`

        api.request('get',url,true).then(res=>{
            setTestResults(res.data)
        })
    },[])

    let testResultElements;
    if (!api.isLoading){
        testResultElements = testResults.map(testResult=>{
            return <SingleTestResult 
                key={testResult.test.title}
                title={testResult.test.title}
                solvedAt={testResult.solvedAt}
                result={testResult.result}
            />
        })
    }

    return (api.isLoading?<>...Loading</>:<div>
        <div class="container">
        <div class="row">
            <div class="col-xs-12">
            <div class="table-responsive" data-pattern="priority-columns">
                <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>Test Title</th>
                        <th data-priority="1">Date</th>
                        <th data-priority="2">Result</th>
                    </tr>
                </thead>
                <tbody>
                    {testResultElements}
                </tbody>
                </table>
            </div>
            </div>
        </div>
        </div>
    </div>)

}