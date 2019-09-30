$(function () {

    $.getJSON('/usernames', function (result) {
        $('#findUser').autocomplete({
            source: result
        });
    }).fail(function () {
        alert('Something went wrong');
    });

    $('#findUser').on('change', function () {
        var username = $('#findUser').val();
        $('.findUserBtn').attr('href', '/user-page/' + username)
    });

});