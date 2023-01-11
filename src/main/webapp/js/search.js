(()=>{

alert("I work")

    // document.getElementById("searchTerm").addEventListener("keyup", function (e){
    //
    //     let searchItem = ""
    //          searchItem += e.key;
    //         if (searchItem.length > 0) {
    //             let form = document.querySelector("form")
    //             form.submit()
    //             //     console.log(form)
    //             // getSearch()
    //         } else {
    //
    //         }
    // })



   const getSearch = async (e) => {
       const searchTerm = e.target.value
       console.log(searchTerm)
       const request = await fetch ("http://localhost:8080/search", {
           method:"POST",
           headers: {
               "Content-Type":"application/json"
           },
           body: JSON.stringify({
               searchTerm
           })
       })
       const response = await request.json()
       console.log(response)

    }
    document.getElementById("searchTerm").oninput = getSearch

}
)()


