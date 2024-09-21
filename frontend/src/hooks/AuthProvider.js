import {useContext , createContext , useState , useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'

const AuthContext = createContext();

const AuthProvider = ({children})=>{
    const [username,setUsername] = useState(localStorage.getItem('username') || '')
    const [token,setToken] = useState(localStorage.getItem('token') || '')
    const navigate = useNavigate()

    const access = async(credentials,isRegistered)=>{
        try{
            const url = `${process.env.REACT_APP_API_URL}/auth/${isRegistered?'login':'register'}`
            const res = await axios.post(url,credentials)

            if (res.status===200){
                setUsername(res.data.user.username)
                setToken(res.data.token)
                localStorage.setItem('username',res.data.user.username)
                localStorage.setItem('token',res.data.token)
                navigate(`/profile/${res.data.user.username}`,{replace:true})
                return;
            } else {
                navigate('/',{replace:true})
            }
        } catch (err){
            console.log(err)
            navigate('/',{replace:true})
        }
    }

    const logout = ()=>{
        localStorage.removeItem('username')
        localStorage.removeItem('token')
        setUsername('')
        setToken('')
        navigate('/',{replace:true})
    }

    return (
        <AuthContext.Provider value={{ username , token , access , logout}}>
          {children}
        </AuthContext.Provider>)
}

export default AuthProvider;

export const useAuth = () => {
    return useContext(AuthContext);
};