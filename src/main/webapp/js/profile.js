(async ()=>{
    const port = 8080;


    const userId = document.getElementById("userId").value

    const clearListings = () => {
        document.getElementById("listingsContainer").innerHTML = "";

    }

    const shuffle = (array)=> {
        let currentIndex = array.length,  randomIndex;


        while (currentIndex != 0) {


            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex--;


            [array[currentIndex], array[randomIndex]] = [
                array[randomIndex], array[currentIndex]];
        }

        return array;
    }

    const bgCoverImg = async () => {

        const response = await fetch(`http://localhost:${port}/listings`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'

            },
            body: JSON.stringify({
                userId: userId
            })
        });

        const data = await response.json();

        const backgroundImg = document.getElementById("backgroundImg");
        const top50 = data.slice(0, 50)

        let randoImg = Array.of(...top50, ...top50, ...top50, ...top50, ...top50, ...top50, ...top50)

        randoImg = shuffle(randoImg)

        randoImg.forEach(listing => {

            const imgContain = document.createElement('div');
            imgContain.className = "imgContain";
            imgContain.style.backgroundImage = `url(${listing.image})`;
            backgroundImg.appendChild(imgContain);

        });
    }

    await bgCoverImg()

    const getAllListings = async () => {

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

        const container = document.getElementById("listingsContainer")
        container.innerHTML = ""
        data.forEach(listing => {

            const listingDiv = document.createElement("div")
            listingDiv.classList.add("container", "listing", "col-12", "col-md-6", "col-lg-4", "mb-3")

            const cardDiv = document.createElement("div")
            cardDiv.className = "card"
            cardDiv.onclick = () => window.location.href = `http://localhost:${port}/listing?id=${listing.id}`

            const cardHeader = document.createElement("div")
            cardHeader.classList.add("card-header", "d-flex", "justify-content-between")
            const title = document.createElement("h4")
            title.innerText = listing.title.length > 10? listing.title.substring(0, 10) + "...": listing.title
            const deleteBtn = document.createElement("button")
            deleteBtn.classList.add("btn", "btn-danger")
            deleteBtn.id = "deleteButton"
            deleteBtn.setAttribute("listing", listing.id)
            deleteBtn.innerText = "X"
            deleteBtn.style.maxHeight = "38px"
            deleteBtn.style.maxWidth = "36px"
            deleteBtn.onclick = deleteFunction

            const cardBody = document.createElement("div")
            cardBody.classList.add("card-body", "d-flex", "flex-column", "align-items-center")

            const positionDiv = document.createElement("div")
            positionDiv.classList.add("d-flex", "justify-content-between", "align-items-start")

            const imgA = document.createElement("a")
            imgA.href = "/listing?id=" + listing.id
            const img = document.createElement("img")
            img.style.width = "222px"
            img.style.height = "350px"
            img.src = listing.image
            img.alt = listing.title
            img.classList.add("img-fluid", "rounded", "mb-2", "img-thumbnail")
            const descriptionP = document.createElement("p")
            descriptionP.innerText = listing.description.substring(0,75) + "..."

            cardHeader.appendChild(title)
            cardHeader.appendChild(deleteBtn)
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

    const deleteFunction = async function (e) {

        e.stopPropagation()

        const userId = document.getElementById("userId").value
        const listingId = this.getAttribute("listing")

        const deleteRequest = await fetch(`http://localhost:${port}/deletelisting`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
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

        }
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
        formTag.className = "createForm"

        const titleLable = document.createElement("label")
        titleLable.for = "title"
        const titleInput = document.createElement("input")
        titleInput.name="title"
        titleInput.id = "title"
        titleInput.type = "text"
        titleInput.placeholder = "enter manga title"
        titleInput.classList.add("createForm_input")

        const priceLable = document.createElement("label")
        priceLable.for = "price"
        const priceInput = document.createElement("input")
        priceInput.name="price"
        priceInput.id = "price"
        priceInput.type = "text"
        priceInput.placeholder = "set a price for the listing"
        priceInput.classList.add("createForm_input")

        const createSubmitBtn = document.createElement("button")
        createSubmitBtn.classList.add("btn", "btn-secondary","createForm_submit")
        createSubmitBtn.type = "button"
        createSubmitBtn.id = "submitBtn"
        createSubmitBtn.value = "Submit"
        createSubmitBtn.innerText = "Submit"

        formTag.appendChild(titleLable)
        formTag.appendChild(titleInput)
        formTag.appendChild(priceLable)
        formTag.appendChild(priceInput)
        formTag.appendChild(createSubmitBtn)

        createSubmitBtn.onclick = ()=> {
            createSearchTerm(document.getElementById("title").value, document.getElementById("price").value)
            createForm.style.display = "none";
            addListingBtn.style.display = "inline-block"
        }

        return formTag;
    }

    addListingBtn.onclick = async ()=> {
        createForm.style.display = "block" //Diaplay it
        createForm.innerHTML = "" //Clear it
        createForm.appendChild(form()) //Fill it
        addListingBtn.style.display = "none" //Hide listing btn

    }

})()