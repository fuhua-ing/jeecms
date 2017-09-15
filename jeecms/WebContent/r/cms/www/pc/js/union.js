(function(){
	var tabBtns = document.getElementsByClassName("tabBtn");
	for(var i=0;i<tabBtns.length;i++){
		tabBtns[i].onclick = (function(i){
			return function(){
				if(!this.classList.contains("tabActive")){
					document.getElementsByClassName("tabActive")[0].classList.remove('tabActive');
					this.classList.add('tabActive');
					document.getElementsByClassName("on")[0].classList.remove('on');
					document.getElementsByClassName("forTab")[i].classList.add("on");
				}
			}
		})(i)
	}
})()