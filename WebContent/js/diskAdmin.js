/**
 * 
 */


$(document).ready(function(){
	
	
	$(".butplacegenre button").first().click(function() {
		$(".butplacegenre").append(" <div class='inputs'><br>*<input form='general' required type='text' name='genre'//></div>");
	})
	
	$(".butplacegenre button:eq(1)").click(function() {
		$(".butplacegenre .inputs").last().remove();
	})
	
		$(".butplaceactor button").first().click(function() {
		$(".butplaceactor").append(" <div class='inputs'><br>*<input form='general' required type='text' name='name'//>" +
				"</div>");
	})
	
	$(".butplaceactor button:eq(1)").click(function() {
		$(".butplaceactor .inputs").last().remove();
	})
	
});
		
