let dialogModul = (function () {
	var timerId;
    function setTimer() {
    	timerId = setTimeout(function (){
        	$('#modal').modal('show')
    	}, document.getElementsByClassName("carousel-item").length * 2500);
    }

    function showResult() {
    	clearTimeout(timerId);
        $('#modal').modal('show');
    }
return {
	setTimer,
	showResult
}
}());
dialogModul.setTimer();