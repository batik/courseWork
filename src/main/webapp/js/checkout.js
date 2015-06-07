function removeLine(id) {
    $.ajax({
        type: "POST",
        url: window.pageContext + "/cart/delete?productId=" + id,
        async: true,
        success: function (data) {
            $('#line' + id).remove();
            $("#total").html(data.total);
            if (data.total <= 0) {
                $('#nextButt').remove()
            }
        }
    });
}


$('input[type=number]').keypress(function(event){
    event.preventDefault ? event.preventDefault() : event.returnValue = false;
});

function changeQuantity(id) {
		    var productCount = $('#count' + id).val();
		    $.ajax({
		        type: "POST",
		        url: window.pageContext + "/cart/change?productId="+id+"&productCount="+productCount,
		        async: true,
		        success: function (data) {
		            $("#count" + id).html(data.count);
		            $("#price" + id).html(data.price);
		            $("#totalLine" + id).html(data.totalLine);
		            $("#total").html(data.total);
		        }
		    });
		}

$(function () {
    var nextInfoButton = $("#nextInfoButton");
    var address = $("#address");
    var cardNumber = $("#cardNumber");


    function isEmpty(input) {
        return input == null || input.trim().length == 0;
    }

    function validate() {
        if (!isEmpty($("#address").val()) && !isEmpty($("#cardNumber").val())) {
            nextInfoButton.removeClass('Disabled').addClass('submitbtn');
            nextInfoButton.prop('disabled', false);
        } else {
            nextInfoButton.removeClass('submitbtn').addClass('Disabled');
            nextInfoButton.prop('disabled', true);
        }
    }

    address.blur(function () {
        validate();
    });

    cardNumber.blur(function () {
        validate();
    });

    $("#buyButton").click()
});