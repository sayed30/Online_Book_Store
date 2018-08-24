<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>View Orders</title>

    <script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/bookReport.js" type="text/javascript"></script>
	<script src="js/manager.js" type="text/javascript"></script>
	<script src="js/temp.js" type="text/javascript"></script>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->

  </head>
<style>
    .table-responsive{margin-top:25px;}



    </style>
  <body>
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Book Store</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="Manager_d.html">Home</a>
            </li>
            <li class="nav-item">
					<a class="nav-link" href="Settings.html">Settings</a>
			</li>
			<li class="nav-item">
					<a class="nav-link logout" id="logout" href="#">Logout</a>
				</li>
          </ul>
        </div>
      </div>
    </nav>
    <br>
    <br>
    <br>
     <div class="container">
		<div class="table-responsive">
      <#if books?has_content>
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>ISBN</th>
            <th>Author Name</th>
            <th>Title</th>
            <th>Quantity In Stock</th>
            <th>Minimum Threshold</th>
            <th>Update Quantity</th>
          </tr>
        </thead>
        <tbody>
        <#list books as book>
          <tr>
            <td>${book.getIsbn()?c}</td>
            <td>${book.getAuthor()}</td>
            <td>${book.getTitle()}</td>
            <td>${book.getQuantity()}</td>
            <td>${book.getThreshold()}</td>
            <td>
              <input type="number" min="0" class="updatedQuantity"/>
              <button type="button" value="${book.getIsbn()?c}" class="updatedQuantityButton">Update</button>
            </td>
          </tr>
        </#list>
        </tbody>
      </table>
      <#else>
        <p>No Books under threshold.</p>
      </#if>
    </div>
	</div>
  </body>
</html>
