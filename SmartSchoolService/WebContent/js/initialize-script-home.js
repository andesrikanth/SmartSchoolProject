
//Global Variables
var schoolName="Srikanth Public School";
var schoolLogoPath="img/SchoolLogo.png";
var menu1="Academics";
var menu2="Branches";
var menu1_options = ["Curriculum", "Faculty"];
var menu1_options_location = ["home.jsf", "https://www.google.com"];
var menu2_options = ["New York", "California"];
var menu2_options_location = ["#", "home.jsf"];




//Method that will replace values on the jsf page dynamically based on the values set in the above global variables.
function setStaticValues() {
	document.getElementById("schoolLogo").src=schoolLogoPath;
	document.getElementById("schoolName").innerHTML=schoolName;
	document.getElementById("menu1").innerHTML=menu1;
	document.getElementById("menu2").innerHTML=menu2;
	
	
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
	for (index = 0; index < menu1_options.length; index++) {
		menu2_values.addMenuItem(menu2_options[index],"location='"+menu2_options_location[index]+"'");
	}
	  
writeMenus();

} 