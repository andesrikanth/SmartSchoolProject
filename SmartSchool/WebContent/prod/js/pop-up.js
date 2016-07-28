
function mmLoadMenus() {

if (window.first_menu) return;

window.first_menu = new Menu("root",100,25,"Arial, Helvetica, sans-serif",13,"#2e3092","#da2126","#f3fbfe","#FFFFFF","left","middle",5,0,1000,-5,7,true,true,true,5,true,true);
//new Menu("root",79,16,"Arial, Helvetica, sans-serif",10,"#000000","#FFFFFF","#99FFCC","#000000","left","middle",3,0,1000,-5,7,true,true,true,0,true,true);

first_menu.addMenuItem("Home","location='home.xhtml'");
first_menu.addMenuItem("Testing","location='https://www.google.com'");

window.second_menu = new Menu("root",100,25,"Arial, Helvetica, sans-serif",13,"#2e3092","#da2126","#f3fbfe","#FFFFFF","left","middle",5,0,1000,-5,7,true,true,true,5,true,true);

second_menu.addMenuItem("Test2","location='https://www.google.com'");
second_menu.addMenuItem("My&nbsp;Test","location='https://www.google.com'");
  
writeMenus();

} 