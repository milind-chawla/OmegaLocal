<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${links}" var="link">
	<li id='<c:out value="${link['id']}" />'><a href="${link['path']}">${link['name']}</a></li>	
</c:forEach>

<!-- <c:out value="${links.toString()}" /> -->
