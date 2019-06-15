$(document).ready(function() {

    $("#nav-user-name").html(sessionStorage.getItem('username'));
    $("#sidebar-statistic").addClass("active");

    //getScheduleRate();

    //getBoxOffice();

    //getAudiencePrice();

    //getPlacingRate();

    //getPolularMovie();

    getPie();
});

function getScheduleRate() {

    getRequest(
        '/statistics/scheduleRate',
        function (res) {
            var data = res.content||[];
            var tableData = data.map(function (item) {
                return {
                    value: item.time,
                    name: item.name
                };
            });
            var nameList = data.map(function (item) {
                return item.name;
            });
            var option = {
                title : {
                    text: '今日排片率',
                    subtext: new Date().toLocaleDateString(),
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x : 'center',
                    y : 'bottom',
                    data:nameList
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {
                            show: true,
                            type: ['pie', 'funnel']
                        },
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                series : [
                    {
                        name:'面积模式',
                        type:'pie',
                        radius : [30, 110],
                        center : ['50%', '50%'],
                        roseType : 'area',
                        data:tableData
                    }
                ]
            };
            var scheduleRateChart = echarts.init($("#schedule-rate-container")[0]);
            scheduleRateChart.setOption(option);
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function getBoxOffice() {

    getRequest(
        '/statistics/boxOffice/price',
        function (res) {
            var data = res.content || [];
            var tableData = data.map(function (item) {
                return item.boxOffice;
            });
            var nameList = data.map(function (item) {
                return item.name;
            });
            var option = {
                title : {
                    text: '所有电影票房',
                    subtext: '截止至'+new Date().toLocaleDateString(),
                    x:'center'
                },
                xAxis: {
                    type: 'category',
                    data: nameList
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: tableData,
                    type: 'bar'
                }]
            };
            var scheduleRateChart = echarts.init($("#box-office-container")[0]);
            scheduleRateChart.setOption(option);
        },
        function (error) {
            alert(JSON.stringify(error));
        });
}

function getAudiencePrice() {
    getRequest(
        '/statistics/audience/price',
        function (res) {
            var data = res.content || [];
            var tableData = data.map(function (item) {
                return item.price;
            });
            var nameList = data.map(function (item) {
                return formatDate(new Date(item.date));
            });
            var option = {
                title : {
                    text: '每日客单价',
                    x:'center'
                },
                xAxis: {
                    type: 'category',
                    data: nameList
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: tableData,
                    type: 'line'
                }]
            };
            var scheduleRateChart = echarts.init($("#audience-price-container")[0]);
            scheduleRateChart.setOption(option);
        },
        function (error) {
            alert(JSON.stringify(error));
        });
}

function getPlacingRate() {

    getRequest(
        '/statistics/PlacingRate',
        function (res) {
            var data = res.content || [];
            var tableData = data.map(function (item) {
                return item.placing;
            });
            var nameList = data.map(function (item) {
                return formatDate(new Date(item.date));
            });
            var option = {
                title : {
                    text: '每日上座率',
                    x:'center'
                },
                xAxis: {
                    type: 'category',
                    data: nameList
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: tableData,
                    type: 'line'
                }]
            };
            var scheduleRateChart = echarts.init($("#place-rate-container")[0]);
            scheduleRateChart.setOption(option);
        },
        function (error) {
            alert(JSON.stringify(error));
        });
}

function getPolularMovie() {

    getRequest(
        '/statistics/popular/movie?days=' + 30 + '&movieNum=' + 4,
        function (res) {
            var data = res.content || [];
            var tableData = data.map(function (item) {
                return item.boxOffice;
            });
            var nameList = data.map(function (item) {
                return item.name;
            });
            var option = {
                title : {
                    text: '最受欢迎电影',
                    subtext: '截止至'+new Date().toLocaleDateString(),
                    x:'center'
                },
                xAxis: {
                    type: 'category',
                    data: nameList
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: tableData,
                    type: 'bar'
                }]
            };
            var scheduleRateChart = echarts.init($("#popular-movie-container")[0]);
            scheduleRateChart.setOption(option);
        },
        function (error) {
            alert(JSON.stringify(error));
        });
}

function getPie() {
    var ctx = document.getElementById("myChart3").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [
                    5,
                    4,
                    3,
                    2,
                ],
                backgroundColor: [
                    '#63ed7a',
                    '#ffa426',
                    '#fc544b',
                    '#6777ef',
                ],
                label: 'Dataset 1'
            }],
            labels: [
                '黑衣人：全球追缉',
                '哆啦A梦：大雄的月球探险记',
                '大侦探皮卡丘',
                '千与千寻'
            ],
        },
        options: {
            responsive: true,
            legend: {
                position: 'bottom',
            },
        }
    });
}