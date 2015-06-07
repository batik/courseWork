/**************************************************/
/*   jQuery validation
/*************************************************/
function validateJQForm() {
    var isRadioChecked = false;
    var lastRadio;
    hideErrorMessage();
    $('form[name="register"] input').not(' :input[type=submit] ').each(function () {
        var element = $(this);
        switch (element.attr('type')) {
        case 'radio':
            lastRadio = element;
            if ($("form input:radio").is(':checked')) {
                isRadioChecked = true;
            }
            break;
        case 'text':
            if (element.attr('name') == "email") {
                validateEmail(element);
            } else {
                validateFieldNotEmpty(element);
            }
            break;
        case 'password':
            validateFieldNotEmpty(element);
            break;
        }
    });
    if (!isRadioChecked) {
        showErrorMessage(lastRadio);
    }
    return checkErrors();
}


function validateEmail(email) {
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if (!emailReg.test(email.val()) || email.val() === "") {
        showErrorMessage(email);
    }
}

function validateFieldNotEmpty(element) {
    if (element.val() === "") {
        showErrorMessage(element);
    }
}

function hideErrorMessage() {
    $(".error-message-visible").attr('class', 'error-message-hidden');
}

function checkErrors() {
    if ($(".error-message-visible").length > 0) {
        return false;
    }
    return true;
}

function showErrorMessage(element) {
    var errorField = element.attr('name') + "_span";
    if ($('span[name="' + errorField + '"]').hasClass("error-message-hidden")) {
        $('span[name="' + errorField + '"]').attr('class', 'error-message-visible');
    }
}
