<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clarity Super User</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery.monthpicker.min.js"></script>
</head>
<style>


.main_menu {
	width: 165px;
	font-family: "Roboto", sans-serif;
	font-size: 12px;
	font-weight: lighter;
	background-color: #e1e1e1;
	color: #175668;
	text-align: center;
	height: 28px;
	line-height: 25px;
	border: 2px solid #ffffff;
	border-radius: 0px;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	-webkit-font-smoothing: antialiased;
	display: inline-block;
	vertical-align: middle;
	-webkit-transform: perspective(1px) translateZ(0);
	transform: perspective(1px) translateZ(0px);
	box-shadow: 0px 0px 1px transparent;
	position: relative;
	-webkit-transition-property: color;
	transition-property: color;
	-webkit-transition-duration: 0.5s;
	transition-duration: 0.5s;
}

.main_menu::before {
	content: "";
	position: absolute;
	z-index: -1;
	top: 0px;
	left: 0px;
	right: 0px;
	bottom: 0px;
	background: #175668;
	-webkit-transform: scaleY(0);
	transform: scaleY(0);
	-webkit-transform-origin: 50% 0;
	transform-origin: 50% 0;
	-webkit-transition-property: transform;
	transition-property: transform;
	-webkit-transition-duration: 0.5s;
	transition-duration: 0.5s;
	-webkit-transition-timing-function: ease-out;
	transition-timing-function: ease-out;
}
.main_menu:hover, .main_menu:focus, .main_menu:active {
	color: white;
}
.main_menu:hover::before, .main_menu:focus::before, .main_menu:active::before {
	-webkit-transform: scaleY(1);
	transform: scaleY(1);
	-webkit-transition-timing-function: cubic-bezier(0.52, 1.64, 0.37, 0.66);
	transition-timing-function: cubic-bezier(0.52, 1.64, 0.37, 0.66);
}


div.home_header{
	height: 5em;
    width: 100%;
	padding-top: 1em;
	padding-bottom: 1px;
	color:teal ;
	background-color:white;
	clear: left;
	text-align: center;
}
div.Options{
  font-family:serif;
  font:cursive;
  font-size:large;
	height: 5em;
    width: 100%;
	padding-top: 1em;
	padding-bottom: 1px;
	padding-left:400px;
	color:black ;
	background-color:white;
	clear: left;
	text-align: left;
}
div.Associate{
margin-left:45px; width:100%;
visibility:hidden;
}


ul.tab {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-image: url(images/table_header_bg.jpg);
    width: 110%;
/*     border:1px solid black; */
   border-top : 0px solid black;
/*    padding : 1px;  */
  padding-bottom : 1px; 
}


.tabcontent {
    display: none;
    padding: 6px 12px;
    font-size:medium;
    -webkit-animation: fadeEffect 1s;
    animation: fadeEffect 1s;
}

@-webkit-keyframes fadeEffect {
    from {opacity: 0;}
    to {opacity: 1;}
}

@keyframes fadeEffect {
    from {opacity: 0;}
    to {opacity: 1;}
}
 form.box{/* margin: 3%; */
text-align: left;
border: thin;
border-color:black;
display: block;
} 
table,td,th{
    border: 1px solid  black;
}
div.month{
float: right;
 cursor: pointer;
 
 
}


</style>
<body>
     <table id="Table1"  border="0" cellspacing="0" cellpadding="0" runat="Server" style="width:110%" background="images/inner_top.jpg">
               
                
           <tr align="left">
<!--                    <td style="width: 100%; height: 120px;" align="right" > -->
                   <td style="margin-top:-5px; width:1365px; color:#b0d4c5; font-weight:lighter; font-family:Colonna MT; font-size:44pt;
                       margin-left:60px;">CLARITY TRACKER</td>  
                    <!-- <td style="width: 307px; height: 105px;" >
                    </td> -->
                  
                </tr>
           </table>

<div>
<ul class="tab">
<li class="main_menu" onclick="openClarity(event,'ClarityHours')">Clarity Hours</li>
<li class="main_menu" onclick="openClarity(event,'ClarityDetails')">Clarity Details</li>
<li class="main_menu" onclick="openClarity(event,'Roles')">Roles</li>
</ul>

<div class="box" >
<div id="ClarityHours" class="tabcontent">
<%@ include file='ClarityHours.jsp' %>
</div>
<div id="ClarityDetails" class="tabcontent" style="visibility: visible;">
<%@ include file='ClarityDetails.jsp' %>
</div>
<div id="Roles" class="tabcontent">
<%@ include file='Roles.jsp' %>
<%-- <%@ include file='Roles-pulkit.jsp' %> --%>
</div>
</div>
</div>
<script>
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
<script>

function openClarity(evt,option){
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	 for (i = 0; i < tabcontent.length; i++) {
	        tabcontent[i].style.display = "none";
	    }
	 tablinks = document.getElementsByClassName("tablinks");
	 for (i = 0; i < tablinks.length; i++) {
	        tablinks[i].className = tablinks[i].className.replace(" active", "");
	    } 
	 document.getElementById(option).style.display = "block";
	    evt.currentTarget.className += " active";
}

document.getElementById("defaultOpen").click(); 

function acceptMonth() {
	
}
</script>
<div style="background-image:url(images/footer.jpg); height:95px; border-top:1px solid #e5e5e5; padding:-20px; clear:both; bottom:0;position:relative;width:110%;">
</div>
</body>
</html>