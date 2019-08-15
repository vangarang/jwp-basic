<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<%@include file="/commons/_head.jspf"%>
</head>
<body>
	<%@include file="/commons/_top.jspf"%>
	
	<div class="container" id="main">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-default content-main">
				<form name="question" method="post" action="/user/create">
					<div class="form-group">
						<label for="userId">아이디</label>
						<div class="controls">${user.userId}</div>
						<input type="hidden" value="${user.userId}"/>
					</div>
					<div class="form-group">
						<label for="password">비밀번호</label> <input type="password"
							class="form-control" id="password" value="${user.password}" name="password"
							placeholder="Password">
					</div>
					<div class="form-group">
						<label for="name">이름</label> <input class="form-control"
							value="${user.name}" id="name" name="name" placeholder="Name">
					</div>
					<div class="form-group">
						<label for="email">이메일</label> <input type="email"
							class="form-control" id="email" name="email" value="${user.email}" placeholder="Email">
					</div>
					<button type="submit" class="btn btn-success clearfix pull-right">수정 완료</button>
					<div class="clearfix" />
				</form>
			</div>
		</div>
	</div>

	<!-- script references -->
	<script src="../js/jquery-2.2.0.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/scripts.js"></script>
</body>
</html>