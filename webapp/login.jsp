<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<%@include file="commons/_head.jspf"%>
</head>
<body>
	<%@include file="commons/_top.jspf"%>

	<div class="container" id="main">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-default content-main">
				<form name="question" method="post" action="/users/login">
				
					<!-- expression에서 자동으로 찾아주기 때문에 삭제 가능 -->
					<%-- <% 
						Object errorMessage = request.getAttribute("errorMessage");
					%> --%>
					<c:if test="${not empty errorMessage}">
					<div class="control-group">
						<label class="error">${errorMessage}</label>
					</div>
					</c:if>
					<div class="form-group">
						<label for="userId">사용자 아이디</label> <input class="form-control"
							id="userId" name="userId" placeholder="User ID">
					</div>
					<div class="form-group">
						<label for="password">비밀번호</label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="Password">
					</div>
					<button type="submit" class="btn btn-success clearfix pull-right">로그인</button>
					<div class="clearfix" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>