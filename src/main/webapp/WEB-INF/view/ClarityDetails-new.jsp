<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
div.pager span {
	display: inline-block;
	width: 1.8em;
	height: 1.8em;
	line-height: 1.8;
	text-align: center;
	cursor: pointer;
	background: #000;
	color: #fff;
	margin-right: 0.5em;
	font-family: Verdana, Geneva, sans-serif;
}

div.pager span.active {
	background: #c00;
}

.sty2_row_01 td
{
    font-family: Verdana, Geneva, sans-serif;
    font-size: 11px;
    font-weight: normal;
    border-top: 1px solid #e5e5e5;
    border-right: 1px solid #e5e5e5;
    height: 35;
    padding-left: 10px;
}
.sty2_row_02 td
{
    font-family: Verdana, Geneva, sans-serif;
    font-size: 11px;
    font-weight: normal;
    border-top: 1px solid #e5e5e5;
    border-right: 1px solid #e5e5e5;
    background-image: url(images/row_02_bg.gif);
    height: 55;
    padding-left: 10px;
    text-align:center;
}

.table_header th
{
    font-family: Verdana, Geneva, sans-serif;
    font-size: 12px;
    font-weight: bold;
    background-image: url(images/table_header_bg.jpg);
    border-top: 1px solid #bcd490;
    border-right: 1px solid #bcd490;
    height: 5ex;
    color:white;
    text-align:center;
     
}

.edit_header 
{
/*     font-family: Verdana, Geneva, sans-serif;
    /* font-size: 60px;
    font-weight: bold; */
/*     background-image: url(D:\images/row_02_bg.gif); */
/*     border-top: 1px solid #bcd490; */
/*     border-right: 1px solid #bcd490; 
    height: 35; */
    
    
     }


.form-cont
{
    font-family: Verdana, Geneva, sans-serif;
    font-size: 11px;
    font-weight: normal;
/*     border-top: 1px solid #e5e5e5; */
/*     border-right: 1px solid #e5e5e5; */
    height: 55;
    padding-left: 10px;
}

ul.pagination {
    display: inline-block;
    padding: 0;
    margin: 0;
}

ul.pagination li {display: inline;}

ul.pagination li a {
    color: black;
    float: left;
    padding: 8px 16px;
    text-decoration: none;
}

ul.pagination li a.active {
    background-color: #175668;
    color: white;
}

