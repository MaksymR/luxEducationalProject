<%@ page import="com.riaboshapka.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client's list</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Education web-application!)</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Product's list:</h2>
        </div>
        <ul>
            <%
                List<Product> productsList = (List<Product>) request.getAttribute("productsList");

                if (productsList != null && !productsList.isEmpty()) {
                    out.println("<ul class=\"w3-ul\">");
                    for (Product product : productsList) {
                        out.println("<li class=\"w3-hover-sand\">" + product + "</li>");
                    }
                    out.println("</ul>");
                } else {
                    out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n" +
                            "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                            "   class=\"w3-button w3-margin-right w3-display-right w3-round-large" +
                            " w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                            "   <h5>There are no products yet!</h5>\n" +
                            "</div>");
                }
            %>
        </ul>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/productMenu.html'">Back to product menu</button>

    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>
