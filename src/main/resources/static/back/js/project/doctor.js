$(document).ready(function() {
    $("form").submit(function(e){
        e.preventDefault();
    });
    viewAllDoctors();
});

function viewAllDoctors(){
$.ajax({
				url : "doctor/getAll",
				type : "GET",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				dataType : 'json',
				success: function (responseText) {
                	    for (i = 0; i<responseText.payload.length; i++) {
                	    	$('#docTbody').append(
                		    	    "<tr>"
                		    		+"<td class=\"hide_me\">"
                		    	    +responseText.payload[i].doctorId
                		    		+"</td>"
                		            +"<td>"
                		            +responseText.payload[i].firstName
                		    		+"</td>"
                		            +"<td>"
                		            +responseText.payload[i].specialization
                		    		+"</td>"
                			        +"<td>"
                		            +responseText.payload[i].nic
                		    		+"</td>"
                		    		+"<td>"
                                    +responseText.payload[i].contactNo
                                    +"</td>"
                                    +"<td>"
                                    +responseText.payload[i].email
                                    +"</td>"
                                    +"<td>"
                                    +responseText.payload[i].address
                                    +"</td>"
                                    +"<td>"
                                    +"<div class=\"d-flex\">"
                                    +"<button onclick=\"editHotel("+responseText.payload[i].doctorId+")\" class=\"btn btn-primary shadow btn-xs sharp me-1\"><i class=\"fa fa-pencil-alt\"></i></button>"
                                    +"<button onclick=\"showDeleteHotel("+responseText.payload[i].doctorId+")\" class=\"btn btn-danger shadow btn-xs sharp\"><i class=\"fa fa-trash\"></i></button>"
                                    +"</div>"
                                    +"</td>"
                		    	    +"</tr>" );

                	    }
                         $('#docTable').DataTable();

				},
				error : function(e) {
					// alert(e);
					console.log(e)
				}
			});
}
function process(){
$.ajax({
				url : "appointment/process",
				type : "GET",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				dataType : 'json',
				success: function (responseText) {

				},
				error : function(e) {
					// alert(e);
					console.log(e)
				}
			});
}

function printAllDoctors(){
	var w = window.open("docreport");
}