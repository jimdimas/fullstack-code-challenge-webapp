import {useContext , createContext , useState , useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'

const AuthContext = createContext();

const AuthProvider = ({children})=>{
    const [username,setUsername] = useState(localStorage.getItem('username') || '')
    const [token,setToken] = useState(localStorage.getItem('token') || '')
    const [role,setRole] = useState(localStorage.getItem('role') || '')
    const navigate = useNavigate()

    const access = async(credentials,isRegistered,setMessage)=>{
        try{
            const url = `${process.env.REACT_APP_API_URL}/auth/${isRegistered?'login':'register'}`
            const res = await axios.post(url,credentials)

            if (res.status===200){
                if (isRegistered){
                    setUsername(res.data.user.username)
                    setToken(res.data.token)
                    setRole(res.data.user.role)
                    localStorage.setItem('username',res.data.user.username)
                    localStorage.setItem('token',res.data.token)
                    localStorage.setItem('role',res.data.user.role)
                    navigate(`/profile/${res.data.user.username}`,{replace:true})

                } else {
                    setMessage(res.data.message)
                }
            } else {
                navigate('/',{replace:true})
            }
        } catch (err){
            if (err.response){
                if (err.response.status===400) setMessage(err.response.data.message)
                else console.log(err)
        }
     }
    }

    const logout = ()=>{
        localStorage.removeItem('username')
        localStorage.removeItem('token')
        localStorage.removeItem('role')
        setUsername('')
        setToken('')
        setRole('')
        navigate('/',{replace:true})
    }

    return (
        <AuthContext.Provider value={{ username , token , role , access , logout}}>
          {children}
        </AuthContext.Provider>)
}

export default AuthProvider;

export const useAuth = () => {
    return useContext(AuthContext);
};