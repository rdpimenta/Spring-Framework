<%@ page import ="java.util.List"%>
<%
    List<String> listaDeEmpresas = (List<String>) request.getAttribute("listaDeEmpresas");
%>

<html>
    <body>
        <h1>Lista de empresas:</h1>
        <%
            for (String empresa : listaDeEmpresas) {
        %>

            <li><%= empresa %></li>

        <%
            }
        %>
    </body>
</html>