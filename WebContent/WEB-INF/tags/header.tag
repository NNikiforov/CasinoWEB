<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-dark bg-dark">
	<a class="navbar-brand text-light noselect"> <img
		src="/Casino/img/logo.png" width="30" height="30"
		class="d-inline-block align-top mr-sm-2" alt="" /> CASINO
	</a>
	<div class="form-inline my-0 noselect">
		<c:choose>
			<c:when test="${not empty authorizedUser}">
				<div class="btn-group mx-2">
					<c:choose>
						<c:when test="${authorizedUser.getRole() == 'PUNTER'}">
							<button type="button" class="btn btn-warning dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">${authorizedUser.getName()}
								${authorizedUser.getSurname()}</button>
							<div class="dropdown-menu dropdown-menu-right">
								<a class="dropdown-item" href="#">Balance:
									${authorizedUser.getCash()}$</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Rating:
									${authorizedUser.getRating()}</a>
							</div>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-warning"
								aria-haspopup="true"
								aria-expanded="false">${authorizedUser.getName()}
								${authorizedUser.getSurname()}</button>
						</c:otherwise>
					</c:choose>
				</div>
				<form class="my-0" action="/Casino/general/logout.html">
					<button class="btn btn-secondary my-0 mr-sm-2" type="submit">
						Sign out</button>
				</form>
			</c:when>
			<c:otherwise>
				<form class="my-0" action="/Casino/general/login.html">
					<button class="btn btn-success my-0 mr-sm-2" type="submit">
						Sign in</button>
				</form>
				<form class="my-0" action="/Casino/general/register.html">
					<button class="btn btn-warning my-0 my-sm-0" type="submit">
						Sign up</button>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</nav>