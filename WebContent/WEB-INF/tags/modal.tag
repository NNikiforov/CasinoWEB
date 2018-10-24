<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true"
	type="java.lang.String"%>
<%@attribute name="message" required="false" rtexprvalue="true"
	type="java.lang.String"%>

<div class="modal" id="modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">${title}</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body"><p>${message}</p></div>
			<div class="modal-footer">
				<button type="button" class="btn" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>