var contextRoot = $("meta[name='contextRoot']").attr("content");
var userType = $("meta[name='userType']").attr("content")
var activeLang = $("meta[name='activeLang']").attr("content")
var dateFormat = $("meta[name='dateFormat']").attr("content")
var baseCurrency = $("meta[name='baseCurrency']").attr("content")
var error = $("meta[name='contextRoot']").attr("content") + "error"
var skipPwd = $("meta[name='skipPwd']").attr("content")

localStorage.removeItem("skipPwd");
localStorage.setItem("activeLang", activeLang);

var title = 'There was no activity for a while';
var message = 'Would you like to stay connected?';
var logoutButton = "Logout";
var keepAliveButton = "Stay Connected";
var countdownMessage = "Session Expires in {timer} Seconds";
if ("ar" === activeLang) {
	title = "لم يكن هناك نشاط في الصفحة لفترة من الزمن";
	message = "هل تريد ان تبقى مسجلًا؟";
	logoutButton = "تسجيل الخروج";
	keepAliveButton = "البقاء متصلا";
	countdownMessage = "تنتهي الجلسة في {timer} ثانية";
}

var english = {
	"sEmptyTable": "No data available in table",
	"sInfo": "Showing _START_ to _END_ of _TOTAL_ entries",
	"sInfoEmpty": "Showing 0 to 0 of 0 entries",
	"sInfoFiltered": "(filtered from _MAX_ total entries)",
	"sInfoPostFix": "",
	"sInfoThousands": ",",
	"sLengthMenu": "Show _MENU_ entries",
	"sLoadingRecords": "Loading...",
	"sProcessing": "Processing...",
	"sSearch": "Search:",
	"sZeroRecords": "No matching records found",
	"oPaginate": {
		"sFirst": "First",
		"sLast": "Last",
		"sNext": "Next",
		"sPrevious": "Previous"
	},
	"oAria": {
		"sSortAscending": ": activate to sort column ascending",
		"sSortDescending": ": activate to sort column descending"
	}
}

var arabic = {
	"sEmptyTable": "ليست هناك بيانات متاحة في الجدول",
	"sLoadingRecords": "جارٍ التحميل...",
	"sProcessing": "جارٍ التحميل...",
	"sLengthMenu": "أظهر _MENU_ مدخلات",
	"sZeroRecords": "لم يعثر على أية سجلات",
	"sInfo": "إظهار _START_ إلى _END_ من أصل _TOTAL_ مدخل",
	"sInfoEmpty": "يعرض 0 إلى 0 من أصل 0 سجل",
	"sInfoFiltered": "(منتقاة من مجموع _MAX_ مُدخل)",
	"sInfoPostFix": "",
	"sSearch": "ابحث:",
	"sUrl": "",
	"oPaginate": {
		"sFirst": "الأول",
		"sPrevious": "السابق",
		"sNext": "التالي",
		"sLast": "الأخير"
	},
	"oAria": {
		"sSortAscending": ": تفعيل لترتيب العمود تصاعدياً",
		"sSortDescending": ": تفعيل لترتيب العمود تنازلياً"
	}
}




if (skipPwd) {
	localStorage.setItem("skipPwd", skipPwd);
}
var chosenPlaceHolder = "Select an Option";
var currentLang = english;
if ("ar" === activeLang) {
	chosenPlaceHolder = "\u062d\u062f\u062f \u0627\u062e\u062a\u064a\u0627\u0631\u0627";
	currentLang = arabic;
}

function getURL(path) {
	//if contextpath is found in the front.
	if (path.indexOf(contextRoot) == 0) {
		return path;
	} else {
		if (path.substring(0, 1) == '/') {
			path = path.substring(1);
		}
		return contextRoot + path;
	}
}

var data_action = "10ab81";

/** ****************** Choosen component *************** */
var chosenConfig = {
	inherit_select_classes: true,
	disable_search_threshold: 5,
	no_results_text: "ar" === activeLang? 'عذرًا, لم يتم العثور على اي نتائج' : 'Sorry, Nothing found.',
	placeholder_text_single: chosenPlaceHolder
};

var chosenConfigWithoutParent = {
	disable_search_threshold: 5,
	no_results_text: 'Oops, nothing found!',
	placeholder_text_single: chosenPlaceHolder
};

$(document).ready(function () {
	window.history.pushState(null, null, "");
	window.addEventListener('popstate', function () {
		history.pushState(null, null, "");
	});

	if ($("#breakOutOfFrame").length) {
		if (top.location != location) {
			top.location.href = document.location.href;
		}
	}

 
	//disable some keys like F5
	$(document).on("keydown", function (e) {
		if (e.keyCode == 116 || e.key === ";"  || e.keyCode == 27) {
			e.preventDefault();
			return false;
		}
	});
	
	/*$(document).on("copy paste cut", ".form-control", function(e) {
		e.stopPropagation(); 
		return false;
	});*/

	$("form").attr('autocomplete', 'off');
	$("input").attr("autocomplete", "off");

	// added for disabling the drop and drop functionality in html elements
	$(document.body).on("dragover", function (e) {
		e.stopPropagation();
		return false;
	});
	$(document.body).on("drop", function (e) {
		e.stopPropagation();
		return false;
	});


	$(".chosen-select").chosen(chosenConfig);
	$('[data-toggle="tooltip"]').tooltip();
	$(document).ajaxError(function (e, xhr, options, thrownError) {
		showErrors(xhr);
	});

	$.fn.bootstrapPasswordMeter = function (options) {
		var settings = $.extend({
			minPasswordLength: 6,
			level0ClassName: 'progress-bar-danger',
			level0Description: 'Weak',
			level1ClassName: 'progress-bar-danger',
			level1Description: 'Not great',
			level2ClassName: 'progress-bar-warning',
			level2Description: 'Better',
			level3ClassName: 'progress-bar-success',
			level3Description: 'Strong',
			level4ClassName: 'progress-bar-success',
			level4Description: 'Very strong',
			parentContainerClass: '.form-group'
		}, options || {});

		$(this).on("keyup", function () {
			var progressBar = $("#pwdStrength");
			var progressBarWidth = 0;
			var progressBarDescription = '';
			if ($(this).val().length >= settings.minPasswordLength) {
				var zxcvbnObj = zxcvbn($(this).val());
				progressBar.removeClass(settings.level0ClassName)
					.removeClass(settings.level1ClassName)
					.removeClass(settings.level2ClassName)
					.removeClass(settings.level3ClassName)
					.removeClass(settings.level4ClassName);
				switch (zxcvbnObj.score) {
					case 0:
						console.log(progressBar);
						progressBarWidth = 25;
						progressBar.addClass(settings.level0ClassName);
						progressBarDescription = settings.level0Description;
						break;
					case 1:
						console.log(progressBar);
						progressBarWidth = 25;
						progressBar.addClass(settings.level1ClassName);
						progressBarDescription = settings.level1Description;
						break;
					case 2:
						console.log(progressBar);
						progressBarWidth = 50;
						progressBar.addClass(settings.level2ClassName);
						progressBarDescription = settings.level2Description;
						break;
					case 3:
						progressBarWidth = 75;
						progressBar.addClass(settings.level3ClassName);
						progressBarDescription = settings.level3Description;
						break;
					case 4:
						progressBarWidth = 100;
						progressBar.addClass(settings.level4ClassName);
						progressBarDescription = settings.level4Description;
						break;
				}
			} else {
				progressBarWidth = 0;
				progressBarDescription = '';
			}
			progressBar.css('width', progressBarWidth + '%');
			progressBar.text(progressBarDescription);
		});
	};
	
	 window.onscroll = function() {myFunction()};

var header = document.getElementById("sticky_header");

	function myFunction() {
		if (header != null) {
			var sticky = header.offsetTop;

			if (window.pageYOffset > sticky) {
				header.classList.add("navbar-fixed-top");
				$(".content").css("padding-top", '91px');


			} else {
				header.classList.remove("navbar-fixed-top");
				$(".content").css("padding-top", '0px');

			}
		}
}

});

