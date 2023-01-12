(async ()=>{
   const addToCartButton = document.getElementById('addToCart');
   addToCartButton.onclick = async function(e) {
      const listingId = this.getAttribute('listing')
      // console.log("Clicked add to cart on listing with id " + listingId)
      const fetchReq = await fetch('http://localhost:8080/addtocart',{
         method : 'POST',
         body : JSON.stringify({
            listingId : listingId
         })
      })
      console.log("Response from add to cart request")
      const cart = await fetchReq.json()
      console.log(cart)

      const cartSize = document.getElementById("cartSize");
      cartSize.innerText = cart.length.toString();
   }


   const getCartItemCount = async () => {
      //Do an api call for the cart size
      const request = await fetch("http://localhost:8080/getcartsize",{
         method: "POST"
      })

      //Grab the response from the api call
      const response = await request.json()

      //Update the cart size indicator
      const cartSize = document.getElementById("cartSize");
      cartSize.innerText = response["cart_size"]

      //Return the cart size
      return response
   }

   //When the page loads update the cart size indicator
   await getCartItemCount();
})()