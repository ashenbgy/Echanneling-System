$(document).ready(function() {
    $("form").submit(function(e){
        e.preventDefault();
    });
    viewAllPatient();
});

function viewAllPatient(){
$.ajax({
				url : "patient/getAll",
				type : "GET",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				dataType : 'json',
				success: function (responseText) {
                	    for (i = 0; i<responseText.payload.length; i++) {
                	    	$('#patientTbody').append(
                		    	    "<tr>"
                		    		+"<td class=\"hide_me\">"
                		    	    +responseText.payload[i].patientId
                		    		+"</td>"
                		            +"<td>"
                		            +responseText.payload[i].firstName
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
                                    +"<button onclick=\"editPatient("+responseText.payload[i].patientId+")\" class=\"btn btn-primary shadow btn-xs sharp me-1\"><i class=\"fa fa-pencil-alt\"></i></button>"
                                    +"<button onclick=\"showDeletePatient("+responseText.payload[i].patientId+")\" class=\"btn btn-danger shadow btn-xs sharp\"><i class=\"fa fa-trash\"></i></button>"
                                    +"</div>"
                                    +"</td>"
                		    	    +"</tr>" );

                	    }
                         $('#patientTable').DataTable();

				},
				error : function(e) {
					// alert(e);
					console.log(e)
				}
			});
}