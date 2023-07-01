window.onload = function() {
    const canvas = document.getElementById("my-canvas");
    const context = canvas.getContext("2d");

    console.log(context);

    let firstx = canvas.width / 2;
    let firsty = canvas.height / 2;

    let firstvx = 5;
    let firstvy = 2;

    const firstradius = 25;

    let secondx = 30;
    let secondy = 30;

    let secondvx = 7;
    let secondvy = 9;

    const secondradius = 30;


    function animate() {
        context.clearRect(0, 0, canvas.width, canvas.height);

        // Update ball position and velocity
        firstx += firstvx;
        firsty += firstvy;
        secondx += secondvx;
        secondy += secondvy;

        // Check for collision
        if((secondx - firstx) * (secondx - firstx) + (secondy - firsty) * (secondy - firsty) <= (firstradius + secondradius) * (firstradius + secondradius)) {
            firstvx = -firstvx;
            firstvy = -firstvy;
            secondvx = -secondvx;
            secondvy = -secondvy;
        } else {
            if(firstx + firstradius > canvas.width || firstx - firstradius < 0) {
                firstvx = -firstvx;
            }
            if(firsty + firstradius > canvas.height || firsty - firstradius < 0) {
                firstvy = -firstvy;
            }

            if(secondx + secondradius > canvas.width || secondx - secondradius < 0) {
                secondvx = -secondvx;
            }
            if(secondy + secondradius > canvas.height || secondy - secondradius < 0) {
                secondvy = -secondvy;
            }
        }
        // Draw the balls
        context.beginPath();
        context.arc(firstx, firsty, firstradius, 0, 2 * Math.PI);
        context.fillStyle = "red";
        context.fill();

        context.beginPath();
        context.arc(secondx, secondy, secondradius, 0, 2 * Math.PI);
        context.fillStyle = "blue";
        context.fill();

        requestAnimationFrame(animate);
    }


    // Start the animation
    animate();
}