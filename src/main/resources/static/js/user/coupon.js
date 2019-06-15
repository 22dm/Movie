let userId;

$(function () {
    userId = sessionStorage.getItem('id');

    getItemList();
});

function getItemList() {
    getRequest(
        `/coupon/getByUserId?userId=${userId}`,
        function (res) {
            renderItemList(res.content);
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function renderItemList(items) {
    let itemList = $('#item-list');
    itemList.empty();
    let itemListDom = '';
    items.forEach(function (coupon) {
        itemListDom += `<div class="col-12 col-sm-6 col-lg-6">
                            <div class="card card-success">
                                <div class="card-header">
                                    <h4>${coupon.name}</h4>
                                    <div class="card-header-action">${formatDateYMD(new Date(coupon.startTime))} - ${formatDateYMD(new Date(coupon.endTime))}</div>
                                </div>
                                <div class="card-body">${coupon.description}</div>
                                <div class="card-footer">满 ${formatPrice(coupon.targetAmount)} 减 ${formatPrice(coupon.discountAmount)}</div>
                            </div>
                        </div>`
    });
    itemList.append(itemListDom);
}