package br.com.alura.gerenciador.servlet;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/editaEmpresa")
public class EditaEmpresaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nomeEmpresa = request.getParameter("nome");
        String paramDataEmpresa = request.getParameter("data");

        Banco banco = new Banco();
        Empresa empresaEncontrada = banco.encontraEmpresaPelaId(id);

        Date dataAbertura;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataAbertura = sdf.parse(paramDataEmpresa);
        } catch (ParseException e) {
            throw new ServletException(e);
        }

        empresaEncontrada.setNome(nomeEmpresa);
        empresaEncontrada.setDataDeAbertura(dataAbertura);

        response.sendRedirect("listaEmpresas");
    }
}
