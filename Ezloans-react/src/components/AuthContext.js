import React, {useState, useContext} from 'react'

const AuthContext = React.createContext("")

export function useAuth() {
    return useContext(AuthContext);
}
export function AuthProvider(props) {
    const [userId, setUserId] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [isUser, setIsUser] = useState(true);
    const [userName, setUserName] = useState(null);
    const [toggle, setToggle] = useState();
    const value = {
        userId,
        setUserId,
        isLoggedIn,
        setIsLoggedIn,
        isUser,
        setIsUser,
        userName,
        setUserName,
        toggle,
        setToggle
    }
  return (
    <AuthContext.Provider value={value}> {props.children} </AuthContext.Provider>
  )
}