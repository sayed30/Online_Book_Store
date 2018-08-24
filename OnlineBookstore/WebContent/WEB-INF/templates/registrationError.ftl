<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Sign Up</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      

    
  </head>

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
              <a class="nav-link" href="index.html">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="login.html">Log-In</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="registation.html">Sign Up</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
     
      
    <div class="container">
      <div style="
   position: absolute;
  margin: auto;
  top: 100px;
  right: 0;
  bottom: 0;
  left: 0;
  width: 100%;
 
   ">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title">Please sign up! <small>It's free!</small></h3>
			 			</div>
			 			<div class="panel-body">
			    		<form role="form" action="BookstoreServlet" method="post">
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="first_name" id="first_name" maxlength="45" class="form-control input-sm" placeholder="First Name" required>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="last_name" id="last_name" maxlength="45" class="form-control input-sm" placeholder="Last Name" required>
			    					</div>
			    				</div>
			    			</div>

			    			<div class="form-group">
			    				<input type="email" name="email" id="email" class="form-control input-sm" maxlength="45" placeholder="Email Address" required>
			    			</div>
                            
                            <div class="form-group">
			    				<input type="tel" name="phoneNumber" id="phoneNumber" maxlength="45" class="form-control input-sm" placeholder="Phone Number" required>
			    			</div>

			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="password" name="password" id="password" maxlength="45" class="form-control input-sm" placeholder="Password" required>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="password" name="password_confirmation" maxlength="45" id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password" required>
			    					</div>
			    				</div>
			    			</div>
			    			<p>Check this box to subscribe to receive promotions via email! <input type="checkbox" name="sub"/></p>
			    			<input type="submit" value="Register" name="signup" class="btn btn-info btn-block">
			    			<#if check == 0>
				    			<p>This email is already in use.</p>
			    			<#elseif check == 1>
			    				<p>Different values for password and password confirmation.</p>
			    			<#else>
			    				<p>Sorry, we are unable to register your account at this time</p>
			    			</#if>
			    		</form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
      </div>
</html>