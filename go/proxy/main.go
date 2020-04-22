package main

import (
	"bytes"
	"encoding/json"
	"io/ioutil"
	"log"
	"net/http"
	"net/http/httputil"
	"net/url"
	"os"
	"strings"
)

type requestPayloadStruct struct {
    ProxyCondition string `json:"proxy_condition"`
}

// Get env var or default
func getEnv(key, fallback string) string {
	if value, ok := os.LookupEnv(key); ok {
		return value
	}
	return fallback
}

// Get the port to listen on
func getListenAddress() string {
	port := getEnv("PORT", "1338")
	return ":" + port
}

// Log the env variables required for a reverse proxy
func logSetup() {
	a_condition_url := os.Getenv("A_CONDITION_URL")
	b_condition_url := os.Getenv("B_CONDITION_URL")
	default_condition_url := os.Getenv("DEFAULT_CONDITION_URL")

	log.Printf("Server will run on: %s\n", getListenAddress())
	log.Printf("Redirecting to A url: %s\n", a_condition_url)
	log.Printf("Redirecting to B url: %s\n", b_condition_url)
	log.Printf("Redirecting to Default url: %s\n", default_condition_url)
}

func serveReverseProxy(target string, res http.ResponseWriter, req *http.Request) {
	// parse the url
	url, _ := url.Parse(target)

	// create the reverse proxy
	proxy := httputil.NewSingleHostReverseProxy(url)

	// Update the headers to allow for SSL redirection
	req.URL.Host = url.Host
	req.URL.Scheme = url.Scheme
	req.Header.Set("X-Forwarded-Host", req.Header.Get("Host"))
	req.Host = url.Host

	proxy.ServeHTTP(res, req)
}

func requestBodyDecoder(request *http.Request) *json.Decoder {
    // Read body to buffer
    body, err := ioutil.ReadAll(request.Body)
    if err != nil {
        log.Printf("Error reading body: %v", err)
        panic(err)
    }

    request.Body = ioutil.NopCloser(bytes.NewBuffer(body))
    return json.NewDecoder(ioutil.NopCloser(bytes.NewBuffer(body)))
}

func parseRequestBody(request *http.Request) requestPayloadStruct {
    decoder := requestBodyDecoder(request)

    var requestPayload requestPayloadStruct
    err := decoder.Decode(&requestPayload)

    if err != nil {
        panic(err)
    }

    return requestPayload
}

func logRequestPayload(requestPayload requestPayloadStruct, proxyUrl string) {
    log.Printf("proxy_condition: %s, proxy_url: %s\n", requestPayload.ProxyCondition, proxyUrl)
}

func getProxyUrl(proxyConditionRaw string) string {
    proxyCondition := strings.ToUpper(proxyConditionRaw)

    a_condition_url := os.Getenv("A_CONDITION_URL")
    b_condition_url := os.Getenv("B_CONDITION_URL")
    default_condition_url := os.Getenv("DEFAULT_CONDITION_URL")

    if proxyCondition == "A" {
        return a_condition_url
    }

    if proxyCondition == "B" {
        return b_condition_url
    }

    return default_condition_url
}

// Given a request, send it to the appropriate url
func handleRequestAndDirect(res http.ResponseWriter, req *http.Request) {
	requestPayload := parseRequestBody(req)

	url := getProxyUrl(requestPayload.ProxyCondition)

	logRequestPayload(requestPayload, url)

	serveReverseProxy(url, res, req)
}

func main() {
	log.Printf("Hi!")
	logSetup()

	// start server
	http.HandleFunc("/", handleRequestAndDirect)
	if err := http.ListenAndServe(getListenAddress(), nil); err != nil {
		panic(err)
	}

}
