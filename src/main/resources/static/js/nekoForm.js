let form = $("#edit-form");
let isEdit = false;
let editId;
let items;

$(function () {
    $('body').append(`
        <div class="modal fade" tabindex="-1" role="dialog" id="info-modal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="info-modal-title"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="info-modal-body"></div>
                    <div class="modal-footer bg-whitesmoke br">
                        <button type="button" class="btn " data-dismiss="modal" id="info-modal-btn">确定</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" tabindex="-1" role="dialog" id="delete-modal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">删除${itemDecsribe}</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        确定要删除此${itemDecsribe}吗？此操作不可撤销。
                    </div>
                    <div class="modal-footer bg-whitesmoke br">
                        <button type="button" class="btn btn-danger" onclick="confirmDelete();">确定</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    `);

    getItemList();
});

function getItemList() {
    getRequest(
        getAllUrl,
        function (res) {
            items = res.content;
            renderItemList();
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function addItem() {
    clearForm();
    $("#form-header").html(`添加${itemDecsribe}`);
    $("#edit-modal").modal('show');
    isEdit = false;
}

function editItem(id) {
    clearForm();
    $("#form-header").html(`修改${itemDecsribe}`);
    isEdit = true;
    editId = id;
    items.forEach(function (item) {
        if(item.id === id){
            fillForm(item);
            $("#edit-modal").modal('show');
        }
    });
}

function confirmEdit() {
    if (form[0].checkValidity() === false) {
        form.addClass('was-validated');
        return;
    }

    let url;
    let requestForm = getForm();
    if(isEdit){
        url = editUrl;
        requestForm.id = editId;
    } else {
        url = addUrl;
    }

    postRequest(
        url,
        requestForm,
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
        });
}

function deleteItem(id) {
    $("#delete-modal").modal('show');
    editId = id;
}

function confirmDelete() {
    postRequest(
        deleteUrl + editId,
        null,
        function(res){
            $("#delete-modal").modal('hide');
            if(res.success) {
                showInfo("操作成功", res.message, false);
            } else {
                showInfo("操作失败", res.message, true);
            }
            getItemList();
        });
}

function showInfo(title, body, error = false, click) {
    $("#info-modal-title").html(title);
    $("#info-modal-body").html(body);
    let btn = $("#info-modal-btn");
    if(error) {
        btn.removeClass('btn-primary');
        btn.addClass('btn-danger');
    } else {
        btn.removeClass('btn-danger');
        btn.addClass('btn-primary');
    }
    btn.click(click);
    $("#info-modal").modal('show');
}