package br.com.alura.gerenciador.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeEmpresa")
public class RemoveEmpresaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Banco banco = new Banco();
        Empresa empresaEncontrada = banco.encontraEmpresaPelaId(id);

        banco.removeEmpresa(empresaEncontrada);

        response.sendRedirect("listaEmpresas");
    }
}
