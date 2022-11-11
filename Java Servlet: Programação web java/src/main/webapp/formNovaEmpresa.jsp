<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<c:url value="/entrada?acao=NovaEmpresa" var="linkServletNovaEmpresa"/>
<c:url value="/entrada?acao=AlteraEmpresa" var="linkServletEditaEmpresa"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:formatDate value="${empresa.dataDeAbertura}" pattern="dd/MM/yyyy" var="data" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
    <c:if test= "${not empty empresa}">
        <form action="${linkServletEditaEmpresa}" method="post">
            Nome: <input type="text" name="nome" value="${empresa.nome}" />
            Data Abertura: <input type="text" name="data" value="${data}" />
            <input type="hidden" name="id" value="${empresa.id}" />
            <input type="submit" />
        </form>
    </c:if>

    <c:if test= "${empty empresa}">
        <form action="${linkServletNovaEmpresa}" method="post">
            Nome: <input type="text" name="nome" />
            Data Abertura: <input type="text" name="data" />
            <input type="submit" />
        </form>
    </c:if>
</body>
</html>
