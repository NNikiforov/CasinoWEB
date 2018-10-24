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
		<u:navigator currentPosition="Rules"></u:navigator>
		<div class="tab-content">
			<div id="rules" class="container tab-pane active">
				<h3>How to play?</h3>
				<p>A game of faro was often called a "faro bank". It was played
					with an entire deck of playing cards. One person was designated the
					"banker" and the other player, known as "punter".
				<ul>
					<li>A deck of cards was shuffled and placed inside a "dealing
						box", a mechanical device.</li>
					<li>Cards one by one are pulled out of the deck until the
						chosen card of any suit falls out.</li>
					<li>If the card went to the left of the bank, then the
						punter <b>LOST</b>, otherwise <b>WIN</b>.
					</li>
					<li>If there is a card on both sides, then it is "PLIE".
					And bank is won.</li>
					<li><b>MEGA WIN</b> If the costume of the winning card matches yours,
						then your bet increases.
					</li>
					<li><b>SMALL LOSING</b> If the losing card suit matches yours, then your bet decreases.
					</li>
				</ul>
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
</body>
</html>