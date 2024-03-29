window.onload = function() {
    const canvas = document.getElementById("my-canvas");
    const context = canvas.getContext("2d");

    console.log(context);

    const background = {
        image: new Image(),
        load:function() {
            this.image.src = "background.png"
        },
        draw: function() {
            context.drawImage(this.image, 0, 0, canvas.width, canvas.height);
        }
    }

    const sprite = {
        image:new Image(),
        frameWidth: 165,
        frameHeight: 250,
        numFrames: 7,
        currentFrame: 0,
        frameRate: 12,
        lastUpdate: 0,
        x: 0,
        y: 0,
        vx: 0,

        load:function() {
            this.image.src = "sprites.png";
        },

        draw:function() {
            context.drawImage(this.image, this.currentFrame * this.frameWidth, 0, this.frameWidth, this.frameHeight, this.x, this.y, this.frameWidth, this.frameHeight);
        },

        update:function(time) {
            //console.log("time = " + time + ", " + this.lastUpdate + ", " + (1000 / this.frameRate));
            this.vx = 2;
            this.x += this.vx;
            
            this.y = canvas.height - this.frameHeight;

            if(time - this.lastUpdate > 1000 / this.frameRate) {
                //console.log("I'm updating because time elapsed!");
                this.currentFrame = (this.currentFrame + 1) % this.numFrames;
                this.lastUpdate = time;
            }
        }
    };

    background.load();
    sprite.load();

    function animate(time) {
        context.clearRect(0, 0, canvas.width, canvas.height);

        background.draw();

        sprite.update(time)
        sprite.draw();


        requestAnimationFrame(animate);
    }


    // Start the animation
    animate();
}