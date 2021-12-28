package models

import (
	"payment/pkg/configs"

	"github.com/jinzhu/gorm"
)

var db *gorm.DB

type Payment struct {
	gorm.Model
	ExternalID  string  `json:"external_id" form:"external_id" gorm:"column:external_id"`
	Transaction string  `json:"transaction_id" form:"transaction_id" gorm:"column:transaction_id"`
	Amount      float64 `json:"amount" form:"amount" gorm:"column:amount"`
}

func init() {
	configs.Connect()
	db = configs.GetDB()
	db.AutoMigrate(&Payment{})
}

func GetPayments() []Payment {
	var Payments []Payment
	db.Find(&Payments)
	return Payments
}

func GetPayment(transaction string) Payment {
	var getPayment Payment
	db.Where("transaction_id=?", transaction).Find(&getPayment)
	return getPayment
}

func (p *Payment) CreatePayment() *Payment {
	db.NewRecord(p)
	db.Create(&p)
	return p
}
