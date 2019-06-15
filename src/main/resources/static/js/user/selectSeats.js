let selectedSeats = [];
let order = {ticketId: [], couponId: 0};
let price;
let schedule;
let seats;

$(document).ready(function () {
    scheduleId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    userId = sessionStorage.getItem('id');

    getScheduleAndSeats();
});

function creatOrder() {
    let seats = [];
    for (let seat of selectedSeats) {
        seats.push({
            rowIndex: seat[0],
            columnIndex: seat[1]
        })
    }

    postRequest(
        '/order/creat',
        {
            userId: sessionStorage.getItem('id'),
            scheduleId: scheduleId,
            seats: seats,
            price: price
        },
        function (res) {
            window.location.href="/user/pay?orderId=" + res.content;
        },
        function (error) {
            alert(error);
        });
}

function getScheduleAndSeats() {
    getRequest(
        '/order/getSeatsStatus?id=' + scheduleId,
        function (res) {
            if (res.success) {
                schedule = res.content.schedule;
                seats = res.content.seats;
                renderSchedule();
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function renderSchedule() {
    $('#movie-name').html(schedule.movie.name);
    $('#movie-poster').attr("src", schedule.movie.posterUrl);
    $('#movie-length').html(schedule.movie.length);
    $('#movie-type').html(schedule.movie.type);
    $('#hall-name').html(schedule.hall.name);
    $('#schedule-fare').html(formatPrice(schedule.fare));
    $('#schedule-type').html(schedule.type);
    $('#schedule-time').html(formatDateMDHM(schedule.startTime));

    let seatsListDom = "";
    for(let i = 0; i < seats.length; i++){
        let temp = "";
        for(let j = 0; j < seats[0].length; j++){
            let icon;
            let opacity;
            let click = "";
            if(seats[i][j] === -1){
                opacity = "opacity: 0";
            } else if(seats[i][j] === 1){
                icon = "text-warning";
            } else {
                icon = "text-secondary";
                click = `onclick="seatClick(${i}, ${j});"`;
            }
            temp += `<div class = "fas fa-couch mb-3 mr-1 ${icon}" style="font-size: 30px;${opacity}" id="seat-${i}-${j}" ${click}></div>`;
        }
        seatsListDom += "<div>" + temp + "</div>";
    }

    $('#seats-list').append(seatsListDom);
}

function seatClick(i, j) {
    let seat = $("#seat-" + i + "-" + j);
    if (seat.hasClass("text-secondary")) {
        seat.removeClass("text-secondary");
        seat.addClass("text-primary");

        selectedSeats[selectedSeats.length] = [i, j]
    } else {
        seat.removeClass("text-primary");
        seat.addClass("text-secondary");

        selectedSeats = selectedSeats.filter(function (value) {
            return value[0] !== i || value[1] !== j;
        })
    }

    selectedSeats.sort(function (x, y) {
        let res = x[0] - y[0];
        return res === 0 ? x[1] - y[1] : res;
    });

    let seatDetailStr = "";
    if (selectedSeats.length === 0) {
        $('#no-ticket').show();
        $('#has-ticket').hide();
        $('#confirm-btn').addClass("disabled");
    } else {
        for (let seatLoc of selectedSeats) {
            seatDetailStr += `<button class="btn btn-outline-primary" onclick="seatClick(${seatLoc[0]}, ${seatLoc[1]});">${seatLoc[0] + 1} 排 ${seatLoc[1] + 1} 座 </button>`;
        }
        $('#no-ticket').hide();
        $('#has-ticket').show();
        $('#confirm-btn').removeClass("disabled");
    }
    $('#ticket-list').html(seatDetailStr);

    price = schedule.fare * selectedSeats.length;
    $('#total').html(formatPrice(price));
}