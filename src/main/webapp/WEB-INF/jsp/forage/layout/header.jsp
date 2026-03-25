<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forage Pro | Management System</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/forage.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>
    <header>
        <div class="logo">
            <h1>Forage<span>Pro</span></h1>
        </div>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/forage/client">Clients</a></li>
                <li><a href="${pageContext.request.contextPath}/forage/demande">Demandes</a></li>
                <li><a href="${pageContext.request.contextPath}/forage/devis/form">Nouveau Devis</a></li>
            </ul>
        </nav>
    </header>