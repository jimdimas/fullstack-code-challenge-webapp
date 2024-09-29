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
import PublicRoute from './routes/PublicRoute';
import SolveProblem from './components/SolveProblem';
import Solution from './components/Solution';
import UploadProblem from './components/UploadProblem';
import AdminRoute from './routes/AdminRoute';
import Users from './components/Users';

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <Header/>
        <Routes>
          <Route path="/" element={<Home/>}/>
            <Route element={<PublicRoute />} >
              <Route path="/register" element={<Auth isRegistered={false}/>}/>
              <Route path="/login" element={<Auth isRegistered={true} />}/>
            </Route>
            <Route element={<AuthorizedRoute />}>
              <Route path="/profile/:username">
                <Route index element={<Profile />}/>
                <Route path="solutions" element={<Solution profile={true}/>}/>
              </Route>
              <Route path="/problem">
                <Route index element={<Problem/>}/>
                <Route path="upload" element={<UploadProblem/>}/>
                <Route path=":problemId">
                  <Route index element={<SolveProblem />}/>
                  <Route path="solutions" element={<Solution problem={true}/>}/>
                </Route>
              </Route>
              <Route element={<AdminRoute/>}>
                <Route path="/students" element={<Users mode="STUDENT"/>}/>
                <Route path="/supervisors" element={<Users mode="SUPERVISOR"/>}/>
              </Route>
            </Route>
            <Route path="/*" element={<NotFound/>}/>
          </Routes>
        </AuthProvider>
    </div>
  );
}

export default App;
