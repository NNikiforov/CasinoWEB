<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true"
	type="java.lang.String"%>
<%@attribute name="sequence" required="false" rtexprvalue="true"
	type="java.lang.String"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty sequence}">
	<div class="col block text-center">
		<label for="${title}"><c:out value="${title}" /></label>
		<div id="${title}" class="card-slider carousel slide"
			data-ride="carousel" data-pause="false">
			<c:set var="count" scope="request" value="0" />
			<div class="carousel-inner" name="card-slider">
				<c:forTokens items="${sequence}" delims=" " var="card">
					<c:choose>
						<c:when test="${count == 0}">
							<div class="carousel-item playing-card active" id="${card}"></div>
						</c:when>
						<c:otherwise>
							<div class="carousel-item playing-card" id="${card}"></div>
						</c:otherwise>
					</c:choose>
					<c:set var="count" scope="request" value="${count + 1}" />
				</c:forTokens>
			</div>
			<ol class="carousel-indicators">
				<c:forEach var="i" begin="1" end="${count}">
					<c:choose>
						<c:when test="${i == 1}">
							<li data-target="${title}" class="active" />
						</c:when>
						<c:otherwise>
							<li data-target="${title}" />
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ol>
		</div>
	</div>
</c:if>