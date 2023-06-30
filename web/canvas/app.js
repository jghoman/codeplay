window.onload = function() {
    const canvas = document.getElementById("my-canvas");
    const context = canvas.getContext("2d");

    console.log(context);

    context.fillStyle = "black";
    context.font = "24px sans-serif";
    context.fillText("Hello, world!", 50, 50);

}