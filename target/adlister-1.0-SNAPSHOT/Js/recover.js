(()=>{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const sentFlag = urlParams.get('sent')
    const error = urlParams.get('error')
    const ret = urlParams.get('return')

    if(sentFlag){
        alert("The recovery email has been sent to your email. Please check your inbox and spam folders and click on the link.")
    }

    if(error === 'notfound'){
        const makeNew = confirm("There's no account with that email, would you like to register a new account?")
        if(makeNew){
            window.location.href="/register"
        }
    }

    if(ret){
        //Grab the nonce
        const nonce = urlParams.get('nonce')

        //Hide the email input form
        const email_input_form = document.getElementById('email_input_form')
        email_input_form.style.display = 'none'

        //Grab a handle to the main card
        const main_card = document.getElementById('main_card')

        //Create a form to reset the password
        const form = document.createElement("form")
        form.classList.add("reset_password_form")
        form.action = "/resetpassword"
        form.method = "POST"

        //Create a password label
        const passLabel = document.createElement('label')
        passLabel.setAttribute('for',"password")

        //Create password label text span
        const passSpan = document.createElement('span')
        passSpan.innerText = "New Password"

        //Append it to the label
        passLabel.appendChild(passSpan)

        //Create a password input
        const pass = document.createElement('input')
        pass.type = 'password'
        pass.placeholder = "New Password"
        pass.id = "password"
        pass.name = "password"

        //Append the password input to the password label
        passLabel.appendChild(pass)

        //Create a password paragraph and append the password label to it
        const passParagraph = document.createElement('p')
        passParagraph.appendChild(passLabel)

        //Create a password confirmation label
        const passConfLabel = document.createElement('label')
        passConfLabel.setAttribute('for','password_conf')

        //Create pass conf text span
        const passConfSpan = document.createElement('span')
        passConfSpan.innerText = "Confirm New Password"

        //Add it to the label
        passConfLabel.appendChild(passConfSpan);

        //Create a password confirmation input
        const pass_conf = document.createElement('input')
        pass_conf.type = 'password'
        pass_conf.placeholder = "New Password Confirmation"
        pass_conf.id = "password_conf"
        pass_conf.name = "password_conf"

        //Append the password conf input to the password conf label
        passConfLabel.appendChild(pass_conf)

        //Create a password conf paragraph and append the password conf label to it
        const passConfParagraph = document.createElement('p')
        passConfParagraph.appendChild(passConfLabel)

        //Create a submit button for the form
        const submitBtn = document.createElement('button')
        submitBtn.type = 'submit'
        submitBtn.innerText = "Update Password"

        //Create a submit button paragraph and append the submit button to it
        const submitBtnParagraph = document.createElement('p')
        submitBtnParagraph.append(submitBtn)
        submitBtn.onclick = (e) => {
            e.preventDefault(); //Don't submit the form right away

            //Instead do a validation check on the input and make sure the passwords match
            if(pass.value === pass_conf.value){
                //Submit the form with a fetch request
                console.log("Submitting the form now.")
            }else{
                alert("Your passwords do not match, please check them and try again.");
            }
        }

        //Create a hidden nonce field
        const nonceField = document.createElement('input')
        nonceField.type = 'hidden'
        nonceField.value = nonce

        //Append the password and password conf paragraphs, the submit button and the hidden nonce field to the form
        form.appendChild(passParagraph)
        form.appendChild(passConfParagraph)
        form.appendChild(nonceField)
        form.appendChild(submitBtnParagraph)

        //Append the form to the main card
        main_card.append(form);
    }

})()