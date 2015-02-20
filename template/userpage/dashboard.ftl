<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<#include "/common/common.ftl">
<div id="mainTitle">Dashboard</div>

<div id="mainBody">
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Projects</h3>
    </div>
    <div id="projects-container" class="panel-body">
        Panel content
    </div>
</div>
<script>
$(document).ready(function () {
    $.get("/tcm/dashboard/projects", function(projects) {
        $('#projects-container').html(projects);
    });
});
</script>
</div>
</body>
</html>