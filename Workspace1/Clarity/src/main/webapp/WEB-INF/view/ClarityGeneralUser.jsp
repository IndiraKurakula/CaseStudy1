<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clarity General User</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery.monthpicker.min.js"></script>


<script>
/* DISABLING BACK BUTTON OF BROWSER*/

/* window.onbeforeunload = function() { return "You work will be lost."; }; */
(function (global) { 

    if(typeof (global) === "undefined") {
        throw new Error("window is undefined");
    }

    var _hash = "!";
    var noBackPlease = function () {
        global.location.href += "#";

        global.setTimeout(function () {
            global.location.href += "!";
        }, 50);
    };

    global.onhashchange = function () {
        if (global.location.hash !== _hash) {
            global.location.hash = _hash;
        }
    };

    global.onload = function () {            
        noBackPlease();

        // disables backspace on page except on input fields and textarea..
        document.body.onkeydown = function (e) {
            var elm = e.target.nodeName.toLowerCase();
            if (e.which === 8 && (elm !== 'input' && elm  !== 'textarea')) {
                e.preventDefault();
            }
            // stopping event bubbling up the DOM tree..
            e.stopPropagation();
        };          
    }

})(window);
</script>


</head>

<body>

<table id="Table1" border="0" cellspacing="0" cellpadding="0"
		runat="Server"  style="width:106%; background-image:url(images/inner_top.jpg);">
		

		<tr align="left">
<!-- 		<td style="width: 100%; height: 105px;" align="right"></td> -->
			 <td style="margin-top:-5px; width:1365px; color:#b0d4c5; font-weight:lighter; font-family:Colonna MT; font-size:44pt;
                       margin-left:60px;">CLARITY TRACKER</td> 
		</tr>
	</table>
           
           
<%@ include file='ClarityHoursGeneral.jsp' %>

<div style="background-image:url(images/footer.jpg); height:95px; border-top:1px solid #e5e5e5; padding:-20px; clear:both; bottom:0;position:relative;width:106%;">
</div>
</body>






</html>