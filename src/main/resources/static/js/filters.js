function filter() {
    var requiredAddress = document.getElementById("address").value.toUpperCase();
    var bottomPrice = document.getElementById("bottomPrice").value;
    var topPrice = document.getElementById("topPrice").value;
    var records = document.getElementById("group").getElementsByTagName("record");
    var ifUsersRecords = document.getElementById("usersRecords").checked;
    var berth = document.getElementById("berth").value;
    if (topPrice == 0) {
        topPrice = Infinity;
    }
    for (i = 0; i < records.length; i++) {
        var recordAddress = records[i].getElementsByTagName("h4")[0].textContent.toUpperCase();
        var recordPrice = Number(records[i].getElementsByTagName("h4")[1].textContent.slice(0, -1));
        var ticker = records[i].getElementsByClassName("ticker").length;
        var berthEn = records[i].getElementsByClassName("berth")[0].textContent;
        if (recordAddress.indexOf(requiredAddress) != -1 && recordPrice <= topPrice && recordPrice >= bottomPrice && (ifUsersRecords && ticker || !ifUsersRecords) && berth <= berthEn ) {
            records[i].style.display = "";
        } else {
            records[i].style.display = "none";
        }
    }
}