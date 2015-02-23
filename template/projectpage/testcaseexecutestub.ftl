<html>
<head>
    <title>Project: ${(project.name)!''} - Execute Test Case</title>
</head>
<body>
<#include "/common/common.ftl">
<div id="mainNavigation"><a href="/page/projectpage/home/${(project.id)!''}">Project: ${(project.name)!''}</a></div>

<div id="mainTitle">Execute Test Case: ${(testCase.title)!''}</div>

<div id="mainBody">
    <form action="/page/projectpage/testcaseexecutesave/${(testCase.id)!''}" method="post">
    <div class="container">
        <div class="row">
            <div class="col-md-2">Project:</div>
            <div class="col-md-4"><a href="/page/projectpage/home/${(project.id)!''}">${(project.name)!''}</a></div>
        </div>
        <div class="row">
            <div class="col-md-2">Title:</div>
            <div class="col-md-4">${(testCase.title)!''}</div>
        </div>
        <div class="row">
            <div class="col-md-2">Summary:</div>
            <div class="col-md-4">${(testCaseInfo.summary)!''}</div>
        </div>
        <div class="row">
            <div class="col-md-2">Pre-Conditions:</div>
            <div class="col-md-4">${(testCaseInfo.preconditions)!''}</div>
        </div>
        <div class="row">
            <div class="col-md-2">Comments</div>
            <div class="col-md-4"><textarea name="comments" id="comments" class="form-control"></textarea></div>
        </div>
        <button class="btn btn-primary" type="submit">Start</button>

    </div>
    </form>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Steps</h3>
        </div>
        <div id="steps-container" class="panel-body">
            Panel content
        </div>
    </div>


    <script>
$(document).ready(function () {
    $.get("/mgr/testcase/testcase/testCase.id)!''}/steps", function(data) {
        $('#steps-container').html(data);
    });
});
</script>

</div>

</body>
</html>