function Validate() {

	var email = document.getElementById("email").value;
	var login = document.getElementById("login").value;
	var password = document.getElementById("password").value;
	var password2 = document.getElementById("password2").value;
	var status = document.getElementById("attent");
	var mre = /^[^\s()<>@,;:\/]+@\w[\w\.-]+\.[a-z]{2,}$/;
	var lre = /^[a-zA-Z0-9_-]{4,24}$/;
	var pre = /^[a-zA-Z0-9_-]{6,24}$/;

	if (!lre.test(login))
		status.innerHTML = "Логин должен состоять минимум из 4 символов";
	else if (!pre.test(password)) {
		status.innerHTML = "Пароль должен состоять минимум из 6 символов";
		return false;
	} else if (!pre.test(password2)) {
		status.innerHTML = "Пароль должен состоять минимум из 6 символов";
		return false;
	}
	else if (!mre.test(email)) {
		status.innerHTML = "E-mail неправильный";
		return false;
	} else {
		return true;
	}

}