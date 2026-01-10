var c = 0;
function refreshPagination() {
	var totPages = $("#totalPages").val();
	var visiblePages = 7;
	if (totPages == 0) {
		visiblePages = 0;
	}
	if (totPages > 0) {
		$('.mypagination').empty();
		$('.mypagination').twbsPagination({
			totalPages: totPages,
			visiblePages: visiblePages,
			first: '&laquo;',
			prev: '&lsaquo;',
			next: '&rsaquo;',
			last: '&raquo;',
			onPageClick: function(event, page) {
				if (c > 0) {
					//remove above condition causes 401 error on page load.	            		
					onClickOfPage(page);
				}
				c = 1;

			}
		});
		$('.mypagination').show();
	} else {
		$('.mypagination').hide();
	}
}

refreshPagination();