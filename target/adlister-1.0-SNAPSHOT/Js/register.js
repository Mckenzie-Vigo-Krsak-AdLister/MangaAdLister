(()=>{
   const queryString = window.location.search;
   const urlParams = new URLSearchParams(queryString);
   const err = urlParams.get('error')
   if (err === 'passwordmismatch'){
      alert('PASSWORD DOES NOT MATCH')
   }else if ( err === 'accountexists' ){

      let conf = confirm('There ia already an existing account with that email, do you want want to go to login page or create a new account? ')
      if (conf){
         window.location.href = '/login'
      }
   }
})()