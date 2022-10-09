$(document).ready(function() {
    $("form").submit(function(e){
        e.preventDefault();
    });
    viewAllCenters();
});

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
                		            +responseText.payload[i].centerName1
                		    		+"</td>"
                		    		+"<td>"
                		            +responseText.payload[i].centerName2
                		    		+"</td>"
                		    		+"<td>"
                                    +responseText.payload[i].centerAddress
                                    +"</td>"
                		    		+"<td>"
                                    +responseText.payload[i].centerContactNo
                                    +"</td>"
                                    +"<td>"
                                    +responseText.payload[i].centerEmail
                                    +"</td>"
                                    +"<td>"
                                    +responseText.payload[i].approve
                                    +"</td>"
                                    +"<td>"
                                    +"<div class=\"d-flex\">"
                                    +"<button onclick=\"editHotel("+responseText.payload[i].centerId+")\" class=\"btn btn-primary shadow btn-xs sharp me-1\"><i class=\"fa fa-pencil-alt\"></i></button>"
                                    +"<button onclick=\"showDeleteHotel("+responseText.payload[i].centerId+")\" class=\"btn btn-danger shadow btn-xs sharp\"><i class=\"fa fa-trash\"></i></button>"
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