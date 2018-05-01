function validate() {

    var name = document.getElementById("name").value == "";
    for(var i = 0; i < name.length; i ++) {
        if(name[i].value == "") {
            alert("Please enter a valid name");
            return false;
        }

    }

}