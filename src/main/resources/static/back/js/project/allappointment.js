$(document).ready(function() {
    $("form").submit(function(e){
        e.preventDefault();
    });
    viewAppointments1();
});

function viewAppointments1(){
$.ajax({
				url : "appointment/getAll",
				type : "GET",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				dataType : 'json',
				success: function (responseText) {
                	    for (i = 0; i<responseText.payload.length; i++) {
                	    	$('#appTbody1').append(
                		    	    "<tr>"
                		    		+"<td class=\"hide_me\">"
                		    	    +responseText.payload[i].appId
                		    		+"</td>"
                		            +"<td>"
                		            +responseText.payload[i].appDate
                		    		+"</td>"
                		    		+"<td>"
                		            +responseText.payload[i].startTime
                		    		+"</td>"
                		    		+"<td>"
                		            +responseText.payload[i].endTime
                		    		+"</td>"
                		    		+"<td>"
                                    +responseText.payload[i].doctorMappingCenter.doctor.firstName +" "+ responseText.payload[i].doctorMappingCenter.doctor.lastName
                                    +"</td>"
                                    +"<td>"
                                    +responseText.payload[i].doctorMappingCenter.center.firstName
                                    +"</td>"
                                    +"<td>"
                                    +"<div class=\"d-flex\">"
                                    +"<button onclick=\"editHotel("+responseText.payload[i].appId+")\" class=\"btn btn-primary shadow btn-xs sharp me-1\"><i class=\"fa fa-pencil-alt\"></i></button>"
                                    +"<button onclick=\"showDeleteHotel("+responseText.payload[i].appId+")\" class=\"btn btn-danger shadow btn-xs sharp\"><i class=\"fa fa-trash\"></i></button>"
                                    +"</div>"
                                    +"</td>"
                		    	    +"</tr>" );

                	    }
                         $('#appTable1').DataTable();

				},
				error : function(e) {
					// alert(e);
					console.log(e)
				}
			});
}

function viewAppointmentsCenter(){
	$.ajax({
					url : "appointment/center/" + centerId,
					type : "GET",
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					dataType : 'json',
					success: function (responseText) {
	                	    for (i = 0; i<responseText.payload.length; i++) {
	                	    	$('#appTbody1').append(
	                		    	    "<tr>"
	                		    		+"<td class=\"hide_me\">"
	                		    	    +responseText.payload[i].appId
	                		    		+"</td>"
	                		            +"<td>"
	                		            +responseText.payload[i].appDate
	                		    		+"</td>"
	                		    		+"<td>"
	                		            +responseText.payload[i].startTime
	                		    		+"</td>"
	                		    		+"<td>"
	                                    +responseText.payload[i].endTime
	                                    +"</td>"
	                                    +"<td>"
	                                    +"<div class=\"d-flex\">"
	                                    +"<button onclick=\"editHotel("+responseText.payload[i].appId+")\" class=\"btn btn-primary shadow btn-xs sharp me-1\"><i class=\"fa fa-pencil-alt\"></i></button>"
	                                    +"<button onclick=\"showDeleteHotel("+responseText.payload[i].appId+")\" class=\"btn btn-danger shadow btn-xs sharp\"><i class=\"fa fa-trash\"></i></button>"
	                                    +"</div>"
	                                    +"</td>"
	                		    	    +"</tr>" );

	                	    }
	                         $('#appTable1').DataTable();

					},
					error : function(e) {
						// alert(e);
						console.log(e)
					}
				});
	}

function viewAppointmentsDoctor(){
	$.ajax({
					url : "appointment/doctor/" + doctorId,
					type : "GET",
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					dataType : 'json',
					success: function (responseText) {
	                	    for (i = 0; i<responseText.payload.length; i++) {
	                	    	$('#appTbody1').append(
	                		    	    "<tr>"
	                		    		+"<td class=\"hide_me\">"
	                		    	    +responseText.payload[i].appId
	                		    		+"</td>"
	                		            +"<td>"
	                		            +responseText.payload[i].appDate
	                		    		+"</td>"
	                		    		+"<td>"
	                		            +responseText.payload[i].startTime
	                		    		+"</td>"
	                		    		+"<td>"
	                                    +responseText.payload[i].endTime
	                                    +"</td>"
	                                    +"<td>"
	                                    +"<div class=\"d-flex\">"
	                                    +"<button onclick=\"editHotel("+responseText.payload[i].appId+")\" class=\"btn btn-primary shadow btn-xs sharp me-1\"><i class=\"fa fa-pencil-alt\"></i></button>"
	                                    +"<button onclick=\"showDeleteHotel("+responseText.payload[i].appId+")\" class=\"btn btn-danger shadow btn-xs sharp\"><i class=\"fa fa-trash\"></i></button>"
	                                    +"</div>"
	                                    +"</td>"
	                		    	    +"</tr>" );

	                	    }
	                         $('#appTable1').DataTable();

					},
					error : function(e) {
						// alert(e);
						console.log(e)
					}
				});
	}