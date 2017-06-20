<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="beans.AssociateDetails"%>
<%@page import="beans.ClarityHours"%>

<%
	HttpSession session1 = request.getSession(false);

	LinkedHashMap<AssociateDetails, ClarityHours> lp = (LinkedHashMap<AssociateDetails, ClarityHours>) session1
			.getAttribute("viewresult");

	AssociateDetails ad = null;
	ClarityHours ch = null;
	Iterator iterator = lp.keySet().iterator();
	while (iterator.hasNext()) {
		ad = (AssociateDetails) iterator.next();
		ch = (ClarityHours) lp.get(ad);
	}

	int assId = ad.getAssociateID();
%>
<html>
<head>
<style>
.edit_header {
	font-family: Verdana, Geneva, sans-serif;
	font-size: 11px;
	font-weight: bold;
	height: 35;
}

#btnupdate {
	font-family: Verdana, Geneva, sans-serif;
	font-size: 11px;
	font-weight: bold;
	height: 35;
}

.edit_statics {
	font-family: Verdana, Geneva, sans-serif;
	font-size: 11px;
	height: 35;
}

.time-field {
	width: 40px;
	border-radius: 0px
}

.time-field-backlog {
	width: 40px;
	border-radius: 0px
}

.totalhours {
	border: 0;
	width: 40px;
}

.totalbhours {
	border: 0;
	width: 40px;
}

.fghours {
	width: 40px;
	border-radius: 0px
}

.ui-datepicker-calendar {
	display: none;
}

#updatebtn {
	right: 0;
	position: absolute;
}

#headers {
	font-family: Verdana, Geneva, sans-serif;
	font-size: 10px;
	/* 	font-weight: bold; */
	background-image: url(images/table_header_bg.jpg);
	border-top: 1px solid #bcd490;
	border-right: 1px solid #bcd490;
	height: 35;
	color: white;
	text-align: center;
}

#blogheader {
	font-family: Verdana, Geneva, sans-serif;
	font-size: 10px;
	/* 	font-weight: bold; */
	background-image: url(images/table_header_bg.jpg);
	border-top: 1px solid #bcd490;
	border-right: 1px solid #bcd490;
	height: 35;
	color: white;
	text-align: center;
}
</style>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
<script type="text/javascript" src="js/dojo/dojo/dojo.js"></script>

