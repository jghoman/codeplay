use "collections"

// A Munchausen number is a natural number n the sum of whose digits (in base 10), each raised to the power of itself, equals n.
// 
// (Munchausen is also spelled: Münchhausen.)
// 
// For instance: 3435 = 3^3 + 4^4 + 3^3 + 5^5
// 
// Task
// Find all Munchausen numbers between 1 and 5000

actor Main
    let env:Env
    
    // Apparently there is no pow for integers in the stdlib... ?
    fun pow(i: USize, j: USize): USize =>
        var product:USize = 1
        for k in Range(0, j) do
            product = product * i
        end
        
        product
    
    fun is_munchausen(i:USize): Bool =>
        var sum:USize = 0
        var num:USize = i

        while num > 0 do
            let digit = num % 10
            num = num / 10
            sum = sum + pow(digit, digit)
        end
    sum == i

    new create(env': Env) =>
        env = env'
        for i in Range(1, 5000) do
            if is_munchausen(i) then
                env.out.print(i.string() + " is a Munchausen number.")
            end
        end