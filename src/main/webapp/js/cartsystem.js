const addToCartButtons = document.getElementsByClassName("addToCartButton")

// const userId = document.getElementById("userId")
const cartSizeLabel = document.getElementById("cartSizeLabel")
const cartItems = document.getElementById("cartItems");
const addToCartClick = async function(e) {
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
   await getCartSize()
   alert("Item added to your cart!.")
}

Array.from(addToCartButtons).forEach(button=>{
   button.onclick = addToCartClick;
})


export const getCartSize = async () => {
   setTimeout(async ()=>{
      const fetchReq = await fetch('http://localhost:8080/getcartsize',{
         method : 'POST',
         body : JSON.stringify({
            // userId : userId.value
         })
      })

      const resp = await fetchReq.json()
      console.log("Cart Size:")
      console.log(resp)

      cartSizeLabel.innerText = resp
   },300)
}

const removeCartItem = async (item) => {
   const request = await fetch('http://localhost:8080/removeFromCart',{
      method : "POST",
      headers : {
         "Content-Type" : "application/json",
      },
      body : JSON.stringify({
         item: item.item
      })
   })

   const response = await request.json()
   console.log(response)

   await getCartItems()
   await getCartSize()
}

const generateCartItem = (item)=> {
   console.log("Generating cart item for:")
   console.log(item)
   //Main cart item container
   const container = document.createElement('div')
   container.className = "cart_item_container"

   //Image block
   const image = document.createElement('img')
   image.src = item.listing.image
   image.className = "cart_item_image"
   container.appendChild(image);

   //Title
   const title = document.createElement('div')
   title.innerText = item.listing.title
   title.className = "cart_item_title"
   container.appendChild(title)

   //Seller's name
   const seller = document.createElement('div')
   seller.innerText = item["owner"]["firstName"] + " " + item["owner"]["lastName"];
   seller.className = "cart_item_seller"
   container.appendChild(seller)

   //Price
   const price = document.createElement("div")
   price.innerText = "$" + item["listing"]["price"].toFixed(2)
   price.className = "cart_item_price"
   container.appendChild(price)

   //Description substring
   const description = document.createElement("div")
   description.innerText = item["listing"]["description"].substring(0,10) + "..."
   description.className = "cart_item_description"
   container.appendChild(description)


   //Remove button
   const removebtn = document.createElement('button')
   removebtn.innerText = "Remove"
   removebtn.className = "cart_item_remove_button"
   removebtn.onclick = (e) => removeCartItem(item)

   const removeBtnContainer = document.createElement("div")
   removeBtnContainer.appendChild(removebtn)

   container.appendChild(removeBtnContainer)

   return container;
}

export const getCartItems = async () => {
   const request = await fetch('http://localhost:8080/cart',{
      method : 'POST',
      body : JSON.stringify({})
   })

   const response = await request.json()
   console.log("Cart Items:")
   console.log(response)
   if(response) {
      //Clear the list of cart items
      cartItems.innerHTML = "";

      //Iterate through the response items and display them
      response.forEach(item=>{
         cartItems.appendChild(generateCartItem(item))
      })
   }
}

if(cartItems){
   setTimeout(async () => {
      await getCartItems();
   })
}


const cartButton = document.getElementById("cartButton");
if(cartButton) {
   cartButton.onclick = () => {
      console.log("Cart button clicked.")
      window.location.href = "/cart"
   }
}