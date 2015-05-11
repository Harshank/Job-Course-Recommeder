/**
 * Created by harsh on 5/10/15.
 */
function fetchCourses(skill) {
    var course=[];
    console.log('GO');
    $.ajax({
        url: "/recommendations/"+skill,
        success: function (response) {
            course = response;
            console.log(response);
            $("#courseDiv").html(course);
        },
        error: function () {
            // Stop displaying loading indicator
            kendo.ui.progress(editEvent.container, false);
            console.error("uh oh!");
        }
    });
}