package controllers

import (
	"encoding/json"
	"net/http"
	"payment/pkg/models"
	"payment/pkg/utils"

	"github.com/gorilla/mux"
)

var NewPayment models.Payment

func CheckTransaction(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	transaction := vars["transaction"]
	paymentDetails := models.GetPayment(transaction)
	res, _ := json.Marshal(paymentDetails)
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	w.Write(res)
}

func Pay(w http.ResponseWriter, r *http.Request) {
	CreatePayment := &models.Payment{}
	utils.ParseBody(r, CreatePayment)
	p := CreatePayment.CreatePayment()
	res, _ := json.Marshal(p)
	w.WriteHeader(http.StatusCreated)
	w.Write(res)
}
