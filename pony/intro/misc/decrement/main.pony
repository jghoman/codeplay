
actor Decrementer
    var name:String
    var next:(Decrementer | None)

    new create(name': String, next': (Decrementer|None)) =>
        name = name'
        next = next'

    be decrement(value: U32, env:Env) =>
        match next
        | None => env.out.print("I'm " + name + ", the last and the value is " + value.string())
        | let n:Decrementer => 
            env.out.print(name + " sending it on")
            n.decrement(value - 1, env)
        end
    
actor Main
    new create(env: Env) =>
        let last:Decrementer = Decrementer("Last One", None)
        let nextToLast:Decrementer = Decrementer("Next to Last", last)
        let first:Decrementer = Decrementer("First", nextToLast)
        first.decrement(42, env)