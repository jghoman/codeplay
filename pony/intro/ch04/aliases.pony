actor AliasMain
    // Types of aliasing
    // * Assigning a value to a variable
    // * Passing a value as an argument to a method
    // * Call a method creates an alias to the thing being called
    let env: Env

    // Nope: Only one iso can point to a variable
    //fun test(a: Wombat iso) =>
    //    var b: Wombat iso = a

    // Tag doesn't allow read nor write, so this is ok... 
    fun test_iso_aliasing(a: Wombat iso) =>
        var b: Wombat tag = a

    // Box allows reading, but not writing, so is ok
    fun test_trn_aliasing(a: Wombat trn) =>
        var b: Wombat box = a
    
    // The following is confusing and, I believe, a bug
    // in the tutorial
    fun test_ephemeral_type(a: Wombat iso):None val =>
        consume a

    fun test_ephemeral_type2(a: Wombat iso): Wombat iso^ =>
        consume a

    fun test_alias_types[A](a: A) =>
        var b: A! = a

    new create(env': Env) =>
        env = env'

        let w:None val = test_ephemeral_type(Wombat("huh"))

        let w2:Wombat iso = test_ephemeral_type2(Wombat("huh2"))