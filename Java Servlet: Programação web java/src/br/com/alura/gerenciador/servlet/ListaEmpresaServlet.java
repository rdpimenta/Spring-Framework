package br.com.alura.gerenciador.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listaEmpresas")
public class ListaEmpresaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Lista de empresas:");

        List<Empresa> listaDeEmpresas = Banco.getLista();

        RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas.jsp");
        request.setAttribute("listaDeEmpresas", listaDeEmpresas);
        rd.forward(request, response);
    }
}
