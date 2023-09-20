import React, {useState, useContext} from 'react'

const AuthContext = React.createContext()

export function useAuth() {
    return useContext(AuthContext);
}
export function AuthProvider(props) {
    const [authUser, setAuthUser] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [isUser, setIsUser] = useState(false);

    // useEffect(() => {
        
    // });
    const value = {
        authUser,
        setAuthUser,
        isLoggedIn,
        setIsLoggedIn,
        isUser,
        setIsUser
    }
  return (
    <AuthContext.Provider value={value}> {props.children} </AuthContext.Provider>
  )
}

// export default AuthContext