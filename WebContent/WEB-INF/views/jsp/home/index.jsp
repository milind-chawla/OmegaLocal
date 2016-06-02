<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Home</title>
    <meta name='description' content=''>
  </head>
  <body>
  	<c:set var="i" value="0" scope="page"></c:set>
    <div class="row">
    	<c:forEach items="${links}" var="link">
    		<div class="col-md-4">
				<div class="alert alert-warning text-center" role="alert">
	  				<a id="${link['id']}" href="${link['path']}" class="alert-link">${link['name']}</a>
	  			</div>
	  		</div>
	  		<c:set var="i" value="${i + 1}"></c:set>
	  		<c:if test="${i % 3 == 0}">
	  			</div>
	  			<div class="row"> 
	  		</c:if>
		</c:forEach>
	</div>
  </body>
</html>
