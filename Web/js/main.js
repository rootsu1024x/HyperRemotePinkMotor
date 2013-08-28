$(document).ready(function(){
	$("input[name=NYANNYAN]").click(function(){
		$.get("http://example.com/cgi-bin/input.cgi?power="+$("input[name=POWER]").val(),function(){
			console.log("NYAN");
		});
	});
});
