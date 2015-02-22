<ul class="list-group">
    <#list executions as execution>
    <li class="list-group-item">
        <a href="/page/projectpage/execution/${execution.id}">${execution.date}</a> User: ${execution.user.username} Status: ${execution.resultCode} ${execution.statusCode}<br/>
        Comments: ${wikiModel.toHtml(execution.comments)}
    </li>
    </#list>
</ul>




