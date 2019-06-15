function login() {
    postRequest(
        '/check',
        getForm(),
        function (res) {
            if (res.success) {
                sessionStorage.setItem('username', res.content.username);
                sessionStorage.setItem('id', res.content.id);
                if (res.content.role === 1) {
                    sessionStorage.setItem('role', 'admin');
                    window.location.href = "/admin/movie"
                } else {
                    sessionStorage.setItem('role', 'user');
                    window.location.href = "/index"
                }
            } else {
                console.log(res);
                showInfo('登录失败', res.message, true);
            }
        },
        function (error) {
            alert(error);
        });
}

function getForm() {
    return {
        username: $('#username').val(),
        password: $('#password').val()
    };
}