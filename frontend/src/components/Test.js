import React from "react";
import {useApi} from '../hooks/useApi'
import SingleTest from "./SingleTest";

export default function Test(){
    const [tests,setTests] = React.useState()

    const api = useApi()

    React.useEffect(()=>{
        const url = "/test"
        api.request('get',url,true).then(res=>{
            setTests(res.data)
        })
    },[])

    let testElements;
    if (!api.isLoading){
        testElements = tests.map(test=>{
            return <SingleTest 
                key={test.title}
                title={test.title}
                points={test.points}
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
                        <th>Title</th>
                        <th data-priority="1">Points</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {testElements}
                </tbody>
                </table>
            </div>
            </div>
        </div>
        </div>
    </div>)
}