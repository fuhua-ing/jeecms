(function(){
	document.querySelector('.addBtn').onclick = function(){
		var tdStr = '<td><input class="inputSty inSty" type="text" name=""></td>';
		var tr = document.createElement('tr');
		var trStr = '';
		for(var i=0;i<4;i++){
			trStr += tdStr;
		}
		tr.innerHTML = trStr;
		document.querySelector('.tableBox table').append(tr);
	}
})()