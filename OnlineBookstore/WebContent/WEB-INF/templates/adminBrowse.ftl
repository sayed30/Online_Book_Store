<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Browse Books</title>

	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/admin.js" type="text/javascript"></script>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/heroic-features.css" rel="stylesheet">
    <link href="css/browsebutton.css" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" id="welcome" href="#"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          <li class="nav-item">
					<form class="form-inline" action="AdminServlet" method="post">
						<input type="text" name="term" class="form-control" placeholder="Search here"/>
						<select class="form-control" name="category" id="dropDown_search">
							<option value="0">ISBN</option>
							<option value="1">Author</option>
							<option value="2">Title</option>
							<option value="3">Subject</option>
						</select>
						<button type="submit" name="searchBooks" class="form-control">Search</button>
					</form>
				</li>
            <li class="nav-item active">
              <a class="nav-link" href="Admin.html">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="AddBook.html">Add Book</a>
            </li>
            <li class="nav-item"> 
            <form action="AdminServlet" method="post">
              <button type="submit" class="btn btn-link browsebutton" name="browse" id="browse" value="Browse Books">Browse Books</button>
              </form>
            </li>
            <li class="nav-item"> 
            		<form action="AdminServlet" method="post">
              			<button type="submit" class="btn btn-link browsebutton" name="salesReport" id="salesReport" value="View EOD">View EOD</button>
              		</form>
            	</li>
            	<li class="nav-item"> 
            		<form action="AdminServlet" method="post">
              			<button type="submit" class="btn btn-link browsebutton" name="lowQty" id="lowQty" value="View Low Quantity">Low Inventory</button>
              		</form>
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

	<br/>
	<br/>
    <!-- Page Content -->
    <div class="container">

      <!-- Page Features -->
      <div class="row text-center" id="bookRow">
		<#list books as book>
        <div class="col-lg-3 col-md-6 mb-4">
          <div class="card">
            <img class="card-img-top" src="${book.getPicture()}" alt="${book.getTitle()}">
            <div class="card-body">
              <h4 class="card-title">${book.getTitle()}</h4>
              <p class="card-text">${book.getDescription()}</p>
            </div>
            <div class="card-footer">
            <form action="AdminServlet" method="post">
              <button type="submit" class="btn btn-primary" id="edit" name="editbook" value="${book.getIsbn()?c}">Edit Book</button>
              <button type="submit" class="btn btn-primary" id="delete" name="deletebook" value="${book.getIsbn()?c}">Delete Book</button>
            </form>
            </div>
          </div>
        </div>
		</#list>
      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper/popper.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

  </body>

</html>
