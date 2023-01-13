(async ()=>{


    const port = 8080;
    //
    // const imgContainer = document.getElementById("imgContainer")
    //

    const userId = document.getElementById("userId").value
    //
    const clearListings = () => {
        document.getElementById("listingsContainer").innerHTML = "";

    }

    const shuffle = (array)=> {
        let currentIndex = array.length,  randomIndex;

        // While there remain elements to shuffle.
        while (currentIndex != 0) {

            // Pick a remaining element.
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex--;

            // And swap it with the current element.
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
        // let array = [];
        // let url;
        //
        // let array2 = [];
        // console.log(data);

        // for (let i = 0; i < data.length; i++) {
        //     // let url;
        //     // let url1;
        //
        //     url = data[i].image;
        //     console.log(url);
        //     body.style.backgroundImage = "url(" + url + ")";
        //     array.push(url);
        //     // backgroundImg = "no-repeat"
        //     // array2.push(backgroundImg);
        //
        //
        //     // document.getElementById("bgContainer").style.backgroundImage = `url(array[i])`;
        //     // document.getElementById("bgContainer").style.backgroundSize = "cover";
        //     // document.getElementById("bgContainer").style.backgroundRepeat = array2[i];
        //
        // }
        const backgroundImg = document.getElementById("backgroundImg");
        const top50 = data.slice(0, 50)

        // let imageLoadCount = 0;
        let randoImg = Array.of(...top50, ...top50, ...top50, ...top50, ...top50, ...top50, ...top50)

        randoImg = shuffle(randoImg)

        randoImg.forEach(listing => {

            // const background = document.getElementById("background")
            // const ctxt = background.getContext("2d");
            // const currentX = 1
            // const currentY = 1
            // const maxWidth = 100
            // const maxHeight = 100
            // const maxX
            // let currentImg = 0

            const imgContain = document.createElement('div');
            imgContain.className = "imgContain";
            // imgContain.style.maxWidth = "300px !important";
            imgContain.style.backgroundImage = `url(${listing.image})`;
            // imgContain.style.backgroundSize = 'cover !important';
            // imgContain.style.backgroundRepeat = 'no-repeat !important';
            // imgContain.style.backgroundPosition = 'center !important';
            // imgContain.style.width = '300px !important';
            // imgContain.style.height = '200px !important';

            backgroundImg.appendChild(imgContain);



        });


//         const imgContainer = document.getElementById("imgContainer");
// // fix this so that it creates a background in the .jsp with all the image urls pulled form the fecth request
//
//         data.forEach(listing => {
//             const bgCover = document.createElement('div')
//             bgCover.className = "face"
//
//             const bgImg = document.createElement('img')
//             bgImg.src = listing.image
//
//             const bgMask = document.createElement('div')
//             bgMask.className = "mask"
//
//             bgCover.appendChild(bgImg)
//             bgCover.appendChild(bgMask)
//             imgContainer.appendChild(bgCover)
//
//         })


//         for (let i = 0; i < data.length; i++) {
//             url = data[i].image;
//
//         }
        // for (url of data) {
        //     imgContainer.style.backgroundImage = `url(${url.image})`;
        //
        // }
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
        console.log(data);

        const container = document.getElementById("listingsContainer")
        container.innerHTML = ""
        data.forEach(listing => {

            // const bgCover = document.createElement('div')
            // bgCover.className = "face"
            //
            // const bgImg = document.createElement('img')
            // bgImg.src = listing.image
            //
            // const bgMask = document.createElement('div')
            // bgMask.className = "mask"
            //
            // bgCover.appendChild(bgImg)
            // bgCover.appendChild(bgMask)
            // bgContainer.appendChild(bgCover)

            const listingDiv = document.createElement("div")
            listingDiv.classList.add("listing", "col-12", "col-md-6", "col-lg-4", "mb-3")

            const cardDiv = document.createElement("div")
            cardDiv.className = "card"
            cardDiv.onclick = () => window.location.href = `http://localhost:${port}/listing?id=${listing.id}`

            const cardHeader = document.createElement("div")
            cardHeader.classList.add("card-header", "d-flex", "justify-content-between")
            const title = document.createElement("h4")
            // title.innerText = listing.title
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
            // const priceP = document.createElement("p")
            // priceP.class = "fw-bold"
            // priceP.innerText = "Buy this Manga for $" + listing.price + "0"
            // const cartBtn = document.createElement("button")
            // cartBtn.id = "cartBtn"
            // cartBtn.class = "btn"
            // const cartIcon = document.createElement("i")
            // cartIcon.classList.add("bi", "bi-cart-plus")

            const imgA = document.createElement("a")
            imgA.href = "/listing?id=" + listing.id
            const img = document.createElement("img")
            img.style.width = "222px"
            img.style.height = "350px"
            img.src = listing.image
            img.alt = listing.title
            img.classList.add("img-fluid", "rounded", "mb-2", "img-thumbnail")
            const descriptionP = document.createElement("p")
            // descriptionP.style.fontFamily = "monospace"
            descriptionP.innerText = listing.description.substring(0,75) + "..."

            cardHeader.appendChild(title)
            cardHeader.appendChild(deleteBtn)
            // positionDiv.appendChild(priceP)
            // cartBtn.appendChild(cartIcon)
            // positionDiv.appendChild(cartBtn)
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
        // createForm.appendChild(formTag)

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

    // const deleteListing = document.getElementById("deleteButton")

})()