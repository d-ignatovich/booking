var tomorrow = new Date(new Date().getTime() + 86400000);
var dd = tomorrow.getDate();
var mm = tomorrow.getMonth() + 1;
var yyyy = tomorrow.getFullYear();
if (dd < 10) {
   dd = '0' + dd;
}
if (mm < 10) {
   mm = '0' + mm;
}  
tomorrow = yyyy + '-' + mm + '-' + dd;
document.getElementById("start").setAttribute("min", tomorrow);

var afterTomorrow = new Date(new Date().getTime() + 86400000 * 2);
var dd = afterTomorrow.getDate();
var mm = afterTomorrow.getMonth() + 1;
var yyyy = afterTomorrow.getFullYear();
if (dd < 10) {
   dd = '0' + dd;
}
if (mm < 10) {
   mm = '0' + mm;
}
afterTomorrow = yyyy + '-' + mm + '-' + dd;
document.getElementById("end").setAttribute("min", afterTomorrow);

var max = new Date(new Date().getTime() + 86400000 * 90);
var dd = max.getDate();
var mm = max.getMonth() + 1;
var yyyy = max.getFullYear();
if (dd < 10) {
   dd = '0' + dd;
}
if (mm < 10) {
   mm = '0' + mm;
}
max = yyyy + '-' + mm + '-' + dd;
document.getElementById("end").setAttribute("max", max);