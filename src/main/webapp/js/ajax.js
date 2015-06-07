$('#form-login').blur(function(){
        var login = $(this).val().toString();
        $.ajax({
            type: "GET",
            url: window.pageContext + '/validLogin?first_name=' + login,
            async: true,
            dataType: "text",
            success: function(data) {
                if(data === "true"){
                    $("#login-exist").attr("class", "error-message-visible");
                }
                else{
                    $("#login-exist").attr("class", "error-message-hidden");
                }
            }
        });
});