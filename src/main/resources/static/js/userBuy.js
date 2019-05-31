$(document).ready(function () {
    getMovieList();

    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderTicketList(tickets) {

        $('.ticket-list').empty();
        ticketList = '';
        tickets.forEach(function (ticket) {
            if(ticket.state === '已完成' || ticket.state === '已失效')
            ticketList +=
                "<tr>" +
                "<td>" +
                ticket.schedule.movieName +
                "</td>" +
                "<td>" +
                ticket.schedule.hallName +
                "</td>" +
                "<td>" +
                (ticket.rowIndex + 1) + "排" + (ticket.columnIndex + 1) + "座" +
                "</td>" +
                "<td>" +
                formatDate(ticket.schedule.startTime) +
                "</td>" +
                "<td>" +
                formatDate(ticket.schedule.endTime) +
                "</td>" +
                "<td>" +
                ticket.state +
                "</td>" +
                "</tr>";
        });
        $('.ticket-list').append(ticketList);
    }

    function formatDate(date) {
        return date.substring(0, 19).replace("T", " ");
    }
});