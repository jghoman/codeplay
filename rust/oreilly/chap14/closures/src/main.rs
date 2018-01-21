
struct City {
    name: String,
    population: i64,
    county: String,
    monster_attack_risk: f64
}

fn city_population_descending(city: &City) -> i64 {
    -city.population
}

fn sort_cities1(cities: &mut Vec<City>) {
    cities.sort_by_key(city_population_descending);
}

fn sort_cities2(cities: &mut Vec<City>) {
    cities.sort_by_key(|city| -city.population);
}

fn count_selected_cities(cities:&Vec<City>, test_fn:fn(&City) -> bool) -> usize
{
    let mut count = 0;
    
    for city in cities {
        if test_fn(city) {
            count += 1;
        }
    }
    count
}

fn has_monster_attacks(city:&City) -> bool {
    city.monster_attack_risk > 0.0
}

fn main() {
    let mut cities = Vec::new();
    cities.push(City {name: "New York".to_string(),
        population:23,
        county:"New York".to_string(),
        monster_attack_risk:0.75 });
    cities.push(
        City {name: "Tampa".to_string(),
        population:2233,
        county:"Hillsborough".to_string(),
        monster_attack_risk:0.0 });

    let monsters = count_selected_cities(&cities, has_monster_attacks);

    println!("Hello, world! monster cities = {}", monsters);
}
