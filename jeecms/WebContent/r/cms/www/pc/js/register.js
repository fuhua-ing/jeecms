
function getMessage(e){
	var username = $("#username").val();
	var myreg = /^((1[0-9]{2})+\d{8})$/;
	if(myreg.test(username)){
		resetCode(); //倒计时
	}else{
		$('username').focus();
	}
	if(!myreg.test(username)){
		/*弹出框('请输入有效的手机号码');*/
		$('#J_getCode').click(function(){
			$('.cover-level').css('display','block');
			$('.alert-text').text('请输入正确的手机号码');
		})
		$('.close-dialog').click(function(){
			$('.cover-level').css('display','none');
		})
		return;
	}
	
	//倒计时
	function resetCode(){
		$('#J_getCode').hide();
		$('#J_second').html('60');
		$('#J_resetCode').show();
		var second = 60;
		var timer = null;
		timer = setInterval(function(){
			second -= 1;
			if(second >0 ){
				$('#J_second').html(second);
			}else{
				clearInterval(timer);
				$('#J_getCode').show();
				$('#J_resetCode').hide();
			}
		},1000);
	}
	var value={};
	value.username = username;
	value.captcha= $("#captcha").val();
	
	if(null==captcha||captcha==""){
		alert("验证码不能为空");
	}
	
	$.ajax({
		url : '/register_getmessage.jspx',
		type : 'post',
		data : value,
		async : false, // 默认为true 异步
		error : function(data) {
			if(data=="error.phone.nulls"){
				alert("手机号码是空的");
				refreshCa();
			}else if(data=="error.phone.exsit"){
				$('.cover-level').css('display','block');
				$('.alert-text').text('手机号码已存在');
				$('.close-dialog').click(function(){
				$('.cover-level').css('display','none');
				})
				refreshCa();
			}else if(data=="error.phone.wrong"){
				alert("请输入正确的手机号码");
				refreshCa();
			}else if(data=="error.captcha"){
				$('.cover-level').css('display','block');
				$('.alert-text').text('验证码错误');
				$('.close-dialog').click(function(){
				$('.cover-level').css('display','none');
				})
				refreshCa();
			}
		},
		success : function(data) {
			if(data=="error.phone.nulls"){
				alert("手机号码是空的");
				refreshCa();
			}else if(data=="error.phone.exsit"){
				$('.cover-level').css('display','block');
				$('.alert-text').text('手机号码已存在');
				$('.close-dialog').click(function(){
				$('.cover-level').css('display','none');
				})
				refreshCa();
			}else if(data=="error.phone.wrong"){
				alert("请输入正确的手机号码");
				refreshCa();
			}else if(data=="error.captcha"){
				$('.cover-level').css('display','block');
				$('.alert-text').text('验证码错误');
				$('.close-dialog').click(function(){
				$('.cover-level').css('display','none');
				})
				refreshCa();
			}
		}
	});
}

function refreshCa(){
	$('#refresh').attr('src','/captcha.svl');
}


$("#myforms").submit(function() {

	var username = $("#username").val();
	var captcha = $("#captcha").val();
	var message = $("#message").val();
	var pdword_first = $("#passwd").val();
	var pdword_confirm = $("#password_confirm").val();

	if(null==username||username==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('请输入手机号码');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	if(username.search(/^1[3578][0-9]{9}$/)==-1){
		$('.cover-level').css('display','block');
		$('.alert-text').text('请输入正确的手机格式');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return false;
	}
	if(null==captcha||captcha==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('请输入验证码');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	
	if(null==message||message==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('请输入短信验证码');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	
	if(null==pdword_first||pdword_first==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('请输入密码');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		});
		event.preventDefault();
		return;
	}
	
	if(null==pdword_confirm||pdword_confirm==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('请再次输入密码');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	
	if(pdword_confirm!=pdword_first){
		$('.cover-level').css('display','block');
		$('.alert-text').text('两次密码不一致');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	
	var value = {
		username : $("#telNumber").val(),
		captcha : $("#captcha").val(),
		message : $("#message").val(),
		passwd : $("#password").val()
	};

	
});
$("#registerTrue").click(function() {

	var email = $("#email").val();

	var personName = $("#personName").val();
	var sex = $("#sex").val()=="1" ? true:false;
	var graduate = $("#graduate").val();
	var localPhone = $("#localPhone").val();
	var qq = $("#qq").val();
	var msn = $("#msn").val();
	var companyName = $("#companyName").val();
	var companyCatagroy = $("#companyCatagroy").val();
	var adress_province = $("#companyAddressProvince").val();
	var adress_city = $("#companyAddressCity").val();
	var adressDetail = $("#adressDetail").val();
	var companyPosition = $("#companyPosition").val();
	var companyIntroduce = $("#companyIntroduce").val();
	var personalDetail = $("#personalDetail").val();
	var checkboxId = $("#checkboxId").val();
	var username = $("#username").val();
	var passwd = $("#passwd").val();
	var provinceName = $("#companyAddressProvince :selected").text();
	var cityName = $("#companyAddressCity :selected").text();

	if(personName==null||personName==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('姓名不能为空');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	if(localPhone==null||localPhone==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('固定电话不能为空');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	if(qq==null||qq==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('QQ不能为空');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	if(email==null||email==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('电子邮箱不能为空');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	if(companyName==null||companyName==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('公司名称不能为空');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	if(companyPosition==null||companyPosition==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('职务不能为空');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	if(companyCatagroy==null||companyCatagroy==""){
		$('.cover-level').css('display','block');
		$('.alert-text').text('请选择一个职业');
		$('.close-dialog').click(function(){
		$('.cover-level').css('display','none');
		})
		event.preventDefault();
		return;
	}
	var value = {
		
			realname : personName,
			gender : sex,
			education : graduate,
			phone : localPhone,
			qq : qq,
			msn:msn,
			companyName : companyName,
			companyType : companyCatagroy,
			companyAddressProvince : adress_province,
			companyAddressCity : adress_city,
			companyAddressDetail : adressDetail,
			companyPosition: companyPosition,
			companyIntro:companyIntroduce,
			intro:personalDetail,
			provinceName:provinceName,
			cityName:cityName,
			userImg:"",
			email:email
	};
	
var fileObj = document.getElementById("userImg").files[0]; 
/*var filename=getFileName($("#userImg").val());*/
if(fileObj!=undefined){
	var filename=getFileName($("#userImg").val());
	function getFileName(o){
	    var pos=o.lastIndexOf("\\");
	    return o.substring(pos+1);  
	}
	 var formData = new FormData();
	 formData.append("filename", filename); // 文件对象  
	 formData.append("uploadFile", fileObj); // 文件对象
	 formData.append("mark",true);
	 formData.append("imgType","userImg");

$.ajax({
		url : '/member/o_upload_image.jspx',
		type : 'post',
		data : formData,
		async : false, // 默认为true 异步
		cache: false,//上传文件无需缓存 
		processData: false,//用于对data参数进行序列化处理 这里必须false
		contentType: false, //必须 
		error : function() {
			alert('error');
		},
		success : function(data) {
			alert(data);
			registerUser(data,value);
		}
	})}else{
		registerUser("",value);
	};;
	
});

function registerUser(path,value){
	value.userImg=path;
	$.ajax({
		url : '/register.jspx',
		type : 'post',
		data : value,
		async : false, // 默认为true 异步
		error : function(data) {
			alert('error '+data);
		},
		success : function(data) {
			window.location.href="/register_success.jspx";
		}
	});
}
