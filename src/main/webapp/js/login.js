(()=>{
    console.log("I am running.")
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const err = urlParams.get('error')

    if(err === 'authfailed'){
        alert("There was an error authenticating you. Please check your login credentials and try again.");
    }else if(err === 'notfound'){
        alert("That account was not found. Please try again.");
    }
})()