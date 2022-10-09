$(document).ready(function() {
    $("form").submit(function(e){
        e.preventDefault();
    });
    viewAllDoctors();
    viewAllCenters();
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
                		            +responseText.payload[i].firstName +" "+responseText.payload[i].lastName
                		    		+"</td>"
                		            +"<td>"
                		            +responseText.payload[i].specialization
                		    		+"</td>"
                			        +"<td>"
                		            +responseText.payload[i].nic
                		    		+"</td>"
                                    +"<td>"
                                    +"<div>"
                                    +"<button onclick=\"editHotel("+responseText.payload[i].doctorId+")\" class=\"btn btn-success shadow btn-xs sharp me-1\"><i class=\"fa fa-check\"></i></button>"
                                     +"&nbsp"
                                    +"<button onclick=\"showDeleteHotel("+responseText.payload[i].doctorId+")\" class=\"btn btn-danger shadow btn-xs sharp\"><i class=\"fa fa-ban\"></i></button>"
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

function viewAllCenters(){
	$.ajax({
					url : "center/getAll",
					type : "GET",
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					dataType : 'json',
					success: function (responseText) {
	                	    for (i = 0; i<responseText.payload.length; i++) {
	                	    	$('#centerTbody').append(
	                		    	    "<tr>"
	                		    		+"<td class=\"hide_me\">"
	                		    	    +responseText.payload[i].centerId
	                		    		+"</td>"
	                		            +"<td>"
	                		            +responseText.payload[i].firstName +" "+responseText.payload[i].lastName
	                		    		+"</td>"
	                		    		+"<td>"
	                		            +responseText.payload[i].contactNo
	                		    		+"</td>"
	                		    		+"<td>"
	                                    +responseText.payload[i].email
	                                    +"</td>"
	                                    +"<td>"
	                                    +"<div>"
	                                    +"<button onclick=\"editHotel("+responseText.payload[i].centerId+")\" class=\"btn btn-success shadow btn-xs sharp me-1\"><i class=\"fa fa-check\"></i></button>"
	                                    +"&nbsp"
	                                    +"<button onclick=\"showDeleteHotel("+responseText.payload[i].centerId+")\" class=\"btn btn-danger shadow btn-xs sharp\"><i class=\"fa fa-ban\"></i></button>"
	                                    +"</div>"
	                                    +"</td>"
	                		    	    +"</tr>" );

	                	    }
	                         $('#centerTable').DataTable();

					},
					error : function(e) {
						// alert(e);
						console.log(e)
					}
				});
	}