/*
$(window).load(function(){
	$('body').backDetect(function(){
	  // Callback function
	  top.location.href ="google.co.in";
	});
  });
*/
$(window).on("navigate", function (event, data) {
	var direction = data.state.direction;
	if (direction == 'back') {
		// do something
	}
	if (direction == 'forward') {
		// do something else
	}
});

var publicKey = null;

/*if (publicKey == null) {
console.log('test')
console.log($("meta[name='_csrf_header']").attr("content"));
console.log($("meta[name='_csrf']").attr("content"));

	$.ajax({
		url: getURL('/login/key'),
		type: 'POST',
		beforeSend: function (xhr) {
			xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
		},
		success: function (data) {
			publicKey = data;
		}
	});
}
*/
// commented for enable web shims validation on ios
/*webshim
		.setOptions(
				'enhanceAuto',
				!(matchMedia('(max-device-width: 720px)').matches || matchMedia('(max-device-width: 1024px)').matches
						&& Modernizr.touchevents));*/

webshim.setOptions('forms', {
	replaceValidationUI: true,
	lazyCustomMessages: true,
	addValidators: true,
	// instant validation options
	iVal: {
		// a simple selector of your form element[s]
		"sel": ".ws-validate",

		// whether webshim should show a bubble on invalid event: "hide" | true
		// | false
		"handleBubble": "hide",

		// selector or function to find the wrapper of the form field[s] (if
		// there are more than one form field in the wrapper they will be
		// handled as a group with one combined errormessage)
		fieldWrapper: '.form-group',

		// wether an invalid input should be re-checked while user types
		"recheckDelay": 400,

		// in case a developer has set novalidate attribute to a form element
		// and wants the form validated on submit, this should be set to true
		"submitCheck": false,

		// Events to check for validity/update validation UI
		"events": "focusout change", // Note: The 'invalid' and
		// 'updatevalidation' events will be
		// always used

		// add bootstrap specific classes
		errorMessageClass: 'help-block',
		// successWrapperClass: 'has-success',
		errorWrapperClass: 'has-error',

		// show/hide effect: 'no' | 'slide' | 'fade'
		"fx": "slide"
	}
});

webshim.setOptions('forms-ext', {
	replaceUI: "auto",
	types: 'date month',
	"widgets": {
		"startView": 2,
		"nogrouping": true,
		"popover": {
			"inline": false
		}

	},
	number: {
		calculateWidth: false
	},

	time: {
		calculateWidth: false
	}
});


// webshims will implement those features in all browsers/devices
// but will only enhance capable browsers on desktop with custom styleable
// mediaelement controls and form widgets
webshim.polyfill('forms forms-ext dom-support');

$.webshims.formcfg = {
	en: {
		dFormat: '/',
		dateSigns: '/',
		patterns: {
			d: dateFormat
		}
	},
	ar: {
		dFormat: '/',
		dateSigns: '/',
		patterns: {
			d: dateFormat
		}
	}
};

webshim.activeLang(activeLang === 'en' ? 'en-GB' : activeLang);

// chosen element is displayed hidden through chosen plugin
$.webshims.ready('forms forms-ext dom-support', function ($, webshims) {
	$('.chosen').each(function () {
		var shadowElement = $(this).next().closest("chzn-container");
		if (shadowElement[0]) {
			webshims.addShadowDom(this, shadowElement);
		}
	});
});

/** ******************* Checkbox ********************* */
$(".check-hidden:checked").parents(".checkinput").addClass("active");
$("body").on("click", ".check-hidden", function (e) {
	e.stopPropagation();
	if ($(this).is(':checked')) {
		$(this).parents(".checkinput").addClass("active");
	} else {
		$(this).parents(".checkinput").removeClass("active");
	}
});

/** ************collapsePanel*********************** */
$('.panel-collapse').on(
	'show.bs.collapse',
	function () {
		$(this).parent().find(".fa-angle-right").removeClass(
			"fa-angle-right").addClass("fa-angle-down");
		$(this).children().find(".fa-angle-down").removeClass(
			"fa-angle-down").addClass("fa-angle-right");
	}).on(
		'hide.bs.collapse',
		function () {
			$(this).parent().find(".fa-angle-down")
				.removeClass("fa-angle-down").addClass("fa-angle-right");
		});

/** ************************************************ */
/* Need to Move Below Lines to Seprate File Later */
/*
 * webshims.validityMessages.en-GB = { "only-number" : "Please enter only
 * Number", "only-text" : "Please enter only Text" }
 */

/** ********************* Select Component *************** */

