package models

import (
	"github.com/jinzhu/gorm"
)

var db *gorm.DB

type Payment struct {
	gorm.model
	Transaction string `gorm:""json:"transaction"`
}
