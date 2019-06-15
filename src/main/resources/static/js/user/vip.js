let userId;
let cardId;

$(document).ready(function () {
    userId = sessionStorage.getItem('id');

    getCard();
});

function getCard() {
    getRequest(
        '/vip/get?userId=' + userId,
        function (res) {
            if (res.content.joinDate != null) {
                $('#no-card').hide();
                $('#have-card').show();
                renderCharge(res.content.charges);
                $('#hist-title').show();
                $('#hist-text').show();
                $('#hist-table').show();
                cardId = res.content.id;
                $('#card-balance').text(formatPrice(res.content.balance));
            }
            renderPromotion(res.content.promotions);
        },
        function (error) {
            alert(error);
        });
}

function renderCharge(charges) {
    let itemList = $('#item-list');
    itemList.empty();
    let itemListDom = '';
    charges.forEach(function (charge) {
        itemListDom += `<tr>
                            <td>${formatDate(charge.time)}</td>
                            <td>${formatPrice(charge.charge)}</td>
                            <td>${formatPrice(charge.gift)}</td>
                            <td>银行卡</td>
                        </tr>`;
    });
    itemList.append(itemListDom);
}

function renderPromotion(promotions) {
    let promotionList = $("#promotion-list");
    let chargeAmountInput = $('#charge-amount-input');
    promotionList.empty();
    chargeAmountInput.empty();
    let promotionDom = "";
    let chargeAmountDom = '';
    promotions.forEach(function (promotion) {
        promotionDom += `<li>冲 ${formatPrice(promotion.target)} 送 ${formatPrice(promotion.gift)}</li>`;
        chargeAmountDom += `<label class="selectgroup-item">
                                <input type="radio" name="value" value="${promotion.target}" class="selectgroup-input">
                                <span class="selectgroup-button">${formatPrice(promotion.target)}</span>
                            </label>`;
    });
    promotionList.append(promotionDom);
    chargeAmountInput.append(chargeAmountDom);
}

function buy() {
    clearForm();
    $('#buy-modal').modal('show');
}

function charge() {
    clearForm();
    $('#charge-modal').modal('show');
}

function clearForm() {
    $('#buy-card-input').val('');
    $('#buy-password-input').val('');
    $('#charge-amount-input').val('');
    $('#charge-card-input').val('');
    $('#charge-password-input').val('');
}

function getBuyForm() {
    return {userId: userId,
        payForm: {
            cardNumber: $('#buy-card-input').val(),
            password:$('#buy-password-input').val(),
            mention: 0,
            amount: 25
        }
    };
}

function getChargeForm() {
    let amount;
    let amountList = $('#charge-amount-input label input');
    [...amountList].forEach(function (radio) {
        if(radio.checked){
            amount = radio.value;
        }
    });
    return {cardId: cardId,
        payForm: {
            cardNumber: $('#charge-card-input').val(),
            password:$('#charge-password-input').val(),
            mention: 0,
            amount: amount,
        }
    };
}

function confirmBuy(){
    postRequest(
        "/vip/buy",
        getBuyForm(),
        function (res) {
            $('#buy-modal').modal('hide');
            $('#info-modal').modal('show');
            getCard();
        },
        function (error) {
            alert(error);
        });
}

function confirmCharge() {
    postRequest(
        '/vip/charge',
        getChargeForm(),
        function (res) {
            $('#charge-modal').modal('hide');
            $('#info-modal').modal('show');
            getCard();
        },
        function (error) {
            alert(error);
        });
}

function formatDate(date) {
    return date.substring(5, 10).replace("-", ".");
}