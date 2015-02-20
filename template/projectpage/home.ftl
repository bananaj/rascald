<html>
<head>
    <title>Project: ${(project.name)!''}</title>
</head>
<body>
<#include "/common/common.ftl">
<div id="mainTitle">Project: ${(project.name)!''}</div>

<div id="mainBody">
    <div class="container">
        <div class="row">
            <div class="col-md-2">Description:</div>
            <div class="col-md-4">${(project.description)!''}</div>
        </div>
    </div>
    <div id="mainBody">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Test Cases</h3>
        </div>
        <div id="testcases-container" class="panel-body">
            Panel content
        </div>
    </div>


<script>
$(document).ready(function () {
    $.get("/tcm/testcase/project/${(project.id)!''}/testcases", function(data) {
        $('#testcases-container').html(data);
    });

    var menuItem = $('<li></li>').clone();
    var linkItem = $('<a></a>').clone();
    $(linkItem).html("Add Case");
    $(linkItem).attr("href","/page/projectpage/testcaseadd/${(project.id)!''}");
    $(menuItem).append($(linkItem));
    $('#menu-context').after($(menuItem));

});
</script>
</div>

</body>
</html>