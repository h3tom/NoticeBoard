$(function () {
    var client = null;

    client = Stomp.client('ws://localhost:8080/chat');
    client.connect({}, function () {
        client.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body).message, JSON.parse(message.body).user)
        });
    });

    function showMessage(message, user) {
        var newResponse = document.createElement('p');
        newResponse.appendChild(document.createTextNode(user + ': '));
        newResponse.appendChild(document.createTextNode(message));
        var response = $('#response');
        response.append(newResponse);
    }

    $('#sendChatMessage').on('click', function () {
        var chatMessageToSend = document.getElementById('chatMessageToSend').value;
        client.send('/app/chat', {}, JSON.stringify({'message': chatMessageToSend, 'user': ''}));
        $('#chatMessageToSend').val('');
    });
});


