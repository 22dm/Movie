let form = $("#edit-form");
let isEdit = false;
let editId;
let items;

let cal = $("#myEvent");
let hallList = $('#hall-list');

$(document).ready(function () {
    $("#nav-user-name").html(sessionStorage.getItem('username'));
    $("#sidebar-schedule").addClass("active");

    cal.fullCalendar({
        height: 'auto',
        header: {
            left: 'prev, next today',
            center: 'title',
            right: 'month, agendaWeek, listMonth'
        },
        allDaySlot: false,
        defaultView: 'agendaWeek',
        editable: false,
        eventClick: function(calEvent) {
            editItem(calEvent.id);
        }
    });

    getAllHalls();
    getAllMovies();

    hallList.selectric().on('change', getItemList);
});

function getAllHalls() {
    getRequest(
        '/hall/getAll',
        function (res) {
            let halls = res.content;
            halls.forEach(function (hall) {
                hallList.append(`<option value=${hall.id}>${hall.name}</option>`);
                $('#hall-input').append(`<option value=${hall.id}>${hall.name}</option>`);
            });
            hallList.selectric('refresh');
            $('#hall-input').selectric('refresh');
            getItemList();
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function getAllMovies() {
    getRequest(
        '/movie/getOnAndPre',
        function (res) {
            res.content.forEach(function (movie) {
                $('#movie-input').append(`<option value=${movie.id}>${movie.name}</option>`);
            });
            $('#movie-input').selectric('refresh');
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function getItemList() {
    getRequest(
        '/schedule/getByHallId?hallId=' + hallList.val(),
        function (res) {
            items = res.content;
            renderCalender();
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function renderCalender() {
    let events = [];
    items.forEach(function (schedule) {
        events.push({
            title: schedule.movie.name,
            start: schedule.startTime,
            end: schedule.endTime,
            id: schedule.id,
            backgroundColor: "#007bff",
            borderColor: "#007bff",
            textColor: '#fff'
        })
    });
    cal.fullCalendar('removeEvents');
    cal.fullCalendar('addEventSource', events);
    cal.fullCalendar('rerenderEvents');
}

function getForm() {
    return {
        hallId: $("#hall-input").val(),
        movieId: $("#movie-input").val(),
        startTime: $("#start-time-input").val(),
        type: $("#type-input").val(),
        fare: $("#price-input").val() * 100
    };
}

function fillForm(schedule) {
    $("#hall-input").prop('value', schedule.hall.id ? schedule.hall.id : 0).selectric('refresh');
    $("#movie-input").prop('value', schedule.movie.id ? schedule.movie.id : 0).selectric('refresh');
    $("#start-time-input").val(schedule.startTime);
    $("#type-input").val(schedule.type);
    $("#price-input").val(formatPrice(schedule.fare));
}

function clearForm() {
    $('#hall-input').prop('selectedIndex', 0).selectric('refresh');
    $('#movie-input').prop('selectedIndex', 0).selectric('refresh');
    $("#start-date-input").val("");
    $("#type-input").val("");
    $("#price-input").val("");
    if(form.hasClass("was-validated")){
        form.removeClass("was-validated")
    }
}

function addItem() {
    clearForm();
    $("#form-header").html('新增排片');
    $("#delete-btn").hide();
    $("#edit-modal").modal('show');
    isEdit = false;
}

function editItem(id) {
    clearForm();
    $("#form-header").html('修改排片');
    $("#delete-btn").show();
    isEdit = true;
    editId = id;
    getRequest(
        '/schedule/get?id='+ id,
        function(res){
            console.log(res);
            fillForm(res.content);
            $('#edit-modal').modal('show');
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
    let scheduleForm = getForm();
    if(isEdit){
        url = '/schedule/edit';
        scheduleForm.id = editId;
    } else {
        url = '/schedule/add';
    }

    postRequest(
        url,
        scheduleForm,
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

function deleteItem() {
    $("#edit-modal").modal('hide');
    $("#delete-modal").modal('show');
}

function confirmDelete() {
    postRequest(
        `/schedule/delete?id=${editId}`,
        null,
        function(res){
            $("#delete-modal").modal('hide');
            $("#info-modal").modal('show');
            getItemList(hallList.val());
            clearForm();
        },
        function (error) {
            alert(error);
        });
}

