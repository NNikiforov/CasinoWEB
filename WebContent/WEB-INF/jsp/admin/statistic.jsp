
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<u:header />
	<div class="container-fluid my-2 py-2 px-0 rounded" id="container">
		<u:navigator currentPosition="Statistic" />
		<c:if test="${not empty yield}">
				<h3 class="text-center m-2">
					<b>Current casino's yield:</b>
					<fmt:setLocale value="en_US" />
					<fmt:formatNumber value="${yield}" type="currency" />
				</h3>
			</c:if>
		<c:choose>
			<c:when test="${not empty punters}">
				<div id="stat" class="container tab-pane active px-0 py-2">
					<table id="table" class="display table table-striped w-100">
						<thead>
							<tr>
								<th class="text-center">Name</th>
								<th class="text-center">Login</th>
								<th class="text-center">Cash</th>
								<th class="text-center">Rating</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="punter" items="${punters}">
								<tr>
									<td class="text-center">${punter.getName()} ${punter.getSurname()}</td>
									<td class="text-center">${punter.getLogin()}</td>
									<td>${punter.getCash()}</td>
									<td class="px-0"><c:out value="${punter.getRating()}" /></td>
									<td class="text-center">
										<form action="block.html" method="POST">
											<c:choose>
												<c:when test="${punter.isBlocked() != 0}">
													<button class="btn btn-danger" type="submit"
														name="identity" value="${punter.getIdentity()}"> Unlock </button>
												</c:when>
												<c:otherwise>
													<button class="btn" type="submit" name="identity"
														value="${punter.getIdentity()}"> Block </button>
												</c:otherwise>
											</c:choose>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th class="text-center">Name</th>
								<th class="text-center">Login</th>
								<th class="text-center">Cash</th>
								<th class="text-center">Rating</th>
								<th></th>
							</tr>
						</tfoot>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<div class="p-5 text-center">
					<h4>Statistic is clear.</h4>
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
	<c:if test="${not empty punters}">
		<script src="/Casino/js/datatables.js"></script>
	</c:if>
</body>
</html>