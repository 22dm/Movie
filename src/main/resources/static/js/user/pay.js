let coupons = [];
let isVIP = false;
let useVIP = true;
let couponId;
let actualTotal;
let order;
let orderId;
let couponList;
let userId;

$(function () {
    orderId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    userId = sessionStorage.getItem('id');

    couponList = $("#coupon-list");
    couponList.selectric().on('change', changeCoupon);

    getOrder();
    getCard();
});

function getOrder() {
    getRequest(
        `/order/get?id=${orderId}`,
        function (res) {
            if (res.success) {
                order = res.content;
                renderOrder();
                getCoupons();
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        });
}

function renderOrder() {
    $('#order-id').html(order.id);
    $('#movie-name').html(order.schedule.movie.name);
    $('#hall-name').html(order.schedule.hall.name);
    $('#start-time').html(formatDateMDHM(order.schedule.startTime));
    $('#end-time').html(formatDateMDHM(order.schedule.endTime));
    $('#schedule-type').html(order.schedule.type);
    let ticketStr = "";
    for (let seat of order.seats) {
        ticketStr += `${seat.rowIndex + 1} 排 ${seat.columnIndex + 1} 座<br/>`;
    }
    $('#seats-list').html(ticketStr);
    $('#total-old').html(formatPrice(order.price));
}

function getCoupons() {
    getRequest(
        '/coupon/getAll',
        function (res) {
            coupons = res.content;
            renderCoupons();
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function renderCoupons() {
    let couponTicketStr = `<option>不使用优惠券</option>`;
    for (let coupon of coupons) {
        couponTicketStr += `<option>${coupon.name}：满${formatPrice(coupon.targetAmount)} 减 ${formatPrice(coupon.discountAmount)}</option>`;
    }
    couponList.html(couponTicketStr).selectric('refresh');
    changeCoupon(0);
}

function changeCoupon() {
    let couponIndex = couponList.prop('selectedIndex');
    if (couponIndex !== 0) {
        couponId = coupons[couponIndex - 1].id;
        actualTotal = order.price - parseFloat(coupons[couponIndex - 1].discountAmount);
    } else {
        couponId = null;
        actualTotal = order.price;
    }
    $('#total').html(formatPrice(actualTotal));
    $('#card-total').html(formatPrice(actualTotal));
    $('#bank-total').html(formatPrice(actualTotal));
}

function getCard() {
    getRequest(
        `/vip/get?userId=${userId}`,
        function (res) {
            isVIP = res.content.joinDate != null;
            renderCard(res.content);
        },
        function (error) {
            alert(error);
        });
}

function renderCard(card) {
    if (isVIP) {
        $('#have-card-tab').show();
        $('#bank-tab').removeClass("active");
        $('#card-tab').addClass("active");
        $('#bank-pay').removeClass("active show");
        $('#card-pay').addClass("active show");
        $('#have-card').show();
        $('#card-balance').text(formatPrice(card.balance));
    }
}

function payOrder() {
    $("#pay-modal").modal('show');
}

function getForm() {
    useVIP = isVIP && $('#card-tab').hasClass('active');
    return {
        orderId: order.id,
        couponId: couponId,
        payForm:
            {
                amount: actualTotal,
                cardNumber: $('#bankId-input'),
                password: $('#password-input'),
                mention: useVIP ? 1 : 0
            }
    }
}

function confirmPay() {
    let payForm = getForm();

    postRequest(
        '/order/pay',
        payForm,
        function (res) {
            $('#pay-modal').modal('hide');
            $('#info-modal').modal('show');
        },
        function (error) {
            alert(error);
        });
}

function confirmInfo() {
    window.location.href = "/user/order"
}