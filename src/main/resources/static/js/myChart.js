//this helps translate the HTML craziness into a much more readable array
var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

//initializing new variables
var arrayLength = chartJsonArray.length;
//initialize empty arrays
var numericData = [];
var labelData = [];

//standard loop, apparently JavaScript loops look a lot like Java
for(var i = 0; i < arrayLength; i++) {
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

//For a Pie Chart, this is in javascript
new Chart(document.getElementById("myPieChart"), {
	type: 'pie',
	options: {
		plugins: {
		    title: {
		      display: true,
		      text: 'Project Statuses'
		    }
		}
	  },
	data: {
		labels: labelData,
		datasets: [{
			label: 'My First dataset',
			//array of colors, these are codes associated with specific colors and they'll be assinged to the different data points within the
			//pie chart
			backgroundColor: ["#3e95cd","#8e5ea2","#3cba9f"],
			//commented out border color because I prefer the look of whitespace over the hot pink
			//borderColor: 'rgb(255,99,132)',
			data: numericData
		}]
	}
});

//takes in the array values seen below as HTML, and returns the format seen in the comment below
//"[{"value":1,"label": "COMPLETED"},{"value":2,"label": "INPROGRESS"},{"value":1,"label": "NOTSTARTED"}]"
function decodeHtml(html) {
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}