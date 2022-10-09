$(document).ready(function() {
//    $("form").submit(function(e){
//        e.preventDefault();
//    });
   // viewAllCenters();
    viewAllActiveCenters();
});

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
                	    for (i = 0; i<responseText.payload.length; i++) {
                	    	$('#docPost').append(
                	    	 "<div class=\"col-12 col-sm-6 col-md-12 col-lg-6 col-xl-4\">"
                             +"<div class=\"dc-docpostholder\">"
                                +"<div class=\"dc-docpostcontent\">"
                                +"<div class=\"dc-title\">"
                                +" <a href=\"javascript:void(0)\" class=\"dc-docstatus\">"+responseText.payload[i].address+"</a>"
                                +" <h3><a href=\"javascript:void(0);\">"+responseText.payload[i].firstName+" "+responseText.payload[i].lastName+"</a> <i class=\"fa fa-award dc-awardtooltip\"><em>Medical Registration Verified</em></i> <i class=\"fa fa-check-circle dc-awardtooltip\"><em>Medical Registration Verified</em></i></h3>"
                                +" <ul class=\"dc-docinfo\">"
                                +" <li>"
                                +" <em>"+responseText.payload[i].email+"</em>"
                                +" <li>"
                                +" <li>"
                                +" <em>"+responseText.payload[i].contactNo+"</em>"
                                +" <li>"
                                +" <li>"
                                +"<span class=\"dc-stars\"><span></span></span><em>2100 Feedback</em>"
                                +" <li>"
                                +" </ul>"
                                +" </div>"
                                +" <div class=\"dc-doclocation\">"
                                +"<a href=\"javascript:void(0);\" class=\"dc-btn\">Book Now</a>"
                                +" </div>"
                                +" </div>"
                                +" </div>"
                                +" </div>"
                		    );
                	    }
				},
				error : function(e) {
					// alert(e);
					console.log(e)
				}
			});
}