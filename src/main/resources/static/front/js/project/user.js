$(document).ready(function() {
    $("form").submit(function(e){
        e.preventDefault();
    });
    changeDiv();
});

function saveUser(){
    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var nic = $("#nic").val();
    var contactNo = $("#contactNo").val();
    var address = $("#address").val();
    var email = $("#email").val();
    var username = $("#firstName").val();
    var password = $("#userpassword").val();
    var checkUser = $("input[type='radio'][name='typejoin']:checked").val();
    var specialization =$('#specialization').find(":selected").text();

    var user      ={
                    username:username,
                    password:password
                    }
    var docrequestObj = {
    				  firstName:firstName,
    				  lastName:lastName,
    				  nic:nic,
    				  contactNo:contactNo,
    				  address:address,
                      email:email,
                      specialization:specialization,
                      user:user
    	              }
    var requestObj = {
        				  firstName:firstName,
        				  lastName:lastName,
        				  nic:nic,
        				  contactNo:contactNo,
        				  address:address,
                          email:email,
                          user:user
        	              }
    //Doctor
    if(parseFloat(checkUser)==1){
           $.ajax({
                      url: "doctor/",
                      type:"POST",
                      headers: {
                          'Accept': 'application/json',
                          'Content-Type': 'application/json'
                      },
                      dataType:"json",
                      data: JSON.stringify(docrequestObj),
                      success: function(data) {
                          alert(data.messages);
                          location.reload();
                      },
                      error: function(data){
                     var err = eval("(" + data.responseText + ")");
                      alert(err.details);
                      }
            });
    }
    //Channel Center
    else if(parseFloat(checkUser)==2){
           $.ajax({
                      url: "center/save",
                      type:"POST",
                      headers: {
                          'Accept': 'application/json',
                          'Content-Type': 'application/json'
                      },
                      dataType:"json",
                      data: JSON.stringify(requestObj),
                      success: function(data) {
                          alert(data.messages);
                          location.reload();
                      },
                      error: function(data){
                     var err = eval("(" + data.responseText + ")");
                      alert(err.details);
                      }
            });
    }
    //Patient
    else if(parseFloat(checkUser)==3){
           $.ajax({
                      url: "patient/save",
                      type:"POST",
                      headers: {
                          'Accept': 'application/json',
                          'Content-Type': 'application/json'
                      },
                      dataType:"json",
                      data: JSON.stringify(requestObj),
                      success: function(data) {
                          alert(data.messages);
                          location.reload();
                      },
                      error: function(data){
                     var err = eval("(" + data.responseText + ")");
                      alert(err.details);
                      }
            });
    }
}

function changeDiv(){
   if ($("input[name='typejoin'][value='1']").prop("checked")) {
            $("#specDiv").show();
    }else{
        $("#specDiv").hide();
    }
}