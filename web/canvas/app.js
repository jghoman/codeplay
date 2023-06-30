window.onload = function() {
    const canvas = document.getElementById("my-canvas");
    const context = canvas.getContext("2d");

    console.log(context);

    const radian = Math.PI / 180;

    context.fillStyle = "red";
    context.fillRect(150, 50, 100, 50);

    // Rotate the canvas by 45 degrees
    context.rotate(45 * radian);

    // Draw another rectangle
    context.fillStyle = "blue"
    context.fillRect(150, 50, 100, 50);

}