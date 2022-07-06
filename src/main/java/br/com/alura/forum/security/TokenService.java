package br.com.alura.forum.security;

import br.com.alura.forum.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("API do fórum da Alura") //App que gerou esse token
                .setSubject(logado.getId().toString()) //Dono do token, quem é o usuário autenticado que esse token pertence
                .setIssuedAt(hoje) //Data de geração desse token
                .setExpiration(dataExpiracao) //Data da expiração do token
                .signWith(SignatureAlgorithm.HS256, secret) //Criptografado
                .compact();
    }


    public boolean isTokenValido(String token) {
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
