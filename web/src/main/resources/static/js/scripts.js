function sendForContact() {
    let url = window.location.href;
    arr = url.split("/");
    result = arr[0] + "//" + arr[2]
    request = new XMLHttpRequest();
    url = result + '/contact';
    const name = document.getElementById("input-name").value;
    if (name === null || name === "") {
        window.alert("Name field should be filled");
        return false;
    }
    const number = document.getElementById("input-number").value;
    if (number === null || number === "") {
        window.alert("Number field should be filled");
        return false;
    }
    const email = document.getElementById("input-email").value;
    if (email === null || email === "") {
        window.alert("Email field should be filled");
        return false;
    }
    const message = document.getElementById("input-message").value;
    if (message === null || message === "") {
        window.alert("Email field should be filled");
        return false;
    }
    const json = JSON.stringify({
        name: name,
        number: number,
        email: email,
        message: message
    });
    request.open('POST', url, false);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.send(json);
    let responseJson;
    if (request.status === 201) {
        window.alert("Your request has been sent");
        window.location.reload();
    } else if (request.status === 400) {
        responseJson = JSON.parse(request.responseText);
        window.alert("Error(s) during sending: " + responseJson);
    }
    $('#message').modal('show');
    return false;
}