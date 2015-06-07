	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			<div class="pages">
                  <c:if test="${page gt total_page_number}">
                      <c:set var="page" value="${total_page_number}"/>
                  </c:if>
                  <c:if test="${page lt 1}">
                      <c:set var="page" value="1"/>
                  </c:if>
                  <c:choose>
                      <c:when test="${page eq 1 and total_page_number gt 1}" >1<a href="#">2</a>
                      <c:if test="${page + 1 ne total_page_number}">...<a href="#">${total_page_number}</a>
                      </c:if>
                      </c:when>
                      <c:when test="${page ne 1 and page ne total_page_number}" >
                          <c:if test="${page - 1 gt 1}">
                          <a href="#">1</a>...
                          </c:if>
                          <a href="#">${page - 1}</a>${page}<a href="#">${page + 1}</a>
                          <c:if test="${page + 1 lt total_page_number}">...<a href="#">${total_page_number}</a>
                          </c:if>
                      </c:when>
                      <c:when test="${page eq total_page_number and total_page_number gt 1}" >
                      <c:if test="${page-1 ne 1}"><a href="#">1</a>...
                      </c:if><a href="#">${page - 1}</a>${page}
                      </c:when>
                      <c:when test="${page eq 1 and total_page_number eq 1}">${page}
                      </c:when>
                  </c:choose>
</div>