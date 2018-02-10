use "collections"

actor MyActor
    var name: String val
    var env: Env

    new create(name': String, env': Env) =>
        name = name'
        env = env'

    be sayHi() =>
        for i in Range(0, 100) do
            env.out.print(i.string() + ": Hi, I'm " + name)
        end

actor Main
    be sayHiFromMain(env: Env) =>
        for i in Range(0, 100) do
            env.out.print(i.string() + ": Hi from main!")
        end

    new create(env: Env) =>
        var actorA = MyActor("actorA", env)
        var actorB = MyActor("actorB", env)
        actorA.sayHi()
        actorB.sayHi()
        env.out.print("Can I still use env?")
        sayHiFromMain(env)

