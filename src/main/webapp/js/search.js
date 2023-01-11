(()=>{
    const searchinput = document.getElementById("searchTerm")
    searchinput.onchange = async (e) => {
        const searchTerm = e.target.value
        console.log(searchTerm)
        const request = await fetch ("http://localhost:8080/search", {
            method:"POST",
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                searchTerm : searchTerm
            })
        })
        const response = await request.json()
        console.log(response)
    }
})()


