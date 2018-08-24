<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>View Users</title>

	<script src="js/jquery.js" type="text/javascript"></script>
	
	<!-- <script src="js/admin.js" type="text/javascript"></script> -->

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/browsebutton.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
      
    <!-- Custom styles for this template -->

  </head>
<style>
    .table-responsive{margin-top:25px;}
    
    
    
    </style>
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
    <br>
    <br>
    <div class="container">
    	<br>
			<div class="panel-body">
	            	<div class="row">
	            		<div class="col-sm-1 head">Account ID</div>
	                	<div class="col-sm-2 head">User Type</div>
	                	<div class="col-sm-2 head">Full Name</div>
	                	<div class="col-sm-2 head">Email</div>
	                	<div class="col-sm-1 head">Status</div>
	                	<div class="col-sm-2 head">Authorize As</div>
	                	<div class="col-sm-2 head">Suspend</div>
	             	</div>
	             	<#list userList as user>
		             	<form action="AdminServlet" method="post">
			            	<div class="row">
			            		<input type="hidden" name="id" value="${user.getUserId()?c}"/>
			                	<div class="col-sm-1 col">${user.getUserId()?c}</div>
			                	<div class="col-sm-2 col">${user.getUserType()}</div>
			                	<div class="col-sm-2 col">${user.getFname()} ${user.getLname()}</div>
			                	<div class="col-sm-2 col">${user.getEmail()}</div>
			                	<div class="col-sm-1 col">${user.getStatus()}</div>
			                	<div class="col-sm-2 col">
			                	<select class="form-control" name="authorizeDrop" id="authorizeDrop">
										<option value="0">N/A</option>
										<option value="1">Customer</option>
										<option value="2">Shipping Employee</option>
										<option value="3">System Administrator</option>
										<option value="4">Manager</option>
								</select>
								<button type="submit" name="authorizeUser" id="authorizeUser" class="btn btn-primary">Authorize</button></div>
			                	<div class="col-sm-2 col">
			                		<button type="submit" name="suspendUser" id="suspendUser" class="btn btn-primary">Suspend</button>
			                		<button type="submit" name="unsuspendUser" id="unsuspendUser" class="btn btn-primary">Unsuspend</button>
			                	</div>
			            	</div>
			            </form>
		            </#list>
	           </div>
	     </div>
  </body>
</html>
