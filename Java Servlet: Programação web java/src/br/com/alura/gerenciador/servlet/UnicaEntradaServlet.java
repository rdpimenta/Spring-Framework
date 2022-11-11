package br.com.alura.gerenciador.servlet;

import br.com.alura.gerenciador.acao.*;

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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramAcao = req.getParameter("acao");

        switch (paramAcao) {
            case "ListaEmpresas":
                ListaEmpresas listaEmpresas = new ListaEmpresas();
                listaEmpresas.executa(req, resp);
                break;
            case "RemoveEmpresa":
                RemoveEmpresa removeEmpresa = new RemoveEmpresa();
                removeEmpresa.executa(req, resp);
                break;
            case "MostraEmpresa":
                MostraEmpresa mostraEmpresa = new MostraEmpresa();
                mostraEmpresa.executa(req, resp);
                break;
            case "NovaEmpresa":
                NovaEmpresa novaEmpresa = new NovaEmpresa();
                novaEmpresa.executa(req, resp);
                break;
            case "AlteraEmpresa":
                AlteraEmpresa alteraEmpresa = new AlteraEmpresa();
                alteraEmpresa.executa(req, resp);
                break;
        }
    }
}
