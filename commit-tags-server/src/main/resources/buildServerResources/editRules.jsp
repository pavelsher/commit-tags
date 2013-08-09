<%@include file="/include.jsp"%>
<jsp:useBean id="currentProject" type="jetbrains.buildServer.serverSide.SProject" scope="request"/>
<jsp:useBean id="rulesBean" type="org.jetbrains.buildServer.commitTags.web.TagRulesBean" scope="request"/>
<bs:linkScript>
    ${teamcityPluginResourcesPath}/js/editRules.js
</bs:linkScript>
<bs:linkCSS>
    ${teamcityPluginResourcesPath}/css/editRules.css
</bs:linkCSS>

<div>
<c:if test="${empty rulesBean.tagRules}">
    There are no change tag rules defined.
</c:if>
</div>

<c:if test="${not empty rulesBean.tagRules}">
<l:tableWithHighlighting className="settings" highlightImmediately="true">
    <tr>
        <th>Tag Name</th>
        <th colspan="3">Rule</th>
    </tr>
    <c:forEach items="${rulesBean.tagRules}" var="r">
        <c:set var="onclick" value="onclick='BS.EditRulesDialog.show()'"/>
        <tr>
            <td class="highlight" ${onclick}><c:out value="${r.tag}"/></td>
            <td class="highlight" ${onclick}><c:out value="${r.description}"/></td>
            <td class="edit highlight"><a href="#" ${onclick}>edit</a></td>
            <td class="edit highlight"><a href="#">delete</a></td>
        </tr>
    </c:forEach>
</l:tableWithHighlighting>
</c:if>

<forms:addButton onclick="BS.EditRulesDialog.show(); return false">Add new rule</forms:addButton>

<c:url var="action" value="/admin/commit-tags/editRules.html"/>
<bs:modalDialog formId="editTagRuleForm" title="Edit Tag Rule" action="${action}" saveCommand="" closeCommand="BS.EditRulesDialog.close()">
    <table>
        <tr>
            <th><label for="regexpPattern">File Regexp Pattern: <l:star/></label></th>
            <td>
                <forms:textField className="longField" name="regexpPattern"/>
                <span class="error" id="error_regexpPattern"></span>
            </td>
        </tr>
        <tr>
            <th><label for="tagNameField">Tag Name: <l:star/></label></th>
            <td>
                <forms:textField className="longField" name="tagNameField"/>
                <span class="error" id="error_tagNameField"></span>
            </td>
        </tr>
    </table>
    <div class="saveButtonsBlock">
        <forms:cancel onclick="BS.EditRulesDialog.close()"/>
        <input type="hidden" name="projectId" value="${currentProject.externalId}"/>
        <forms:submit name="editTagRule" label="Save"/>
        <forms:saving id="saving_tagRule"/>
    </div>
</bs:modalDialog>
