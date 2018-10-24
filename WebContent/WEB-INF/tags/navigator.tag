<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="currentPosition" required="true" rtexprvalue="true"
	type="java.lang.String"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="nav nav-tabs justify-content-center" role="tablist">
	<c:choose>
		<c:when test="${currentPosition == 'Rules'}">
			<li class="nav-item mx-2"><a class="nav-link active"
				data-toggle="tab" href="#rules"> Rules </a></li>
		</c:when>
		<c:otherwise>
			<li class="nav-item mx-2"><a class="nav-link"
				href="/Casino/general/main.html"> Rules </a></li>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${currentPosition == 'Game'}">
			<li class="nav-item mx-2"><a class="nav-link active"
				data-toggle="tab" href="#game"> Game </a></li>
		</c:when>
		<c:otherwise>
			<c:if test="${authorizedUser.getRole() == 'PUNTER'}">
				<li class="nav-item mx-2"><a class="nav-link"
					href="/Casino/punter/game.html"> Game </a></li>
			</c:if>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${currentPosition == 'Top'}">
			<li class="nav-item mx-2"><a class="nav-link active"
				data-toggle="tab" href="#top"> Top </a></li>
		</c:when>
		<c:otherwise>
			<li class="nav-item mx-2"><a class="nav-link"
				href="/Casino/general/top.html"> Top </a></li>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${currentPosition == 'History'}">
			<li class="nav-item mx-2"><a class="nav-link active"
				data-toggle="tab" href="#history"> History </a></li>
		</c:when>
		<c:otherwise>
			<c:if test="${authorizedUser.getRole() == 'PUNTER'}">
				<li class="nav-item mx-2"><a class="nav-link"
					href="/Casino/punter/history.html"> History </a></li>
			</c:if>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${currentPosition == 'Bank operations'}">
			<li class="nav-item mx-2"><a class="nav-link active"
				data-toggle="tab" href="#bank"> Bank operations </a></li>
		</c:when>
		<c:otherwise>
			<c:if test="${authorizedUser.getRole() == 'ADMIN'}">
				<li class="nav-item mx-2"><a class="nav-link"
					href="/Casino/admin/bank.html"> Bank operations </a></li>
			</c:if>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${currentPosition == 'Statistic'}">
			<li class="nav-item mx-2"><a class="nav-link active"
				data-toggle="tab" href="#stat"> Statistic </a></li>
		</c:when>
		<c:otherwise>
			<c:if test="${authorizedUser.getRole() == 'ADMIN'}">
				<li class="nav-item mx-2"><a class="nav-link"
					href="/Casino/admin/statistic.html"> Statistic </a></li>
			</c:if>
		</c:otherwise>
	</c:choose>
</ul>
