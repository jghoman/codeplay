use std::collections::HashMap;

struct Request {
    method: String,
    url: String,
    headers: HashMap<String, String>,
    body: Vec<u8>
}

struct Response {
    code: u32,
    headers: HashMap<String, String>,
    body: Vec<u8>
}

type BoxedCallBack = Box<Fn(&Request) -> Response>;

struct BasicRouter {
    routes: HashMap<String, BoxedCallBack>
}

impl BasicRouter {
    fn new() -> BasicRouter {
        BasicRouter {routes: HashMap::new() }
    }

    fn add_route<C>(&mut self, url: &str, callback: C) 
        where C:Fn(&Request) -> Response + 'static
    {
        self.routes.insert(url.to_string(), Box::new(callback));
    }

    fn not_found_request(&self) -> Response {
        return Response{ code: 404, headers:HashMap::new(), body: Vec::new() };
    }

    fn handle_request(&self, request: &Request) -> Response {
        match self.routes.get(&request.url) {
            None => self.not_found_request(),
            Some(callback) => callback(request)
        }
    }

}

fn get_form_response() -> Response {
  return Response {code: 32, headers: HashMap::new(), body: Vec::new() };
}

fn get_gcd_response(_: &Request) -> Response {
    return Response { code: 402, headers: HashMap::new(), body: Vec::new() };
}

fn main() {
    let mut router = BasicRouter::new();
    router.add_route("/", |_| get_form_response());
    router.add_route("/gcd", |req| get_gcd_response(req));
}
