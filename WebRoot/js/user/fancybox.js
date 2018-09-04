/**
 * 
 *标的详情页贷款材料查看js
 * 
 */
$(function() {
	$("a[rel=group]").fancybox({ 
	        'titlePosition' : 'over', 
	        openEffect: 'elastic',
	        'cyclic'        : true, 
	        'centerOnScroll': true,
	        'titleFormat'   : function(title, currentArray, currentIndex, currentOpts) { 
	                    return '<span id="fancybox-title-over">' + (currentIndex + 1) + 
	                    ' / ' + currentArray.length + (title.length ? '   ' + title : '') + '</span>'; 
	        },
	        onStart: function () {
	            $('body').css('overflow','hidden');
	        },
	        onClosed: function () {
	            $('body').css('overflow','auto');
	        }
	        
	    });
	});