import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Auth from './components/Auth';
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import Home from './components/Home';
import Profile from './components/Profile';
import NotFound from './components/NotFounds';
import Problem from './components/Problem';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/register" element={<Auth isRegistered={false}/>}/>
          <Route path="/login" element={<Auth isRegistered={true} />}/>
          <Route path="/profile/:username" element={<Profile/>}/>
          <Route path="/problem">
            <Route index element={<Problem/>}/>
          </Route>
          <Route path="/*" element={<NotFound/>}/>
        </Routes>
      </BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