(function ($) {

	$(".searchclear").click(function () {
		$(".searchinput").val('');
	});

	additionalCustomValidation($('body'));

	$.fn.dropselect = function (options) {
		// options=$.extend({},$.fn.dropselect.add,options); // Setting default
		// height for the component
		return this
			.each(function () {
				var $ds = $(this);
				// DOM creation and attaching acccordion function
				var $header = $('<div class="panel-heading collapseHeader" data-toggle="collapse"><p class="panel-title small"><span class="glyphicon glyphicon-chevron-right"></span>&nbsp;'
					+ $ds.data("title") + '</p></div>');
				var $footer = $('<div class="list-group selectHolder"></div>');
				// var $search=$('<div class="input-group"><input
				// type="text" class="searchInput form-control"><span
				// class="input-group-btn"><button class="btn btn-default"
				// type="button"><span class="glyphicon
				// glyphicon-search"></span></button></span></div>');
				var $search = $('<div class="form-group dssearch"><input type="text" class="searchInput form-control" placeholder="Type to search"><span class="glyphicon glyphicon-search form-control-search"></span></div>');
				$ds.addClass('list-group panel-collapse collapse').wrap(
					'<div class="panel panel-default cust_panel no-arr form-control" id="'
					+ $ds.data("setid") + '"></div>').parent()
					.append($footer).prepend($header);
				var $holder = $ds.parent().find($footer);
				var $stopdbclick = $('<div class="stopdbclick"></div>')
				// add button functionality
				if ($ds.attr('data-addurl')) {
					var $add = $('<a href="'
						+ $ds.data("addurl")
						+ '" class="btn btn-default selectBtn"><i class="fa fa-user-plus" aria-hidden="true"></i></a>')
					$header.append($add);
					$add.on("click", function (e) {
						e.stopPropagation()
					});
				}
				// select functionality
				$ds.find("input[type=radio]").on(
					"click",
					function () {
						if ($(this).is(':checked')) {
							$(this).parents("label").addClass(
								"btn-primary active").siblings(
									"label").removeClass(
										"btn-primary active");
							$selected = $(this).parents("label");
							$selected.appendTo($holder);
							$ds.collapse('hide');
							$ds.find('.searchInput').val('');
							$ds.find("label").show();
							$(this).parents("label").find(".nme span")
								.css({
									'font-weight': 'normal',
									'color': '#666'
								});
						}
					}); // end of select functionality
				// Activate accordion if more thatn single value present
				if ($ds.find("label").length > 1) {
					// check weather pre-selected value is present on
					// document load
					$ds.find("input[type=radio]").each(
						function () {
							if ($(this).is(':checked')) {
								$(this).parents("label").addClass(
									"btn-primary active").appendTo(
										$holder);
							}
						})
					// collapse expand functionality
					$header
						.bind(
							"click",
							function () {
								var position = $(".content")
									.scrollTop();
								$(".content").animate({
									scrollTop: position + 1
								}, "fast");
								if ($ds.parent().find($footer).has(
									"label").length) { // if
									// already
									// has
									// selected
									// value
									$container = $(this).parent()
										.find($ds);
									$ds.parent().find($footer)
										.find("label")
										.prependTo($container);
								}
								if ($ds.find("label").length >= 5) { // prepend
									// Search
									// functionality
									// if
									// list
									// is
									// >= 5
									$search.prependTo($ds);
									// Search functionality strats
									// here
									$ds
										.find('.searchInput')
										.focus()
										.keyup(
											function () {
												searchWord = $(
													this)
													.val();
												if (searchWord.length >= 1) {
													$ds
														.find(
															"label")
														.hide();
													$ds
														.find(
															"label .nme span")
														.css(
															{
																'font-weight': 'normal',
																'color': '#666'
															});
													$ds
														.find(
															"label .nme")
														.each(
															function (
																i,
																data) {
																text = $(
																	this)
																	.text();
																if (text
																	.match(RegExp(
																		searchWord,
																		'i'))) {
																	// var
																	// cols=text.match(RegExp(searchWord,'i'));
																	newHTML = text
																		.replace(
																			text
																				.match(RegExp(
																					searchWord,
																					'i')),
																			'<span style="font-weight:bold;color:#000;">'
																			+ text
																				.match(RegExp(
																					searchWord,
																					'i'))
																			+ '</span>');
																	$ds
																		.find(
																			'label .nme:eq('
																			+ i
																			+ ')')
																		.html(
																			newHTML)
																		.parents(
																			"label")
																		.show();
																}
															});
												} else {
													$ds
														.find(
															"label")
														.show();
													$ds
														.find(
															"label .nme span")
														.css(
															{
																'font-weight': 'normal',
																'color': '#666'
															});
												}
											});
									$ds
										.find('.searchInput')
										.keypress(
											function (e) {
												if (e.which == 13) {
													e
														.preventDefault();
													$(this)
														.val(
															'');
													$ds
														.find(
															"label")
														.show();

												}
											});
									// Search functionality Ends
									// here
								}
								$ds.collapse('toggle');
							});

				} else {
					$header.unbind("click");
					$ds.find("label").find("input[type=radio]").attr(
						"checked", "checked");
					$ds.find("label").addClass("btn-primary active")
						.appendTo($holder);
				}
				// icon(collapse and expand mode) manipulation.
				$ds.on(
					'show.bs.collapse',
					function () {
						$(this).parent().find(
							".glyphicon-chevron-right")
							.removeClass("glyphicon-chevron-right")
							.addClass("glyphicon-chevron-down");

					}).on(
						'hide.bs.collapse',
						function () {
							$stopdbclick.appendTo($ds);
							$(this).parent()
								.find(".glyphicon-chevron-down")
								.removeClass("glyphicon-chevron-down")
								.addClass("glyphicon-chevron-right");
							var $latestChecked = $ds.find(
								"input[type=radio]").is(':checked');
							if ($latestChecked) {
								$ds.find("input[type=radio]:checked")
									.parents("label").addClass(
										"btn-primary active")
									.appendTo($holder);
							}
						}).on('hidden.bs.collapse', function () {
							$ds.find($stopdbclick).remove();
						}); // end of icon manipulation

			}); // end of return
	}; // end of dropselect
}(jQuery)); // end of plugin

/*
 * Treeview 1.4.2 - jQuery plugin to hide and show branches of a tree
 * 
 * http://bassistance.de/jquery-plugins/jquery-plugin-treeview/
 * 
 * Copyright Jörn Zaefferer Released under the MIT license:
 * http://www.opensource.org/licenses/mit-license.php
 */

