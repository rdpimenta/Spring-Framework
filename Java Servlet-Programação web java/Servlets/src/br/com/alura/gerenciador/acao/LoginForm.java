package br.com.alura.gerenciador.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginForm implements Acao {
    @Override
    public String executa(HttpServletRequest request)
            throws ServletException, IOException {
        return "forward:formLogin.jsp";
    }
}
