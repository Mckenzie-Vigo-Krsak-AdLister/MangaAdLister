(async ()=>{

    const port = 8083;

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

        // const formTag = document.createElement("form")
        // formTag.action = "/profile"
        // formTag.method = "post"
        //
        // const titleLable = document.createElement("label")
        // titleLable.for = "title"
        // const titleInput = document.createElement("input")
        // titleInput.name="title"
        // titleInput.id = "title"
        // titleInput.type = "text"
        // titleInput.placeholder = "enter manga title"
        //
        // const priceLable = document.createElement("label")
        // priceLable.for = "price"
        // const priceInput = document.createElement("input")
        // priceInput.name="price"
        // priceInput.id = "price"
        // priceInput.type = "text"
        // priceInput.placeholder = "set a price for the listing"
        //
        // const createSubmitBtn = document.createElement("button")
        // createSubmitBtn.classList.add("btn", "btn-secondary")
        // createSubmitBtn.type = "button"
        // createSubmitBtn.id = "submitBtn"
        // createSubmitBtn.value = "Submit"
        // createSubmitBtn.innerText = "Submit"
        //
        // formTag.appendChild(titleLable)
        // formTag.appendChild(titleInput)
        // formTag.appendChild(priceLable)
        // formTag.appendChild(priceInput)
        // formTag.appendChild(createSubmitBtn)
        // createForm.appendChild(formTag)
        //
        // const submitBtn = document.getElementById("submitBtn")
        //
        // submitBtn.onclick = ()=> {
        //     createSearchTerm(document.getElementById("title").value)
        //     createForm.style.display = "none"
        //     addListingBtn.style.display = "inline-block"
        // }




    }
})()