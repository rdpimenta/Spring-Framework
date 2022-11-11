package br.com.alura.gerenciador.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mostraEmpresa")
public class MostraEmpresaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        Banco banco = new Banco();
        Empresa empresaEncontrada = banco.encontraEmpresaPelaId(id);

        RequestDispatcher rd = request.getRequestDispatcher("/formNovaEmpresa.jsp");
        request.setAttribute("empresa", empresaEncontrada);
        rd.forward(request, response);
    }
}
