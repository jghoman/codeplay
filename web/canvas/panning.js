window.onload = function () {
    const canvas = document.getElementById("my-canvas");
    const context = canvas.getContext("2d");

    console.log(context);

    const bgImg = new Image();
    bgImg.src = "background.png";

    const runnerImg = new Image();
    runnerImg.src = "sprites.png";

    const camera = {
        x: 0,
        y: 0,
        width: canvas.width,
        height: canvas.height,
        cameraX: 0,
        cameraY: 0,

        update: function () {
            const div = 2;
            // Update camera position
            this.cameraX = runner.x - this.width / div;
            this.cameraY = runner.y - this.height / div;

            // Keep camera within bounds of game world
            if (this.cameraX < 0) {
                this.cameraX = 0;
            }

            if (this.cameraX + this.width > bgImg.width) {
                this.cameraX = bgImg.width - this.width;
            }

            if (this.cameraY < 0) {
                this.cameraY = 0;
            }

            if (this.cameraY + this.height > bgImg.height) {
                this.cameraY = bgImg.height - this.height;
            }


        },
    };

    const background = {
        draw: function () {
            // Draw background image with camera offset
            context.drawImage(bgImg, camera.cameraX, camera.cameraY, camera.width, camera.height, 0, 0, camera.width, camera.height);
        },
    };

    const runner = {
        frameWidth: 165,
        frameHeight: 250,
        numFrames: 21,
        currentFrame: 0,
        numRows: 3,
        framesPerRow: 7,
        rowHeight: 292,
        frameRate: 12,
        lastUpdate: 0,
        x: 50,
        y: 600,
        speed: 5,

        draw: function () {
            // Draw character image with camera offset
            row = Math.floor(this.currentFrame / this.framesPerRow);
            context.drawImage(runnerImg,
                ((this.currentFrame % this.framesPerRow) * this.frameWidth),
                row * this.rowHeight,
                this.frameWidth,
                this.frameHeight,
                this.x - camera.cameraX,
                this.y - camera.cameraY,
                this.frameWidth,
                this.frameHeight);
        },

        update: function (time) {
            if (time - this.lastUpdate > 1000 / this.frameRate) {
                this.currentFrame = (this.currentFrame + 1) % this.numFrames;
                this.lastUpdate = time;
            }

            if (keys.ArrowLeft) {
                this.x -= this.speed;
            }

            if (keys.ArrowRight) {
                this.x += this.speed;
            }

            if (keys.ArrowUp) {
                this.y -= this.speed;
            }

            if (keys.ArrowDown) {
                this.y += this.speed;
            }
        },

    };

    function gameLoop(time) {
        context.clearRect(0, 0, canvas.width, canvas.height);

        camera.update();
        runner.update(time);

        background.draw();
        runner.draw();

        requestAnimationFrame(gameLoop);
    }

    const keys = {};
    window.addEventListener("keydown", (e) => { keys[e.key] = true; })
    window.addEventListener("keyup", (e) => { keys[e.key] = false; })

    // wait for images to load before starting the game loop
    Promise.all([loadImage(bgImg), loadImage(runnerImg)]).then(() => { gameLoop(); });

    // Load image with promise
    function loadImage(img) {
        return new Promise((resolve, reject) => {
            img.onload = () => resolve(img);
            img.onerror = reject;
        });
    }


}