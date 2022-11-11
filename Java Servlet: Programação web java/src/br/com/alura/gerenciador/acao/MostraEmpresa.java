package br.com.alura.gerenciador.acao;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MostraEmpresa {
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Banco banco = new Banco();
        Empresa empresaEncontrada = banco.encontraEmpresaPelaId(id);

        RequestDispatcher rd = request.getRequestDispatcher("/formNovaEmpresa.jsp");
        request.setAttribute("empresa", empresaEncontrada);
        rd.forward(request, response);
    }
}
