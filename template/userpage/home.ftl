<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<#include "/common/common.ftl">
<div id="mainTitle">Dashboard</div>

<div id="mainBody">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h3>Projects</h3>
                <hr class="h-accent"/>
                <div id="projects-container">
                    Panel content
                </div>
            </div>
            <div class="col-md-6">
                <h3>Latest</h3>
                <hr class="h-accent"/>
                <div id="actionslatest-container">
                    Panel content
                </div>
            </div>
        </div>
    </div>


<script>
$(document).ready(function () {
    $.get("/mgr/dashboard/projects", function(projects) {
        $('#projects-container').html(projects);
    });

    $.get("/mgr/user/actionslatest", function(actionslatest) {
        $('#actionslatest-container').html(actionslatest);

    });
});
</script>
</div>
</body>
</html>