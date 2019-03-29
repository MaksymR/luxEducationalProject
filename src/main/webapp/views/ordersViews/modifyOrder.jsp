<%@ page import="com.riaboshapka.domain.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify the order</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-padding">
    <%
        if (request.getAttribute("order") != null) {
            Order order = (Order) request.getAttribute("order");
            out.println("<div class=\"w3-panel w3-light-green w3-display-container w3-card-4 w3-round\">\n" +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large" +
                    " w3-hover-light-green w3-border w3-border-light-green w3-hover-border-grey\">×</span>\n" +
                    "<h5>Order {" +
                    "id='" + order.getId() + '\'' +
                    "client='" + order.getClient() + '\'' +
                    ", product='" + order.getProducts() + '\'' +
                    "} modified!</h5>\n" + "</div>");
        }
    %>
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-light-green">
            <h2>Modify an existing order</h2>
            <h2 class="w3-text-black">Enter order's, client's and product's ID for modifying of the order</h2>
        </div>

        <form method="post" class="w3-selection w3-light-grey w3-padding">

            <label>Order's ID:
                <input type="text" name="ordersID"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>


            <label>Client's ID:
                <input type="text" name="clientsID"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>

            <label>Product's ID:
                <input type="text" name="productsID"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>

            <button type="submit" class="w3-btn w3-light-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large w3-text-black" onclick="location.href='/ordersMenu.html'">Back to order menu
    </button>
</div>
</body>
</html>
