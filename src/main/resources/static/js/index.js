$(document).ready(function () {
    getMovieList('');

    function getMovieList(keyword) {
        getRequest(
            `/movie/search?keyword=${keyword}`,
            function (res) {
                renderMovieList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    function renderMovieList(list) {
        let movieList = $('#movie-list');
        movieList.empty();
        let movieDomStr = '';
        list.forEach(function (movie) {
            movieDomStr +=
                `<div class="col-12 col-sm-6 col-md-6 col-lg-3">
                    <article class="article">
                        <div class="article-header" style='height: 236px'>
                            <div class="article-image" id="movie-poster-${movie.id}"></div>
                            <div class="article-title">
                                <h2><a href="/user/movieDetail?id=${movie.id}">${movie.name}</a></h2>
                            </div>
                        </div>
                    </article>
                 </div>`;
        });
        movieList.append(movieDomStr);
        list.forEach(function (movie) {
            $(`#movie-poster-${movie.id}`).css("background-image", `url("${movie.posterUrl}")`);
        });
    }

    $('#search-btn').click(function () {
        getMovieList($('#search-input').val());
    });
});