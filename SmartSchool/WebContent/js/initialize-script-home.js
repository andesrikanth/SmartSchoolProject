
//Global Variables
var schoolName="Boston Public School";
var schoolLogoPath="img/SchoolLogo.png";

var menu1="Academics";
var menu2="Branches";
var menu3="Admin";

var menu1_options = ["Curriculum", "Faculty"];
var menu1_options_location = ["home.jsf", "https://www.google.com"];
var menu2_options = ["New York", "California"];
var menu2_options_location = ["#", "home.jsf"];
var menu3_options = ["Standard Register", "Student Register","Class Register"];
var menu3_options_location = ["StandardRegister.xhtml", "StudentRegister.xhtml","#"];

var OfficeAddress1="2300 Washington Street";
var OfficeAddress2="Roxbury, MA 02119";
var OfficePhone="Phone : 617-635-9000";
var OfficeFax="Fax : 617-635-9059";


//Method that will replace values on the jsf page dynamically based on the values set in the above global variables.
function setStaticValues() {
	
	//Below are the standard static menu items.(common for every page)
	document.getElementById("schoolLogo").src=schoolLogoPath;
	document.getElementById("schoolName").innerHTML=schoolName;
	
	document.getElementById("menu1").innerHTML=menu1;
	document.getElementById("menu2").innerHTML=menu2;
	
	document.getElementById("OfficeAddress1").innerHTML=OfficeAddress1;
	document.getElementById("OfficeAddress2").innerHTML=OfficeAddress2;
	document.getElementById("OfficePhone").innerHTML=OfficePhone;
	document.getElementById("OfficeFax").innerHTML=OfficeFax;
	
	//below are the varying menus per each page. Here need to check whether the menu component is null or not.
	if(document.getElementById("menu3") != null){
		document.getElementById("menu3").innerHTML=menu3;
	}
	
}


//Menu loading function
function mmLoadMenus() {
if (window.menu1_values) return;

	window.menu1_values = new Menu("root",100,25,"Arial, Helvetica, sans-serif",13,"#2e3092","#da2126","#f3fbfe","#FFFFFF","left","middle",5,0,1000,-5,7,true,true,true,5,true,true);
	for (index = 0; index < menu1_options.length; index++) {
		menu1_values.addMenuItem(menu1_options[index],"location='"+menu1_options_location[index]+"'");
	}
	
	
	/*menu1_values.addMenuItem("Curriculum","location='home.xhtml'");
	menu1_values.addMenuItem("Faculty","location='https://www.google.com'");*/
	
	window.menu2_values = new Menu("root",100,25,"Arial, Helvetica, sans-serif",13,"#2e3092","#da2126","#f3fbfe","#FFFFFF","left","middle",5,0,1000,-5,7,true,true,true,5,true,true);
	for (index = 0; index < menu2_options.length; index++) {
		menu2_values.addMenuItem(menu2_options[index],"location='"+menu2_options_location[index]+"'");
	}
	
	window.menu3_values = new Menu("root",120,25,"Arial, Helvetica, sans-serif",13,"#2e3092","#da2126","#f3fbfe","#FFFFFF","left","middle",5,0,1000,-5,7,true,true,true,5,true,true);
	for (index = 0; index < menu3_options.length; index++) {
		menu3_values.addMenuItem(menu3_options[index],"location='"+menu3_options_location[index]+"'");
	}
	  
	writeMenus();

} 