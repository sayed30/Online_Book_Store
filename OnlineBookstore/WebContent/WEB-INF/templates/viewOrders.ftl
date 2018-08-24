<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>View Orders</title>
    
    <script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/order.js" type="text/javascript"></script>
	<script src="js/temp.js" type="text/javascript"></script>
	<script src="js/userHistory.js" type="text/javascript"></script>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      
    <!-- Custom styles for this template -->

  </head>
<style>
    .table-responsive{margin-top:25px;}
    
    
    
    </style>
  <body>
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Orders</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" id="home" href="">Home</a>
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
    <br>
     <div class="container">
		<div class="table-responsive">
		
		<#if orderList?has_content>			
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Payment Info</th>
            <th>Order Date</th>
            <th>Order Total</th>
            <th>Status</th>
            <th>Details</th>
          </tr>
        </thead>
        <tbody>
        <#list orderList as order>
          <tr>
            <td>${order.getOrderNumber()?c}</td>
            <td>${order.getPaymentMethod()}</td>
            <td>${order.getDate()}</td>
            <td>${order.getOrderTotal()?string.currency}</td>
            <td><span class="label label-info">${order.getOrderStatus()}</span></td>
            <td><button class="btn btn-primary transact" value="${order.getOrderNumber()?c}">View</button></td>
          </tr>
          <tr style="display:none;" id="${order.getOrderNumber()?c}">
          <td></td>
          <td>
  			<table>
  				<thead>
  					<tr>
  						<th>Title</th>
  						<th>Author</th>
  						<th>Quantity</th>
  						<th>Total</th>
  						<th>Rating</th>
  						<th>Rate Book</th>
  					</tr>
  				</thead>
  				<tbody>
	          		<#list order.getTransactionList() as tAct>
					<tr>
						<td><span class="label label-info">${tAct.getTitle()}</span></td>
						<td><span class="label label-info">${tAct.getAuthor()}</span></td>
						<td><span class="label label-info">${tAct.getTransactionQty()}</span></td>
						<td><span class="label label-info">${tAct.getTransactionTotal()}</span></td>
						<td><span class="label label-info">${tAct.getRating()?c}</span></td>
						<td><span class="label label-info">
							<select class="rateSelect">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
							<input type="hidden" class="order" value="${tAct.getOrderNumber()?c}"/>
							<button class="btn btn-primary rate" value="${tAct.getTransactionISBN()?c}">Rate</button>
						</span></td>
					</tr>
	          		</#list>
	          	</tbody>
	          </table>
	      </td>
          </tr>
        </#list>
        </tbody>
      </table>
      <#else>
      </#if>
		
      <#if orders?has_content>			
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>ID</th>
            <th>User ID</th>
            <th>Shipping Address</th>
            <th>Billing Address</th>
            <th>Payment Info</th>
            <th>Order Date</th>
            <th>Order Total</th>
            <th>Status</th>
            <th>Change Status</th>
          </tr>
        </thead>
        <tbody>
        <#list orders as order>
          <tr>
            <td>${order.getOrderNumber()?c}</td>
            <td>${order.getUserID()}</td>
            <td>${order.getShippingAddress()}</td>
            <td>${order.getBillingAddress()}</td>
            <td>${order.getPaymentMethod()}</td>
            <td>${order.getDate()}</td>
            <td>${order.getOrderTotal()?string.currency}</td>
            <td><span class="label label-info">${order.getOrderStatus()}</span></td>
            <td>
            	<select class="statusSelect">
            		<option value="pending">Pending</option>
            		<option value="shipping">Shipping</option>
            		<option value="completed">Completed</option>
            		<option value="canceled">Canceled</option>
            	</select>
            	<button type="button" value="${order.getOrderNumber()}" class="shipButton">Submit</button>
            </td>
          </tr>
        </#list>
        </tbody>
      </table>
      <#else>
      <p>There are no orders in the system.</p>
      </#if>
    </div>
	</div>
  </body>
</html>
