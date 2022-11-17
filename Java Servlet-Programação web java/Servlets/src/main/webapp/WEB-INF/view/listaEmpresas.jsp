<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <body>
    <c:import url="logout-parcial.jsp"/>

    Usuario Logado: ${usuarioLogado.login }

    <br>
    <br>
    <br>

        <c:if test="${not empty empresa}">
                    Empresa ${ empresa } cadastrada com sucesso!
        </c:if>

        <h1>Lista de empresas:</h1> </br>
        <ul>
            <c:forEach items="${ listaDeEmpresas }" var="empresa">
                <li>
                    ${ empresa.nome } - <fmt:formatDate value="${empresa.dataDeAbertura}" pattern="dd/MM/yyyy"/>
                    <a href="/gerenciador/entrada?acao=MostraEmpresa&id=${empresa.id}">edita</a>
                    <a href="/gerenciador/entrada?acao=RemoveEmpresa&id=${empresa.id}">remove</a>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>