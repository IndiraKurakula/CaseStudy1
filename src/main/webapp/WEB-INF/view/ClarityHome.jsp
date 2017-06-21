<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ClarityHome</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<style>

.edit_header 
{
    font-family: Verdana, Geneva, sans-serif;
    font-size: 11px;
    font-weight: bold;
    height: 35;
     }
     
div.home_header {
	height: 5em;
	width: 100%;
	padding-top: 1em;
	padding-bottom: 1px;
	color: teal;
	background-color: white;
	clear: left;
	text-align: center;
}

div.Options {
	font-family: serif;
	font: cursive;
	font-size: large;
	height: 5em;
	width: 100%;
	padding-top: 1em;
	padding-bottom: 1px;
	padding-left: 500px;
	color: black;
	background-color: white;
	clear: left;
	text-align: left;
}

	
div.Options2{
		font-family:serif;
		font:cursive;
		font-size:large;
		height: 5em;
    	width: 100%;
		padding-top: 10em;
		padding-bottom: 1px;
		padding-left:600px;
		color:black ;
		background-color:white;
		clear: left;
		text-align: left;
		
	}


div.Associate {
	margin-left: 45px;
	width: 100%;
	visibility: hidden;
}
</style>

<script>
	function myFunction() {
		if (document.getElementById('rb2').checked) {
	    	OpenAssociate("Lead&Super");
	    }
	    else if (document.getElementById('rb1').checked) {
	    	OpenAssociate("General");
	    }
	    if (document.getElementById('rb3').checked){
			OpenUpload();
		}
	}
</script>



<body onload= "myFunction()">
	<table id="Table1" border="0" cellspacing="0" cellpadding="0"
		runat="Server"  style="width:106%; background-image:url(images/inner_top.jpg);">
		

		<tr align="left">
<!-- 		<td style="width: 100%; height: 105px;" align="right"></td> -->
			 <td style="margin-top:-5px; width:1365px; color:#b0d4c5; font-weight:lighter; font-family:Colonna MT; font-size:44pt;
                       margin-left:60px;">CLARITY TRACKER</td> 
		</tr>
	</table>

	           
	     <%--       <%
			String id = (String) request.getAttribute("id");
	           if (id.equals("valid"))
	           {
		%> --%>
		
		<%
			String errmsg = (String) request.getAttribute("errmsg");
			try {
				if (errmsg.equals(null)) {
					int a = 2 + 3;
				} else {
		%>
		<center>

			<font  color="red"><div><%=request.getAttribute("errmsg")%></div></font>
		</center>
		<%
			}
			} catch (NullPointerException e) {
			}
		%>
		
		
		
		<div class="Options">
<input type="radio" name="clarity" id="rb3" value="Upload" onclick="OpenUpload()"> 
<span id="ctl00_cphContents_lblheading" class="edit_header" style="text-align:right">File Upload for PMO users</span><br>



<div id="Upload" class="Upload" style="font-size: 12px">
<form method="post" action="upload" enctype="multipart/form-data">
		<!-- <input type="file" class="file" name="fileUpload"
			data-buttonName="btn-primary"> <br>  -->
			<input type="file" name="fileUpload" data-buttonName="btn-primary" accept=".csv"> <br>
			<input type="submit"
			value="Upload" class="edit_header"
style="color:white;background-color:#175668; font-weight: bold">
	</form>
 </div>
</div>

<script>
function OpenUpload(){
	
	 if (document.getElementById('rb3').checked) 
	  {		document.getElementById("Associate").style.visibility='hidden';
	      document.getElementById("Upload").style.visibility='visible';
	      document.getElementById("rb1").checked = false;
	      document.getElementById("rb2").checked = false;
	  } else {
		  document.getElementById("Upload").style.visibility='hidden';
	  }
}
</script>
<%--  <%}
	           %> --%>

	<form action="submituser" method="post">

		<%
			String msg = (String) request.getAttribute("msg");
			try {
				if (msg.equals(null)) {
					int a = 2 + 3;
				} else {
		%>
		<center>

			<font size="3" color="red"><div><%=request.getAttribute("msg")%></div></font>
		</center>
		<%
			}
			} catch (NullPointerException e) {
			}
		%>



		


		
	
<%-- <%	
if(id.equals("invalid")){ %> --%>	


<div class="Options">

<br>
<div>
<input type="radio" name="clarity" id="rb1" value="General" checked onclick="OpenAssociate(this.value)">
<span id="ctl00_cphContents_lblheading" class="edit_header" style="text-align:right">Submit the clarity sheet for yourself</span><br>
</div>
<input type="radio" name="clarity" id="rb2" value="Lead&Super" onclick="OpenAssociate(this.value)">
<span id="ctl00_cphContents_lblheading" class="edit_header">Submit the clarity for other associates</span><br>
	
			
<div id="Associate" class="Associate">

<h3 class="edit_header" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enter the Associate Details</h3><br>
<span id="ctl00_cphContents_lblheading" class="edit_header"> Associate Id:
<input autocomplete="on" type="text" name="userid" id="userid" onkeyup="verify(this.id);">
</span>
<!-- required="true" -->
</div>

<script type="text/javascript">
function verify(frm)
{
    var element = document.getElementById('userid');
    var element2 = element.valueOf();
    if(isNaN(new Number(element.value)))
            element.value = element.value.substring(0, element.value.length - 1) 
            
            if((element2>999999)||(element2<100000)){
            	document.getElementById('userid').value=null;
            }
}

function OpenAssociate(val){
	if(val=='Lead&Super'){
		document.getElementById("Associate").style.visibility='visible';
		document.getElementById("Upload").style.visibility='hidden';
		document.getElementById("rb3").checked = false;
		document.getElementById("userid").required=true;
	}
	else if(val=='General'){
		document.getElementById("Associate").style.visibility='hidden';
		document.getElementById("Upload").style.visibility='hidden';
		document.getElementById("rb3").checked = false;
	}
	
}
</script>
</div>

<div class="Options2">

<br><!--  <a href="submituser" class="Next"
	style="padding-left: 50px; visibility: visible;"><img
	src="images/nex_button.jpg"></a> <input
	type="button" name="Options" id="Options" style="display: none;"
	required="true" /> -->
<button type="submit" class="edit_header"
style="color:white;background-color:#175668; font-weight: bold">Next</button>
<!-- background-color:#55c6c6;color:white; background-image: url(images/table_header_bg.jpg);font-weight:bold;-->
			<br>
			
		</div>

	</form>

<%-- <%	}%>	 --%>



	




	<div style="background-image:url(images/footer.jpg); height:95px; border-top:1px solid #e5e5e5; padding:-20px; clear:both; bottom:0;position:absolute;width:106%;">
  <!-- <div style="float:right; margin-top:40px; clear:both;">
    <div style="float:right; clear:both"><img src="D:\images/Hcsc_logo.jpg" style="height: 45px; width: 120px;" /></div>
    <div style="float:right"><img src="D:\images/CTS_logo.jpg" /></div>
    <div style="float:right;">
      <div class="nine_font" style="padding:18px 20px 0 0;">© 2016 Cognizant Technology Solutions.<br />
        All rights reserved.</div>
    </div>
  </div> -->
</div>
</body>
</html>