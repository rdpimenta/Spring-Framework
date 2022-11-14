package br.com.alura.gerenciador.servlet;

import br.com.alura.gerenciador.acao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramAcao = request.getParameter("acao");
        String nome;
        String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao;
        Class classe;

        try {
            classe = Class.forName(nomeDaClasse);//carrega a classe com o nome
            Acao acao = (Acao) classe.newInstance();
            nome = acao.executa(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String[] tipoEEndereco = nome.split(":");

        if(tipoEEndereco[0].equals("forward")) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
            rd.forward(request, response);
        } else {
            response.sendRedirect(tipoEEndereco[1]);
        }
    }
}
