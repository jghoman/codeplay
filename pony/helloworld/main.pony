class Wombat
  let name: String
  var _hunger_level: U64
  var _thirst_level: U64 = 1

  new create(name': String) =>
    name = name'
    _hunger_level = 0

  new hungry(name': String, hunger': U64) =>
    name = name'
    _hunger_level = hunger'

  fun hunger(): U64 => _hunger_level

  fun ref set_hunger(to: U64 = 0): U64 => _hunger_level = to

actor Main
  new create(env: Env) =>
    let defaultWombat = Wombat("Fantastibat")
    let hungryWombat = Wombat.hungry("Nomsbat", 12)

    env.out.print("Hmmmm")
    //env.out.print(defaultWombat)
