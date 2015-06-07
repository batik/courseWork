/**************************************************/
/*   jQuery login validation
/*************************************************/
function validateJQLogin() {
    hideErrorMessageLogin();
    $('form[name="login"] input').not('input[type=submit], input[type=hidden]').each(function () {
        var element = $(this);
        switch (element.attr('type')) {
        case 'text':
            validateFieldNotEmptyLogin(element);
            break;
        case 'password':
            validateFieldNotEmptyLogin(element);
            break;
        }
    });
    return checkErrorsLogin();
}

function validateFieldNotEmptyLogin(element) {
    if (element.val() === "") {
        showErrorMessageLogin(element);
    }
}

function hideErrorMessageLogin() {
    $(".error-message-visible").attr('class', 'error-message-hidden');
}

function checkErrorsLogin() {
    if ($(".error-message-visible").length > 0) {
        return false;
    }
    return true;
}

function showErrorMessageLogin(element) {
    var errorField = element.attr('name') + "_span";
    if ($('span[name="' + errorField + '"]').hasClass("error-message-hidden")) {
        $('span[name="' + errorField + '"]').attr('class', 'error-message-visible');
    }
}
