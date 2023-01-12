(async ()=>{
    // const default_view = document.getElementById('default_view')
    const search_view = document.getElementById('search_view')
    const searchinput = document.getElementById("searchTerm")


    const populateResults = (response) => {
        //Empty any previous results
        search_view.innerHTML = ""

        //Iterate through the new results
        for(let item in response){
            const listing = response[item]
            // const title = listing.title;
            // const description = listing.description;
            // const image = listing.image;

            //Generate a listing container
            const listingContainer = document.createElement('div')
            console.log(listing)
            // listingContainer.classList.add('col-6','col-md-4','col-lg-3')
            // listingContainer.style.display = 'inline-block'

            const listingTitle = document.createElement('h2')
            listingTitle.innertText = listing.title

            const listingImageLink = document.createElement('a')
            listingImageLink.href = `/listing?id=${listing.id}`

            const listingImage = document.createElement('img')
            // listingImage.classList.add('img-fluid','img-thumbnail')
            listingImage.src = listing.image

            // const descriptionParagraph = document.createElement('p')
            // descriptionParagraph.innerText = listing.description

            listingContainer.appendChild(listingTitle);

            listingImageLink.appendChild(listingImage);

            listingContainer.appendChild(listingImageLink);

            // listingContainer.appendChild(descriptionParagraph);

            //Attach it to the search_view
            search_view.appendChild(listingContainer);
        }
    }

    const doSearch = async (e) => {
        const searchTerm = e.target.value

        if(searchTerm.length === 0){
            console.log(searchTerm)
            const request = await fetch("http://localhost:8080/search", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    searchTerm: ''
                })
            })
            const response = await request.json()
            console.log(response)
            // default_view.style.display = 'none !important'
            search_view.style.display = 'grid !important'
            populateResults(response)
        }else {
            console.log(searchTerm)
            const request = await fetch("http://localhost:8080/search", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    searchTerm: searchTerm
                })
            })
            const response = await request.json()
            console.log(response)
            // default_view.style.display = 'none !important'
            search_view.style.display = 'grid !important'
            populateResults(response)
        }
    }
    searchinput.onkeyup = doSearch
    await doSearch({target: {value: ''}})


})()


