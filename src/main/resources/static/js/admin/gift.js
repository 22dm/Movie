let form = $("#edit-form");

$(document).ready(function () {
    $("#nav-user-name").html(sessionStorage.getItem('username'));
    $("#sidebar-promotion").addClass("active");
    $("#sidebar-gift").addClass("active");

    getCouponList();

    $("#top-input").change(function () {
        getItemList(Math.round($("#top-input").val() * 100));
    });
});

function getItemList(cost) {
    getRequest(
        '/statistics/topUser?cost=' + cost,
        function (res) {
            renderItemList(res.content);
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function renderItemList(items) {
    let itemList = $("#item-list");
    itemList.empty();
    let itemListDom = "";
    items.forEach(function (user) {
        itemListDom += `<tr>
                            <td>
                                <div class="custom-checkbox custom-control">
                                    <input type="checkbox" data-checkboxes="top-users" class="custom-control-input" id="checkbox-${user.id}">
                                    <label for="checkbox-${user.id}" class="custom-control-label">&nbsp;</label>
                                </div>
                            </td>
                            <td><img alt="image" src="/assets/img/avatar/avatar-1.png" class="rounded-circle mr-3" width="35">${user.username}</td>
                            <td>${formatPrice(user.cost)}</td>
                        </tr>`;
    });
    itemList.append(itemListDom);
}

function getCouponList() {
    getRequest(
        '/coupon/getAll',
        function (res) {
            res.content.forEach(function (coupon) {
                $("#coupon-input").append("<option value=" + coupon.id + ">" + coupon.name + "（满 " + formatPrice(coupon.targetAmount)  + " 减 " + formatPrice(coupon.discountAmount) + "）</option>");
            });
            $('#coupon-input').select2({minimumResultsForSearch: Infinity});
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function getForm() {
    return {
        name: $("#name-input").val(),
        description: $("#description-input").val(),
        targetAmount: $("#target-amount-input").val() * 100,
        discountAmount: $("#discount-amount-input").val() * 100,
        startTime: $("#start-date-input").val(),
        endTime: $("#end-date-input").val()
    };
}

function clearForm() {
    $("#name-input").val();
    $("#description-input").val();
    $("#target-amount-input").val();
    $("#discount-amount-input").val();
    $("#start-date-input").val();
    $("#end-date-input").val();
    if(form.hasClass("was-validated")){
        form.removeClass("was-validated")
    }
}

function addItem() {
    clearForm();
    $("#edit-modal").modal('show');
}

function confirmEdit() {
    if (form[0].checkValidity() === false) {
        form.addClass('was-validated');
        return;
    }

    postRequest(
        '/coupon/add',
        getForm(),
        function (res) {
            if(res.success) {
                $("#edit-modal").modal('hide');
                showInfo("操作成功", res.message, false);
                getItemList();
            } else {
                $("#edit-modal").modal('hide');
                showInfo("操作失败", res.message, true, function () {
                    $("#edit-modal").modal('show');
                });
            }
        },
        function (error) {
            alert(error);
        });
}