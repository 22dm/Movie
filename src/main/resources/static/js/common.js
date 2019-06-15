function getRequest(url, onSuccess, onError) {
    $.ajax({
        type: 'GET',
        url: url,
        async: true,
        success: onSuccess,
        error: onError
    });
}

function postRequest(url, data, onSuccess, onError) {
    $.ajax({
        type: 'POST',
        url: url,
        async: true,
        data: JSON.stringify(data),
        contentType: 'application/json',
        processData: false,
        success: onSuccess,
        error: onError
    });
}

function formatDateYMD(dateStr) {
    let date = new Date(dateStr);
    let year = date.getFullYear() + '';
    let month = date.getMonth() + 1 + '';
    let day = date.getDate() + '';
    month.length === 1 && (month = '0' + month);
    day.length === 1 && (day = '0' + day);
    return year + '-' + month + '-' + day;
}

function formatDateMD(dateStr) {
    let date = new Date(dateStr);
    let month = date.getMonth() + 1 + '';
    let day = date.getDate() + '';
    month.length === 1 && (month = '0' + month);
    day.length === 1 && (day = '0' + day);
    return month + ' 月 ' + day + ' 日';
}

function formatDateMDHM(dateStr) {
    let date = new Date(dateStr);
    let month = date.getMonth() + 1 + '';
    let day = date.getDate() + '';
    let hour = date.getHours() + '';
    let minutes = date.getMinutes() + '';
    month.length === 1 && (month = '0' + month);
    day.length === 1 && (day = '0' + day);
    hour.length === 1 && (hour = '0' + hour);
    minutes.length === 1 && (minutes = '0' + minutes);
    return month + ' 月 ' + day + ' 日 ' + hour + ":" + minutes;
}

function formatDateHM(dateStr) {
    let date = new Date(dateStr);
    let hour = date.getHours() + '';
    let minutes = date.getMinutes() + '';
    hour.length === 1 && (hour = '0' + hour);
    minutes.length === 1 && (minutes = '0' + minutes);
    return hour + ":" + minutes;
}

function formatDate(dateStr) {
    let date = new Date(dateStr);
    let year = date.getFullYear() + "";
    let month = date.getMonth() + 1 + "";
    let day = date.getDate() + "";
    let hour = date.getHours() + "";
    let minutes = date.getMinutes() + "";
    month.length === 1 && (month = "0" + month);
    day.length === 1 && (day = "0" + day);
    hour.length === 1 && (hour = "0" + hour);
    minutes.length === 1 && (minutes = "0" + minutes);
    return year + "-" + month + '-' + day + ' ' + hour + ":" + minutes;
}

function formatPrice(price) {
    return (price / 100).toFixed(2);
}

function logout() {
    postRequest(
        '/logout',
        null,
        function () {
            sessionStorage.clear();
            window.location.href = '/index';
        });
}
