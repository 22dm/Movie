let itemDecsribe = "优惠券";
let addUrl = "/coupon/add";
let getAllUrl = "/coupon/getAll";

$(document).ready(function () {
    $("#nav-user-name").html(sessionStorage.getItem('username'));
    $("#sidebar-promotion").addClass("active");
    $("#sidebar-coupon").addClass("active");
});

function renderItemList() {
    let itemList = $("#item-list");
    itemList.empty();
    let itemListDom = "";
    items.forEach(function (coupon) {
        itemListDom += `<div class="col-12 col-sm-6 col-lg-4">
                            <div class="card card-success">
                                <div class="card-header">
                                    <h4>${coupon.name}</h4>
                                    <div class="card-header-action">${formatDateYMD(coupon.startTime)} - ${formatDateYMD(coupon.endTime)}</div>
                                </div>
                                <div class="card-body">${coupon.description}</div>
                                <div class="card-footer">满 ${formatPrice(coupon.targetAmount)} 减 ${formatPrice(coupon.discountAmount)}</div>
                            </div>
                        </div>`
    });
    itemList.append(itemListDom);
}

function getForm() {
    return {
        name: $("#name-input").val(),
        description: $("#description-input").val(),
        targetAmount: Math.round($("#target-amount-input").val() * 100),
        discountAmount: Math.round($("#discount-amount-input").val() * 100),
        startTime: $("#start-date-input").val(),
        endTime: $("#end-date-input").val()
    };
}

function clearForm() {
    $("#name-input").val("");
    $("#description-input").val("");
    $("#target-amount-input").val("");
    $("#discount-amount-input").val("");
    $("#start-date-input").val("");
    $("#end-date-input").val("");
    if(form.hasClass("was-validated")){
        form.removeClass("was-validated")
    }
}