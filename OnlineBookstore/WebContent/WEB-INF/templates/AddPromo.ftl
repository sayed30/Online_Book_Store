<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Add Promotion</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/admin.js" type="text/javascript"></script>
	<link href="css/browsebutton.css" rel="stylesheet">  
    <!-- Custom styles for this template -->
    
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
              <a class="nav-link" href="AddPromo.html">Add Promotion</a>
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
			    		<h3 class="panel-title">Add a Promotion</h3>
			 			</div>
			 			<div class="panel-body">
			    		<form role="form" action="AdminServlet" method="post">
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="promoName" id="promoName" maxlength="45" class="form-control input-sm" placeholder="Promotion Name" required>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="percentage" minlength="1" maxlength="2" id="percentage" class="form-control input-sm" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="Promotion Percentage" required>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-8 col-sm-8 col-md-8">
			    					<div class="form-group">
			    						<input type="date" name="expiration" id="expiration" class="form-control input-sm" placeholder="Expiration Date" required>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-12 col-sm-12 col-md-12">
			    					<div class="form-group">
			                			<input type="text" name="promoID" minlength="1" maxlength="10" id="promoID" class="form-control input-sm" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="Promotion ID" required>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			
			    			<input type="submit" value="Add Promotion" name="addpromotion" class="btn btn-info btn-block">
			    			<p>${message}</p>
			    		</form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
      </div>
</html>
