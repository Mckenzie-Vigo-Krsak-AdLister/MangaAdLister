

    document.getElementById("searchTerm").addEventListener("keyup", function (e){

        let searchItem = ""
             searchItem += e.key;
            if (searchItem.length > 0) {
                let form = document.querySelector("form")
                form.submit()
                //     console.log(form)
                // getSearch()
            } else {

            }
    })



    // function getSearch(){
    //     fetch("jdbc:mysql://localhost:3306/manga_adlister?allowPublicKeyRetrieval=true&useSSL=false/-uroot-pcodeup").then(response => response.json()).then(response =>{console.log(response)})
    //
    // }


