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
      console.log(await fetchReq.json())
   }


   // await fetchFormTest()
   await fetchBodyTest()
})()