<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<a href="main.html" class="navbar-brand text-light noselect"><img
			src="/Casino/img/logo.png" width="30" height="30"
			class="d-inline-block align-top  mr-sm-2" alt="" /> CASINO </a>
		<form class="my-0" action="login.html">
			<button class="btn btn-success my-0 mr-sm-2" type="submit">
				Sign in</button>
		</form>
	</nav>
	<c:if test="${not empty message}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert" name="error">
			<c:out value="${message}" />
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>
	<div class="container my-4" name="container">
		<div class="row justify-content-center text-center">
			<div class="col-*">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Registration</h5>
						<form action="register.html" method="POST" id="registerForm"
							onSubmit="document.getElementById('submit').disabled=true;">
							<div class="input-group mb-3"
								title="Enter a name using only english symbols.">
								<div class="input-group-prepend noselect">
									<span class="input-group-text" id="nameInput">Name</span>
								</div>
								<input type="text" class="form-control" required="required"
									pattern="[A-Za-z]+" aria-describedby="nameInput" name="name"
									placeholder="Enter your name.">
							</div>
							<div class="input-group mb-3"
								title="Enter a surname using only english symbols.">
								<div class="input-group-prepend noselect">
									<span class="input-group-text" id="surnameInput">Surname</span>
								</div>
								<input type="text" name="surname" class="form-control"
									required="required" pattern="[A-Za-z]+"
									aria-describedby="surnameInput"
									placeholder="Enter your surname.">
							</div>
							<div class="input-group mb-3" title="Enter an email.">
								<div class="input-group-prepend noselect">
									<span class="input-group-text" id="emailInput">&#128231;</span>
								</div>
								<input type="email" name="login" class="form-control"
									required="required" aria-describedby="emailInput"
									placeholder="Enter your email.">
							</div>
							<div class="input-group mb-3" title="Enter new password.">
								<div class="input-group-prepend noselect w-10">
									<span class="input-group-text" id="pswdInput">&#128274;</span>
								</div>
								<input type="password" name="password" class="form-control"
									required="required" aria-describedby="pswdInput"
									placeholder="Enter your password.">
							</div>
							<div class="input-group mb-3" title="Confirm new password.">
								<div class="input-group-prepend noselect">
									<span class="input-group-text" id="confirmPswdInput">&#10003;</span>
								</div>
								<input type="password" name="password2" class="form-control"
									required="required" aria-describedby="confirmPswdInput"
									placeholder="Confirm your password.">
							</div>
							<div class="row">
								<div class="col form-group mb-3"
									title="Enter your age from 1 to 99.">
									<label for="ageInput">Age</label> <input type="text" name="age"
										id="ageInput" class="form-control" required="required"
										aria-describedby="ageInput" placeholder="Age."
										pattern="[1-9][0-9]?">
								</div>
								<div class="col form-group">
									<label for="genderSelect">Gender</label> <select
										id="genderSelect" class="form-control" name="gender">
										<option selected>Male</option>
										<option>Female</option>
									</select>
								</div>
							</div>
							<button type="submit" class="btn btn-primary"
								onclick="return validator.validatePasswordForm()"
								id="submit">Sign up</button>
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
	<script src="/Casino/js/validator.js"></script>
</body>
</html>