<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

<head>
<title>List Customers</title>

<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<<<<<<< HEAD
=======

<script type="text/javascript">
	function submitClicked(){
		document.getElementById('uploadCSVSubmit').addEventListener('click',
				openUploadDialog());
		function openUploadDialog() {
			document.getElementById('csvFileUpload').click();
		}
	}
</script>
>>>>>>> dev

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
<<<<<<< HEAD
		
		<div style="margin-top: 10px;">
=======

		<div id="content">

>>>>>>> dev
			<!-- put new button: Add Customer -->
			<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />
<<<<<<< HEAD
			<!-- Add Customer list in CSV format button -->
			<input type="button" value="Download CSV"
				onclick="window.location.href='downloadCSV'; return false;"
				class="add-button" />
			<!-- Upload CSV data -->
			<input type="button" value="Upload Customer Data"
				onclick="window.location.href='uploadCSV'; return false;"
				class="add-button" />
		</div>
		<div id="content">

			<!-- put new button: Add Customer -->

			<!-- <input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" /> -->
=======

			<!-- put new button: Add Customer -->
			<form method="post" enctype="multipart/form-data" action="processCSV" accept="csv"
				id="form_csv">
				<input type="file" name="uploadedCSV" /> 
				<input type="submit"
					value="Upload CSV Data" name="uploadCSV" class="add-button" />
			</form>

			<!-- put new button: Add Customer -->
			<input type="button" value="Download CSV"
				onclick="window.location.href='downloadCSV'; return false;"
				class="add-button" />

			<!-- put new button: Add Customer -->
			<input type="button" value="Download PDF"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />
>>>>>>> dev

			<!--  add our html table here -->

			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${customers}">

					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>

						<td>
							<!-- display the update link --> <a href="${updateLink}">Update</a>
							| <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>

					</tr>

				</c:forEach>

			</table>

		</div>

	</div>


</body>

</html>









