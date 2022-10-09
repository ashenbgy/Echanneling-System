$(document).ready(function() {
	$("form").submit(function(e) {
		e.preventDefault();
	});
	viewAllActiveCenters();
});

function saveCenter() {
	var userId = document.getElementById('userId').innerHTML;
	var centerId =$('#cent').find(":selected").val();
	var startTime = $("#time1").val();
	var endTime = $("#time2").val();
	var chanelDay = $('#channelDay').find(":selected").text();

	var requestObj = {
		userId : userId,
		centerId : centerId,
		startTime : startTime,
		endTime : endTime,
		chanelDay : chanelDay
	}
	$.ajax({
		url : "doctor/register",
		type : "POST",
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		dataType : "json",
		data : JSON.stringify(requestObj),
		success : function(data) {
			alert(data.messages);
			location.reload();
		},
		error : function(data) {
			var err = eval("(" + data.responseText + ")");
			alert(err.details);
		}
	});

}

function viewAllActiveCenters() {
	$.ajax({
		url : "center/getAllActive",
		type : "GET",
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		dataType : 'json',
		success : function(responseText) {
			for (j = 0; j < responseText.payload.length; j++) {
				$('#cent').append(
						"<option value=" + responseText.payload[j].centerId
								+ ">" + responseText.payload[j].firstName + " "
								+ responseText.payload[j].lastName
								+ "</option>").trigger("chosen:updated");
			}
		},
		error : function(e) {
			// alert(e);
			console.log(e)
		}
	});
}