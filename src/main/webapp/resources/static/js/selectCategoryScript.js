$(function () {

    $('.categoriesOptions').on('change', function () {
        $('.goToCategory').attr('href', "/home/" + $(this).val());
    });
});