<script>
	function closeWin() {
		myWindow.close();
	}
	
	//window.onbeforeunload = function() { return "You work will be lost."; };
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#fg").hide();
						$("#thours").hide();
						$("#totalhours").hide();
						$("#totalbhours").hide();
						getLastMonths("selectMonth", 12);
							$("#totalhours").hide();
							$("#totalbhours").hide();
							$("#newbacklogbtn").hide();
							getsheet($("#selectMonth").val());
							getFieldGlassHours();
							sendRequest($("#selectMonth").val());
							$("#updatebtn").show();
						
						$("#newbacklogbtn").hide();
						$("#selectMonth").bind("change", function() {
							$("#totalhours").hide();
							$("#totalbhours").hide();
							$("#newbacklogbtn").hide();
							getsheet();
							getFieldGlassHours();
							sendRequest();
							$("#updatebtn").show();
						});
						$("#btnupdate").click(function() {
							$("#totalhours").show();
							$("#totalbhours").show();
							var calBool = calculateTotalHours();
							if(calBool ==1){
								return false
							}
							
						});
						var checkBoxCount=0;
						$("#checkbacklog").bind("change", function() {
							if(checkBoxCount==0 && $("#checkbacklog").is(":checked") == true){
							displayBacklog(0);
							}
							if($("#checkbacklog").is(":checked") == false){
							checkBoxCount++;
							$("#backLogMonth").slideUp("fast", function() {
							 });}
							
							if($("#checkbacklog").is(":checked") == true && checkBoxCount > 0 ){
							checkBoxCount++;
							$("#backLogMonth").slideDown("fast", function() {
							});}
						});
						$("#btnaddnewbaclog").click(function() {
							createBacklog(0);
						});
						$("#btnsubmit")
								.click(
										function() {
											var submitBool = submitValidations();
											if (submitBool == 2) {
												var submitconfirmation = confirm(" Are you sure you want to submit the time sheet?");
												if (submitconfirmation) {
													submittimesheet();
											} else {
													return false;
												}
											} else {
												return false;
											}
										});
					});

	/* JS function for retreving fieldglass Data */
	function getFieldGlassHours() {
		var year = $("#selectMonth").val().split(',')[0];
		var month = $("#selectMonth").val().split(',')[1];
		$.ajax({
			cache: false,
			method : "GET",
			url : "fieldglassDetails",
			data : "year=" + year + "&month=" + month,
			success : function(data) {
				var inputsFgHours = $(".fghours");
				inputsFgHours.value = data;
				$(".fghours").val(inputsFgHours.value);
			}
		});
	}

	/* JS function for calculating Total Hours */
	function calculateTotalHours() {
		var arr = new Array();
		var backarr = new Array();
		var allHours = 0;
		var hoursentered = 0;
		var backhoursentered = 0;
		var allbackloghours = 0;
		var timesheetDataIssue =0;
		var backlogDataIssue =0;
		var timesheetInvalid =0;
		var backlogInvalid =0;
		var alltimesheet = $('[id^=timesheet_]');
		var backlogtimesheet = alltimesheet.each(function() {
			if ($(this).val().length == 0) {
				hoursentered = 0;
			}
			else{
				if ($(this).val() == "L" || $(this).val() == "l" || $(this).val() =="H" || $(this).val()=="h") {
					hoursentered = 0;
			}
				else if($(this).val() <=0 || $(this).val()>24 ){
					alert("You are not allowed to enter more than 24 Hrs and less than 0 Hrs for a day.");
					timesheetDataIssue =1;
			}
			else {
				if(!(isNaN($(this).val()))){
					
				hoursentered = parseInt($(this).val());
				}
				else{
					alert("Please enter valid Hours");
					timesheetInvalid=1;
					return false;
				}
				
			}
			}
			allHours = allHours + hoursentered;
		});
		var inputsTimeSheetHours = $(".totalhours");
		inputsTimeSheetHours.value = allHours;
		if(allHours>0)
		$(".totalhours").val(inputsTimeSheetHours.value);
		var backlogalltimesheet = $('[id^=backlogid_]');
		backlogalltimesheet.each(function() {
			var hoursentered = $(this).val();
			if ($(this).val().length == 0) {
				backhoursentered = 0;
			}
			else{
				if ($(this).val() == "L" || $(this).val() == "l" || $(this).val() =="H" || $(this).val()=="h") {
					backhoursentered = 0;
				}
					else if($(this).val() <=0 || $(this).val()>24 ){
						alert("You are not allowed to enter more than 24 Hrs and less than 0 Hrs for a day.");
						backlogDataIssue = 1;
						return false;
			}
					else {
						if(!(isNaN($(this).val()))){
							backhoursentered = parseInt($(this).val());
						}
						else{
							if(timesheetInvalid==1){
								backlogInvalid=1;
								return false;
							}
							else{
							
							alert("Please enter valid Hours");
							backlogInvalid=1;
							return false;
							}
						}
						
					}
			}
			allbackloghours = allbackloghours + backhoursentered;
		});
		if(backlogDataIssue ==1 || timesheetDataIssue==1 || timesheetInvalid==1){
			return false;
		}
		else{
		var inputsBacklogHours = $(".totalbhours");
		inputsBacklogHours.value = allbackloghours;
		if(allbackloghours>0)
		$(".totalbhours").val(inputsBacklogHours.value)
		}
	}

	/* JS function for autopopulation of clarity hours already submitted */
	function sendRequest() {
		var year = $("#selectMonth").val().split(',')[0];
		var month = $("#selectMonth").val().split(',')[1];
		$.ajax({
			cache: false,
			method : "GET",
			url : "tdetails",
			data : "year=" + year + "&month=" + month,
			success : function(data) {
				var mydata = JSON.parse(data);
				console.log(mydata);
				var inputs = $(".time-field");
				var backlogs = [], count = 0;
				for (var x = 0; x < mydata.times.length; x++) {
					for (t in mydata.times[x]) {
						if (t.split("-")[1] != "null" && x == 0) {
							backlogs.push(t);
						}
					}
					break;
				}
				if(backlogs.length==0){
					$("#backlogtable").html("");
					$("#checkbacklog").attr('checked', false);
				}
				for (var b = 0; b < backlogs.length; b++) {
					var backlogmonthnoneditable =1;
					$("#checkbacklog").attr('checked', true);
					if (b == 0){
						$("#backlogtable").html("");
					}
					displayBacklog(b,backlogmonthnoneditable);
					$("#backlogselect0" + b).val(
							(backlogs[b].split("-")[1]).replace("/", ","));
					if (backlogs.length == b + 1) {
						var createBacklogCheck = 1;
					} else {
						var createBacklogCheck = 0;
					}
					backlogTimesheet(backlogs[b], b + 1, createBacklogCheck);
					$("#backlogselect" + (b + 1)).val(
							(backlogs[b].split("-")[1]).replace("/", ","));
				}
				for (var x = 0; x < mydata.times.length; x++) {
					for (t in mydata.times[x]) {
						if (t.split("-")[1] != "null") {
							if (Number(mydata.times[x][t]) != 0) {
								$("#backlogid_" + t.split("/").join("_")).val(
										mydata.times[x][t]);
							}
						} else {
							if (Number(mydata.times[x][t]) != 0) {
								if ((Number(mydata.times[x][t])) == "101") {
									mydata.times[x][t] = "L";
								}
								if ((Number(mydata.times[x][t])) == "102") {
									mydata.times[x][t] = "H";
								}
								inputs[x].value = mydata.times[x][t];
							}
						}
					}
					;

				}
			}
		
		});
	}

	/* JS function for displaying the sheet based on the month and year selected */
	function getsheet() {
		if ($("#selectMonth").val() != "0") {
			$("#fg").show();
			var year = $("#selectMonth").val().split(',')[0];
			var month = $("#selectMonth").val().split(',')[1];
			var i = 0;
			generateTimeSheet(month, year);
			$(".time-field")
					.bind(
							"keyup",
							function() {
								if(hiddenloc=="ONSITE"){
								if ($(this).val() > 8) {
									var confirmHours = confirm("You have entered more than 8 hours. Press OK if you want"+
											" to enter the hours for the current month . Press Cancel if you want to enter as previous months backlogs?");
									if (confirmHours) {

									} else {
										var hoursEntered = $(this).val();
										$(this).val(8);
										diffHours = hoursEntered - 8;
										if(i<1 && ($("#checkbacklog").is(":checked") == false))
										alert("Please enter the remaining "
												+ diffHours
												+ " hours after selecting the backlog month");
										
										if (i == 0 && ($("#checkbacklog").is(":checked") == false)) {
											$("#checkbacklog")
											.attr('checked', true);
											displayBacklog(0);
											i++;
										} else {
											alert("Please enter the remaining "
													+ diffHours
													+ " hours in the below backlog month selected");
										}

									}
								}
								
								}
								else{
									
									if ($(this).val() > 9) {
										var confirmHours = confirm("You have entered more than 9 hours. Press OK if you want"+
												" to enter the hours for the current month . Press Cancel if you want to enter as previous months backlogs?");
										if (confirmHours) {

										} else {
											var hoursEntered = $(this).val();
											$(this).val(9);
											diffHours = hoursEntered - 9;
											if(i<1 && ($("#checkbacklog").is(":checked") == false))
											alert("Please enter the remaining "
													+ diffHours
													+ " hours after selecting the backlog month");
											
											if (i == 0 && ($("#checkbacklog").is(":checked") == false)) {
												$("#checkbacklog")
												.attr('checked', true);
												displayBacklog(0);
												i++;
											} else {
												alert("Please enter the remaining "
														+ diffHours
														+ " hours in the below backlog month selected");
											}

										}
									}
									
								}
							});
			$("#allbuttons").show();
		}
	}

	/* JS function for dispalying the backlog checkbox option once month selected */
	function displayBacklog(identifier,id2) {
		if ($("#checkbacklog").is(":checked") == true) {
			$("#backLogMonth").show();
			
			createBacklog(identifier,id2);
		} else {
			$("#backLogMonth").hide();
			
			 
		}
	}

	/* JS function for creating backlog table - includes selectMonth option , Sheet based on select month and Remove button */
	function createBacklog(identifier,id2) {
		var logtable = $("#backlogtable");
		if(id2==1){
		var rowid = identifier + 1;
		}
		else
		var rowid = parseInt(this.$("#rowval").val()) + 1;
		$("#rowval").val(rowid);
		logtable
				.append("<tr id=\"rowid" + rowid + "\"><td><select id=\"backlogselect" + rowid + "\"></select></td> <td id=\"backlogsheet" + rowid + "\"></td><td id=\"removetd" + rowid + "\"></td></tr>");
		getLastMonthsBacklog("backlogselect" + rowid, 12);
		if(id2==1)
		$("#backlogselect" + rowid).attr('disabled',true);
		$("#backlogselect" + rowid).bind("change", function() {
			backlogselectchange(rowid)
			backlogDates = $("#backlogselect" + rowid).val();
		});
	}

	/* JS function for retreving the backlog timesheet already submitted */
	function backlogTimesheet(backlog, rowid, lastBacklogCheck) {
		var year = backlog.split("/")[0];
		var month = backlog.split("/")[1];
		var blog = backlog.split("-")[1].replace("/", ",");
		$("#backlogsheet" + rowid)
				.html(generateBacklogSheet(month, year, blog));
		if ($("#remove" + rowid).length == 0) {
			$("#removetd" + rowid)
					.html(
							"<input  id=\"remove" + rowid + "\" type=\"button\" value=\"Remove\" style='background-color: #52949b' />");
			$("#remove" + rowid)
					.bind(
							"click",
							function() {
								var removeconfirmation = confirm(" Are you sure you want to remove the row?");
								if (removeconfirmation)
									$(this).closest("tr").remove();
							});
			if (lastBacklogCheck == 0){
				
			}
			else
				$("#newbacklogbtn").show();
		}
	}

	/* JS function for changing the sheet based on change of backlog month */
	function backlogselectchange(rowid) {
		if ($("#backlogselect" + rowid).val() != "0") {
			var year = $("#selectMonth").val().split(',')[0];
			var month = $("#selectMonth").val().split(',')[1];
			var blog = $("#backlogselect" + rowid).val();
			$("#backlogsheet" + rowid).html(
					generateBacklogSheet(month, year, blog));
			if ($("#remove" + rowid).length == 0) {
				$("#removetd" + rowid)
						.html(
								"<input  id=\"remove" + rowid + "\" type=\"button\" value=\"Remove\" style='background-color: #52949b' />");
				
				$("#remove" + rowid)
						.bind(
								"click",
								function() {
									var removeconfirmation = confirm(" Please press OK if you want to remove the added row");
									if (removeconfirmation)
										$(this).closest("tr").remove();
								});
				$("#newbacklogbtn").show();
			}
		}
	}

	/* JS function for retreiving last 12 months in select Month option  */
	function getLastMonths(dropdownid, n) {
		var monthName = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		var dropDown = document.getElementById(dropdownid);
		var months = new Array();
		var today = new Date();
		var year = today.getFullYear();
		var month = today.getMonth();
	//	dropDown.options[0] = new Option("--Select Month--", "0");
		var i = 0;
		while (i <= n) {
			var key = year + "," + (month + 1);
			var value = monthName[month] + " - " + year;
			dropDown.options[i] = new Option(value, key);
			if (month == 0) {
				month = 11;
				year--;
			} else {
				month--;
			}
			i++;
		}
	}
	
	/* JS function for retreiving last 12 months in backlog select Month option after eliminating the selected month in clarity */
	function getLastMonthsBacklog(dropdownid, n) {
		var monthName = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		var id = $("#selectMonth").val();
		var timesheetYear = $("#selectMonth").val().split(',')[0];
		var timesheetMonth = $("#selectMonth").val().split(',')[1];
		var dropDown = document.getElementById(dropdownid);
		var newMonth =0
		var months = new Array();
		var year = timesheetYear;
		var month = timesheetMonth;
		if(month==1){
			newMonth = 11;
			year--
		}
		else{
		newMonth = (month-1)-1;
		}
		dropDown.options[0] = new Option("-Select-", "0");
		var i = 1;
		while (i <= n) {
			var key = year + "," + (newMonth + 1);
			var value = monthName[newMonth] + " - " + year;
			dropDown.options[i] = new Option(value, key);
			if (newMonth == 0) {
				newMonth = 11;
				year--;
			} else {
				newMonth--;
			}
			i++;
		}
	}

	/* JS function for generating timesheet based on month selected  */
	function generateTimeSheet(month, year) {
		var days = [ "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" ];
		var holiDays = [ "Sun", "Sat" ];
		var numberOfdays = findTotalDayInMonth(month, year);
		var position = new Date(year, month - 1, 1).getDay();
		var headers = "";
		var body = "";
		for (var i = 1; i <= numberOfdays; i++) {
			var sundayPosition = 0;
			var dayName = "";
			if (days.length > position) {
				dayName = days[position];
				position++;
			} else {
				position = 0;
				dayName = days[position];
				position++;
			}
			var timesheetid = "timesheet_" + year + "_" + month + "_" + i;
			headers += "<th>" + dayName + "<br>" + i + "/" + month + "</th>";
			if (dayName == "Sat" || dayName == "Sun") {
				body += "<td><input id=\"" + timesheetid + "\" class='time-field' type='text' style='background-color:lightgray'></td>";
			} else {
				body += "<td><input id=\"" + timesheetid + "\" class='time-field' type='text'></td>";
			}
		}
		openBacklog();
		$("#headers").html(headers);
		$("#tbody").html(body);

	}
	
	/* JS function for generating backlog timesheet based on month selected  */
	function generateBacklogSheet(month, year, blog) {
		var days = [ "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" ];
		var holiDays = [ "Sun", "Sat" ];
		var numberOfdays = findTotalDayInMonth(month, year);
		var position = new Date(year, month - 1, 1).getDay();
		var headers = "";
		var body = "";
		for (var i = 1; i <= numberOfdays; i++) {
			var sundayPosition = 0;
			var dayName = "";
			if (days.length > position) {
				dayName = days[position];
				position++;
			} else {
				position = 0;
				dayName = days[position];
				position++;
			}
			var backlogid = "backlogid_" + year + "_" + month + "_" + i + "-"
					+ blog.replace(",", '_');
			headers += "<th>" + dayName + "<br>" + i + "/" + month + "</th>";
			if (dayName == "Sat" || dayName == "Sun") {
				body += "<td><input id=\"" + backlogid + "\" class='time-field-backlog' type='text' style='background-color:lightgray'></td>";
			} else {
				body += "<td><input id=\"" + backlogid + "\" class='time-field-backlog' type='text'></td>";
			}
		}
		var backlogtext = "<table border=\"1\"><thead id='blogheader'><tr>" + headers + "</tr></thead><tbody><tr>"
				+ body + "<br/> </tr></tbody</table>";
		return backlogtext;
	}

	/* JS function for finding number of days of a month  */
	function findTotalDayInMonth(month, year) {
		console.log(new Date(year, month, 0).getDate());
		return new Date(year, month, 0).getDate();
	}

	/* JS function for displaying backlog and holiday once time sheet is generated  */
	function openBacklog() {
		$("#backLog").show();
		$("#holiday").show();
		
	}

	/* JS function for all validations once submit is pressed  */
	function submitValidations() {
		var alltimesheet = $('[id^=timesheet_]');
		var backlogalltimesheet = $('[id^=backlogid_]');
		var year = "";
		var month = "";
		var bLogArray = new Array();
		var timesheetCount = 0;
		var backlogCount = 0;
		var backLogdate = "";
		var breakOut = 0;
		var breakOutBacklog = 0;
		var breakOutHourCheck = 0;
		var breakOutAlphabetCheck = 0;
		var breakOutHourCheckBacLog = 0;
		var breakOutAlphabetCheckBacLog = 0;
		alltimesheet
		.each(function() {
			timesheetCount++;
		});
		alltimesheet
				.each(function() {
					
					year = $(this).attr('id').split('_')[1];
					month = $(this).attr('id').split('_')[2];
					var hoursentered = $(this).val();
					if (hoursentered.length > 0) {
						if (hoursentered > 24 && hoursentered <= 0) {
							alert("You are not allowed to enter more than 24 Hrs and less than 0 Hrs for a day .");
							breakOutHourCheck = 1;
						} else if (!(hoursentered == "L" || hoursentered == "l"
								|| hoursentered == "H" || hoursentered == "h" || (hoursentered < 24 && hoursentered >= 0))) {
							if(breakOutAlphabetCheck==1){
							
							}
							else{
							alert("Please Enter Valid Hours");
							breakOutAlphabetCheck = 1;
							return false;
							}
							
						}
					}
					
				})
		backlogalltimesheet
				.each(function() {
			backlogCount++;
				});
		backlogalltimesheet
		.each(function() {
					var yearTs = $(this).attr('id').split('_')[1];
					var monthTs = $(this).attr('id').split('_')[2];
					var date = $(this).attr('id').split('_')[3];
					var newDate = date.split('-')[0];
					var yearBl = date.split('-')[1];
					var monthBl = $(this).attr('id').split('_')[4];
					var hoursentered = $(this).val();
					if (hoursentered.length > 0) {
						if (hoursentered > 24 && hoursentered <= 0) {
							alert("You are not allowed to enter more than 24 Hrs and less than 0 Hrs for a day.");
							breakOutHourCheckBacLog = 1;
						} 
						else 
							if(!(hoursentered < 24 && hoursentered >= 0)) {
							if(breakOutAlphabetCheck==0 && breakOutAlphabetCheckBacLog==0){
							alert("Please Enter Valid Hours");
							breakOutAlphabetCheckBacLog=1;
							return false;
							}
							else if(breakOutAlphabetCheck==1 && breakOutAlphabetCheckBacLog==0){
								breakOutAlphabetCheckBacLog=1;
								return false;
							
								}
							}
					}
					
					if (year == yearBl && month == monthBl) {
						if (breakOut == 0) {
							alert("You are not allowed to select same month for timesheet and backlog.");
							breakOut = 1;
							return false;
						}
					}
					var yearMonthBacklog = yearBl.concat("/", monthBl);
					if (!(bLogArray.indexOf(yearMonthBacklog)>-1)) {
						bLogArray.push(yearMonthBacklog);
					}
					
				})
		if (backlogCount > timesheetCount) {
			var numberBacklogs = backlogCount / timesheetCount;
			if (bLogArray.length != numberBacklogs) {
				alert("You are not allowed to select same month for two backlogs.");
				breakOutBacklog = 1;
			}
		}
		if ((breakOut == 1) || breakOutBacklog == 1 || breakOutHourCheck == 1
				|| breakOutAlphabetCheck == 1
				|| breakOutAlphabetCheckBacLog == 1
				|| breakOutAlphabetCheckBacLog == 1) {
			return 1;
		} else {
			return 2;
		}
	}

	/* JS function for submission of the hours  */
	function submittimesheet() {
		var arr = new Array();
		var backarr = new Array();
		var alltimesheet = $('[id^=timesheet_]');
		var checkTimeSheet=0;
		alltimesheet.each(function() {
			var year = $(this).attr('id').split('_')[1];
			var month = $(this).attr('id').split('_')[2];
			var date = $(this).attr('id').split('_')[3];
			
			/*changed*/
			var hoursentered = $(this).val().trim();
			
			
			if (hoursentered.length == 0)
				hoursentered = 0;
				if (hoursentered == "L" || hoursentered == "l") {
					hoursentered = 101;
				}
				if (hoursentered == "H" || hoursentered == "h") {
					hoursentered = 102;
				}
			
			var alltimesheetdata = year.concat('/', month, '/', date, '-',
					hoursentered);
			arr.push(alltimesheetdata)
			document.getElementById('hidden_array').value = arr;
		})
		var backarr = new Array();
		var backlogMonthArray = new Array();
		var backlogDate = "";
		var checkBacklog=0;
		var backlogalltimesheet = $('[id^=backlogid_]');


backlogalltimesheet.each(function() {
			var year = $(this).attr('id').split('_')[1];
			var month = $(this).attr('id').split('_')[2];
			var date = $(this).attr('id').split('_')[3];
			var newDate = date.split('-')[0];
			var yrblog = date.split('-')[1];
			var mnblog = $(this).attr('id').split('_')[4];
			var combinedMonYear = yrblog.concat('/', mnblog);
			
			/*changed*/
			var hoursentered = $(this).val().trim();
			
			if (hoursentered.length == 0)
				hoursentered = 0;
			var backlogalltimesheetdata = year.concat('/', month, '/', newDate,
					':' + combinedMonYear + '-', hoursentered);
			backarr.push(backlogalltimesheetdata);
			document.getElementById('backlog_hidden_array').value = backarr;
		})
	}
