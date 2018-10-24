var slider = document.getElementById("bet");
var output = document.getElementById("betVal");
output.innerHTML = slider.value; 

slider.oninput = function() {
    output.innerHTML = this.value;
}
