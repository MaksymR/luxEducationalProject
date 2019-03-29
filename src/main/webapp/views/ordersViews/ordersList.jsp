<%@ page import="com.riaboshapka.domain.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order's list</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-light-grey">

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Education web-application!)</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Order's list:</h2>
        </div>
        <ul>
            <%
                List<Order> ordersList = (List<Order>) request.getAttribute("ordersList");

                if (ordersList != null && !ordersList.isEmpty()) {
                    out.println("<ul class=\"w3-ul\">");
                    for (Order order : ordersList) {
                        out.println("<li class=\"w3-hover-sand\">" + order + "</li>");
                    }
                    out.println("</ul>");
                } else {
                    out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n" +
                            "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                            "   class=\"w3-button w3-margin-right w3-display-right w3-round-large" +
                            " w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                            "   <h5>There are no orders yet!</h5>\n" +
                            "</div>");
                }
            %>
        </ul>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/ordersMenu.html'">Back to order menu</button>
</div>

</body>
</html>
