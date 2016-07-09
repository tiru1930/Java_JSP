$("#signupform").submit(function(event){
	var fname = $('input[name=inputFName]').val();
    var lname = $('input[name=inputLName]').val();
	var email = $('input[name=inputEmail]').val();
	var password = $('input[name=inputPassword]').val();
	var phonenumber = $('input[name=phoneNumber]').val();
	var pincode = $('input[name=pincode]').val();
	var address = $('input[name=address]').val();
	var obj ={
	  "fname":fname,
	  "lname":lname,
   	  "email":email,
   	  "password":password,
   	  "phonenumber":phonenumber,
   	  "pincode":pincode,
   	  "address":address
   }
	var xhr = new XMLHttpRequest();
	var url = "/Signupservlet";
	xhr.open("POST",url, true);
	xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	xhr.onreadystatechange = function () { 
	    if (xhr.readyState == 4 && xhr.status == 200) {
	        var json = JSON.parse(xhr.responseText);
	        console.log(json.email + ", " + json.password)
	    }
	}
	xhr.send(JSON.stringify(data));
});