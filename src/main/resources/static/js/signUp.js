$(document).ready(function () {
    let password = $("#password");
    let confirmPassword = $("#confirm-password");

    function validatePassword(){
        if(password.val() !== confirmPassword.val()) {
            confirmPassword[0].setCustomValidity("Passwords Don't Match");
        } else {
            confirmPassword[0].setCustomValidity('');
        }
    }

    password.change(validatePassword);
    confirmPassword.keyup(validatePassword);

    $("#signup-btn").click(function () {
        postRequest(
            '/register',
            getForm(),
            function (res) {
                if (res.success) {
                    alert("注册成功");
                    window.location.href = "/login";
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(error);
            });
    });
});

function getForm() {
    return {
        username: $('#signUp-name').val(),
        password: $('#signUp-password').val()
    };
}