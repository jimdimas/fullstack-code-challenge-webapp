import './App.css';
import Header from './components/Header';
import AuthProvider from './hooks/AuthProvider';
import Auth from './components/Auth';
import { Routes,Route } from 'react-router-dom';
import Home from './components/Home';
import Profile from './components/Profile';
import NotFound from './components/NotFounds';
import Problem from './components/Problem';
import AuthorizedRoute from './routes/AuthorizedRoute';

function App() {
  return (
    <div className="App">
      <Header/>
      <AuthProvider>
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/register" element={<Auth isRegistered={false}/>}/>
            <Route path="/login" element={<Auth isRegistered={true} />}/>
            <Route element={<AuthorizedRoute />}>
              <Route path="/profile/:username" element={<Profile/>}/>
              <Route path="/problem">
                <Route index element={<Problem/>}/>
              </Route>
            </Route>
            <Route path="/*" element={<NotFound/>}/>
          </Routes>
        </AuthProvider>
    </div>
  );
}

export default App;
