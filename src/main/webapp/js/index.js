

$('.form').find('input, textarea').on('keyup blur focus', function(e) {

	var $this = $(this), label = $this.prev('label');

	if (e.type === 'keyup') {
		if ($this.val() === '') {
			label.removeClass('active highlight');
		} else {
			label.addClass('active highlight');
		}
	} else if (e.type === 'blur') {
		if ($this.val() === '') {
			label.removeClass('active highlight');
		} else {
			label.removeClass('highlight');
		}
	} else if (e.type === 'focus') {

		if ($this.val() === '') {
			label.removeClass('highlight');
		} else if ($this.val() !== '') {
			label.addClass('highlight');
		}
	}

});

$('.tab a').on('click', function(e) {

	e.preventDefault();

	$(this).parent().addClass('active');
	$(this).parent().siblings().removeClass('active');

	target = $(this).attr('href');

	$('.tab-content > div').not(target).hide();

	$(target).fadeIn(600);

});

function submitDetailsForm() {
	$("#regform").submit(function(e) {
		e.preventDefault();
		var fname = $("input[name=fname]").val();
		console.log("uname" + fname);

		var d = {
			"uname" : fname,
			"lname" : $("input[name=lname]").val(),
			"telnum" : $('input[type="tel"]').val(),
			"email" : $("input[name=email]").val(),
			"pwd" : $("input[name=pwd]").val()
		};
		var val = JSON.stringify(d);
		console.log("val is " + val);
		$.ajax({
			type : "POST",
			url : "/apartmentDemo/rest/auth/signup",
			contentType : "application/json; charSet=UTF-8",
			crossDomain : true,
			data : val,
			dataType : "json"
		}).done(function(data) {
			
			console.log("success" + JSON.stringify(data));

		}).fail(function(data) {
			console.log("failure " + JSON.stringify(data));
		});

	});
}

function loginAuth() {
	$("#loginform").submit(function(e) {
		e.preventDefault();
		var auth = {
			"email" : $("input[name=emailid]").val(),
			"salt" : $("input[name=salt]").val()
		};
		console.log("auth is " + JSON.stringify(auth));

		$.ajax({
			type : "POST",
			url : "/apartmentDemo/rest/auth/login",
			contentType : "application/json; charSet=UTF-8",
			crossDomain : true,
			data : JSON.stringify(auth),
			dataType : "json"
		}).done(function(data) {
			console.log("success" + JSON.stringify(data));
			console.log("data.email"+data.email)
			window.location.href = "/apartmentDemo/UserLogin.html?uname="+data.name;

		}).fail(function(data) {
			console.log("failure " + JSON.stringify(data));
		});

	});
}