
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link href="/Casino/css/main.css" rel="stylesheet" type="text/css">
<link href="/Casino/css/datatables.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>Casino</title>
</head>
<body>
	<u:header/>
	<div class="container-fluid my-2 py-2 px-0 rounded" id="container">
		<u:navigator currentPosition="History" />
		<c:choose>
			<c:when test="${not empty bets}">
			<div id="history" class="container tab-pane active px-0 py-2">
				<table id="table" class="display table table-striped w-100">
					<thead>
						<tr>
							<th>Selected card</th>
							<th>Amount</th>
							<th>Bet time</th>
							<th>Result</th>
							<th>Profit</th>
							<th>Result deck</th>
							<th></th>
						</tr>
					</thead>
						<tbody>
							<c:forEach var="bet" items="${bets}">
								<tr>
									<td class="text-center">${bet.getCard().toCustomString()}</td>
									<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
											value="${bet.getAmount()}" type="currency" /></td>
									<td><fmt:formatDate type="both" timeZone="GMT+3"
											dateStyle="short" timeStyle="short" value="${bet.getTime()}" />
									</td>
									<td><c:out value="${bet.getResult().getText()}" /></td>
									<td class="px-0"><c:out value="${bet.getProfit()}" /></td>
									<td class="px-0">
										<div class="dropdown">
											<button class="btn btn-secondary dropdown-toggle"
												type="button" id="dropdownMenuButton" data-toggle="dropdown"
												aria-haspopup="true" aria-expanded="false">Deck</button>
											<div class="dropdown-menu"
												aria-labelledby="dropdownMenuButton">
												<p>
													<c:out value="${bet.getDeck()}" />
												</p>
											</div>
										</div>
									</td>
									<td>
										<form action="remove.html" method="POST">
											<button class="btn" type="submit" name="betIdentity"
												value="${bet.getIdentity()}">&#128465;</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
						<tr>
							<th>Selected card</th>
							<th>Amount</th>
							<th>Bet time</th>
							<th>Result</th>
							<th>Profit</th>
							<th>Result deck</th>
							<th></th>
						</tr>
					</tfoot>
				</table>
			</div>
			</c:when>
			<c:otherwise>
				<div class="p-5 text-center">
					<h4>Your history is clear.</h4>
				</div>
			</c:otherwise>
		</c:choose>
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
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
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
	<c:if test="${not empty bets}">
		<script src="/Casino/js/datatables.js"></script>
	</c:if>
</body>
</html>