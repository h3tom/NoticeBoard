$(function () {

    $('#inputComment').on('input', function () {
        var current = $(this).val().length;
        if (current > 60) {
            $(this).val($(this).val().substr(0, 60));
        }
        $('.counter').text(current);
    });
});