let itemDecsribe = "退款策略";
let addUrl = "/order/addRefund";
let deleteUrl = "/order/deleteRefund?hours=";
let getAllUrl = "/order/getRefund";

$(document).ready(function() {
    $("#nav-user-name").html(sessionStorage.getItem('username'));
    $("#sidebar-rule").addClass("active");
    $("#sidebar-refund").addClass("active");
});

function renderItemList(){
    let itemList = $("#item-list");
    itemList.empty();
    let itemListDom = "";
    items.forEach(function (refund) {
        itemListDom += `<tr>
                            <td>${refund.hours}</td>
                            <td>${refund.get}</td>
                            <td><a href="#" class="btn btn-danger" onclick="deleteItem(${refund.hours});">删除</a></td>
                        </tr>`;
    });
    itemList.append(itemListDom);
}

function getForm() {
    return {
        hours: $('#hours-input').val(),
        get: $('#get-input').val(),
    };
}

function clearForm() {
    $("#hours-input").val("");
    $("#get-input").val("");
    if(form.hasClass("was-validated")){
        form.removeClass("was-validated")
    }
}