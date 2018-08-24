<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>View Addresses</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/customerhome.js" type="text/javascript"></script>
    <!-- Custom styles for this template -->
    
    <link href="css/browsebutton.css" rel="stylesheet">
    
  </head>

  <body style="display: none;">
     <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" id="welcome" href="#"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="Customer.html">Home
                <span class="sr-only">(current)</span>
              </a>
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
        	<div class="panel panel-default ">
        		<div class="panel-heading">
			    		<h3 class="panel-title">Addresses</h3>
			 			</div>
			 			<div class="panel-body">
			    		<div class="pre-scrollable">
			    		<#if addressList?has_content>
			    		<#list addressList as address>
			    		<div class="border border-dark address" style="background-color: white; cursor: pointer;">
			    			<input type="hidden" class="addressId" value="${address.getId()?c}"/>
			    			<input type="hidden" class="streetAddress" value="${address.getStreet()}"/>
			    			<input type="hidden" class="cityAddress" value="${address.getCity()}"/>
			    			<input type="hidden" class="stateAddress" value="${address.getState()}"/>
			    			<input type="hidden" class="zipAddress" value="${address.getZip()}"/>
			    			${address.getStreet()}<br>
			    			${address.getCity()}, ${address.getState()} ${address.getZip()}
			    		</div>
			    		</#list>
			    		<#else>
			    		<div class="border border-dark">You currently do not have any addresses.</div>
			    		</#if>
			    	</div>
			    	<form action="CustomerServlet" method="post">
			    		<div class="col-xs-8 col-sm-8 col-md-8">
							<button type="submit" value="" name="deleteAddress" id="deleteButton" class="btn btn-info btn-block">Delete Address</button>
						</div>
			    	</form>
	    		</div>
    		</div>
    		
    	</div>
    	<form role="form" class="col-xs-8 col-sm-8 col-md-8" action="CustomerServlet" method=post>
    	<div class="panel panel-default col-md-8">
    		<div class="panel-heading">
			    	<h3 class="panel-title">Edit/Add Address</h3>
			</div>
			
    		<div class="row">
    			
			    <div class="col-xs-12 col-sm-12 col-md-12">
			    	<div class="form-group">
			        	<input type="text" name="street" maxlength="100" id="street" class="form-control input-sm" placeholder="Street" required/>
			    	</div>
			    </div>
			  
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 col-md-4">
			    	<div class="form-group">
			    		<input type="text" name="city" id="city" maxlength="45" class="form-control input-sm" placeholder="City" required/>
			    	</div>
			    </div>
			    <div class="col-xs-4 col-sm-4 col-md-4">
			    	<div class="form-group">
			    		<select class="state form-control input-sm" id="state" name="state">
			    			<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
							<option value="DE">Delaware</option>
							<option value="DC">District Of Columbia</option>
							<option value="FL">Florida</option>
							<option value="GA">Georgia</option>
							<option value="HI">Hawaii</option>
							<option value="ID">Idaho</option>
							<option value="IL">Illinois</option>
							<option value="IN">Indiana</option>
							<option value="IA">Iowa</option>
							<option value="KS">Kansas</option>
							<option value="KY">Kentucky</option>
							<option value="LA">Louisiana</option>
							<option value="ME">Maine</option>
							<option value="MD">Maryland</option>
							<option value="MA">Massachusetts</option>
							<option value="MI">Michigan</option>
							<option value="MN">Minnesota</option>
							<option value="MS">Mississippi</option>
							<option value="MO">Missouri</option>
							<option value="MT">Montana</option>
							<option value="NE">Nebraska</option>
							<option value="NV">Nevada</option>
							<option value="NH">New Hampshire</option>
							<option value="NJ">New Jersey</option>
							<option value="NM">New Mexico</option>
							<option value="NY">New York</option>
							<option value="NC">North Carolina</option>
							<option value="ND">North Dakota</option>
							<option value="OH">Ohio</option>
							<option value="OK">Oklahoma</option>
							<option value="OR">Oregon</option>
							<option value="PA">Pennsylvania</option>
							<option value="RI">Rhode Island</option>
							<option value="SC">South Carolina</option>
							<option value="SD">South Dakota</option>
							<option value="TN">Tennessee</option>
							<option value="TX">Texas</option>
							<option value="UT">Utah</option>
							<option value="VT">Vermont</option>
							<option value="VA">Virginia</option>
							<option value="WA">Washington</option>
							<option value="WV">West Virginia</option>
							<option value="WI">Wisconsin</option>
							<option value="WY">Wyoming</option>
			    		</select>
			    	</div>
			    </div>
			    <div class="col-xs-4 col-sm-4 col-md-4">
			    	<div class="form-group">
			    		<input type="text" name="zip" id="zip" maxlength="45" class="form-control input-sm" placeholder="Zip code" onkeypress='return event.charCode >= 48 && event.charCode <= 57' required/>
			    	</div>
			    </div>
			</div>
			<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<div class="form-group" class="col-xs-12 col-sm-12 col-md-12">
					<div class="col-xs-5 col-sm-5 col-md-5">
					<button type="submit" value="" name="editAddress" id="editButton" class="btn btn-info btn-block">Edit Address</button>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5">
					<button type="submit" value="" name="addAddress" id="addButton" class="btn btn-info btn-block">Add Address</button>
					</div>
				</div>
				<div class="form-group">
				${message}
				</div>
				</div>
			</div>
    	</div>
    	</form>
    </div>
    </div>
      </div>
</html>
