<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>完整demo</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ueditor/ueditor.all.min.js"> </script>
    <style type="text/css">
        div{
            width:100%;
        }
    </style>
</head>
<body>
<div>
    <h1>完整demo</h1>
    <form action="${pageContext.request.contextPath}/edit">
		<textarea id="editor" name="test" style="width:500px;height:400px;"></textarea>
		<input type="submit" value="提交" />
    </form>
</div>


<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');

   /*  ue.ready(function() {
        ue.execCommand('${pageContext.request.contextPath}/upload', function(editor) {
                return {
                    'key': 'value'
                };
        });
    }); */
</script>
</body>
</html>