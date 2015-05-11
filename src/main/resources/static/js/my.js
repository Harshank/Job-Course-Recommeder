/**
 * Created by harsh on 5/10/15.
 */
function fetchCourses(skill) {
    var course=[];
    $.ajax({
        url: "/recommendations",
        success: function (response) {
            course = response;
        },
        error: function () {
            // Stop displaying loading indicator
            kendo.ui.progress(editEvent.container, false);
            console.error("uh oh!");
        }
    });
    return course;
}