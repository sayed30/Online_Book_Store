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
    <br><br>
    <div class="container">
		<form action="CustomerServlet" method="post">
			<h5>Shipping Address</h5>
			<#list shippingAddress as shippingAddress>
				<input type="hidden" name="shipAddress" value="${shippingAddress.getStreet()}, ${shippingAddress.getCity()}, ${shippingAddress.getState()}, ${shippingAddress.getZip()}">
				<span class="label label-default">${shippingAddress.getStreet()}, ${shippingAddress.getCity()}, ${shippingAddress.getState()}, ${shippingAddress.getZip()}</span>
			</#list>
			<br><br><h5>Billing Address</h5>
			<#list billingAddress as billingAddress>
				<input type="hidden" name="billAddress" value="${billingAddress.getStreet()}, ${billingAddress.getCity()}, ${billingAddress.getState()}, ${billingAddress.getZip()}">
				<span class="label label-default">${billingAddress.getStreet()}, ${billingAddress.getCity()}, ${billingAddress.getState()}, ${billingAddress.getZip()}</span>
			</#list>
			<br><br><h5>Payment Type</h5>
			<#list billingCard as billingCard>
				<input type = "hidden" name="billCard" value="${billingCard.getType()}, ${billingCard.getExpirationDate()}, ${billingCard.getNumber()}">
				<span class="label label-default">${billingCard.getType()}, ${billingCard.getExpirationDate()}, ${billingCard.getNumber()}</span>
			</#list>
			<br><br><h5>Cart</h5>
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-3"><h4>Title</h4></div>
				<div class="col-sm-3"><h4>Quantity</h4></div>
				<div class="col-sm-3"><h4>Total</h4></div>
			</div>
			<#list cart as cart>
			<div class="row">
				<div class="col-sm-3"><img class="img-thumbnail" src="${book[cart?index].getPicture()}" alt="${book[cart?index].getTitle()}"></div>
				<div class="col-sm-3"><p>${book[cart?index].getTitle()}</p></div>
				<div class="col-sm-3">
					<span class="label label-default" name="cartTotal">${cart.getQty()}</span>
					<input type="hidden" name="cartID" value="${cart.getCartID()}">
				</div>
				<div class="col-sm-3"><p name="total">${cart.getTotal()?string.currency}</p></div>
			</div>
			</#list>
			<br><br>
			<span class="label label-default">Total = </span>
			<#list promo as promo>
				<input type="hidden" name="promoCode" value="${promo}">
			</#list>
			<#list orderTotal as orderTotal>
			<input type="hidden" name="orderTotal" value="${orderTotal}"><span class="label label-default">${orderTotal}</span>
			</#list>
			<button class="btn btn-link" value="completePurchase" name="completePurchase">Complete Purchase</button>
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