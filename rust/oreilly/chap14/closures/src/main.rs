
struct City {
    name: String,
    population: i64,
    county: String
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

fn main() {
    println!("Hello, world!");
}
