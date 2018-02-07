
class Wombat
    let name: String box

    new iso create(name': String) =>
        name = name'
    
    fun box string(): String box => name

actor Main
    let env: Env
    // Mutable reference capabilities
    //   * None of these allow other actors to read frpom or write to the object

    // iso: No other variable by any actor can read or write (read and write unique)
    // var anIso: String iso = "iso"

    // trn: No other variable by any actor can write to that object AND
    //      No other variable can be used by other actors to read or write.
    //      This allows multiple variables in same actor to *read* from it (write unique)
    // var aTrn: String trn = "trn"

    // ref: No other variable can be used by other actors to read or write to object
    //      This allows other variables in same actor to read and write to object
    //      Not at all unique.
    // var aRef: String ref = "ref"

    // Immutable reference capabilities
    // val: No other variable can be used by any actor to write to the object
    //      (globally immutable)
    var aVal: String val = "val"

    // box: No other variable can be used by other actors to write to that object
    //      Other actors may be able to read objecct.
    //      Other variables in same actor may be able to write to object.
    //      (The above two conditions are mutually exclusive)
    var aBox: String box = "box"

    // Opaqe reference capability
    // tag: Makes no guranteee about a variable.  Can't be used to read or write
    var aTag: String tag = "tag"

    fun consume_a_variable(a: Wombat iso) =>
        env.out.print("Consuming that wombat!")
        var b: Wombat iso = consume a
        // Not allowed, as have consume the variable...
        // var c: Wombat = a

    new create(env': Env) =>
        env = env'

        let w = Wombat("huh")
        env.out.print("Let's go ahead and consume a wombat...")

        consume_a_variable(Wombat("Wommie") )
        //consume_a_variable(w)
        
        //env.out.print("My wombat = " + w.string())