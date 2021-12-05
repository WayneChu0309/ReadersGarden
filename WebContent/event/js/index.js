// window.onload = function() {
//     var counter = 1;
//     $(`input#row${counter}`)[0].checked = true;
//     counter++;
//     setInterval(function(){
//         $(`input#row${counter}`)[0].checked = true;
//         counter++;
//         if (counter > 6) {
//             counter = 1;
//         }
//     }, 3500);
    
//  };


$(".slide .container label").click(function(){
    // index 從0開始, 所以需加1
    var index = $(".slide .container label").index(this)
    counter = (index + 1);
});


$(".row-items").each(function () {
    var that = this;
    $(that).children("a").on("mouseenter", function(){
        $(this).css("opacity", "1");
        $(that).children("img").css("opacity", ".5")
    });
    $(that).children("a").on("mouseleave", function(){
        $(this).css("opacity", "0");
        $(that).children("img").css("opacity", "1")
    });
})

