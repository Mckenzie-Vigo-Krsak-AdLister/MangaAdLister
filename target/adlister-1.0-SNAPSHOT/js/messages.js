let newPara;
let text;
let pos, top, left, width, height;
const chatBox = document.getElementsByClassName("chat-box")
const bubble = document.getElementsByClassName("chat-box");
const inputPara = document.getElementsByClassName("talking-para");
const form = document.getElementsByClassName("talking-form");
const sendBtn = document.getElementsByClassName("talking-btn")
sendBtn.onclick = ()=> {
    event.preventDefault();
    text = inputPara.val();

    form.addClass("animate");

    chatBox.append(
        '<div class="dialog clear"><span class="para right transparent">' +
        text +
        "</span></div>"
    );

    newPara = document.getElementsByClassName("dialog:last, para")
    pos = newPara.position();
    width = newPara.width();
    height = newPara.height();

    setTimeout(function () {
        form.removeClass("animate");
        bubble.text(text).removeClass("hide");
        bubble.animate(
            {
                top: pos.top,
                left: pos.left,
                bottom: "initial",
                width: width,
                height: height,
                padding: "6px 20px 6px 15px",
                borderRadius: "20px"
            },
            500,
            function () {
                newPara.removeClass("transparent");
                bubble.addClass("hide").removeAttr("style");
            }
        );
    }, 1000);
}