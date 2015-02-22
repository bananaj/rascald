<html>
<head>
    <title>Project: ${project.name} - Add Test Case</title>
</head>
<body>
<#include "/common/common.ftl">
<div id="mainTitle">Project: ${project.name} - Add Test Case</div>

<div id="mainBody">
<#assign title=""/>
<#assign summary=""/>
<#assign preconditions=""/>
<#assign cancelurl="/page/projectpage/home/${project.id}"/>


<#if testCase?has_content>
    <#assign title>${testCase.title}</#assign>
    <#assign cancelurl="/page/projectpage/testcase/${testCase.id}"/>
</#if>

<#if testCaseInfo?has_content>
    <#assign summary>${testCaseInfo.summary}</#assign>
    <#assign preconditions>${testCaseInfo.preconditions}</#assign>
</#if>


    <style>
        .form-control { width: 100%; }
    </style>
    <form method="post" action="/page/projectpage/testcasesave" id="formTestCase">
        <input type="hidden" name="projectId" value="${project.id}"/>

        <#if testCase?has_content>
            <input type="hidden" name="testCaseId" value="${testCase.id}"/>
        </#if>


        <div class="container">
            <div class="row">
                <div class="input-group col-md-12">
                    <input type="text" class="form-control" placeholder="Title" name="title" id="title" minlength="2" required value="${title}"/>
                </div>
            </div>
            <div class="row">
                    <label class="col-md-2">Summary:</label>
                    <textarea class="form-control col-md-10" name="summary" id="summary">${summary}</textarea>
            </div>

            <div class="row">
                <label class="col-md-2">Pre-Conditions:</label>
                <textarea class="form-control col-md-10" name="preconditions" id="preconditions">${preconditions}</textarea>
            </div>

            <table id="steps-container" class="table table-responsive">
                <thead>
                    <th>Description</th>
                    <th>Expected Result</th>
                    <th><button class="btn btn-default" onclick="addStep()" type="button">(+)</button></th>
                </thead>
                <tbody>

                </tbody>
            </table>

            <div class="row">
                <button class="btn btn-primary" type="submit">Save</button>
                <a class="btn btn-primary" href="${cancelurl}">Cancel</a>
            </div>
        </div>
    </form>

    <div id="step-hidden" style="display:none">
        <div class="step">
            <textarea class="step-description" rows="4" style="width: 100%" required minlength="2"></textarea>
            <textarea class="step-expected" rows="4" style="width: 100%"></textarea>
            <input type="hidden" class="step-order"/>
            <input type="hidden" class="step-id"/>

            <a class="step-increment">(+)</a>
            <a class="step-decrement">(-)</a>
        </div>
    </div>

    <script>
var stepNumber = 0;
$(document).ready(function () {

<#if testCaseInfo?has_content>
    <#list testCaseInfo.getSteps() as step>
        <#if step?has_content>
        addStep('${step.id}', '${step.description?js_string}','${step.expected?js_string}');
        </#if>
    </#list>
<#else>
    addStep('','','');
</#if>


    $('#formTestCase').validate();
});

function addStep(id, description, expected) {
    stepNumber = stepNumber + 1;
    var stepHtml = $('#step-hidden').html();
    var step = $('<tr class="step"/>').clone();
    $(step).attr("step", stepNumber);

    var el = $('#step-hidden').find('.step-description').clone();
    $(el).attr('id', 'stepdescription.' + stepNumber);
    $(el).attr('name', 'stepdescription.' + stepNumber);
    $(el).val(description);
    var elContainer = $('<td/>').clone();
    $(elContainer).append($(el));
    $(step).append($(elContainer));

    el = $('#step-hidden').find('.step-expected').clone();
    $(el).attr('id', 'stepexpected.' + stepNumber);
    $(el).attr('name', 'stepexpected.' + stepNumber);
    $(el).val(expected);
    elContainer = $('<td/>').clone();
    $(elContainer).append($(el));

    el = $('#step-hidden').find('.step-id').clone();
    $(el).attr('id', 'stepid.' + stepNumber);
    $(el).attr('name', 'stepid.' + stepNumber);
    $(el).val(id);
    $(elContainer).append($(el));

    $(step).append($(elContainer));

    var size = $('#steps-container').find('.step').length;

    el = $('#step-hidden').find('.step-order').clone();
    $(el).attr('id', 'steporder.' + stepNumber);
    $(el).attr('name', 'steporder.' + stepNumber);
    $(el).val(size + 1);

    elContainer = $('<td/>').clone();
    $(elContainer).append($(el));

    el = $('#step-hidden').find('.step-increment').clone();
    $(el).attr('id', 'step-increment-' + stepNumber);
    $(el).attr('onclick', 'increment(' + stepNumber + ')');
    $(elContainer).append($(el));

    el = $('#step-hidden').find('.step-decrement').clone();
    $(el).attr('id', 'step-decrement-' + stepNumber);
    $(el).attr('onclick', 'decrement(' + stepNumber + ')');
    $(elContainer).append($(el));


    $(step).append($(elContainer));




    $('#steps-container tbody').append($(step));
}

function increment(step) {

    var step = $("tr[step='" + step + "']");
    var stepVal = $(step).find('.step-order').val();
    stepVal = parseInt(stepVal) + 1;
    var nextStep = $.find(".step-order[value='" + stepVal + "']");
    if ($(nextStep).length > 0) {
        $(nextStep).parent().parent().after($(step));
        $.each($.find('.step'), function( index, value ) {
            $(value).find('.step-order').val(index + 1);
        });
    }

}


function decrement(step) {


    var step = $("tr[step='" + step + "']");
    var stepVal = $(step).find('.step-order').val();
    stepVal = parseInt(stepVal) - 1;
    var nextStep = $.find(".step-order[value='" + stepVal + "']");
    if ($(nextStep).length > 0) {
        alert('decrement');
        $(nextStep).parent().parent().before($(step));
        $.each($.find('.step'), function( index, value ) {
            $(value).find('.step-order').val(index - 1);
        });
    }

}

    </script>

</div>
</body>
</html>