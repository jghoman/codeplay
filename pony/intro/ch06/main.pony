actor Main
    // Simple use of matching directly on values
    fun match_on_values(x:U32): String =>
        match x
        | 1 => "one"
        | 2 => "two"
        | 3 => "three"
        | 5 => "not four"
        else
            "something else"
        end

    fun match_foos(f:Foo): String =>
        // Yep, not the most efficient take...
        match f
        | Foo(1) => "one"
        | Foo(2) => "two"
        | Foo(3) => "three"
        | Foo(5) => "not four"
        else
            "something else"
        end

    fun match_type_and_value(x: (U32 | String | None)): String =>
        match x
        | None => "none"
        | 2    => "two"
        | 3    => "three"
        | "5"  => "not four"
        else
            "something else"
        end

    fun match_and_capture(x: (U32 | String | None)): String =>
        match x
        | None => "none"
        | 2 => "two"
        | 3 => "three"
        | let u: U32 => "other integer"
        | let s: String => s
        // Current tutorial has this, but it's not necessary as matches are exhaustive
        // TODO: Open up ticket to fix this in tutorial.
        // else
        //     "something else"
        end

    fun match_on_more_than_one_operand(x: (String | None), y: U32): String =>
        match (x,y)
        // Current tutorial has this, but compile fails
        // | (None, let y: U32) => "none"
        // TODO: Open up a ticket to fix this in tutorial
        | (None, let y': U32) => "none"
        | (let s: String, 2) => s + " two"
        | (let s: String, 3) => s + " three"
        | (let s: String, let u: U32) => s + " other integer"
        else
            "something else"
        end
    
    fun match_on_more_than_one_ignored_operand(x: (String | None), y: U32): String =>
        match (x,y)
        | (None, _) => "none"
        | (let s: String, 2) => s + " two"
        else
            "Something else"
        end

    fun match_with_guards(x: (String | None), y: U32): String =>
        match (x, y)
        | (None, _) => "none"
        | (let s: String, 2) => s + " two"
        | (let s: String, 3) => s + " three"
        | (let s: String, let u: U32) if u > 14 => s + " other big integer"
        | (let s: String, _) => s + " other small integer"
         else
            "something else"
        end

    new create(env: Env) =>
        env.out.print("match_on_values(5) = " + match_on_values(5))
        
        env.out.print("match_foos(Foo(3)) = " + match_foos(Foo(3)))
        
        env.out.print("match_type_and_value(None) = " + match_type_and_value(None))
        env.out.print("match_type_and_value(2) = " + match_type_and_value(2))
        env.out.print("match_type_and_value(\"5\") = " + match_type_and_value("5"))
        
        env.out.print("match_and_capture(99) = " + match_and_capture(99))

        env.out.print("match_on_more_than_one_operand(\"hi\", 2) = " + match_on_more_than_one_operand("hi", 2))

        env.out.print("match_on_more_than_one_ignored_operand(\"yo\", 2) = " + match_on_more_than_one_ignored_operand("yo", 2))

        env.out.print("match_with_guards(\"yo\", 2) = " + match_with_guards("yo", 2))
        env.out.print("match_with_guards(\"yo\", 16) = " + match_with_guards("yo", 16))
        env.out.print("match_with_guards(\"yo\", 12) = " + match_with_guards("yo", 12))

    class Foo
        var _x: U32

        new create(x: U32) =>
            _x = x

        fun eq(that: Foo): Bool =>
            _x == that._x