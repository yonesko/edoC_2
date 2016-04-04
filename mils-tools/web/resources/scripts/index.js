/**
 * Created by gleb on 11.03.16.
 */
function clearFields() {
    document.getElementById('SQLtext').value='';
}

function sendonkeypress() {
    if (event.keyCode == 13) {
        document.getElementById('send').click();
        document.getElementById('chatMsgContent').focus();
        setTimeout(scrollchatDiv, 100);
        return false;
    }
}
function scrollchatDiv() {
    var objDiv = document.getElementById("chatDiv");
    objDiv.scrollTop = objDiv.scrollHeight;
    console.log('scrollchatDiv');
}