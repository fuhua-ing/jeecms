(function(){
	setTimeout(function(){
		location.href = '';
	},5000)
	var num = 5;
	var time = setInterval(function(){
		num --;
		document.querySelector('.time').innerHTML = num;
		if(num == 0){
			clearInterval(time);
			location.href = '';
		}
	},1000)
})()