(function ($) {

	// TODO rewrite as a widget, removing all the extra plugins
	$
		.extend(
			$.fn,
			{
				swapClass: function (c1, c2) {
					var c1Elements = this.filter('.' + c1);
					this.filter('.' + c2).removeClass(c2).addClass(c1);
					c1Elements.removeClass(c1).addClass(c2);
					return this;
				},
				replaceClass: function (c1, c2) {
					return this.filter('.' + c1).removeClass(c1)
						.addClass(c2).end();
				},
				hoverClass: function (className) {
					className = className || "hover";
					return this.hover(function () {
						$(this).addClass(className);
					}, function () {
						$(this).removeClass(className);
					});
				},
				heightToggle: function (animated, callback) {
					animated ? this.animate({
						height: "toggle"
					}, animated, callback)
						: this
							.each(function () {
								jQuery(this)[jQuery(this).is(
									":hidden") ? "show"
									: "hide"]();
								if (callback)
									callback.apply(this,
										arguments);
							});
				},
				heightHide: function (animated, callback) {
					if (animated) {
						this.animate({
							height: "hide"
						}, animated, callback);
					} else {
						this.hide();
						if (callback)
							this.each(callback);
					}
				},
				prepareBranches: function (settings) {
					if (!settings.prerendered) {
						// mark last tree items
						this.filter(":last-child:not(ul)").addClass(
							CLASSES.last);
						// collapse whole tree, or only those marked as
						// closed, anyway except those marked as open
						this
							.filter(
								(settings.collapsed ? "" : "."
									+ CLASSES.closed)
								+ ":not(."
								+ CLASSES.open + ")")
							.find(">ul").hide();
					}
					// return all items with sublists
					return this.filter(":has(>ul)");
				},
				applyClasses: function (settings, toggler) {
					// TODO use event delegation
					this.filter(":has(>ul):not(:has(>a))")
						.find(">span").unbind("click.treeview")
						.bind("click.treeview", function (event) {
							// don't handle click events on
							// children, eg. checkboxes
							if (this == event.target)
								toggler.apply($(this).next());
						}).add($("a", this)).hoverClass();

					if (!settings.prerendered) {
						// handle closed ones first
						this.filter(":has(>ul:hidden)").addClass(
							CLASSES.expandable).replaceClass(
								CLASSES.last, CLASSES.lastExpandable);

						// handle open ones
						this.not(":has(>ul:hidden)").addClass(
							CLASSES.collapsable).replaceClass(
								CLASSES.last, CLASSES.lastCollapsable);

						// create hitarea if not present
						var hitarea = this.find("div."
							+ CLASSES.hitarea);
						if (!hitarea.length)
							hitarea = this.prepend(
								"<div class=\"" + CLASSES.hitarea
								+ "\"/>").find(
									"div." + CLASSES.hitarea);
						hitarea
							.removeClass()
							.addClass(CLASSES.hitarea)
							.each(
								function () {
									var classes = "";
									$
										.each(
											$(this)
												.parent()
												.attr(
													"class")
												.split(
													" "),
											function () {
												classes += this
													+ "-hitarea ";
											});
									$(this).addClass(classes);
								})
					}

					// apply event to hitarea
					this.find("div." + CLASSES.hitarea).click(toggler);
				},
				treeview: function (settings) {

					settings = $.extend({
						cookieId: "treeview"
					}, settings);

					if (settings.toggle) {
						var callback = settings.toggle;
						settings.toggle = function () {
							return callback.apply($(this).parent()[0],
								arguments);
						};
					}

					// factory for treecontroller
					function treeController(tree, control) {
						// factory for click handlers
						function handler(filter) {
							return function () {
								// reuse toggle event handler, applying
								// the elements to toggle
								// start searching for all hitareas
								toggler
									.apply($(
										"div."
										+ CLASSES.hitarea,
										tree)
										.filter(
											function () {
												// for plain
												// toggle,
												// no filter
												// is
												// provided,
												// otherwise
												// we need
												// to check
												// the
												// parent
												// element
												return filter ? $(
													this)
													.parent(
														"."
														+ filter).length
													: true;
											}));
								return false;
							};
						}
						// click on first element to collapse tree
						$("a:eq(0)", control).click(
							handler(CLASSES.collapsable));
						// click on second to expand tree
						$("a:eq(1)", control).click(
							handler(CLASSES.expandable));
						// click on third to toggle tree
						$("a:eq(2)", control).click(handler());
					}

					// handle toggle event
					function toggler() {
						$(this).parent()
							// swap classes for hitarea
							.find(">.hitarea").swapClass(
								CLASSES.collapsableHitarea,
								CLASSES.expandableHitarea).swapClass(
									CLASSES.lastCollapsableHitarea,
									CLASSES.lastExpandableHitarea).end()
							// swap classes for parent li
							.swapClass(CLASSES.collapsable,
								CLASSES.expandable).swapClass(
									CLASSES.lastCollapsable,
									CLASSES.lastExpandable)
							// find child lists
							.find(">ul")
							// toggle them
							.heightToggle(settings.animated,
								settings.toggle);
						if (settings.unique) {
							$(this)
								.parent()
								.siblings()
								// swap classes for hitarea
								.find(">.hitarea")
								.replaceClass(
									CLASSES.collapsableHitarea,
									CLASSES.expandableHitarea)
								.replaceClass(
									CLASSES.lastCollapsableHitarea,
									CLASSES.lastExpandableHitarea)
								.end().replaceClass(
									CLASSES.collapsable,
									CLASSES.expandable)
								.replaceClass(
									CLASSES.lastCollapsable,
									CLASSES.lastExpandable)
								.find(">ul").heightHide(
									settings.animated,
									settings.toggle);
						}
					}
					this.data("toggler", toggler);

					function serialize() {
						function binary(arg) {
							return arg ? 1 : 0;
						}
						var data = [];
						branches.each(function (i, e) {
							data[i] = $(e).is(":has(>ul:visible)") ? 1
								: 0;
						});
						$.cookie(settings.cookieId, data.join(""),
							settings.cookieOptions);
					}

					function deserialize() {
						var stored = $.cookie(settings.cookieId);
						if (stored) {
							var data = stored.split("");
							branches
								.each(function (i, e) {
									$(e).find(">ul")[parseInt(data[i]) ? "show"
										: "hide"]();
								});
						}
					}

					// add treeview class to activate styles
					this.addClass("treeview");

					// prepare branches and find all tree items with
					// child lists
					var branches = this.find("li").prepareBranches(
						settings);

					switch (settings.persist) {
						case "cookie":
							var toggleCallback = settings.toggle;
							settings.toggle = function () {
								serialize();
								if (toggleCallback) {
									toggleCallback.apply(this, arguments);
								}
							};
							deserialize();
							break;
						case "location":
							var current = this
								.find("a")
								.filter(
									function () {
										return location.href
											.toLowerCase()
											.indexOf(
												this.href
													.toLowerCase()) == 0;
									});
							if (current.length) {
								// TODO update the open/closed classes
								var items = current.addClass("selected")
									.parents("ul, li").add(
										current.next()).show();
								if (settings.prerendered) {
									// if prerendered is on, replicate the
									// basic class swapping
									items
										.filter("li")
										.swapClass(CLASSES.collapsable,
											CLASSES.expandable)
										.swapClass(
											CLASSES.lastCollapsable,
											CLASSES.lastExpandable)
										.find(">.hitarea")
										.swapClass(
											CLASSES.collapsableHitarea,
											CLASSES.expandableHitarea)
										.swapClass(
											CLASSES.lastCollapsableHitarea,
											CLASSES.lastExpandableHitarea);
								}
							}
							break;
					}

					branches.applyClasses(settings, toggler);

					// if control option is set, create the
					// treecontroller and show it
					if (settings.control) {
						treeController(this, settings.control);
						$(settings.control).show();
					}

					return this;
				}
			});

	// classes used by the plugin
	// need to be styled via external stylesheet, see first example
	$.treeview = {};
	var CLASSES = ($.treeview.classes = {
		open: "open",
		closed: "closed",
		expandable: "expandable",
		expandableHitarea: "expandable-hitarea",
		lastExpandableHitarea: "lastExpandable-hitarea",
		collapsable: "collapsable",
		collapsableHitarea: "collapsable-hitarea",
		lastCollapsableHitarea: "lastCollapsable-hitarea",
		lastCollapsable: "lastCollapsable",
		lastExpandable: "lastExpandable",
		last: "last",
		hitarea: "hitarea"
	});

})(jQuery);

function showModal($elem, data, keyboard) {
	$elem.empty();
	$elem.html(data);
	if (keyboard === undefined) {
		keyboard = true;
	}
	$elem.modal({
		backdrop: "static",
		show: true,
		keyboard: keyboard
	});
	$elem.find(".chosen-select").chosen(chosenConfigWithoutParent);
	additionalCustomValidation($elem);
	$elem.updatePolyfill();
	getFocused($elem);
	$('input[type="date"]').attr("placeholder", "DD/MM/YYYY");
}

function closeModal($modalDiv,$modalCloseDiv){
		$modalCloseDiv.click();
		$modalDiv.removeClass('modal-open');
		$('.modal-backdrop').remove();
}

