// From https://blog.angular-university.io/javascript-for-java-developers/
// create an empty object - no class was needed !!
var superhero = {};
 
superhero.name = 'Superman';  
superhero.strength = 100;

var flyFunction = function() {
  console.log('Flying like a bird!')
}

superhero.fly = flyFunction
console.log(superhero)
superhero.fly();

var superman = {
  heroName: 'Superman',

  sayHello: function() {
    console.log("Hello, I'm " + this.heroName);
  }
}

// Handling 'this'
superman.sayHello()

var failThis = superman.sayHello

// Wow...
failThis();

failThis.call(superman);

// prototype inheritance
var avengersHero = {
  editor: 'Marvel'
};

var ironMan = {};

ironMan.__proto__ = avengersHero;

console.log('Iron Man is copyrighted by ' + ironMan.editor);


// Constructors
function SuperHero(name, strength) {
  this.name = name;
  this.strength = strength;
}

var superman = new SuperHero('Superman', 100);

console.log('Hello, my name is ' + superman.name);

// Alternative to new
var superHeroPrototype = {
  sayHello:  function() {
    console.log('Hello, my name is ' + this.name);
  }
};

var superman = Object.create(superHeroPrototype);
superman.name = 'Superman';

// Module encapsulation
function createHero(heroName) {
  var name = heroName;

  return {
    fly: function(destination) {
      console.log(name + ' flying to ' + destination);
    }
  };
}

var flyingHero = createHero("bob");
flyingHero.fly("Barbuda")

function counterLoop() {
  console.log('counter before declaration = ' + i);
  
  for (var i = 0; i < 3; i++) {
    console.log('counter = ' + i);
  }

  console.log('counter after loop = ' + i);
}

counterLoop();
