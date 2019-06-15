let itemDecsribe = "优惠策略";
let addUrl = "/vip/addPromotion";
let deleteUrl = "/vip/deletePromotion?target=";
let getAllUrl = "/vip/getPromotion";

$(document).ready(function() {
    $("#nav-user-name").html(sessionStorage.getItem('username'));
    $("#sidebar-rule").addClass("active");
    $("#sidebar-card").addClass("active");
});

function renderItemList(){
    let itemList = $("#item-list");
    itemList.empty();
    let itemListDom = "";
    items.forEach(function (promotion) {
        itemListDom += `<tr>
                            <td>${formatPrice(promotion.target)}</td>
                            <td>${formatPrice(promotion.gift)}</td>
                            <td><a href="#" class="btn btn-danger" onclick="deleteItem(${promotion.target});">删除</a></td>
                        </tr>`;
    });
    itemList.append(itemListDom);
}

function getForm() {
    return {
        target: Math.round($('#target-input').val() * 100),
        gift: Math.round($('#gift-input').val() * 100),
    };
}

function clearForm() {
    $("#target-input").val("");
    $("#gift-input").val("");
    if(form.hasClass("was-validated")){
        form.removeClass("was-validated")
    }
}