<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <script src="<c:url value="../../resources/styles/js/jquery.min.js" />"></script>
    <script src="<c:url value="../../resources/styles/js/bootstrap.min.js" />"></script>
    <link href="<c:url value="../../resources/styles/css/bootstrap.min.css" />"
          rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">${sportsbetting}</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="home">${home}<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="events">${events}</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${language}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}?lang=hu">${hungarian}</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}?lang=en">${english}</a>
                </div>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <a href="login" class="btn btn-outline-success my-2 my-sm-0" role="button">${logout}</a>
        </ul>
    </div>
</nav>



<div class="m-3">
    <div class="card">
        <h5 class="card-header">${account_details}</h5>
        <form method="post" class="m-3">
            <div class="form-group">
                <label>${name}</label>
                <input class="form-control" placeholder="Enter name" name="playername" required>
            </div>
            <div class="form-group">
                <label>${date_of_birth}</label>
                <input type="date" class="form-control" placeholder="Enter date of birth" name="dateofbirth" required>
            </div>
            <div class="form-group">
                <label>${account_number}</label>
                <input class="form-control" placeholder="XXXXXXXX-XXXXXXXX" name="accountnumber" pattern="^\d{8}-\d{8}$" required>
            </div>
            <div class="form-group">
                <label for="exampleFormControlSelect1">${currency}</label>
                <select class="form-control" id="exampleFormControlSelect1" name="currency">
                    <option>HUF</option>
                    <option>EUR</option>
                    <option>USD</option>
                </select>
            </div>
            <div class="form-group">
                <label>${balance_web}</label>
                <input type="number" min="1000" class="form-control"placeholder="Minimum amount 1000" name="balance" required>
            </div>
            <button type="submit" class="btn btn-primary">${save}</button>
        </form>
    </div>
</div>

<div class="m-3">
    <div class="card">
        <h5 class="card-header">${player_info}</h5>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">${name}</th>
                <th scope="col">${email_address}</th>
                <th scope="col">${account_number}</th>
                <th scope="col">${date_of_birth}</th>
                <th scope="col">${balance_web}</th>
                <th scope="col">${currency}</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${player.getName()}</td>
                <td>${player.getEmail()}</td>
                <td>${player.getAccountNumber()}</td>
                <td>${player.getBirth()}</td>
                <td>${player.getBalance()}</td>
                <td>${player.getCurrency()}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="m-3">
    <div class="card">
        <h5 class="card-header">${wagers_web}</h5>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"> </th>
                <th scope="col">#</th>
                <th scope="col">${event_title}</th>
                <th scope="col">${event_type}</th>
                <th scope="col">${bet_type}</th>
                <th scope="col">${outcome_value}</th>
                <th scope="col">${outcome_odd}</th>
                <th scope="col">${wager_amount}</th>
                <th scope="col">${winner}</th>
                <th scope="col">${processed}</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${wagers}" var="item">
                    <tr>
                        <td>
                            <form method="post" action="remove">
                                <input type="hidden" name="wagerId" value="${item.getId()}">
                                <button type="submit" class="btn btn-primary">${remove_button}</button>
                            </form>
                        </td>
                        <td><c:out value="${item.getId()}" /></td>
                        <td><c:out value="${item.getOdd().getOutcome().getBet().getEvent().getTitle()} - ${item.getOdd().getOutcome().getBet().getEvent().getStartDate()}" /></td>
                        <td><c:out value="${item.getOdd().getOutcome().getBet().getEvent().getType()}" /></td>
                        <td><c:out value="${item.getOdd().getOutcome().getBet().getType()}" /></td>
                        <td><c:out value="${item.getOdd().getOutcome().getDescription()}" /></td>
                        <td><c:out value="${item.getOdd().getValue()}" /></td>
                        <td><c:out value="${item.getAmount()} ${item.getCurrency()}" /></td>
                        <td><c:out value="${item.isWin()}" /></td>
                        <td><c:out value="${item.isProcessed()}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
