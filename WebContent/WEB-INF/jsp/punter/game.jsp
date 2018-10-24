
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags"%>
<html lang="en">
<head>

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
	<u:header />
	<div class="container-fluid my-2 p-2 rounded" id="container">
		<u:navigator currentPosition="Game" />
		<c:choose>
			<c:when test="${authorizedUser.isBlocked() == 0 }">
				<div id="game" class="container tab-pane active">
					<div class="row">
						<u:slider title="Losing card" sequence="${loseSequence}" />
						<div class="col text-center">
							<c:if test="${not empty lastBet}">
								<div class="alert alert-warning m-2" role="alert">
									<b>Your last bet:</b><br>${lastBet}
								</div>
							</c:if>
							<img alt="CROUPIER" src="/Casino/img/croupier.gif" id="croupier">
							<c:if test="${not empty gameResult}">
								<button type="submit" class="btn"
									onclick="return dialogModul.showResult();">Show result</button>
							</c:if>
						</div>
						<u:slider title="Win card" sequence="${winSequence}" />
					</div>
					<form action="game.html" method="POST" name="bet-container"
						onSubmit="document.getElementById('submit').disabled=true;">
						<div class="card-chooser d-inline-block text-center m-2">
							<label for="card-value">Card value:</label>
							<div class="row btn-group btn-group-toggle m-0"
								data-toggle="buttons" id="card-value">
								<label class="col-6 btn btn-warning rounded-0"> <input
									type="radio" name="card-value" value="A" autocomplete="off">
									A
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="2" autocomplete="off">
									2
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="3" autocomplete="off">
									3
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="4" autocomplete="off">
									4
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="5" autocomplete="off">
									5
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="6" autocomplete="off">
									6
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="7" autocomplete="off">
									7
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="8" autocomplete="off">
									8
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="9" autocomplete="off">
									9
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="10" autocomplete="off">
									10
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="J" autocomplete="off">
									J
								</label> <label class="col-6 btn btn-warning m-0"> <input
									type="radio" name="card-value" value="Q" autocomplete="off">
									Q
								</label> <label class="col-12 btn btn-warning m-0 rounded-0"> <input
									type="radio" name="card-value" value="K" autocomplete="off">
									K
								</label>
							</div>
						</div>
						<div class="card-chooser d-inline-block m-2 align-top text-center">
							<div>
								<label class="d-block" for="card-suit">Сard suit:</label>
								<div class="row btn-group btn-group-toggle w-100 m-0"
									data-toggle="buttons" id="card-suit">
									<label class="col-3 btn btn-warning rounded-0"> <input
										type="radio" name="card-suit" value="S" autocomplete="off">
										&#9824;
									</label> <label class="col-3 btn btn-warning"> <input
										type="radio" name="card-suit" value="C" autocomplete="off">
										&#9827;
									</label> <label class="col-3 btn btn-warning"> <input
										type="radio" name="card-suit" value="H" autocomplete="off">
										&#9829;
									</label> <label class="col-3 btn btn-warning rounded-0"> <input
										type="radio" name="card-suit" value="D" autocomplete="off">
										&#9830;
									</label>
								</div>
							</div>
							<div class="m-2 align-top">
								<label class="d-block" for="bet">Bet:</label> <input
									type="range" min="1" max="1000" value="50" class="slider"
									name="bet" id="bet"> <label class="d-block" for="bet">Value:<span
									id="betVal">0</span></label>
							</div>
							<button type="submit" class="btn btn-success"
								onclick="return validator.validateGameForm();" id="submit">Put</button>
						</div>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="p-5 text-center">
					<h4>Your account is blocked.</h4>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${not empty gameResult}">
		<u:modal title="Result" message="${gameResult}" />
	</c:if>
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
	<script src="/Casino/js/validator.js"></script>
	<script src="/Casino/js/slider.js"></script>
	<c:if test="${not empty gameResult}">
		<script src="/Casino/js/result.js"></script>
	</c:if>
</body>
</html>