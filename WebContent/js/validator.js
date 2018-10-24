let validator = (function () {
    function validatePasswordForm() {
        let pswd1 = document.getElementsByName('password')[0];
        let pswd2 = document.getElementsByName('password2')[0];
        if (pswd1.value !== pswd2.value) {
        	pswd1.setAttribute("value", "");
        	pswd2.setAttribute("value", "");
        	let message = "This password " +
				"does not match that entered in the password " +
				"field, please try again.";
        	resetError('container', message);
        	return false;
        } else {
        	return true;
        }
    }
    
    function validateGameForm() {
        let cardValues = document.getElementsByName('card-value');
        let cardValue;
        for (var i = 0; i < cardValues.length; i++) {
        	if (cardValues[i].checked) {
        		cardValue = cardValues[i];
        		break;
        	}
        }
        if (!cardValue){
        	let message = "Sorry, you must select the value of the card."; 
        	resetError('bet-container', message);
        	return false;
        }
        let cardSuits = document.getElementsByName('card-suit');
        let cardSuit;
        for (var i = 0; i < cardSuits.length; i++) {
        	if (cardSuits[i].checked) {
        		cardSuit = cardSuits[i];
        		break;
        	}
        }
        if (!cardSuit){
        	let message = "Sorry, you must select the suit of the card."; 
        	resetError('bet-container', message);
        	return false;
        }
        return true;
    }

    function validateBankForm() {
    	let selector = document.getElementsByName('login')[0];
    	let punter;
        if (!selector || selector.value == -1) {
        	let message = "Sorry, you must select punter."; 
        	resetError('bank-container', message);
        	return false;
        }
        let operations = document.getElementsByName('operation');
        let operation;
        for (var i = 0; i < operations.length; i++) {
        	if (operations[i].checked) {
        		operation = operations[i];
        		break;
        	}
        }
        if (!operation){
        	let message = "Sorry, you must select bank operation."; 
        	resetError('bank-container', message);
        	return false;
        }
        let amountElement = document.getElementsByName('amount')[0];
        let amount;
        if (!amountElement || !amountElement.value.match('[1-9][0-9]{0,6}')){
        	let message = "Sorry, you must select correct amount."; 
        	resetError('bank-container', message);
        	return false;
        }
        return true;
    }

    function resetError(previousElemName, messageText) {
    	let error = document.getElementsByName('error')[0];

    	if (error) {
    		error.parentNode.removeChild(error);
    	}

    	let div = document.createElement('div');
    	let button = document.createElement('button');
    	let span = document.createElement('span');
    	let symbol = document.createTextNode("х");
    	let message = document.createTextNode(messageText);

    	div.className = "alert alert-danger alert-dismissible fade show";
    	button.className = "close";

    	div.setAttribute("name", "error");
    	div.setAttribute("role", "alert");
    	button.setAttribute("type", "button");
    	button.setAttribute("data-dismiss", "alert");
    	button.setAttribute("aria-label", "Close");
    	span.setAttribute("aria-hidden","true");

    	span.appendChild(symbol);
    	button.appendChild(span);
    	div.appendChild(message);
    	div.appendChild(button);

    	document.getElementsByName(previousElemName)[0].parentNode.insertBefore(div, document.getElementsByName(previousElemName)[0]);
    }

    return {
    	validatePasswordForm,
    	validateGameForm,
    	validateBankForm
    }
}());