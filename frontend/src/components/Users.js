import React from "react";
import { useApi } from "../hooks/useApi";
import SingleUser from "./SingleUser";

export default function Users(props){
    const [users,setUsers] = React.useState()
    const api = useApi()

    React.useEffect(()=>{
        let uri = props.mode==="STUDENT"?"/student":"/supervisor"
        api.request('get',uri,true).then(res=>{
            setUsers(res.data)
        })
    },[])

    let usersRows;
    if (!api.isLoading){
        if (props.mode==="STUDENT"){
            usersRows = users.map(user=>{
                return <SingleUser 
                    key={user.username}
                    mode={props.mode}
                    username={user.username}
                    email={user.email}
                    school={user.school}
                    level={user.level}
                    ranking={user.ranking}
                />
            })
        } else {
            usersRows = users.map(user=>{
                return <SingleUser 
                    key={user.username}
                    mode={props.mode}
                    username={user.username}
                    email={user.email}
                    organization={user.organization}
                    expertise={user.expertise}
                />
            })
        }
    }

    return (api.isLoading?<>Loading...</>:
        <div>
            <div class="container">
            <div class="row">
                <div class="col-xs-12">
                <div class="table-responsive" data-pattern="priority-columns">
                    <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Email</th>
                            {props.mode==="STUDENT"?<>
                                <th>School</th>
                                <th>Level</th>
                                <th>Ranking</th>
                            </>:
                            <>
                                <th>Organization</th>
                                <th>Expertise</th>
                            </>}
                            
                        </tr>
                    </thead>
                    <tbody>
                        {usersRows}
                    </tbody>
                    </table>
                </div>
                </div>
            </div>
            </div>
        </div>
    )
}
