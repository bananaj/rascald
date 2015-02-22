<html>
<head>
    <title>Project: ${(project.name)!''} - Test Case: </title>
</head>
<body>
<#include "/common/common.ftl">
<div id="mainNavigation"><a href="/page/projectpage/home/${(project.id)!''}">Project: ${(project.name)!''}</a></div>

<div id="mainTitle">Test Case Executed: ${(testCase.title)!''} <a href="/page/projectpage/testcaseexecute/${(testCase.id)!''}">(+) Execute Again</a></div>

<div id="mainBody">
    <div class="container">
        <div class="row">
            <div class="col-md-2">Project:</div>
            <div class="col-md-4"><a href="/page/projectpage/home/${(project.id)!''}">${(project.name)!''}</a></div>
        </div>
        <div class="row">
            <div class="col-md-2">Execution of (Title):</div>
            <div class="col-md-4"><a href="/page/projectpage/testcase/${(testCase.id)!''}">${(testCase.title)!''}</a></div>
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
            <div class="col-md-2">User:</div>
            <div class="col-md-4">${(execution.user.username)!''}</div>
        </div>
        <div class="row">
            <div class="col-md-2">Date:</div>
            <div class="col-md-4">${(execution.date)!''}</div>
        </div>
        <div class="row">
            <div class="col-md-2">Comments:</div>
            <div class="col-md-4">${(wikiModel.toHtml(execution.comments))!''}</div>
        </div>
    </div>
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
    $.get("/tcm/execution/execution/${(execution.id)!''}/steps", function(data) {
        $('#steps-container').html(data);
    });
});

function execute(stepId) {
    $.get("/tcm/execution/execution/${(execution.id)!''}/" + stepId + "/onestep", function(data) {
        $('#formExecuteStep').html(data);
        $('#modalExecuteStep').modal('show');
    });
}

function saveExecuteStep() {

    $.post("/tcm/execution/execution/onestep/save", $('#formExecuteStep').serialize())
    .success(function(){
        $.get("/tcm/execution/execution/${(execution.id)!''}/steps", function(data) {
            $('#steps-container').html(data);
            $('#modalExecuteStep').modal('hide');
        });
    });
}
</script>


    <!-- Modal -->
    <div class="modal fade" id="modalExecuteStep" tabindex="-1" role="dialog" aria-labelledby="modalExecuteStep" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <div class="modal-body">
                    <form name="formExecuteStep" id="formExecuteStep">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" onclick="saveExecuteStep()">Commit</button>
                </div>
            </div>
        </div>
    </div>



</div>

</body>
</html>