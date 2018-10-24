<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link href="/Casino/css/main.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<title>Casino</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<a href="/Casino/general/main.html" class="navbar-brand text-light noselect" 
		title="Enter a name using only english symbols.">
			<img src="/Casino/img/logo.png" width="30" height="30" 
			class="d-inline-block align-top  mr-sm-2" alt="" />
			CASINO
		</a>
		<form class="form-inline my-0" action="register.html">
			<button class="btn btn-warning my-2 my-sm-0" type="submit">Sign up</button>
		</form>
	</nav>
	<c:if test="${not empty message}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<c:out value="${message}" />
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>

	<div class="container my-4">
		<div class="row justify-content-center text-center">
			<div class="col-*">
				<div class="card">
					<div class="card-body">
						<img src="/Casino/img/logo.png" width="80" height="80"
							class="d-inline-block align-top" alt="" />
						<h5 class="card-title">Sign in to CASINO</h5>
						<form action="login.html" method="POST">
							<div class="form-group">
								<label for="emailInput">Email address</label> <input
									type="email" class="form-control" required="required"
									id="emailInput" aria-describedby="emailHelp"
									placeholder="Enter email" name="login">
							</div>
							<div class="form-group">
								<label for="passwordInput">Password</label> <input
									type="password" class="form-control" required="required"
									id="passwordInput" placeholder="Password" name="password">
							</div>
							<button type="submit" class="btn btn-primary">Sign in</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer
		class="container-fluid fixed-bottom bg-dark text-white-50 text-center">
		<div class="row align-items-center noselect">
			<div class="col">
				<p>© 2018 CASINO</p>
			</div>
			<div class="col">
				<p>
					Editor:<br> Nikiforov Nikita
				</p>
			</div>
			<div class="col">
				<p>Email: nikkika2011@gmail.com</p>
			</div>
		</div>
	</footer>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>