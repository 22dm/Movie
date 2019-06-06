let userId;
let refundId;

$(document).ready(function () {
    userId = sessionStorage.getItem('id');

    getOrderList();
});

function getOrderList(){
    getRequest(
        '/order/getByUserId?userId=' + userId,
        function (res) {
            renderTicketList(res.content);
        },
        function (error) {
            alert(error);
        });
}


function renderTicketList(orders) {
    let orderLock = $("#order-lock");
    let orderPay = $("#order-pay");
    let orderUsed = $("#order-used");
    
    orders.forEach(function (order) {
        let seatsDom = '';
        order.seats.forEach(function (seat) {
            seatsDom += `<li>${seat.columnIndex + 1} 排 ${seat.rowIndex + 1} 座</li>`
        });
        let orderDom = `</div>
                    </div>
                    <div class="card-body">
                        <div class="media">
                            <img class="mr-4" height="160px" src="${order.schedule.movie.posterUrl}" alt="">
                            <div class="media-body">
                                <h5>${order.schedule.movie.name}</h5>
                                <ul>
                                    <li class="mb-0">${formatDate(order.schedule.startTime)} 场</li>
                                    <li class="mb-0">${order.schedule.hall.name}：
                                        <ul>${seatsDom}</ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>`;

        if (order.status === 0) {
            orderDom = `<div class="col-12 col-md-12 col-lg-12">
                            <div class="card card-warning">
                                <div class="card-header">
                                    <div class="badge badge-warning mr-3">待支付</div>
                                    <h4>${formatDate(order.time)}</h4>
                                    <h4>订单号：${order.id}</h4>
                                    <div class="card-header-action"><span class="mr-3">￥${formatPrice(order.price)}</span>
                                    <a href="/user/pay?orderId=${order.id}" class="btn btn-icon icon-left btn-primary"><i class="far fa-edit"></i>去支付</a>
                            ${orderDom}`;
            orderLock.append(orderDom);
        } else if (order.status === 1) {
            orderDom = `<div class="col-12 col-md-12 col-lg-12">
                            <div class="card card-success">
                                <div class="card-header">
                                    <div class="badge badge-success mr-3">已支付</div>
                                    <h4>${formatDate(order.time)}</h4>
                                    <h4>订单号：${order.id}</h4>
                                    <div class="card-header-action"><span class="mr-3">￥${formatPrice(order.price)}</span>
                                    <a href="#" class="btn btn-icon icon-left btn-primary" onclick="refund(${order.id});"><i class="far fa-edit"></i>退票</a>
                            ${orderDom}`;
            orderPay.append(orderDom);
        } else if (order.status === 2) {
            orderDom = `<div class="col-12 col-md-12 col-lg-12">
                            <div class="card card-dark">
                                <div class="card-header">
                                    <div class="badge badge-warning mr-3">已失效</div>
                                    <h4>${formatDate(order.time)}</h4>
                                    <h4>订单号：${order.id}</h4>
                                    <div class="card-header-action"><span class="mr-3">￥${formatPrice(order.price)}</span>
                            ${orderDom}`;
            orderUsed.append(orderDom);
        }
    });
}

function refund(id) {
    refundId = id;
    getRequest(
        '/order/refundInfo?orderId=' + id,
        function (res) {
            $('#refund-amount').html(formatPrice(res.content));
            $('#delete-modal').modal('show');
        },
        function (error) {
            alert(error);
        });
}

function confirmRefund() {
    postRequest(
        '/order/refund?orderId=' + refundId,
        null,
        function (res) {
            $('#delete-modal').modal('hide');
            $('#info-modal').modal('show');
            getOrderList();
        },
        function (error) {
            alert(error);
        });
}