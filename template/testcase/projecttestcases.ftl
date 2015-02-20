<ul class="list-group">
    <#list testcases as testcase>
    <li class="list-group-item">
        <a href="/page/projectpage/testcase/${testcase.id}">${testcase.title}</a>
    </li>
</#list>
</ul>




