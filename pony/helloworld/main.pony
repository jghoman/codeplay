
trait Named
  fun get_name(): String

interface HasName
  fun name(): String

class Wombat is Named
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

  fun get_name(): String => name

actor Aardvark
  let name: String
  var _hunger_level: U64 = 0
  
  new create(name': String) =>
    name = name'

  be eat(amount: U64) =>
    _hunger_level = _hunger_level - amount.min(_hunger_level)

// 2 marker values
primitive OpenedDoor
primitive ClosedDoor

type DoorState is (OpenedDoor | ClosedDoor)

// A collection of functions
primitive BasicMath
  fun add(a: U64, b: U64): U64 => a + b

  fun multiply(a: U64, b: U64): U64 => a * b

primitive Red   fun apply(): U32 => 0xFF0000FF
primitive Green fun apply(): U32 => 0x00FF00FF
primitive Blue  fun apply(): U32 => 0x0000FFFF

type Colour is (Red | Blue | Green)

trait HasFirstName
  fun name(): String => "Bob"

trait HasAge
  fun age(): U32 => 42

trait HasAddress
  fun address(): String => "3 Abbey Road"

type Person is (HasName & HasAge & HasAddress)

class Foo
  var _count:I32 = 0

  fun say_hi():String => "hi"

  fun ref apply() => _count = _count + 1

  fun get_count():I32 => _count

class UpdateSugar
  let _env:Env

  new create(env': Env) =>
    _env = env'
    _env.out.print("In create")

  fun update(someText:String, value: I32) =>
    _env.out.print("In update with " + someText + " and " + value.string())

actor Main
  fun factorial(x: I32): I32 ? =>
    if x < 0 then error end
    if x == 0 then
      1
    else
      x * factorial(x - 1)?
    end

  new create(env: Env) =>
    let defaultWombat = Wombat("Fantastibat")
    let hungryWombat = Wombat.hungry("Nomsbat", 12)

    env.out.print("Hmmmm")
    // env.out.print("Wombat = " + defaultWombat.string())

    let doorState : DoorState = ClosedDoor
    let isDoorOpen : Bool = match doorState
      | OpenedDoor => true
      | ClosedDoor => false
    end

    env.out.print("Is the door open? " + isDoorOpen.string())
    env.out.print("2 + 3 = " + BasicMath.add(2, 3).string())

    let anArdvark = Aardvark("Aardy")
    anArdvark.eat(22)

    let myColour = Green
    env.out.print("My colour is " + myColour.apply().string())

    try
      let ans = factorial(-3)?
    else
      env.out.print("Couldn't calculate factorial -3")
    end

    let foo = Foo
    env.out.print("foo = " + foo.say_hi())

    foo()
    foo()
    env.out.print("count = " + foo.get_count().string())

    // Update sugar
    let updateSugar = UpdateSugar(env)
    updateSugar("Hello") = 32