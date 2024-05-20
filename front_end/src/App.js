import './App.css';

import { AnimeDetails } from './components/anime_Details/AnimeDetails';
import HeaderComponent from './components/Header/HeaderComponent';
import { Index } from './components/Index';
import { Login } from './components/Login/Login';

import { BrowserRouter, Route, Routes,Navigate } from 'react-router-dom';
import { Register } from './components/Register/Register';
import Threads from './components/Threads/Threads';
import Thread from './components/Threads/Thread/Thread';
import { FooterComponent } from './components/Footer/FooterComponent';
import NewThread from './components/Threads/Thread/NewThread';
import { ProfileUser } from './components/Profile/User/ProfileUser';
import { Settings } from './components/Profile/SettingsUser/Settings';


function App() {
    // Verifica si el usuario está autenticado (por ejemplo, si existe un token en localStorage)
    const idUser = localStorage.getItem("userId");
    const idName = localStorage.getItem("userName");
    const idImage = localStorage.getItem("userImage");
    const isAuthenticated = idUser !== null; // Check if idUser is not null

    return (
<div className="App">
    <BrowserRouter>
        <HeaderComponent userId = {idUser} idName={idName} idImage={idImage} />
        <Routes>
          <Route exact path='/' element={<Index />} />
          <Route path="/anime/:id" element={<AnimeDetails />} />
          <Route path='/Login' element={<Login />} />
          <Route path='/Register' element={<Register />} />
          <Route path='/user/:name/*' element={<ProfileUser userId={idUser} idImage={idImage}/>}/>
          <Route path="/settings/*" element={isAuthenticated ? <Settings userId={idUser} /> : <Navigate to="/Login" />} />
          <Route path='/Threads' element={<Threads userId={idUser} />}/>
          <Route path='/Threads/:id' element={<Thread userId = {idUser} idImage = {idImage}/>}/>
          <Route path='/thread/new' element={isAuthenticated ? <NewThread userId={idUser} /> : <Navigate to="/Login" />} />
        </Routes>
        <FooterComponent/>
    </BrowserRouter>
  </div>
    
  );
}

export default App;
