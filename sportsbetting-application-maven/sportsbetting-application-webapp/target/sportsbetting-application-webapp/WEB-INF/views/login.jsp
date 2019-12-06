<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title>Sportsbetting web application</title>
		<script src="<c:url value="../../resources/styles/js/jquery.min.js" />"></script>
        <script src="<c:url value="../../resources/styles/js/bootstrap.min.js" />"></script>
        <link href="<c:url value="../../resources/styles/css/bootstrap.min.css" />"
              rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card card-signin my-5">
                    <div class="card-body">
                        <h5 class="card-title text-center">${sign_in}</h5>
                        <form class="form-signin" method="post">
                            <div class="form-group">
                                <label for="exampleInputEmail1">${email_address}</label>
                                <input name="input_email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">${password}</label>
                                <input name="input_password" type="password" class="form-control" id="exampleInputPassword1">
                            </div>
                            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">${sign_in}</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>