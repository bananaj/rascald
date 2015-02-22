<table class="table table-responsive">
    <thead>
        <th>Description</th>
        <th>Expected Result</th>
        <th></th>
    </thead>
    <tbody>
    <#list steps as step>
        <#if step?has_content>
        <tr>
            <td>${wikiModel.toHtml(step.description)}</td>
            <td>${wikiModel.toHtml(step.expected)}</td>
            <td></td>
        </tr>
        </#if>

    </#list>
    </tbody>
</table>




