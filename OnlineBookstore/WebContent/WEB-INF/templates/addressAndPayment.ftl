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

<body onload="showValue()">

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
					</li>
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
	<#list error as error>
		<div style="color:#800; margin: auto; text-align: center;" width="100%">
			${error}
			</div>
	</#list>
	<br/>

	<div class="container">
		<form action="CustomerServlet" method="post">
			<h4>Select Shipping Address</h4>
			<table class="table table-striped">
				<thead>
					<tr>
					<th>Select</th>
					<th>Street</th>
					<th>City</th>
					<th>State</th>
					<th>Zip</th>
					</tr>
				</thead>
				<tbody>
					<#list address as address>
					<tr>
						<td><input type="radio" name="selectShipAddress" value="${address.getId()}"></td>
						<td>${address.getStreet()}</td>
						<td>${address.getCity()}</td>
						<td>${address.getState()}</td>
						<td>${address.getZip()}</td>
					</tr>
					</#list>
					<tr>
						<td><input type="radio" name="selectShipAddress" value="newAddress" checked>Add Shipping Address</td>
						<td><input type="text" name="newshipstreet" id="street" maxlength="100" class="form-control input-sm" placeholder="Street"></td>
						<td><input type="text" name="newshipcity" id="city" maxlength="45" class="form-control input-sm" placeholder="City"/></td>
						<td><!-- <input type="text" name="newshipstate" id="state" class="form-control input-sm" placeholder="State"/>-->
							<select class="state form-control input-sm" id="state" name="newshipstate">
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
						</td>
						<td><input type="text" name="newshipzip" id="zip" class="form-control input-sm" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="Zip code"/></td>
					</tr>
				</tbody>
			</table>
			
			<br>
					
			<h4>Select Billing Address</h4>
			<table class="table table-striped">
				<thead>
					<tr>
					<th>Select</th>
					<th>Street</th>
					<th>City</th>
					<th>State</th>
					<th>Zip</th>
					</tr>
				</thead>
				<tbody>
					<#list address as address>
					<tr>
						<td><input type="radio" name="selectBillAddress" value="${address.getId()}"></td>
						<td>${address.getStreet()}</td>
						<td>${address.getCity()}</td>
						<td>${address.getState()}</td>
						<td>${address.getZip()}</td>
					</tr>
					</#list>
					<tr>
						<td><input type="radio" name="selectBillAddress" value="newAddress" checked>Add Billing Address</td>
						<td><input type="text" name="newbillstreet" id="street" maxlength="100" class="form-control input-sm" placeholder="Street"></td>
						<td><input type="text" name="newbillcity" id="city" maxlength="45" class="form-control input-sm" placeholder="City"/></td>
						<td><!-- <input type="text" name="newbillstate" id="state" class="form-control input-sm" placeholder="State"/>-->
							<select class="state form-control input-sm" id="state" name="newbillstate">
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
						</td>
						<td><input type="text" name="newbillzip" id="zip" maxlength="45" class="form-control input-sm" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="Zip code"/></td>
					</tr>
				</tbody>
			</table>
			
			<br><br>
					
			<h4>Select Card</h4>
			<table class="table table-striped">
				<thead>
					<tr>
					<th>Select</th>
					<th>Card NUmber</th>
					<th>Card Type</th>
					<th>Expiry Date</th>
					</tr>
				</thead>
				<tbody>
					<#list card as card>
					<tr>
						<td><input type="radio" name="selectCard" value="${card.getId()}"></td>
						<td>${card.getNumber()}</td>
						<td>${card.getType()}</td>
						<td>${card.getExpirationDate()}</td>
						<td></td>
					</tr>
					</#list>
					<tr>
						<td><input type="radio" name="selectCard" value="newCard" checked>Add Card</td>
						<td>
							<input type="text" name="newcardnumber" minlength="16" maxlength="16" id="number" maxlength="16" class="form-control input-sm" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="Credit Card Number">
						</td>
						<td>
							<select class="state form-control input-sm" id="type" name="newcardtype">
								<option value="MasterCard">MasterCard</option>
								<option value="Visa">Visa</option>
								<option value="American Express">American Express</option>
								<option value="Discover">Discover</option>
							</select>
						</td>
						<td><input type="text" name="newcardexpiration" id="expiration" class="form-control input-sm" placeholder="Expiration Date" ></td>
						<td><input type="text" name="newccid" id="ccid" minlength="3" maxlength="3" class="form-control input-sm" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="CC ID"></td>
					</tr>
				</tbody>
			</table>
			<#list total as total>
				<input type="hidden" name="orderTotal" value="${total}">
				</#list>
			<#list promo as promo>
				<input type="hidden" name="promoCode" value="${promo}">
			</#list>
			<button class="btn btn-link" value="continueToCheckOut" name="continueToCheckOut">Continue To CheckOut</button>
		</form>
	</div>

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