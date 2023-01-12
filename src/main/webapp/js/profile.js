(async ()=>{

    const port = 8080;

    const clearListings = () => {
        document.getElementById("listingsContainer").innerHTML = "";
    }

    const getAllListings = async () => {
        const userId = document.getElementById("userId").value
        const response = await fetch(`http://localhost:${port}/profilelistings`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'

            },
            body: JSON.stringify({
                userId: userId
            })
        });

        const data = await response.json();
        console.log(data);

        const container = document.getElementById("listingsContainer")
        container.innerHTML = ""
        data.forEach(listing => {

            const listingDiv = document.createElement("div")
            listingDiv.classList.add("listing", "col-12", "col-md-6", "col-lg-4", "mb-3")

            const cardDiv = document.createElement("div")
            cardDiv.className = "card"

            const cardHeader = document.createElement("div")
            cardHeader.classList.add("card-header", "d-flex", "justify-content-between")
            const title = document.createElement("h2")
            title.innerText = listing.title
            const deleteBtn = document.createElement("button")
            deleteBtn.classList.add("btn", "btn-danger")
            deleteBtn.id = "deleteButton"
            deleteBtn.setAttribute("listing", listing.id)
            deleteBtn.innerText = "X"

            deleteBtn.onclick = deleteFunction

            const cardBody = document.createElement("div")
            cardBody.classList.add("card-body", "d-flex", "flex-column", "align-items-center")

            const positionDiv = document.createElement("div")
            positionDiv.classList.add("d-flex", "justify-content-between", "align-items-start")
            const priceP = document.createElement("p")
            priceP.class = "fw-bold"
            priceP.innerText = "Buy this Manga for $" + listing.price + "0"
            const cartBtn = document.createElement("button")
            cartBtn.id = "cartBtn"
            cartBtn.class = "btn"
            const cartIcon = document.createElement("i")
            cartIcon.classList.add("bi", "bi-cart-plus")

            const imgA = document.createElement("a")
            imgA.href = "/listing?id=" + listing.id
            const img = document.createElement("img")
            img.src = listing.image
            img.alt = listing.title
            img.classList.add("img-fluid", "rounded", "mb-2", "img-thumbnail")
            const descriptionP = document.createElement("p")
            descriptionP.innerText = listing.description

            cardHeader.appendChild(title)
            cardHeader.appendChild(deleteBtn)
            positionDiv.appendChild(priceP)
            cartBtn.appendChild(cartIcon)
            positionDiv.appendChild(cartBtn)
            imgA.appendChild(img)
            cardBody.appendChild(positionDiv)
            cardBody.appendChild(imgA)
            cardBody.appendChild(descriptionP)
            cardDiv.appendChild(cardHeader)
            cardDiv.appendChild(cardBody)
            listingDiv.appendChild(cardDiv)
            container.appendChild(listingDiv)

        })

        return data;
    }

    const deleteFunction = async function () {
        console.log("i work");
        const userId = document.getElementById("userId").value
        const listingId = this.getAttribute("listing")

        const deleteRequest = await fetch(`http://localhost:${port}/deletelisting`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // 'Cookie': document.cookie
            },
            body: JSON.stringify({
                listingId: listingId,
                userId: userId
            })
        })

        const response = await deleteRequest.json()
        if (response){
            clearListings()
            await getAllListings()
            assignedDeleteHandlers()
        }
        console.log(response);

    }

    await getAllListings();

    const createSearchTerm = async (srcTrm, price)=> {
        try{
            const userId = document.getElementById("userId").value
            const options = {
                method: 'GET',
                headers: {
                    'X-RapidAPI-Key': '577da23267mshfa52157d7354156p1f0741jsna34b9317a8ae',
                    'X-RapidAPI-Host': 'myanimelist.p.rapidapi.com'
                }
            };
            const externalApi = await fetch(`https://myanimelist.p.rapidapi.com/manga/search/${srcTrm}`, options);

            const data = (await externalApi.json())[0];

            const response = await fetch(`http://localhost:${port}/addlisting`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // 'Cookie': document.cookie
                }, body: JSON.stringify({
                    newListing: data,
                    userId: userId,
                    price: price
                })
            })

            const created = await response.json();
            console.log(created);

            clearListings()
            await getAllListings()

        } catch (err){
            console.log(err);
        }
    }

    const addListingBtn = document.getElementById("addListingBtn")
    const createForm = document.getElementById("createForm")

    const form = ()=> {

        const formTag = document.createElement("form")
        formTag.action = "/profile"
        formTag.method = "post"

        const titleLable = document.createElement("label")
        titleLable.for = "title"
        const titleInput = document.createElement("input")
        titleInput.name="title"
        titleInput.id = "title"
        titleInput.type = "text"
        titleInput.placeholder = "enter manga title"

        const priceLable = document.createElement("label")
        priceLable.for = "price"
        const priceInput = document.createElement("input")
        priceInput.name="price"
        priceInput.id = "price"
        priceInput.type = "text"
        priceInput.placeholder = "set a price for the listing"

        const createSubmitBtn = document.createElement("button")
        createSubmitBtn.classList.add("btn", "btn-secondary")
        createSubmitBtn.type = "button"
        createSubmitBtn.id = "submitBtn"
        createSubmitBtn.value = "Submit"
        createSubmitBtn.innerText = "Submit"

        formTag.appendChild(titleLable)
        formTag.appendChild(titleInput)
        formTag.appendChild(priceLable)
        formTag.appendChild(priceInput)
        formTag.appendChild(createSubmitBtn)
        // createForm.appendChild(formTag)

        createSubmitBtn.onclick = ()=> {
            createSearchTerm(document.getElementById("title").value, document.getElementById("price").value)
            createForm.innerHTML = ""
            addListingBtn.style.display = "inline-block"
        }

        return formTag;

    }

    addListingBtn.onclick = async ()=> {

        createForm.innerHTML = "";
        createForm.appendChild(form())
        addListingBtn.style.display = "none"

    }

    // const deleteListing = document.getElementById("deleteButton")

})()