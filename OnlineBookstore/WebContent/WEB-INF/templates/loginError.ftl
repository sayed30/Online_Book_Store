<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Log In</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      
    <!-- Custom styles for this template -->
    <link href="css/login.css" rel="stylesheet">
    <link href="css/browsebutton.css" rel="stylesheet">
  </head>

  <body>
      <div style="position: absolute;
  margin: auto;
  top: 100px;
  right: 0;
  bottom: 0;
  left: 0;
  width: 100%;">
      
    
     <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Book Store</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          <li class="nav-item">
					<form class="form-inline" action="BookstoreServlet" method="post">
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
              <a class="nav-link" href="index.html">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
				<form action="BookstoreServlet" method="post">
              		<button type="submit" class="btn btn-link browsebutton"  name="browse" id="browse" value="Browse Books">Browse Books</button>
             	</form>
			</li>
            <li class="nav-item">
              <a class="nav-link" href="registation.html">Sign Up</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container">

      <form class="form-signin" action="BookstoreServlet" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" name="email" maxlength="45" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="pass" id="inputPassword" maxlength="45" class="form-control" placeholder="Password" required>
        <a href="ForgotPassword.html">Forgot Password</a>
        <button class="btn btn-lg btn-primary btn-block" name="login" type="submit">Sign in</button>
        <p>${message}</p>
      </form>
 

    </div> <!-- /container -->
      </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
