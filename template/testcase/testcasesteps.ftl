<table class="table table-responsive">
    <thead>
        <th>Description</th>
        <th>Expected Result</th>
        <th></th>
    </thead>
    <tbody>
    <#list steps as step>
        <tr>
            <td>${markdownProcessor.markdown(step.description)}</td>
            <td>${markdownProcessor.markdown(step.expected)}</td>
            <td></td>
        </tr>

    </#list>
    </tbody>
</table>




