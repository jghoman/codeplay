use "time"

class FromPackage
    let greeting: String

    new create(greeting': String) =>
        greeting = greeting'

    fun do_greeting(env: Env) => env.out.print(greeting + ": " + Time.now()._1.string())
