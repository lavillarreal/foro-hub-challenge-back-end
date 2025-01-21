package com.alura.forohub.services;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alura.forohub.entities.Usuario;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

public class SecurityAgent {

    @Autowired
    private DatabaseContext databaseContext;

    @Value("${jwt.secret.key}")
    private String secretKey;

    // Genera un token JWT
    public String generateJwtToken(String emailString) {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(emailString)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(30 * 24 * 60 * 60))) // 30 días
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Genera el hash de la contraseña
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public Usuario isValidUser(Usuario user){

        if (databaseContext.getUsuarioRepository().findByCorreoElectronico(user.getCorreoElectronico()) != null){
            Usuario registeredUser = !(databaseContext.getUsuarioRepository().findByCorreoElectronico(user.getCorreoElectronico()) == null) ? (Usuario) databaseContext.getUsuarioRepository().findByCorreoElectronico(user.getCorreoElectronico()) : null;

            if (registeredUser != null){
                if (hashPassword(user.getContrasena()) == registeredUser.getContrasena());
                return registeredUser;
            }
        }
        return null;
    }

    public boolean isAdmin(Usuario user){

        Usuario registeredUser = isValidUser(user);

        if (registeredUser != null){

            if (registeredUser.isAdmin() == true){
                return true;
            }
        }
        return false;
    }

    public boolean isAdminUser(String authorization) {
        String username = null;
        if (authorization.startsWith("Bearer ")) {
            username = getUsernameFromToken(authorization.substring(7));
        } else if (authorization.startsWith("Basic ")) {
            String[] authInfo = authorization.split(" ");
            String[] credentials = new String(Base64.getDecoder().decode(authInfo[1])).split(":");
            username = credentials[0];
        }
        if (username != null) {
            Usuario user = databaseContext.getUsuarioRepository().findByCorreoElectronico(username);
            return isAdmin(user);
        }
        return false;
    }

    public boolean isAuthorized(String authorization) {
        if (authorization == null) {
            return false;
        }
        else{
            if (authorization.startsWith("Basic ")){
                
                try{
                    String[] auth_info = authorization.split(" ");
                    auth_info = auth_info[1].split(":");
                    Usuario testUser = new Usuario();
                    testUser.setCorreoElectronico(auth_info[0]);
                    testUser.setContrasena(auth_info[1]);
                    if(isValidUser(testUser) != null){
                        return true;
                    }
                }
                catch (Exception e){
                    System.out.println("InvalidUser: " + e.getMessage());
                }
            }
            else if (authorization.startsWith("Bearer ")){
                
                try{
                    String[] auth_info = authorization.split(" ");
                    String tempToken = auth_info[1];

                    if(!tempToken.isBlank()){
                        if(isTokenValid(tempToken)){
                            return true;
                        }
                    }
                    return false;
                }
                catch (Exception e){
                    System.out.println("InvalidUser: " + e.getMessage());
                }
            }
            else{
                return false;
            }
        }
        return false;        
    }

    public boolean isTokenValid(String token) {
        try {

            SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());

            // Verificar el token
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

            return true; // Si el token es válido

        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Token no soportado: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Token malformado: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Firma del token inválida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Token vacío o nulo: " + e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        try {
            SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());

            Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            System.out.println("Error al obtener el usuario del token: " + e.getMessage());
            return null;
        }
    }

    public DatabaseContext getDatabaseContext(){
        return databaseContext;
    }

}

