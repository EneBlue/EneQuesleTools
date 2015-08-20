<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.form.js"></script>
</head>
<body>
	<h2>Hello World!</h2>

	<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
		<input name="localUrl" type="file"><br> <input
			value="Upload File to Server" type="submit">
	</form>
	<div class="progress">
		<div class="bar"></div>
		<div class="percent">0%</div>
	</div>
	<div id="status"></div>
	<script>
		(function() {

			var bar = $('.bar');
			var percent = $('.percent');
			var status = $('#status');

			$('form').ajaxForm(
					{
						beforeSend : function() {
							status.empty();
							var percentVal = '0%';
							bar.width(percentVal)
							percent.html(percentVal);
						},
						uploadProgress : function(event, position, total,
								percentComplete) {
							var percentVal = percentComplete + '%';
							bar.width(percentVal)
							percent.html(percentVal);
						},
						success : function() {
							var percentVal = '100%';
							bar.width(percentVal)
							percent.html(percentVal);
						},
						complete : function(xhr) {
							status.html(xhr.responseText);
						}
					});
		})();
	</script>
</body>
</html>
