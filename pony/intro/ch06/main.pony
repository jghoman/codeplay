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

    new create(env: Env) =>
        env.out.print("match_on_values(5) = " + match_on_values(5))
        env.out.print("match_foos(Foo(3)) = " + match_foos(Foo(3)))

    class Foo
        var _x: U32

        new create(x: U32) =>
            _x = x

        fun eq(that: Foo): Bool =>
            _x == that._x