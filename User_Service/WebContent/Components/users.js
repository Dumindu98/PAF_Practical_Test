$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidRuser_IDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "UsersAPI",
		type : type,
		data : $("#formUser").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onUserSaveComplete(response.responseText, status);
		}
	});	
});

function onUserSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divUsersGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidRuser_IDSave").val("");
	$("#formUser")[0].reset();
}

//DELETE=============================================================================
	$(document).on("click", ".btnRemove", function(event) {
		$.ajax({
			url : "UsersAPI",
			type : "DELETE",
			data : "Ruser_ID=" + $(this).data("userid"),
			dataType : "text",
			complete : function(response, status) {
				onUserDeleteComplete(response.responseText, status);
			}
		});
	});
	
	
	function onUserDeleteComplete(response, status) {
		if (status == "success") {
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success") {
				$("#alertSuccess").text("Successfully deleted.");
				$("#alertSuccess").show();
				$("#divUsersGrid").html(resultSet.data);
			} else if (resultSet.status.trim() == "error") {
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
		} else if (status == "error") {
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		} else {
			$("#alertError").text("Unknown error while deleting..");
			$("#alertError").show();
		}
	}


// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidRuser_IDSave").val(
					$(this).closest("tr").find('#hidRuser_IDUpdate').val());
			$("#Ruser_name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#Ruser_address").val($(this).closest("tr").find('td:eq(1)').text());
			$("#Ruser_gender").val($(this).closest("tr").find('td:eq(2)').text());
			$("#Ruser_age").val($(this).closest("tr").find('td:eq(3)').text());
			$("#Ruser_notes").val($(this).closest("tr").find('td:eq(4)').text());
		});

// CLIENTMODEL=========================================================================
function validateItemForm() {
	// USERNAME
	if ($("#Ruser_name").val().trim() == "") {
		return "Insert Username.";
	}
	
	// ADDRESS
	if ($("#Ruser_address").val().trim() == "") {
		return "Insert Address.";
	}
	
	// is numerical value
	//var tmpPhone = $("#phoneNo").val().trim();
	//if (!$.isNumeric(tmpPhone)) {
		//return "Insert Only Numbers for Phone No.";
	//}

	// GENDER
	if ($("#Ruser_gender").val().trim() == "") {
		return "Insert Gender.";
	}
	
	// is numerical value
	//var tmpAge = $("#age").val().trim();
	//if (!$.isNumeric(tmpAge)) {
		//return "Insert a numerical value for Age.";
	//}
	
	// AGE---------------------------
	if ($("#Ruser_age").val().trim() == "") {
		return "Insert Age.";
	}
	// USER_NOTES------------------------
	if ($("#Ruser_notes").val().trim() == "") {
		return "Insert User Notes.";
	}
	
	return true;
}
