let itemDecsribe = "员工账户";
let addUrl = "/manager/add";
let editUrl = "/manager/edit";
let deleteUrl = "/manager/delete?userId=";
let getAllUrl = "/manager/get";

$(document).ready(function() {
    $("#nav-user-name").html(sessionStorage.getItem('username'));
    $("#sidebar-manager").addClass("active");
});

function renderItemList(){
    let itemList = $("#item-list");
    itemList.empty();
    let itemListDom = "";
    items.forEach(function (manager) {
        itemListDom += `<tr>
                            <td>${manager.username}</td>
                            <td>
                                <a href="#" class="btn btn-primary mr-3" onclick="editItem(${manager.id});">修改</a>
                                <a href="#" class="btn btn-danger" onclick="deleteItem(${manager.id});">删除</a>
                            </td>
                        </tr>`;
    });
    itemList.append(itemListDom);
}

function getForm() {
    return {
        username: $('#username-input').val(),
        password: $('#password-input').val(),
        role: 1,
    };
}

function fillForm(user) {
    $('#username-input').val(user.username);
}

function clearForm() {
    $("#username-input").val("");
    $("#password-input").val("");
    if(form.hasClass("was-validated")){
        form.removeClass("was-validated")
    }
}