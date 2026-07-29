<%@page contentType="text/html; charset=utf-8"%>
<%String sType = request.getParameter("type");%>
<html>
    <head>
        <style type="text/css">
            body {
                background: #7096FA;
                color: #000;
                margin: 0px 0px;
                padding:0px;
                top:0px;
            }
            .expandLeftSide {
                padding-right:3px; padding-left:  5px;float:left;padding-bottom: 3px;padding-top: 6px;
            }
        </style>
    </head>
    <body>
        <%
        String cid = request.getParameter("cid");
        %>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resource/css/dhtmlxtree.css">
        <script  type="text/javascript" src="<%=request.getContextPath()%>/resource/js/dhtmlxcommon.js"></script>
        <script  type="text/javascript" src="<%=request.getContextPath()%>/resource/js/dhtmlxtree.js"></script>
        <script type="text/javascript">
            function closeAllRoots(){
                var rootsAr = tree.getSubItems(0).split(",")
                for(var i=0;i<rootsAr.length;i++){
                    tree.closeAllItems(rootsAr[i])
                }
            }
            function verifykey(oElement,oEvent) {
                if (oEvent.keyCode==13 && oElement.onclick) {
                    oElement.onclick();
                }
            }
            function toggleExpand( clusterid ) {
                img_obj=document.getElementById("i"+clusterid);
                altText = img_obj.alt;
                if(altText.indexOf("All")>0){
                    tree.closeAllItems(0);
                    img_obj.alt   ="Display Headings   Only";
                    img_obj.src ="<%=request.getContextPath()%>/resource/images/but_expandall.gif";
                }
                else {
                    tree.openAllItems(0);
                    img_obj.alt ="Display All Tree";
                    img_obj.src ="<%=request.getContextPath()%>/resource/images/but_collapseall.gif";
                }
            }
        </script>
        <table width=100% height="99%">
            <tr>
                <td width="12%" height="32">
                    <div class="expandLeftSide">
                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td align="left">
                                    <a onkeypress="verifyKey(); return false;" onclick="addCategory(tree.getSelectedItemId()); return false" id="add" href="#">
                                        <img border="0" src="<%=request.getContextPath()%>/resource/images/add.gif" id="iex0" alt="Thêm"/>
                                    </a>
                                </td>
                                <td>&nbsp;</td>
                                <td align="right">
                                    <a onkeypress="verifyKey(); return false;" onclick="editCategory(tree.getSelectedItemId());" id="edit" href="#">
                                        <img border="0" src="<%=request.getContextPath()%>/resource/images/edit.gif" id="iex2" alt="Sửa"/>
                                    </a>
                                </td>
                                <td>&nbsp;</td>
                                <td align="right">
                                    <a onkeypress="verifyKey(); return false;" onclick="delCategory(tree.getSelectedItemId());" id="del" href="#">
                                        <img border="0" src="<%=request.getContextPath()%>/resource/images/del.gif" id="iex1" alt="Xóa"/>
                                    </a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
                <td width="87%" height="32">&nbsp;</td>
            </tr>
            <tr>
                <td valign="top" colspan="2">
                    <div id="treeboxbox_tree" style="width:100%; height:100%;background-color:#f5f5f5;border :1px solid Silver;; overflow:auto;">      </div>
                </td>
            </tr>
        </table>
        <script type="text/javascript">//load tree
            tree = new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
            tree.setImagePath("<%=request.getContextPath()%>/resource/images/");
            tree.setStdImages("iconText.gif","folderOpen.gif","folderClosed.gif");
            tree.loadXML("tree.jsp?type=<%=sType%>");
            tree.setOnClickHandler(function(id){changeContent(id);});
            function changeContent(id){
                type = document.hidden.txtType.value;
                document.hidden.txtId.value = id;
                if(type == id ) id = 0;
                parent.main.location.href = "<%=request.getContextPath()%>/siteManager/sitemanager.jsp?cid=" + id;
            }

            function addCategory(id){
                type = document.hidden.txtType.value;
                if(type == id ) id = 0;
                parent.frames['main'].location.href = "<%=request.getContextPath()%>/siteManager/group/addCat.jsp?cid="+id;
            }

            function delCategory(id){
                type = document.hidden.txtType.value;                
                if(type == id || id == "bai-viet") {
                    alert("Bạn chưa chọn chuyên mục cần xóa!");
                    return;
                }
                if (confirm("Bạn có thực sự muốn xóa chuyên mục có id= " + id + " không ?")){
                    parent.frames['main'].location.href = "<%=request.getContextPath()%>/siteManager/group/delCat.jsp?cid=" + id;
                }
            }

            function editCategory(id){
                type = document.hidden.txtType.value;
                if(type == id || id == "bai-viet") {
                    alert("Bạn chưa chọn chuyên mục cần sửa");
                    return;
                }
                else
                {
                    parent.frames['main'].location.href = "<%=request.getContextPath()%>/siteManager/group/editCat.jsp?cid=" + id;
                }
                
            }
        </script>
        <form action="" name="hidden">
            <input type="hidden" name="txtType" id="txtType" value="<%=sType%>"/>
            <input type="hidden" name="txtId" id="txtId" value="<%=cid%>"/>
        </form>
    </body>
</html>