ul.pagination li a:hover:not(.active) {background-color: #ddd;}

  </style>


<!-- CODE FOR SEARCH BOX -->
<!--<style>


 input[type=text]:focus {
	width: 20%;
}
</style> -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.6/handlebars.js">

</script>
<script type="text/javascript">
	
</script>


<script type="text/javascript">
/* function MonthOption(monthval) {
	window.alert(monthval)
}

function ProjectOption(proval) {
	window.alert(proval)
} */


function MonthSearch(val) {
	$.ajax({
		method : "GET",
		data:{'month':val},
		url : "listAssociatesByMonth",
		success : function(data) {
			console.log(JSON.parse(data));
			populateAssociatesList(JSON.parse(data));
		}
	});
	
}

function ProjectSearch(val) {
	$.ajax({
		method : "GET",
		data:{'proj':val},
		url : "listAssociatesByProject",
		success : function(data) {
			console.log(JSON.parse(data));
			populateAssociatesList(JSON.parse(data));
		}
	});
	
}

	 $(document).ready(function() {
		//alert("Here we are");
		/* getLastMonths("selectMonth", 12); */
		TimeSheetHeaders(new Date().getMonth()+1,new Date().getFullYear());
		 getAllAssociates(new Date().getMonth()+1,new Date().getFullYear()); 
         getAllProjNames();
	}); 
	 
	 function monthChange(data){
		 getAllAssociates(data.value,document.getElementsByName('selectYear')[0].value);
		 
	 }
	
	 function myFunction(searchedvalue,projname,monthname){
			
			var id = searchedvalue;
			var proj=projname;
		    var month=monthname;
		    var year=document.getElementsByName('selectYear')[0].value;
		    if((proj=='select')&&(month!='select')){
		    	window.alert("Project Name should be selected")
				
		    }
		    else{
		    	
		    	getAssociatesBySearch(id,proj,month,year);
		    }
		} 	
			
	 
		function getAssociatesBySearch(val1,val2,val3,year) {
			
			 var d = new Date();
  			  var n =d.getMonth()+1;
			
			if(val3=='select'){
				//window.alert(n) 
				val3=n.toString();
				//window.alert(val3) 
			} 
			
			$.ajax({
				method : "GET",
				data : {'id' : val1 ,'proj' : val2,'month' : val3,'year' : year},
				
				url : "listAssociatesBySearch",
			<%-- 	data :"<%=session.getAttribute("ad")%>", --%>
				success : function(data) {
					//console.log(JSON.parse(data));
					TimeSheetHeaders(val3,year);
					populateAssociatesList(JSON.parse(data),val3,year);
				}
			});
			
		}

	 
	
	function getAllAssociates(month,year){
		alert();
		//alert("InsidegetALLAsso"+month+year);
		$.ajax({
			method : "GET",
			url : "listAssociates",
			data: 'month='+month+'&year='+year,
			success : function(data) {
				//alert("InsidegetALLAsso-function"+month+year);
				console.log(JSON.parse(data));
				populateAssociatesList(JSON.parse(data),month,year);
			}
		});
		
	}
	

function getAllProjNames(){
		
		$.ajax({
			method : "GET",
			url : "listProjects",
			success : function(data) {
				console.log(JSON.parse(data));
				populateProjectList(JSON.parse(data));
			}
		});
		
	}

	
	function populateAssociatesList(associates,month,year) {
// 		var month="12";
// 		var year="2016";
       //alert("InsidePopluTEsso"+month+year);
		DateHeaders(month,year);
		var myassociates=convertMyJsonFormat(associates);
		console.log(myassociates);
		var source = $("#associateRow").html();
		var html = convertToHtml(source, associates);
		$("#associatesList").html(html);
	}
	
	
function populateProjectList(associates) {
		var source = $("#projectOptions").html();
		console.log(source);
		console.log(associates);
		var html = convertToHtml(source, associates);
		//console.log(html);
		$("#selectProject").html(html);
	}
	
	
	 function convertMyJsonFormat(details){
		var associate=[];
		for(key in details){
// 			console.log()
for(var i=0;i<details[key].length;i++){
	console.log(details[key].length);
			details[key][i]['times']=details[key][i]['timings']['times'];
}
				associate.push({"associatedetails":details[key]});
// 				associates.push({"times":details[key]['timings']});
				console.log(associate);
		}
		return associate;
	}  
	
	
	
	//Indira Timesheet code starts
	function TimeSheetHeaders(month, year) {
		
		var numberOfdays = findTotalDayInMonth(month, year);
		var position = new Date(year, month - 1, 1).getDay();
		var headers = "";
		var body = "";
		for (var i = 1; i <= numberOfdays; i++) {
			headers += "<th>" + i + "/" + month + "</th>";
		}
		//$("#headers").html(headers);
	}
	

	function findTotalDayInMonth(month, year) {
		console.log(new Date(year, month, 0).getDate());
		return new Date(year, month, 0).getDate();
	}


	function DateHeaders(month, year) {
		
		var numberOfdays = findTotalDayInMonth(month, year);
		var position = new Date(year, month - 1, 1).getDay();
		var headers = "";
		var body = "";
		for (var i = 1; i <= numberOfdays; i++) {
			headers += "<th>" + i + "/" + month + "</th>";
			//body +="<td>"+
		}
		$("#associateHeaders tr th").remove("th:gt(12)");
		$("#associateHeaders tr").append(headers);
	}
	
	 
		function convertToHtml(source, data) {
		var template = Handlebars.compile(source);
		var html = template(data);
		return html;
	}
	 
	// Indira Timesheet code ends
	
	function check() {
	    var val = document.getElementById('selectProject').value; // change here
	    if (val == "select") {
	    	 window.location.href = "exportbutton"  ;
	        
	    } else if(val != "")
	    	window.location.href = "exportbuttonaftersearch?val=" + val;
	    	
	}
	
	
	
// 	To edit the associate Details for the super user
	function editAssociate(id) {
		$("#editModal").modal("show");
		$("#AssociateID").val(id);
		$.ajax({
			method : "GET",
			url : "editAssociate",
			data : "id="+id,
			success : function(data) {
				var associate=JSON.parse(data);
				console.log(associate);
				$("[name='AssociateID']").val(associate.AssociateID);
				$("[name='HCSCID']").val(associate.HCSCID);
				$("[name='AssociateName']").val(associate.AssociateName);
				$("[name='ProjectName']").val(associate.ProjectName);
				$("[name='Location']").val(associate.Location);
				$("[name='ClarityAccess']").val(associate.ClarityAccess);
				$("[name='Location']").val(associate.Location);
				$("[name='HCSCEmailID']").val(associate.HCSCEmailid);
				$("[name='PhoneNumber']").val(associate.PhoneNumber);
				$("[name='HCSCJoiningDate']").val(associate.HCSCJoiningDate);
				$("[name='LastClarityUpdateDate']").val(associate.LastClarityUpdateDate);
				$("[name='Rate']").val(associate.Rate);
				$()
			}
		});
	}
	$.getJSON('listAssociates','month='+12+'year='+2016, callBack);

/* function paginate(){
		
}
	 */
	//CODE FOR PAGINATION
// $.getJSON('listAssociates', callBack);

        var myData = []; //hold data
      function callBack(data){
    	   //alert('In callback function');
       	myData = data;
         console.log(myData);
         compileAndDisplayTemplate();
        paginate();
        }
      function compileAndDisplayTemplate(){
         var source = $("#associateRow").html();
          var template = Handlebars.compile(source);
         var html = template(myData);
         console.log(html);
       	$("#associatesList").html(html);
       }

       
      function paginate(){
          $('table.paginated').each(function() {
          var currentPage = 0;
           var numPerPage = 4;
           var $table = $(this);
          $table.bind('repaginate', function() {
               $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
            });
           $table.trigger('repaginate');
            var numRows = $table.find('tbody tr').length;
            var numPages = Math.ceil(numRows / numPerPage);
            var $pager = $('<div class="pager"></div>');
            for (var page = 0; page < numPages; page++) {
                $('<span class="page-number"></span>').text(page + 1).bind('click', {
                    newPage: page
                }, function(event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                }).appendTo($pager).addClass('clickable');
            }
              $pager.insertAfter($table).find('span.page-number:first').addClass('active');
          });
        }
       function refreshFields(){
    	   document.getElementsByName('selectProject')[0].value="select";
    	   document.getElementsByName('selectMonth')[0].value="select";
    	document.getElementsByName('selectYear')[0].value=new Date().getFullYear();
    	   document.getElementsByName('searchedvalue')[0].value=defaultStatus;
    	   
    	   TimeSheetHeaders(new Date().getMonth()+1,new Date().getFullYear());
  		 getAllAssociates(new Date().getMonth()+1,new Date().getFullYear());
       }
	
	var scrt_var = document.getElementById('selectProject'); 
	</script>

</head>

<!-- <h2>Clarity Details of the Associates</h2> -->
<span id="ctl00_cphContents_lblheading" style="font-size: 18px;font-family: Verdana, Geneva, sans-serif;font-weight: bold;">Clarity Details of the Associates</span>
<br>
<!-- <div>

	
		<a  class="btn btn-info btn-md" id="exportafterSearch" onclick="return check()" style="color:white;background-color:#175668; font-weight: bold">   <span
		class="glyphicon glyphicon-export" style="color:white"></span> Export
	</a>
</div> class="edit_header"   style="font-size: 20px;font-family: Verdana, Geneva, sans-serif;font-weight: 100;"-->

<div align="center"  style="font-size: 80%;font-family: Verdana, Geneva, sans-serif;font-weight: lighter;">
<b > Select Month and Year : <select
	id="selectMonth" name="selectMonth" onselect="monthChange(this)">
	<!--  onclick="MonthSearch(this.value)" onselect="MonthOption(this.value)"-->
	<option value="select" selected="selected">--Select--</option>
	<option value="01">January</option>
	<option value="02">February</option>
	<option value="03">March</option>
	<option value="04">April</option>
	<option value="05">May</option>
	<option value="06">June</option>
	<option value="07">July</option>
	<option value="08">August</option>
	<option value="09">September</option>
	<option value="10">October</option>
	<option value="11">November</option>
	<option value="12">December</option>
	
</select>
&nbsp;

 <select id="selectYear" name="selectYear" style="width: 70px;">

 </select>
 &nbsp;&nbsp;&nbsp;
<script>

var start =new Date().getFullYear()-1 ;
var end = new Date().getFullYear();
var options = "";
for(var year = end ; year >=start; year--){
  options += "<option>"+ year +"</option>";
}
document.getElementById("selectYear").innerHTML = options;
</script>
 <b> Select Project Name: </b> <select id="selectProject" name="selectProject" >
 
	<!-- onclick="ProjectSearch(this.value)" onselect="ProjectOption(this.value)" id="selectProject" -->
	
</select> <script id="projectOptions" type="text/x-handlebars-template">

<option value="select" selected="selected">--Select--</option>
 {{#each projectdetails}}

<option scope="row" value="{{ProjectName}}" >{{ProjectName}}</option><br>

{{/each}}
</script> 
&nbsp;&nbsp;&nbsp;

<input type="text" name="searchedvalue" id="searchedvalue" placeholder="Search.."  style="width: 200px;"> 
</div>
<br>
<div align="center">
<button type="button" class="btn btn-default btn-sm" style="color:white;background-color:#175668"  
onclick="myFunction(document.getElementsByName('searchedvalue')[0].value,document.getElementsByName('selectProject')[0].value,document.getElementsByName('selectMonth')[0].value)">
          <span class="glyphicon glyphicon-search" ></span> Search 
        </button>
&nbsp;&nbsp;&nbsp;

 <button type="button" class="btn btn-default btn-sm" style="color:white;background-color:#175668" onclick="refreshFields()">
          <span class="glyphicon glyphicon-refresh"></span> Reset
        </button>

&nbsp;&nbsp;&nbsp;

<button type="button" class="btn btn-default btn-sm" id="exportafterSearch" onclick="return check()" style="color:white;background-color:#175668">
          <span class="glyphicon glyphicon-export"></span> Export
        </button>

</div>

<!-- </table> style="overflow:scroll;height:370px;width:100%;overflow:auto"class="container"  style="overflow:scroll;width:100%;overflow:auto;height:inherit;-->

<br>

<div class="container"  style="overflow:scroll;height:inherit;width:100%;overflow:auto">
	<div class="row">
		<div class=col-lg-12></div>
	</div>
	<table id="associateTable" class="paginated table table-striped">
		<thead id="associateHeaders" style="height:40px;">
		<tr class="table_header">
				<th>#</th>
				<th>AssociateID</th>
				<th>HCSCID</th>
				<th>AssociateName</th>
				<th>ProjectName</th>
				<th>Location</th>
				<th>ClarityAccess</th>
				<th>HCSCEmailid</th>
				<th>PhoneNumber</th>
				<th>HCSCJoiningDate</th>
				<th>LastClarityUpdateDate</th>
				<th>Rate</th>
				<th>Edit</th>
			</tr>
		</thead>
		<tbody id="associatesList"  >
		</tbody>
	</table>
</div>


<!-- <div align="center">
<ul class="pagination" >
  <li><a href="#">«</a></li>
  <li><a href="#">1</a></li>
  <li><a class="active" href="#">2</a></li>
  <li><a href="#">3</a></li>
  <li><a href="#">4</a></li>
  <li><a href="#">5</a></li>
  <li><a href="#">6</a></li>
  <li><a href="#">7</a></li>
  <li><a href="#">»</a></li>
</ul>
</div> -->
<script id="associateRow" type="text/x-handlebars-template">
 {{#each associatedetails}}
<tr style="height:35px" class="sty2_row_02">
<th scope="row">{{index}}</th>
<td>{{AssociateID}}</td>
<td>{{HCSCID}}</td>
<td>{{AssociateName}}</td>
<td>{{ProjectName}}</td>
<td>{{Location}}</td>
<td>{{ClarityAccess}}</td>
<td>{{HCSCEmailid}}</td>
<td>{{PhoneNumber}}</td>
<td>{{HCSCJoiningDate}}</td>
<td>{{LastClarityUpdateDate}}</td>
<td>{{Rate}}</td>
<td><button class="btn btn-primary" style="background-color:#52949b" onclick="editAssociate({{AssociateID}})"><span title="edit"
							class="glyphicon glyphicon-pencil" style="color:white;background-color:#52949b"></span></button></td>
{{#each times}}{{#each this}}<td>{{this}}</td>{{/each}}{{/each}}

</tr>
{{/each}}
</script>




<!-- code for Edit Associate-->
<!-- {{#times}}<td>a</td>{{/times}}  -->
	<div class="modal fade" id="editModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
		<!-- 					<h4 class="modal-title add">Edit Associate</h4> -->
 		  <table id="Table1"  border="0" cellspacing="0" cellpadding="0" runat="Server" style="background-image:url(images/inner_top.jpg);">
                <tr align="left">
                   <td style="margin-top:-5px; width:1365px; color:#b0d4c5; font-weight:lighter; font-family:Colonna MT; font-size:44pt;
                       margin-left:60px;">Edit Associate</td>          
                </tr>
          </table>
          		</div>
				<div class="modal-body">
				<!-- Row start -->
				<div class="row">
					<div class="col-md-12 col-sm-6 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<form id="editAssociate" class="form-horizontal row-border"
									action="updateAssociate" method="post">
									<!-- 										<div class="form-group"> -->
									<!-- 											<label class="col-md-4 control-label">AssociateID</label> -->
									<!-- 											<div class="col-md-8"> -->
									<input type="hidden" name="AssociateID" class="form-control">
									<input type="hidden" name="ProjectName">
									<!-- 											</div> -->
									<!-- 										</div> -->
									<div class="form-group">
										<label class="col-md-4 control-label" style="text-align:left">HCSCID</label>
										<div class="col-md-8">
											<input class="form-control" type="text" name="HCSCID" style="font-size: 11px;font-weight: normal">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label" style="text-align:left">AssociateName</label>
										<div class="col-md-8">
											<input class="form-control" type="text" name="AssociateName" style="font-size: 11px;font-weight: normal">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label" style="text-align:left">Location</label>
										<div class="col-md-8">
											<input class="form-control" type="text" name="Location" style="font-size: 11px;font-weight: normal">
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-4 control-label" style="text-align:left">ClarityAccess</label>
										<div class="col-md-8">
											<input class="form-control" type="text" name="ClarityAccess" style="font-size: 11px;font-weight: normal">
										</div>
									</div>



									<div class="form-group">
										<label class="col-md-4 control-label" style="text-align:left">HCSCEmailid</label>
										<div class="col-md-8">
											<input class="form-control" type="text" name="HCSCEmailID" style="font-size: 11px;font-weight: normal">
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-4 control-label" style="text-align:left">PhoneNumber</label>
										<div class="col-md-8">
											<input class="form-control" type="text" name="PhoneNumber" style="font-size: 11px;font-weight: normal">
										</div>
									</div>



									<div class="form-group">
										<label class="col-md-4 control-label" style="text-align:left">HCSCJoiningDate</label>
										<div class="col-md-8">
											<input class="form-control" type="text"
												name="HCSCJoiningDate" style="font-size: 11px;font-weight: normal">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label" style="text-align:left">LastClarityUpdateDate</label>
										<div class="col-md-8">
											<input class="form-control" type="text"
												name="LastClarityUpdateDate" style="font-size: 11px;font-weight: normal">
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-4 control-label" style="text-align:left">Rate</label>
										<div class="col-md-8">
											<input class="form-control" type="text" name="Rate" style="font-size: 11px;font-weight: normal">
										</div>
									</div>


									<div class="modal-footer">
										<button type="submit" class="btn btn-default btn-sm"  style="color:white;background-color:#175668"  id="associateEdit" >
							          <span class="glyphicon glyphicon-edit"></span> Update
							        </button>
									
									
										<button type="button" class="btn btn-default btn-sm"
										
											data-dismiss="modal" style="color:white;background-color:#175668">
											<span class="glyphicon glyphicon-eye-close"></span> Close</button>
					
									</div>
						


								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

