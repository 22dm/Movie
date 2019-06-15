var dates;
var movieId;
var userId;
var like;

$(document).ready(function () {
    movieId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    userId = sessionStorage.getItem('id');

    getMovie();
    getLike();
    getSchedule();
});

function getMovie() {
    getRequest(
        '/movie/get?id=' + movieId,
        function (res) {
            let movie = res.content;
            //isLike = data.islike;
            renderMovie(movie);
        },
        function (error) {
            alert(error);
        }
    );
}

function getSchedule() {
    getRequest(
        '/schedule/userGet?movieId=' + movieId,
        function (res) {
            if (res.success && res.content.length > 0) {
                renderItemList(res.content);
            }else {
                $("#schedules").hide();
            }
        },
        function (error) {
            alert(error);
        }
    );
}

function renderMovie(movie) {
        $('#movie-name').html(movie.name);
        $('#movie-poster').attr("src", movie.posterUrl);
        $('#movie-length').html(movie.length);
        $('#movie-type').html(movie.type);
        $('#movie-description').html(movie.description);
        $('#movie-country').html(movie.country);
        $('#movie-language').html(movie.language);
        $('#movie-date').html(formatDateYMD(movie.startDate));
        $('#movie-actor').html(movie.actor);
}

function getLike() {
    getRequest(
        `/movie/getByIdWithLike?movieId=${movieId}&userId=${userId}`,
        function (res) {
            like = res.content;
            renderLike();
            renderItemList(res.content);
        },
        function (error) {
            alert(error);
        }
    );
}

function renderLike() {
    let likeIcon = $('#like-icon');
    if(like.isLike){
        likeIcon.removeClass("far");
        likeIcon.addClass("fas");
        $('#like-btn').html('已想看');
    }else{
        likeIcon.removeClass("fas");
        likeIcon.addClass("far");
        $('#like-btn').html('想看');
    }
    $('#like-count').html(like.count)
}

function renderItemList(schedules) {
    let first = true;

    schedules.forEach(function (schedule) {
        let startDateStr = formatDateYMD(schedule.startTime);
        let activeShow = "";
        if(first){
            activeShow = "active show";
        }
        if($("#tab-" + startDateStr).length === 0){
            $("#schedule-date").append(`<li class="nav-item ${activeShow}"><a class="nav-link" id="tab-${startDateStr}" data-toggle="tab" href="#table-${startDateStr}" role="tab" aria-selected="false">${formatDateMD(schedule.startTime)}</a></li>`);
            $("#schedule-list").append(`<div class="tab-pane fade ${activeShow}" id="table-${startDateStr}" role="tabpanel">
                                            <div class="table-responsive">
                                                <table class="table table-bordered table-md text-center">
                                                    <thead>
                                                        <tr>
                                                            <th>影厅名</th>
                                                            <th>开始时间</th>
                                                            <th>预计散场时间</th>
                                                            <th>类型</th>
                                                            <th>票价</th>
                                                            <th>购买</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="list-${startDateStr}"></tbody>
                                                </table>
                                            </div>
                                          </div>`);
        }
        $("#list-" + startDateStr).append(`<tr>
                                             <td>${schedule.hall.name}</td>
                                             <td>${formatDateHM(schedule.startTime)}</td>
                                             <td>${formatDateHM(schedule.endTime)}</td>
                                             <td>${schedule.type}</td>
                                             <td>￥${formatPrice(schedule.fare)}</td>
                                             <td><a href="/user/selectSeats?id=${schedule.id}" class="btn btn-primary">选座购票</a></td>
                                           </tr>`);

        first = false;
    });
}

function movieLike() {
    let url = like.isLike ?`/movie/unlike?movieId=${movieId}&userId=${userId}` :`/movie/like?movieId=${movieId}&userId=${userId}`;
    postRequest(
        url,
        null,
        function (res) {
            getLike();
        },
        function (error) {
            alert(error);
        });
}