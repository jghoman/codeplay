use std::cmp::max;
use std::cell::Cell;

struct Character<'a> { 
  name: &'a str,
  max_hit_points: i32,
  curr_hit_points: i32,
  points_per_attack: i32,
}

impl<'a> Character<'a> {
  fn new(name:  &'a str , max_hit_points: i32, points_per_attack: i32) -> Character<'a> {
    Character {name: name, 
      max_hit_points: max_hit_points,
      curr_hit_points: max_hit_points,
      points_per_attack: points_per_attack
    }
  }

  fn is_alive(&self) -> bool {
    self.curr_hit_points > 0
  }

  fn decrease_health(&self, hit_points:i32) -> Character {
    Character {name: self.name,
      max_hit_points: self.max_hit_points,
      curr_hit_points: max(0, self.curr_hit_points - hit_points),
      points_per_attack: self.points_per_attack
    }
  } 

  fn attack(&self, c:Character) {
  }
}

#[test]
fn decrease_health_works_correctly() {
  let starting_health = 50;
  let bob = Character::new("Bob", starting_health, 32);
  let dead_bob = bob.decrease_health(starting_health * 2);
  assert!(dead_bob.curr_hit_points == 0);
  assert!(dead_bob.is_alive() == false);

  let surviving_bob = bob.decrease_health(starting_health - 1);
  println!("{}", surviving_bob.curr_hit_points);
  assert!(surviving_bob.curr_hit_points == 1);
  assert!(surviving_bob.is_alive() == true);
}

fn main() {
}
