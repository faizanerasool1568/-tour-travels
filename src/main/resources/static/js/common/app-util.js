 window.parent.toastr.options = {
	"closeButton" : true,
	"debug" : false,
	"newestOnTop" : false,
	"progressBar" : true,
	"positionClass" : "toast-top-center",
	"preventDuplicates" : true,
	"onclick" : null,
	"showDuration" : "300",
	"hideDuration" : "1000",
	"timeOut" : "5000",
	"extendedTimeOut" : "1000",
	"showEasing" : "swing",
	"hideEasing" : "linear"
}


function errorEvent(message){
    window.parent.toastr.error(message);
}

function recordCreatedEvent(data){
    window.parent.toastr.success('Record Created');
}

function recordUpdatedEvent(data){
    window.parent.toastr.success('Record Updated');
}

function recordDeleted(data){
    window.parent.toastr.success('Record Deleted');
}

function postToCanvas(path,  paramValue) {
    //formMenu is in layout.html
    paramValue = paramValue || 0;
    var form = $('#formMenu')
    form.attr('action', getURL(path));
    form.attr('method', "post");
    form.attr('target', "icanvas");
    if (paramValue != 0 && paramValue != 'null') {
        form.append('<input type="hidden" name="parentId" value="' + paramValue  + '" />');
    }
    form.submit();
}



var noSpinner = ["keep-alive", "secure-images","save-usercorporate-template","user-access-session","user-confirm"];
let skipSpinner = false;
$(document).ready(function() {
		var $loading = window.parent.$('#loading').hide();
		$(document).ajaxSend(
			function(e, xhr, settings) {
				if (noSpinner.indexOf(settings.url) === -1 && settings.url.indexOf("skipSpinner") === -1
					&& $(".nospinner").length === 0) {
					showLoader($loading);
				}

			}).ajaxStop(function() {
                if ($(".showSpinner").length === 0) {
					$loading.hide();
				}
			});

		$(document).on("submit", "form", function(e) {
			var faction = $(this).attr('action');
			//alert(faction.indexOf("-download"));
			//skip if the url contains download. except download for all other URLs spinner will work
			if (faction.indexOf("-download") == -1 && faction != null) {
				$(this).find(':submit').attr("disabled", true);
				$(this).find(':button').attr("disabled", true);
				$(this).find('button[type="submit"] i').attr('class',
					'glyphicon glyphicon-refresh animate');
				showLoader();
				e.preventDefault
			}
		});
		
		$(document.body).on("dragover", function(e) {
			e.stopPropagation();
			return false;
		});
		$(document.body).on("drop", function(e) {
			e.stopPropagation();
			return false;
		});



	});

function showLoader() {
	var $loading = window.parent.$('#loading');
	if (!$loading.is(":visible")) {
		$loading.show();
	}
}

function hideLoader() {
	var $loading = window.parent.$('#loading');
	$loading.hide();
}

function redirectCall(url) {
	var form = $('#formMenu');
	form.attr('action', getURL(url));
	form.attr('method', "post");
	form.submit();
}

function hideSpinner() {
	window.parent.$('#loading').hide();
}
function showingSpinner() {
	var $loading = window.parent.$('#loading').hide();
	$loading.show();
	
};




