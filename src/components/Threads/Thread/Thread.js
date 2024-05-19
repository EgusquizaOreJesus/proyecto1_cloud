import React, { useState, useEffect, useRef } from 'react';
import { useParams } from 'react-router-dom'; // Importa Link de React Router
import axios from 'axios';
import ListComment from './Respuestas/ListComment';
import backendUrl from '../../../ApiConfig';

const Thread = ({userId ,idImage} ) => {
    const { id } = useParams();
    
    //console.log(id)
    //console.log(idImage)
    const [listcomments, setListcomments] = useState([]);
    const userNick = localStorage.getItem('userName');
    const [hilos, setHilos] = useState({ tema: '', contenido: '', fechaCreacion: '',nickname: '', respuestaIds: [] });
    const [editorState, setEditorState] = useState(false);
    const [editorContent, setEditorContent] = useState(''); // Nuevo estado para el contenido del editor
    const [imageruta, setImageruta] = useState('');
    const textareaRef = useRef(null);
    const handleTextareaInput = () => {
      const textarea = textareaRef.current;
      setEditorContent(textarea.value); // Actualiza el estado con el contenido del editor
      textarea.style.height = `auto`;
      textarea.style.height = `${textarea.scrollHeight}px`;
    };

    const toggleEditorState = () => {
      setEditorState(!editorState);
    };
    useEffect(() => {
      axios.get(`${backendUrl}/respuestas/rootRespuestas/${id}`)
        .then((response) => {
          console.log(response.data);
          setListcomments(response.data);
        })
        .catch((error) => {
          console.error('Error al obtener los hilos:', error);
        });
    
      axios.get(`${backendUrl}/hilos/find/${id}`)
        .then((response) => {
          //console.log(response.data);
          setHilos(response.data);
          //console.log("get");
        })
        .catch((error) => {
          console.error('Error al obtener los hilos:', error);
        });
        // obtener informacion de usuario 
        axios.get(`${backendUrl}/usuarios/find/${hilos.id}`)
        .then((response) => {
          //console.log(response.data);
          setImageruta(response.data.enlace_imagen);
          //console.log("get");
        })
        .catch((error) => {
          console.error('Error al obtener los hilos:', error);
        });
    }, [id, hilos.id]);

    // Verificación de si hilos es null o undefined
    if (!hilos) {
        return <div>Cargando...</div>; // o cualquier otro indicador de carga que desees mostrar
    }

  const handleRespuestaSubmit = () => {
        // Verifica que el contenido del editor tenga al menos 10 caracteres
      if (editorContent.length < 10) {
          alert('El contenido debe tener al menos 10 caracteres.');
          return;
        }
    // Enviar la respuesta al backend
    axios.post(`${backendUrl}/respuestas/${userId}/${id}`, { contenido: editorContent })
      .then((response) => {
        // Actualizar la vista o realizar cualquier acción necesaria después de enviar la respuesta
        console.log('Respuesta enviada con éxito:', response.data);
        window.location.reload();

      })
      .catch((error) => {
        console.error('Error al enviar la respuesta:', error);
      });
  };

  const calculateTimeAgo = (createdDate) => {
    const currentDate = new Date();
    const createdDateObj = new Date(createdDate);
    const timeDifference = currentDate - createdDateObj;
    const seconds = Math.floor(timeDifference / 1000);
    const minutes = Math.floor(seconds / 60);
    const hours = Math.floor(minutes / 60);
    const days = Math.floor(hours / 24);
    const years = Math.floor(days / 365);

    if (years > 0) {
      return `replied ${years} ${years === 1 ? 'year' : 'years'} ago`;
    } else if (days > 0) {
      return `replied ${days} ${days === 1 ? 'day' : 'days'} ago`;
    } else if (hours > 0) {
      return `replied ${hours} ${hours === 1 ? 'hour' : 'hours'} ago`;
    } else if (minutes > 0) {
      return `replied ${minutes} ${minutes === 1 ? 'minute' : 'minutes'} ago`;
    } else {
      return `replied ${seconds} ${seconds === 1 ? 'second' : 'seconds'} ago`;
    }
  };
  return (
    <div className='Page_content_forum'>
      <div className='forum_container'>
        <div className={`comment-editor ${editorState ? 'active':''}`}>
          <div className='wrap_comment_editor'>
            <div className='editor'>
              <div className='input textarea'>
              <textarea
              autoComplete='off'
              style={{ minHeight: '120px', height: '120px' }}
              className='textarea__inner'
              placeholder='What are you thinking?'
              onInput={handleTextareaInput}
              rows={1}
              ref={textareaRef}
            ></textarea>
              </div>
              <div className='actions'>
                <div className='button like' onClick={toggleEditorState}>Cancel</div>
                <div className='button comment' onClick={handleRespuestaSubmit}>Send</div>                 
              </div>
            </div>
            <div className='preview'>
              <h2 className='prev'>Preview:</h2>
              <p className='prev_content'>{editorContent}</p>
            </div>
          </div>
        </div>
        <h1 className='title_thread'>{hilos.tema}</h1>
        <div className='body'>
          <div className='header'>
          <a href={`/user/${hilos.nickname}`} className='user'>
                <div className='avatar' style={{
                      backgroundImage: `url(${imageruta !== null ? imageruta : '../../../images/profile/profile.png'})`,
                    }}    
                />{hilos.nickname}
            </a>
            <div className='info'>
              <div className='time'>{calculateTimeAgo(hilos.fechaCreacion)}</div>
            </div>
          </div>
          <div className='markdown' style={{ overflowWrap: 'break-word', wordWrap: 'break-word', wordBreak: 'break-word', maxWidth: '100%' }}>
            <p className='markdown_container'>{hilos.contenido}</p>
          </div>
          <div className='footer_thread'>
            <div className='labels'>
            </div>
            <div className='actions'>
            <div className='button like'>Like</div>
            <div className='button comment' onClick={toggleEditorState}>Comment</div>          
            </div>
          </div>
          {userNick === hilos.userNickname && 
          <div className='delete'>
            <button className='delete_button'>Delete</button>
          </div>
          }
        </div>
        <ListComment listcomments = {listcomments}/>
      </div>


    </div>
  );
};

export default Thread;