</script>

</head>
<body>

	<span id="ctl00_cphContents_lblheading" class="edit_header"
		style="font-size: 14px">Clarity Hours Submission</span>
	<br>
	<div class="panel panel-default">
		<table>
			<div class="edit_header" style="color: #427068">
				<br> <b style="color: black">Associate Id: </b><%=ad.getAssociateID()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b style="color: black">Associate Name: </b><%=ad.getAssociateName()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b style="color: black">HCSC ID: </b><%=ad.getHCSCID()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b style="color: black">Project Name: </b><%=ad.getProjectName()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b style="color: black">Location: </b><%=ad.getOnsiteOffshore()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b style="color: black">City: </b><%=ad.getLocation()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%-- <b style="color: black">Rate: </b><%=ad.getRate()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
				<input type="hidden" id="hiddenloc" name="hiddenloc"
					value=<%=ad.getOnsiteOffshore()%>></input>
			</div>
			<br>

			<div id="updatebtn" class="edit_statics">
				<input title="View Total Hours" type="submit" class="btnupdate"
					id="btnupdate" value="VIEW TOTALS"
					style="background-color: #52949b; color: white" />&nbsp;&nbsp;&nbsp;&nbsp;
				<!--  <div id="totalhourtext">-->
				TimeSheet Hours : <input ="text"  id="totalhours" class="totalhours" />
				Backlog Hours : <input ="text"  id="totalbhours" class="totalbhours" />

			</div>
			<div id="fg" class="edit_statics"
				style="font-size: 11px; color: black">
				FieldGlass Hours : &nbsp; <input ="text" id="fghours"
					class="fghours" readonly />
			</div>

			<br>

			</div>



		</table>
	</div>

	<div id="statictext" class="edit_statics" style="font-size: 11px">
		<!-- 			<b> Enter current month efforts in the below section &nbsp; </b> -->
		** Enter Current Month Efforts in the Below Section
	</div>
	<div>
		<br>
		<table>
			<tr>
				<td><select id="selectMonth" class="edit_header"></select></td>
				<td>

					<table border=1 class="edit_header">
						<thead id="headers"></thead>
						<tbody id="tbody">
						</tbody>
					</table>
				</td>
			</tr>
		</table>



		<div id="holiday" class="edit_statics" style="color: #e53310">
			** Please Fill With L or H for Leaves or Holidays Respectively <br>
			<br>
		</div>

		<div id="backlogsEdit" class="edit_statics">** Enter Backlog
			Efforts by Clicking on Backlog Checkbox</div>
		<!-- 		<table border=1> -->
		<!-- 			<thead id="backlogheaders"></thead> -->
		<!-- 			<tbody id="backlogtbody"> -->
		<!-- 			</tbody> -->
		<!-- 		</table> -->
		<div id="backLog" class="edit_header">
			<br /> <input type="checkbox" name="checkbacklog" id="checkbacklog">
			Please Tick to Submit Previous Month's Backlog Efforts </b>
		</div>
		<input type="hidden" value="0" id="rowval" />
		<div id="backLogMonth" class="edit_header">
			<table id="backlogtable">
			</table>
		</div>
	</div>

	<br />
	<div id="newbacklogbtn" class="edit_header">
		<input title="Add new backlog" type="submit" class="button2"
			id="btnaddnewbaclog" value="ADD BACKLOG"
			style="background-color: #52949b; color: white">
	</div>
	<br>
	<form action="fromClarityHours" name="claritysubmit" method="post"
		class="edit_header">
		<div id="allbuttons">
			<input type="hidden" id="hidden_array" name="hiddenArray"></input> <input
				type="hidden" id="backlog_hidden_array" name="BacklogHiddenArray"></input>
			<input type="hidden" id="backlog_date" name="BacklogDate"></input>

			<!-- 			 <button type="button" class="button2" name="Back" -->
			<!-- 				onclick="history.back()" style="background-color: #52949b">BACK</button>  -->

			<form action="loginhere" method="get">
				<a href=loginhere class="test"><input
					title="Go Back To Home Page" type="button"
					style="color: white; background-color: #52949b" Name="Back"
					value="BACK"></input></a>
			</form>

			<input title="Submit Clarity Hours" type="submit" class="button2"
				id="btnsubmit" value="SUBMIT"
				style="background-color: #52949b; color: white">
		</div>
	</form>
</body>
</html>

















