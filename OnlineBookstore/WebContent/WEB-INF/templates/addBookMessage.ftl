<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Add Book</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/admin.js" type="text/javascript"></script>
    <script src="js/addBook.js" type="text/javascript"></script>
      
    <!-- Custom styles for this template -->
    
    <link href="css/browsebutton.css" rel="stylesheet">
    
  </head>

  <body>
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
              <a class="nav-link" href="AddPromo.html">Add Promo</a>
            </li>
             <li class="nav-item"> 
            <form action="AdminServlet" method="post">
              <button type="submit" class="btn btn-link browsebutton" name="browse" id="browse" value="Browse Books">Browse Books</button>
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
    <br/>
    <div class="container">
      <div style="
    display: inline-block;
    /*position: absolute;*/
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    width: 900px;
    height: 300px;
    margin: auto;
   ">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title">Add a Book</h3>
			 			</div>
			 			<div class="panel-body">
			    		<form role="form" action="AdminServlet" method="post">
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="title" id="title" maxlength="45" class="form-control input-sm" placeholder="Title" required>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="author" id="author" maxlength="45" class="form-control input-sm" placeholder="Author Name" required>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="number" name="edition" id="edition" maxlength="11" class="form-control input-sm" placeholder="Edition" required>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="category" id="category" maxlength="45" class="form-control input-sm" placeholder="Category" required>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="isbn" id="isbn" minlength="10" maxlength="10" class="form-control input-sm" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="ISBN" required>
			    					</div>
			    				</div>
			    			</div>
							
							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="publisher" id="publisher" maxlength="45" class="form-control input-sm" placeholder="Publisher" required>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="number" name="year" id="year" min="1000" max="3000" class="form-control input-sm" placeholder="Publication Year" required>
			    					</div>
			    				</div>
			    			</div>
							
							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="number" name="threshold" min="0" id="threshold" class="form-control input-sm" placeholder="Minimum Threshold" required>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="number" name="quantity" min="0" id="quantity" class="form-control input-sm" placeholder="Quantity" required>
			    					</div>
			    				</div>
			    			</div>
							
							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="number" name="buyprice" id="buyprice" class="form-control input-sm" step=".01" placeholder="Buying Price" required>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="number" name="sellprice" id="sellprice" class="form-control input-sm" step=".01" placeholder="Selling Price" required>
			    					</div>
			    				</div>
			    			</div>
							
			    			<div class="form-group">
			    				<input type="url" name="picture" id="picture" class="form-control input-sm" placeholder="Cover URL" required>
			    			</div>
			    			
			    			<div class="form-group">
			    				<textarea name="description" id="description" class="form-control input-sm" placeholder="Description" required></textarea>
			    			</div>
			    			
			    			<input type="submit" value="Add Book" name="addbook" class="btn btn-info btn-block">
			    			<p id="bookMessage">${message}</p>
			    		</form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
      </div>
</html>
