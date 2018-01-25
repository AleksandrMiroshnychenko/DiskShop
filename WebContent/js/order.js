var dif = false;
$(document).ready(function() {

	var cost = ${priceSum};
	var del = ${deliveryPrice};
	var sum = cost + del;
	$(".temporary").hide();
	$("#message").html("Сумма заказа: " + cost);
	$(".temporary :input").prop('required', false);
	
	$("#check input").click(function() {

		if (dif == false) {
			$(".temporary :input").prop('required', true);
			$("#message").html("Сумма заказа: " + sum);
			$(".temporary").show();

			/*
			 * $("#checkCol").after().append("<tr class='temporary'><td >Область/регион</td><td>
			 * <input required='required' id="region" type='text' name='region'></td></tr>" + "<tr class='temporary'><td >Город:</td><td>
			 * <input required='required' type='text' id="city" name='city'></td></tr>" + "<tr class='temporary'><td>Адрес:</td><td>
			 * <input required='required' type='text' id="address"
			 * name='address'></td></tr>"+ "<tr class='temporary'><td>Почтовый
			 * индекс:</td><td> <input required='required' type='text'
			 * id="postIndex" name='postIndex'></td></tr>"+ "<tr class='temporary'><td colspan='2'>стоимость
			 * доставки: " + del+ "</td></tr>");
			 */
			dif = true;
		} else {
			$(".temporary :input").prop('required', false);
			$("#message").html("Сумма заказа: " + cost);
			$(".temporary").hide();
			dif = false;

		}
	});

})

function toDelivery() {

	if (dif == false)
		return true;

	var region = document.getElementById("region").value;
	var city = document.getElementById("city").value;
	var address = document.getElementById("address").value;
	var postIndex = document.getElementById("postIndex").value;
	var status = document.getElementById("attent");
	var lre = /^[0-9]{5}$/;
	var pre = /^[а-яА-ЯёЁ]{1,40}$/;
	var are = /^[а-яА-ЯёЁ]{1,40}\s{1}\d+$/;

	if (!pre.test(region)) {
		status.innerHTML = "Название области должно состоять только из кириллических символов";
		return false;
	} else if (!pre.test(city)) {
		status.innerHTML = "Название города должно состоять только из кириллических символов";
		return false;
	} else if (!are.test(address)) {
		status.innerHTML = "Адрес должен состоять из названия улицы и номера дома";
		return false;
	} else if (!lre.test(postIndex)) {
		status.innerHTML = "Индекс должен состоять из 5 цифер";
		return false;
	} else {
		return true;
	}
}

function toOrder() {
	toDelivery();
	if (toDelivery()) {

		var mail = document.getElementById("mail").value;
		var lastname = document.getElementById("lastname").value;
		var firstname = document.getElementById("firstname").value;
		var surname = document.getElementById("surname").value;
		var phone = document.getElementById("phone").value;
		var status = document.getElementById("attent");
		var mre = /^[^\s()<>@,;:\/]+@\w[\w\.-]+\.[a-z]{2,}$/;
		var lre = /^(\+?\d{1,2})?\d{10}$/;
		var pre = /^[а-яА-Я]{2,40}$/;

		if (!pre.test(lastname)) {
			status.innerHTML = "Фамилия должна состоять только из кириллических символов";
			return false;
		} else if (!pre.test(firstname)) {
			status.innerHTML = "Имя должно состоять только из кириллических символов";
			return false;
		} else if (!pre.test(surname)) {
			status.innerHTML = "Отчество должно состоять только из кириллических символов";
			return false;
		} else if (!lre.test(phone)) {
			status.innerHTML = "Номер мобильного указан некорректно";
			return false;
		} else if (!mre.test(mail)) {
			status.innerHTML = "E-mail неправильный";
			return false;
		} else {
			return true;
		}
	} else
		return false;
}
