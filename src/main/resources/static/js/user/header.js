$(document).ready(function () {

    let userId = sessionStorage.getItem('id');

    if(userId != null){
        $("#user-name").html(sessionStorage.getItem('username'));
        $("#no-login").hide();
        $("#user-login").show();
    }
});