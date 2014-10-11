<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/stylesheet.css">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
		<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="script.js"></script>
		<title>开宝箱</title>
	</head>
	<body>
		<div class="inner" style="background-image: url(pic/background.jpg); width=1336; height:768">
			<div class="content">
				<div class="container">
					<div class="row">
						
						<div class="col-md-2 col-md-offset-5">
							<div class="result_box" id="result">
								抽奖结果
							</div>
						</div>
						
					</div>
					<div class="row">
						<div class="col-md-2 col-md-offset-6">
							<button type="button" class="btn btn-success btn-lg btn-block" id="start" disabled="disabled">开始</button>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-success btn-lg btn-block" id="end" disabled="disabled">结束</button>
						</div>

					</div>
					
				</div>
			</div>
		</div>
		


	</body>
</html>