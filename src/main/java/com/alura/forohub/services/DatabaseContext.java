package com.alura.forohub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.forohub.entities.*;
import com.alura.forohub.repositories.*;

@Service
public class DatabaseContext {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    // Métodos para Usuario
    public void saveUsuario(Usuario usuario) {
        this.usuarioRepository.save(usuario);
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository newUsuarioRepository) {
        this.usuarioRepository = newUsuarioRepository;
    }

    // Métodos para Curso
    public void saveCurso(Curso curso) {
        this.cursoRepository.save(curso);
    }

    public CursoRepository getCursoRepository() {
        return cursoRepository;
    }

    public void setCursoRepository(CursoRepository newCursoRepository) {
        this.cursoRepository = newCursoRepository;
    }

    // Métodos para Perfil
    public void savePerfil(Perfil perfil) {
        this.perfilRepository.save(perfil);
    }

    public PerfilRepository getPerfilRepository() {
        return perfilRepository;
    }

    public void setPerfilRepository(PerfilRepository newPerfilRepository) {
        this.perfilRepository = newPerfilRepository;
    }

    // Métodos para Tópico
    public void saveTopico(Topico topico) {
        this.topicoRepository.save(topico);
    }

    public TopicoRepository getTopicoRepository() {
        return topicoRepository;
    }

    public void setTopicoRepository(TopicoRepository newTopicoRepository) {
        this.topicoRepository = newTopicoRepository;
    }

    // Métodos para Respuesta
    public void saveRespuesta(Respuesta respuesta) {
        this.respuestaRepository.save(respuesta);
    }

    public RespuestaRepository getRespuestaRepository() {
        return respuestaRepository;
    }

    public void setRespuestaRepository(RespuestaRepository newRespuestaRepository) {
        this.respuestaRepository = newRespuestaRepository;
    }
}
