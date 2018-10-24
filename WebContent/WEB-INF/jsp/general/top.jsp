
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
	<u:header/>
	<div class="container-fluid my-2 p-2 rounded" id="container">
		<u:navigator currentPosition="Top"/>
		<div id="top" class="container tab-pane active py-2">
			<h3>Top of punters.</h3>
			<p>These are the ten most rated punters of the game.</p>
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
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col" />
						<th scope="col">Name</th>
						<th scope="col">Surname</th>
						<th scope="col">Rating</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty top}">
						<c:set var = "count" scope = "page" value = "1"/>
						<c:forEach items="${top}" var = "punter">
         					<tr>
								<th scope="row"><c:out value = "${count}"/></th>
								<td><c:out value = "${punter.getName()}"/></td>
								<td><c:out value = "${punter.getSurname()}"/></td>
								<td><c:out value = "${punter.getRating()}"/></td>
							</tr>
							<c:set var = "count" scope = "page" value = "${count + 1}"/>
      					</c:forEach>
					</c:if>
				</tbody>
			</table>
			<c:if test="${not empty currentPosition}">
				<h4>Your current position: <c:out value="${currentPosition}"></c:out></h4>
			</c:if>
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