function additionalCustomValidation($elem) {
	$elem.find('.past-date').prop('min', function () {
		return new Date().toJSON().split('T')[0];
	});

	$elem.find('.only-text').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z_ ]/g)) {
			return $('.only-text').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.only-alphabet').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z]/g)) {
			return $('.only-alphabet').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.only-alphabet-space').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z ]/g)) {
			return $('.only-alphabet-space').getErrorMessage('typeMismatch');
		}
	});
	
	$elem.find('.only-alphabet-space-arabic').on('validatevalue', function(e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z\u0600-\u06FF ]/g)) {
			return $('.only-alphabet-space-arabic').getErrorMessage('typeMismatch');
		}
	});
	
	

	$elem.find('.user-names').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z,. ]/g)) {
			return $('.user-names').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.only-text-underscore').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z_]/g)) {
			return $('.only-text-underscore').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.alpha-numeric').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z0-9_ ]/g)) {
			return $('.alpha-numeric').getErrorMessage('typeMismatch');
		}

	});
	$elem.find('.alpha-numeric-without-underscor-space').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z0-9]/g)) {
			return $('.alpha-numeric').getErrorMessage('typeMismatch');
		}

	});
	$elem.find('.alpha-numeric-with-space').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z0-9 ]/g)) {
			return $('.alpha-numeric-with-space').getErrorMessage('typeMismatch');
		}

	});

	$elem.find('.alpha-numeric-code').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z0-9_]/g)) {
			return $('.alpha-numeric-code').getErrorMessage('typeMismatch');
		}

	});


	$elem.find('.alpha-numeric-address').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z0-9,-]/g)) {
			return $('.alpha-numeric-address').getErrorMessage('typeMismatch');
		}

	});

    $elem.find('.alpha-num-with-space').on('validatevalue', function (e, extra) {
        var value = extra.value;
        if (value.match(/[^a-zA-Z0-9,\- ]/g)) {
            return $('.alpha-num-with-space').getErrorMessage('typeMismatch');
        }else{
            var myArray = value.split("-");
            if(myArray.length != 2){
                return $('.alpha-num-with-space').getErrorMessage('typeMismatch');
            }else{
                if(myArray[0].toUpperCase().trim() == 'CCV' || myArray[0].toUpperCase().trim() == 'CCM'){
                }else{
                    if(isNaN(myArray[0])){
                        return $('.alpha-num-with-space').getErrorMessage('typeMismatch');
                    }
                    if(myArray[1].length < 2){
                        return $('.alpha-num-with-space').getErrorMessage('typeMismatch');
                    }
                }
            }
        }
    });

	$elem.find('.alpha-numeric-excludeus').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^a-zA-Z0-9]/g)) {
			return $('.alpha-numeric-excludeus').getErrorMessage('typeMismatch');
		}

	});

	$elem.find('.only-number').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^0-9]/g)) {
			return $('.only-number').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.only-cust-number').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^0-9]/g)) {
			return $('.only-cust-number').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.only-phone-number').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^1-9]/g)) {
			return $('.only-phone-number').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.phone-number').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/(^((0))[0-9]{9})/g)) {
			return $('.phone-number').getErrorMessage('typeMismatch');
		}
		if ((value.length > 10) || (value.length < 10)) {
			return $('.phone-number').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.fax-number').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/[^+0-9]{1,12}$/)) {
			return $('.fax-number').getErrorMessage('typeMismatch');
		}
		if ((value.length > 13) || (value.length < 10)) {
			return $('.fax-number').getErrorMessage('typeMismatch');
		}
	});
	$elem.find('.max-min-digits').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/(^((0))[0-9]{10})/g)) {
			return $('.max-min-digits').getErrorMessage('typeMismatch');
		}
		if ((value.length < 0) || (value.length > 10)) {
			return $('.max-min-digits').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.max-min-11digits').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/(^((0))[0-9]{10})/g)) {
			return $('.max-min-11digits').getErrorMessage('typeMismatch');
		}
		if ((value.length < 0) || (value.length > 11)) {
			return $('.max-min-11digits').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.max-min-13digits').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/(^((0))[0-9]{10})/g)) {
			return $('.max-min-13digits').getErrorMessage('typeMismatch');
		}
		if ((value.length < 0) || (value.length > 13)) {
			return $('.max-min-13digits').getErrorMessage('typeMismatch');
		}
	});
	
	$elem.find('.max-min-5digits').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/(^((0))[0-9]{10})/g)) {
			return $('.max-min-5digits').getErrorMessage('typeMismatch');
		}
		if ((value.length < 0) || (value.length > 5)) {
			return $('.max-min-5digits').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.max-min-4digits').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/(^((0))[0-9]{10})/g)) {
			return $('.max-min-4digits').getErrorMessage('typeMismatch');
		}
		if ((value.length < 0) || (value.length > 4)) {
			return $('.max-min-4digits').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.max-min-3digits').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/(^((0))[0-9]{10})/g)) {
			return $('.max-min-3digits').getErrorMessage('typeMismatch');
		}
		if ((value.length < 0) || (value.length > 3)) {
			return $('.max-min-3digits').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.max-min-alphanum').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if ((value.length > 25)) {
			return $('.max-min-alphanum').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.max-min-alphabet').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if ((value.length > 25)) {
			return $('.max-min-alphabet').getErrorMessage('typeMismatch');
		}
	});
	$elem.find('.mobile-number').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if ((value.length < 10 || value.length > 10)) {
			return $('.mobile-number').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.postal-code').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/^[0-9]{1,5}$/)) {
			return $('.postal-code').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.swift-code').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.match(/^[0-9]{1,14}$/)) {
			return $('.swift-code').getErrorMessage('typeMismatch');
		}
		if ((value.length > 15) || (value.length < 15)) {
			return $('.swift-code').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.iso-code').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (!value.match(/^[0-9]{1,3}$/)) {
			return $('.iso-code').getErrorMessage('typeMismatch');
		}
		if (!(value.length == 3)) {
			return $('.iso-code').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.number-of-sub-units').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (!value.match(/[0-4]/g)) {
			return $('.number-of-sub-units').getErrorMessage('typeMismatch');
		}
	});
	$elem.find('.decimal-separator').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (!value.match(/[,.]/g)) {
			return $('.decimal-separator').getErrorMessage('typeMismatch');
		}
	});
	$elem.find('.auto-fill-validate').on(
		'validatevalue',
		function (e, extra) {
			var attr = $(this).attr('required');
			if (typeof attr !== typeof undefined && attr !== false) {
				var value = $("#target").val();
				if (value == null || value == '') {
					return $('.auto-fill-validate').getErrorMessage(
						'typeMismatch');
				}
			}
		});

	$elem.find('.max-min-authorizers').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (!value.match(/[1-9]/g)) {
			return $('.max-min-authorizers').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.email-fsd').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (!value.match(/^(([^ -/\[\]\\^_.:-?\s{-~@"]+(\.[^ -/\[\]\\^_:=?.{-~@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
			return $('.email').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.email').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if(value.trim()!=''){
			
		
		if (!value.match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
			return $('.email').getErrorMessage('typeMismatch');
		}
		}
		else{
			return $('.email').getErrorMessage('emailMismatch');
		}
	});

	$elem.find('.reject-text').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (value.length > 200) {
			return $('.reject-text').getErrorMessage('typeMismatch');
		}
		else if (value.trim() =='') {
			return $('.reject-text').getErrorMessage('textMismatch');
		}
	});

	$elem.find('.10-digit-number-starts-1').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (!value.match(/^[1-9]\d{9}$/)) {
			return $('.10-digit-number-starts-1').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.10-digit-number-starts-2').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (!value.match(/^[2-9]\d{9}$/)) {
			return $('.10-digit-number-starts-2').getErrorMessage('typeMismatch');
		}
	});

	$elem.find('.24-digit-alphanumeric').on('validatevalue', function (e, extra) {
		var value = extra.value;
		if (!value.match(/^([a-zA-Z0-9_-]){24}$/)) {
			return $('.24-digit-alphanumeric').getErrorMessage('typeMismatch');
		}
	});
}

function updateChosen($elem) {
	$elem.find(".chosen-select").chosen(chosenConfig);
}

// var data_action = data_action.split("-")[2].substring(3, 9);

function showFragment($elem, data) {
	$elem.empty().html(data);
	$elem.find(".chosen-select").chosen(chosenConfig);
	additionalCustomValidation($elem);
	$elem.updatePolyfill();
}

function showFragmentAppend($elem, data) {
	$elem.append(data);
	$elem.find(".chosen-select").chosen(chosenConfig);
	additionalCustomValidation($elem);
	$elem.updatePolyfill();
}

