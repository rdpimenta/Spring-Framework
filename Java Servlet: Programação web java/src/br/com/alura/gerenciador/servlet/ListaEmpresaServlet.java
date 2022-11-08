package br.com.alura.gerenciador.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/listaEmpresas")
public class ListaEmpresaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Lista de empresas:");

        PrintWriter out = response.getWriter();
        out.println("<html><body><h1>Lista de empresas:</h1><ul>");
        Banco.getLista().forEach(empresa -> out.println("<li>" + empresa.getNome() + "</li>"));
        out.println("</ul></body></html>");
    }
}
