$(document).ready(function(){

    var movieId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var userId = sessionStorage.getItem('id');
    var isLike = false;
    var movie;
    var status = 0;

    getMovie();
    if(sessionStorage.getItem('role') === 'admin')
        getMovieLikeChart();

    function getMovieLikeChart() {
       getRequest(
           '/movie/' + movieId + '/like/date',
           function(res){
               var data = res.content,
                    dateArray = [],
                    numberArray = [];
               data.forEach(function (item) {
                   dateArray.push(item.likeTime);
                   numberArray.push(item.likeNum);
               });

               var myChart = echarts.init($("#like-date-chart")[0]);

               // 指定图表的配置项和数据
               var option = {
                   title: {
                       text: '想看人数变化表'
                   },
                   xAxis: {
                       type: 'category',
                       data: dateArray
                   },
                   yAxis: {
                       type: 'value'
                   },
                   series: [{
                       data: numberArray,
                       type: 'line'
                   }]
               };

               // 使用刚指定的配置项和数据显示图表。
               myChart.setOption(option);
           },
           function (error) {
               alert(error);
           }
       );
    }

    function getMovie() {
        getRequest(
            '/movie/'+movieId + '/' + userId,
            function(res){
                var data = res.content;
                isLike = data.islike;
                status = data.status;
                repaintMovieDetail(data);
                movie = data;
            },
            function (error) {
                alert(error);
            }
        );
    }

    function repaintMovieDetail(movie) {
        !isLike ? $('.icon-heart').removeClass('error-text') : $('.icon-heart').addClass('error-text');
        $('#like-btn span').text(isLike ? ' 已想看' : ' 想 看');
        $('#delete-btn span').text(status==0 ?  ' 下 架' : ' 已下架');
        $('#movie-img').attr('src',movie.posterUrl);
        $('#movie-name').text(movie.name);
        $('#order-movie-name').text(movie.name);
        $('#movie-description').text(movie.description);
        $('#movie-startDate').text(new Date(movie.startDate).toLocaleDateString());
        $('#movie-type').text(movie.type);
        $('#movie-country').text(movie.country);
        $('#movie-language').text(movie.language);
        $('#movie-director').text(movie.director);
        $('#movie-starring').text(movie.starring);
        $('#movie-writer').text(movie.screenWriter);
        $('#movie-length').text(movie.length);
    }

    // user界面才有
    $('#like-btn').click(function () {
        var url = isLike ?'/movie/'+ movieId +'/unlike?userId='+ userId :'/movie/'+ movieId +'/like?userId='+ userId;
        postRequest(
             url,
            null,
            function (res) {
                 isLike = !isLike;
                getMovie();
            },
            function (error) {
                alert(error);
            });
    });

    // admin界面才有
    $("#modify-btn").click(function(){
        $("#movie-modify-name-input").val(movie.name);
        $("#movie-modify-date-input").val(movie.startDate.substr(0,10));
        $("#movie-modify-img-input").val(movie.posterUrl);
        $("#movie-modify-description-input").val(movie.description);
        $("#movie-modify-type-input").val(movie.type);
        $("#movie-modify-length-input").val(movie.length);
        $("#movie-modify-country-input").val(movie.country);
        $("#movie-modify-language-input").val(movie.language);
        $("#movie-modify-director-input").val(movie.director);
        $("#movie-modify-star-input").val(movie.starring);
        $("#movie-modify-writer-input").val(movie.screenWriter);
    });
    
    $("#movie-modify-btn").click(function () {
       var formData = getMovieForm();
        if(!validateModifiedMovieForm(formData)) {
            return; 
        }
        postRequest(
            '/movie/update',
            formData,
            function (res) {
                getMovie();
                $("#movieModalModify").modal('hide');
            },
             function (error) {
                alert(error);
            });
    });
    
        function getMovieForm() {
        return {
            id: movieId,
            name: $('#movie-modify-name-input').val(),
            startDate: $('#movie-modify-date-input').val(),
            posterUrl: $('#movie-modify-img-input').val(),
            description: $('#movie-modify-description-input').val(),
            type: $('#movie-modify-type-input').val(),
            length: $('#movie-modify-length-input').val(),
            country: $('#movie-modify-country-input').val(),
            starring: $('#movie-modify-star-input').val(),
            director: $('#movie-modify-director-input').val(),
            screenWriter: $('#movie-modify-writer-input').val(),
            language: $('#movie-modify-language-input').val()
        };
    }

    function validateModifiedMovieForm(data) {
        var isValidate = true;
        if(!data.name) {
            isValidate = false;
            $('#movie-modify-name-input').parent('.form-group').addClass('has-error');
            alert("电影名称不能为空！")
        }
        else if(!data.startDate) {
            isValidate = false;
            $('#movie-modify-date-input').parent('.form-group').addClass('has-error');
            alert("上映时间不能为空！")
        }
        else if(!data.posterUrl) {
            isValidate = false;
            $('#movie-modify-img-input').parent('.form-group').addClass('has-error');
            alert("请添加电影海报！")
        }
        else if(!data.length) {
            isValidate = false;
            $('#movie-modify-length-input').parent('.form-group').addClass('has-error');
            alert("时长不能为空！")
        }
        
        //url格式检查
        var UrlStr ='(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]';
        var re = new RegExp(UrlStr)
        if (data.posterUrl && !re.test(data.posterUrl)){
            isValidate = false;
            $('#movie-modify-img-input').parent('.form-group').addClass('has-error');
            alert("请输入正确的图片url地址！")
        }
        return isValidate;
    }
    
    $("#delete-btn").click(function () {
        var r = confirm("确认要下架该电影吗？")
        var form = {
            movieIdList:[movieId]
        }
        if (r){
            status = 1;
            postRequest(
                '/movie/off/batch',
                form,
                function(res){
                    getMovie();
                },
                function (error) {
                    alert(error);
                });
        }
    alert("该电影已下架");
    });

});