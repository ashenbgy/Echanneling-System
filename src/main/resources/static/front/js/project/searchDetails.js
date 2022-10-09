$(document).ready(function() {
    searchDetails();
});

function searchDetails() {

	 var doctorId =window.opener.$("#docItems").val()> 0 ?window.opener.$("#docItems").val(): 0;
	 var specialization=window.opener.$("#specialization").val()=="0" ? "" :window.opener.$("#specialization").val();
	 var centerId =window.opener.$("#cent").val()> 0 ?window.opener.$("#cent").val(): 0;

	 var requestObj = {
                  doctorId:doctorId,
                  specialization:specialization,
                  centerId:centerId
     }
	$.ajax({
		url : "patient/search",
        type : "POST",
        headers : {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        dataType : 'json',
        data: JSON.stringify(requestObj),
	    success: function (responseText) {
	    for (i = 0; i<responseText.payload.length; i++) {
	    		$('#searchTBody').append(
	    	    "<tr>"
	    		+"<td>"
	    	    +responseText.payload[i].doctor.firstName +" "+responseText.payload[i].doctor.lastName
	    		+"</td>"
                +"<td>"
                +responseText.payload[i].doctor.specialization
	    		+"</td>"
	    		+"<td>"
	            +responseText.payload[i].center.firstName +" "+responseText.payload[i].center.lastName
		        +"</td>"
		        +"<td>"
	            +responseText.payload[i].center.address
		        +"</td>"
		    	+"<td>"
	            +responseText.payload[i].center.contactNo
		        +"</td>"
		        +"<td>"
                +"<button class=\"btn btn-success\" onClick=\"bookChannel("+responseText.payload[i].docMapId+")\" value=\""+responseText.payload[i].docMapId+"\">Channel</button>"
                +"</td>"
	    	   +"</tr>" );
	    	}
		   },
		   error: function(e){
		          //alert(e);
				   	console.log(e)
		   }
	});
}
function bookChannel(docMapId){
    var userId = document.getElementById('userId').innerHTML;
    if(userId=="" || userId==null){
    alert("Please Sign in to Continue");
    }else{
    $("#appointment").modal();
        $.ajax({
                url : "appointment/viewMapping/" + docMapId,
                type : "GET",
                headers : {
                    'Accept' : 'application/json',
                    'Content-Type' : 'application/json'
                },
                dataType : 'json',
                success: function (responseText) {
                        for (i = 0; i<responseText.payload.length; i++) {

                           var appointmentDate =new Date(responseText.payload[i].appDate);
                           var dd = String(appointmentDate.getDate()).padStart(2, '0');
                           var mm = String(appointmentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
                           var yyyy = appointmentDate.getFullYear();
                           appointmentDate = yyyy + '-' + mm + '-' + dd;

                            if(responseText.payload[i].patient == null){
                            $('#appTBody').append(
                                "<tr>"
                                +"<td>"
                                +"<input type=\"radio\" name=\"appCheck\" value=\""+responseText.payload[i].appId+"\">"
                                +"</td>"
                                +"<td>"
                                +appointmentDate
                                +"</td>"
                                +"<td>"
                                +responseText.payload[i].startTime.substring(0,2)+":"+responseText.payload[i].startTime.substring(2,4)
                                +"</td>"
                                +"<td>"
                                +responseText.payload[i].endTime.substring(0,2)+":"+responseText.payload[i].endTime.substring(2,4)
                                +"</td>"
                                +"<td>"
                                +"<div class=\"d-flex\">"
                                +"<button class=\"btn btn-success\" disabled>Available</button>"
                                +"</div>"
                                +"</td>"
                                +"</tr>" );
                            }else{
                             $('#appTBody').append(
                                "<tr>"
                                +"<td>"
                                +"<input type=\"radio\" name=\"appCheck\" value=\""+responseText.payload[i].appId+"\">"
                                +"</td>"
                                +"<td>"
                                +appointmentDate
                                +"</td>"
                                +"<td>"
                                +responseText.payload[i].startTime.substring(0,2)+":"+responseText.payload[i].startTime.substring(2,4)
                                +"</td>"
                                +"<td>"
                                +responseText.payload[i].endTime.substring(0,2)+":"+responseText.payload[i].endTime.substring(2,4)
                                +"</td>"
                                +"<td>"
                                +"<div class=\"d-flex\">"
                                +"<button class=\"btn btn-danger\" disabled>Not Available</button>"
                                +"</div>"
                                +"</td>"
                                +"</tr>" );
                            }

                        }
                         $('#appTable').DataTable();

                },
                error : function(e) {
                    // alert(e);
                    console.log(e)
                }
        });
    }
}

function createAppointment(){
    var rowCount = document.getElementById("appTable").rows.length;
    var appTable=document.getElementById("appTable");
    var appId = 0;
    for ( i = 1; i < rowCount; i++ ){
        if($(appTable.rows[i]).find('input[type="radio"]').is(":checked")){
            appId = $(appTable.rows[i]).find('input[type="radio"]:checked').val();
        }
    }

    var comment = $("#comment").val();
    var requestObj = {
                  appId:appId,
                  comment:comment
     }

        $.ajax({
                url : "appointment/viewMapping/" + appId,
                type : "GET",
                headers : {
                    'Accept' : 'application/json',
                    'Content-Type' : 'application/json'
                },
                dataType : 'json',
                success: function (data) {
                    alert("Successfully booked your appointment");
                },
                error : function(data) {
                    var err = eval("(" + data.responseText + ")");
                    alert(err.details);
                }
        });
}