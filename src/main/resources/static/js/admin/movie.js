let form = $("#edit-form");
let isEdit = false;
let editId;

$(document).ready(function(){
    $("#nav-user-name").html(sessionStorage.getItem('username'));
    $("#sidebar-movie").addClass("active");

    getItemList();
});

function getItemList(){
    let itemsPre, itemsOn, itemsOff;
    getRequest(
        '/movie/getPre',
        function (res) {
            itemsPre = res.content;
            getRequest(
                '/movie/getOn',
                function (res) {
                    itemsOn = res.content;
                    getRequest(
                        '/movie/getOff',
                        function (res) {
                            itemsOff = res.content;
                            renderItemList(itemsPre, itemsOn, itemsOff);
                        },
                        function (error) {
                            alert(error);
                        }
                    );
                },
                function (error) {
                    alert(error);
                }
            );
        },
        function (error) {
            alert(error);
        }
    );


}

function renderItemList(itemsPre, itemsOn, itemsOff) {
    let itemList = $('#item-pre');
    itemList.empty();
    let itemDomStr = '';
    itemsPre.forEach(function (movie) {
        itemDomStr += "<div class=\"col-12 col-md-6 col-lg-6\">" +
            "<div class=\"card card-info\">" +
            "<div class=\"card-header\">" +
            "<h4>" + formatDateYMD(movie.startDate) + " 上映</h4>" +
            "<div class=\"card-header-action\">" +
            "<a href=\"#\" class=\"btn btn-icon icon-left btn-primary\" onclick=\"editItem(" + movie.id + ");\" style=\"margin-right: 10px;\"><i class=\"far fa-edit\"></i>修改电影</a>" +
            "<a href=\"#\" class=\"btn btn-icon icon-left btn-danger\" onclick=\"deleteItem(" + movie.id + ");\"><i class=\"fas fa-times\"></i>下架电影</a>" +
            "</div>" +
            "</div>" +
            "<div class=\"card-body\">" +
            "<div class=\"media\">" +
            "<img class=\"mr-3\" height=\"100px\" src=\"" + (movie.posterUrl || "../images/defaultAvatar.jpg") + "\" alt=\"Generic placeholder image\">" +
            "<div class=\"media-body\">" +
            "<h5 class=\"mt-0\">" + movie.name + "</h5>" +
            "<p class=\"mb-0\">" + movie.type + "</p>" +
            "<p class=\"mb-0\">" + movie.length + " 分钟</p>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>";
    });
    itemList.append(itemDomStr);

    itemList = $('#item-on');
    itemList.empty();
    itemDomStr = '';
    itemsOn.forEach(function (movie) {
        itemDomStr += "<div class=\"col-12 col-md-6 col-lg-6\">" +
            "<div class=\"card card-success\">" +
            "<div class=\"card-header\">" +
            "<h4>" + formatDateYMD(movie.startDate) + " 上映</h4>" +
            "<div class=\"card-header-action\">" +
            "<a href=\"#\" class=\"btn btn-icon icon-left btn-primary\" onclick=\"editItem(" + movie.id + ");\" style=\"margin-right: 10px;\"><i class=\"far fa-edit\"></i>修改电影</a>" +
            "<a href=\"#\" class=\"btn btn-icon icon-left btn-danger\" onclick=\"deleteItem(" + movie.id + ");\"><i class=\"fas fa-times\"></i>下架电影</a>" +
            "</div>" +
            "</div>" +
            "<div class=\"card-body\">" +
            "<div class=\"media\">" +
            "<img class=\"mr-3\" height=\"100px\" src=\"" + (movie.posterUrl || "../images/defaultAvatar.jpg") + "\" alt=\"Generic placeholder image\">" +
            "<div class=\"media-body\">" +
            "<h5 class=\"mt-0\">" + movie.name + "</h5>" +
            "<p class=\"mb-0\">" + movie.type + "</p>" +
            "<p class=\"mb-0\">" + movie.length + " 分钟</p>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>";
    });
    itemList.append(itemDomStr);

    itemList = $('#item-off');
    itemList.empty();
    itemDomStr = '';
    itemsOff.forEach(function (movie) {
        itemDomStr += "<div class=\"col-12 col-md-6 col-lg-6\">" +
            "<div class=\"card card-dark\">" +
            "<div class=\"card-header\">" +
            "<h4>" + formatDateYMD(movie.startDate) + " 下架</h4>" +
            "<div class=\"card-header-action\">" +
            "</div>" +
            "</div>" +
            "<div class=\"card-body\">" +
            "<div class=\"media\">" +
            "<img class=\"mr-3\" height=\"100px\" src=\"" + (movie.posterUrl || "../images/defaultAvatar.jpg") + "\" alt=\"Generic placeholder image\">" +
            "<div class=\"media-body\">" +
            "<h5 class=\"mt-0\">" + movie.name + "</h5>" +
            "<p class=\"mb-0\">" + movie.type + "</p>" +
            "<p class=\"mb-0\">" + movie.length + " 分钟</p>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>";
    });
    itemList.append(itemDomStr);
}

function getForm() {
    return {
        name: $('#name-input').val(),
        startDate: $('#date-input').val(),
        posterUrl: $('#img-input').val(),
        description: $('#description-input').val(),
        type: $('#type-input').val(),
        length: $('#length-input').val(),
        country: $('#country-input').val(),
        actor: $('#director-input').val(),
        language: $('#language-input').val()
    };
}

function fillForm(movie) {
    $("#name-input").val(movie.name);
    $("#date-input").val(movie.startDate);
    $("#img-input").val(movie.posterUrl);
    $("#description-input").val(movie.description);
    $("#type-input").val(movie.type);
    $("#length-input").val(movie.length);
    $("#country-input").val(movie.country);
    $("#language-input").val(movie.language);
    $("#actor-input").val(movie.director);
}

function clearForm() {
    $("#name-input").val("");
    $("#date-input").val("");
    $("#img-input").val("");
    $("#description-input").val("");
    $("#type-input").val("");
    $("#length-input").val("");
    $("#country-input").val("");
    $("#language-input").val("");
    $("#actor-input").val("");
    if(form.hasClass("was-validated")){
        form.removeClass("was-validated")
    }
}

function addItem() {
    clearForm();
    $("#form-header").html("上架电影");
    $("#edit-modal").modal('show');
    isEdit = false;
}

function editItem(id) {
    clearForm();
    $("#form-header").html("修改电影");
    isEdit = true;
    editId = id;
    getRequest(
        '/movie/get?id=' + id,
        function(res){
            fillForm(res.content);
            $("#edit-modal").modal('show');
        },
        function (error) {
            alert(error);
        }
    );
}

function confirmEdit() {
    if (form[0].checkValidity() === false) {
        form.addClass('was-validated');
        return;
    }

    let url;
    let movieForm = getForm();
    if(isEdit){
        url = '/movie/edit';
        movieForm.id = editId;
    } else {
        url = '/movie/add';
    }

    postRequest(
        url,
        movieForm,
        function (res) {
            $("#editMovieModal").modal('hide');
            $("#movieSuccessModal").modal('show');
            getItemList();
            clearForm();
        },
        function (error) {
            alert(error);
        });
}

function deleteItem(id){
    $("#delete-modal").modal('show');
    editId = id;
}

function confirmDelete() {
    postRequest(
        '/movie/delete?id=' + editId,
        null,
        function(res){
            $("#delete-modal").modal('hide');
            $("#info-modal").modal('show');
            getItemList();
        },
        function (error) {
            alert(error);
        });
}

