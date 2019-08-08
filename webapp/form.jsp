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
			
				<c:set var="actionUrl" value="/users/create" />
				<c:if test="${not empty user.userId}">
					<c:set var="actionUrl" value="/users/update"/>
				</c:if>
				<form name="question" method="post" action="${actionUrl}">
					<div class="form-group">
						<label for="userId">사용자 아이디</label>
						<c:choose> 
						<c:when test="${empty user.userId}">
							<input class="form-control" id="userId" name="userId" placeholder="User ID" value="${user.userId}">
						</c:when>
						<c:otherwise>
							<input class="form-control" id="userId" name="userId" placeholder="User ID" value="${user.userId}" disabled>
						</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="password">비밀번호</label> 
						<input type="password" class="form-control" id="password" name="password" placeholder="Password" value="${user.password}">
					</div>
					<div class="form-group">
						<label for="name">이름</label> 
						<input class="form-control" id="name" name="name" value="${user.name}" placeholder="Name">
					</div>
					<div class="form-group">
						<label for="email">이메일</label> <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="Email">
					</div>
					<c:choose>
					<c:when test="${not empty user.userId}">
						<button type="submit" class="btn btn-success clearfix pull-right">수정 완료</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn btn-success clearfix pull-right">회원가입</button>
					</c:otherwise>
					</c:choose>
					<div class="clearfix" />
				</form>
			</div>
		</div>
	</div>

	<!-- script references -->
	<script src="/js/jquery-2.2.0.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/scripts.js"></script>
</body>
</html>