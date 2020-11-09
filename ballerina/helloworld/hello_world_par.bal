import ballerina/io;

public function main() {
    @strand {thread: "any"}
    worker w1 {
        io:println("Hello, world! #m");
    }

    @strand {thread: "any"}
    worker w2 {
        io:println("Hello, world! #n");
    }

    @strand {thread: "any"}
    worker w3 {
        io:print("Hello, world! #k");
    }
}