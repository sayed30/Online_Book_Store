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
    <script src="js/credit.js" type="text/javascript"></script>
    
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="js/monthpicker.js" type="text/javascript"></script>
    
    <!-- Custom styles for this template -->
    
    <link href="css/browsebutton.css" rel="stylesheet">
    
    <style>
		#expiration .ui-datepicker-calendar {
   		display: none;
 		}
	</style>
    
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
			    		<h3 class="panel-title">Credit Cards</h3>
			 			</div>
			 			<div class="panel-body">
			    		<div class="pre-scrollable">
			    		<#if cardList?has_content>
			    		<#list cardList as card>
			    		<div class="border border-dark credit" style="background-color: white; cursor: pointer;">
			    			<input type="hidden" class="creditNumber" value="${card.getNumber()}"/>
			    			<input type="hidden" class="creditDate" value="${card.getExpirationDate()}"/>
			    			<input type="hidden" class="creditType" value="${card.getType()}"/>
			    			<input type="hidden" class="creditID" value="${card.getId()?c}"/>
			    			Credit Card Type: ${card.getType()}<br>
			    			Credit Card Number: ${card.getNumber()}<br>
			    			Expiration Date: ${card.getExpirationDate()}<br>
			    		</div>
			    		</#list>
			    		<#else>
			    		<div class="border border-dark">
			    		You do not have any credit cards on this account.
			    		</div>
			    		</#if>
			    	</div>
			    	<form action="CustomerServlet" method="post">
			    	<div class="col-xs-8 col-sm-8 col-md-8">
					<button type="submit" value="" name="deleteCard" id="deleteButton" class="btn btn-info btn-block">Delete Credit Card</button>
					</div>
					</form>
	    		</div>
    		</div>
    		
    	</div>
    	<form role="form" class="col-xs-8 col-sm-8 col-md-8" action="CustomerServlet" method=post>
    	<div class="panel panel-default col-md-8">
    		<div class="panel-heading">
			    	<h3 class="panel-title">Add/Delete Credit Cards</h3>
			</div>
			
    		<div class="row">
    			
			    <div class="col-xs-12 col-sm-12 col-md-12">
			    	<div class="form-group">
			        	<input type="password" name="number" id="number" minlength="16" maxlength="16" class="form-control input-sm" placeholder="Credit Card Number" onkeypress='return event.charCode >= 48 && event.charCode <= 57' required>
			    	</div>
			    </div>
			  
			</div>
			<div class="row">
				<div class="col-xs-3 col-sm-6 col-md-3">
					<div class="form-group">
						<input type="password" name="security" minlength="3" maxlength="3" id="security" class="form-control input-sm" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="CSC" required/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-6 col-md-6">
			    	<div class="form-group">
			    		<select class="state form-control input-sm" id="type" name="type" required>
			    			<option value="MasterCard">MasterCard</option>
							<option value="Visa">Visa</option>
							<option value="American Express">American Express</option>
							<option value="Discover">Discover</option>
			    		</select>
			    	</div>
			    </div>
			    <div class="col-xs-6 col-sm-6 col-md-6">
			    	<div class="form-group">
			    		<input type="text" name="expiration" id="expiration" class="form-control input-sm" placeholder="Expiration Date" required>
			    	</div>
			    </div>
			</div>
			<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<div class="form-group">
					<div class="col-xs-8 col-sm-8 col-md-8">
					<button type="submit" value="temp" name="addCard" id="addCard" class="btn btn-info btn-block">Add Credit Card</button>
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
