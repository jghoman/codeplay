window.onload = function() {
    const canvas = document.getElementById("my-canvas");
    const context = canvas.getContext("2d");

    console.log(context);

    context.lineWidth = 20;
    // context.strokeStyle = "red";

    // context.strokeRect(50, 50, 200, 200);
    // context.beginPath();
    // context.moveTo(50, 50);
    // context.lineTo(150, 150);
    // context.stroke();

    context.lineWidth = 20;

    const gradient = context.createLinearGradient(50, 50, 200, 200);
    gradient.addColorStop(0, "red");
    gradient.addColorStop(1, "blue");
    context.strokeStyle = gradient;

    // Draw a rectangle with a gradient stroke
    context.strokeRect(50, 50, 200, 200);
}