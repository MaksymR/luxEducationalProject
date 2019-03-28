<%@ page import="com.riaboshapka.domain.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify the client</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-light-grey">

<div class="w3-container w3-padding">
    <%
        if (request.getAttribute("client") != null) {
            Client client = (Client) request.getAttribute("client");
            out.println("<div class=\"w3-panel w3-light-green w3-display-container w3-card-4 w3-round\">\n" +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large" +
                    " w3-hover-light-green w3-border w3-border-light-green w3-hover-border-grey\">×</span>\n" +
                    "<h5>Client {" +
                    "id='" + client.getId() + '\'' +
                    "name='" + client.getName() + '\'' +
                    ", surname='" + client.getSurname() + '\'' +
                    ", age=" + client.getAge() +
                    ", phone='" + client.getPhone() + '\'' +
                    ", email='" + client.getEmail() + '\'' +
                    "} modified!</h5>\n" + "</div>");
        }
    %>
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-light-green">
            <h2>Modify an existing client</h2>
            <h2 class="w3-text-black">Enter client's ID for modifying of the client</h2>
        </div>

        <form method="post" class="w3-selection w3-light-grey w3-padding">

            <label>Client's Id:
                <input type="text" name="id"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>


            <label>Client's name:
                <input type="text" name="name"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>

            <label>Client's surname:
                <input type="text" name="surname"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>

            <label>Client's age:
                <input type="text" name="age"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>

            <label>Client's phone:
                <input type="text" name="phone"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>

            <label>Client's email:
                <input type="text" name="email"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>
            <button type="submit" class="w3-btn w3-light-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large w3-text-black" onclick="location.href='/clientMenu.html'">Back to client menu
    </button>
</div>

</body>
</html>
