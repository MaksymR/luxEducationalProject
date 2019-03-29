<%@ page import="com.riaboshapka.domain.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify the product</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-light-grey">

<div class="w3-container w3-padding">
    <%
        if (request.getAttribute("product") != null) {
            Product product = (Product) request.getAttribute("product");
            out.println("<div class=\"w3-panel w3-light-green w3-display-container w3-card-4 w3-round\">\n" +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large" +
                    " w3-hover-light-green w3-border w3-border-light-green w3-hover-border-grey\">×</span>\n" +
                    "<h5>Product {" +
                    "id='" + product.getId() + '\'' +
                    "name='" + product.getName() + '\'' +
                    ", price='" + product.getPrice() + '\'' +
                    "} modified!</h5>\n" + "</div>");
        }
    %>
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-light-green">
            <h2>Modify an existing product</h2>
            <h2 class="w3-text-black">Enter product's ID for modifying of the product</h2>
        </div>

        <form method="post" class="w3-selection w3-light-grey w3-padding">

            <label>Product's Id:
                <input type="text" name="id"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>


            <label>Product's name:
                <input type="text" name="name"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>

            <label>Product's price:
                <input type="text" name="price"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>

            <button type="submit" class="w3-btn w3-light-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large w3-text-black" onclick="location.href='/productMenu.html'">Back to product menu
    </button>

    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>

</body>
</html>
