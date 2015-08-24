<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/kindeditor/lang/zh_CN.js"></script>

</head>
<body>

<form action="/kindeditor" method="post">
<textarea id="document_editor" rows="7" class="form-control valid" cols="200"
				style="width: 100%; resize: none; min-height: 500px;"
				name="content"></textarea>
<br/>
<input type="submit" value="提交" />
</form>
<script type="text/javascript">
	
	KindEditor.ready(function(K) {
        window.editor = K.create('#document_editor', {
        	uploadJson : '${pageContext.request.contextPath}/upload',
            fileManagerJson : '${pageContext.request.contextPath}/upload',
            afterBlur: function(){this.sync();},
            allowFileManager : true,
            imageUploadJson : '${pageContext.request.contextPath}/upload' 
        });
	});
	</script>
</body>
</html>