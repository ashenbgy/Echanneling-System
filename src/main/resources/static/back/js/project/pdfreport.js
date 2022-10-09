$(document).ready(function() {
    $("form").submit(function(e){
        e.preventDefault();
    });
    allDoctors();
});

function allDoctors(){
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
	                	    	$('#docPdfTbody').append(
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
	                		    	    +"</tr>" );

	                	    }
	                         
	                         window.addEventListner("load",window.print());
					},
					error : function(e) {
						// alert(e);
						console.log(e)
					}
				});
	}