<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<script>
	$(document).ready(function(){
		$(".nav-tabs a:first").tab("show");
		$(".triggerRemove").click(function(e){
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>



<h1>${user.name}</h1>





<br><br>

<!-- Nav tabs -->
<ul class="nav nav-tabs">
	<c:forEach items="${user.blogs}" var="blog">
		<li><a href="#blog_${blog.id}" data-toggle="tab">${blog.name}</a></li>
	</c:forEach>
</ul>
<!-- Tab panes -->
<div class="tab-content">
	<c:forEach items="${user.blogs}" var="blog">
		<div class="tab-pane" id="blog_${blog.id}">
			<h1>${blog.name}</h1>
			<p>
			<a href="<spring:url value='/blog/remove/${blog.id}.html' />" class="btn btn-danger triggerRemove">remove blog</a>
			${blog.url}
			</p>
			
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Title</th>
						<th>Link</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${blog.items}" var="item">
						<tr>
							<td>${item.title}</td>
							<td>${item.link}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:forEach>
</div>


<!-- 刪除User Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Remove user</h4>
      </div>
      <div class="modal-body">
       Really remove?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>