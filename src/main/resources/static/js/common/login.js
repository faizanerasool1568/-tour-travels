
$(function() {

    //csrf setup
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    var $loading = $('#loading').hide();

    //loader icon
    $(document).ajaxStart(function () {
        $loading.show();
    }).ajaxStop(function () {
        $loading.hide();
    });

    $('form').on('submit', function() {
        $(this).find('button[type="submit"] i').attr('class', 'glyphicon glyphicon-refresh animate');
        $loading.show();
    });
	
});






