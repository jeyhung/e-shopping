package main

import (
	"fmt"
	"log"
	"net/http"
	"payment/pkg/routes"

	"github.com/gorilla/mux"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

func main() {
	fmt.Println("Payment microservice started!")
	r := mux.NewRouter()
	routes.RegisterPaymentRoutes(r)
	http.Handle("/", r)
	log.Fatal(http.ListenAndServe("localhost:8080", r))
	fmt.Println("Payment microservice running!")
}
