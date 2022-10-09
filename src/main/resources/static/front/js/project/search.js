$(document).ready(function() {
/* $("form").submit(function(e){
        e.preventDefault();
 });*/

  viewAllActiveDoctors();
  viewAllActiveCenters();

});
function viewAllActiveDoctors(){
$.ajax({
				url : "doctor/getAllActive",
				type : "GET",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				dataType : 'json',
				success: function (responseText) {
                	    for (i = 0; i<responseText.payload.length; i++) {
                	    	$('#docItems').append(
                            "<option value=" + responseText.payload[i].doctorId + ">"
                            +"Dr. "
                            + responseText.payload[i].firstName+" "+responseText.payload[i].lastName
                            + "</option>").trigger("chosen:updated");
                	    }
				},
				error : function(e) {
					// alert(e);
					console.log(e)
				}
			});
}

function viewAllActiveCenters(){
$.ajax({
				url : "center/getAllActive",
				type : "GET",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				dataType : 'json',
				success: function (responseText) {
                	    for (j = 0; j<responseText.payload.length; j++) {
                	    	$('#cent').append(
                	    	"<option value="+responseText.payload[j].centerId+">"
                            +responseText.payload[j].firstName +" "+responseText.payload[j].lastName
                            +"</option>"
                		    ).trigger("chosen:updated");
                	    }
				},
				error : function(e) {
					// alert(e);
					console.log(e)
				}
			});
}
 function search() {
		window.open("searchDetails");
}

