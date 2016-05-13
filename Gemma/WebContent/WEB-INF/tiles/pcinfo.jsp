<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="wrapper">
	<div class="checkout container">

		<header>
			<h1>
				Hi, <br />Let's test a transaction
			</h1>
			<p>Make a test payment with Braintree using PayPal or a card</p>
		</header>

		<form id="payment-form" method="post" action="/processcart">
			<section>
				<div class="bt-drop-in-wrapper">
					<div id="bt-dropin"></div>
				</div>

				<label for="amount"> <span class="input-label">Amount</span>
						<input id="amount" name="amount" type="tel" min="1"
							placeholder="Amount" value="10" />
				</label>
			</section>
			<button class="button" type="submit">
				<span>Test Transaction</span>
			</button>
		</form>
	</div>
</div>

<script src="https://js.braintreegateway.com/v2/braintree.js"></script>
<script type="text/javascript">
    /*<![CDATA[*/
    alert("Here");
    var checkout = new Demo({
      formID: 'payment-form'
    })

    var client_token = ${clientToken};
    braintree.setup(client_token, "dropin", {
      container: "bt-dropin"
    });
    /*]]>*/
  </script>