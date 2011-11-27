
$(document).ready(function() {
  $(".tasksSelect").change(function(event) {

    // TODO delegate object queries to dedicated class (shared between JS scripts)
    
    var taskIdElement = $(this);
    assertNotNull("taskIdElement", taskIdElement);

    var updateTaskAjaxUrl = getUpdateTaskAjaxUrl();

    var participantId = getParticipantId($(this));
    
    var amountElement = getAmountElement($(this));

     // Ajax call to task update service
    jQuery.getJSON(updateTaskAjaxUrl, {
      participantId : participantId,
      taskId : taskIdElement.val()
    }, function(data, status) {
    //TODO manage errors
      updateTask(taskIdElement, data.taskId);
      updateAmount(amountElement, data.newAmount);
      updateHistory();
    });
  });

});

function updateTask(taskIdElement, newTaskId){
  if (taskIdElement.val() != newTaskId) {
    // set new value (in case of error)
    taskIdElement.val(newTaskId);
    // highlight field
    taskIdElement.effect("highlight", {}, 1000);
  }
}

/**
 * Update the registration amount with a JQuery effect.
 * @param amountElement
 * @param newAmount
 */
function updateAmount(amountElement, newAmount){
  if (amountElement.html() != newAmount) {
    // hide amount with slide up effect
    amountElement.slideUp('fast', function() {
      // update amount
      amountElement.html(newAmount);
      // show amount with slide up effect
      amountElement.slideDown('slow');
    });
  }
}