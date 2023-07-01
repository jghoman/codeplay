window.onload = function() {
    const canvas = document.getElementById("my-canvas");
    const context = canvas.getContext("2d");

    console.log(context);

    let firstx = 0;
    let secondx = canvas.width;
    let direction = "right"
    function animate() {
        // Clear the canvas
        context.clearRect(0, 0, canvas.width, canvas.height);


        // Update the circle's position
        
        if (direction === "right") {
            firstx += 2;
            secondx -= 1;
            if(firstx + 25 > canvas.width) {
                direction = "left"
            }
        } else {
            firstx -= 2;
            secondx += 1;
            if(firstx <= 0) {
                direction = "right"
            }
        }
        



        // Draw the circle
        context.beginPath();
        context.arc(firstx, 50, 25, 0, 2 * Math.PI);
        context.fillStyle = "pink";
        context.fill();

        // Draw another circle
        context.beginPath();
        context.arc(secondx, 250, 45, 0, 2 * Math.PI);
        context.fillStyle = "lightblue";
        context.fill();

        // Schedule the next frame
        requestAnimationFrame(animate);
    }

    // Start the animation
    animate();
}