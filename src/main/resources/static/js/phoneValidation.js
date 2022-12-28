function validate() {
    var phone = document.getElementById('floatingPhone').value;
    if (!validatePhoneNumber(phone)) {

    document.getElementById('phone_error').classList.remove('hidden');
    } else {

    document.getElementById('phone_error').classList.add('hidden');
        alert("validation success")
    }
}

function validatePhoneNumber(input_str) {
    var re = /^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$/;
  
    return re.test(input_str);
  }
