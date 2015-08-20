<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.2.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<img src="${pageContext.request.contextPath}/image" id="captcha" /> <a href="javascript:;" onclick="changeVerifyCode();">看不清？</a>

<input type="text" id="code" /><button onclick="submitCode();">提交</button>

<script type="text/javascript">

function submitCode(){
	var code = $("#code").val();
	console.log(code);
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/valid",
		data:{code: code},
		success: function (data){
			data = JSON.parse(data);
			if(data.success){
				console.log("验证码正确");
			}else{
				console.log("验证码错误");
			}
		}
	});
}

function changeVerifyCode() {
	var imgSrc = $("#captcha");
	var src = imgSrc.attr("src");
	imgSrc.attr("src", chgUrl(src));
}

//时间戳     
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳     
function chgUrl(url) {
	var timestamp = (new Date()).valueOf();
	if ((url.indexOf("?") >= 0)) {
		url = url + "?timestamp=" + timestamp;
	} else {
		url = url.split("?")[0] + "?timestamp=" + timestamp;
	}

	return url;
}
</script>			
</body>
</html>
