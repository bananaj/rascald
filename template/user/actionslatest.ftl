<table class="table table-condensed">
    <thead>
        <th>Date</th>
        <th>Description</th>
    </thead>
    <tbody>

<#list actionslatest as action>
        <tr>
            <td>${action.date}</td>
            <td>${m.s('action_' + action.actionCd)} ${m.s('context_' + action.contextCd)} </td>
        </tr>
</#list>
    </tbody>
</table>