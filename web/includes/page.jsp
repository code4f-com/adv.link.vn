<%@page pageEncoding="UTF-8"%>
<%
    if (totalPage > 1) {

%>
<div align="center" class="pagination">
    <%
        if (currentPage != 1) {
    %>
    <a  href = "<%=response.encodeURL(pageURL + "page=" + 1)%>" >&lt;&lt; First</a>
    <%
        }
        if (currentPage == 1) {
    %>
    <span class="disabled">&lt;&lt; prev</span>
    <%    } else {
    %>
    <a  href = "<%=response.encodeURL(pageURL + "page=" + (currentPage - 1))%>" >&lt;&lt; prev</a>
    <%            }
    %>
    <span class="current"><%=currentPage%></span>
    <%
        if (currentPage + 1 <= totalPage) {
    %>
    <a href="<%=response.encodeURL(pageURL + "page=" + (currentPage + 1))%>"><%=currentPage + 1%></a>
    <%
        }
        if (currentPage + 2 <= totalPage) {
    %>
    <a href="<%=response.encodeURL(pageURL + "page=" + (currentPage + 2))%>"><%=currentPage + 2%></a>
    <%
        }
        if (totalPage - 2 > currentPage + 2) {
    %>
    ...<a href="<%=response.encodeURL(pageURL + "page=" + (totalPage - 2))%>"><%=totalPage - 2%></a>
    <%
        }
        if (totalPage - 1 > currentPage + 3) {
    %>
    <a href="<%=response.encodeURL(pageURL + "page=" + (totalPage - 1))%>"><%=totalPage - 1%></a>
    <%
        }
        if (totalPage > currentPage + 4) {
    %>
    <a href="<%=response.encodeURL(pageURL + "page=" + totalPage)%>"><%=totalPage%></a>
    <%
        }
        if (currentPage < totalPage) {
    %>
    <a href="<%=response.encodeURL(pageURL + "page=" + (currentPage + 1))%>">next &gt;&gt;</a>
    <%        } else {
    %>
    <span class="disabled">next &gt;&gt;</span>
    <%        }
        if (currentPage != totalPage) {
    %>
    <a href="<%=response.encodeURL(pageURL + "page=" + totalPage)%>">Last &gt;&gt;</a>
    <%}%>
</div>
<%
    }
%>
