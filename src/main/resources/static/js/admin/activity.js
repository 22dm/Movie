let itemDecsribe = "优惠活动";
let addUrl = "/activity/add";
let getAllUrl = "/activity/getAll";

$(document).ready(function () {
    $("#nav-user-name").html(sessionStorage.getItem('username'));
    $("#sidebar-promotion").addClass("active");
    $("#sidebar-activity").addClass("active");

    getAllMovies();
    getCouponList();
});

function renderItemList() {
    let itemList = $("#item-list");
    itemList.empty();
    let itemListDom = "";
    items.forEach(function (activity) {

        let movieDomStr = "";
        if (activity.movies.length) {
            activity.movies.forEach(function (movie) {
                movieDomStr += "<li>" + movie + "</li>";
            });
        } else {
            movieDomStr = '<li>所有热映电影</li>';
        }

        itemListDom += `<div class="col-12 col-sm-6 col-lg-4">
                            <div class="card card-success">
                                <div class="card-header">
                                    <h4>${activity.name}</h4>
                                    <div class="card-header-action">${formatDateYMD(activity.startTime)} - ${formatDateYMD(activity.endTime)}</div>
                                </div>
                                <div class="card-body">参与电影：${movieDomStr}</div>
                                <div class="card-footer">${activity.coupon.name}：满 ${formatPrice(activity.coupon.targetAmount)} 减 ${formatPrice(activity.coupon.discountAmount)}</div>
                            </div>
                        </div>`
    });
    itemList.append(itemListDom);
}

function getAllMovies() {
    getRequest(
        '/movie/getOnAndPre',
        function (res) {
            let movieList = res.content;
            movieList.forEach(function (movie) {
                $('#movie-input').append(`<option value=${movie.id}>${movie.name}</option>`);
            });
            $('#movie-input').select2({width: '100%', closeOnSelect: false, allowClear: true, placeholder: '全部电影'});
        },
        function (error) {
            alert(error);
        }
    );
}

function getForm() {
    return {
        name: $("#name-input").val(),
        description: $("#description-input").val(),
        movies: $("#movie-input").val(),
        couponId: $("#coupon-input").val(),
        startTime: $("#start-date-input").val(),
        endTime: $("#end-date-input").val()
    };
}

function clearForm() {
    $("#name-input").val("");
    $("#description-input").val("");
    $("#movie-input").val(null).trigger("change");
    $("#coupon-input").val("");
    $("#start-date-input").val("");
    $("#end-date-input").val("");
    if(form.hasClass("was-validated")){
        form.removeClass("was-validated")
    }
}

function getCouponList() {
    getRequest(
        '/coupon/getAll',
        function (res) {
            res.content.forEach(function (coupon) {
                $("#coupon-input").append(`<option value=${coupon.id}>${coupon.name}（满 ${formatPrice(coupon.targetAmount)} 减 ${formatPrice(coupon.discountAmount)}</option>`);
            });
            $('#coupon-input').select2({width: '100%', minimumResultsForSearch: Infinity});
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}