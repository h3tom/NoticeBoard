$(function () {

    $('.getRandomJoke').on('click', function () {
        $.getJSON('https://icanhazdadjoke.com/', function (result) {
            alert(result.joke);
        });
    });

});