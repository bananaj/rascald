    <table class="table table-responsive">
        <thead>
            <th>Description</th>
            <th>Exepected Result</th>
            <th>Results</th>
        </thead>
        <tbody>
            <#list steps as step>
            <tr>
                <td>${wikiModel.toHtml(step.description)}</td>
                <td>${wikiModel.toHtml(step.expected)}</td>
                <td><button class="btn btn-default pull-right" onclick="execute('${step.id}')">(+)</button>
                    <ul>
                        <#list executionsteps as executionstep>
                            <#if step.id == executionstep.stepId>
                                <li>
                                    ${executionstep.resultCode} ${executionstep.user.username} ${executionstep.date?iso_utc}<br/>
                                    ${wikiModel.toHtml(executionstep.comments)}
                                </li>
                            </#if>
                        </#list>
                    </ul>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
