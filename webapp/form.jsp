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
				<c:if test="${isUpdate}">
					<c:set var="actionUrl" value="/users/update"/>
				</c:if>
				<form name="question" method="post" action="${actionUrl}">
					<c:if test="${not empty errorMessage}">
					<div class="control-group">
						<label class="error">${errorMessage}</label>
					</div>
					</c:if>
					<div class="form-group">
						<label for="userId">사용자 아이디</label>
						<c:choose> 
						<c:when test="${isUpdate}">
							<input class="form-control" id="userId" name="userId" placeholder="User ID" value="${user.userId}" disabled>
						</c:when>
						<c:otherwise>
							<input class="form-control" id="userId" name="userId" placeholder="User ID" value="${user.userId}">
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
					<c:set var="btnText" value="회원가입" />
					<c:if test="${isUpdate}">
						<c:set var="btnText" value="수정완료"/>
					</c:if>
					<button type="submit" class="btn btn-success clearfix pull-right">${btnText}</button>
					
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