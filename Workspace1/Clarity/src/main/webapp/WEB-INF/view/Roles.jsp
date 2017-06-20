<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style>
.edit_header
{
    font-family: Verdana, Geneva, sans-serif;
    font-size: 11px;
    font-weight: bold;
    height: 35;
    
 }
 </style>
 <script>
 function validateRoles(id,role){
	 alert('validateroles1');
	 alert(id);
	 alert(role);
	 $.ajax({
			method : "POST",
			data:{'id':id , 'role':role},
			url : "Submit",
			success : function(data) {
				 alert('Roles updation success');
				 alert(JSON.parse(data));
				populateRoleSuccess(JSON.parse(data));
			//	$("#message").html(JSON.parse(data));
			}
		});
	 
 
 }
 
/*  $('form').on('submit',function(){
	            alert('submitted');
	 }); */
 
 
 function populateRoleSuccess(roles) {

		 alert('validateroles3');
	   var source = $("#messagesource").html();
		console.log(source);
		console.log(roles); 
		var html = convertHtml(source, roles);
		
		$("#message").html(html);	


	}
	 
	 function convertHtml(source, data) {
		 alert('validateroles4');
			var template = Handlebars.compile(source);
			var html = template(data);
			return html;
		}
 </script>
 
<script id="messagesource" type="text/x-handlebars-template">
{{msg}}
</script> 
<!--  <form action="Submit" method="post"> -->
 
<%--  	<%
  String msg=(String)request.getAttribute("msg");
   try
  {
  if((msg.equals(null)))
  {
	  int c=2+3;
   %>   

    <%}
  else
  {
	  %> 
	  <div class="Wrapper"> 
	  <div ><%=request.getAttribute("msg")%></div> 
	  </div>
<% 	
  }
      }catch(NullPointerException e)
  {    	
  }
  %>  --%>
  <body>
  <div id="message" align="center" style="color: red"></div>
 
  <center>
 <div class="panel panel-default">
<div class="panel-heading text-center">
	<p style="width: 130px; text-align: left;" class="edit_header">
		Associate Id: <input type="text" name = "changeid"  required="true"
			onblur="fillField(this,'Name');" onfocus="clearField(this,'Name');" style="font-size:11px;font-weight:normal;"/>
	<p  class="edit_header" style="width: 70px; text-align: left;">
		Role: <select id="roles" name="newrole" style="width: 120px">
			<option value="General User">General User</option>
			<option value="Lead User">Lead User</option>
			<option value="Super User">Super User</option>
		</select> <br> <br>
											<button type="submit" id="associateEdit" onclick="validateRoles(document.getElementsByName('changeid')[0].value, document.getElementsByName('newrole')[0].value)" class="btn btn-success" style="background-color:#175668;color:white;font-weight:bold">Save</button>
											
											</p>
											</div>
											</div>
											</center>
										
<!-- </form>  -->
</body>