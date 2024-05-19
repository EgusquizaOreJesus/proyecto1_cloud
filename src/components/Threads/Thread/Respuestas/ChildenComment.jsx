// ChildenComment.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ListComment from './ListComment';
import backendUrl from '../../../../ApiConfig';

export const ChildenComment = ({ comment_id, depth }) => {
  const [listcomments, setListcomments] = useState([]);

  useEffect(() => {
    let timer;
    const fetchData = async () => {
      try {
        // Esperar 500 milisegundos antes de realizar la solicitud al servidor
        await new Promise((resolve) => {
          timer = setTimeout(resolve, 500);
        });

        const response = await axios.get(`${backendUrl}/respuestas/respuestas_hijas/${comment_id}`);
        setListcomments(response.data);
      } catch (error) {
        console.error('Error al obtener las respuestas anidadas:', error);
      }
    };

    fetchData();

    return () => clearTimeout(timer); // Limpiar el temporizador en la desinstalación
  }, [comment_id]);

  return <ListComment listcomments={listcomments} depth={depth} />;
};
