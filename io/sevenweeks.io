"Howdy\n" print

# Clone an object
Vehicle := Object clone

Vehicle print
Vehicle description := "Something to take you places."
Vehicle print
Vehicle description = "Now it's something else!"
Vehicle print

Vehicle slotNames print

Car := Vehicle clone
Car slotNames print
"\n" print
Car type print
"\n" print

ferrari := Car clone
ferrari type print
"\n" print