function appendDatatoDiv($elem, data) {
	$elem.html("");
	$elem.html(data);
}

function getFocused($elem) {
	$elem
		.on(
			'shown.bs.modal',
			function () {
				var form = $elem.find('form');
				var firstInput = form
					.find(
						'input[type=text],input[type=password],input[type=radio],input[type=checkbox],textarea,select')
					.filter(':visible:first');
				if (firstInput != null) {
					firstInput.focus();
				}
			});
}

$(".modal").on('hidden.bs.modal', function () {
	$(this).data('bs.modal', null);
});

$(".modal").on('shown.bs.modal', function () {
   //modal shown case
});

function resetHTMLForm($formId) {
	// REFRESH FORM INPUT ELEMENTS EXCEPT RADIO BUTTONS
	$formId.find(".form-control").val("");
	$formId.find(".chosen-select").trigger("chosen:updated");
	$formId.find(".ajax-label").remove();
	// REFRESH RADIO BUTTONS
	$formId.find(".active").removeClass("active");
	$formId.find("input:checked").prop('checked', false);
}

function fieldCustomValidity($ele, errorMsg, value) {
	$ele.setCustomValidity(errorMsg);
	$ele.val(value).trigger('updatecustomvalidity');
	$ele.trigger('refreshvalidationui');
}

function checkFieldDependents($ele, $flagele, isValid) {
	var deferred = $.Deferred();
	if ($flagele.val())
		$flagele.attr("isfilled", "true");
	$($ele).on('validatevalue', function (e, extra) {
		var dependents = $(this).attr("data-mandatoryfields");
		var splitter = dependents.split(",");
		for (var i = 0; i < splitter.length; i++) {
			var fieldValue = $("#" + splitter[i]).val();
			if (fieldValue == "") {
				isValid = false;
				deferred.resolve(isValid);
				return $("#" + splitter[i]).attr("data-error-message");
			} else if ($($flagele).attr("isfilled") === "true") {
				deferred.resolve(true);
			}
		}
	});
	return deferred.promise();
}

function showErrors(xhr) {
console.log("server error code ",xhr.status);
	if (xhr.status == 400) {
		show_400_FieldErrors(xhr);
	} else if (xhr.status == 503) {
		showMiddlewareException(xhr);
	} else if (xhr.status == 500) {
		$('#ajaxError')
			.empty()
			.html(
				"<div class='alert alert-danger'><Strong></Strong>Unable to Process Your Request</div>");
	} else if (xhr.status == 403) {
		accessDeniedError();
	} else if (xhr.status == 417) {
		show_417_errors(xhr);
	} else if (xhr.status == 412) {
		show_412_errors(xhr);
	} else if (xhr.status == 410) {
		top.location.href = getURL("/error/session-invalid");
	} else if (xhr.status == 401) {
		if (xhr.responseText === "sec-breach") {
			top.location.href = getURL("/error/attack");
		}else if (xhr.responseText === "session-terminate") {
			top.location.href = getURL("/error/session-terminate");
			
	// commented session invalid case and added session terminate as redirecting to 
	// session invalid page for 1298 UAT defect ID Expiry | session time out
	// } else {
	//	top.location.href = getURL("/error/session-invalid");
	// }
		}else {
			top.location.href = getURL("/error/session-terminate");
		}
		
	}
	var $loading = window.parent.$('#loading');
	$loading.hide();
}

function showMiddlewareException(xhr) {
	$('#ajaxError').empty().html(
		"<div class='alert alert-danger'>" + xhr.responseText + "</div>");
}

function show_417_errors(xhr) {
	$('#ajaxError').empty().html(
		"<div class='alert alert-danger'>" + xhr.responseText + "</div>");
}

function show_412_errors(xhr) {
	window.parent.toastr.error(xhr.responseText);
}

function show_400_FieldErrors(xhr) {
	var jsonErr = JSON.parse(xhr.responseText);
	for (var idx in jsonErr.fieldErrors) {
		var err = jsonErr.fieldErrors[idx];
		if (err.code.toString() == "error.min.auth.users.invalid"
			|| err.code.toString() == "error.named.auth.users.invalid"
			|| err.code.toString() == "error.corporate.account") {
			show_400_workflowError(err.message);
		} else {
			var err = jsonErr.fieldErrors[idx];
			var field = $("[name^='" + err.field + "']");
			var type = $("#"+err.field).attr('type');
			var text ="";
			if(type != "file"){
			  text = field.val();
			}
			field.setCustomValidity(err.message);
			field.val(text).trigger('updatevalidation');
			if ($(field).hasClass("chosen-select")) {
				var scrollpos = $(field).next();
				$('html, body').animate({ scrollTop: scrollpos.position().top }, 'slow');
			}
		}

	}
}

function show_400_workflowError(message) {
	$('#ajaxError').empty().html(
		"<div class='alert alert-danger'>" + message + "</div>");
}

function encryptByPublicKey(value) {
	if (skipPwd) {
		return value;
	} else {
		var encrypt = new JSEncrypt();
		encrypt.setPublicKey(publicKey);
		var encrypted = encrypt.encrypt(value);
		return encrypted;
	}
}


$(document).on('change', '.form-control', function () {
	$(this).setCustomValidity('');
	$(this).trigger('refreshvalidationui');
})


jQuery.fn.ForceNumericOnly =
	function () {
		return this.each(function () {
			$(this).keydown(function (e) {
				var key = e.charCode || e.keyCode || 0;
				// allow backspace, tab, delete, enter, arrows, numbers and keypad numbers ONLY
				// home, end, period, and numpad decimal
				return (
					key == 8 ||
					key == 9 ||
					key == 13 ||
					key == 46 ||
					key == 110 ||
					key == 190 ||
					(key >= 35 && key <= 40) ||
					(key >= 48 && key <= 57) ||
					(key >= 96 && key <= 105));
			});
		});
	};


$.webshims.ready('form-validators', function () {
	$.webshim.customErrorMessages.dependent[''] = "Enter the Confirmation password to proceed.";
	$.webshim.customErrorMessages.dependent['ar'] = "\u0642\u0645\u0020\u0628\u0643\u062a\u0627\u0628\u0629\u0020\u062a\u0623\u0643\u064a\u062f\u0020\u0643\u0644\u0645\u0629\u0020\u0627\u0644\u0645\u0631\u0648\u0631\u0020\u0644\u0644\u0645\u062a\u0627\u0628\u0639\u0629\u002e\u000a";
	$.webshims.addCustomValidityRule('ibcustomvalidations', function (elem, value) {

		if ($(elem).hasClass('valid-email')) {
			var val = $(elem).val();
			if (!value.match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
				return $.setCustomValidationMsg('valid-email');
			}
		}
		return false;
	}, 'Error');

	$.setCustomValidationMsg = function (key) {
		$.webshims.customErrorMessages.ibcustomvalidations['en'] = $('.' + key).getErrorMessage('typeMismatch');
		$.webshims.customErrorMessages.ibcustomvalidations['ar'] = $('.' + key).getErrorMessage('typeMismatch');
		return true;
	}
});

$(document).on("keypress", "input:password", function (e) {
	var s = String.fromCharCode(e.which);
	if (s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey) {
		var name = $("#caps").text();
		if (name.length == 0) {
			$('<small id="caps" style="color:red">WARNING! Caps lock is ON.</small>').insertAfter(this);
		}
	} else if (!e.keyCode == 8 || e.shiftKey == false) {
		$("#caps").remove();
	}
});

