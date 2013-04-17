<%@page contentType="text/html, charset=utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<c:forEach var="h" items="${companyStaffList}">
	<li><input type="checkbox" ${(h.needToShow)? "checked":""} onChange="setNeedToShow(this.checked, '${f:h(h.key)}')">${f:h(h.sname)} ${f:h(h.fname)} (${f:h(h.kanaSname)} ${f:h(h.kanaFname)})
</c:forEach>
<c:forEach var="d" items="${departmentList}">
	<c:if test="${department==d}">
		<li>${f:h(d)}
		<ul>
		<c:forEach var="h" items="${departmentStaffList}">
			<li><input type="checkbox" ${(h.needToShow)? "checked":""} onChange="setNeedToShow(this.checked, '${f:h(h.key)}')">${f:h(h.sname)} ${f:h(h.fname)} (${f:h(h.kanaSname)} ${f:h(h.kanaFname)})
		</c:forEach>
		<c:forEach var="e" items="${sectionList}">
			<c:if test="${section==e}">
				<li>${f:h(e)}
				<ul>
				<c:forEach var="h" items="${sectionStaffList}">
					<li><input type="checkbox" ${(h.needToShow)? "checked":""} onChange="setNeedToShow(this.checked, '${f:h(h.key)}')">${f:h(h.sname)} ${f:h(h.fname)} (${f:h(h.kanaSname)} ${f:h(h.kanaFname)})
				</c:forEach>
				<c:forEach var="f" items="${subsectionList}">
					<c:if test="${subsection==f}">
						<li>${f:h(f)}
						<ul>
						<c:forEach var="h" items="${employeeList}">
							<li><input type="checkbox" ${(h.needToShow)? "checked":""} onChange="setNeedToShow(this.checked, '${f:h(h.key)}')">${f:h(h.sname)} ${f:h(h.fname)} (${f:h(h.kanaSname)} ${f:h(h.kanaFname)})
						</c:forEach>
						</ul>
					</c:if>
					<c:if test="${subsection!=f}">
						<li><a href="/admin?department=${department}&section=${section}&subsection=${f:h(f)}">${f:h(f)}</a>
					</c:if>
				</c:forEach>
				</ul>
			</c:if>
			<c:if test="${section!=e}">
				<li><a href="/admin?department=${department}&section=${f:h(e)}">${f:h(e)}</a>
			</c:if>
		</c:forEach>
		</ul>
	</c:if>
	<c:if test="${department!=d}">
		<li><a href="/admin?department=${f:h(d)}">${f:h(d)}</a>
	</c:if>
</c:forEach>
