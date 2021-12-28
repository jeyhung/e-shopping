package routes

import (
	"payment/pkg/controllers"

	"github.com/gorilla/mux"
)

var RegisterPaymentRoutes = func(router *mux.Router) {
	router.HandleFunc("/payments/", controllers.Pay).Methods("POST")
	router.HandleFunc("/payments/{transaction}", controllers.CheckTransaction).Methods("GET")
}
