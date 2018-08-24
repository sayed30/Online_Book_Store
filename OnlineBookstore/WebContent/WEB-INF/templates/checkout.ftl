<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Online Book Store</title>

	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/customerhome.js" type="text/javascript"></script>

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
					<form class="form-inline" action="CustomerServlet" method="post">
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
              <a class="nav-link" href="Customer.html">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item"> 
            <form action="CustomerServlet" method="post">
              <button type="submit"class="btn btn-link browsebutton" name="browse" id="browse" value="Browse Books">Browse Books</button>
              </form>
            </li>&nbsp;
          	<li class="nav-item">
              <form role="form" action="CustomerServlet" method="post">
              	<button type="submit"class="btn btn-link browsebutton" name="getCart" id="getCart" value="Cart">My Cart</button>
              </form>
            </li>
            <li class="nav-item">
            <form action="CustomerServlet" method="post">
            		<button type="submit" class="btn btn-link browsebutton" name="viewHistory" id="viewHistory" value="View Order History">Order-History</button>
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

			<div class="container">
		    	<div class="row">
		    		<div class="col-sm-3"></div>
					<div class="col-sm-3"><h4>Title</h4></div>
					<div class="col-sm-3"><h4>Quantity</h4></div>
					<div class="col-sm-3"><h4>Total</h4></div>
				</div>
				<#assign sum = 0>
		    	<#list cart as cart>
		    		<#assign sum = sum + cart.getTotal()>
		    		<div class="row">
		    			<div class="col-sm-3"><img class="img-thumbnail" src="${book[cart?index].getPicture()}" alt="${book[cart?index].getTitle()}"></div>
		    			<div class="col-sm-3"><p>${book[cart?index].getTitle()}</p></div>
		    			<div class="col-sm-3"><p>${cart.getQty()}</p></div>
		    			<div class="col-sm-3"><p class = "total">${cart.getTotal()}</p></div>
					</div>
				</#list>
			</div>
	
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
			    		<#list addressList as address>
			    		<div class="border border-dark address">
			    			<input type="hidden" class="addressId" value="${address.getId()?c}"/>
			    			<input type="hidden" class="streetAddress" value="${address.getStreet()}"/>
			    			<input type="hidden" class="cityAddress" value="${address.getCity()}"/>
			    			<input type="hidden" class="stateAddress" value="${address.getState()}"/>
			    			<input type="hidden" class="zipAddress" value="${address.getZip()}"/>
			    			${address.getStreet()}<br>
			    			${address.getCity()}, ${address.getState()} ${address.getZip()}
			    		</div>
			    		</#list>
			    	</div>
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
			        	<input type="text" name="street" id="street" class="form-control input-sm" placeholder="Street">
			    	</div>
			    </div>
			  
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 col-md-4">
			    	<div class="form-group">
			    		<input type="text" name="city" id="city" class="form-control input-sm" placeholder="City"/>
			    	</div>
			    </div>
			    <div class="col-xs-4 col-sm-4 col-md-4">
			    	<div class="form-group">
			    		<!-- <input type="text" name="state" id="state" class="form-control input-sm" placeholder="State"/>-->
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
			    		<input type="text" name="zip" id="zip" class="form-control input-sm" placeholder="Zip code"/>
			    	</div>
			    </div>
			</div>
			<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<div class="form-group">
					<div class="col-xs-5 col-sm-5 col-md-5">
					<button type="submit" value="" name="editAddress" id="editButton" class="btn btn-info btn-block">Edit Address</button>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5">
					<button type="submit" value="" name="addAddress" id="addButton" class="btn btn-info btn-block">Add Address</button>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5">
					<button type="submit" value="" name="deleteAddress" id="deleteButton" class="btn btn-info btn-block">Delete Address</button>
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
			    		<div class="border border-dark credit">
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
			        	<input type="text" name="number" id="number" maxlength="16" class="form-control input-sm" placeholder="Credit Card Number" required>
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
					<div class="col-xs-5 col-sm-5 col-md-5">
					<button type="submit" value="" name="addCard" id="addCard" class="btn btn-info btn-block">Add Credit Card</button>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5">
					<button type="submit" value="" name="deleteCard" id="deleteButton" class="btn btn-info btn-block">Delete Credit Card</button>
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
