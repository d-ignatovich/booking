var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1;
var yyyy = today.getFullYear();
if (dd < 10) {
   dd = '0' + dd;
}
if (mm < 10) {
   mm = '0' + mm;
}  
today = yyyy + '-' + mm + '-' + dd;
document.getElementById("start").setAttribute("min", today);

var tommorow = new Date(new Date().getTime() + 86400000);
var dd = tommorow.getDate();
var mm = tommorow.getMonth() + 1;
var yyyy = tommorow.getFullYear();
if (dd < 10) {
   dd = '0' + dd;
}
if (mm < 10) {
   mm = '0' + mm;
} 

tommorow = yyyy + '-' + mm + '-' + dd;
document.getElementById("end").setAttribute("min", tommorow);