webshims.validityMessages.en = {
	typeMismatch: {
		defaultMessage: "Please enter a valid value.",
		email: "Please enter an email address.",
		url: "Please enter a URL."
	},
	badInput: {
		defaultMessage: "Please enter a valid value.",
		number: "Please enter a number.",
		date: "Please enter a date.",
		time: "Please enter a time.",
		range: "Invalid input.",
		month: "Please enter a valid value.",
		"datetime-local": "Please enter a datetime."
	},
	rangeUnderflow: {
		defaultMessage: "Value must be greater than or equal to {%min}.",
		date: "This date must be later or equal to {%min}.",
		time: "Value must be at or after {%min}.",
		"datetime-local": "Value must be at or after {%min}.",
		month: "Value must be at or after {%min}."
	},
	rangeOverflow: {
		defaultMessage: "Value must be less than or equal to {%max}.",
		date: "Value must be at or before {%max}.",
		time: "Value must be at or before {%max}.",
		"datetime-local": "Value must be at or before {%max}.",
		month: "Value must be at or before {%max}."
	},
	stepMismatch: "Invalid input.",
	tooLong: "Please enter at most {%maxlength} character(s). You entered {%valueLen}.",
	tooShort: "Please enter at least {%minlength} character(s). You entered {%valueLen}.",
	patternMismatch: "Invalid input. {%title}",
	valueMissing: {
		defaultMessage: "Please fill out this field.",
		checkbox: "Please check this box if you want to proceed.",
		select: "Please select an option.",
		radio: "Please select an option."
	}
}, webshims.formcfg.en = {
	numberFormat: {
		".": ".",
		",": ","
	},
	numberSigns: ".",
	dateSigns: "/",
	timeSigns: ":. ",
	dFormat: "/",
	patterns: {
		d: "yy/mm/dd"
	},
	month: {
		currentText: "This month"
	},
	time: {
		currentText: "Now"
	},
	date: {
		closeText: "Done",
		clear: "Clear",
		prevText: "Prev",
		nextText: "Next",
		currentText: "Today",
		monthNames: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
		monthNamesShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
		dayNames: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
		dayNamesShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
		dayNamesMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"],
		weekHeader: "Wk",
		firstDay: 1,
		isRTL: !1,
		showMonthAfterYear: !1,
		yearSuffix: ""
	}
};



webshims.validityMessages.ar = {
	typeMismatch: {
		email: "\u0623\u062f\u062e\u0650\u0644 \u0631\u062c\u0627\u0621\u064b \u0627\u0644\u0628\u0631\u064a\u062f \u0627\u0644\u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a.",
		url: "\u0623\u062f\u062e\u0650\u0644 \u0631\u062c\u0627\u0621\u064b \u0639\u0646\u0648\u0627\u0646 \u0627\u0644\u0645\u0648\u0642\u0639."
	},
	badInput: {
		number: "\u0642\u064a\u0645\u0629 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d\u0629",
		date: "\u0642\u064a\u0645\u0629 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d\u0629",
		time: "\u0642\u064a\u0645\u0629 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d\u0629",
		range: "\u0642\u064a\u0645\u0629 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d\u0629",
		"datetime-local": "\u0642\u064a\u0645\u0629 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d\u0629"
	},
	tooLong: "\u0642\u064a\u0645\u0629 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d\u0629",
	patternMismatch: "\u0627\u062a\u0628\u0639 \u0631\u062c\u0627\u0621\u064b \u0627\u0644\u062a\u0646\u0633\u064a\u0642 \u0627\u0644\u0645\u0637\u0644\u0648\u0628: {%title}.",
	valueMissing: {
		defaultMessage: "\u062d\u0642\u0644 \u0625\u0644\u0632\u0627\u0645\u064a",
		checkbox: "\u0631\u062c\u0627\u0621\u064b \u0639\u0644\u0651\u0645 \u0647\u0630\u0627 \u0627\u0644\u0645\u0631\u0628\u0639 \u0625\u0646 \u0623\u0631\u062f\u062a \u0627\u0644\u0645\u062a\u0627\u0628\u0639\u0629.",
		select: "\u0631\u062c\u0627\u0621\u064b \u0627\u062e\u062a\u0631 \u0639\u0646\u0635\u0631\u064b\u0627 \u0645\u0646 \u0627\u0644\u0644\u0627\u0626\u062d\u0629.",
		radio: "\u0631\u062c\u0627\u0621\u064b \u0627\u062e\u062a\u0631 \u0623\u062d\u062f \u0647\u0630\u0647 \u0627\u0644\u062e\u064a\u0627\u0631\u0627\u062a."
	},
	rangeUnderflow: {
		defaultMessage: "\u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0627\u0644\u0642\u064a\u0645\u0629 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0623\u0648 \u062a\u0633\u0627\u0648\u064a {%min}.",
		date: "\u064A\u062C\u0628 \u0623\u0646 \u064A\u0643\u0648\u0646 \u0627\u0644\u062A\u0627\u0631\u064A\u062E \u0623\u0643\u0628\u0631 \u0645\u0646 \u0623\u0648 \u064A\u0633\u0627\u0648\u064A {%min}.",
		time: "\u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0627\u0644\u0642\u064a\u0645\u0629 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0623\u0648 \u062a\u0633\u0627\u0648\u064a {%min}.",
		"datetime-local": "\u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0627\u0644\u0642\u064a\u0645\u0629 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0623\u0648 \u062a\u0633\u0627\u0648\u064a {%min}."
	},
	rangeOverflow: {
		defaultMessage: "\u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0627\u0644\u0642\u064a\u0645\u0629 \u0623\u0642\u0644 \u0645\u0646 \u0623\u0648 \u062a\u0633\u0627\u0648\u064a {%max}.",
		date: "\u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0627\u0644\u0642\u064a\u0645\u0629 \u0623\u0642\u0644 \u0645\u0646 \u0623\u0648 \u062a\u0633\u0627\u0648\u064a {%max}.",
		time: "\u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0627\u0644\u0642\u064a\u0645\u0629 \u0623\u0642\u0644 \u0645\u0646 \u0623\u0648 \u062a\u0633\u0627\u0648\u064a {%max}.",
		"datetime-local": "\u064a\u062c\u0628 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0627\u0644\u0642\u064a\u0645\u0629 \u0623\u0642\u0644 \u0645\u0646 \u0623\u0648 \u062a\u0633\u0627\u0648\u064a {%max}."
	},
	stepMismatch: "\u0642\u064a\u0645\u0629 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d\u0629",
	tooShort: "\u064a\u0631\u062c\u0649 \u0625\u062f\u062e\u0627\u0644 {%minlength} \u0623\u0631\u0642\u0627\u0645 \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644\u002e \u0644\u0642\u062f \u0623\u062f\u062e\u0644\u062a {%valueLen}",
}, webshims.formcfg.ar = {
	numberFormat: {
		".": ".",
		",": ","
	},
	numberSigns: ".",
	dateSigns: "/",
	timeSigns: ":. ",
	dFormat: "/",
	patterns: {
		d: "dd/mm/yy"
	},
	month: {
		currentText: "\u0647\u0630\u0627 \u0627\u0644\u0634\u0647\u0631"
	},
	date: {
		closeText: "\u0625\u063a\u0644\u0627\u0642",
		clear: "\u0645\u0633\u062d",
		prevText: "<\u0627\u0644\u0633\u0627\u0628\u0642",
		nextText: "\u0627\u0644\u062a\u0627\u0644\u064a>",
		currentText: "\u0627\u0644\u064a\u0648\u0645",
		monthNames: ["\u064a\u0646\u0627\u064a\u0631", "\u0641\u0628\u0631\u0627\u064a\u0631", "\u0645\u0627\u0631\u0633", "\u0627\u0628\u0631\u064a\u0644", "\u0645\u0627\u064a\u0648","\u064A\u0648\u0646\u064A\u0648","\u064A\u0648\u0644\u064A\u0648", "\u0627\u063a\u0633\u0637\u0633", "\u0633\u064a\u0628\u062a\u0645\u0628\u0631", "\u0627\u0643\u062a\u0648\u0628\u0631", "\u0646\u0648\u0641\u0645\u0628\u0631", "\u062f\u064a\u0633\u0645\u0628\u0631"],
		monthNamesShort: ["\u064a\u0646\u0627\u064a\u0631", "\u0641\u0628\u0631\u0627\u064a\u0631", "\u0645\u0627\u0631\u0633", "\u0627\u0628\u0631\u064a\u0644", "\u0645\u0627\u064a\u0648", "\u064a\u0648\u0646\u064a\u0648", "\u064a\u0648\u0644\u064a\u0648", "\u0627\u063a\u0633\u0637\u0633", "\u0633\u064a\u0628\u062a\u0645\u0628\u0631", "\u0627\u0643\u062a\u0648\u0628\u0631", "\u0646\u0648\u0641\u0645\u0628\u0631", "\u062f\u064a\u0633\u0645\u0628\u0631"],
		dayNames: ["\u0627\u0644\u0623\u062d\u062f", "\u0627\u0644\u0627\u062b\u0646\u064a\u0646", "\u0627\u0644\u062b\u0644\u0627\u062b\u0627\u0621", "\u0627\u0644\u0623\u0631\u0628\u0639\u0627\u0621", "\u0627\u0644\u062e\u0645\u064a\u0633", "\u0627\u0644\u062c\u0645\u0639\u0629", "\u0627\u0644\u0633\u0628\u062a"],
		dayNamesShort: ["\u0627\u0644\u0623\u062d\u062f", "\u0627\u0644\u0627\u062b\u0646\u064a\u0646", "\u0627\u0644\u062b\u0644\u0627\u062b\u0627\u0621", "\u0627\u0644\u0623\u0631\u0628\u0639\u0627\u0621", "\u0627\u0644\u062e\u0645\u064a\u0633", "\u0627\u0644\u062c\u0645\u0639\u0629", "\u0627\u0644\u0633\u0628\u062a"],
		dayNamesMin: ["\u062d", "\u0646", "\u062b", "\u0631", "\u062e", "\u062c", "\u0633"],
		weekHeader: "\u0623\u0633\u0628\u0648\u0639",
		firstDay: 1,
		isRTL: true,
		showMonthAfterYear: !1,
		yearSuffix: ""
	}
};


