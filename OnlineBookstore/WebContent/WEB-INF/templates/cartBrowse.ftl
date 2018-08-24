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
				<form class="form-inline" action="BookstoreServlet" method="post">
					<input type="text" class="form-control" placeholder="Search here">
					<select class="form-control" id="dropDown_search">
						<option>Search By</option>
						<option>ISBN</option>
						<option>Author</option>
						<option>Name</option>
					</select>
					<button type="submit" class="form-control">Search</button>
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
             <li class="nav-item" style="margin-right: 10px;">
              <form role="form" action="CustomerServlet" class="test" method="post">
              	<button type="submit"class="btn btn-link browsebutton" name="getCart" id="getCart" value="Cart">MyCart</button>
              </form>
            </li>
            <li class="nav-item" style="margin-right: 10px;">
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
    
    <br/><br/>

	<#list error as error>
		<div style="color:#800; margin: auto; text-align: center;" width="100%">
			${error}
			</div>
	</#list>
	<br/>

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
    		<form action="CustomerServlet" method="post">
	    		<div class="row">
	    			<div class="col-sm-3"><img class="img-thumbnail" src="${book[cart?index].getPicture()}" alt="${book[cart?index].getTitle()}"></div>
	    			<div class="col-sm-3"><p>${book[cart?index].getTitle()}</p></div>
	    			<div class="col-sm-3">
	    				<select name="quantity">
	    					<#list 1..20 as x>
	    						${x}
	    						<#if x == cart.getQty()>
	    							<option selected=selected vlaue="${x}">${x}</option>
								<#else>
	    							<option value="${x}">${x}</option>
	    						</#if>
	    					</#list>
	    				</select>
	    				<input type="hidden" name="cartID" value="${cart.getCartID()}">
	    				<button class="btn btn-link" value="${cart.getIsbn()}" name="updateItem">Update</button>
	    				<button class="btn btn-link" value="${cart.getIsbn()}" name="deleteItem">Delete</button>
	    			</div>
	    			<div class="col-sm-3"><p name="total">${cart.getTotal()?string.currency}</p></div>
				</div>
			</form>
		</#list>
	</div>
	
	<form action="CustomerServlet" method="post">
		<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-3"></div>
		<div class="col-sm-3"></div>
		<div class="col-sm-3">
				<span class="label label-default">Total = </span>
				<input type="hidden" name="orderTotal" value="${total}"><span class="label label-default">${total?string.currency}</span>
		</div>
		</div>
		<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-3">
			<label>Enter Promo Code:</label>
			<#list promo as promo><input type="text" maxlength="10" id="promoCode" name="promoCode" onkeypress='return event.charCode >= 48 && event.charCode <= 57' value="${promo}" placeholder="Promo Code"></#list>
		</div>
		<div class="col-sm-3"><button class="btn btn-link" value="applyPromo" name="applyPromo">Apply</button></div>
		<div class="col-sm-3"><button class="btn btn-link" value="checkout" name="checkoutCart">Checkout</button></div>
		</div>
	</form>
	
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