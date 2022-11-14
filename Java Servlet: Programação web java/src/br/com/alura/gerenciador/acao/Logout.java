package br.com.alura.gerenciador.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Acao {
    @Override
    public String executa(HttpServletRequest request) throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        sessao.invalidate();

        return "redirect:entrada?acao=LoginForm";
    }
}