$(document).on("click", ".ws-popover-opener", function () {
	$('.ws-empty').attr("disabled", false);
});

if (!String.prototype.endsWith) {
	String.prototype.endsWith = function (search, this_len) {
		if (this_len === undefined || this_len > this.length) {
			this_len = this.length;
		}
		return this.substring(this_len - search.length, this_len) === search;
	};
}

function show(ele) {
	$(ele).removeClass('ele-hide');
	$(ele).addClass('ele-show');
}

function hide(ele) {
	$(ele).removeClass('ele-show');
	$(ele).addClass('ele-hide');
}

function scrollTop(myDiv, eleFocus) {
	$('html, body').animate({
		scrollTop: $(myDiv).offset().top
	}, 'slow', function () {
		$(eleFocus).focus();
	});
}

function updatePolyfill(updateDiv) {
	$(updateDiv).find(".chosen-select").chosen(chosenConfigWithoutParent);
	$(updateDiv).updatePolyfill();
}

function ajaxPost(url, postData, updateDiv, scrollTo, focusTo, callback) {
	$.ajax({
		type: 'POST',
		url: getURL(url),
		data: postData,
		success: function (data) {
			$(updateDiv).empty();
			$(updateDiv).html(data);
			updatePolyfill(updateDiv);

			if (scrollTo != undefined) {
				console.log(scrollTo);
				scrollTop(scrollTo, focusTo);
			}
			if (callback != undefined) {
				callback();
			}
		}
	});
}

webshim.setOptions("forms-ext", {
	"date": {
		"buttonOnly": true
	}
});

function showElement($element){
	$element.removeClass("ele-hide");
    $element.addClass("ele-show");
}



$('.help_ico').popover({
	trigger: 'focus'
	
  });


$('.help_ico').on('shown.bs.popover', function () {
	
	if ($("body").find(".popover_backdrop").length < 1){
		
		console.log("satisfied")
		var body = document.querySelector('body');
		var widthBefore = body.offsetWidth; // width of the html body when scroll appers
		var set_width= window.innerWidth - widthBefore + "px"
		//body.style.paddingRight = set_width;
		$(".header_top,.header_bottom,.content").css("padding-right",set_width)
		$(".navbar-fixed-top").css("padding-right",set_width);
		$("body").addClass("stopscroll");
		$("body").append("<div class='popover_backdrop'></div>");
		
		
	}
  });

  $('.help_ico').on('hide.bs.popover', function () {
	$("body").removeClass("stopscroll");
	$(".popover_backdrop").remove();
	$(".header_top,.header_bottom,.content").css("padding-right","0px")
	$(".navbar-fixed-top").css("padding-right","0px");

  });
  $('.help_ico').on('hidden.bs.popover', function () {
	$(".popover_backdrop").remove();
	
  });


$('.panel-collapse').on('show.bs.collapse', function () {
	$(this).parent().find(".fa-angle-right").removeClass("fa-angle-right").addClass("fa-angle-down");
	$(this).children().find(".fa-angle-down").removeClass("fa-angle-down").addClass("fa-angle-right");
}).on('hide.bs.collapse', function () {
	$(this).parent().find(".fa-angle-down").removeClass("fa-angle-down").addClass("fa-angle-right");
});


 $(document).on("click", "#switchLang", function(e) {
  		var pathname = window.location.pathname;
  		var lang=$(this).attr("lang");
  		//var url='/prelogin/switch-locale'+"?lang="+lang+"&preUrl="+pathname;
  		let searchParams = new URLSearchParams(window.location.search);
  		let token = searchParams.get('token');
  		var url;
  		if(searchParams.has('token')) {
  			url='/prelogin/switch-locale'+"?lang="+lang+"&preUrl="+pathname+"&token="+token;
  		}else{
  			url='/prelogin/switch-locale'+"?lang="+lang+"&preUrl="+pathname;
  		}
  		postToCanvas(url);
  		
});





$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
    // Initialize popover component
    $(function () {
        $('[data-toggle="popover"]').popover()
    })
});


$(document).on("click", "#switchLangEn", function(e) {
  		 var url='/prelogin/change-session-language'+"?sessionlang=en_US";
  		postToCanvas(url);
  		
});

$(document).on("click", "#switchLangAr", function(e) {
  		 var url='/prelogin/change-session-language'+"?sessionlang=ar_SA";
  		postToCanvas(url);
  		
});




















