package com.alura.forohub;

import com.alura.forohub.repositories.*;
import com.alura.forohub.services.DatabaseContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication(scanBasePackages = "com.alura.forohub.*")
@EntityScan(basePackages = {"com.alura.forohub.*"})
@EnableJpaRepositories(basePackages = "com.alura.forohub.*")
public class ForohubApplication {

	@Autowired
	private static DatabaseContext databaseContext;

	private static BufferedReader stdin_reader;

	public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(ForohubApplication.class, args);
        stdin_reader = new BufferedReader(new InputStreamReader(System.in));
        databaseContext = new DatabaseContext();

        // Asignar repositorios al DatabaseContext
        databaseContext.setCursoRepository(context.getBean(CursoRepository.class));
        databaseContext.setUsuarioRepository(context.getBean(UsuarioRepository.class));
        databaseContext.setPerfilRepository(context.getBean(PerfilRepository.class));
        databaseContext.setTopicoRepository(context.getBean(TopicoRepository.class));
        databaseContext.setRespuestaRepository(context.getBean(RespuestaRepository.class));
		while(true){
			System.out.print("JAVA BACKEND");
			stdin_reader.readLine();
		}
    }
}
