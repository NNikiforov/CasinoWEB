<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags"%>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link href="/Casino/css/main.css" rel="stylesheet" type="text/css">
<link href="/Casino/css/slider.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<title>Casino</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty authorizedUser}">
			<c:choose>
				<c:when test="${authorizedUser.getRole() == 'ADMIN'}">
					<c:set var="role" scope="request" value="${1}" />
				</c:when>
				<c:otherwise>
					<c:set var="role" scope="request" value="${2}" />
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:set var="role" scope="request" value="${3}" />
		</c:otherwise>
	</c:choose>
	<u:header />

	<div class="container-fluid my-2 p-2 rounded" id="container">
		<u:navigator currentPosition="Bank operations"></u:navigator>
		<div class="tab-content">
			<div id="rules" class="container p-1 tab-pane active">
				<h3>How to work?</h3>
				<p>At this window you can Withdraw or Deposit some punters money.</p>
			</div>
			<div id="game" class="container tab-pane active">
				<form action="/Casino/admin/bank.html" method="POST"
					name="bank-container"
					onSubmit="document.getElementById('submit').disabled=true;">
					<div class="card-chooser d-inline-block text-center m-2">
						<div class="form-group">
							<label for="selector">Punter's login:</label> <select
								onchange="info.show(this.value)" class="form-control"
								name="login" id="selector">
								<option value="-1" disabled selected>Choose punter...</option>
								<c:if test="${not empty punters}">
									<c:forEach var="punter" items="${punters}">
										<option value="${punter.getIdentity()}">${punter.getLogin()}</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
						<c:if test="${not empty punters}">
							<c:forEach var="punter" items="${punters}">
								<div id="${punter.getIdentity()}" style="display: none;">
									<p class="text-left">
										<b>Punter:</b> ${punter.getName()} ${punter.getSurname()}
									</p>
									<p class="text-left">
										<b>Cash:</b> ${punter.getCash()}
									</p>
								</div>
							</c:forEach>
						</c:if>
					</div>
					<div class="card-chooser d-inline-block m-2 align-top text-center">
						<div class="align-top">
							<label class="d-block" for="card-suit">Choose operation:</label>
							<div class="row btn-group btn-group-toggle w-100 m-0"
								data-toggle="buttons" id="card-suit">
								<label class="col-6 btn btn-secondary"> <input
									type="radio" name="operation" value="Withdraw"
									autocomplete="off"> Withdraw
								</label> <label class="col-6 btn btn-secondary"> <input
									type="radio" name="operation" value="Deposit"
									autocomplete="off"> Deposit
								</label>
							</div>
						</div>
						<div class="m-2 align-top">
							<label class="d-block" for="in-amount">Amount:</label>
							<div class="input-group mb-3" id="in-amount">
								<div class="input-group-prepend">
									<span class="input-group-text">$</span>
								</div>
								<input type="text" class="form-control" name="amount">
								<div class="input-group-append">
									<span class="input-group-text">.00</span>
								</div>
							</div>
						</div>
						<button type="submit" class="btn btn-success"
							onclick="return validator.validateBankForm();" id="submit">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<footer class="container-fluid bg-dark text-white-50 text-center">
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
	<script src="/Casino/js/punter-info.js"></script>
	<script src="/Casino/js/validator.js"></script>
</body>
</html>