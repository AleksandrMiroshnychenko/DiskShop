
function userValidate(order) {

    	var status = document.getElementById("attent");
    	var cre = /^[0-9]{1,20}$/;
    	var dre = /^[0-9]{1,18}$/;
    if (!cre.test(count)) status.innerHTML = "Недопустипое количество заказываемого товара";
    else if (!dre.test(duration)) status.innerHTML = "Недопустимая длительность аренды книги";
    //else getElementById(order).submit();
   }


function countPrice(){
	
	var ch =  ${disk.sellable};
	
	if(ch != 1){
		var status = $("#attent").last();
		var duration = $(".pages #number").first().val();
		var count = $(".pages #number").last().val();
		var prod = duration * count * ${perMonth};
	}
    status.html("Стоимость аренды без доставки: " + prod);
};

$(document).ready(countPrice());