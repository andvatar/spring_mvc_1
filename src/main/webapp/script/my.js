function delete_task(task_id) {
    let url = "/" + task_id;
    $.ajax({
        url:url,
        type: 'DELETE',
        success: function() {
            window.location.reload();
        }
    });
}

function edit_task(task_id) {
    let identifier_delete = "#delete_" + task_id;
    $(identifier_delete).remove();

    let identifier_edit = "#edit" + task_id;
    let save_button = "<button id='save_'" + task_id + "'>Save</button>";
    $(identifier_edit).html(save_button);
    let property_save_tag = "update_task(" + task_id + ")";
    $(identifier_edit).attr("onclick", property_save_tag);

    let current_tr_element = $(identifier_edit).parent().parent();
    let children = current_tr_element.children;
    let td_description = children[1];
    td_description.attr("data-editable", true);
    //td_description.innerHTML = "<input id='input_description_" + task_id + "' type='text' value='" + td_description.innerHTML + "'>";

    let td_status = children[2];
    let status_id = "#select_status_" + task_id;
    let status_current_value = td_status.innerHTML;
    td_status.innerHTML = getDropdownStatusHtml(task_id);
    $(status_id).val(status_current_value).change();
}

function getDropdownStatusHtml(task_id) {
    let status_id = "select_status_" + task_id;
    return "<label for = 'status'></label>"
        + "<select id=" + status_id + "name='status'>"
        + "<option value = 'IN_PROGRESS'>IN_PROGRESS</option>"
        + "<option value = 'DONE'>DONE</option>"
        + "<option value = 'PAUSED'>PAUSED</option>"
        + "</select>"
}

function update_task() {
    console.log("start")
    let task_id = document.getElementById('id').value;
    let url = "/" + task_id;

    let value_description = $("#description").val();
    let value_status = $("#status").val();

    console.log(task_id)
    console.log(value_description)
    console.log(value_status)

    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        async: false,
        data: JSON.stringify({"description": value_description, "status":value_status})
    });

    setTimeout(() => {
        document.location.reload();
    }, 300);
}

function add_task() {
    console.log("start")
    //let task_id = document.getElementById('id').value;
    let url = "/";// + task_id;

    let value_description = $("#create_description").val();
    let value_status = $("#create_status").val();

    //console.log(task_id)
    console.log(value_description)
    console.log(value_status)

    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        async: false,
        data: JSON.stringify({"description": value_description, "status":value_status})
    });

    setTimeout(() => {
        document.location.reload();
    }, 300);
}

$('#editModal').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget) // Button that triggered the modal
    let description = button.data('description') // Extract info from data-* attributes
    let status = button.data('status') // Extract info from data-* attributes
    let id = button.data('id') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    let modal = $(this)
    //modal.find('.modal-title').text('New message to ' + recipient)
    modal.find('#description').val(description)
    modal.find('#status').val(status)
    modal.find('#id').val(id)
})