var form = $(".talking-form");
var inputPara = $(".talking-para");
var bubble = $(".bubble-effect");
var chatBox = $(".chat-box");
var newPara;
var text;
var pos, top, left, width, height;

$(".talking-btn").click(function (event) {
    event.preventDefault();
    text = inputPara.val();

    form.addClass("animate");

    chatBox.append(
        '<div class="dialog clear"><span class="para right transparent">' +
        text +
        "</span></div>"
    );

    newPara = $(".dialog:last .para");
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
});