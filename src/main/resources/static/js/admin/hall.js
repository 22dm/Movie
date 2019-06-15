let itemDecsribe = "影厅";
let addUrl = "/hall/add";
let editUrl = "/hall/edit";
let deleteUrl = "/hall/delete?id=";
let getAllUrl = "/hall/getAll";

let seatsList = $("#seats-list");
let seatsStatus;

$(document).ready(function() {
    $("#nav-user-name").html(sessionStorage.getItem('username'));
    $("#sidebar-hall").addClass("active");

    $("#row-input").change(renderEmptySeats);
    $("#column-input").change(renderEmptySeats);
});

function renderItemList(){
    let itemList = $("#item-list");
    itemList.empty();
    let itemListDom = "";
    items.forEach(function (hall) {
        itemListDom += `<tr>
                            <td>${hall.name}</td>
                            <td>${hall.row}</td>
                            <td>${hall.column}</td>
                            <td>
                                <a href="#" class="btn btn-primary mr-3" onclick="editItem(${hall.id});">修改</a>
                                <a href="#" class="btn btn-danger" onclick="deleteItem(${hall.id});">删除</a>
                            </td>
                        </tr>`;
    });
    itemList.append(itemListDom);
}

function getForm() {
    return {
        name: $('#name-input').val(),
        row: $('#row-input').val(),
        column: $('#column-input').val(),
        seats: {
            status: seatsStatus
        }
    };
}

function fillForm(hall) {
    $("#name-input").val(hall.name);
    $("#row-input").val(hall.row);
    $("#column-input").val(hall.column);
    seatsStatus = hall.seats;
    renderSeats();
}

function clearForm() {
    $("#name-input").val("");
    $("#row-input").val("");
    $("#column-input").val("");
    seatsList.empty();
    if(form.hasClass("was-validated")){
        form.removeClass("was-validated")
    }
}

function renderEmptySeats() {
    seatsList.empty();
    seatsStatus = [];
    let row = $("#row-input").val();
    let column = $("#column-input").val();
    for(let i = 0; i < row; i++){
        seatsStatus.push([]);
        for(let j = 0; j < column; j++){
            seatsStatus[i].push(0);
        }
    }
    renderSeats();
}

function renderSeats() {
    if(seatsStatus.length === 0 || seatsStatus[0].length === 0)
        return;
    let seatsListDom = "";
    for(let i = 0; i < seatsStatus.length; i++){
        let temp = "";
        for(let j = 0; j < seatsStatus[0].length; j++){
            let icon;
            if(seatsStatus[i][j] === -1){
                icon = "text-warning";
            }else {
                icon = "text-secondary";
            }
            temp += `<div class = "fas fa-couch mb-2 mr-1 ${icon}" style="font-size: 30px" id="seat-${i}-${j}" onclick="changeSeatStatus(${i}, ${j});"></div>`;
        }
        seatsListDom += `<div>${temp}</div>`;
    }
    seatsList.append(seatsListDom);
}

function changeSeatStatus(row, column) {
    let seat = $(`#seat-${row}-${column}`);
    if(seatsStatus[row][column] === 0){
        seat.removeClass("text-secondary");
        seat.addClass("text-warning");
        seatsStatus[row][column] = -1;
    } else {
        seat.removeClass("text-warning");
        seat.addClass("text-secondary");
        seatsStatus[row][column] = 0;
    }
}