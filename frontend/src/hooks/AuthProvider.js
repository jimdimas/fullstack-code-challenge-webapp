import {useContext , createContext , useState } from 'react'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'

const AuthContext = createContext();

const AuthProvider = ({children})=>{
    const [user,setUser] = useState()
    const [token,setToken] = useState(localStorage.getItem("token") || null)
    const navigate = useNavigate()

    const access = async(credentials,isRegistered)=>{
        try{
            const url = `${process.env.REACT_APP_API_URL}/auth/${isRegistered?'login':'register'}`
            const res = await axios.post(url,credentials)

            if (res.status===200){
                setUser(res.data.user)
                setToken(res.data.token)
                console.log(res.data)
                localStorage.setItem("token",token)
                navigate(`/profile/${user.username}`,{replace:true})
                return;
            } else {
                navigate('/',{replace:true})
            }
        } catch (err){
            console.log(err)
            navigate('/',{replace:true})
        }
    }

    return (
        <AuthContext.Provider value={{ user , token , access}}>
          {children}
        </AuthContext.Provider>)
}

export default AuthProvider;

export const useAuth = () => {
    return useContext(AuthContext);
};