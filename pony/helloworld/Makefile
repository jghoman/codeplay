compile: main.pony
	docker run -v $(shell pwd):/src/main ponylang/ponyc

run: compile
	docker run -v $(shell pwd):/src/main ponylang/ponyc ./main

clean:
	rm main

pull_ponyc:
	docker pull ponylang/ponyc:latest