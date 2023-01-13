(async ()=>{

    const search_view = document.getElementById('search_view')
    const searchinput = document.getElementById("searchTerm")
    const port = 8083;

    const populateResults = (response) => {
        if (search_view){

            //Empty any previous results
            search_view.innerHTML = ""

            //Iterate through the new results
            for(let item in response) {
                const listing = response[item]

                const listingDiv = document.createElement("div")
                listingDiv.classList.add("container", "listing", "col-12", "col-md-6", "col-lg-4", "mb-3")

                const cardDiv = document.createElement("div")
                cardDiv.className = "card"
                cardDiv.onclick = () => window.location.href = `http://localhost:${port}/listing?id=${listing.id}`

                const cardHeader = document.createElement("div")
                cardHeader.classList.add("card-header", "d-flex", "justify-content-center")
                const title = document.createElement("h4")
                title.innerText = listing.title.length > 10? listing.title.substring(0, 10) + "...": listing.title

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
                imgA.appendChild(img)
                cardBody.appendChild(positionDiv)
                cardBody.appendChild(imgA)
                cardBody.appendChild(descriptionP)
                cardDiv.appendChild(cardHeader)
                cardDiv.appendChild(cardBody)
                listingDiv.appendChild(cardDiv)
                search_view.appendChild(listingDiv)

                // cardBody.appendChild(positionDiv)
                // cardDiv.appendChild(cardFooter)
                // cardDiv.appendChild(cardBody)
                // listingContainer.appendChild(cardDiv)
                //
                // listingContainer.appendChild(listingTitle);
                //
                // listingImageLink.appendChild(listingImage)
                //
                // listingContainer.appendChild(listingImageLink);
                //
                // search_view.appendChild(listingContainer);
            }
        }

    }

    const doSearch = async (e) => {
        const searchTerm = e.target.value

        if(searchTerm.length === 0){
            const request = await fetch(`http://localhost:${port}/search`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    searchTerm: ''
                })
            })
            const response = await request.json()
            populateResults(response)
        }else {
            const request = await fetch(`http://localhost:${port}/search`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    searchTerm: searchTerm
                })
            })
            const response = await request.json()
            search_view.style.display = 'grid !important'
            populateResults(response)
        }
    }
    searchinput.onkeyup = doSearch
    await doSearch({target: {value: ''}})

})()


