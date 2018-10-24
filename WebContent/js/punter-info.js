let info = (function () {
	var lastValue;
    function show(id) {
    	if (lastValue) {
    		lastValue.style = "display: none;";
    	}
    	lastValue = document.getElementById(id);
    	if (lastValue) {
    		lastValue.style = "";
    	}
    }
    return {
    	show
    }
}());