$().ready(function() {
	var ascensor = $('#demo').ascensor({
		direction:  [[0, 0], [1, 0], [2, 0], [0, 1], [1, 1], [2, 1], [0, 2], [1, 2], [2, 2], [0, 3], [1, 3]],
		easing: 'easeInOutExpo',
		time: 500
	});

	$('.navbar-nav .dropdown-menu a').on('click', function() {
		var index = parseInt(this.id.replace('page-nav-btn', ''));
        ascensor.trigger("scrollToStage", index);
    });
});