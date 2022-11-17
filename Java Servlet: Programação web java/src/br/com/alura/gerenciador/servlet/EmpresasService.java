package br.com.alura.gerenciador.servlet;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/empresas")
public class EmpresasService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empresa> empresas = new Banco().getEmpresas();

//        Produz JSON
//        Gson gson = new Gson();
//        String json = gson.toJson(empresas);
//
//        response.setContentType("application/json");
//        response.getWriter().println(json);

        XStream xstream = new XStream();
        xstream.alias("empresa", Empresa.class);
        String xml = xstream.toXML(empresas);

        response.setContentType("application/xml");
        response.getWriter().print(xml);


    }

}
