<html>
<head>
    <title>Project: ${(project.name)!''} - Test Case: </title>
</head>
<body>
<#include "/common/common.ftl">
<div id="mainNavigation"><a href="/page/projectpage/home/${(project.id)!''}">Project: ${(project.name)!''}</a></div>

<div id="mainTitle">Test Case: ${(testCase.title)!''}</div>

<div id="mainBody">
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
            <div class="col-md-4">${(markdownProcessor.markdown(testCaseInfo.summary))!''}</div>
        </div>
        <div class="row">
            <div class="col-md-2">Pre-Conditions:</div>
            <div class="col-md-4">${(markdownProcessor.markdown(testCaseInfo.preconditions))!''}</div>
        </div>
    </div>

    <h3>Steps</h3>
    <hr/>
    <div id="steps-container" style="padding-left: 10px">

    </div>

    <h3>Executions</h3>
    <hr/>
    <div id="executions-container">

    </div>


    <script>
$(document).ready(function () {
    $.get("/tcm/testcase/testcase/${(testCase.id)!''}/steps", function(data) {
        $('#steps-container').html(data);
    });

    $.get("/tcm/testcase/testcase/${(testCase.id)!''}/executions", function(data) {
        $('#executions-container').html(data);
    });

    var menuItem = $('<li></li>').clone();
    var linkItem = $('<a></a>').clone();
    $(linkItem).html("Edit Case");
    $(linkItem).attr("href","/page/projectpage/testcase/${(testCase.id)!''}/edit");
    $(menuItem).append($(linkItem));
    $('#menu-context').after($(menuItem));

    menuItem = $('<li></li>').clone();
    var linkItem = $('<a></a>').clone();
    $(linkItem).html("Execute Case");
    $(linkItem).attr("href","/page/projectpage/testcase/${(testCase.id)!''}/execute/stub");
    $(menuItem).append($(linkItem));
    $('#menu-context').after($(menuItem));

    menuItem = $('<li></li>').clone();
    linkItem = $('<a></a>').clone();
    $(linkItem).html("Add Case");
    $(linkItem).attr("href","/page/projectpage/testcaseadd/${(project.id)!''}");
    $(menuItem).append($(linkItem));
    $('#menu-context').after($(menuItem));

});
</script>
</div>

</body>
</html>