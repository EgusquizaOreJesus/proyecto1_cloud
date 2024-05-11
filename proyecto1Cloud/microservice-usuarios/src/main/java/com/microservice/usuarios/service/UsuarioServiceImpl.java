package com.microservice.usuarios.service;

import com.microservice.usuarios.AppConfig;
import com.microservice.usuarios.client.HilosClient;
import com.microservice.usuarios.domain.Usuario;
import com.microservice.usuarios.dto.HilosDTO;
import com.microservice.usuarios.dto.ImagesDTO;
import com.microservice.usuarios.dto.UsuarioDTO;
import com.microservice.usuarios.exception.UserNotFoundException;
import com.microservice.usuarios.http.response.HilosByUserResponse;
import com.microservice.usuarios.persistence.UsuarioRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HilosClient hilosClient;

    @Autowired
    private AppConfig appConfig;

    @Override
    public List<UsuarioDTO> findAll() {
        // pasar mi lista de usuarios a un DTO
        List<UsuarioDTO> usuarioDTOList = usuarioRepository.findAll().stream().map(usuario -> UsuarioDTO.builder()
                .nickname(usuario.getNickname())
                .correo(usuario.getEmail())
                .enlace_imagen(usuario.getEnlace_imagen())
                .enlace_portada(usuario.getEnlace_portada())
                .hilosCreados(usuario.getHilosCreados())
                .respuestasParticipadas(usuario.getRespuestasParticipadas())
                .estados(usuario.getEstados())
                .respuestas(usuario.getRespuestas())
                .build()).toList();

        return usuarioDTOList;
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            return ResponseEntity.ok(UsuarioDTO.builder()
                    .nickname(usuario.getNickname())
                    .correo(usuario.getEmail())
                    .enlace_imagen(usuario.getEnlace_imagen())
                    .enlace_portada(usuario.getEnlace_portada())
                    .hilosCreados(usuario.getHilosCreados())
                    .respuestasParticipadas(usuario.getRespuestasParticipadas())
                    .estados(usuario.getEstados())
                    .respuestas(usuario.getRespuestas())
                    .build());
        }
        throw new UserNotFoundException();
    }

    @Override
    public void updateHilos(Long id, Long hilos) {
        Usuario user = usuarioRepository.findById(id).orElse(null);
        if (user != null) {
            user.getHilosCreados().add(hilos);
            usuarioRepository.save(user);
        }
    }

    @Override
    public void updateRespuestas(Long id, Long respuestas) {
        Usuario user = usuarioRepository.findById(id).orElseThrow( () -> new UserNotFoundException());
        if (user != null) {
            user.getRespuestasParticipadas().add(respuestas);
            usuarioRepository.save(user);
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {
        Usuario user = usuarioRepository.findById(id).orElseThrow( () -> new UserNotFoundException());
        if (user != null) {
            user.getEstados().add(estado);
            usuarioRepository.save(user);
        }
    }

    @Override
    public ResponseEntity<?> uploadPicture(Long usuarioId, MultipartFile file, String pictureType) throws IOException {
        Optional<Usuario> optionalUser = usuarioRepository.findById(usuarioId);
        if (optionalUser.isPresent()){
            Usuario user = optionalUser.get();
            String projectDirectory = System.getProperty("user.dir");   // Obtén la ruta del directorio del proyecto

            // verificar el tipo de imagen
            String relativeFolderPath;
            String pictureAttribute;
            if ("profile".equals(pictureType)) {
                relativeFolderPath = "/src/main/resources/profile-pictures/";
                pictureAttribute = "image_path";
            } else if ("background".equals(pictureType)) {
                relativeFolderPath = "/src/main/resources/background-pictures/";
                pictureAttribute = "background_picture";
            } else {
                throw new IllegalArgumentException("Invalid pictureType. Use 'profile' or 'background'.");
            }

            // Combina la ruta del proyecto con la ruta relativa
            java.nio.file.Path folderPath = Paths.get(projectDirectory, relativeFolderPath);

            // Asegúrate de que el directorio exista, si no, créalo
            Files.createDirectories(folderPath);

            // Elimina imágenes antiguas asociadas al usuario
            eliminarImagenesAntiguas(user, pictureType, folderPath);

            // Verifica la extensión del archivo
            String[] allowedExtensions = { "jpg", "jpeg", "png", "gif" };
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();

            if (Arrays.asList(allowedExtensions).contains(fileExtension)) {
                // Construye el nombre del archivo
                String fileName = "picture_" + pictureType + "_" + usuarioId + "_" + System.currentTimeMillis() + "." + fileExtension;

                // Combina la ruta del directorio con el nombre del archivo
                Path filePath = folderPath.resolve(fileName);

                // Guarda el archivo en el sistema de archivos
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Actualiza la entidad de usuario con la ruta del archivo
                if ("profile".equals(pictureType)) {
                    user.setImage_path(filePath.toString());
                    // ruta para acceder a la imagen
                    String rutaPerfil = appConfig.getBackendBaseUrl() + "/api/usuarios/" + usuarioId + "/profile_picture";
                    user.setEnlace_imagen(rutaPerfil);
                } else if ("background".equals(pictureType)) {
                    user.setBackground_picture(filePath.toString());
                    String rutaFondo = appConfig.getBackendBaseUrl() + "/api/usuarios/" + usuarioId + "/background_picture";
                    user.setEnlace_portada(rutaFondo);
                }

                // Guarda el usuario actualizado en el repositorio

                usuarioRepository.save(user);
                return ResponseEntity.ok(UsuarioDTO.builder()
                        .nickname(user.getNickname())
                        .correo(user.getEmail())
                        .enlace_imagen(user.getEnlace_imagen())
                        .enlace_portada(user.getEnlace_portada())
                        .hilosCreados(user.getHilosCreados())
                        .respuestasParticipadas(user.getRespuestasParticipadas())
                        .estados(user.getEstados())
                        .respuestas(user.getRespuestas())
                        .build());
            } else {
                throw new IllegalArgumentException("Tipo de archivo no admitido. Use imágenes con extensiones: " + Arrays.toString(allowedExtensions));
            }
        }else{
            throw new UserNotFoundException();
        }
    }

    @Override
    public void update(Long id, Usuario usuario) {
        Usuario user = usuarioRepository.findById(id).orElse(null);
        if (user != null) {
            user.setEmail(usuario.getEmail());
            user.setNickname(usuario.getNickname());
            user.getHilosCreados().addAll(usuario.getHilosCreados());
            usuarioRepository.save(user);
        }
    }

    @Override
    public ResponseEntity<byte[]> getProfilePicture(Long usuarioId) {
        try{
            Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new UserNotFoundException());
            Path filePath = Paths.get(usuario.getImage_path());
            byte[] imageBytes = Files.readAllBytes(filePath);

            // Construir la respuesta con el contenido de la imagen y los encabezados adecuados
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Ajusta el tipo de contenido según el formato de tus imágenes

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<byte[]> getBackgroundPicture(Long usuarioId) {
        try{
            Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new UserNotFoundException());
            Path filePath = Paths.get(usuario.getBackground_picture());
            byte[] imageBytes = Files.readAllBytes(filePath);

            // Construir la respuesta con el contenido de la imagen y los encabezados adecuados
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Ajusta el tipo de contenido según el formato de tus imágenes

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<ImagesDTO> getProfilePicturePath(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new UserNotFoundException());
        return ResponseEntity.ok(ImagesDTO.builder()
                .enlace_imagen(usuario.getEnlace_imagen())
                .enlace_portada(usuario.getEnlace_portada())
                .build());
    }

    @Override
    public void save(Usuario course) {
        usuarioRepository.save(course);
    }

    @Override
    public HilosByUserResponse findHilosByUser(Long userId) {
        // printear
        System.out.println("Buscando hilos por usuario: " + userId);
        // Consultar el usuario
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);
        // Consultar los hilos del usuario
        List<HilosDTO> hilosDTOList = hilosClient.findAllHilosByUser(userId);

        // Crear la respuesta personalizada
        return HilosByUserResponse.builder()
                .nickname(usuario.getNickname())
                .email(usuario.getEmail())
                .enlace_imagen(usuario.getEnlace_imagen())
                .enlace_portada(usuario.getEnlace_portada())
                .hilos(hilosDTOList).build();
    }

    @Override
    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
        }
    }

    public void eliminarImagenesAntiguas(Usuario usuario, String pictureType, Path folderPath) throws IOException {
        // Elimina imágenes antiguas
        if ("profile".equals(pictureType)) {
            eliminarImagenAntigua(usuario.getImage_path(), folderPath);
        } else if ("background".equals(pictureType)) {
            eliminarImagenAntigua(usuario.getBackground_picture(), folderPath);
        }
    }

    public void eliminarImagenAntigua(String imagePath, Path folderPath) throws IOException {
        if (imagePath != null && !imagePath.isEmpty()) {
            Path oldFilePath = folderPath.resolve(Paths.get(imagePath).getFileName());
            Files.deleteIfExists(oldFilePath);
        }
    }
}
