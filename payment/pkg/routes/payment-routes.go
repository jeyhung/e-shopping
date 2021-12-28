package routes

import (
	"github.com/gorilla/mux"
	"github.com/jeyhung/e-shopping/payment/controllers"
)

var RegisterPaymentRoutes = func(router *mux.Router) {
	router.HandleFunc("/payments/", controllers.Pay).Methods("POST")
	router.HandleFunc("/payments/{transaction}", controllers.CheckTransaction).Methods("GET")
}
