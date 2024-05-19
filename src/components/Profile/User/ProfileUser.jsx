import React, { useEffect, useState } from 'react'
import { Route, Routes, useParams } from 'react-router-dom';
import './styles/ProfileUser.css'
import { FavoritesUser } from './routes_profile/FavoritesUser';
import { Overview } from './routes_profile/Overview';
import { SocialUser } from './routes_profile/SocialUser';
import axios from 'axios';
import backendUrl from '../../../ApiConfig';
import {useNavigate} from 'react-router-dom';
export const ProfileUser = ({userId}) => {
    const navigate = useNavigate();
    const { name } = useParams();
    const [selectedNavItem, setSelectedNavItem] = useState('overview');

    const [user, setUser] = useState({});
    useEffect(() => {
      axios.get(`${backendUrl}/usuarios/find_by_nickname/${name}`)
      .then((response) => {
        setUser(response.data.body);
      }).catch((error) => {
      console.error("Error fetching user data:", error);
      });

    }, [name]);
    useEffect(() => {
      // Recuperar el estado del elemento de navegación desde localStorage
      const storedNavItem = localStorage.getItem('selectedNavItem');
      if (storedNavItem) {
        setSelectedNavItem(storedNavItem);
      }
    }, []); // Se ejecuta solo una vez al montar el componente
    useEffect(() => {
        // Limpiar el valor almacenado en localStorage cuando el componente se desmonta
        return () => {
          localStorage.removeItem('selectedNavItem');
        };
      }, []);
    const handleNavItemClick = (navItem) => {
      setSelectedNavItem(navItem);
      if(navItem === 'overview'){
        navigate(`/user/${name}`);  
      }else{
        navigate(`/user/${name}/${navItem}`);
      }
      //href={`/user/${name}/social`}
      // Almacenar el estado del elemento de navegación en localStorage
      localStorage.setItem('selectedNavItem', navItem);
    };
    console.log(userId);
  return (
    <div className='Page_Content'>
        <div className='header_wrap'>
            <div className='banner'
            style={{
              backgroundImage: user.enlace_portada !== null ? `url(${user.enlace_portada})` : 'none'}}
              >
                <div className='shadow'></div>
                <div className='container'>
                    <div className='banner_container'>
                        <img src={user.enlace_imagen !== null ? user.enlace_imagen: require('../../images/profile/profile.png')} className='avatar' alt="profile" />
                        <div className='name_perfil'>{name}</div>
                        <div className='actions'></div>
                    </div>
                </div>
            </div>

            <div className='nav_wrap'>
                <div className='nav_container'>
                  <div
                    className={`link ${selectedNavItem === 'overview' ? 'active' : ''}`}
                    style={{cursor: 'pointer'}}
                    onClick={() => handleNavItemClick('overview')}
                  >
                  Overview
                  </div>
                  <div
                    className={`link ${selectedNavItem === 'favorites' ? 'active' : ''}`}
                    onClick={() => handleNavItemClick('favorites')}
                    style={{cursor: 'pointer'}}
                  >
                    Favorites
                  </div>
                  <div
                    className={`link ${selectedNavItem === 'social' ? 'active' : ''}`}
                    onClick={() => handleNavItemClick('social')}
                    style={{cursor: 'pointer'}}

                  >
                    Social
                  </div>
                </div>
            </div>
        </div>
        <div className='container_page'>
            <Routes>
                <Route path="/" element={<Overview userId={userId} name={name} />} />
                <Route path="/favorites" element={<FavoritesUser userId={userId} name={name} />} />
                <Route path="/social" element={<SocialUser userId={userId} name={name} />} />
            </Routes>
        </div>
    </div>
  )
}
